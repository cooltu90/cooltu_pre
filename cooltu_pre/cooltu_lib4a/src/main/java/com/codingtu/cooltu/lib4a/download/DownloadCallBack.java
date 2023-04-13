package com.codingtu.cooltu.lib4a.download;

public interface DownloadCallBack {

    void onStart();//下载开始

    void onProgress(long totalLen, long currentLen);//下载进度

    void onFinish(String path);//下载完成

    void onFail(Throwable throwable);//下载失败

}
