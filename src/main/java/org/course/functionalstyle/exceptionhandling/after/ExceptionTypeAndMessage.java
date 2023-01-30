package org.course.functionalstyle.exceptionhandling.after;

import java.lang.reflect.Type;

public class ExceptionTypeAndMessage {
    private final String message;
    private final Class<?> exceptionType;

    private ExceptionTypeAndMessage(Class<?> exceptionType, String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Type getExceptionType() {
        return exceptionType;
    }

    public static ExceptionTypeAndMessage of(Class<?> exceptionType, String message) {
        return new ExceptionTypeAndMessage(exceptionType, message);
    }
}
