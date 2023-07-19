package com.codingtu.cooltu.lib4a.function;

import java.util.function.Predicate;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
