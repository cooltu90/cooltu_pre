package cooltu.lib4j.json.base;

import java.util.List;

public interface JsonHolder {
    <T> T toBean(Class<T> tClass, String json);

    String toJson(Object obj);

    <T> List<T> toBeanList(Class<T> tClass, String json);

    JO toJO(String json);

    JO createJO();

    JA toJA(String json);
}