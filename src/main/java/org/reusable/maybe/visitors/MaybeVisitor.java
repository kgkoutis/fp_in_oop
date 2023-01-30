package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Nothing;

public interface MaybeVisitor<T, R> {
    R visit(Just<T> just);

    R visit(Nothing<T> nothing);
}
