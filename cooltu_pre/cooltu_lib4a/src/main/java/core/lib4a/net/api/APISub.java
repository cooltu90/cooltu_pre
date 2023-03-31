package core.lib4a.net.api;

import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

import core.lib4a.log.Logs;
import core.lib4a.net.NetTool;
import core.lib4a.net.bean.CoreSendParams;
import core.lib4a.net.netback.NetBackI;
import core.lib4a.net.retrofit.OkHttpClientCreater;
import core.lib4a.net.retrofit.RetrofitCreater;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

public class APISub implements API {
    private CreateApi createApi;
    /**************************************************
     *
     * 回调的方法名字,默认的为api的方法名，可以自己设置名字
     *
     **************************************************/
    private String m;
    private String group;
    private String baseUrl;
    private RetrofitCreater retrofitCreater;
    private OkHttpClientCreater okHttpClientCreater;
    private CoreSendParams params;

    public APISub(CreateApi createApi, String m, String baseUrl) {
        this.createApi = createApi;
        this.baseUrl = baseUrl;
        this.m = m;
    }

    public APISub(CreateApi createApi, String m, String baseUrl, CoreSendParams params) {
        this.createApi = createApi;
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
        this.retrofitCreater = retrofitCreater;
        return this;
    }

    @Override
    public API okHttp(OkHttpClientCreater okHttpClientCreater) {
        this.okHttpClientCreater = okHttpClientCreater;
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

        Scheduler work = null;
        if (isForceNewThread || Looper.getMainLooper().getThread() == Thread.currentThread()) {
            work = Schedulers.io();
        } else {
            work = Schedulers.trampoline();
        }

        Retrofit retrofit = NetTool
                .getRetrofit(group, baseUrl, retrofitCreater, okHttpClientCreater);

        createApi.create(retrofit, params).subscribeOn(work).observeOn(resultScheduler)
                .subscribe(new Consumer<Result<ResponseBody>>() {
                    @Override
                    public void accept(Result<ResponseBody> result) {
                        try {
                            if (helper != null)
                                helper.accept(m, result, params, objs);
                        } catch (Exception e) {
                            Logs.w(e);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        try {
                            if (helper != null)
                                helper.accept(m, Result.<ResponseBody>error(throwable), params, objs);
                        } catch (Exception e) {
                            Logs.w(e);
                        }
                    }
                });

    }

}
