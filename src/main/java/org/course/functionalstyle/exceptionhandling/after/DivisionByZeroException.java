package org.course.functionalstyle.exceptionhandling.after;

public final class DivisionByZeroException extends Throwable {
    public DivisionByZeroException(final String message) {
        super(message);
    }

    public DivisionByZeroException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }

    public DivisionByZeroException(final Throwable cause) {
        super(cause);
    }

    public DivisionByZeroException(final String message,
                                   final Throwable cause,
                                   final boolean enableSuppression,
                                   final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DivisionByZeroException() {
    }
}
