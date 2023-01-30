package org.reusable.prisms;

import org.reusable.maybe.Maybe;

public class PrismExtensions {
    /**
     * Identity lens
     */
    public static <T> Prism<T, T> identity() {
        return Prism.of(
                Maybe::just,
                a -> _1 -> a);
    }
}
