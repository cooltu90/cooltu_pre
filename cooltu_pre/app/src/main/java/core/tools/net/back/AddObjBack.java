package core.tools.net.back;

import java.util.List;

import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;
import com.codingtu.cooltu.lib4a.net.netback.NetBack;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class AddObjBack extends NetBack {

    @Override
    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);

    }
}
