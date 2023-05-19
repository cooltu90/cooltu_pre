package com.codingtu.cooltu_pre.net;

import com.codingtu.cooltu.processor.annotation.net.Apis;
import com.codingtu.cooltu.processor.annotation.net.method.POST;
import com.codingtu.cooltu_pre.bean.MyLabel;

import java.util.List;

@Apis
public interface UserApi {

    @POST(value = "upload", isJsonBody = true)
    public void getUser(List<MyLabel> labels);
}
