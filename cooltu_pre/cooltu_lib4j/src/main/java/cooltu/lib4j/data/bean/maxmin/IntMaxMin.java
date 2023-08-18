package cooltu.lib4j.data.bean.maxmin;

import cooltu.lib4j.data.bean.num.IntGetter;

import java.util.List;

public class IntMaxMin<T extends IntGetter> extends MaxMin<Integer, T> {

    public static <T extends IntGetter> MaxMin<Integer, T> obtain(List<T> list) {
        return obtain(list, new Getter<Integer, T>() {
            @Override
            public Integer getNum(T t) {
                return t.obtainInt();
            }
        });
    }

}
