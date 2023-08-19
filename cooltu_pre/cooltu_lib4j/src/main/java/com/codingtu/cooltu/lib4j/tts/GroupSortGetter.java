package com.codingtu.cooltu.lib4j.tts;

public interface GroupSortGetter<T> {
    String getGroup(int level, T t);

    int getLevels();

    int compare(T o1, T o2);

}
