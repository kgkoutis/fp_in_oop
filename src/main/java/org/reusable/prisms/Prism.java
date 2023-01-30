package org.reusable.prisms;

import org.reusable.lenses.Lens;
import org.reusable.maybe.Maybe;

import java.util.function.Function;

/**
 * Primitive prism type for creating transformations through Maybe
 */
public final class Prism<A, B> {
    public final Function<A, Maybe<B>> getF;
    public final Function<B, Function<A, A>> setF;

    private Prism(final Function<A, Maybe<B>> get,
                  final Function<B, Function<A, A>> set) {
        this.getF = get;
        this.setF = set;
    }

    public Maybe<B> get(final A value) {
        return getF.apply(value);
    }

    public A set(final B value,
                 final A cont) {
        return setF.apply(value).apply(cont);
    }

    public static <A, B> Prism<A, B> of(final Function<A, Maybe<B>> Get,
                                        final Function<B, Function<A, A>> Set) {
        return new Prism<>(Get, Set);
    }

    public static <A, B> Prism<A, B> of(final Lens<A, B> lens) {

        return of(
                a -> Maybe.just(lens.getF.apply(a)),
                lens.setF
        );
    }

    public static <A, B> Prism<A, B> mof(final Lens<A, Maybe<B>> lens) {
        return of(
                lens.getF,
                v -> lens.setF.apply(Maybe.just(v))
        );
    }

    public Function<A, A> update(final Function<B, B> f) {
        final var self = this;
        return a -> self.getF.apply(a).map(v -> self.set(f.apply(v), a)).ifNothing(a);
    }

    public A update(final Function<B, B> f,
                    final A value) {
        final var self = this;
        return getF.apply(value).map(v -> self.set(f.apply(v), value)).ifNothing(value);
    }

    public static <A, B> Prism<A, B> fromLens(final Lens<A, B> value) {
        return of(value);
    }

    public static <A, B> Prism<A, B> mfromLens(final Lens<A, Maybe<B>> value) {
        return mof(value);
    }
}

