package cooltu.lib4j.data.bean;

import cooltu.lib4j.json.JsonTool;
import cooltu.lib4j.tools.CountTool;

import java.lang.reflect.Field;

public class CoreBean {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Class<? extends CoreBean> aClass = getClass();

        sb.append(aClass.getSimpleName() + "{\r\n");

        Field[] declaredFields = aClass.getDeclaredFields();

        for (int i = 0; i < CountTool.count(declaredFields); i++) {
            Field field = declaredFields[i];
            field.setAccessible(true);
            try {
                sb.append('\t' + field.getName() + " = '" + field.get(this) + "'\r\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sb.append("}");

        return sb.toString();
    }

    public String toJson() {
        return JsonTool.toJson(this);
    }

}
