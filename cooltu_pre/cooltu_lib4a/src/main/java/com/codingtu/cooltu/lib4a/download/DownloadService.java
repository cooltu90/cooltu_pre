package com.codingtu.cooltu.lib4a.download;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadService {

    @Streaming
    @GET
    Flowable<Result<ResponseBody>> download(@Url String url);
}

