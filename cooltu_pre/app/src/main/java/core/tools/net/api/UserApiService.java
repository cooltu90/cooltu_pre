package core.tools.net.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface UserApiService {
    @retrofit2.http.POST("upload")
    Flowable<Result<ResponseBody>> getUser(
            @retrofit2.http.Body okhttp3.RequestBody body
    );

}
