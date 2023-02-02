package org.reusable.lenses.utils;

/**
 * This class is an example object building error.
 * */
public class StringTooLongBuildingError extends BuildingError {
    private final String value;
    private final int maxLength;
    private final String message;

    private StringTooLongBuildingError(final String value,
                                       final int maxLength) {
        this.message = "String too long: " + value + " (max length: " + maxLength + ")";
        this.value = value;
        this.maxLength = maxLength;
    }

    public static StringTooLongBuildingError of(final String value,
                                                final int maxLength) {
        return new StringTooLongBuildingError(value, maxLength);
    }

    public String getValue() {
        return value;
    }

    public int getMaxLength() {
        return maxLength;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
