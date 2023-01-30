package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Nothing;

public class MaybeIsJustVisitor<T> implements MaybeVisitor<T,Boolean> {
    @Override
    public Boolean visit(Just<T> just) {
        return true;
    }

    @Override
    public Boolean visit(Nothing<T> nothing) {
        return false;
    }
}
