package org.course.functionalstyle.exceptionhandling.after;

public final class ApiCrashedException extends Throwable {
    public ApiCrashedException(final String message) {
        super(message);
    }

    public ApiCrashedException(final String message,
                               final Throwable cause) {
        super(message, cause);
    }

    public ApiCrashedException(final Throwable cause) {
        super(cause);
    }

    public ApiCrashedException(final String message,
                               final Throwable cause,
                               final boolean enableSuppression,
                               final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiCrashedException() {
    }
}
