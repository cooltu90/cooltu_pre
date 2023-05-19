package core.tools;

import com.codingtu.cooltu.lib4a.net.NetTool;
import com.codingtu.cooltu.lib4a.net.api.API;
import com.codingtu.cooltu.lib4a.net.api.CreateApi;
import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

public class Net {

    private static final String GET_USER = "getUserBack";





    public static API getUser(java.util.List<com.codingtu.cooltu_pre.bean.MyLabel> labels) {
        core.tools.net.params.GetUserParams params = new core.tools.net.params.GetUserParams();
        params.labels = labels;

        return NetTool.api(new CreateApi() {
            @Override
            public Flowable<Result<ResponseBody>>
            create(Retrofit retrofit, CoreSendParams ps) {
                core.tools.net.params.GetUserParams params = (core.tools.net.params.GetUserParams) ps;

                return retrofit.create(core.tools.net.api.UserApiService.class).getUser(
                        NetTool.toJsonBody(cooltu.lib4j.json.JsonTool.toJson(labels))
                );
            }
        }, GET_USER, com.codingtu.cooltu.lib4a.CoreConfigs.configs().getBaseUrl(), params);
    }


}
