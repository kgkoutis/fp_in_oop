package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Nothing;

public class MaybeIsNothingVisitor<T> implements MaybeVisitor<T,Boolean> {
    @Override
    public Boolean visit(Just<T> just) {
        return false;
    }

    @Override
    public Boolean visit(Nothing<T> nothing) {
        return true;
    }
}