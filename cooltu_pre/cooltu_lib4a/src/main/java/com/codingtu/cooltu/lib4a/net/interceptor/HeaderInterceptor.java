package com.codingtu.cooltu.lib4a.net.interceptor;

import com.codingtu.cooltu.lib4a.bean.KVS;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.net.NetTool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        try {
            String method = request.method();

            KVS publicHeaders = null;

            if ("GET".equals(method)) {
                publicHeaders = getPublicHeaders(request, "GET", NetTool.getParamsGet(request));
            } else {
                publicHeaders = getPublicHeaders(request, "POST", NetTool.getParamsPost(request));
            }

            if (publicHeaders != null) {
                Request.Builder builder = request.newBuilder();
                for (int i = 0; i < publicHeaders.size(); i++) {
                    builder.addHeader(publicHeaders.key(i), publicHeaders.value(i));
                }
                request = builder.build();
            }
        } catch (Exception e) {
            Logs.w("===HeaderInterceptor=============");
            Logs.w(e);
        }
        return chain.proceed(request);
    }

    protected abstract KVS getPublicHeaders(Request request, String Method, KVS params);

}
