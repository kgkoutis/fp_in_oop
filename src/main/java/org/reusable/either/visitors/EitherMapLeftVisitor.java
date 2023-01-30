package org.reusable.either.visitors;

import org.reusable.either.Either;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public class EitherMapLeftVisitor<L, R, V> implements EitherVisitor<L, R, Either<V, R>> {
    private final Function<L, V> forLeft;

    public EitherMapLeftVisitor(Function<L, V> forLeft) {
        this.forLeft = forLeft;
    }

    @Override
    public Either<V, R> visit(Left<L, R> left) {
        return Either.left(forLeft.apply(left.getValue()));
    }

    @Override
    public Either<V, R> visit(Right<L, R> right) {
        return Either.right(right.getValue());
    }
}
