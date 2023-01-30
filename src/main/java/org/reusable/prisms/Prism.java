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

    private Prism(Function<A, Maybe<B>> get, Function<B, Function<A, A>> set) {
        this.getF = get;
        this.setF = set;
    }

    public Maybe<B> get(A value) {
        return getF.apply(value);
    }

    public A set(B value, A cont) {
        return setF.apply(value).apply(cont);
    }

    public static <A, B> Prism<A, B> of(Function<A, Maybe<B>> Get, Function<B, Function<A, A>> Set) {
        return new Prism<>(Get, Set);
    }

    public static <A, B> Prism<A, B> of(Lens<A, B> lens) {

        return of(
                a -> Maybe.just(lens.getF.apply(a)),
                lens.setF
        );
    }

    public static <A, B> Prism<A, B> mof(Lens<A, Maybe<B>> lens) {
        return of(
                lens.getF,
                v -> lens.setF.apply(Maybe.just(v))
        );
    }

    public Function<A, A> update(Function<B, B> f) {
        var self = this;
        return a -> self.getF.apply(a).map(v -> self.set(f.apply(v), a)).ifNothing(a);
    }

    public A update(Function<B, B> f, A value) {
        var self = this;
        return getF.apply(value).map(v -> self.set(f.apply(v), value)).ifNothing(value);
    }

    public static <A, B> Prism<A, B> fromLens(Lens<A, B> value) {
        return of(value);
    }

    public static <A, B> Prism<A, B> mfromLens(Lens<A, Maybe<B>> value) {
        return mof(value);
    }
}

