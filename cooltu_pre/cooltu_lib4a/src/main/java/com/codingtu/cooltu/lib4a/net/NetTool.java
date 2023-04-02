package com.codingtu.cooltu.lib4a.net;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import cooltu.lib4j.json.JsonTool;
import cooltu.lib4j.json.base.JO;
import cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.bean.KVS;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.net.api.API;
import com.codingtu.cooltu.lib4a.net.api.APISub;
import com.codingtu.cooltu.lib4a.net.api.CreateApi;
import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;
import com.codingtu.cooltu.lib4a.net.retrofit.OkHttpClientCreater;
import com.codingtu.cooltu.lib4a.net.retrofit.RetrofitCreater;
import com.codingtu.cooltu.lib4a.net.retrofit.RetrofitMap;
import com.codingtu.cooltu.lib4a.tools.MediaFileTool;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Retrofit;

public class NetTool {

    private static RetrofitMap map;

    public static Retrofit getRetrofit(String group, String baseUrl, RetrofitCreater retrofitCreater, OkHttpClientCreater okHttpClientCreater) {
        if (map == null) {
            map = new RetrofitMap();
        }
        return map.get(group, baseUrl, retrofitCreater, okHttpClientCreater);
    }

    /************************************************
     *
     * api
     *
     ************************************************/

    public static API api(CreateApi createApi, String code) {
        return api(createApi, code, CoreConfigs.configs().getBaseUrl());
    }

    public static API api(CreateApi createApi, String code, String baseUrl) {
        return new APISub(createApi, code, baseUrl);
    }

    public static API api(CreateApi createApi, String code, CoreSendParams params) {
        return api(createApi, code, CoreConfigs.configs().getBaseUrl(), params);
    }

    public static API api(CreateApi createApi, String code, String baseUrl, CoreSendParams params) {
        return new APISub(createApi, code, baseUrl, params);
    }

    /************************************************
     *
     * params
     *
     ************************************************/

    public static KVS getParamsGet(Request request) {
        KVS params = new KVS();
        HttpUrl url = request.url();
        for (int i = 0; i < url.querySize(); i++) {
            params.add(url.queryParameterName(i), url.queryParameterValue(i));
        }
        Logs.i("getParamsGet:" + params);
        return params;
    }

    public static KVS getParamsPost(Request request) {
        KVS params = getParamsGet(request);
        if (request.body() instanceof FormBody) {
            FormBody body = (FormBody) request.body();
            for (int i = 0; i < body.size(); i++) {
                params.add(body.name(i), body.value(i));
            }
        } else if (request.body() instanceof MultipartBody) {
            MultipartBody multipartBody = (MultipartBody) request.body();
            for (int i = 0; i < multipartBody.size(); i++) {
                MultipartBody.Part part = multipartBody.part(i);
                RequestBody body = part.body();
                if ("text".equals(body.contentType().type())) {
                    try {

                        String name = part.headers().get("Content-Disposition");
                        name = StringTool.getSub(name, "name=", "\"", "\"");

                        Buffer buffer = new Buffer();
                        body.writeTo(buffer);
                        String value = buffer.readString(Charset.forName("UTF-8"));

                        params.add(name, value);

                    } catch (IOException e) {
                        Logs.w(e);
                    }
                }
            }
        } else if (request.body() instanceof RequestBody) {
            try {
                RequestBody body = request.body();
                String type = body.contentType().toString();
                if (StringTool.isNotBlank(type) && type.startsWith("application/json")) {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String json = buffer.readString(Charset.forName("UTF-8"));
                    JO jo = JsonTool.toJO(json);
                    for (String key : jo.keys()) {
                        String string = jo.getString(key);
                        params.add(key, string);
                    }

                }
            } catch (Exception e) {
            }
        }
        Logs.i("getParamsPost:" + params);
        return params;
    }


    /************************************************
     *
     * retrofit body
     *
     ************************************************/
    public static RequestBody toRequestBody(String str) {
        if (StringTool.isBlank(str))
            return null;
        return RequestBody.create(MediaType.parse("text/plain"), str);
    }

    public static RequestBody toJsonBody(String str) {
        if (StringTool.isBlank(str))
            return null;
        return RequestBody.create(MediaType.parse("application/json"), str);
    }


    public static MultipartBody.Part toFilePart(String name, String filePath) {
        File f = new File(filePath);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MediaFileTool.getFileMimeType(filePath)), f);
        return MultipartBody.Part.createFormData(name, f.getName(), requestFile);
    }

    public static RequestBody toRequestBody(File file) {
        return RequestBody.create(MediaType.parse("application/octet-stream"), file);
    }


    public static Map<String, RequestBody> toRequestBodyMap(String paramName, String image) {
        HashMap<String, RequestBody> args = new HashMap<String, RequestBody>();
        File file = new File(image);
        String fileName = paramName + "\";type=image/jpeg; filename=\"" + file.getName() + "";
        args.put(fileName, toRequestBody(file));
        return args;
    }
}
