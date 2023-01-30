package org.reusable.either.visitors;

import org.reusable.either.Left;
import org.reusable.either.Right;

public interface EitherVisitor<L, R, T> {
    T visit(Left<L, R> left);

    T visit(Right<L, R> right);
}