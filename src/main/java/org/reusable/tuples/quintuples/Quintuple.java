package org.reusable.tuples.quintuples;

import java.util.function.Function;

public interface Quintuple<T, U, V, W, X> {
    T first();

    U second();

    V third();

    W fourth();

    X fifth();

    default <R> Quintuple<R, U, V, W, X> mapFirst(final Function<? super T, ? extends R> mapper) {
        return Quintuples.of(mapper.apply(first()), second(), third(), fourth(), fifth());
    }

    default <R> Quintuple<T, R, V, W, X> mapSecond(final Function<? super U, ? extends R> mapper) {
        return Quintuples.of(first(), mapper.apply(second()), third(), fourth(), fifth());
    }

    default <R> Quintuple<T, U, R, W, X> mapThird(final Function<? super V, ? extends R> mapper) {
        return Quintuples.of(first(), second(), mapper.apply(third()), fourth(), fifth());
    }

    default <R> Quintuple<T, U, V, R, X> mapFourth(final Function<? super W, ? extends R> mapper) {
        return Quintuples.of(first(), second(), third(), mapper.apply(fourth()), fifth());
    }

    default <R> Quintuple<T, U, V, W, R> mapFifth(final Function<? super X, ? extends R> mapper) {
        return Quintuples.of(first(), second(), third(), fourth(), mapper.apply(fifth()));
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> map(final Function<T, Y> mapFirst,
                                                      final Function<U, Z> mapSecond,
                                                      final Function<V, A> mapThird,
                                                      final Function<W, B> mapFourth,
                                                      final Function<X, X> mapFifth
    ) {
        return Quintuples.of(mapFirst.apply(first()), mapSecond.apply(second()), mapThird.apply(third()), mapFourth.apply(fourth()), mapFifth.apply(fifth()));
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> map(final Quintuple<Function<T, Y>, Function<U, Z>, Function<V, A>, Function<W, B>, Function<X, X>> quintuple) {
        return Quintuples.of(quintuple.first().apply(first()), quintuple.second().apply(second()), quintuple.third()
                .apply(third()), quintuple.fourth().apply(fourth()), quintuple.fifth().apply(fifth()));
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindFirst(final Function<T, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(first());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindSecond(final Function<U, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(second());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindThird(final Function<V, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(third());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindFourth(final Function<W, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(fourth());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindFifth(final Function<X, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(fifth());
    }
}
