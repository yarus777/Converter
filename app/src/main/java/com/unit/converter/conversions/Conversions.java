package com.unit.converter.conversions;


import com.unit.converter.R;
import com.unit.converter.interfaces.IConstants;

import java.util.ArrayList;
import java.util.List;


public class Conversions implements IConstants{

    private static Conversions mInstance = null;

    private List<ConversionCategory> mConversions = new ArrayList<>();

    public static Conversions getInstance() {

        if (mInstance == null) {
            mInstance = new Conversions();
        }
        return mInstance;
    }

    public ConversionCategory getById(int id) {
        return mConversions.get(id);
    }

    public List<ConversionCategory> getConversions() {
        return mConversions;
    }

    public int getSize() {
      return  mConversions.size();
    }

    private Conversions() {
        getDimensionConversions();
        getMechanicsConversions();
        getJobConversions();
        getMotionConversions();
        getUsefulConversions();
        getElectricityConversions();
        getPhotometryConversions();
        getRadioactivityConversions();
        getChemistryConversions();
        getMagnetismConversions();
    }

    private void getDimensionConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getLengthConversions());
        conversions.add(getAreaConversions());
        conversions.add(getVolumeConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.DIMENSION, R.string.dimension, R.drawable.ic_dimension, conversions));
    }


    private void getMechanicsConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getMassConversions());
        conversions.add(getPressureConversions());
        conversions.add(getForceConversions());
        conversions.add(getTorqueConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.MECHANICS, R.string.mechanics, R.drawable.ic_mechanics, conversions));
    }

    private void getJobConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getPowerConversions());
        conversions.add(getTemperatureConversions());
        conversions.add(getEnergyConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.ENERGY, R.string.energy, R.drawable.ic_energy_category, conversions));
    }

    private void getMotionConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getSpeedConversions());
        conversions.add(getAccelerationConversions());
        conversions.add(getAngularVelocity());
        conversions.add(getAngularAcceleration());
        mConversions.add(new ConversionCategory(ConversionCategory.MOTION, R.string.motion, R.drawable.ic_motion, conversions));
    }

    private void getUsefulConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getTimeConversions());
        conversions.add(getFuelConsumptionConversions());
        //conversions.add(getCookingConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.USEFUL, R.string.useful, R.drawable.ic_useful, conversions));
    }

    private void getElectricityConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getElectricalChargeConversions());
        conversions.add(getElectricalCurrentConversions());
        conversions.add(getElectricalResistanceConversions());
        conversions.add(getElectricalPotentialConversions());
        conversions.add(getElectricalCapacitanceConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.ELECTRICITY, R.string.electricity, R.drawable.ic_electricity, conversions));
    }

    private void getPhotometryConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getIlluminanceConversions());
        conversions.add(getLuminanceConversions());
        conversions.add(getLuminousIntensityConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.PHOTOMETRY, R.string.photometry, R.drawable.ic_photometry, conversions));
    }

    private void getRadioactivityConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getRadiationConversions());
        conversions.add(getSubRadioactivityConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.RADIOACTIVITY, R.string.radioactivity, R.drawable.ic_radioactivity, conversions));
    }

    private void getChemistryConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getDensityConversions());
        conversions.add(getDynamicViscosityConversions());
        conversions.add(getKinematicViscosityConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.CHEMISTRY, R.string.chemistry, R.drawable.ic_chemistry, conversions));
    }

    private void getMagnetismConversions() {
        List<Conversion> conversions = new ArrayList<>();
        conversions.add(getMagneticFluxConversions());
        conversions.add(getMagneticFluxDensityConversions());
        conversions.add(getMagneticFieldStrengthConversions());
        mConversions.add(new ConversionCategory(ConversionCategory.MAGNETISM, R.string.magnetism, R.drawable.ic_magnetism, conversions));
    }


    private Conversion getLengthConversions() {
        //Base unit: metre

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(KILOMETRE, R.string.kilometre, 1000.0, 0.001));
        units.add(new Unit(HECTOMETRE, R.string.hectometre, 100.0, 0.01));
        units.add(new Unit(DECAMETRE, R.string.decametre, 10.0, 0.1));
        units.add(new Unit(METRE, R.string.metre, 1.0, 1.0));
        units.add(new Unit(DECIMETRE, R.string.decimetre, 0.1, 10.0));
        units.add(new Unit(CENTIMETRE, R.string.centimetre, 0.01, 100.0));
        units.add(new Unit(MILLIMETRE, R.string.millimetre, 0.001, 1000.0));
        units.add(new Unit(MICROMETRE, R.string.micrometre, 0.000001, 1000000.0));
        units.add(new Unit(NANOMETRE, R.string.nanometre, 0.000000001, 1000000000.0));
        units.add(new Unit(ANGSTROM, R.string.angstrom, 0.0000000001, 10000000000.0));
        units.add(new Unit(PICOMETRE, R.string.picometre, 0.000000000001, 1000000000000.0));
        units.add(new Unit(MILE, R.string.mile, 1609.344, 0.00062137119223733397));
        units.add(new Unit(FURLONG, R.string.furlong, 201.168, 0.0049709695379));
        units.add(new Unit(CHAIN, R.string.chain, 20.1168, 0.04970969538));
        units.add(new Unit(ROD, R.string.rod, 5.0292, 0.1988387815));
        units.add(new Unit(YARD, R.string.yard, 0.9144, 1.09361329833770779));
        units.add(new Unit(FOOT, R.string.foot, 0.3048, 3.28083989501312336));
        units.add(new Unit(LINK, R.string.link, 0.201168, 4.9709695379));
        units.add(new Unit(HAND, R.string.hand, 0.1016, 9.842519685));
        units.add(new Unit(INCH, R.string.inch, 0.0254, 39.3700787401574803));
        units.add(new Unit(THOU, R.string.thou, 0.0000254, 39370.07874015748));
        units.add(new Unit(NAUTICAL_MILE, R.string.nautical_mile, 1852.0, 0.000539956803455723542));
        units.add(new Unit(CABLE, R.string.cable, 185.2, 0.005399568035));
        units.add(new Unit(FATHOM, R.string.fathom, 1.8288, 0.5468066492));
        units.add(new Unit(LAND_LEAGUE, R.string.land_league, 4828.032, 0.0002071237307));
        units.add(new Unit(NAUTICAL_LEAGUE, R.string.nautical_league, 55556.0, 0.0001799856012));
        units.add(new Unit(VERST, R.string.verst, 1066.8, 0.0009373828271));
        units.add(new Unit(MEZHEVAYA_VERST, R.string.mezhevaya_verst, 2133.6, 0.0004686914136));
        units.add(new Unit(KOSAYA_SAZHEN, R.string.kosaya_sazhen, 2.48, 0.4032258065));
        units.add(new Unit(MAKHOVAYA_SAZHEN, R.string.makhovaya_sazhen, 1.778, 0.5624296963));
        units.add(new Unit(SAZHEN, R.string.sazhen, 2.1336, 0.4686914136));
        units.add(new Unit(ARSHIN, R.string.arshin, 0.7112, 1.4060742407));
        units.add(new Unit(SPAN, R.string.span, 0.1778, 5.6242969629));
        units.add(new Unit(VERSHOK, R.string.vershok, 0.04445, 22.4971878515));
        units.add(new Unit(LINE_LEN, R.string.line_len, 0.00254, 393.7007874016));
        units.add(new Unit(DOT, R.string.dot, 0.000254, 3937.007874015748));
        return new Conversion(Conversion.LENGTH, R.string.length, R.drawable.ic_length, units);
    }

    private Conversion getAreaConversions() {
        //Base unit: square metre

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(SQ_KILOMETRES, R.string.sq_kilometre, 1000000.0, 0.000001));
        units.add(new Unit(SQ_METRES, R.string.sq_metre, 1.0, 1.0));
        units.add(new Unit(SQ_DECIMETRES, R.string.sq_decimetre, 0.01, 100.0));
        units.add(new Unit(SQ_CENTIMETRES, R.string.sq_centimetre, 0.0001, 10000.0));
        units.add(new Unit(SQ_MILLIMETRES, R.string.sq_millimetre, 0.000001, 1000000.0));
        units.add(new Unit(ARE, R.string.are, 100.0, 0.01));
        units.add(new Unit(DECARE, R.string.decare, 1000.0, 0.001));
        units.add(new Unit(HECTARE, R.string.hectare, 10000.0, 0.0001));
        units.add(new Unit(SQ_MILE, R.string.sq_mile, 2589988.110336, 0.000000386102158542445847));
        units.add(new Unit(SQ_YARD, R.string.sq_yard, 0.83612736, 1.19599004630108026));
        units.add(new Unit(SQ_FOOT, R.string.sq_foot, 0.09290304, 10.7639104167097223));
        units.add(new Unit(SQ_INCH, R.string.sq_inch, 0.00064516, 1550.00310000620001));
        units.add(new Unit(ROOD, R.string.rood, 1011.7141056, 0.0009884215259));
        units.add(new Unit(ACRE, R.string.acre, 4046.8564224, 0.000247105381467165342));
        units.add(new Unit(SQ_CHAIN, R.string.sq_chain, 404.68564224, 0.002471053815));
        units.add(new Unit(SQ_ROD, R.string.sq_rod, 25.29285264, 0.03953686103));
        units.add(new Unit(SQ_MIL, R.string.sq_mil, 0.00000000064516, 550003100.0062000124));
        return new Conversion(Conversion.AREA, R.string.area, R.drawable.ic_area, units);
    }

    private Conversion getVolumeConversions() {
        // Base unit - cubic metre

        List<Unit> units = new ArrayList<>();

        units.add(new Unit(HECTOLITRE, R.string.hectolitre, 0.1, 10.0));
        units.add(new Unit(DECALITRE, R.string.decalitre, 0.01, 100.0));
        units.add(new Unit(LITRE, R.string.litre, 0.001, 1000.0));
        units.add(new Unit(MILLILITRE, R.string.millilitre, 0.000001, 1000000.0));
        units.add(new Unit(BARREL, R.string.barrel, 0.119240471196, 8.38641436057614017079));
        units.add(new Unit(BARREL_UK, R.string.barrel_uk, 0.16365924, 6.11025689719688298687));
        units.add(new Unit(BARREL_OIL, R.string.oil_barrel, 0.1589872949, 6.2898107704));
        units.add(new Unit(BUSHEL, R.string.bushel, 0.03636872, 27.4961560374));
        units.add(new Unit(GALLON, R.string.gallon, 0.003785411784, 264.172052358148415));
        units.add(new Unit(DRY_GALLON_US, R.string.gallon_dry, 0.004404883771, 227.02074606721));
        units.add(new Unit(GALLON_UK, R.string.gallon_uk, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(QUART, R.string.quart, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(DRY_QUART_US, R.string.quart_dry, 0.0011012209, 1056.747331713));
        units.add(new Unit(QUART_UK, R.string.quart_uk, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(PINT, R.string.pint, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(PINT_UK, R.string.pint_uk, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(CUP, R.string.cup, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(GILL, R.string.gill, 0.0001420653125, 7039.01594557081));
        units.add(new Unit(FLUID_OUNCE, R.string.fluid_ounce, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(FLUID_OUNCE_UK, R.string.fluid_ounce_uk, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(TEASPOON, R.string.teaspoon, 0.000005, 200000));
        units.add(new Unit(TEASPOON_US, R.string.teaspoon_us, 0.00000492892159375, 202884.1362111));
        units.add(new Unit(TEASPOON_UK, R.string.teaspoon_uk, 0.000005919388020833, 168936.3826936994));
        units.add(new Unit(TABLESPOON, R.string.tablespoon, 0.000015, 66666.66666667));
        units.add(new Unit(TABLESPOON_US, R.string.tablespoon_us, 0.00001478676478125, 67628.04540369));
        units.add(new Unit(TABLESPOON_UK, R.string.tablespoon_uk, 0.0000177581640625, 56312.12756457));
        units.add(new Unit(DESSERTSPOON_US, R.string.dessertspoon_us, 0.0000098578431875, 101442.0681055));
        units.add(new Unit(DESSERTSPOON_UK, R.string.dessertspoon_uk, 0.00001183877604167, 84468.19134685));
        units.add(new Unit(DRAM, R.string.fluid_dram, 0.000003551632813, 281560.6378228324));
        units.add(new Unit(SCRUPLE, R.string.fluid_scruple, 0.000001183877604, 844681.9134684971));
        units.add(new Unit(DROP, R.string.drop, 0.00000009865646701, 10136182.9616219653));
        units.add(new Unit(DROP_IMP, R.string.drop_imperial, 0.00000005, 20000000.0));
        units.add(new Unit(MIN, R.string.min, 0.00000005919388021, 16893638.2693699421));
        units.add(new Unit(CUBIC_KM, R.string.cubic_km, 1000000000.0, 0.000000001));
        units.add(new Unit(CUBIC_M, R.string.cubic_m, 1.0, 1.0));
        units.add(new Unit(CUBIC_DM, R.string.cubic_m, 0.001, 1000.0));
        units.add(new Unit(CUBIC_CM, R.string.cubic_cm, 0.000001, 1000000.0));
        units.add(new Unit(CUBIC_MM, R.string.cubic_mm, 0.000000001, 1000000000.0));
        units.add(new Unit(CUBIC_MILE, R.string.cubic_mile, 4168181825.440579584, 0.0000000002399127586));
        units.add(new Unit(CUBIC_YARD, R.string.cubic_yard, 0.7645548692741148, 1.3079506));
        units.add(new Unit(CUBIC_FOOT, R.string.cubic_foot, 0.028316846592, 35.3146667214885903));
        units.add(new Unit(CUBIC_INCH, R.string.cubic_inch, 0.000016387064, 61023.744094732284));
        return new Conversion(Conversion.VOLUME, R.string.volume, R.drawable.ic_volume, units);
    }

//getMechanicsConversions

    private Conversion getMassConversions() {
        //Base unit - Kilograms

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(TONNE, R.string.tonne, 1000.0, 0.001));
        units.add(new Unit(CENTNER, R.string.centner, 100.0, 0.01));
        units.add(new Unit(KILOGRAM, R.string.kilogram, 1.0, 1.0));
        units.add(new Unit(GRAM, R.string.gram, 0.001, 1000.0));
        units.add(new Unit(MILLIGRAM, R.string.milligram, 0.000001, 1000000.0));
        units.add(new Unit(MICROGRAM, R.string.microgram, 0.000000001, 1000000000.0));
        units.add(new Unit(NANOGRAM, R.string.nanogram, 0.000000000001, 1000000000000.0));
        units.add(new Unit(LONG_TON, R.string.long_ton, 1016.0469088, 0.0009842065276110606282276));
        units.add(new Unit(SHORT_TON, R.string.short_ton, 907.18474, 0.0011023113109243879));
        units.add(new Unit(HUNDREDWEIGHT, R.string.hundredweigth, 50.80234544, 0.01968413055));
        units.add(new Unit(CENTAL, R.string.cental, 45.359237, 0.02204622622));
        units.add(new Unit(QUARTER, R.string.quarter, 12.70058636, 0.07873652221));
        units.add(new Unit(STONE, R.string.stone, 6.35029318, 0.15747304441777));
        units.add(new Unit(POUND, R.string.pound, 0.45359237, 2.20462262184877581));
        units.add(new Unit(POUND_TROY, R.string.pound_troy, 0.3732417216, 2.6792288807));
        units.add(new Unit(OUNCE, R.string.ounce, 0.028349523125, 35.27396194958041291568));
        units.add(new Unit(OUNCE_TROY, R.string.ounce_troy, 0.0311034768, 32.1507465686));
        units.add(new Unit(DRAM_WEIGHT, R.string.dram, 0.001771845195, 564.3833912928));
        units.add(new Unit(DRAM_TROY, R.string.dram_troy, 0.0038879346, 257.205972549));
        units.add(new Unit(PENNYWEIGHT, R.string.pennyweight, 0.00155517384, 643.01493137256));
        units.add(new Unit(CARAT, R.string.carat, 0.0002051965483, 4873.3763219815));
        units.add(new Unit(CARAT_METRIC, R.string.carat_metric, 0.0002, 5000.0));
        units.add(new Unit(GRAIN, R.string.grain, 0.00006479891, 15432.35835294143065061));
        units.add(new Unit(POOD, R.string.pood, 16.3804964, 0.06104821097));
        return new Conversion(Conversion.MASS, R.string.mass, R.drawable.ic_mass, units);
    }

    private Conversion getPressureConversions() {
        //Base unit - Pa

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(MEGAPASCAL, R.string.megapascal, 1000000.0, 0.000001));
        units.add(new Unit(KILOPASCAL, R.string.kilopascal, 1000.0, 0.001));
        units.add(new Unit(HECTOPASCAL, R.string.hectopascal, 100.0, 0.01));
        units.add(new Unit(PASCAL, R.string.pascal, 1.0, 1.0));
        units.add(new Unit(BAR, R.string.bar, 100000.0, 0.00001));
        units.add(new Unit(MMBAR, R.string.mmbar, 100.0, 0.01));
        units.add(new Unit(MCBAR, R.string.mcbar, 0.1, 10));
        units.add(new Unit(PSI, R.string.psi, 6894.757293168361, 0.000145037737730209222));
        units.add(new Unit(PSF, R.string.psf, 47.880258980335840277777777778, 0.020885434233150127968));
        units.add(new Unit(ATMOSPHERE, R.string.atmosphere, 101325.0, 0.0000098692326671601283));
        units.add(new Unit(TECHNICAL_ATMOSPHERE, R.string.technical_atmosphere, 98066.5, 0.0000101971621297792824257));
        units.add(new Unit(CMHG, R.string.cmhg, 1333.224, 0.000750061505));
        units.add(new Unit(MMHG, R.string.mmhg, 133.3224, 0.00750061505));
        units.add(new Unit(MCHG, R.string.mchg, 0.1333224, 7.5006150504));
        units.add(new Unit(IHG, R.string.ihg, 3386.38896, 0.0002952998051));
        units.add(new Unit(FHG, R.string.fhg, 40636.66752, 0.00002460831709));
        units.add(new Unit(MWC, R.string.mwc, 9806.65, 0.0001019716213));
        units.add(new Unit(CMWC, R.string.cmwc, 98.0665, 0.01019716213));
        units.add(new Unit(MMWC, R.string.mmwc, 9.80665, 0.1019716213));
        units.add(new Unit(IWC, R.string.iwc, 249.08891, 0.00401463076));
        units.add(new Unit(FWC, R.string.fwc, 2989.06692, 0.0003345525633));
        units.add(new Unit(TORR, R.string.torr, 133.3223684210526315789, 0.00750061682704169751));
        return new Conversion(Conversion.PRESSURE, R.string.pressure, R.drawable.ic_pressure, units);
    }

    private Conversion getForceConversions() {
        //Base unit - Newton

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(MEGANEWTON, R.string.meganewton, 1000000.0, 0.000001));
        units.add(new Unit(KILONEWTON, R.string.kilonewton, 1000.0, 0.001));
        units.add(new Unit(NEWTON_FORCE, R.string.newton_force, 1.0, 1.0));
        units.add(new Unit(MILLINEWTON, R.string.millinewton, 0.001, 1000.0));
        units.add(new Unit(MICRONEWTON, R.string.micronewton, 0.000001, 1000000.0));
        units.add(new Unit(TON_FORCE, R.string.ton_force, 8896.443230521, 0.0001124044715));
        units.add(new Unit(KIP_FORCE, R.string.kip_force, 4448.2216152605, 0.0002248089431));
        units.add(new Unit(POUND_FORCE, R.string.pound_force, 4.4482216153, 0.2248089431));
        units.add(new Unit(OUNCE_FORCE, R.string.ounce_force, 0.278013851, 3.5969430896));
        units.add(new Unit(TONNE_FORCE, R.string.tonne_force, 9806.65, 0.0001019716213));
        units.add(new Unit(KILOPOND, R.string.kilopond, 9.80655, 0.1019716213));
        units.add(new Unit(KILOGRAM_FORCE, R.string.kilogram_force, 9.80655, 0.1019716213));
        units.add(new Unit(GRAVE_FORCE, R.string.grave_force, 9.80655, 0.1019716213));
        units.add(new Unit(GRAM_FORCE, R.string.gram_force, 0.00980665, 101.9716212978));
        units.add(new Unit(MILLIGRAVE_FORCE, R.string.milligrave_force, 0.00980665, 101.9716212978));
        units.add(new Unit(GRAVET_FORCE, R.string.gravet_force, 0.00980665, 101.9716212978));
        units.add(new Unit(MILLIGRAM_FORCE, R.string.milligram_force, 0.00000980665, 101971.6212977928));
        units.add(new Unit(STHENE, R.string.sthene, 100000.0, 0.000001));
        units.add(new Unit(POUNDAL, R.string.poundal, 0.1382549544, 7.2330138512));
        units.add(new Unit(DYNE, R.string.dyne, 0.00001, 100000.0));
        return new Conversion(Conversion.FORCE, R.string.force, R.drawable.ic_force, units);
    }

    private Conversion getTorqueConversions() {
        //Base unit - Newton metre

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(NEWTON_METRE, R.string.newton_metre, 1.0, 1.0));
        units.add(new Unit(DECANEWTON_METRE, R.string.decanewton_metre, 10.0, 0.1));
        units.add(new Unit(KILONEWTON_METRE, R.string.kilonewton_metre, 1000.0, 0.001));
        units.add(new Unit(NEWTON_CENTIMETRE, R.string.newton_centimetre, 0.001, 100.0));
        units.add(new Unit(M_KGF, R.string.m_kgf, 9.8067, 0.102));
        units.add(new Unit(M_GF, R.string.m_gf, 0.009807, 101.9716));
        units.add(new Unit(CM_KGF, R.string.cm_kgf, 0.09807, 10.1972));
        units.add(new Unit(CM_GF, R.string.cm_gf, 0.00009807, 10197.1621));
        units.add(new Unit(FT_LB_F, R.string.ft_lb_f, 1.3558, 0.7376));
        units.add(new Unit(FT_PDL, R.string.ft_pdl, 0.04214, 23.7304));
        units.add(new Unit(IN_LB_F, R.string.in_lb_f, 0.113, 8.8507));
        return new Conversion(Conversion.TORQUE, R.string.torque, R.drawable.ic_torque, units);
    }


    private Conversion getPowerConversions() {
        //Base unit - Watt

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(MEGAWATT, R.string.megawatt, 1000000.0, 0.000001));
        units.add(new Unit(KILOWATT, R.string.kilowatt, 1000.0, 0.001));
        units.add(new Unit(WATT, R.string.watt, 1.0, 1.0));
        units.add(new Unit(MEGACALORIE_S, R.string.megacalorie_s, 4186800.0, 0.0000002388458966));
        units.add(new Unit(KILOCALORIE_S, R.string.kilocalorie_s, 4186.8, 0.0002388458966));
        units.add(new Unit(CALORIE_S, R.string.calorie_s, 4.1868, 0.23884589662749594));
        units.add(new Unit(MEGACALORIE_H, R.string.megacalorie_h, 1163.0, 0.0008598452279));
        units.add(new Unit(KILOCALORIE_H, R.string.kilocalorie_h, 1.163, 0.8598452279));
        units.add(new Unit(CALORIE_H, R.string.calorie_h, 0.001163, 859.845227859));
        units.add(new Unit(BHP, R.string.bhp, 9810.657, 0.0001019299727));
        units.add(new Unit(EHP, R.string.ehp, 746.0, 0.001340482574));
        units.add(new Unit(HP, R.string.hp, 735.49875, 0.00135962161730390432));
        units.add(new Unit(HP_UK, R.string.hp_uk, 745.69987158227022, 0.00134102208959502793));
        units.add(new Unit(JOULE_S, R.string.joule_s, 1.0, 1.0));
        units.add(new Unit(ERG_S, R.string.erg_s, 0.0000001, 10000000.0));
        units.add(new Unit(BTU_S, R.string.btu_s, 1055.05585262, 0.0009478171203133172));
        units.add(new Unit(BTU_M, R.string.btu_m, 17.5842642103, 0.05686902722));
        units.add(new Unit(BTU_H, R.string.btu_h, 0.2930710702, 3.4121416331));
        return new Conversion(Conversion.POWER, R.string.power, R.drawable.ic_power, units);
    }

    private Conversion getTemperatureConversions() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(CELSIUS, R.string.celsius, 0.0, 0.0));
        units.add(new Unit(FAHRENHEIT, R.string.fahrenheit, 0.0, 0.0));
        units.add(new Unit(KELVIN, R.string.kelvin, 0.0, 0.0));
        units.add(new Unit(RANKINE, R.string.rankine, 0.0, 0.0));
        units.add(new Unit(DELISLE, R.string.delisle, 0.0, 0.0));
        units.add(new Unit(NEWTON, R.string.newton, 0.0, 0.0));
        units.add(new Unit(REAUMUR, R.string.reaumur, 0.0, 0.0));
        units.add(new Unit(ROMER, R.string.romer, 0.0, 0.0));
        return new Conversion(Conversion.TEMPERATURE, R.string.temperature, R.drawable.ic_temperature, units);
    }

    private Conversion getEnergyConversions() {
        //Base unit - Joule

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(JOULE, R.string.joule, 1.0, 1.0));
        units.add(new Unit(KILOJOULE, R.string.kilojoule, 1000.0, 0.001));
        units.add(new Unit(MEGAJOULE, R.string.megajoule, 1000000.0, 0.000001));
        units.add(new Unit(GIGAJOULE, R.string.gigajoule, 1000000000.0, 0.000000001));
        units.add(new Unit(CALORIE, R.string.calorie, 4.1868, 0.2388458966));
        units.add(new Unit(KILOCALORIE, R.string.kilocalorie, 4186.8, 0.0002388458966));
        units.add(new Unit(MEGACALORIE, R.string.megacalorie, 4186800.0, 0.0000002388458966));
        units.add(new Unit(GIGACALORIE, R.string.gigacalorie, 4186800000.0, 0.0000000002388458966));
        units.add(new Unit(WATT_H, R.string.watt_h, 3600.0, 0.0002777777778));
        units.add(new Unit(KILOWATT_H, R.string.kilowatt_h, 3600000.0, 0.0000002777777778));
        units.add(new Unit(MEGAWATT_H, R.string.megawatt_h, 3600000000.0, 0.0000000002777777778));
        units.add(new Unit(WATT_S, R.string.watt_s, 1.0, 1.0));
        units.add(new Unit(ERG, R.string.erg, 0.0000001, 10000000.0));
        units.add(new Unit(QUAD, R.string.quad, 1055055852620000000.0, 0.0000000000000000009478171203));
        units.add(new Unit(THERM, R.string.therm, 105505585.262, 0.00000000947817203));
        units.add(new Unit(THERMIE, R.string.thermie, 4186800.0, 0.0000002388458966));
        units.add(new Unit(BTU_ISO, R.string.btu_iso, 1054.5, 0.0009483167378));
        units.add(new Unit(BTU_IT, R.string.btu_it, 1055.05585262, 0.0009478171203));
        units.add(new Unit(FT_LBF, R.string.ft_lbf, 1.3558179483, 0.7375621493));
        units.add(new Unit(BOE, R.string.boe, 6119348090.0, 0.0000000001634161001));
        units.add(new Unit(T_TNT, R.string.t_tnt, 4612070450.0, 0.0000000002168223601));
        units.add(new Unit(K_TNT, R.string.k_tnt, 4612070.45, 0.0000002168223601));
        units.add(new Unit(TON_TNT, R.string.ton_tnt, 4183999932.044933, 0.00000000023900574));
        units.add(new Unit(ELECTRONVOLT, R.string.electronvolt, 0.00000000000000000016, 6250000000000000000.0));
        return new Conversion(Conversion.ENERGY, R.string.energy, R.drawable.ic_energy, units);
    }

    private Conversion getSpeedConversions() {
        //base unit - m/s

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(KM_HR, R.string.km_h, 0.27777777777778, 3.6));
        units.add(new Unit(KM_MIN, R.string.km_m, 16.6666666667, 0.06));
        units.add(new Unit(KM_SEC, R.string.km_s, 1000.0, 0.001));
        units.add(new Unit(M_HR, R.string.m_h, 0.0002777777778, 3600.0));
        units.add(new Unit(M_MIN, R.string.m_m, 0.01666666667, 60.0));
        units.add(new Unit(M_SEC, R.string.m_s, 1.0, 1.0));
        units.add(new Unit(CM_HR, R.string.cm_h, 0.000002777777778, 360000.0));
        units.add(new Unit(CM_MIN, R.string.cm_m, 0.0001666666667, 6000.0));
        units.add(new Unit(CM_SEC, R.string.cm_s, 0.01, 100.0));
        units.add(new Unit(MM_HR, R.string.mm_h, 0.0000002777777778, 3600000.0));
        units.add(new Unit(MM_MIN, R.string.mm_m, 0.00001666666667, 60000.0));
        units.add(new Unit(MM_SEC, R.string.mm_s, 0.001, 1000.0));
        units.add(new Unit(MPH, R.string.mph, 0.44704, 2.2369362920544));
        units.add(new Unit(MPMIN, R.string.mpm, 26.8224, 0.03728227153));
        units.add(new Unit(MPSEC, R.string.mps, 1609.344, 0.0006213711922));
        units.add(new Unit(FPH, R.string.fph, 0.00008466666667, 11811.02362204724));
        units.add(new Unit(FPMIN, R.string.fpm, 0.00508, 196.8503937008));
        units.add(new Unit(FPSEC, R.string.fps, 0.3048, 3.2808398950131));
        units.add(new Unit(IPH, R.string.iph, 0.000007055555556, 141732.2834645669));
        units.add(new Unit(IPMIN, R.string.ipm, 0.0004233333333, 2362.2047244094));
        units.add(new Unit(IPSEC, R.string.ips, 0.0254, 39.3700787402));
        units.add(new Unit(KNOT, R.string.knot, 0.51444444444444, 1.9438444924406));
        return new Conversion(Conversion.SPEED, R.string.speed, R.drawable.ic_speed, units);
    }

    private Conversion getAccelerationConversions() {
        //base unit - m/s2

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(ST_GRAVITY, R.string.standart_gravity, 9.80665, 0.1019716213));
        units.add(new Unit(GALILEO, R.string.galileo, 0.01, 100.0));
        units.add(new Unit(KM_HS, R.string.km_hs, 0.2777777778, 3.6));
        units.add(new Unit(KM_MS, R.string.km_ms, 16.6666666667, 0.06));
        units.add(new Unit(KM_SQS, R.string.km_sqs, 1000.0, 0.001));
        units.add(new Unit(M_HS, R.string.m_hs, 0.0002777777778, 3600.0));
        units.add(new Unit(M_MS, R.string.m_ms, 0.01666666667, 60.0));
        units.add(new Unit(M_SQS, R.string.m_sqs, 1.0, 1.0));
        units.add(new Unit(MP_HS, R.string.mph_s, 0.44704, 2.2369362921));
        units.add(new Unit(MP_MS, R.string.mpm_s, 26.8224, 0.02728227153));
        units.add(new Unit(MP_SQS, R.string.mp_sqs, 1609.344, 0.0006213711922));
        units.add(new Unit(FP_HS, R.string.fph_s, 0.00008466666667, 11811.02362204724));
        units.add(new Unit(FP_MS, R.string.fpm_s, 0.00508, 196.8503937008));
        units.add(new Unit(FP_SQS, R.string.fp_sqs, 0.3048, 3.280839895));
        units.add(new Unit(IP_HS, R.string.iph_s, 0.000007055555556, 141732.2834645669));
        units.add(new Unit(IP_MS, R.string.ipm_s, 0.0004233333333, 2362.2047244094));
        units.add(new Unit(IP_SQS, R.string.ip_sqs, 0.0254, 39.3700787402));
        units.add(new Unit(KN_S, R.string.kn_s, 0.5144444444, 1.9438444924));
        return new Conversion(Conversion.ACCELERATION, R.string.acceleration, R.drawable.ic_acceleration, units);
    }

    private Conversion getAngularVelocity() {
        //base unit - rad/sec

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(REV_WEEK, R.string.rev_week, 0.00001039, 96256.9096));
        units.add(new Unit(REV_DAY, R.string.rev_day, 0.00007272, 13750.9871));
        units.add(new Unit(REV_H, R.string.rev_h, 0.001745, 572.9578));
        units.add(new Unit(REV_MIN, R.string.rev_min, 0.1047, 9.5493));
        units.add(new Unit(REV_SEC, R.string.rev_sec, 6.2832, 0.1592));
        units.add(new Unit(RAD_WEEK, R.string.rad_week, 0.000001653, 604800.0));
        units.add(new Unit(RAD_DAY, R.string.rad_day, 0.00001157, 86400.0));
        units.add(new Unit(RAD_H, R.string.rad_h, 0.0002778, 3600.0));
        units.add(new Unit(RAD_MIN, R.string.rad_min, 0.01667, 60.0));
        units.add(new Unit(RAD_SEC, R.string.rad_sec, 1.0, 1.0));
        units.add(new Unit(DEG_WEEK, R.string.deg_week, 0.00000002886, 34652487.4495));
        units.add(new Unit(DEG_DAY, R.string.deg_day, 0.000000202, 4950355.3499));
        units.add(new Unit(DEG_H, R.string.deg_h, 0.000004848, 206264.8062));
        units.add(new Unit(DEG_MIN, R.string.deg_min, 0.0002909, 3437.7468));
        units.add(new Unit(DEG_SEC, R.string.deg_sec, 0.01745, 57.2958));
        return new Conversion(Conversion.ANGULAR_VELOCITY, R.string.angular_velocity, R.drawable.ic_angular_velocity, units);
    }

    private Conversion getAngularAcceleration() {
        //base unit - rad/sec2

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(RAD_SQH, R.string.rad_sqh, 0.000000077160493827, 12960000.0));
        units.add(new Unit(RAD_SQMIN, R.string.rad_sqmin, 0.00027777777778, 3600.0));
        units.add(new Unit(RAD_SQSEC, R.string.rad_sqsec, 1.0, 1.0));
        units.add(new Unit(REV_SQH, R.string.rev_sqh, 0.00000048481368105, 2062648.06272));
        units.add(new Unit(REV_SQMIN, R.string.rev_sqmin, 0.00175, 572.9578));
        units.add(new Unit(REV_SQSEC, R.string.rev_sqsec, 6.28319, 0.15915));
        units.add(new Unit(REV_MINSEC, R.string.rev_minsec, 0.10472, 9.5493));
        units.add(new Unit(REV_HMIN, R.string.rev_hmin, 0.000029088820863, 34377.46771));
        return new Conversion(Conversion.ANGULAR_ACCELERATION, R.string.angular_acceleration, R.drawable.ic_angular_acceleration, units);
    }

    private Conversion getTimeConversions() {
        //Base unit - seconds

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(YEAR, R.string.year, 31536000.0, 0.0000000317097919837645865));
        units.add(new Unit(MONTH, R.string.month, 2628000.0, 0.0000003805175));
        units.add(new Unit(WEEK, R.string.week, 604800.0, 0.00000165343915343915344));
        units.add(new Unit(DAY, R.string.day, 86400.0, 0.0000115740740740740741));
        units.add(new Unit(HOUR, R.string.hour, 3600.0, 0.000277777777777777778));
        units.add(new Unit(MINUTE, R.string.minute, 60.0, 0.0166666666666666667));
        units.add(new Unit(SECOND, R.string.second, 1.0, 1.0));
        units.add(new Unit(MILLISECOND, R.string.millisecond, 0.001, 1000.0));
        units.add(new Unit(NANOSECOND, R.string.nanosecond, 0.000000001, 1000000000.0));
        return new Conversion(Conversion.TIME, R.string.time, R.drawable.ic_time, units);
    }

    private Conversion getFuelConsumptionConversions() {
        //Base unit - Miles per Gallon UK

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(MPG_US, R.string.mpg_us, 1.2009709237288135, 0.8326596258427036));
        units.add(new Unit(MPG_UK, R.string.mpg_uk, 1.0, 1.0));
        units.add(new Unit(L_100K, R.string.l_100k, 282.4858757062147, 282.4858757062147));
        units.add(new Unit(KM_L, R.string.km_l, 2.8248587571, 0.354));
        units.add(new Unit(MILES_L, R.string.miles_l, 4.545197740112995, 0.22001243008079552));
        return new Conversion(Conversion.FUEL_CONSUMPTION, R.string.fuel_consumption, R.drawable.ic_fuel, units);
    }

    private Conversion getCookingConversions() {
        // Base unit - cubic metre
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(TEASPOON, R.string.teaspoon, 0.0000049289215938, 202884.136211058));
        units.add(new Unit(TABLESPOON, R.string.tablespoon, 0.0000147867647812, 67628.045403686));
        units.add(new Unit(CUP, R.string.cup, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(FLUID_OUNCE, R.string.fluid_ounce, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(FLUID_OUNCE_UK, R.string.fluid_ounce_uk, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(PINT, R.string.pint, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(PINT_UK, R.string.pint_uk, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(QUART, R.string.quart, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(QUART_UK, R.string.quart_uk, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(GALLON, R.string.gallon, 0.003785411784, 264.172052358148415));
        units.add(new Unit(GALLON_UK, R.string.gallon_uk, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(MILLILITRE, R.string.millilitre, 0.000001, 1000000.0));
        units.add(new Unit(LITRE, R.string.litre, 0.001, 1000.0));
        return new Conversion(Conversion.COOKING, R.string.cooking, R.drawable.ic_cooking, units);
    }

    private Conversion getElectricalChargeConversions() {
        // Base unit - Coulomb

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(COULOMB, R.string.coulomb, 1.0, 1.0));
        units.add(new Unit(MILLICOULOMB, R.string.millicoulomb, 0.001, 1000.0));
        units.add(new Unit(MICROCOULOMB, R.string.microcoulomb, 0.000001, 1000000.0));
        units.add(new Unit(NANOCOULOMB, R.string.nanocoulomb, 0.000000001, 1000000000.0));
        units.add(new Unit(KILOCOULOMB, R.string.kilocoulomb, 1000.0, 0.001));
        units.add(new Unit(MEGACOULOMB, R.string.megacoulomb, 1000000.0, 0.000001));
        units.add(new Unit(ABCOULOMB, R.string.abcoulomb, 10.0, 0.1));
        units.add(new Unit(MILLIAMPERE_H, R.string.milliampere_h, 3.6, 0.2777777778));
        units.add(new Unit(MILLIAMPERE_M, R.string.milliampere_m, 0.06, 16.6666666667));
        units.add(new Unit(MILLIAMPERE_S, R.string.milliampere_s, 0.001, 1000.0));
        units.add(new Unit(AMPERE_H, R.string.ampere_h, 3600.0, 0.0002777777778));
        units.add(new Unit(AMPERE_M, R.string.ampere_m, 60.0, 0.0166666666667));
        units.add(new Unit(AMPERE_S, R.string.ampere_s, 1.0, 1.0));
        units.add(new Unit(FARADAY, R.string.faraday, 96485.3399, 0.00001036426882));
        return new Conversion(Conversion.EL_CHARGE, R.string.el_charge, R.drawable.ic_charge, units);
    }

    private Conversion getElectricalCurrentConversions() {
        // Base unit - Ampere

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(AMPERE, R.string.ampere, 1.0, 1.0));
        units.add(new Unit(MILLIAMPERE, R.string.milliampere, 0.001, 1000.0));
        units.add(new Unit(MICROAMPERE, R.string.microampere, 0.000001, 1000000.0));
        units.add(new Unit(NANOAMPERE, R.string.nanoampere, 0.000000001, 1000000000.0));
        units.add(new Unit(KILOAMPERE, R.string.kiloampere, 1000.0, 0.001));
        units.add(new Unit(MEGAAMPERE, R.string.megaampere, 1000000.0, 0.000001));
        units.add(new Unit(GIGAAMPERE, R.string.gigaampere, 1000000000.0, 0.000000001));
        units.add(new Unit(ABAMPERE, R.string.abampere, 10.0, 0.1));
        units.add(new Unit(COULOMB_S, R.string.coulomb_s, 1.0, 1.0));
        return new Conversion(Conversion.EL_CURRENT, R.string.el_current, R.drawable.ic_current, units);
    }

    private Conversion getElectricalResistanceConversions() {
        // Base unit - Ohm

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(OHM, R.string.ohm, 1.0, 1.0));
        units.add(new Unit(MILLIOHM, R.string.milliohm, 0.001, 1000.0));
        units.add(new Unit(MICROOHM, R.string.microohm, 0.000001, 1000000.0));
        units.add(new Unit(NANOOHM, R.string.nanoohm, 0.000000001, 1000000000.0));
        units.add(new Unit(KILOOHM, R.string.kiloohm, 1000.0, 0.001));
        units.add(new Unit(MEGAOHM, R.string.megaohm, 1000000.0, 0.000001));
        units.add(new Unit(GIGAOHM, R.string.gigaohm, 1000000000.0, 0.000000001));
        units.add(new Unit(ABOHM, R.string.abohm, 0.000000001, 1000000000.0));
        units.add(new Unit(VOLT_AMPERE, R.string.volt_ampere, 1.0, 1.0));
        return new Conversion(Conversion.EL_RESISTANCE, R.string.el_resistance, R.drawable.ic_resistance, units);
    }

    private Conversion getElectricalPotentialConversions() {
        // Base unit - Volt

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(VOLT, R.string.volt, 1.0, 1.0));
        units.add(new Unit(MILLIVOLT, R.string.millivolt, 0.001, 1000.0));
        units.add(new Unit(MICROVOLT, R.string.microvolt, 0.000001, 1000000.0));
        units.add(new Unit(NANOVOLT, R.string.nanovolt, 0.000000001, 1000000000.0));
        units.add(new Unit(KILOVOLT, R.string.kilovolt, 1000.0, 0.001));
        units.add(new Unit(MEGAVOLT, R.string.megavolt, 1000000.0, 0.000001));
        units.add(new Unit(GIGAVOLT, R.string.gigavolt, 1000000000.0, 0.000000001));
        units.add(new Unit(ABVOLT, R.string.abvolt, 0.00000001, 100000000.0));
        units.add(new Unit(STATVOLT, R.string.statvolt, 299.792458, 0.003335640952));
        units.add(new Unit(WATT_AMPERE, R.string.watt_ampere, 1.0, 1.0));
        return new Conversion(Conversion.EL_POTENTIAL, R.string.el_potential, R.drawable.ic_potential, units);
    }

    private Conversion getElectricalCapacitanceConversions() {
        // Base unit - Farad

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(FARAD, R.string.farad, 1.0, 1.0));
        units.add(new Unit(MILLIFARAD, R.string.millifarad, 0.001, 1000.0));
        units.add(new Unit(MICROFARAD, R.string.microfarad, 0.000001, 1000000.0));
        units.add(new Unit(NANOFARAD, R.string.nanofarad, 0.000000001, 1000000000.0));
        units.add(new Unit(PICOFARAD, R.string.picofarad, 0.000000000001, 1000000000000.0));
        units.add(new Unit(DECAFARAD, R.string.decafarad, 10.0, 0.1));
        units.add(new Unit(HECTOFARAD, R.string.hectofarad, 100.0, 0.01));
        units.add(new Unit(KILOFARAD, R.string.kilofarad, 1000.0, 0.001));
        units.add(new Unit(MEGAFARAD, R.string.megafarad, 1000000.0, 0.000001));
        units.add(new Unit(GIGAFARAD, R.string.gigafarad, 1000000000.0, 0.000000001));
        units.add(new Unit(ABFARAD, R.string.abfarad, 1000000000.0, 0.000000001));
        units.add(new Unit(STATFARAD, R.string.statfarad, 0.00000000000111265006, 898755178736.81764));
        units.add(new Unit(COULOMB_VOLT, R.string.coulomb_volt, 1.0, 1.0));
        return new Conversion(Conversion.EL_CAPACITANCE, R.string.el_capacitance, R.drawable.ic_capacitance, units);
    }

    private Conversion getLuminanceConversions() {
        // Base unit - Candela per square metre(nit)

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(CANDELA_SQM, R.string.candela_sqm, 1.0, 1.0));
        units.add(new Unit(CANDELA_SQCM, R.string.candela_sqcm, 10000.0, 0.0001));
        units.add(new Unit(CANDELA_SQFT, R.string.candela_sqf, 10.763910417, 0.09290304));
        units.add(new Unit(CANDELA_SQIN, R.string.candela_sqi, 1550.003100048, 0.00064516));
        units.add(new Unit(KILOCANDELA_SQM, R.string.kilocandela_sqm, 1000.0, 0.001));
        units.add(new Unit(LUMEN_SQM_STER, R.string.lumen_sqm_ster, 1.0, 1.0));
        units.add(new Unit(LUMEN_SQCM_STER, R.string.lumen_sqcm_ster, 10000.0, 0.0001));
        units.add(new Unit(LUMEN_SQFT_STER, R.string.lumen_sqft_ster, 10.763910417, 0.09290304));
        units.add(new Unit(NIT, R.string.nit, 1.0, 1.0));
        units.add(new Unit(MILLINIT, R.string.millinit, 0.001, 1000.0));
        units.add(new Unit(LAMBERT, R.string.lambert, 318.3098861838, 0.003141592654));
        units.add(new Unit(MILLILAMBERT, R.string.millilambert, 3.183098862, 0.3141592654));
        units.add(new Unit(FT_LAMBERT, R.string.foot_lambert, 0.3183098862, 3.1415926536));
        units.add(new Unit(STILB, R.string.stilb, 10000.0, 0.0001));
        units.add(new Unit(APOSTILB, R.string.apostilb, 0.31831, 3.14159));
        units.add(new Unit(BLONDEL, R.string.blondel, 0.31831, 3.14159));
        units.add(new Unit(BRIL, R.string.bril, 0.000000031830988618, 31415926.5359));
        units.add(new Unit(SKOT, R.string.skot, 0.00031830988618, 3141.59265));
        return new Conversion(Conversion.LUMINANCE, R.string.luminance, R.drawable.ic_luminance, units);
    }

    private Conversion getIlluminanceConversions() {
        // Base unit - Lux

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(LUMEN_SQM, R.string.lumen_sqm, 1.0, 1.0));
        units.add(new Unit(LUMEN_SQCM, R.string.lumen_sqcm, 10000.0, 0.0001));
        units.add(new Unit(LUMEN_SQFT, R.string.lumen_sqf, 10.763910417, 0.09290304));
        units.add(new Unit(LUMEN_SQIN, R.string.lumen_sqi, 1550.003100048, 0.00064516));
        units.add(new Unit(LUX, R.string.lux, 1.0, 1.0));
        units.add(new Unit(PHOT, R.string.phot, 10000.0, 0.0001));
        units.add(new Unit(NOX, R.string.nox, 0.001, 1000.0));
        units.add(new Unit(M_CANDELE, R.string.m_candel, 1.0, 1.0));
        units.add(new Unit(CM_CANDELE, R.string.cm_candel, 10000.0, 0.0001));
        units.add(new Unit(FT_CANDELE, R.string.ft_candel, 10.763910417, 0.09290304));
        units.add(new Unit(CANDELE_STER_SQM, R.string.candela_ster_sqm, 1.0, 1.0));
        return new Conversion(Conversion.ILLUMINANCE, R.string.illuminance, R.drawable.ic_illuminance, units);
    }

    private Conversion getLuminousIntensityConversions() {
        // Base unit - Candela

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(CANDELA, R.string.candela, 1.0, 1.0));
        units.add(new Unit(CANDLE_GER, R.string.candle_ger, 1.05263, 0.95));
        units.add(new Unit(CANDLE_UK, R.string.candle_uk, 1.04167, 0.96));
        units.add(new Unit(DEC_CANDLE, R.string.dec_candle, 1.0, 1.0));
        units.add(new Unit(CANDLE_PEN, R.string.candle_pen, 1.0, 1.0));
        units.add(new Unit(PEN_CANDLE, R.string.pen_candle, 10.0, 0.1));
        units.add(new Unit(HEFNER_CANDLE, R.string.hefner_candle, 0.9, 1.11111));
        units.add(new Unit(BOUGIE_CANDLE, R.string.bougie_dec, 1.0, 1.0));
        units.add(new Unit(LUMEN_STER, R.string.lumen_ster, 1.0, 1.0));
        units.add(new Unit(INTER_CANDLE, R.string.inter_candle, 1.0, 1.0));
        units.add(new Unit(CARCEL_UNIT, R.string.carcel_unit, 9.61, 0.10406));
        return new Conversion(Conversion.LUMINOUS_INTENSITY, R.string.luminous_intensity, R.drawable.ic_luminous_intensity, units);
    }

    private Conversion getRadiationConversions() {
        // Base unit - Gray or Sievert

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(GRAY, R.string.gray, 1.0, 1.0));
        units.add(new Unit(MEGAGRAY, R.string.megagray, 1000000.0, 0.000001));
        units.add(new Unit(KILOGRAY, R.string.kilogray, 1000.0, 0.001));
        units.add(new Unit(HECTOGRAY, R.string.hectogray, 100.0, 0.01));
        units.add(new Unit(DECAGRAY, R.string.decagray, 10.0, 0.1));
        units.add(new Unit(DECIGRAY, R.string.decigray, 0.1, 10.0));
        units.add(new Unit(CENTIGRAY, R.string.centigray, 0.01, 100.0));
        units.add(new Unit(MILLIGRAY, R.string.milligray, 0.001, 1000.0));
        units.add(new Unit(MICROGRAY, R.string.microgray, 0.000001, 1000000.0));
        units.add(new Unit(RAD, R.string.rad, 0.01, 100.0));
        units.add(new Unit(MILLIRAD, R.string.millirad, 0.00001, 100000.0));
        units.add(new Unit(SIEVERT, R.string.sievert, 1.0, 1.0));
        units.add(new Unit(MILLISIEVERT, R.string.millisievert, 0.001, 1000.0));
        units.add(new Unit(MICROSIEVERT, R.string.microsievert, 0.000001, 1000000.0));
        units.add(new Unit(NANOSIEVERT, R.string.nanosievert, 0.000000001, 1000000000.0));
        units.add(new Unit(ROENTGEN, R.string.roentgen, 0.008695652174, 115.0));
        units.add(new Unit(MILLIROENTGEN, R.string.milliroentgen, 0.000008695652174, 115000.0));
        units.add(new Unit(MICROROENTGEN, R.string.microroentgen, 0.000000008695652174, 115000000.0));
        units.add(new Unit(RONTGEN_EQ_MAN, R.string.röntgen_equivalent_man, 0.01, 100.0));
        return new Conversion(Conversion.RADIATION, R.string.radiation, R.drawable.ic_radiation, units);
    }

    private Conversion getSubRadioactivityConversions() {
        // Base unit - Becquerel

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(BECQUEREL, R.string.becquerel, 1.0, 1.0));
        units.add(new Unit(GIGABECQUEREL, R.string.gigabecquerel, 1000000000.0, 0.000000001));
        units.add(new Unit(MEGABECQUEREL, R.string.megabecquerel, 1000000.0, 0.000001));
        units.add(new Unit(KILOBECQUEREL, R.string.kilobecquerel, 1000.0, 0.001));
        units.add(new Unit(CURIE, R.string.curie, 37000000000.0, 0.00000000002702702703));
        units.add(new Unit(MILLICURIE, R.string.millicurie, 37000000.0, 0.00000002702702703));
        units.add(new Unit(MICROCURIE, R.string.microcurie, 37000.0, 0.00002702702703));
        units.add(new Unit(RUTHERFORD, R.string.rutherford, 1000000.0, 0.000001));
        units.add(new Unit(DPS, R.string.dps, 1.0, 1.0));
        units.add(new Unit(DPM, R.string.dpm, 0.01666666666667, 60.0));
        return new Conversion(Conversion.RADIOACTIVITY, R.string.radioactivity, R.drawable.ic_radioactivity, units);
    }

    private Conversion getDensityConversions() {
        // Base unit - Kilogram per cubic metre

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(T_CUBM, R.string.t_cubm, 1000.0, 0.001));
        units.add(new Unit(KG_CUBM, R.string.kg_cubm, 1.0, 1.0));
        units.add(new Unit(KG_CUBDM, R.string.kg_cubdm, 1000.0, 0.001));
        units.add(new Unit(KG_L, R.string.kg_l, 1000.0, 0.001));
        units.add(new Unit(G_CUBM, R.string.g_cubm, 0.001, 1000.0));
        units.add(new Unit(G_CUBCM, R.string.g_cubcm, 1000.0, 0.001));
        units.add(new Unit(G_L, R.string.g_l, 1.0, 1.0));
        units.add(new Unit(G_ML, R.string.g_ml, 1000.0, 0.001));
        units.add(new Unit(LB_CUBFT, R.string.lb_cubft, 16.01846337, 0.06242796059));
        units.add(new Unit(LB_CUBIN, R.string.lb_cubin, 27679.90471, 0.000036127292));
        units.add(new Unit(LB_GAL, R.string.lb_gal, 119.8264273169, 0.008345404452));
        units.add(new Unit(OZ_CUBFT, R.string.oz_cubft, 1.001153961, 0.9988473691));
        units.add(new Unit(OZ_CUBIN, R.string.oz_cubin, 1729.994044, 0.0005780366721));
        units.add(new Unit(OZ_GAL, R.string.oz_gal, 7.4891517073, 0.1335264712));
        return new Conversion(Conversion.DENSITY, R.string.density, R.drawable.ic_density, units);
    }

    private Conversion getDynamicViscosityConversions() {
        // Base unit - Pascal second

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(PASCAL_S, R.string.pascal_s, 1.0, 1.0));
        units.add(new Unit(POISE, R.string.poise, 0.1, 10.0));
        units.add(new Unit(LB_FTH, R.string.lb_fth, 0.0004133789, 2419.0881537495));
        units.add(new Unit(LB_FTS, R.string.lb_fts, 1.48816404, 0.6719689316));
        units.add(new Unit(LBFS_SQFT, R.string.lbfs_sqft, 47.880259, 0.02088543422));
        units.add(new Unit(LBFS_SQIN, R.string.lbfs_sqin, 6894.757296, 0.0001450377377));
        units.add(new Unit(PDS_SQFT, R.string.pds_sqft, 1.48816, 0.67197));
        units.add(new Unit(SLUG_FT_S, R.string.slug_ft_s, 47.88026, 0.02089));
        units.add(new Unit(G_CM_S, R.string.g_cm_s, 0.1, 10.0));
        units.add(new Unit(KGFS_SQM, R.string.kgfs_sqm, 9.80665, 0.10197));
        units.add(new Unit(NS_SQM, R.string.ns_sqm, 1.0, 1.0));
        units.add(new Unit(MILLINS_SQM, R.string.millins_sqm, 0.001, 1000.0));
        units.add(new Unit(DYNES_SQCM, R.string.dynes_sqcm, 0.1, 10.0));
        return new Conversion(Conversion.DYNAMIC_VISCOSITY, R.string.dynamic_viscosity, R.drawable.ic_dynamicviscosity, units);
    }

    private Conversion getKinematicViscosityConversions() {
        // Base unit - Square metre per second

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(SQM_S, R.string.sqm_s, 1.0, 1.0));
        units.add(new Unit(SQM_H, R.string.sqm_h, 0.00027777777778, 3600.0));
        units.add(new Unit(SQCM_S, R.string.sqcm_s, 0.0001, 10000.0));
        units.add(new Unit(SQMM_S, R.string.sqmm_s, 0.000001, 1000000.0));
        units.add(new Unit(SQFT_S, R.string.sqft_s, 0.09290304, 10.7639104167));
        units.add(new Unit(SQFT_H, R.string.sqft_h, 0.0000258064, 38750.0775));
        units.add(new Unit(SQIN_S, R.string.sqin_s, 0.00064516, 1550.0031));
        units.add(new Unit(STOKES, R.string.stokes, 0.0001, 10000.0));
        return new Conversion(Conversion.KINEMATIC_VISCOSITY, R.string.kinematic_viscosity, R.drawable.ic_kinematicviscosity, units);
    }

    private Conversion getMagneticFluxConversions() {
        // Base unit - Weber

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(WEBER, R.string.weber, 1.0, 1.0));
        units.add(new Unit(MILLIWEBER, R.string.milliweber, 0.001, 1000.0));
        units.add(new Unit(MICROWEBER, R.string.microweber, 0.000001, 100000.0));
        units.add(new Unit(VOLT_S, R.string.volt_s, 1.0, 1.0));
        units.add(new Unit(UNIT_POLE, R.string.unit_pole, 0.00000012566370614, 7957747.15459));
        units.add(new Unit(MEGALINE, R.string.megaline, 0.01, 100.0));
        units.add(new Unit(KILOLINE, R.string.kiloline, 0.00001, 100000.0));
        units.add(new Unit(LINE, R.string.line, 0.000000001, 100000000.0));
        units.add(new Unit(MAXWELL, R.string.maxwell, 0.00000001, 100000000.0));
        units.add(new Unit(TESLA_SQM, R.string.tesla_sqm, 1.0, 1.0));
        units.add(new Unit(TESLA_SQCM, R.string.tesla_sqcm, 0.0001, 10000.0));
        units.add(new Unit(GAUSS_SQCM, R.string.gauss_sqcm, 0.00000001, 100000000.0));
        units.add(new Unit(MAGNETIC_FLUX_QUANTUM, R.string.magnetic_flux_quantum, 0.00000000000000206783461, 483597670320000.0));
        return new Conversion(Conversion.MAGNETIC_FLUX, R.string.magnetic_flux, R.drawable.ic_magneticflux, units);
    }

    private Conversion getMagneticFluxDensityConversions() {
        // Base unit - Tesla

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(MEGATESLA, R.string.megatesla, 1000000.0, 0.000001));
        units.add(new Unit(KILOTESLA, R.string.kilotesla, 1000.0, 0.001));
        units.add(new Unit(TESLA, R.string.tesla, 1.0, 1.0));
        units.add(new Unit(MILLITESLA, R.string.millitesla, 0.001, 1000.0));
        units.add(new Unit(MICROTESLA, R.string.microtesla, 0.000001, 1000000.0));
        units.add(new Unit(NANOTESLA, R.string.nanotesla, 0.000000001, 1000000000.0));
        units.add(new Unit(PICOTESLA, R.string.picotesla, 0.000000000001, 1000000000000.0));
        units.add(new Unit(GAUSS, R.string.gauss, 0.0001, 10000.0));
        units.add(new Unit(GAMMA, R.string.gamma, 0.000000001, 1000000000.0));
        units.add(new Unit(WEBER_SQM, R.string.weber_sqm, 1.0, 1.0));
        units.add(new Unit(WEBER_SQCM, R.string.weber_sqcm, 10000.0, 0.0001));
        units.add(new Unit(WEBER_SQIN, R.string.weber_sqin, 1550.0031, 0.00064516));
        units.add(new Unit(MAXWELL_SQM, R.string.maxwell_sqm, 1.0, 1.0));
        units.add(new Unit(MAXWELL_SQCM, R.string.maxwell_sqcm, 0.0001, 10000.0));
        units.add(new Unit(MAXWELL_SQIN, R.string.maxwell_sqin, 0.000015500031, 64516.0));
        units.add(new Unit(LINE_SQCM, R.string.line_sqcm, 0.0001, 10000.0));
        units.add(new Unit(LINE_SQIN, R.string.line_sqin, 0.000015500031, 64516.0));
        return new Conversion(Conversion.MAGNETIC_FLUX_DENSITY, R.string.magnetic_flux_density, R.drawable.ic_magneticfluxdensity, units);
    }

    private Conversion getMagneticFieldStrengthConversions() {
        // Base unit - Ampere per metre

        List<Unit> units = new ArrayList<>();
        units.add(new Unit(AMPERE_METRE, R.string.ampere_metre, 1.0, 1.0));
        units.add(new Unit(KILOAMPERE_M, R.string.kiloampere_m, 1000.0, 0.001));
        units.add(new Unit(AMPERE_TURN_M, R.string.ampere_turn_m, 1.0, 1.0));
        units.add(new Unit(OERSTED, R.string.oersted, 79.57747, 0.01257));
        return new Conversion(Conversion.MAGNETIC_FIELD_STRENGTH, R.string.magnetic_field_strength, R.drawable.ic_magneticfieldstrength, units);
    }


}
