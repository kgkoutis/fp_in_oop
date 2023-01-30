package org.reusable.lazy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Memoizer {

    private static final ConcurrentMap<String, Object> CACHE = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> Supplier<T> memoize(final String identifier,
                                          final Supplier<T> fn) {
        return () -> (T) CACHE.computeIfAbsent(identifier, key -> fn.get());
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> memoize(final String identifier,
                                                final Function<T, R> fn) {
        return (T t) -> (R) CACHE.computeIfAbsent(identifier, key -> fn.apply(t));
    }

    public static <T> Consumer<T> memoize(final String identifier,
                                          final Consumer<T> fn) {
        return (T t) -> CACHE.computeIfAbsent(identifier, key -> {
            fn.accept(t);
            return null;
        });
    }
}
