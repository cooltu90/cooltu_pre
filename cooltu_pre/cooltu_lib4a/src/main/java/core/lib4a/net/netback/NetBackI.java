package core.lib4a.net.netback;

import java.util.List;

import core.lib4a.net.bean.CoreSendParams;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface NetBackI {
    void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs);
}
