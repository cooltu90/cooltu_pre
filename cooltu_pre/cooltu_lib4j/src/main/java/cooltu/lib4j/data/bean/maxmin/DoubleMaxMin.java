package cooltu.lib4j.data.bean.maxmin;

import cooltu.lib4j.data.bean.num.DoubleGetter;

import java.util.List;

public class DoubleMaxMin<T extends DoubleGetter> extends MaxMin<Double, T> {

    public static <T extends DoubleGetter> MaxMin<Double, T> obtain(List<T> list) {
        return obtain(list, new Getter<Double, T>() {
            @Override
            public Double getNum(T t) {
                return t.obtainDouble();
            }
        });
    }

}
