package org.reusable.either.visitors;

import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public class EitherMatchVisitor<L, R, T> implements EitherVisitor<L, R, T> {
    private final Function<L, T> applierLeft;
    private final Function<R, T> applierRight;

    public EitherMatchVisitor(final Function<L, T> applierLeft,
                              final Function<R, T> applierRight) {
        this.applierLeft = applierLeft;
        this.applierRight = applierRight;
    }

    @Override
    public T visit(final Left<L, R> left) {
        return applierLeft.apply(left.getValue());
    }

    @Override
    public T visit(final Right<L, R> right) {
        return applierRight.apply(right.getValue());
    }
}
