package com.unit.converter.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.unit.converter.R;
import com.unit.converter.database.factories.HelperFactory;
import com.unit.converter.enums.FragmentEnum;
import com.unit.converter.fragments.BaseFragment;
import com.unit.converter.fragments.ConverterFragment;
import com.unit.converter.fragments.FavoritesFragment;
import com.unit.converter.fragments.MainFragment;
import com.unit.converter.fragments.SettingsFragment;
import com.unit.converter.fragments.SplashFragment;
import com.unit.converter.interfaces.IConstants;
import com.unit.converter.interfaces.IGraphicConstants;
import com.unit.converter.utils.DialogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IGraphicConstants, IConstants, View.OnClickListener {

    private BaseFragment mCurrentFragment;
    private Bundle mFragmentBundle;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        init();
    }

    private void init() {


        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        mToolbar.findViewById(R.id.open_favourite).setOnClickListener(this);
        mToolbar.findViewById(R.id.settings).setOnClickListener(this);
        mDrawer = findViewById(R.id.drawer_layout);
        setUpDrawer(mDrawer);

        HelperFactory.setHelper(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        switchFragment(FragmentEnum.SPLASH_FRAGMENT);
    }

    public void setUpDrawer(DrawerLayout drawer) {
        if (drawer != null && mToolbar != null) {
            mToggle = new ActionBarDrawerToggle(
                    this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mToggle.syncState();
            mToggle.setHomeAsUpIndicator(this.getResources().getDrawable(HOME_AS_UP_IMAGE_ID));
            mToggle.setDrawerIndicatorEnabled(true);
            mToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            drawer.addDrawerListener(mToggle);
        }
    }

    public void setFragmentBundle(Bundle mFragmentBundle) {
        this.mFragmentBundle = mFragmentBundle;
    }

    public void setToolbarTitle(FragmentEnum fragmentEnum) {
        if (mToolbar != null) {
            mToolbar.setTitle(getResources().getString(fragmentEnum.getFragmentTitleId()));
        }
    }

    public void setToolbarTitle(int title) {
        if (mToolbar != null) {
            mToolbar.setTitle(getResources().getString(title));
        }
    }

    private void setNavigationButtonAndTitle(boolean isHomeAsUp, FragmentEnum fragmentEnum) {
        if (mToggle != null) {
            mToggle.setDrawerIndicatorEnabled(isHomeAsUp);
        }
        setToolbarTitle(fragmentEnum);
    }

    public void switchFragment(FragmentEnum fragmentEnum) {
        boolean isHomeAsUp = false;
        boolean isEnableNavigationMenu = true;
        switch (fragmentEnum) {
            case SPLASH_FRAGMENT:
                mCurrentFragment = new SplashFragment();
                isEnableNavigationMenu = false;
                break;
            case MAIN_FRAGMENT:
                mCurrentFragment = new MainFragment();
                isEnableNavigationMenu = false;
                break;
            case CONVERTOR_FRAGMENT:
                mCurrentFragment = new ConverterFragment();
                mCurrentFragment.setArguments(mFragmentBundle);
                break;
            case SETTINGS_FRAGMENT:
                mCurrentFragment = new SettingsFragment();
                mCurrentFragment.setArguments(mFragmentBundle);
                break;
            case FAVORITES_FRAGMENT:
                mCurrentFragment = new FavoritesFragment();
                mCurrentFragment.setArguments(mFragmentBundle);
                break;

        }
        setToolbarTitle(fragmentEnum);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.addToBackStack(null);

        transaction.replace(R.id.frame_fragment_layout, mCurrentFragment, String.valueOf(fragmentEnum.getFragmentId()));
        transaction.commitAllowingStateLoss();

        setNavigationMenuVisible(isEnableNavigationMenu);

        invalidateOptionsMenu();
        setNavigationButtonAndTitle(isHomeAsUp, fragmentEnum);
    }

    public void setNavigationMenuVisible(boolean isEnableNavigationMenu) {
        if (!isEnableNavigationMenu) {
            if (mCurrentFragment instanceof SplashFragment) {
                setToolbarVisibility(false);
            } else {
                setToolbarVisibility(true);
            }
            if (mDrawer != null) {
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
            if (mToggle != null) {
                mToggle.setHomeAsUpIndicator(null);
            }

        } else {
            if (mDrawer != null) {
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
            setToolbarVisibility(true);
            if (mToggle != null) {
                mToggle.setHomeAsUpIndicator(this.getResources().getDrawable(HOME_AS_UP_IMAGE_ID));
            }
        }
    }

    public void setToolbarVisibility(boolean isVisible) {
        if (mToolbar != null) {
            if (isVisible) {
                mToolbar.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
            } else {
                mToolbar.setVisibility(View.GONE);
            }
        }

    }

    public void setToolbarButtonsVisibility(boolean isAddFavoritesVisible, boolean isOpenfavoritesVisible, boolean isSettingsVisible) {
        mToolbar.findViewById(R.id.add_favourite).setVisibility(isAddFavoritesVisible ? View.VISIBLE : View.GONE);
        mToolbar.findViewById(R.id.open_favourite).setVisibility(isOpenfavoritesVisible ? View.VISIBLE : View.GONE);
        mToolbar.findViewById(R.id.settings).setVisibility(isSettingsVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer != null) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else {
                if (mCurrentFragment == null || !mCurrentFragment.onBackPressed()) {
                    DialogUtils.getExitDialog(this).show();
                }
            }
        }
        /*if (manager.getBackStackEntryCount()>2) {
            manager.popBackStack();
        }
        else {
            DialogUtils.getExitDialog(this).show();
        }*/
    }


    public BaseFragment getCurrentFragment() {
        return mCurrentFragment;
    }


    @Override
    public void onClick(View view) {
        List<Integer> list = new ArrayList<>();
        Bundle b = null;
        if (mFragmentBundle == null) {
            b = new Bundle();
            list.add(Integer.parseInt(getCurrentFragment().getTag()));
        } else {
            b = mFragmentBundle;
            list = (List<Integer>) mFragmentBundle.getSerializable(FRAGMENT);
            if (!list.contains(Integer.parseInt(getCurrentFragment().getTag()))) {
                list.add(Integer.parseInt(getCurrentFragment().getTag()));
            }
        }
        b.putSerializable(FRAGMENT, (Serializable) list);
        setFragmentBundle(b);
        switch (view.getId()) {
            case R.id.open_favourite:
                switchFragment(FragmentEnum.FAVORITES_FRAGMENT);
                break;
            case R.id.settings:
                switchFragment(FragmentEnum.SETTINGS_FRAGMENT);
                break;
        }
    }
}
