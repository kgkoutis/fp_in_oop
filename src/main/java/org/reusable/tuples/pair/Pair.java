package org.reusable.tuples.pair;

import java.util.function.Function;

public interface Pair<T, V> {
    T first();
    V second();


    default <F> Pair<F, V> mapFirst(Function<T, F> f) {
        return Pairs.of(f.apply(first()), second());
    }

    default <S> Pair<T, S> mapSecond(Function<V, S> f) {
        return Pairs.of(first(), f.apply(second()));
    }

    default <F, S> Pair<F, S> map(Function<T, F> mapFirst, Function<V, S> mapSecond) {
        return Pairs.of(mapFirst.apply(first()), mapSecond.apply(second()));
    }

    default <F, S> Pair<F, S> map(Pair<Function<T, F>, Function<V, S>> pair) {
        return Pairs.of(pair.first().apply(first()), pair.second().apply(second()));
    }

    default <F, S> Pair<F, S> bindFirst(Function<T, Pair<F, S>> f) {
        return f.apply(first());
    }

    default <F, S> Pair<F, S> bindSecond(Function<V, Pair<F, S>> f) {
        return f.apply(second());
    }
}
