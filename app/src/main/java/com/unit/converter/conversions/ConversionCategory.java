package com.unit.converter.conversions;


import java.io.Serializable;
import java.util.List;

public class ConversionCategory implements Serializable{

    static final int DIMENSION = 0;
    static final int MECHANICS = 1;
    static final int ENERGY = 2;
    static final int MOTION = 3;
    static final int USEFUL = 4;
    static final int ELECTRICITY = 5;
    static final int PHOTOMETRY = 6;
    static final int RADIOACTIVITY = 7;
    static final int MAGNETISM = 8;
    static final int CHEMISTRY = 9;
    static final int ACOUSTICS = 10;

    private int mId;
    private int mConversionCategoryName;
    private int mConversionCategoryIcon;
    private List<Conversion> mConversionCategoryList;

    ConversionCategory(int mId, int mConversionCategoryName, int mConversionCategoryIcon, List<Conversion> mConversionCategoryList) {
        this.mId = mId;
        this.mConversionCategoryName = mConversionCategoryName;
        this.mConversionCategoryIcon = mConversionCategoryIcon;
        this.mConversionCategoryList = mConversionCategoryList;
    }

    public int getId() {
        return mId;
    }

    public int getConversionCategoryName() {
        return mConversionCategoryName;
    }

    public List<Conversion> getConversionCategoryList() {
        return mConversionCategoryList;
    }

    public int getConversionCategoryIcon() {
        return mConversionCategoryIcon;
    }
}
