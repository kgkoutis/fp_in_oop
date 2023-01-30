package org.course.typestypestypes.primitiveobsession.after;

import java.util.Comparator;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * This could be a record in later Java versions.
 */
public final class DepartmentCode implements Formattable, Comparable<DepartmentCode> {
    private static final Pattern pattern = Pattern.compile("[0-9]{6}");
    private final String code;
    private final int hashCode;

    private DepartmentCode(final String code) {
        this.code = code;
        this.hashCode = Objects.hash(code);
    }

    // public static factory method
    public static DepartmentCode of(final String code) {
        if (inputIsInvalid(code))
            throw new IllegalArgumentException("Invalid department code");

        return new DepartmentCode(code);
    }

    private static boolean inputIsInvalid(final String code) {
        return code == null || code.length() != 6 || !pattern.matcher(code).matches();
    }

    public String getCode() {
        return code;
    }

    public Location getLocation() {
        return code.charAt(0) < '6' ? Location.AMSTERDAM : Location.GRONINGEN;
    }

    // copy method
    public DepartmentCode copy() {
        return new DepartmentCode(this.code);
    }

    // standard boilerplate code
    public boolean equals(final DepartmentCode code) {
        return Objects.equals(this, code);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DepartmentCode that = (DepartmentCode) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public String toString() {
        return "DepartmentCode{" +
                "code='" + code + '\'' +
                '}';
    }

    // extra methods
    private static final Comparator<DepartmentCode> CODE_COMPARATOR = Comparator.comparing(DepartmentCode::getCode);

    @Override
    public int compareTo(final DepartmentCode o) {
        return CODE_COMPARATOR.compare(this, o);
    }

    @Override
    public void formatTo(final Formatter formatter,
                         final int flags,
                         final int width,
                         final int precision) {
        // possibly more control over the formatting
        formatter.format("%s", code);
    }
}
