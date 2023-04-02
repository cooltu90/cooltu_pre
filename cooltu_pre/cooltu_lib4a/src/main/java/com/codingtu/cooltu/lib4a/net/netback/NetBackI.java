package com.codingtu.cooltu.lib4a.net.netback;

import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface NetBackI {
    void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs);
}
