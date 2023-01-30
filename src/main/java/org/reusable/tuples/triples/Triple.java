package org.reusable.tuples.triples;

import java.util.function.Function;

public interface Triple<T, U, V> {

    T first();

    U second();

    V third();

    default <R> Triple<R, U, V> mapFirst(final java.util.function.Function<? super T, ? extends R> mapper) {
        return Triples.of(mapper.apply(first()), second(), third());
    }

    default <R> Triple<T, R, V> mapSecond(final java.util.function.Function<? super U, ? extends R> mapper) {
        return Triples.of(first(), mapper.apply(second()), third());
    }

    default <R> Triple<T, U, R> mapThird(final java.util.function.Function<? super V, ? extends R> mapper) {
        return Triples.of(first(), second(), mapper.apply(third()));
    }

    default <X, Y, Z> Triple<X, Y, Z> map(final Function<T, X> mapFirst, final Function<U, Y> mapSecond, final Function<V, Z> mapThird) {
        return Triples.of(mapFirst.apply(first()), mapSecond.apply(second()), mapThird.apply(third()));
    }

    default <X, Y, Z, A> Triple<X, Y, Z> map(final Triple<Function<T, X>, Function<U, Y>, Function<V, Z>> triple) {
        return Triples.of(triple.first().apply(first()), triple.second().apply(second()), triple.third().apply(third()));
    }

    default <X, Y, Z> Triple<X, Y, Z> bindFirst(final Function<T, Triple<X, Y, Z>> f) {
        return f.apply(first());
    }

    default <X, Y, Z> Triple<X, Y, Z> bindSecond(final Function<U, Triple<X, Y, Z>> f) {
        return f.apply(second());
    }

    default <X, Y, Z> Triple<X, Y, Z> bindThird(final Function<V, Triple<X, Y, Z>> f) {
        return f.apply(third());
    }

}
