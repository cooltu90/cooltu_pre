package com.codingtu.cooltu_pre.net;

import com.codingtu.cooltu.processor.annotation.net.Apis;
import com.codingtu.cooltu.processor.annotation.net.Default;
import com.codingtu.cooltu.processor.annotation.net.Param;
import com.codingtu.cooltu.processor.annotation.net.ParamType;
import com.codingtu.cooltu.processor.annotation.net.method.GET;
import com.codingtu.cooltu.processor.annotation.net.method.POST;
import com.codingtu.cooltu_pre.bean.User;

@Apis(baseUrl = "https://www.xxxxxxxxx.com")
public interface TestApi {

    @GET("/getObj/{ids}")
    public User getObj(
            @Default("stDXFdsdff") @Param(type = ParamType.HEADER, value = "token") String token,
            @Param(type = ParamType.PATH, value = "ids") String id,
            @Param(type = ParamType.DEFAULT, value = "order[desc]", encoded = true) String order
    );

    @POST(value = "/addObj", isJsonBody = true, baseUrl = "https://wwww.sddfsdfsd.com")
    public String addObj(String name, int age);

    @GET("/addObj1")
    public String addObj1();
}
