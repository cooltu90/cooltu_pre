package cooltu.lib4j.data.bean.maxmin;

import cooltu.lib4j.data.bean.num.FloatGetter;

import java.util.List;

public class FloatMaxMin<T extends FloatGetter> extends MaxMin<Float, T> {

    public static <T extends FloatGetter> MaxMin<Float, T> obtain(List<T> list) {
        return obtain(list, new Getter<Float, T>() {
            @Override
            public Float getNum(T t) {
                return t.obtainFloat();
            }
        });
    }

}
