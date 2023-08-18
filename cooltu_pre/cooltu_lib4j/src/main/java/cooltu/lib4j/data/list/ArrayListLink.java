package cooltu.lib4j.data.list;

import cooltu.lib4j.tools.CountTool;

import java.util.ArrayList;
import java.util.List;

public class ArrayListLink<T> {

    private ArrayList<T> ts;

    /**************************************************
     *
     * 静态方法
     *
     **************************************************/
    public static <T> ArrayListLink<T> obtain(List<T> ts) {
        return new ArrayListLink<T>(ts);
    }


    /**************************************************
     *
     * 构造函数
     *
     **************************************************/
    public ArrayListLink() {
        this.ts = new ArrayList<T>();
    }

    public ArrayListLink(List<T> ts) {
        if (ts == null) {
            this.ts = new ArrayList<T>();
        } else if (ts instanceof ArrayList) {
            this.ts = (ArrayList<T>) ts;
        } else {
            for (int i = 0; i < ts.size(); i++) {
                this.ts.add(ts.get(i));
            }
        }
    }

    /**************************************************
     *
     * 获取元素
     *
     **************************************************/
    public T get(int index) {
        if (index >= size())
            return null;
        return ts.get(index);
    }

    /**************************************************
     *
     * 获取数量
     *
     **************************************************/
    public int size() {
        return CountTool.count(ts);
    }

    /**************************************************
     *
     * 添加元素
     *
     **************************************************/
    public ArrayListLink<T> addAll(T... ts) {
        int size = ts == null ? 0 : ts.length;
        for (int i = 0; i < size; i++) {
            this.ts.add(ts[i]);
        }
        return this;
    }

    public ArrayListLink<T> add(T t) {
        this.ts.add(t);
        return this;
    }

    /**************************************************
     *
     * delete_filter
     *
     **************************************************/
    public ArrayListLink<T> delete(T t) {
        this.ts.remove(t);
        return this;
    }

    public ArrayListLink<T> delete(int index) {
        this.ts.remove(index);
        return this;
    }

    public ArrayListLink<T> delete(final DeleteFilter<T> filter) {
        final ArrayList<T> list = new ArrayList<T>();
        for (int i = 0; i < CountTool.count(ts); i++) {
            T t = ts.get(i);
            if (!filter.isDelete(i, t)) {
                list.add(t);
            }
        }
        this.ts = list;
        return this;
    }

    /**************************************************
     *
     * 返回ArrayList
     *
     **************************************************/
    public ArrayList<T> getList() {
        return ts;
    }

    /**************************************************
     *
     * DeleteFilter
     *
     **************************************************/
    public static interface DeleteFilter<T> {
        public boolean isDelete(int position, T t);
    }
}
