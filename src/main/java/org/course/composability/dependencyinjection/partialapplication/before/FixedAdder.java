package org.course.composability.dependencyinjection.partialapplication.before;

final public class FixedAdder {
    final private int firstOperand;

    public FixedAdder(int firstOperand) {
        this.firstOperand = firstOperand;
    }

    public int add(int secondOperand) {
        return firstOperand + secondOperand;
    }
}
