package org.reusable.tuples.quintuples;

import java.util.function.Function;

public interface Quintuple<T, U, V, W, X> {
    T first();
    U second();
    V third();
    W fourth();
    X fifth();

    default <R> Quintuple<R, U, V, W, X> mapFirst(Function<? super T, ? extends R> mapper) {
        return Quintuples.of(mapper.apply(first()), second(), third(), fourth(), fifth());
    }

    default <R> Quintuple<T, R, V, W, X> mapSecond(Function<? super U, ? extends R> mapper) {
        return Quintuples.of(first(), mapper.apply(second()), third(), fourth(), fifth());
    }

    default <R> Quintuple<T, U, R, W, X> mapThird(Function<? super V, ? extends R> mapper) {
        return Quintuples.of(first(), second(), mapper.apply(third()), fourth(), fifth());
    }

    default <R> Quintuple<T, U, V, R, X> mapFourth(Function<? super W, ? extends R> mapper) {
        return Quintuples.of(first(), second(), third(), mapper.apply(fourth()), fifth());
    }

    default <R> Quintuple<T, U, V, W, R> mapFifth(Function<? super X, ? extends R> mapper) {
        return Quintuples.of(first(), second(), third(), fourth(), mapper.apply(fifth()));
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> map(Function<T, Y> mapFirst, Function<U, Z> mapSecond, Function<V, A> mapThird, Function<W, B> mapFourth) {
        return Quintuples.of(mapFirst.apply(first()), mapSecond.apply(second()), mapThird.apply(third()), mapFourth.apply(fourth()), fifth());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> map(Quintuple<Function<T, Y>, Function<U, Z>, Function<V, A>, Function<W, B>, Function<X, X>> quintuple) {
        return Quintuples.of(quintuple.first().apply(first()), quintuple.second().apply(second()), quintuple.third().apply(third()), quintuple.fourth().apply(fourth()), quintuple.fifth().apply(fifth()));
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindFirst(Function<T, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(first());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindSecond(Function<U, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(second());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindThird(Function<V, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(third());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindFourth(Function<W, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(fourth());
    }

    default <Y, Z, A, B> Quintuple<Y, Z, A, B, X> bindFifth(Function<X, Quintuple<Y, Z, A, B, X>> f) {
        return f.apply(fifth());
    }
}
