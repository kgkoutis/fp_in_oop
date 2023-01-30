package org.reusable.tuples.triples;

import java.util.function.Function;

public interface Triple<T, U, V> {

    T first();

    U second();

    V third();

    default <R> Triple<R, U, V> mapFirst(java.util.function.Function<? super T, ? extends R> mapper) {
        return Triples.of(mapper.apply(first()), second(), third());
    }

    default <R> Triple<T, R, V> mapSecond(java.util.function.Function<? super U, ? extends R> mapper) {
        return Triples.of(first(), mapper.apply(second()), third());
    }

    default <R> Triple<T, U, R> mapThird(java.util.function.Function<? super V, ? extends R> mapper) {
        return Triples.of(first(), second(), mapper.apply(third()));
    }

    default <X, Y, Z> Triple<X, Y, Z> map(Function<T, X> mapFirst, Function<U, Y> mapSecond, Function<V, Z> mapThird) {
        return Triples.of(mapFirst.apply(first()), mapSecond.apply(second()), mapThird.apply(third()));
    }

    default <X, Y, Z, A> Triple<X, Y, Z> map(Triple<Function<T, X>, Function<U, Y>, Function<V, Z>> triple) {
        return Triples.of(triple.first().apply(first()), triple.second().apply(second()), triple.third().apply(third()));
    }

    default <X, Y, Z> Triple<X, Y, Z> bindFirst(Function<T, Triple<X, Y, Z>> f) {
        return f.apply(first());
    }

    default <X, Y, Z> Triple<X, Y, Z> bindSecond(Function<U, Triple<X, Y, Z>> f) {
        return f.apply(second());
    }

    default <X, Y, Z> Triple<X, Y, Z> bindThird(Function<V, Triple<X, Y, Z>> f) {
        return f.apply(third());
    }

}
