package cooltu.lib4j.data.bean.maxmin;

import cooltu.lib4j.data.bean.CoreBean;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.MathTool;

import java.util.List;

public class MaxMin<NUM extends Number, T> extends CoreBean {

    public NUM max;
    public NUM min;

    public static interface Getter<NUM extends Number, T> {
        NUM getNum(T t);
    }

    public static <NUM extends Number, T> MaxMin<NUM, T> obtain(List<T> list, Getter<NUM, T> getter) {
        MaxMin<NUM, T> maxMin = new MaxMin<NUM, T>();
        int count = CountTool.count(list);
        if (count > 0) {
            NUM num = getter.getNum(list.get(0));
            maxMin.max = num;
            maxMin.min = num;
            for (int i = 1; i < count; i++) {
                num = getter.getNum(list.get(i));
                maxMin.max = MathTool.max(maxMin.max, num);
                maxMin.min = MathTool.min(maxMin.min, num);
            }
        }
        return maxMin;
    }

}
