package com.unit.converter.conversions;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.unit.converter.utils.SerializableCollectionsType;

import java.io.Serializable;
import java.util.List;

@DatabaseTable(tableName = "ConversionTable")
public class Conversion implements Serializable {

    static final int AREA = 0;
    static final int LENGTH = 1;
    static final int MASS = 2;
    static final int POWER = 3;
    static final int VOLUME = 4;
    static final int SPEED = 5;
    static final int PRESSURE = 6;
    static final int TIME = 7;
    public static final int TEMPERATURE = 8;
    static final int COOKING = 9;
    static final int ACCELERATION = 10;
    static final int FORCE = 11;
    static final int ENERGY = 12;
    static final int EL_CHARGE = 13;
    static final int EL_CURRENT = 14;
    static final int EL_RESISTANCE = 15;
    static final int EL_POTENTIAL = 16;
    static final int EL_CAPACITANCE = 17;
    static final int LUMINANCE = 18;
    static final int ILLUMINANCE = 18;
    static final int RADIATION = 19;
    static final int RADIOACTIVITY = 20;
    static final int DENSITY = 21;
    static final int DYNAMIC_VISCOSITY = 22;
    static final int KINEMATIC_VISCOSITY = 23;
    static final int MAGNETIC_FLUX = 24;
    static final int MAGNETIC_FLUX_DENSITY = 25;
    static final int MAGNETIC_FIELD_STRENGTH = 26;
    static final int TORQUE = 27;
    static final int ANGULAR_VELOCITY = 27;
    static final int ANGULAR_ACCELERATION = 28;
    static final int LUMINOUS_INTENSITY = 29;
    public static final int FUEL_CONSUMPTION = 30;

    public static final String CONVERSION_ID = "mConversionID";

    @DatabaseField(generatedId = true)
    private int mId;
    @DatabaseField(columnName = CONVERSION_ID)
    private int mConversionId;
    @DatabaseField
    private int mConversionName;
    @DatabaseField
    private int mConversionImage;
    @DatabaseField(persisterClass = SerializableCollectionsType.class)
    private List<Unit> units;

    public Conversion() {

    }

    public Conversion(int mConversionId, int mConversionName, int mConversionImage, List<Unit> units) {
        this.mConversionId = mConversionId;
        this.mConversionName = mConversionName;
        this.mConversionImage = mConversionImage;
        this.units = units;
    }

    public int getId() {
        return mConversionId;
    }

    public int getConversionName() {
        return mConversionName;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public Unit getUnitById(int id) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getId() == id) {
                return units.get(i);
            }
        }

        throw new IllegalArgumentException("Invalid unit id supplied");
    }

    public int getConversionImage() {
        return mConversionImage;
    }
}
