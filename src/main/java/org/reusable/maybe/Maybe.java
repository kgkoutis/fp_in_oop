package org.reusable.maybe;

import org.reusable.Unit;
import org.reusable.maybe.visitors.*;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Maybe<T> {
    public abstract <R> R accept(MaybeVisitor<T, R> visitor);

    public static <T> Maybe<T> just(final T value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        return new Just<>(value);
    }

    public static <T> Maybe<T> nothing() {
        return new Nothing<>();
    }

    public static <T> Maybe<T> of(final T value) {
        if (value == null) {
            return nothing();
        }
        return just(value);
    }

    //------ extend with visitors ------------

    public <V> Maybe<V> map(final Function<T, V> f) {
        final MaybeMapVisitor<T, V> visitor = new MaybeMapVisitor<>(f);
        return this.accept(visitor);
    }

    public <V> Maybe<V> bind(final Function<T, Maybe<V>> f) {
        final MaybeBindVisitor<T, V> visitor = new MaybeBindVisitor<>(f);
        return this.accept(visitor);
    }

    public Unit ifJust(final Consumer<? super T> f) {
        final Function<T, Unit> applier = t -> {
            f.accept(t);
            return Unit.get();
        };
        final MaybeIfJustVisitor<T> visitor = new MaybeIfJustVisitor<>(applier);
        return this.accept(visitor);
    }

    public Boolean isJust() {
        final MaybeIsJustVisitor<T> visitor = new MaybeIsJustVisitor<>();
        return this.accept(visitor);
    }

    public Boolean isNothing() {
        final MaybeIsNothingVisitor<T> visitor = new MaybeIsNothingVisitor<>();
        return this.accept(visitor);
    }

    public T ifNothing(final T noneValue) {
        if (this.isJust()) {
            return ((Just<T>) this).getValue();
        }
        if (noneValue == null) {
            throw new IllegalArgumentException("noneValue cannot be null");
        }
        return noneValue;
    }

    public Unit ifNothing(final Runnable f) {
        final Function<T, Unit> applier = t -> {
            f.run();
            return Unit.get();
        };
        final MaybeIfNothingVisitor<T> visitor = new MaybeIfNothingVisitor<>(applier);
        return this.accept(visitor);
    }

    public Unit handle(final Consumer<? super T> f,
                       final Runnable none) {
        if (this.isJust()) {
            f.accept(((Just<T>) this).getValue());
        } else {
            none.run();
        }
        return Unit.get();
    }

    public <R> R match(final Function<? super T, R> f,
                       final R noneValue) {
        if (this.isJust()) {
            return f.apply(((Just<T>) this).getValue());
        } else {
            return noneValue;
        }
    }
}
