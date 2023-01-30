package org.course.functionalstyle.extendingtypes.after;

public final class WindExtender extends Wind {
    private final Wind wind;

    public WindExtender(final Wind wind) {
        super(wind.getSpeedInMetersPerSecond());
        this.wind = wind;
    }

    public WindExtender(final double speedInMetersPerSecond) {
        super(speedInMetersPerSecond);
        this.wind = new Wind(speedInMetersPerSecond);
    }

    public double getSpeedInMetersPerSecond() {
        return wind.getSpeedInMetersPerSecond();
    }

    public double getSpeedInKilometersPerHour() {
        return wind.getSpeedInMetersPerSecond() * 3.6;
    }
}
