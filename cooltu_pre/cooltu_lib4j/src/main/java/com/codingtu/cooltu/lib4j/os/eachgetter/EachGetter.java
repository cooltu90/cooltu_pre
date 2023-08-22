package com.codingtu.cooltu.lib4j.os.eachgetter;

public interface EachGetter<T> {
    public T get(int position);

    public int count();
}
