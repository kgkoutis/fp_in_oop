package org.course.composability.creationpatterns.functionalfactories.before;

/**
 * The LoggerOptionsFactory is a factory for creating Options objects to be used in some Logger.
 * Factories are in general a good way to encapsulate the creation logic of objects.
 * In this case, main doesn't need to know how to create an Options object (meaning: perform validation, input caching, alternative return values etc.).
 * <p>
 * The factory pattern is a creation pattern which is compatible with dependency injection.
 * Notice that Logger doesn't need to know how to create an Options object.
 * It only needs to know that it can get one from ...somewhere (in this case, from the loggerOptionsFactory).
 * <p>
 * But factories can be an extra class to maintain if the instantiation logic is not complex.
 * Can we avoid the usage of loggerOptionsFactory without the need to create the Options object directly in main?
 * What would be the substitute of the factory pattern?
 */
public class Main {
    public static void main(final String[] args) {
        final LoggerOptionsFactory loggerOptionsFactory = new LoggerOptionsFactory(LoggerLevel.DEBUG, 5000, "output.txt");
        final Logger logger = new Logger(loggerOptionsFactory);
        logger.log();
    }
}

