package cooltu.lib4j.json.fastjson;

import com.alibaba.fastjson.JSONArray;
import cooltu.lib4j.json.base.JA;
import cooltu.lib4j.json.base.JO;

import java.util.List;

public class FastJA implements JA {
    private final JSONArray ja;

    public FastJA(JSONArray ja) {
        this.ja = ja;
    }

    public JO getJO(int index) {
        return new FastJO(this.ja.getJSONObject(index));
    }

    @Override
    public <T> List<T> toBeanList(Class<T> tClass) {
        return this.ja.toJavaList(tClass);
    }

    @Override
    public int size() {
        return ja.size();
    }

}