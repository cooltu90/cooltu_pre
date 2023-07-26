package core.tools.net.back;

import java.util.List;

import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;
import com.codingtu.cooltu.lib4a.net.netback.NetBack;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class GetObjBack extends NetBack {
    public com.codingtu.cooltu_pre.bean.User user;
    @Override
    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);
        if (cooltu.lib4j.tools.StringTool.isNotBlank(json)) {
            user = cooltu.lib4j.json.JsonTool.toBean(com.codingtu.cooltu_pre.bean.User.class, json);
        }

    }
}
