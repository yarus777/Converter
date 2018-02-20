package com.unit.converter.sharedpreferences;

import android.content.Context;
import android.preference.PreferenceManager;

import com.unit.converter.R;

/**
 * Синглтон SharedPreferences приложения
 */
public class Preferences {
    public static final String PREFS_NUMBER_OF_DECIMALS = "number_decimals";
    public static final String PREFS_DECIMAL_SEPARATOR = "decimal_separator";
    public static final String PREFS_GROUP_SEPARATOR = "group_separator";


    private static Preferences mInstance;
    private android.content.SharedPreferences mPrefs;
    private Context mContext;

    public static Preferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Preferences(context.getApplicationContext());
        }

        return mInstance;
    }

    private Preferences(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mContext = context;
    }

    public android.content.SharedPreferences getPreferences() {
        return mPrefs;
    }


    public int getNumberDecimals() {
        return Integer.parseInt(mPrefs.getString(PREFS_NUMBER_OF_DECIMALS, mContext.getString(R.string.default_number_decimals)));
    }

    public String getDecimalSeparator() {
        return mPrefs.getString(PREFS_DECIMAL_SEPARATOR, mContext.getString(R.string.default_decimal_separator));
    }

    public String getGroupSeparator() {
        return mPrefs.getString(PREFS_GROUP_SEPARATOR, mContext.getString(R.string.default_group_separator));
    }

    public void setNumberDecimals(String s) {
        mPrefs.edit().putString(PREFS_NUMBER_OF_DECIMALS, s).apply();

    }

    public void setDecimalSeparator(String s) {
        mPrefs.edit().putString(PREFS_DECIMAL_SEPARATOR, s).apply();
    }

    public void setGroupSeparator(String s) {
        mPrefs.edit().putString(PREFS_GROUP_SEPARATOR, s).apply();
    }

}
