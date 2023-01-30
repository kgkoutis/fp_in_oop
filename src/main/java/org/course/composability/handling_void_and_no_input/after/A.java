package org.course.composability.handling_void_and_no_input.after;

import org.reusable.Unit;

import java.util.function.Function;

public final class A {
    public Unit doSomething(final Unit unit) {
        // do something
        return Unit.get();
    }

    public Unit doSomethingElse(final Unit unit) {
        // do something else
        return Unit.get();
    }

    public Unit compose(final Unit unit) {
        final Function<Unit, Unit> f = this::doSomething;
        final Function<Unit, Unit> g = this::doSomethingElse;
        return f.andThen(g).apply(unit);
    }
}
