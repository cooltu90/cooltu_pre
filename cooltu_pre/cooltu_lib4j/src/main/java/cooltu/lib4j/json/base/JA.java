package cooltu.lib4j.json.base;

import java.util.List;

public interface JA {
    <T> List<T> toBeanList(Class<T> tClass);

    int size();

    JO getJO(int index);
}