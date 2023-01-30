package org.reusable.throwing;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T> extends Consumer<T> {
    void applyThrows(T elem) throws Exception;

    @Override
    default void accept(final T t) {
        try {
            applyThrows(t);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> Consumer<T> uncheck(final ThrowingConsumer<T> fn) {
        return fn::accept;
    }
}
