package org.reusable.either;

import org.reusable.Unit;
import org.reusable.either.visitors.*;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Either<L, R> {
    public abstract <T> T accept(EitherVisitor<L, R, T> visitor);
    public static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

    //------ extend with visitors ------------

    public boolean isLeft() {
        EitherIsLeftVisitor<L, R> visitor = new EitherIsLeftVisitor<>();
        return this.accept(visitor);
    }

    public boolean isRight() {
        return !this.isLeft();
    }

    public <V> Either<L,V> map(Function<R,V> forRight) {
        EitherMapVisitor<L, R, V> visitor = new EitherMapVisitor<>(forRight);
        return this.accept(visitor);
    }

    public <V> Either<L,V> mapRight(Function<R,V> forRight) {
        return this.map(forRight);
    }

    public <V> Either<V,R> mapLeft(Function<L,V> forLeft) {
        EitherMapLeftVisitor<L, R, V> visitor = new EitherMapLeftVisitor<>(forLeft);
        return this.accept(visitor);
    }

    public <V> Either<L, V> bind(Function<R, Either<L, V>> forRight) {
        EitherBindVisitor<L, R, V> visitor = new EitherBindVisitor<>(forRight);
        return this.accept(visitor);
    }

    public <V> Either<L, V> bindRight(Function<R, Either<L, V>> forRight) {
        return this.bind(forRight);
    }

    public <V> Either<V, R> bindLeft(Function<L, Either<V, R>> forLeft) {
        EitherBindLeftVisitor<L, R, V> visitor = new EitherBindLeftVisitor<>(forLeft);
        return this.accept(visitor);
    }

    public <V,U> Either<V, U> biMap(Function<L, V> forLeft, Function<R, U> forRight) {
        EitherBiMapVisitor<L, R, V, U> visitor = new EitherBiMapVisitor<>(forLeft, forRight);
        return this.accept(visitor);
    }

    public <V,U> Either<V, U> biBind(Function<L, Either<V, U>> forLeft, Function<R, Either<V, U>> forRight) {
        EitherBiBindVisitor<L, R, V, U> visitor = new EitherBiBindVisitor<>(forLeft, forRight);
        return this.accept(visitor);
    }

    public Unit ifRight(Consumer<? super R> forRight) {
        Function<R, Unit> applier = r -> {
            forRight.accept(r);
            return Unit.get();
        };
        EitherIfRightVisitor<L,R> visitor = new EitherIfRightVisitor<>(applier);
        return this.accept(visitor);
    }

    public Unit ifLeft(Consumer<? super L> forLeft) {
        Function<L, Unit> applier = l -> {
            forLeft.accept(l);
            return Unit.get();
        };
        EitherIfLeftVisitor<L,R> visitor = new EitherIfLeftVisitor<>(applier);
        return this.accept(visitor);
    }

    public Unit handle(Consumer<? super L> forLeft, Consumer<? super R> forRight) {
        Function<L, Unit> applierLeft = l -> {
            forLeft.accept(l);
            return Unit.get();
        };
        Function<R, Unit> applierRight = r -> {
            forRight.accept(r);
            return Unit.get();
        };
        EitherIfLeftRightVisitor<L,R> visitor = new EitherIfLeftRightVisitor<>(applierLeft, applierRight);
        return this.accept(visitor);
    }
}

