package org.reusable.throwing;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T> extends Consumer<T> {
    void applyThrows(T elem) throws Exception;

    @Override
    default void accept(T t) {
        try {
            applyThrows(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> Consumer<T> uncheck(ThrowingConsumer<T> fn) {
        return fn::accept;
    }
}
