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

    private static final String GET_OBJ = "getObjBack";
    private static final String ADD_OBJ = "addObjBack";
    private static final String GET_USER = "getUserBack";


    private static final String BASE_URL_TEST_API = "https://www.xxxxxxxxx.com";
    private static final String BASE_URL_TEST_API_ADD_OBJ = "https://wwww.sddfsdfsd.com";



    public static API getObj(java.lang.String id ,java.lang.String order) {
        core.tools.net.params.GetObjParams params = new core.tools.net.params.GetObjParams();
        params.token = "stDXFdsdff";
        params.id = id;
        params.order = order;

        return NetTool.api(new CreateApi() {
            @Override
            public Flowable<Result<ResponseBody>>
            create(Retrofit retrofit, CoreSendParams ps) {
                core.tools.net.params.GetObjParams params = (core.tools.net.params.GetObjParams) ps;

                return retrofit.create(core.tools.net.api.TestApiService.class).getObj(
                        params.token,
                        params.id,
                        params.order
                );
            }
        }, GET_OBJ, BASE_URL_TEST_API, params);
    }

    public static API addObj(java.lang.String name ,int age) {
        core.tools.net.params.AddObjParams params = new core.tools.net.params.AddObjParams();
        params.name = name;
        params.age = age;

        return NetTool.api(new CreateApi() {
            @Override
            public Flowable<Result<ResponseBody>>
            create(Retrofit retrofit, CoreSendParams ps) {
                core.tools.net.params.AddObjParams params = (core.tools.net.params.AddObjParams) ps;
                cooltu.lib4j.json.base.JO jo = cooltu.lib4j.json.JsonTool.createJO();
                jo.put("name", params.name);
                jo.put("age", params.age);

                return retrofit.create(core.tools.net.api.TestApiService.class).addObj(
                        NetTool.toJsonBody(jo.toJson())
                );
            }
        }, ADD_OBJ, BASE_URL_TEST_API_ADD_OBJ, params);
    }

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
