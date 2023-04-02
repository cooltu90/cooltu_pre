package com.codingtu.cooltu.lib4a.net.interceptor;

import com.codingtu.cooltu.lib4a.bean.KVS;
import com.codingtu.cooltu.lib4a.net.NetTool;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class ParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();

        if ("POST".equals(method)) {
            KVS params = NetTool.getParamsPost(request).add(getPublicParams());
            FormBody.Builder builder = new FormBody.Builder();
            for (int i = 0; i < params.size(); i++) {
                builder.addEncoded(params.key(i), params.value(i));
            }
            request = request.newBuilder().post(builder.build()).build();
        } else if ("GET".equals(method)) {
            KVS params = getPublicParams();
            HttpUrl.Builder builder = request.url().newBuilder();
            for (int i = 0; i < params.size(); i++) {
                builder.addQueryParameter(params.key(i), params.value(i));
            }
            request = request.newBuilder().url(builder.build()).build();
        }

        return chain.proceed(request);
    }

    protected abstract KVS getPublicParams();

}
