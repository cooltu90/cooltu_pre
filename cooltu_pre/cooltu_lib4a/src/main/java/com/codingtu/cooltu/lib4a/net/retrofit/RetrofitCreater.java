package com.codingtu.cooltu.lib4a.net.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface RetrofitCreater {
    public Retrofit create(OkHttpClient okHttpClient, String baseUrl);
}
