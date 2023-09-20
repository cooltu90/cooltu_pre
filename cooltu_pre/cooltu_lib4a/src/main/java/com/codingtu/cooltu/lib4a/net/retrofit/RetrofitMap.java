package com.codingtu.cooltu.lib4a.net.retrofit;

import java.util.HashMap;

import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.net.interceptor.LogInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitMap extends HashMap<String, Retrofit> {

    private static String DEFAULT_GROUP = "default_group";

    public Retrofit get(String group, String baseUrl,
                        RetrofitCreater retrofitCreater,
                        OkHttpClientCreater okHttpClientCreater) {
        if (StringTool.isBlank(group)) {
            group = DEFAULT_GROUP;
        }
        String retrofitName = retrofitName(group, baseUrl);

        Retrofit retrofit = get(retrofitName);
        if (retrofit == null) {
            if (okHttpClientCreater == null) {
                okHttpClientCreater = CoreConfigs.configs().getDefaultOkHttpClientCreater();
            }

            OkHttpClient okHttpClient = null;
            if (okHttpClientCreater != null) {
                okHttpClient = okHttpClientCreater.create();
            } else {
                okHttpClient = createOkHttpClient();
            }

            if (retrofitCreater == null) {
                retrofitCreater = CoreConfigs.configs().getDefaultRetrofitCreater();
            }

            if (retrofitCreater == null) {
                retrofit = createRetrofit(okHttpClient, baseUrl);
            } else {
                retrofit = retrofitCreater.create(okHttpClient, baseUrl);
            }
            put(retrofitName, retrofit);
        }
        return retrofit;
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        CoreConfigs.configs().addInterceptorForDefaultOkHttpClient(builder);
        if (CoreConfigs.configs().isLogHttpConnect()) {
            builder.addInterceptor(new LogInterceptor());
        }
        return builder.build();
    }

    private static Retrofit createRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        return new Retrofit.Builder().client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl).build();
    }

    private static String retrofitName(String group, String baseUrl) {
        return group + "::" + baseUrl;
    }
}
