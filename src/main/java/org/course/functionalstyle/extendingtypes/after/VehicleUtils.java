package org.course.functionalstyle.extendingtypes.after;

public final class VehicleUtils {
    public static String lease(final Vehicle vehicle, final int days) {
        return "Leasing vehicle of brand " + vehicle.getBrand() + " for " + days + " days";
    }
}
