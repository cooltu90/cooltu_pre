package cooltu.lib4j.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cooltu.lib4j.json.base.JA;
import cooltu.lib4j.json.base.JO;
import cooltu.lib4j.json.base.JsonHolder;
import cooltu.lib4j.log.LibLogs;

import java.util.List;

public class FastJsonHolder implements JsonHolder {
    @Override
    public <T> T toBean(Class<T> tClass, String json) {
        try {
            return JSON.parseObject(json, tClass);
        } catch (Throwable e) {
            LibLogs.w(e);
            return null;
        }
    }

    @Override
    public String toJson(Object obj) {
        if (obj == null)
            return null;
        return JSON.toJSONString(obj);
    }

    @Override
    public <T> List<T> toBeanList(Class<T> tClass, String json) {
        try {
            return JSON.parseArray(json, tClass);
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public JO toJO(String json) {
        try {
            return new FastJO(JSON.parseObject(json));
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public JA toJA(String json) {
        try {
            return new FastJA(JSON.parseArray(json));
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public JO createJO() {
        return new FastJO(new JSONObject());
    }


}