package org.course.composability.handling_void_and_no_input.after;

import org.reusable.Unit;

/**
 * You see in class A we tweaked both functions to receive and return a Unit object.
 * What is Unit? It is a class that has a single instance. The Unit pattern is basically to introduce a garbage variable of zero importance that signifies the absence of a value.
 * It is a very common technique in functional programming.
 *
 * Some languages may have another name for Unit, in Haskell e.g. is symbolized by 2 empty brackets like so: ().
 * It's name depends on the language but the semantics are the same.
 *
 * Since now both functions receive an input and return an output we can now compose them.
 *
 * Below we see that main instantiates a unit object and passes it to the compose function. The return value is discarded.
 *
 * Java doesn't allow _ to be used as a variable name so we use _1 instead.
 * */
public class Main {
    public static void main(final String[] args) {
        final A a = new A();
        final Unit unit = Unit.get();
        final Unit _1 = a.compose(unit);
    }
}