package com.codingtu.cooltu.lib4a.tools;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4a.act.OnDestroy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UnZip implements OnDestroy {

    public static interface OnError {
        public void onError(Throwable throwable);

    }

    public static interface OnFinish {
        public void onFinish(long totalLen);
    }


    public static interface OnProgress {
        public void onProgress(long totalLen, long zipedLen);
    }

    public static interface OnStart {
        public void onStart();
    }

    public static interface ZipedNameDeal {
        public String deal(String zipedName);
    }


    /**************************************************
     *
     *
     *
     **************************************************/
    private File src;
    private File destDir;
    private Integer cacheSize;
    private long totalLen;

    private OnError onError;
    private OnFinish onFinish;
    private OnProgress onProgress;
    private OnStart onStart;

    private ZipedNameDeal zipedNameDeal;

    UnZip() {
    }

    @Override
    public void destroy() {
        onError = null;
        onFinish = null;
        onProgress = null;
        onStart = null;
        zipedNameDeal = null;
    }

    public static UnZip src(String srcPath) {
        return src(new File(srcPath));
    }

    public static UnZip src(File src) {
        UnZip unZip = new UnZip();
        unZip.src = src;
        return unZip;
    }

    public UnZip destDir(String destDirPath) {
        return destDir(new File(destDirPath));
    }

    public UnZip destDir(File destDir) {
        this.destDir = destDir;
        return this;
    }

    public UnZip error(OnError onError) {
        this.onError = onError;
        return this;
    }

    public UnZip finish(OnFinish onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public UnZip progress(OnProgress onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public UnZip cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }


    public UnZip start(OnStart onStart) {
        this.onStart = onStart;
        return this;
    }


    public UnZip zipedNameDeal(ZipedNameDeal zipedNameDeal) {
        this.zipedNameDeal = zipedNameDeal;
        return this;
    }

    public void unzip() {
        if (!src.exists()) {
            onError(new RuntimeException("没有找到需要解压的文件"));
            return;
        }

        if (destDir == null) {
            destDir = new File(src.getParentFile().getAbsolutePath());
        }

        if (!destDir.exists()) {
            destDir.mkdirs();
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

                        ZipFile zip = new ZipFile(src);

                        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                            ZipEntry entry = (ZipEntry) entries.nextElement();
                            totalLen += entry.getSize();
                        }

                        String destDirPath = destDir.getAbsolutePath();

                        emitter.onNext(0l);

                        long currentSize = 0;
                        long lastTime = System.currentTimeMillis();
                        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                            ZipEntry entry = (ZipEntry) entries.nextElement();
                            if (entry.getSize() <= 0) {
                                continue;
                            }
                            InputStream in = zip.getInputStream(entry);
                            String zipEntryName = entry.getName();
                            if (zipedNameDeal != null) {
                                zipEntryName = zipedNameDeal.deal(zipEntryName);
                            }
                            //解决路径不兼容的问题
                            String outPath = (destDirPath + Constant.SEPARATOR + zipEntryName).replace('\\', '/');
                            File outFile = new File(outPath);
                            SDCardTool.createFileDir(outFile);
                            OutputStream out = new FileOutputStream(outFile);
                            byte[] buf1 = new byte[cacheSize];
                            int len;
                            while ((len = in.read(buf1)) > 0) {
                                currentSize += len;
                                out.write(buf1, 0, len);
                                long nowTime = System.currentTimeMillis();
                                if (nowTime - lastTime > 100) {
                                    if (currentSize < totalLen)
                                        emitter.onNext(currentSize);
                                    lastTime = nowTime;
                                }
                            }
                            in.close();
                            out.close();
                        }
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
                                onFinish.onFinish(totalLen);
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

    private void onError(Throwable throwable) {
        if (onError != null) {
            onError.onError(throwable);
        }
        destroy();
    }

}
