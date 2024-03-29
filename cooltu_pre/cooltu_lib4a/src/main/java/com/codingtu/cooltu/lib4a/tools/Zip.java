package com.codingtu.cooltu.lib4a.tools;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Zip implements OnDestroy {

    public static interface Pass {
        public boolean pass(File file);
    }

    public static interface ZipedPathDeal {
        public String deal(String path);
    }

    public static interface OnError {
        public void onError(Throwable throwable);

    }

    public static interface OnFinish {
        public void onFinish(File file);
    }


    public static interface OnProgress {
        public void onProgress(long totalLen, long zipedLen);
    }

    public static interface OnStart {
        public void onStart();
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    private File src;
    private File desc;
    private long totalLen;
    private long zipedLen;
    private Integer cacheSize;
    private Pass pass;
    private ZipedPathDeal zipedPathDeal;
    private OnProgress onProgress;
    private OnError onError;
    private OnFinish onFinish;
    private OnStart onStart;
    private long lastTime;

    private Zip(File src) {
        this.src = src;
    }

    @Override
    public void destroy() {
        pass = null;
        zipedPathDeal = null;
        onProgress = null;
        onError = null;
        onFinish = null;
        onStart = null;
    }

    public static Zip src(File src) {
        return new Zip(src);
    }

    public static Zip src(String srcPath) {
        return src(new File(srcPath));
    }

    public Zip desc(File desc) {
        this.desc = desc;
        return this;
    }

    public Zip desc(String descPath) {
        return desc(new File(descPath));
    }

    public Zip progress(OnProgress onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public Zip pass(Pass pass) {
        this.pass = pass;
        return this;
    }

    public Zip error(OnError onError) {
        this.onError = onError;
        return this;
    }

    public Zip finish(OnFinish onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public Zip zipedPathDeal(ZipedPathDeal zipedPathDeal) {
        this.zipedPathDeal = zipedPathDeal;
        return this;
    }

    public Zip cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }


    public Zip start(OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    public void zip() {
        if (!src.exists()) {
            onError(new RuntimeException("没有找到需要压缩打包的文件"));
            return;
        }

        if (desc == null) {
            desc = new File(src.getAbsolutePath() + FileType.d_ZIP);
        }

        if (onProgress != null) {
            onProgress.onProgress(totalLen, 0);
        }

        if (cacheSize == null) {
            cacheSize = 1024 * 512;
        }

        if (onStart != null) {
            onStart.onStart();
        }

        Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                        String zipPath = desc.getAbsolutePath();
                        totalLen = getLength(src);
                        lastTime = System.currentTimeMillis();
                        zip(src, zipPath, emitter);
                        emitter.onNext(totalLen);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long currentLen) throws Exception {
                        if (onProgress != null) {
                            onProgress.onProgress(totalLen, currentLen);
                        }

                        if (currentLen == totalLen) {
                            //完成
                            if (onFinish != null) {
                                onFinish.onFinish(desc);
                            }
                            destroy();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onError(throwable);
                    }
                });

    }

    private void zip(File needPressFile, String zipFilePath, ObservableEmitter<Long> emitter) throws Exception {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFilePath));
            recursivePressFile(out, needPressFile, "", emitter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    Logs.e(e);
                }
            }
        }
    }

    private void recursivePressFile(ZipOutputStream out, File needPressFile, String parentDirName, ObservableEmitter<Long> emitter) throws Exception {
        if (needPressFile.isDirectory()) {
            if (pass != null && pass.pass(needPressFile)) {
                return;
            }
            File[] files = needPressFile.listFiles();
            for (File file : files) {
                recursivePressFile(out, file, (StringTool.isBlank(parentDirName) ? ("") : (parentDirName + Constant.SEPARATOR)) + needPressFile.getName(), emitter);
            }
        } else {
            if (pass != null && pass.pass(needPressFile)) {
                return;
            }
            pressFile(out, needPressFile, parentDirName, emitter);

        }

    }

    private void pressFile(ZipOutputStream out, File file, String parentDirName, ObservableEmitter<Long> emitter) throws Exception {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            String path = parentDirName + Constant.SEPARATOR + file.getName();
            if (zipedPathDeal != null) {
                path = zipedPathDeal.deal(path);
            }
            out.putNextEntry(new ZipEntry(path));

            byte[] buff = new byte[cacheSize];
            int len = 0;

            while ((len = input.read(buff)) != -1) {
                out.write(buff, 0, len);
                zipedLen += len;

                long nowTime = System.currentTimeMillis();
                if (nowTime - lastTime > 100) {
                    if (zipedLen < totalLen)
                        emitter.onNext(zipedLen);
                    lastTime = nowTime;
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    Logs.e(e);
                }
            }
            input = null;
        }

    }

    private long getLength(File file) {
        long len = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < CountTool.count(files); i++) {
                len += getLength(files[i]);
            }
        } else {
            if (pass != null && pass.pass(file)) {
                return 0;
            }
            len = file.length();

        }
        return len;
    }

    private void onError(Throwable throwable) {
        if (onError != null) {
            onError.onError(throwable);
        }
        destroy();
    }

}
