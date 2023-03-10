package org.course.functionalstyle.lazyevaluation.before;

import java.util.Objects;

public final class FakeDbConnection {
    private final String currentThreadName;

    public FakeDbConnection(final String currentThreadName) {
        this.currentThreadName = currentThreadName;
    }

    public String currentThreadName() {
        return currentThreadName;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final FakeDbConnection that = (FakeDbConnection) obj;
        return Objects.equals(this.currentThreadName, that.currentThreadName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentThreadName);
    }

    @Override
    public String toString() {
        return "FakeDbConnection[" +
                "currentThreadName=" + currentThreadName + ']';
    }
}

