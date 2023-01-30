package org.reusable.either.visitors;

import org.reusable.either.Either;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public final class EitherBiMapVisitor<L, R, V, U> implements EitherVisitor<L, R, Either<V, U>> {
    private final Function<L, V> forLeft;
    private final Function<R, U> forRight;

    public EitherBiMapVisitor(final Function<L, V> forLeft,
                              final Function<R, U> forRight) {
        this.forLeft = forLeft;
        this.forRight = forRight;
    }

    @Override
    public Either<V, U> visit(final Left<L, R> left) {
        return Either.left(forLeft.apply(left.getValue()));
    }

    @Override
    public Either<V, U> visit(final Right<L, R> right) {
        return Either.right(forRight.apply(right.getValue()));
    }
}