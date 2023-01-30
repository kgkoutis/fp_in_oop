package org.reusable.throwing;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, U> extends Function<T, U> {

    U applyThrows(T elem) throws Exception;

    @Override
    default U apply(T t) {
        try {
            return applyThrows(t);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T, U> Function<T, U> uncheck(ThrowingFunction<T, U> fn) {
        return fn::apply;
    }
}

