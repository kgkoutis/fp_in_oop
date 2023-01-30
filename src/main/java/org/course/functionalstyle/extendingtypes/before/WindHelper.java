package org.course.functionalstyle.extendingtypes.before;

public final class WindHelper {
    public static double getSpeedInKilometersPerHour(Wind wind) {
        return wind.getSpeedInMetersPerSecond() * 3.6;
    }
}
