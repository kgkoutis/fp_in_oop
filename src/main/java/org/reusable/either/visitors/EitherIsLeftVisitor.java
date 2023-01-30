package org.reusable.either.visitors;

import org.reusable.either.Left;
import org.reusable.either.Right;

public class EitherIsLeftVisitor<L, R> implements EitherVisitor<L, R, Boolean> {
    @Override
    public Boolean visit(Left<L, R> left) {
        return true;
    }

    @Override
    public Boolean visit(Right<L, R> right) {
        return false;
    }
}