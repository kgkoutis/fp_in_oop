package org.course.composability.handling_void_and_no_input.after;

import org.reusable.Unit;

import java.util.function.Function;

final public class A {
    public Unit doSomething(Unit unit) {
        // do something
        return Unit.get();
    }

    public Unit doSomethingElse(Unit unit) {
        // do something else
        return Unit.get();
    }

    public Unit compose(Unit unit) {
        Function<Unit, Unit> f = this::doSomething;
        Function<Unit, Unit> g = this::doSomethingElse;
        return f.andThen(g).apply(unit);
    }
}
