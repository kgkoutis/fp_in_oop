package org.reusable.maybe;

import org.reusable.Unit;
import org.reusable.maybe.visitors.*;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Maybe<T> {
    public abstract <R> R accept(MaybeVisitor<T, R> visitor);

    public static <T> Maybe<T> just(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        return new Just<>(value);
    }

    public static <T> Maybe<T> nothing() {
        return new Nothing<>();
    }

    public static <T> Maybe<T> of(T value) {
        if (value == null) {
            return nothing();
        }
        return just(value);
    }

    //------ extend with visitors ------------

    public <V> Maybe<V> map(Function<T,V> f) {
        MaybeMapVisitor<T, V> visitor = new MaybeMapVisitor<>(f);
        return this.accept(visitor);
    }

    public <V> Maybe<V> bind(Function<T, Maybe<V>> f) {
        MaybeBindVisitor<T, V> visitor = new MaybeBindVisitor<>(f);
        return this.accept(visitor);
    }

    public Unit ifJust(Consumer<? super T> f) {
        Function<T, Unit> applier = t -> {
            f.accept(t);
            return Unit.get();
        };
        MaybeIfJustVisitor<T> visitor = new MaybeIfJustVisitor<>(applier);
        return this.accept(visitor);
    }

    public Boolean isJust() {
        MaybeIsJustVisitor<T> visitor = new MaybeIsJustVisitor<>();
        return this.accept(visitor);
    }

    public Boolean isNothing() {
        MaybeIsNothingVisitor<T> visitor = new MaybeIsNothingVisitor<>();
        return this.accept(visitor);
    }

    public T ifNothing(T noneValue)
    {
        if (this.isJust()) {
            return ((Just<T>)this).getValue();
        }
        if(noneValue == null) {
            throw new IllegalArgumentException("noneValue cannot be null");
        }
        return noneValue;
    }

    public Unit ifNothing(Runnable f) {
        Function<T, Unit> applier = t -> {
            f.run();
            return Unit.get();
        };
        MaybeIfNothingVisitor<T> visitor = new MaybeIfNothingVisitor<>(applier);
        return this.accept(visitor);
    }

    public Unit handle(Consumer<? super T> f, Runnable none) {
        if (this.isJust()) {
            f.accept(((Just<T>)this).getValue());
        } else {
            none.run();
        }
        return Unit.get();
    }

    public <R> R match(Function<? super T, R> f, R noneValue) {
        if (this.isJust()) {
            return f.apply(((Just<T>) this).getValue());
        } else {
            return noneValue;
        }
    }
}
