package org.course.composability.creationpatterns.functionalfactories.before;

public final class LoggerOptionsFactory {
    private final LoggerLevel loggerLevel;
    private final int maxLinesOutput;
    private final String outputFileName;

    public LoggerOptionsFactory(final LoggerLevel loggerLevel,
                                final int maxLinesOutput,
                                final String outputFileName) {
        this.loggerLevel = loggerLevel;
        if (maxLinesOutput < 0 || maxLinesOutput > 10000) {
            throw new IllegalArgumentException("maxLinesOutput must be >= 0 and <= 10000");
        }
        this.maxLinesOutput = maxLinesOutput;
        if (outputFileName == null || outputFileName.isEmpty()) {
            throw new IllegalArgumentException("outputFileName must be non-empty");
        }
        this.outputFileName = outputFileName;
    }

    public Options create() {
        return new Options(loggerLevel, maxLinesOutput, outputFileName);
    }
}

