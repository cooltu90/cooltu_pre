package cooltu.lib4j.json;

import cooltu.lib4j.config.LibConfigs;
import cooltu.lib4j.json.base.JA;
import cooltu.lib4j.json.base.JO;
import cooltu.lib4j.json.base.JsonHolder;
import cooltu.lib4j.json.fastjson.FastJsonHolder;

import java.util.List;

public class JsonTool {

    private static JsonHolder JSON;

    private static JsonHolder getJSON() {
        if (JSON == null) {
            JSON = LibConfigs.configs().createJsonHolder();
            if (JSON == null)
                JSON = new FastJsonHolder();
        }
        return JSON;
    }

    public static <T> T toBean(Class<T> tClass, String json) {
        return getJSON().toBean(tClass, json);
    }

    public static String toJson(Object obj) {
        return getJSON().toJson(obj);
    }

    public static <T> List<T> toBeanList(Class<T> tClass, String json) {
        return getJSON().toBeanList(tClass, json);
    }

    public static JO toJO(String json) {
        return getJSON().toJO(json);
    }

    public static JA toJA(String json) {
        return getJSON().toJA(json);
    }

    public static JO createJO() {
        return getJSON().createJO();
    }


}
