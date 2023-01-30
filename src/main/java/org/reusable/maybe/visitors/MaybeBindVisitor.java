package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Maybe;
import org.reusable.maybe.Nothing;

import java.util.function.Function;

public class MaybeBindVisitor<T, V> implements MaybeVisitor<T, Maybe<V>> {
    private final Function<T, Maybe<V>> bindingFunction;

    public MaybeBindVisitor(Function<T, Maybe<V>> flatMappingFunction) {
        this.bindingFunction = flatMappingFunction;
    }

    @Override
    public Maybe<V> visit(Just<T> just) {
        return bindingFunction.apply(just.getValue());
    }

    @Override
    public Maybe<V> visit(Nothing<T> nothing) {
        return new Nothing<>();
    }
}
