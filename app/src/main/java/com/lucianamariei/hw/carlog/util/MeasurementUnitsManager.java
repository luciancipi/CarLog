package com.lucianamariei.hw.carlog.util;

/**
 * Created by Lucian Amariei on 26.02.2019.
 */
public class MeasurementUnitsManager {
    private static DistanceUnit distanceUnit;
    private static FuelUnit fuelUnit;

    public static DistanceUnit getDistanceUnit() {
        return distanceUnit;
    }

    public static FuelUnit getFuelUnit() {
        return fuelUnit;
    }

    public static void loadPreferredUnits(){
        //TODO
        fuelUnit = FuelUnit.LITRE;
        distanceUnit = DistanceUnit.KM;
    }

    public static void setPreferredUnits() {
        //TODO
    }

    public static int fuelRateToValue(int distance, float fuelRate) {
        if(fuelUnit == FuelUnit.LITRE) {
            //metric system (km * (L/100km))
            return (int)((float)distance * fuelRate / 100f);
        } else {
            //imperial system (MPG)
            return (int)((float)distance / fuelRate);
        }
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}
