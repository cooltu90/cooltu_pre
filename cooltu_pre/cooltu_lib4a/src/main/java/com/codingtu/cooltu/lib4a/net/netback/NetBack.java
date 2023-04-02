package com.codingtu.cooltu.lib4a.net.netback;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

public class NetBack implements NetBackI {
    public CoreSendParams params;
    public List objs;
    public String json;
    public String errorJson;
    public String errorMsg;

    @Override
    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
        this.params = params;
        this.objs = objs;
        if (result != null && !result.isError()) {
            Response<ResponseBody> response = result.response();
            if (response != null) {
                if (response.code() == 200) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        try {
                            json = body.string();
                        } catch (Exception e) {
                            Logs.w(e);
                        }
                    }
                } else {
                    ResponseBody body = response.errorBody();
                    if (body != null) {
                        try {
                            errorJson = body.string();
                        } catch (Exception e) {
                            Logs.w(e);
                        }
                    }
                }
            }
        }
    }

}
