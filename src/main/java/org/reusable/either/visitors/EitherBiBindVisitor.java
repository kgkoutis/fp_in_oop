package org.reusable.either.visitors;

import org.reusable.either.Either;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public final class EitherBiBindVisitor<L, R, V, U> implements EitherVisitor<L, R, Either<V, U>> {
    private final Function<L, Either<V, U>> forLeft;
    private final Function<R, Either<V, U>> forRight;

    public EitherBiBindVisitor(final Function<L, Either<V, U>> forLeft,
                               final Function<R, Either<V, U>> forRight) {
        this.forLeft = forLeft;
        this.forRight = forRight;
    }

    @Override
    public Either<V, U> visit(final Left<L, R> left) {
        return forLeft.apply(left.getValue());
    }

    @Override
    public Either<V, U> visit(final Right<L, R> right) {
        return forRight.apply(right.getValue());
    }
}
