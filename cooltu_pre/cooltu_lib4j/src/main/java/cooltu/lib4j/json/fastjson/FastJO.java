package cooltu.lib4j.json.fastjson;

import com.alibaba.fastjson.JSONObject;
import cooltu.lib4j.json.base.JA;
import cooltu.lib4j.json.base.JO;

import java.util.List;
import java.util.Set;

public class FastJO implements JO {
    private final JSONObject jo;

    public FastJO(JSONObject jo) {
        this.jo = jo;
    }

    @Override
    public String getString(String key) {
        return jo.getString(key);
    }

    @Override
    public Integer getInteger(String key) {
        return jo.getInteger(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return jo.getBooleanValue(key);
    }

    @Override
    public double getDouble(String key) {
        return jo.getDoubleValue(key);
    }

    @Override
    public JA getJA(String key) {
        return new FastJA(jo.getJSONArray(key));
    }

    @Override
    public JO getJO(String key) {
        return new FastJO(jo.getJSONObject(key));
    }

    @Override
    public <T> T getBean(String key, Class<T> tClass) {
        return this.jo.getObject(key, tClass);
    }

    @Override
    public <T> T toBean(Class<T> tClass) {
        return this.jo.toJavaObject(tClass);
    }

    @Override
    public <T> List<T> getBeanList(String key, Class<T> tClass) {
        return getJA(key).toBeanList(tClass);
    }

    @Override
    public JO put(String key, Object value) {
        jo.put(key, value);
        return this;
    }

    @Override
    public String toJson() {
        return jo.toJSONString();
    }

    @Override
    public Set<String> keys() {
        return jo.keySet();
    }
}