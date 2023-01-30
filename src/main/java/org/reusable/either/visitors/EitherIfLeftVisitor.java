package org.reusable.either.visitors;

import org.reusable.Unit;
import org.reusable.either.Left;
import org.reusable.either.Right;

import java.util.function.Function;

public final class EitherIfLeftVisitor<L, R> implements EitherVisitor<L, R, Unit> {
    private final Function<L, Unit> f;

    public EitherIfLeftVisitor(final Function<L, Unit> f) {
        this.f = f;
    }

    @Override
    public Unit visit(final Left<L, R> left) {
        return f.apply(left.getValue());
    }

    @Override
    public Unit visit(final Right<L, R> right) {
        return Unit.get();
    }
}