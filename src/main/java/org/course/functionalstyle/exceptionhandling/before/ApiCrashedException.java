package org.course.functionalstyle.exceptionhandling.before;

public class ApiCrashedException extends Throwable {
    public ApiCrashedException(String message) {
        super(message);
    }

    public ApiCrashedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiCrashedException(Throwable cause) {
        super(cause);
    }

    public ApiCrashedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiCrashedException() {
    }
}
