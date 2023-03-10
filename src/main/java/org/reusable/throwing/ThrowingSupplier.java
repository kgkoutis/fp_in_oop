package org.reusable.throwing;

import java.util.Objects;
import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowingSupplier<T> extends Supplier<T> {
    T getThrows() throws Exception;

    @Override
    default T get() {
        try {
            return getThrows();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> Supplier<T> uncheck(final ThrowingSupplier<T> fn) {
        Objects.requireNonNull(fn);
        return fn::get;
    }
}
