package org.course.composability.dependencyinjection.functionalfactories.after;

import java.util.function.Supplier;

// Another implementation of the same program where we replaced Program with a lambda expression
// Notice that we treat all computation as evaluation of functions.
public class Main2 {
    public static void main(final String[] args) {
        // also possible...
        final Supplier<Options> loggerOptionsFactory = () -> new Options(true, 10, "output.txt");
        final Runnable runnable = () -> System.out.println("Hello World!" + loggerOptionsFactory.get());
        runnable.run();
    }
}