package com.codingtu.cooltu.lib4a.net.api;

import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;

import java.util.ArrayList;
import java.util.List;

import com.codingtu.cooltu.lib4a.net.netback.NetBackI;
import com.codingtu.cooltu.lib4a.net.retrofit.OkHttpClientCreater;
import com.codingtu.cooltu.lib4a.net.retrofit.RetrofitCreater;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class APIMock implements API {
    /**************************************************
     *
     * 回调的方法名字,默认的为api的方法名，可以自己设置名字
     *
     **************************************************/
    private String m;
    private String group;
    private String baseUrl;
    private CoreSendParams params;

    public APIMock(String m, String baseUrl) {
        this.baseUrl = baseUrl;
        this.m = m;
    }

    public APIMock(String m, String baseUrl, CoreSendParams params) {
        this.baseUrl = baseUrl;
        this.m = m;
        this.params = params;
    }

    /**************************************************
     *
     * 自定义配置方法
     *
     **************************************************/

    @Override
    public API b(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public API m(String m) {
        this.m = m;
        return this;
    }

    @Override
    public API group(String group) {
        this.group = group;
        return this;
    }

    @Override
    public API retrofit(RetrofitCreater retrofitCreater) {
        return this;
    }

    @Override
    public API okHttp(OkHttpClientCreater okHttpClientCreater) {
        return this;
    }

    /************************************************
     *
     * add方法
     *
     ************************************************/

    protected List<Object> objs;

    @Override
    public API add(Object obj) {
        if (objs == null) {
            objs = new ArrayList<Object>();
        }
        objs.add(obj);
        return this;
    }

    @Override
    public API set(List<Object> objs) {
        this.objs = objs;
        return this;
    }

    /**************************************************
     *
     * 请求方法
     *
     **************************************************/

    @Override
    public void main(NetBackI helper) {
        main(helper, false);
    }

    @Override
    public void main(NetBackI helper, boolean isForceNewThread) {
        run(helper, AndroidSchedulers.mainThread(), isForceNewThread);
    }

    @Override
    public void io(NetBackI helper) {
        io(helper, false);
    }

    @Override
    public void io(NetBackI helper, boolean isForceNewThread) {
        run(helper, Schedulers.trampoline(), isForceNewThread);
    }

    private void run(final NetBackI helper, Scheduler resultScheduler, boolean isForceNewThread) {
        helper.accept(m, null, params, objs);
    }

}
