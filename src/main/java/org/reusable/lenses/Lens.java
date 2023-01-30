package org.reusable.lenses;

import java.util.function.Function;

/**
 * Primitive lens type for creating well-behaved bidirectional transformations
 */
public final class Lens<A, B> {
    public final Function<A, B> getF;
    public final Function<B, Function<A, A>> setF;

    private Lens(Function<A, B> get, Function<B, Function<A, A>> set) {
        this.getF = get;
        this.setF = set;
    }

    public B get(A value) {
        return getF.apply(value);
    }

    public A set(B value, A cont) {
        return setF.apply(value).apply(cont);
    }

    public static <A, B> Lens<A, B> of(Function<A, B> Get, Function<B, Function<A, A>> Set) {
        return new Lens<>(Get, Set);
    }

    public Function<A, A> update(Function<B, B> f) {
        var self = this;
        return a -> self.set(f.apply(self.getF.apply(a)), a);
    }

    public A update(Function<B, B> f, A value) {
        return set(f.apply(getF.apply(value)), value);
    }
}