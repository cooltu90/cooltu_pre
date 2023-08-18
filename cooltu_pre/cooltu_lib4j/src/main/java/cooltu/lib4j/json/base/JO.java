package cooltu.lib4j.json.base;

import java.util.List;
import java.util.Set;

public interface JO {
    String getString(String key);

    Integer getInteger(String key);

    boolean getBoolean(String key);

    double getDouble(String key);

    JA getJA(String key);

    JO getJO(String key);

    <T> T getBean(String key, Class<T> tClass);

    <T> T toBean(Class<T> tClass);

    <T> List<T> getBeanList(String datas, Class<T> tClass);

    JO put(String key, Object value);

    String toJson();

    Set<String> keys();
}