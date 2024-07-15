package org.nusco.narjillos.application.views;

import org.nusco.narjillos.application.utilities.Effects;
import org.nusco.narjillos.application.utilities.Speed;

public class StatusBarData {

    private final int ticksInLastSecond;
    private final String environmentStatistics;
    private final String performanceStatistics;
    private final Speed speed;
    private final Effects effects;
    private final String trackingStatus;
    private final boolean isBusy;

    public StatusBarData(int ticksInLastSecond, String environmentStatistics, String performanceStatistics, Speed speed,
                         Effects effects, String trackingStatus, boolean isBusy) {
        this.ticksInLastSecond = ticksInLastSecond;
        this.environmentStatistics = environmentStatistics;
        this.performanceStatistics = performanceStatistics;
        this.speed = speed;
        this.effects = effects;
        this.trackingStatus = trackingStatus;
        this.isBusy = isBusy;
    }

    public int getTicksInLastSecond() {
        return ticksInLastSecond;
    }

    public String getEnvironmentStatistics() {
        return environmentStatistics;
    }

    public String getPerformanceStatistics() {
        return performanceStatistics;
    }

    public Speed getSpeed() {
        return speed;
    }

    public Effects getEffects() {
        return effects;
    }

    public String getTrackingStatus() {
        return trackingStatus;
    }

    public boolean isBusy() {
        return isBusy;
    }
}
