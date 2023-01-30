package org.course.composability.dependencyinjection.functionalfactories.before;

/**
 * The LoggerOptionsFactory is a factory for creating Options objects.
 * Factories are in general a good way to encapsulate the creation logic of objects.
 * In this case, main doesn't need to know how to create an Options object (meaning: perform validation, input caching, alternative return values etc.).
 *
 * The factory pattern is a creation pattern which is compatible with dependency injection.
 * Notice that Program doesn't need to know how to create an Options object.
 * It only needs to know that it can get one from ...somewhere (in this case, from the loggerOptionsFactory).
 *
 * Can we avoid the usage of loggerOptionsFactory without the need to create the Options object directly in main?
 * What would be the substitute of the factory pattern?
 */
public class Main {
    public static void main(String[] args) {
        final LoggerOptionsFactory loggerOptionsFactory = new LoggerOptionsFactory(true, 10, "output.txt");
        final Program program = new Program(loggerOptionsFactory);
        program.run();
    }
}
