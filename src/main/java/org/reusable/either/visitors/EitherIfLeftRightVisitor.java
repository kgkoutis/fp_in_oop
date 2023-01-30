package org.reusable.either.visitors;

import org.reusable.Unit;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public class EitherIfLeftRightVisitor<L, R> implements EitherVisitor<L, R, Unit> {
    private final Function<L, Unit> fLeft;
    private final Function<R, Unit> fRight;

    public EitherIfLeftRightVisitor(Function<L, Unit> fLeft, Function<R, Unit> fRight) {
        this.fLeft = fLeft;
        this.fRight = fRight;
    }

    @Override
    public Unit visit(Left<L, R> left) {
        return fLeft.apply(left.getValue());
    }

    @Override
    public Unit visit(Right<L, R> right) {
        return fRight.apply(right.getValue());
    }
}
