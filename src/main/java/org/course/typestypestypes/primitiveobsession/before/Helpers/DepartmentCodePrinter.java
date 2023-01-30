package org.course.typestypestypes.primitiveobsession.before.Helpers;

public final class DepartmentCodePrinter {
    public DepartmentCodePrinter() {
    }

    public void print(final String code,
                      final String name) {
        // TODO: validate code and name
        System.out.println("The IBM department " + name + " is: " + code);
    }
}