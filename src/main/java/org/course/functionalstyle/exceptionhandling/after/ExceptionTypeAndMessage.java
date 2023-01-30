package org.course.functionalstyle.exceptionhandling.after;

import java.lang.reflect.Type;

public final class ExceptionTypeAndMessage {
    private final String message;
    private final Class<?> exceptionType;

    private ExceptionTypeAndMessage(final Class<?> exceptionType,
                                    final String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Type getExceptionType() {
        return exceptionType;
    }

    public static ExceptionTypeAndMessage of(final Class<?> exceptionType,
                                             final String message) {
        return new ExceptionTypeAndMessage(exceptionType, message);
    }
}
