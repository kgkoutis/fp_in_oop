package org.course.functionalstyle.lenses.before;

import java.util.Objects;

public final class Lastname {
    private final String value;

    public Lastname(final String value) {
        this.value = value;
    }

    public static Lastname of(final String value) {
        return new Lastname(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final Lastname that = (Lastname) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
