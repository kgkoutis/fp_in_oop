package org.reusable.tuples.quadruple;

import java.util.function.Function;

public interface Quadruple<T, U, V, W> {

    T first();

    U second();

    V third();

    W fourth();

    default <X> Quadruple<X, U, V, W> mapFirst(final Function<T, X> f) {
        return Quadruples.of(f.apply(first()), second(), third(), fourth());
    }

    default <Y> Quadruple<T, Y, V, W> mapSecond(final Function<U, Y> f) {
        return Quadruples.of(first(), f.apply(second()), third(), fourth());
    }

    default <Z> Quadruple<T, U, Z, W> mapThird(final Function<V, Z> f) {
        return Quadruples.of(first(), second(), f.apply(third()), fourth());
    }

    default <A> Quadruple<T, U, V, A> mapFourth(final Function<W, A> f) {
        return Quadruples.of(first(), second(), third(), f.apply(fourth()));
    }

    default <X, Y, Z, A> Quadruple<X, Y, Z, A> map(final Function<T, X> mapFirst,
                                                   final Function<U, Y> mapSecond,
                                                   final Function<V, Z> mapThird,
                                                   final Function<W, A> mapFourth) {
        return Quadruples.of(mapFirst.apply(first()), mapSecond.apply(second()), mapThird.apply(third()), mapFourth.apply(fourth()));
    }

    default <X, Y, Z, A> Quadruple<X, Y, Z, A> map(final Quadruple<Function<T, X>, Function<U, Y>, Function<V, Z>, Function<W, A>> quadruple) {
        return Quadruples.of(quadruple.first().apply(first()), quadruple.second().apply(second()), quadruple.third().apply(third()), quadruple.fourth().apply(fourth()));
    }

    default <X, Y, Z, A> Quadruple<X, Y, Z, A> bindFirst(final Function<T, Quadruple<X, Y, Z, A>> f) {
        return f.apply(first());
    }

    default <X, Y, Z, A> Quadruple<X, Y, Z, A> bindSecond(final Function<U, Quadruple<X, Y, Z, A>> f) {
        return f.apply(second());
    }

    default <X, Y, Z, A> Quadruple<X, Y, Z, A> bindThird(final Function<V, Quadruple<X, Y, Z, A>> f) {
        return f.apply(third());
    }

    default <X, Y, Z, A> Quadruple<X, Y, Z, A> bindFourth(final Function<W, Quadruple<X, Y, Z, A>> f) {
        return f.apply(fourth());
    }
}
