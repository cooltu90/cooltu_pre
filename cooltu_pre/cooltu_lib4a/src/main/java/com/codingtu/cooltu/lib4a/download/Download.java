package com.codingtu.cooltu.lib4a.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.constant.DownloadStatus;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.net.NetTool;
import com.codingtu.cooltu.lib4a.net.api.API;
import com.codingtu.cooltu.lib4a.net.api.CreateApi;
import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;
import com.codingtu.cooltu.lib4a.net.netback.NetBackI;
import com.codingtu.cooltu.lib4a.tools.HandlerTool;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cooltu.lib4j.tools.StringTool;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

public class Download {

    private String baseUrl;
    private String url;
    private File file;
    private File dir;
    private String fileName;
    private DownloadCallBack callBack;

    public Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case DownloadStatus.START:
                    callBack.onStart();
                    break;
                case DownloadStatus.FAIL:
                    callBack.onFail((Throwable) msg.obj);
                    break;
                case DownloadStatus.FINISH:
                    callBack.onFinish((String) msg.obj);
                    break;
                case DownloadStatus.PROGRESS:
                    long[] ls = (long[]) msg.obj;
                    callBack.onProgress(ls[0], ls[1]);
                    break;
            }
        }
    };


    public static Download url(String url) {
        Download download = new Download();
        int i = url.indexOf("/", url.indexOf(":") + 3);
        download.baseUrl = url.substring(0, i + 1);
        download.url = url.substring(i + 1);
        return download;
    }

    public static Download url(String baseUrl, String url) {
        Download download = new Download();
        download.baseUrl = baseUrl;
        download.url = url;
        return download;
    }

    public Download fileDir(String dir) {
        this.dir = new File(dir);
        return this;
    }

    public Download fileDir(File dir) {
        this.dir = dir;
        return this;
    }

    public Download fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Download file(String path) {
        return file(new File(path));
    }

    public Download file(File file) {
        this.file = file;
        return this;
    }

    public Download callBack(DownloadCallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public void start() {
        if (StringTool.isBlank(baseUrl) || StringTool.isBlank(url)) {
            return;
        }

        if (file == null) {
            if (dir == null) {
                return;
            }
            if (StringTool.isBlank(fileName)) {
                fileName = url.substring(url.lastIndexOf("/") + 1);
            }
            file = new File(dir, fileName);
        }

        NetTool.api(new CreateApi() {
            @Override
            public Flowable<Result<ResponseBody>> create(Retrofit retrofit, CoreSendParams params) {
                return retrofit.create(DownloadService.class).download(url);
            }
        }, "downloadBack", baseUrl, null).io(new NetBackI() {
            @Override
            public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
                if (result.isError()) {
                    handler.sendMessage(obtainOnFailMessage(result.error()));
                } else {
                    writeFileFromIS(file, result.response().body().byteStream(), result.response().body().contentLength());
                }
            }
        });

    }

    private static int sBufferSize = 8192;

    private void writeFileFromIS(File file, InputStream is, long totalLength) {
        //开始下载
        handler.sendEmptyMessage(DownloadStatus.START);

        //创建文件
        if (!file.exists()) {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                handler.sendMessage(obtainOnFailMessage(e));
            }
        }

        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;
                //计算当前下载进度
                handler.sendMessage(obtainOnProgressMessage(totalLength, currentLength));
            }
            //下载完成，并返回保存的文件路径
            handler.sendMessage(obtainOnFinishMessage(file.getAbsolutePath()));
        } catch (IOException e) {
            handler.sendMessage(obtainOnFailMessage(e));
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Message obtainMessage(int what, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        return message;
    }

    private Message obtainOnFailMessage(Throwable error) {
        return obtainMessage(DownloadStatus.FAIL, error);
    }

    private Message obtainOnFinishMessage(String path) {
        return obtainMessage(DownloadStatus.FINISH, path);
    }

    private Message obtainOnProgressMessage(long totalLen, long currentLen) {
        long[] longs = {totalLen, currentLen};
        return obtainMessage(DownloadStatus.PROGRESS, longs);
    }

}
