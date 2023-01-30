package org.reusable.either.visitors;

import org.reusable.Unit;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public class EitherIfRightVisitor<L,R> implements EitherVisitor<L,R, Unit> {
    private final Function<R, Unit> f;

    public EitherIfRightVisitor(Function<R, Unit> f) {
        this.f = f;
    }

    @Override
    public Unit visit(Left<L,R> left) {
        return Unit.get();
    }

    @Override
    public Unit visit(Right<L,R> right) {
        return f.apply(right.getValue());
    }
}