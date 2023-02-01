package org.reusable.either;

import org.reusable.Unit;
import org.reusable.either.visitors.*;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Either<L, R> {
    public abstract <T> T accept(final EitherVisitor<L, R, T> visitor);

    public static <L, R> Either<L, R> left(final L value) {
        return new Left<>(value);
    }

    public static <L, R> Either<L, R> right(final R value) {
        return new Right<>(value);
    }

    //------ extend with visitors ------------

    public boolean isLeft() {
        final EitherIsLeftVisitor<L, R> visitor = new EitherIsLeftVisitor<>();
        return this.accept(visitor);
    }

    public boolean isRight() {
        return !this.isLeft();
    }

    public <V> Either<L, V> mapRight(final Function<R, V> forRight) {
        final EitherMapVisitor<L, R, V> visitor = new EitherMapVisitor<>(forRight);
        return this.accept(visitor);
    }

    public <V> Either<V, R> mapLeft(final Function<L, V> forLeft) {
        final EitherMapLeftVisitor<L, R, V> visitor = new EitherMapLeftVisitor<>(forLeft);
        return this.accept(visitor);
    }

    public <V> Either<L, V> bindRight(final Function<R, Either<L, V>> forRight) {
        final EitherBindRightVisitor<L, R, V> visitor = new EitherBindRightVisitor<>(forRight);
        return this.accept(visitor);
    }

    public <V> Either<V, R> bindLeft(final Function<L, Either<V, R>> forLeft) {
        final EitherBindLeftVisitor<L, R, V> visitor = new EitherBindLeftVisitor<>(forLeft);
        return this.accept(visitor);
    }

    public <V, U> Either<V, U> biMap(final Function<L, V> forLeft,
                                     final Function<R, U> forRight) {
        final EitherBiMapVisitor<L, R, V, U> visitor = new EitherBiMapVisitor<>(forLeft, forRight);
        return this.accept(visitor);
    }

    public <V, U> Either<V, U> biBind(final Function<L, Either<V, U>> forLeft,
                                      final Function<R, Either<V, U>> forRight) {
        final EitherBiBindVisitor<L, R, V, U> visitor = new EitherBiBindVisitor<>(forLeft, forRight);
        return this.accept(visitor);
    }

    public Unit ifRight(final Consumer<? super R> forRight) {
        final Function<R, Unit> applier = r -> {
            forRight.accept(r);
            return Unit.get();
        };
        final EitherIfRightVisitor<L, R> visitor = new EitherIfRightVisitor<>(applier);
        return this.accept(visitor);
    }

    public Unit ifLeft(final Consumer<? super L> forLeft) {
        final Function<L, Unit> applier = l -> {
            forLeft.accept(l);
            return Unit.get();
        };
        final EitherIfLeftVisitor<L, R> visitor = new EitherIfLeftVisitor<>(applier);
        return this.accept(visitor);
    }

    public Unit handle(final Consumer<? super L> forLeft,
                       final Consumer<? super R> forRight) {
        final Function<L, Unit> applierLeft = l -> {
            forLeft.accept(l);
            return Unit.get();
        };
        final Function<R, Unit> applierRight = r -> {
            forRight.accept(r);
            return Unit.get();
        };
        final EitherIfLeftRightVisitor<L, R> visitor = new EitherIfLeftRightVisitor<>(applierLeft, applierRight);
        return this.accept(visitor);
    }

    public <T> T match(final Function<L, T> forLeft,
                       final Function<R, T> forRight) {
        final EitherMatchVisitor<L, R, T> visitor = new EitherMatchVisitor<>(forLeft, forRight);
        return this.accept(visitor);
    }
}

