package org.reusable.maybe.visitors;

import org.reusable.Unit;
import org.reusable.maybe.Just;
import org.reusable.maybe.Nothing;

import java.util.function.Function;

public class MaybeIfJustVisitor<T> implements MaybeVisitor<T, Unit> {
    private final Function<T, Unit> applier;

    public MaybeIfJustVisitor(Function<T, Unit> applier) {
        this.applier = applier;
    }

    @Override
    public Unit visit(Just<T> just) {
        return applier.apply(just.getValue());
    }

    @Override
    public Unit visit(Nothing<T> nothing) {
        return Unit.get();
    }
}
