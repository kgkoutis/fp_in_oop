package org.course.typestypestypes.primitiveobsession.before.Helpers;

public class DepartmentCodeEqualityComparer {
    public boolean areEqual(String code1, String code2) {
        DepartmentCodeValidator validator = new DepartmentCodeValidator();
        if (validator.isValid(code1) && validator.isValid(code2)) {
            return code1.equals(code2);
        }
        throw new IllegalArgumentException("Invalid department code");
    }
}
