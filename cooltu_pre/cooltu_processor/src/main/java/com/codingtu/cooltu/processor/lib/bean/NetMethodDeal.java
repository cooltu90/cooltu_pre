package com.codingtu.cooltu.processor.lib.bean;

import javax.lang.model.element.ExecutableElement;

import com.codingtu.cooltu.processor.annotation.net.method.GET;
import com.codingtu.cooltu.processor.annotation.net.method.POST;
import com.codingtu.cooltu.processor.annotation.net.method.PUT;

public class NetMethodDeal {

    private final ExecutableElement ee;
    private String methodBaseUrl;
    private boolean isJsonBody;
    private boolean isFormUrlEncoded;
    private String value;
    private String netMethodName;
    private String fieldType;

    public NetMethodDeal(ExecutableElement ee) {
        this.ee = ee;

        GET get = ee.getAnnotation(GET.class);
        if (get != null) {
            methodBaseUrl = get.baseUrl();
            value = get.value();
            netMethodName = "GET";
            fieldType = "Query";
        }
        POST post = ee.getAnnotation(POST.class);
        if (post != null) {
            isFormUrlEncoded = !post.isJsonBody();
            methodBaseUrl = post.baseUrl();
            isJsonBody = post.isJsonBody();
            value = post.value();
            netMethodName = "POST";
            fieldType = "Field";
        }

        PUT put = ee.getAnnotation(PUT.class);
        if (put != null) {
            isFormUrlEncoded = !put.isJsonBody();
            methodBaseUrl = put.baseUrl();
            isJsonBody = put.isJsonBody();
            value = put.value();
            netMethodName = "PUT";
            fieldType = "Field";
        }

    }

    public String getMethodBaseUrl() {
        return methodBaseUrl;
    }

    public boolean isJsonBody() {
        return isJsonBody;
    }

    public String value() {
        return value;
    }

    public boolean isFormUrlEncoded() {
        return isFormUrlEncoded;
    }

    public String netMethodName() {
        return netMethodName;
    }

    public String getFieldType(){
        return fieldType;
    }
}
