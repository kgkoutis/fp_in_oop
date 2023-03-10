package org.course.composability.creationpatterns.partialapplication.before;

public final class FixedAdder {
    final private int firstOperand;

    public FixedAdder(final int firstOperand) {
        this.firstOperand = firstOperand;
    }

    public int add(final int secondOperand) {
        return firstOperand + secondOperand;
    }
}
