package org.course.functionalstyle.lenses.before;

import java.util.Objects;

public final class Firstname {
    private final String value;

    public Firstname(String value) {
        this.value = value;
    }

    public static Firstname of(String value) {
        return new Firstname(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Firstname) obj;
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
