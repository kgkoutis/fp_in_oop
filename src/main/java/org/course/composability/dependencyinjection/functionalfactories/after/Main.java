package org.course.composability.dependencyinjection.functionalfactories.after;

import java.util.function.Supplier;

/**
 * Here we tossed away the factory class and replaced it with a lambda expression. Think about it: it does the same job
 * without forcing main to instantiate Options itself in order to pass it to Program constructor.
 * <p>
 * But remember that the LoggerOptionsFactory might capture an important abstraction for our business logic.
 * Meaning, if we foresee that its functionality its really central in our domain model we might better choose to keep it,
 * rather than having just a lambda expression.
 *
 * Notice for example here that the lambda expression, as implemented here, does not validate input parameters.
 * It just creates an Options object with the given parameters. This is not a problem if we are sure that the parameters
 * we pass are always valid. But if we are not sure, we might want to keep the factory class and add validation logic to it.
 * <p>
 * PS: Do you think that Logger can be more or less easily tested?
 */
public class Main {
    public static void main(final String[] args) {
        // also possible...
        log(LoggerLevel.DEBUG, 5000, "output.txt");
    }

    private static void log(final LoggerLevel loggerLevel, final  int maxLinesOutput, final  String outputFileName) {
        validateInput(maxLinesOutput);

        final Supplier<Options> loggerOptionsFactory = () -> new Options(loggerLevel, maxLinesOutput, outputFileName);
        final Logger logger = new Logger(loggerOptionsFactory);
        logger.log();
    }

    private static void validateInput(final int maxLinesOutput) {
        if (maxLinesOutput < 0 || maxLinesOutput > 10000) {
            throw new IllegalArgumentException("maxLinesOutput must be >= 0 and <= 10000");
        }
    }
}

