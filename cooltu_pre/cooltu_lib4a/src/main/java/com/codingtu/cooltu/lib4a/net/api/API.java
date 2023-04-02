package com.codingtu.cooltu.lib4a.net.api;

import java.util.List;

import com.codingtu.cooltu.lib4a.net.netback.NetBackI;
import com.codingtu.cooltu.lib4a.net.retrofit.OkHttpClientCreater;
import com.codingtu.cooltu.lib4a.net.retrofit.RetrofitCreater;

public interface API {

    API b(String baseUrl);

    API m(String m);

    API group(String group);

    API retrofit(RetrofitCreater retrofitCreater);

    API okHttp(OkHttpClientCreater okHttpClientCreater);

    API add(Object obj);

    API set(List<Object> objs);

    void main(NetBackI helper);

    void main(NetBackI helper, boolean isForceNewThread);

    void io(NetBackI helper);

    void io(NetBackI helper, boolean isForceNewThread);


}
