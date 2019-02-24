package com.lucianamariei.hw.carlog.util;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
public enum DistanceUnit {
    KM (1f, "km", "kilometres"),
    MI (1.609f, "mi", "miles");

    private float unitToKmRatio;
    private String shortName;
    private String longName;

    DistanceUnit(float unitToKmRatio, String shortName, String longName) {
        this.unitToKmRatio = unitToKmRatio;
        this.shortName = shortName;
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public static int convert(int distance, DistanceUnit oldUnit, DistanceUnit newUnit) {
        return (int)((newUnit.unitToKmRatio * (float)distance) / oldUnit.unitToKmRatio);
    }
}