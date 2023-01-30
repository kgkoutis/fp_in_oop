package org.reusable.either.visitors;

import org.reusable.Unit;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public class EitherIfLeftVisitor<L,R> implements EitherVisitor<L,R, Unit>
{
    private final Function<L, Unit> f;

    public EitherIfLeftVisitor(Function<L, Unit> f) {
        this.f = f;
    }

    @Override
    public Unit visit(Left<L, R> left) {
        return f.apply(left.getValue());
    }

    @Override
    public Unit visit(Right<L, R> right) {
        return Unit.get();
    }
}