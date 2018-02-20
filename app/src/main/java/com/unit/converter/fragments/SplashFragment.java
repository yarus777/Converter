package com.unit.converter.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unit.converter.R;
import com.unit.converter.enums.FragmentEnum;


public class SplashFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCurrentView = inflater.inflate(R.layout.splash_fragment, null);
        initView();
        return mCurrentView;
    }

    @Override
    protected void initView() {
        final long SPLASH_VISIBILITY_MILLIS = 3000;
        final Handler fragmentHandler = new Handler();
        Runnable fragmentRunnable = new Runnable() {
            @Override
            public void run() {
                switchFragment(FragmentEnum.MAIN_FRAGMENT);
                fragmentHandler.removeCallbacksAndMessages(this);
            }
        };
        fragmentHandler.postDelayed(fragmentRunnable, SPLASH_VISIBILITY_MILLIS);
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
