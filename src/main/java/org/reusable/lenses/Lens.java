package org.reusable.lenses;

import java.util.function.Function;

/**
 * Primitive lens type for creating well-behaved bidirectional transformations
 */
public final class Lens<A, B> {
    public final Function<A, B> getF;
    public final Function<B, Function<A, A>> setF;

    private Lens(final Function<A, B> get,
                 final Function<B, Function<A, A>> set) {
        this.getF = get;
        this.setF = set;
    }

    public B get(final A value) {
        return getF.apply(value);
    }

    public A set(final B value,
                 final A cont) {
        return setF.apply(value).apply(cont);
    }

    public static <A, B> Lens<A, B> of(final Function<A, B> Get,
                                       final Function<B, Function<A, A>> Set) {
        return new Lens<>(Get, Set);
    }

    public Function<A, A> update(final Function<B, B> f) {
        final Lens<A,B> self = this;
        return a -> self.set(f.apply(self.getF.apply(a)), a);
    }

    public A update(final Function<B, B> f,
                    final A value) {
        return set(f.apply(getF.apply(value)), value);
    }
}