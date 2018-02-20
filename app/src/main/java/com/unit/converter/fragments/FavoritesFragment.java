package com.unit.converter.fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unit.converter.R;
import com.unit.converter.adapters.FavoritesRecyclerViewAdapter;
import com.unit.converter.conversions.Conversion;
import com.unit.converter.conversions.ConversionCategory;
import com.unit.converter.conversions.Conversions;
import com.unit.converter.database.factories.HelperFactory;
import com.unit.converter.enums.FragmentEnum;
import com.unit.converter.interfaces.IOnConversionClick;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends BaseFragment implements IOnConversionClick {

    private FavoritesRecyclerViewAdapter mFavoritesRecyclerViewAdapter;
    private RecyclerView mFavoritesRecyclerView;
    private List<Conversion> mDBFavoriteConversions;
    private Bundle mBundle;
    private List<Integer> mFragmentList;
    private Conversions mConversions;
    private List<Conversion> mFavoriteConversions = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCurrentView = inflater.inflate(R.layout.favorites_fragment, null);
        mBundle = getArguments();
        initView();
        return mCurrentView;
    }

    @Override
    protected void initView() {

        if (mBundle != null) {
            mFragmentList = (List<Integer>) mBundle.getSerializable(FRAGMENT);
            if (!mFragmentList.contains(Integer.parseInt(mMainActivity.getCurrentFragment().getTag()))) {
                mFragmentList.add(Integer.parseInt(mMainActivity.getCurrentFragment().getTag()));
            }
        }

        mMainActivity.setToolbarButtonsVisibility(false, false, true);

        mDBFavoriteConversions = HelperFactory.getHelper().getConversionDAO().getConversionModels();

        mFavoritesRecyclerView = mCurrentView.findViewById(R.id.favorites_recycler_view);

        mConversions =  Conversions.getInstance();
        for (ConversionCategory cc : mConversions.getConversions()) {
            for (Conversion c : cc.getConversionCategoryList()) {
                for (Conversion c1 : mDBFavoriteConversions) {
                    if (c1.getId() == c.getId()) {
                        mFavoriteConversions.add(c);
                    }
                }

            }
        }

        mFavoritesRecyclerViewAdapter = new FavoritesRecyclerViewAdapter(mFavoriteConversions, this);
        mFavoritesRecyclerView.setLayoutManager(new LinearLayoutManager(mMainActivity, LinearLayoutManager.VERTICAL, false));
        mFavoritesRecyclerView.setAdapter(mFavoritesRecyclerViewAdapter);

    }

    public boolean onBackPressed() {
        mFragmentList.remove(mFragmentList.size() - 1);
        mMainActivity.switchFragment(FragmentEnum.getEnum(mFragmentList.get(mFragmentList.size() - 1)));
        return true;
    }

    @Override
    public void onConversionClick(Conversion conversion) {
        Bundle dateBundle = new Bundle();
        dateBundle.putSerializable(CONVERSION, conversion);
        dateBundle.putSerializable(FRAGMENT, (Serializable) mFragmentList);
        mMainActivity.setFragmentBundle(dateBundle);
        switchFragment(FragmentEnum.CONVERTOR_FRAGMENT);
    }
}
