package org.course.composability.handling_void_and_no_input.yourturn;

import org.reusable.Unit;

import java.util.function.Function;

public final class A {
    public void doSomething() { // FP problematic form: no input (and no output)
        //....
    }

    public void doSomethingElse() { // FP problematic form: no output (and no input)
        //....
    }


    public void compose() {
        // How could we compose the 2 functions above?
        // hint: it's ok to alter the functions as long as the semantics are preserved

        // final Function<?, ?> f = this::doSomething;
        // final Function<?, ?> g = this::doSomethingElse;

    }
}
