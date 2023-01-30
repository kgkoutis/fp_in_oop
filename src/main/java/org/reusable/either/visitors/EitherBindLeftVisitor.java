package org.reusable.either.visitors;

import org.reusable.either.Either;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public class EitherBindLeftVisitor<L, R, V> implements EitherVisitor<L, R, Either<V, R>> {
    private final Function<L, Either<V, R>> forLeft;

    public EitherBindLeftVisitor(Function<L, Either<V, R>> forLeft) {
        this.forLeft = forLeft;
    }

    @Override
    public Either<V, R> visit(Left<L, R> left) {
        return forLeft.apply(left.getValue());
    }

    @Override
    public Either<V, R> visit(Right<L, R> right) {
        return Either.right(right.getValue());
    }
}
