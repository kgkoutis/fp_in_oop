package org.course.functionalstyle.extendingtypes.yourturn;

public class Wind {
    private final double speedInMetersPerSecond;

    public Wind(final double speedInMetersPerSecond) {
        this.speedInMetersPerSecond = speedInMetersPerSecond;
    }

    public double getSpeedInMetersPerSecond() {
        return speedInMetersPerSecond;
    }
}
