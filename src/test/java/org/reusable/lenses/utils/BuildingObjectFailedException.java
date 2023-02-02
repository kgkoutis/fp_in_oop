package org.reusable.lenses.utils;

import java.util.List;

public final class BuildingObjectFailedException extends Exception {
    private final List<BuildingError> errors;

    public BuildingObjectFailedException(final String message,
                                         final List<BuildingError> errors) {
        super(message);
        this.errors = errors;
    }

    public BuildingObjectFailedException(final String message,
                                         final Throwable cause,
                                         final List<BuildingError> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public BuildingObjectFailedException(final Throwable cause,
                                         final List<BuildingError> errors) {
        super(cause);
        this.errors = errors;
    }

    public BuildingObjectFailedException(final String message,
                                         final Throwable cause,
                                         final boolean enableSuppression,
                                         final boolean writableStackTrace,
                                         final List<BuildingError> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
    }

    public BuildingObjectFailedException(final List<BuildingError> errors) {
        this.errors = errors;
    }

    public List<BuildingError> getErrors() {
        return errors;
    }
}
