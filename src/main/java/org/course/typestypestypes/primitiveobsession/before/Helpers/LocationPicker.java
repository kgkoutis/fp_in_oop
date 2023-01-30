package org.course.typestypestypes.primitiveobsession.before.Helpers;

import org.course.typestypestypes.primitiveobsession.before.Location;

public class LocationPicker {
    public Location getLocation(final String departmentCode) {
        // TODO: validate departmentCode
        switch (departmentCode.charAt(0)) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
                return Location.AMSTERDAM;
            default:
                return Location.GRONINGEN;
        }
    }
}
