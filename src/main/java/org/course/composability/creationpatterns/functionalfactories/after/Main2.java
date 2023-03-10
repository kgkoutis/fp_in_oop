package org.course.composability.creationpatterns.functionalfactories.after;

import java.util.function.Supplier;

/**
 * Another implementation of the same program where we replaced Logger with a lambda expression
 * Notice that we treat all computation as evaluation of functions
 */
public class Main2 {
    public static void main(final String[] args) {
        // also possible...
        log(LoggerLevel.DEBUG, 5000, "output.txt");
    }

    private static void log(final LoggerLevel loggerLevel, final  int maxLinesOutput, final  String outputFileName) {
        validateInput(maxLinesOutput, outputFileName);

        final Supplier<Options> loggerOptionsFactory = () -> new Options(LoggerLevel.DEBUG, 5000, "output.txt");
        final Runnable logging = () -> {
            System.out.println("Logger logs with options = " + loggerOptionsFactory.get());
            // do something with options
            //....
        };
        logging.run();
    }
    private static void validateInput(final int maxLinesOutput, final String outputFileName) {
        if (maxLinesOutput < 0 || maxLinesOutput > 10000) {
            throw new IllegalArgumentException("maxLinesOutput must be >= 0 and <= 10000");
        }
        if (outputFileName == null || outputFileName.isEmpty()) {
            throw new IllegalArgumentException("outputFileName must be non-empty");
        }
    }
}