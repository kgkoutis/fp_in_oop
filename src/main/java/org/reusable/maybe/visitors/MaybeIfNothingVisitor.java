package org.reusable.maybe.visitors;

import org.reusable.Unit;
import org.reusable.maybe.Just;
import org.reusable.maybe.Nothing;

import java.util.function.Function;

public final class MaybeIfNothingVisitor<T> implements MaybeVisitor<T, Unit> {
    private final Function<T, Unit> applier;

    public MaybeIfNothingVisitor(final Function<T, Unit> applier) {
        this.applier = applier;
    }

    @Override
    public Unit visit(final Just<T> just) {
        return Unit.get();
    }

    @Override
    public Unit visit(final Nothing<T> nothing) {
        return applier.apply(null);
    }
}