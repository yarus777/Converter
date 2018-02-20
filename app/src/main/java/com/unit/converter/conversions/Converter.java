package com.unit.converter.conversions;


import com.unit.converter.interfaces.IConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Converter implements IConstants {

    public static double convertAll(double from, double to, double value) {
        double result = value;
        BigDecimal multiplier = new BigDecimal(from).multiply(new BigDecimal(to));
        BigDecimal bdResult = new BigDecimal(value).multiply(multiplier);
        return bdResult.doubleValue();
    }

    public static double convertFuel(double from, double to, double value, int from_n, int to_n) {
        double result = value;

        if (from_n != to_n && value != 0) {
            if (to_n == L_100K)   // Litres/100km
            {
                BigDecimal toBase = new BigDecimal(from);
                BigDecimal fromBase = new BigDecimal(to);
                BigDecimal resultBd = toBase.divide(new BigDecimal(value), RoundingMode.UP).multiply(fromBase);
                result = resultBd.doubleValue();
            }
            else if (from_n == L_100K)   // Litres/100km
            {
                BigDecimal fromBase = new BigDecimal(to);
                BigDecimal toBase = new BigDecimal(from);
                BigDecimal resultBd = fromBase.divide(new BigDecimal(value).multiply(toBase), RoundingMode.UP);
                result = resultBd.doubleValue();
            }
            else {
                BigDecimal multiplier = new BigDecimal(from).multiply(new BigDecimal(to));
                BigDecimal bdResult = new BigDecimal(value).multiply(multiplier);
                result = bdResult.doubleValue();
            }
        }
        return result;
    }


    public static double convertTemperature(int from, int to, double value) {
        double result = value;
        switch (from) {
            case (CELSIUS):
                result = toCelsius(to, value);
                break;
            case (FAHRENHEIT):
                result = toFahrenheit(to, value);
                break;
            case (KELVIN):
                result = toKelvin(to, value);
                break;
            case (RANKINE):
                result = toRankine(to, value);
                break;
            case (DELISLE):
                result = toDelisle(to, value);
                break;
            case (NEWTON):
                result = toNewton(to, value);
                break;
            case (REAUMUR):
                result = toReaumur(to, value);
                break;
            case (ROMER):
                result = toRomer(to, value);
                break;
        }
        return result;
    }

    private static double toCelsius(int id, double value) {
        double result = value;

        switch (id) {
            case (FAHRENHEIT):    // F to C
                result = (value - 32) * 5 / 9;
                break;

            case (KELVIN):    // K to C
                result = value - 273.15;
                break;

            case (RANKINE):    // R to C
                result = (value - 491.67) * 5 / 9;
                break;

            case (DELISLE):    // D to C
                result = 100 - value * 2 / 3;
                break;

            case (NEWTON):    //N to C
                result = value * 100 / 33;
                break;

            case (REAUMUR):    //Re to C
                result = value * 5 / 4;
                break;

            case (ROMER):    //Ro to C
                result = (value - 7.5) * 40 / 21;
                break;

        }

        return result;
    }

    private static double toFahrenheit(int id, double value) {
        double result = value;

        switch (id) {
            case (CELSIUS):    // C to F
                result = value * 9 / 5 + 32;
                break;

            case (KELVIN):    // K to F
                result = value * 9 / 5 - 459.67;
                break;

            case (RANKINE):    // R to F
                result = value - 459.67;
                break;

            case (DELISLE):    //D to F
                result = 212 - value * 6 / 5;
                break;

            case (NEWTON):    //N to F
                result = value * 60 / 11 + 32;
                break;

            case (REAUMUR):    //Re to F
                result = value * 9 / 4 + 32;
                break;

            case (ROMER):    //Ro to F
                result = (value - 7.5) * 24 / 7 + 32;
                break;

        }

        return result;
    }

    private static double toKelvin(int id, double value) {
        double result = value;

        switch (id) {
            case (CELSIUS):    // C to K
                result = value + 273.15;
                break;

            case (FAHRENHEIT):    // F to K
                result = (value + 459.67) * 5 / 9;
                break;

            case (RANKINE):    // R to K
                result = value * 5 / 9;
                break;

            case (DELISLE):    //D to K
                result = 373.15 - value * 2 / 3;
                break;

            case (NEWTON):    //N to K
                result = value * 100 / 33 + 273.15;
                break;

            case (REAUMUR):    //Re to K
                result = value * 5 / 4 + 273.15;
                break;

            case (ROMER):    //Ro to K
                result = (value - 7.5) * 40 / 21 + 273.15;
                break;

        }

        return result;
    }

    private static double toRankine(int id, double value) {
        double result = value;

        switch (id) {
            case (CELSIUS):    // C to R
                result = (value + 273.15) * 9 / 5;
                break;

            case (FAHRENHEIT):    // F to R
                result = value + 459.67;
                break;

            case (KELVIN):    // K to R
                result = value * 9 / 5;
                break;

            case (DELISLE):    //D to R
                result = 671.67 - value * 6 / 5;
                break;

            case (NEWTON):    //N to R
                result = value * 60 / 11 + 491.67;
                break;

            case (REAUMUR):    //Re to R
                result = value * 9 / 4 + 491.67;
                break;

            case (ROMER):    //Ro to R
                result = (value - 7.5) * 24 / 7 + 491.67;
                break;

        }

        return result;
    }

    private static double toDelisle(int id, double value) {
        double result = value;

        switch (id) {
            case (CELSIUS):    // C to D
                result = (100 - value) * 1.5;
                break;

            case (FAHRENHEIT):    // F to D
                result = (212 - value) * 5 / 6;
                break;

            case (KELVIN):    // K to D
                result = (373.15 - value) * 1.5;
                break;

            case (RANKINE):    // R to D
                result = (671.67 - value) * 5 / 6;
                break;

            case (NEWTON):    //N to D
                result = (33 - value) * 50 / 11;
                break;

            case (REAUMUR):    //Re to D
                result = (80 - value) * 1.875;
                break;

            case (ROMER):    //Ro to D
                result = (60 - value) * 20 / 7;
                break;

        }

        return result;
    }

    private static double toNewton(int id, double value) {
        double result = value;

        switch (id) {
            case (CELSIUS):    // C to N
                result = value * 33 / 100;
                break;

            case (FAHRENHEIT):    // F to N
                result = (value - 32) * 11 / 60;
                break;

            case (KELVIN):    // K to N
                result = (value - 273.15) * 33 / 100;
                break;

            case (RANKINE):    // R to N
                result = (value - 491.67) * 11 / 60;
                break;

            case (DELISLE):    //D to N
                result = 33 - value * 11 / 50;
                break;

            case (REAUMUR):    //Re to N
                result = value * 33 / 80;
                break;

            case (ROMER):    //Ro to N
                result = (value - 7.5) * 22 / 35;
                break;
        }

        return result;
    }

    private static double toReaumur(int id, double value) {
        double result = value;

        switch (id) {
            case (CELSIUS):    // C to Re
                result = value * 4 / 5;
                break;

            case (FAHRENHEIT):    // F to Re
                result = (value - 32) * 4 / 9;
                break;

            case (KELVIN):    // K to Re
                result = (value - 273.15) * 4 / 5;
                break;

            case (RANKINE):    // R to Re
                result = (value - 491.67) * 4 / 9;
                break;

            case (DELISLE):    //D to Re
                result = 80 - value * 8 / 15;
                break;

            case (NEWTON):    //N to Re
                result = value * 80 / 33;
                break;

            case (ROMER):    //Ro to Re
                result = (value - 7.5) * 32 / 21;
                break;
        }

        return result;
    }

    private static double toRomer(int id, double value) {
        double result = value;

        switch (id) {
            case (CELSIUS):    // C to Ro
                result = value * 21 / 40 + 7.5;
                break;

            case (FAHRENHEIT):    // F to Ro
                result = (value - 32) * 7 / 24 + 7.5;
                break;

            case (KELVIN):    // K to Ro
                result = (value - 273.15) * 21 / 40 + 7.5;
                break;

            case (RANKINE):    // R to Ro
                result = (value - 491.67) * 7 / 24 + 7.5;
                break;

            case (DELISLE):    //D to Ro
                result = 60 - value * 7 / 20;
                break;

            case (NEWTON):    //N to Ro
                result = value * 35 / 22 + 7.5;
                break;

            case (REAUMUR):    //Re to Ro
                result = value * 21 / 32 + 7.5;
                break;

        }

        return result;
    }
}
