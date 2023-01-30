package org.reusable.throwing;

import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowingSupplier<T> extends Supplier<T> {
    T getThrows() throws Exception;

    @Override
    default T get() {
        try {
            return getThrows();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> Supplier<T> uncheck(ThrowingSupplier<T> fn) {
        return fn::get;
    }
}
