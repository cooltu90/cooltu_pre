package com.codingtu.cooltu.lib4a.net.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.tools.StringTool;

import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.log.Logs;

public class HttpLog {

    private String method;
    private String url;
    private String protocol;
    private String contentLength;
    private String contentType;
    private List<String> requestHeaders;
    private List<String> parameters;


    private String code;
    private List<String> responseHeaders;
    private String time;
    private String responseMessage;
    private String body;

    private String requestBody;

    private Throwable failedThrowable;
    private boolean isFailed;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Throwable getFailedThrowable() {
        return failedThrowable;
    }

    public void setFailedThrowable(Throwable failedThrowable) {
        this.failedThrowable = failedThrowable;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public void setFailed(boolean failed) {
        isFailed = failed;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void addRequestHeader(String header) {
        if (this.requestHeaders == null)
            this.requestHeaders = new ArrayList<String>();
        this.requestHeaders.add(header);
    }

    public void addResponseHeader(String header) {
        if (this.responseHeaders == null)
            this.responseHeaders = new ArrayList<String>();
        this.responseHeaders.add(header);
    }

    public void addParameter(String parameter) {
        if (this.parameters == null)
            this.parameters = new ArrayList<String>();
        this.parameters.add(parameter);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void log() {
        if (!CoreConfigs.configs().isLog()) {
            return;
        }

        ArrayList<String> logs = new ArrayList<String>();
        logs.add(
                "┌──接口请求──────────────────────────────────────────────────────────────────────────────");

        if (protocol != null) {
            logs.add("│\t--> protocol:" + protocol);
        }
        logs.add("│\t--> " + method);
        logs.add("│\t--> " + url);

        if (parameters != null) {
            logs.add("│\t--> 请求参数:");
            for (String Parameter : parameters) {
                logs.add("│\t-->  " + Parameter);

            }
        } else {
            logs.add("│\t--> 请求参数:null");
        }


        if (contentLength != null)
            logs.add("│\t--> Content-Length: " + contentLength + "-byte");
        if (contentType != null)
            logs.add("│\t--> Content-Type: " + contentType);

        if (requestHeaders != null) {
            logs.add("│\t--> 请求头信息:");
            for (String header : requestHeaders) {
                logs.add("│\t-->  " + header);
            }
        } else {
            logs.add("│\t--> 请求头信息:null");
        }

        if (StringTool.isNotBlank(requestBody)) {
            if (requestBody.startsWith("--")) {
                logs.add("│\t--> 请求体:" + requestBody.substring(0, 100));
            } else {
                logs.add("│\t--> 请求体:" + requestBody);
            }
        }

        logs.add("│\t--------------------------------------->");
        logs.add("│\n");
        logs.add("│\t<---------------------------------------");

        if (isFailed) {
            logs.add("│\t<-- 请求失败");
            if (failedThrowable != null) {
                Logs.w(CoreConfigs.configs().getDefaultLogTag() + "_API", failedThrowable);
            }
        } else {
            logs.add(
                    "│\t<-- " + code + (responseMessage == null ? "" : (" " + responseMessage)) + (time == null ? "" : (" " + time)));

            if (responseHeaders != null) {
                logs.add("│\t<-- 返回头信息:");
                for (String header : responseHeaders) {
                    logs.add("│\t<--  " + header);
                }
            } else {
                logs.add("│\t<-- 返回头信息:null");
            }

            logs.add("│\t<-- 返回值:");
//            logs.add("│\t");
            List<String> subLogs = dealBody(body);
            if (subLogs == null) {
                logs.add("│\t<-- " + body);
            } else {
                logs.addAll(subLogs);
            }
        }

        logs.add(
                "└───────────────────────────────────────────────────────────────────────────────────────");


        for (int i = 0; i < logs.size(); i++) {
            Logs.i("LOG_API", logs.get(i));
        }

    }

    private List<String> dealBody(String body) {
        try {

            StringBuilder sb = new StringBuilder();

            ArrayList<String> logs = new ArrayList<String>();

            Object parse = JSON.parse(body);

            if (parse instanceof JSONObject) {
                int level = 0;
                getSpace(sb, level);
                for (int i = 0; i < body.length(); i++) {
                    String sub = body.substring(i, i + 1);
                    if ("{".equals(sub)) {
                        level++;
                        sb.append("{");
                        logs.add(sb.toString());
                        sb = new StringBuilder();
                        getSpace(sb, level);
                    } else if (",".equals(sub)) {
                        sb.append(",");
                        logs.add(sb.toString());
                        sb = new StringBuilder();
                        getSpace(sb, level);
                    } else if ("}".equals(sub)) {
                        level--;
                        logs.add(sb.toString());
                        sb = new StringBuilder();
                        getSpace(sb, level);
                        sb.append("}");
                    } else {
                        sb.append(sub);
                    }
                }
                logs.add(sb.toString());
                return logs;
            }

        } catch (Exception e) {
            Logs.w(e);
        }
        return null;
    }

    private void getSpace(StringBuilder sb, int num) {
        sb.append("│\t");
        for (int i = 0; i < num; i++) {
            sb.append("\t");
        }
    }


//    public void log() {
//        if (!CoreConfigs.configs().isLog()) {
//            return;
//        }
//        StringBuilder sb = new StringBuilder();
//        sb.append(" \n");
//        sb.append(
//                "┌──接口请求──────────────────────────────────────────────────────────────────────────────\n");
//
//        if (protocol != null) {
//            sb.append("│\t--> protocol:" + protocol + "\n");
//        }
//        sb.append("│\t--> " + method + "\n");
//        sb.append("│\t--> " + url + "\n");
//
//        if (parameters != null) {
//            sb.append("│\t--> 请求参数:" + "\n");
//            for (String Parameter : parameters) {
//                sb.append("│\t-->  " + Parameter + "\n");
//
//            }
//        } else {
//            sb.append("│\t--> 请求参数:null\n");
//        }
//
//
//        if (contentLength != null)
//            sb.append("│\t--> Content-Length: " + contentLength + "-byte\n");
//        if (contentType != null)
//            sb.append("│\t--> Content-Type: " + contentType + "\n");
//
//        if (requestHeaders != null) {
//            sb.append("│\t--> 请求头信息:\n");
//            for (String header : requestHeaders) {
//                sb.append("│\t-->  " + header + "\n");
//            }
//        } else {
//            sb.append("│\t--> 请求头信息:null\n");
//        }
//
//        if (StringFunc.isNotBlank(requestBody)) {
//            if (requestBody.startsWith("--")) {
//                sb.append("│\t--> 请求体:" + requestBody.substring(0, 100) + "\n");
//            } else {
//                sb.append("│\t--> 请求体:" + requestBody + "\n");
//            }
//        }
//
//        sb.append("│\t--------------------------------------->\n");
//        sb.append("│\n");
//        sb.append("│\t<---------------------------------------\n");
//
//        if (isFailed) {
//            sb.append("│\t<-- 请求失败\n");
//            if (failedThrowable != null) {
//                Logs.w(CoreConfigs.configs().defaultLogTag() + "_API", failedThrowable);
//            }
//        } else {
//            sb.append(
//                    "│\t<-- " + code + (responseMessage == null ? "" : (" " + responseMessage)) + (time == null ? "" : (" " + time)) + "\n");
//
//            if (responseHeaders != null) {
//                sb.append("│\t<-- 返回头信息:\n");
//                for (String header : responseHeaders) {
//                    sb.append("│\t<--  " + header + "\n");
//                }
//            } else {
//                sb.append("│\t<-- 返回头信息:null\n");
//            }
//
//            sb.append("│\t<-- 返回值:\n");
//            sb.append("│\t");
//            sb.append(dealBody(body));
//            sb.append("\n");
//        }
//
//        sb.append(
//                "└───────────────────────────────────────────────────────────────────────────────────────\n");
//
//        Logs.i(CoreConfigs.configs().defaultLogTag() + "_API", sb.toString());
//    }

//    private String dealBody(String body) {
//        try {
//
//            StringBuilder sb = new StringBuilder();
//
//            Object parse = JSON.parse(body);
//
//            if (parse instanceof JSONObject) {
//                int level = 0;
//                for (int i = 0; i < body.length(); i++) {
//
//                    String sub = body.substring(i, i + 1);
//
//                    if ("{".equals(sub)) {
//                        level++;
//                        sb.append("{\n");
//                        getSpace(sb, level);
//                    } else if (",".equals(sub)) {
//                        sb.append(",\n");
//                        getSpace(sb, level);
//                    } else if ("}".equals(sub)) {
//                        level--;
//                        sb.append("\n");
//                        getSpace(sb, level);
//                        sb.append("}");
//                    } else {
//                        sb.append(sub);
//                    }
//                }
//                body = sb.toString();
//            }
//
//        } catch (Exception e) {
//            Logs.w(e);
//        }
//        return body;
//    }

//    private void getSpace(StringBuilder sb, int num) {
//        sb.append("│\t");
//        for (int i = 0; i < num; i++) {
//            sb.append("\t");
//        }
//    }

    public void setBody(String body) {
        this.body = body;
    }


    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

}
