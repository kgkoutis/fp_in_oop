package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Maybe;
import org.reusable.maybe.Nothing;

import java.util.function.Function;

public final class MaybeMapVisitor<T, V> implements MaybeVisitor<T, Maybe<V>> {
    private final Function<T, V> mappingFunction;

    public MaybeMapVisitor(final Function<T, V> mappingFunction) {
        this.mappingFunction = mappingFunction;
    }

    @Override
    public Maybe<V> visit(final Just<T> just) {
        final V result = mappingFunction.apply(just.getValue());
        return Maybe.just(result);
    }

    @Override
    public Maybe<V> visit(final Nothing<T> nothing) {
        return Maybe.nothing();
    }
}