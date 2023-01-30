package org.course.composability.handling_void_and_no_input.before;

public final class A {
    public void doSomething() { // FP problematic form: no input (and no output)
        //....
    }

    public void doSomethingElse() { // FP problematic form: no output (and no input)
        //....
    }

    public void compose() {
        doSomething();
        doSomethingElse();
    }
}
