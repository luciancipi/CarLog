package com.lucianamariei.hw.carlog.util;

/**
 * Created by Lucian Amariei on 24.02.2019.
 */
public enum FuelUnit {
    LITRE(1f, "Ltr.", "litres"),
    GALLON(3.785f, "Gal.", "gallons");

    private float unitToLitreRatio;
    private String shortName;
    private String longName;

    FuelUnit(float unitToLitreRatio, String shortName, String longName) {
        this.unitToLitreRatio = unitToLitreRatio;
        this.shortName = shortName;
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public static int convert(int volume, FuelUnit oldUnit, FuelUnit newUnit) {
        return (int) ((newUnit.unitToLitreRatio * (float) volume) / oldUnit.unitToLitreRatio);
    }
}
