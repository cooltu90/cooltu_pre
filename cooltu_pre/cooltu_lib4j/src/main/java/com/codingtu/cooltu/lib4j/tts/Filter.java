package com.codingtu.cooltu.lib4j.tts;

public interface Filter<T> {
    public boolean get(int position, T t);
}
