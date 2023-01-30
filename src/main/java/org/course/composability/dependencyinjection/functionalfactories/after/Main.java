package org.course.composability.dependencyinjection.functionalfactories.after;

import java.util.function.Supplier;

/**
 * Here we tossed away the factory class and replaced it with a lambda expression. Think about it: it does the same job
 * without forcing main to instantiate Options itself in order to pass it to Program constructor.
 * <p>
 * But remember that the LoggerOptionsFactory might capture an important abstraction for our business logic.
 * Meaning, if we foresee that its functionality its really central in our domain model we might better choose to keep it,
 * rather than having just a lambda expression.
 * <p>
 * PS: Do you think that Program can be more or less easily tested?
 */
public class Main {
    public static void main(final String[] args) {
        // also possible...
        final Supplier<Options> loggerOptionsFactory = () -> new Options(true, 10, "output.txt");
        final Program program = new Program(loggerOptionsFactory);
        program.run();
    }
}

