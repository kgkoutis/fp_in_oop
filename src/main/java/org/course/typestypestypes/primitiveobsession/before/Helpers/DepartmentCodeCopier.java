package org.course.typestypestypes.primitiveobsession.before.Helpers;

import org.course.typestypestypes.primitiveobsession.before.DepartmentCode;

public final class DepartmentCodeCopier {
    public static DepartmentCode copy(final String departmentCode) {
        final DepartmentCodeValidator validator = new DepartmentCodeValidator();
        if (validator.isValid(departmentCode))
            return new DepartmentCode(departmentCode);

        throw new IllegalArgumentException("Invalid department code");
    }
}
