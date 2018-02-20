package com.unit.converter.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.unit.converter.R;
import com.unit.converter.enums.FragmentEnum;
import com.unit.converter.interfaces.IConstants;
import com.unit.converter.sharedpreferences.Preferences;

import java.util.List;


public class SettingsFragment extends BaseFragment implements IConstants {

    private Spinner mDecimalNimbersSpinner;
    private Spinner mGroupSeparatorSpinner;
    private Spinner mDecimalSeparatorSpinner;
    private Preferences mPrefs;
    private Bundle mBundle;
    private List<Integer> mFragmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCurrentView = inflater.inflate(R.layout.settings_fragment, null);
        mBundle = getArguments();
        initView();
        return mCurrentView;
    }

    @Override
    protected void initView() {
        mPrefs = Preferences.getInstance(mMainActivity);
        if (mBundle != null) {
            mFragmentList = (List<Integer>) mBundle.getSerializable(FRAGMENT);
            if (!mFragmentList.contains(Integer.parseInt(mMainActivity.getCurrentFragment().getTag()))) {
                mFragmentList.add(Integer.parseInt(mMainActivity.getCurrentFragment().getTag()));
            }
        }

        mMainActivity.setToolbarButtonsVisibility(false, false, false);

        mDecimalNimbersSpinner = mCurrentView.findViewById(R.id.number_decimals_spinner);
        mGroupSeparatorSpinner = mCurrentView.findViewById(R.id.group_separator_spinner);
        mDecimalSeparatorSpinner = mCurrentView.findViewById(R.id.decimal_separator_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mMainActivity, R.layout.spinner_item, mMainActivity.getResources().getStringArray(R.array.number_decimals));
        mDecimalNimbersSpinner.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(String.valueOf(mPrefs.getNumberDecimals()));
        mDecimalNimbersSpinner.setSelection(spinnerPosition);
        adapter = new ArrayAdapter<>(mMainActivity, R.layout.spinner_item, mMainActivity.getResources().getStringArray(R.array.group_separators));
        mGroupSeparatorSpinner.setAdapter(adapter);
        spinnerPosition = adapter.getPosition(mPrefs.getGroupSeparator());
        Log.d("MYTAG", "Separator GROUP" + mPrefs.getGroupSeparator());
        mGroupSeparatorSpinner.setSelection(spinnerPosition);
        adapter = new ArrayAdapter<>(mMainActivity, R.layout.spinner_item, mMainActivity.getResources().getStringArray(R.array.decimal_separators));
        mDecimalSeparatorSpinner.setAdapter(adapter);
        spinnerPosition = adapter.getPosition(mPrefs.getDecimalSeparator());
        Log.d("MYTAG", "Separator DEC" + mPrefs.getDecimalSeparator());
        mDecimalSeparatorSpinner.setSelection(spinnerPosition);

        mDecimalNimbersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPrefs.setNumberDecimals((String) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mGroupSeparatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPrefs.setGroupSeparator((String) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mDecimalSeparatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPrefs.setDecimalSeparator((String) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        mFragmentList.remove(mFragmentList.size() - 1);
        mBundle.putBoolean(WERE_SETTINGS_CHANGED, true);
        mMainActivity.setFragmentBundle(mBundle);
        mMainActivity.switchFragment(FragmentEnum.getEnum(mFragmentList.get(mFragmentList.size() - 1)));
        return true;
    }
}




