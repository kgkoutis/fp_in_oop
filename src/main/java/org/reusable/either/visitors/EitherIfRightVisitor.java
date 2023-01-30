package org.reusable.either.visitors;

import org.reusable.Unit;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public final class EitherIfRightVisitor<L, R> implements EitherVisitor<L, R, Unit> {
    private final Function<R, Unit> f;

    public EitherIfRightVisitor(final Function<R, Unit> f) {
        this.f = f;
    }

    @Override
    public Unit visit(final Left<L, R> left) {
        return Unit.get();
    }

    @Override
    public Unit visit(final Right<L, R> right) {
        return f.apply(right.getValue());
    }
}