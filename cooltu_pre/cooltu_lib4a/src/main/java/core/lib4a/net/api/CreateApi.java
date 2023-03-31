package core.lib4a.net.api;

import core.lib4a.net.bean.CoreSendParams;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

public interface CreateApi {
    public Flowable<Result<ResponseBody>> create(Retrofit retrofit, CoreSendParams params);
}
