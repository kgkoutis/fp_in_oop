package org.reusable.either.visitors;

import org.reusable.either.Either;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public class EitherBiMapVisitor<L, R, V, U> implements EitherVisitor<L, R, Either<V, U>> {
    private final Function<L, V> forLeft;
    private final Function<R, U> forRight;

    public EitherBiMapVisitor(Function<L, V> forLeft, Function<R, U> forRight) {
        this.forLeft = forLeft;
        this.forRight = forRight;
    }

    @Override
    public Either<V, U> visit(Left<L, R> left) {
        return Either.left(forLeft.apply(left.getValue()));
    }

    @Override
    public Either<V, U> visit(Right<L, R> right) {
        return Either.right(forRight.apply(right.getValue()));
    }
}