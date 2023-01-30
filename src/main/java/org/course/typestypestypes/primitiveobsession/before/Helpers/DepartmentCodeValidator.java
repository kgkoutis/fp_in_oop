package org.course.typestypestypes.primitiveobsession.before.Helpers;

import java.util.regex.Pattern;

public class DepartmentCodeValidator {

    private static final Pattern pattern = Pattern.compile("[0-9]{6}");

    public boolean isValid(String code) {
        return code.length() == 6 && meetsPattern(code);
    }

    private boolean meetsPattern(String code) {
        return pattern.matcher(code).matches();
    }
}
