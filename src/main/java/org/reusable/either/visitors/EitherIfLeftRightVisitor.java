package org.reusable.either.visitors;

import org.reusable.Unit;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public final class EitherIfLeftRightVisitor<L, R> implements EitherVisitor<L, R, Unit> {
    private final Function<L, Unit> fLeft;
    private final Function<R, Unit> fRight;

    public EitherIfLeftRightVisitor(final Function<L, Unit> fLeft,
                                    final Function<R, Unit> fRight) {
        this.fLeft = fLeft;
        this.fRight = fRight;
    }

    @Override
    public Unit visit(final Left<L, R> left) {
        return fLeft.apply(left.getValue());
    }

    @Override
    public Unit visit(final Right<L, R> right) {
        return fRight.apply(right.getValue());
    }
}
