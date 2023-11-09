package com.codingtu.cooltu.lib4a.tools;

import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

import java.io.File;

public class Upload implements OnDestroy {

    public static interface OnProgress {
        public void onProgress(long totalLen, long currentSize);
    }

    public static interface OnStart {
        public void onStart();
    }

    public static interface OnError {
        public void onError(Throwable throwable);

    }

    public static interface OnFinish {
        public void onFinish();
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    private PostRequest<String> post;

    private File file;
    private String fileKey;

    private OnFinish onFinish;
    private OnError onError;
    private OnProgress onProgress;
    private OnStart onStart;

    @Override
    public void destroy() {
        onFinish = null;
        onError = null;
        onProgress = null;
        onStart = null;
        file = null;
        post = null;
    }


    Upload(String url) {
        if (StringTool.isBlank(url)) {
            onError(new RuntimeException("链接地址不能未空"));
            return;
        }

        post = OkGo.<String>post(url);
        post.tag(this).isMultipart(true);
    }

    public static Upload url(String url) {
        return new Upload(url);
    }

    public Upload header(String key, String value) {
        post.headers(key, value);
        return this;
    }

    public Upload file(String key, File file) {
        this.fileKey = key;
        this.file = file;
        return this;
    }

    public Upload file(String key, String filePath) {
        return file(key, new File(filePath));
    }

    public Upload param(String key, String value) {
        post.params(key, value);
        return this;
    }

    public Upload error(OnError onError) {
        this.onError = onError;
        return this;
    }

    public Upload finish(OnFinish onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public Upload progress(OnProgress onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public Upload start(OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    public void upload() {
        if (post == null) {
            return;
        }

        if (!file.exists()) {
            onError(new RuntimeException("上传文件不存在"));
            return;
        }

        if (onStart != null) {
            onStart.onStart();
        }

        post.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (onFinish != null) {
                    onFinish.onFinish();
                }
                destroy();
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                if (onProgress != null) {
                    onProgress.onProgress(progress.totalSize, progress.currentSize);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Upload.this.onError(response.getException());
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
