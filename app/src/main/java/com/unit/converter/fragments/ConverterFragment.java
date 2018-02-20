package com.unit.converter.fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.unit.converter.R;
import com.unit.converter.adapters.UnitRecyclerViewAdapter;
import com.unit.converter.conversions.Conversion;
import com.unit.converter.conversions.Converter;
import com.unit.converter.database.factories.HelperFactory;
import com.unit.converter.enums.FragmentEnum;
import com.unit.converter.interfaces.IConstants;
import com.unit.converter.interfaces.IOnEditWasChanged;
import com.unit.converter.sharedpreferences.Preferences;
import com.unit.converter.views.MyKeyboardView;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import static com.unit.converter.conversions.Converter.convertFuel;
import static com.unit.converter.conversions.Converter.convertTemperature;

public class ConverterFragment extends BaseFragment implements IConstants, IOnEditWasChanged {

    private UnitRecyclerViewAdapter mUnitRecyclerViewAdapter;
    private RecyclerView mUnitsRecyclerView;
    private Bundle mBundle;
    private Conversion mConversion;
    private List<String> mResults = new ArrayList<>();
    private int mPosition;
    private int mLastTouchedPosition = -1;
    private double mValue;
    private String mStringValue;
    private Preferences mPrefs;
    private MyKeyboardView mKeyboardView;
    private List<Integer> mFragmentList;

    private Toolbar mToolbar;
    private ImageView mStar;
    private boolean wereSettingChanged = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCurrentView = inflater.inflate(R.layout.converter_fragment, null);
        mBundle = getArguments();
        initView();
        return mCurrentView;
    }


    @Override
    protected void initView() {
        mPrefs = Preferences.getInstance(mMainActivity);

        mToolbar = mMainActivity.findViewById(R.id.toolbar);
        mMainActivity.setToolbarButtonsVisibility(true, false, true);
        mToolbar.findViewById(R.id.settings).setOnClickListener(this);
        mStar = mToolbar.findViewById(R.id.add_favourite);
        mStar.setOnClickListener(this);

        if (mBundle != null) {
            mConversion = (Conversion) mBundle.getSerializable(CONVERSION);
            mPosition = mBundle.getInt(CONVERT_POSITION);
            mStringValue = mBundle.getString(CONVERT_STRING);
            mLastTouchedPosition = mBundle.getInt(CURSOR_POSITION);
            mFragmentList = (List<Integer>) mBundle.getSerializable(FRAGMENT);
            wereSettingChanged = mBundle.getBoolean(WERE_SETTINGS_CHANGED);
            if (!mFragmentList.contains(Integer.parseInt(mMainActivity.getCurrentFragment().getTag()))) {
                mFragmentList.add(Integer.parseInt(mMainActivity.getCurrentFragment().getTag()));
            }

        }

        mKeyboardView = mCurrentView.findViewById(R.id.view_keyboard);
        mUnitsRecyclerView = mCurrentView.findViewById(R.id.units_recycler_view);

        if (mConversion != null) {
            mMainActivity.setToolbarTitle(mConversion.getConversionName());
            mStar.setImageResource(HelperFactory.getHelper().getConversionDAO().isConversionExists(mConversion) ? R.drawable.btn_star_big_on : R.drawable.btn_star_big_off);


            mUnitRecyclerViewAdapter = new UnitRecyclerViewAdapter(mMainActivity, mConversion.getUnits(), this, mResults, mKeyboardView);
            mUnitsRecyclerView.setLayoutManager(new LinearLayoutManager(mMainActivity, LinearLayoutManager.VERTICAL, false));
            mUnitsRecyclerView.setAdapter(mUnitRecyclerViewAdapter);
            mUnitsRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mUnitRecyclerViewAdapter.setListener(mLastTouchedPosition);
                }
            });
            mUnitsRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    switch (newState) {
                        case RecyclerView.SCROLL_STATE_IDLE:
                            mUnitRecyclerViewAdapter.setListener(mLastTouchedPosition);
                            break;
                        case RecyclerView.SCROLL_STATE_DRAGGING:
                            break;
                        case RecyclerView.SCROLL_STATE_SETTLING:
                            break;

                    }

                }
            });

            if (mStringValue != null) {
                onEditWasChanged(mPosition, mStringValue);
            }
        }

    }

    @Override
    public void onEditWasChanged(int position, String s) {
        mValue = isNumeric(s.replace(",", ".")) ? Double.parseDouble(s.replace(",", ".")) : 0;
        mPosition = position;
        if (wereSettingChanged) {
            mStringValue = getDecimalFormat().format(mValue);
        } else {
            mStringValue = s;
        }
        wereSettingChanged = false;
        convert();

    }

    @Override
    public void onEditWasTouched(int position) {
        mLastTouchedPosition = position;
    }

    private void convert() {
        double result;
        mResults.clear();
        for (int i = 0; i < mConversion.getUnits().size(); i++) {
            if (i != mPosition) {
                if (mConversion.getId() == Conversion.TEMPERATURE) {
                    result = convertTemperature(mConversion.getUnits().get(i).getId(), mConversion.getUnits().get(mPosition).getId(), mValue);
                }
                else if (mConversion.getId() == Conversion.FUEL_CONSUMPTION) {
                    result = convertFuel(mConversion.getUnits().get(mPosition).getConversionToBaseUnit(), mConversion.getUnits().get(i).getConversionFromBaseUnit(), mValue, mConversion.getUnits().get(i).getId(), mConversion.getUnits().get(mPosition).getId());
                }
                else {
                    result = Converter.convertAll(mConversion.getUnits().get(mPosition).getConversionToBaseUnit(), mConversion.getUnits().get(i).getConversionFromBaseUnit(), mValue);
                }
                if (mStringValue.isEmpty()) {
                    mResults.add(mStringValue);
                } else {
                    mResults.add(getDecimalFormat().format(result));
                }
            } else {
                mResults.add(mStringValue);
            }
        }
        mUnitRecyclerViewAdapter.update(mPosition, mLastTouchedPosition);
    }


    private boolean isNumeric(String number) {
        try {
            double d = Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private DecimalFormat getDecimalFormat() {
        DecimalFormat formatter = new DecimalFormat();

        //Set maximum number of decimal places
        formatter.setMaximumFractionDigits(mPrefs.getNumberDecimals());

        //Set group and decimal separators
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setDecimalSeparator(mPrefs.getDecimalSeparator().charAt(0));

        String groupSeparator = mPrefs.getGroupSeparator();
        boolean isSeparatorUsed = !groupSeparator.equals(mMainActivity.getString(R.string.group_separator_none));
        formatter.setGroupingUsed(isSeparatorUsed);
        if (isSeparatorUsed) {
            symbols.setGroupingSeparator(groupSeparator.charAt(0));
        }

        formatter.setDecimalFormatSymbols(symbols);
        return formatter;
    }


    public boolean onBackPressed() {
        mFragmentList.remove(mFragmentList.size() - 1);
        mMainActivity.switchFragment(FragmentEnum.getEnum(mFragmentList.get(mFragmentList.size() - 1)));
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_favourite:
                try {
                    if (HelperFactory.getHelper().getConversionDAO().isConversionExists(mConversion)) {
                        HelperFactory.getHelper().getConversionDAO().deleteConversionModel(mConversion);
                        mStar.setImageResource(R.drawable.btn_star_big_off);
                        Toast toast = Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.toast_delete_from_favorites), Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        HelperFactory.getHelper().getConversionDAO().create(mConversion);
                        mStar.setImageResource(R.drawable.btn_star_big_on);
                        Toast toast = Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.toast_add_to_favorites), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.settings:
                Bundle b = new Bundle();
                b.putSerializable(FRAGMENT, (Serializable) mFragmentList);
                b.putInt(CONVERT_POSITION, mPosition);
                b.putString(CONVERT_STRING, mStringValue);
                b.putSerializable(CONVERSION, mConversion);
                b.putInt(CURSOR_POSITION, mLastTouchedPosition);
                mMainActivity.setFragmentBundle(b);
                switchFragment(FragmentEnum.SETTINGS_FRAGMENT);
                break;
        }
    }

}
