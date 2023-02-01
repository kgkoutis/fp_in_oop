package org.reusable.tuples.pairs;

import java.util.function.Function;

public interface Pair<T, V> {
    T first();

    V second();


    default <F> Pair<F, V> mapFirst(final Function<T, F> f) {
        return Pairs.of(f.apply(first()), second());
    }

    default <S> Pair<T, S> mapSecond(final Function<V, S> f) {
        return Pairs.of(first(), f.apply(second()));
    }

    default <F, S> Pair<F, S> map(final Function<T, F> mapFirst,
                                  final Function<V, S> mapSecond) {
        return Pairs.of(mapFirst.apply(first()), mapSecond.apply(second()));
    }

    default <F, S> Pair<F, S> map(final Pair<Function<T, F>, Function<V, S>> pair) {
        return Pairs.of(pair.first().apply(first()), pair.second().apply(second()));
    }

    default <F, S> Pair<F, S> bindFirst(final Function<T, Pair<F, S>> f) {
        return f.apply(first());
    }

    default <F, S> Pair<F, S> bindSecond(final Function<V, Pair<F, S>> f) {
        return f.apply(second());
    }
}
