package org.reusable.lazy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Memoizer {

    private static final ConcurrentMap<String, Object> CACHE = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> Supplier<T> memoize(String identifier, Supplier<T> fn) {
        return () -> (T) CACHE.computeIfAbsent(identifier, key -> fn.get());
    }

    @SuppressWarnings("unchecked")
    public static <T,R> Function<T,R> memoize(String identifier, Function<T,R> fn) {
        return (T t) -> (R) CACHE.computeIfAbsent(identifier, key -> fn.apply(t));
    }

    public static <T> Consumer<T> memoize(String identifier, Consumer<T> fn) {
        return (T t) -> CACHE.computeIfAbsent(identifier, key -> {
            fn.accept(t);
            return null;
        });
    }
}
