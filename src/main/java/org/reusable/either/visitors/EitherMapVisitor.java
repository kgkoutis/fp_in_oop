package org.reusable.either.visitors;

import org.reusable.either.Either;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public final class EitherMapVisitor<L, R, V> implements EitherVisitor<L, R, Either<L, V>> {
    private final Function<R, V> forRight;

    public EitherMapVisitor(final Function<R, V> forRight) {
        this.forRight = forRight;
    }

    @Override
    public Either<L, V> visit(final Left<L, R> left) {
        return Either.left(left.getValue());
    }

    @Override
    public Either<L, V> visit(final Right<L, R> right) {
        return Either.right(forRight.apply(right.getValue()));
    }
}