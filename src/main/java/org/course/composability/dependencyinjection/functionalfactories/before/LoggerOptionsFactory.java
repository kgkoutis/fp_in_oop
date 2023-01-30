package org.course.composability.dependencyinjection.functionalfactories.before;

final public class LoggerOptionsFactory {
    private final boolean isVerbose;
    private final int maxLinesOutput;
    private final String outputFileName;

    public LoggerOptionsFactory(boolean isVerbose, int maxLinesOutput, String outputFileName) {
        this.isVerbose = isVerbose;
        if (maxLinesOutput < 0 || maxLinesOutput > 100) {
            throw new IllegalArgumentException("maxLinesOutput must be >= 0 and <= 100");
        }
        this.maxLinesOutput = maxLinesOutput;
        if (outputFileName == null || outputFileName.isEmpty()) {
            throw new IllegalArgumentException("outputFileName must be non-empty");
        }
        this.outputFileName = outputFileName;
    }

    public Options create() {
        return new Options(isVerbose, maxLinesOutput, outputFileName);
    }
}

