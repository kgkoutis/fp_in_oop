package org.reusable.either.visitors;

import org.reusable.either.Left;
import org.reusable.either.Right;

public final class EitherIsLeftVisitor<L, R> implements EitherVisitor<L, R, Boolean> {
    @Override
    public Boolean visit(final Left<L, R> left) {
        return true;
    }

    @Override
    public Boolean visit(final Right<L, R> right) {
        return false;
    }
}