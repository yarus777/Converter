package com.unit.converter.conversions;


import java.io.Serializable;

public class Unit implements Serializable {

    private int mId;
    private int mUnitName;
    private double mConversionToBase;
    private double mConversionFromBase;

    public Unit(int mId, int mUnitName, double mConversionToBase, double mConversionFromBase) {
        this.mId = mId;
        this.mUnitName = mUnitName;
        this.mConversionToBase = mConversionToBase;
        this.mConversionFromBase = mConversionFromBase;
    }

    public int getId() {
        return mId;
    }

    public double getConversionToBaseUnit() {
        return mConversionToBase;
    }

    public double getConversionFromBaseUnit() {
        return mConversionFromBase;
    }

    public int getUnitName() {
        return mUnitName;
    }
}
