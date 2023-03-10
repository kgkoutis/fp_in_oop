package org.reusable;

import java.util.Arrays;
import java.util.Collections;

public final class Lists {
    @SafeVarargs
    public static <T> java.util.List<T> listOf(final T... elements) {
        return Collections.unmodifiableList(Arrays.asList(elements));
    }
}
