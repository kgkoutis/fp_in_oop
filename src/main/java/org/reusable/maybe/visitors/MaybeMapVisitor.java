package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Maybe;
import org.reusable.maybe.Nothing;

import java.util.function.Function;

public class MaybeMapVisitor<T, V> implements MaybeVisitor<T, Maybe<V>> {
    private final Function<T, V> mappingFunction;

    public MaybeMapVisitor(Function<T, V> mappingFunction) {
        this.mappingFunction = mappingFunction;
    }

    @Override
    public Maybe<V> visit(Just<T> just) {
        V result = mappingFunction.apply(just.getValue());
        return Maybe.just(result);
    }

    @Override
    public Maybe<V> visit(Nothing<T> nothing) {
        return Maybe.nothing();
    }
}