package com.unit.converter.fragments;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unit.converter.R;
import com.unit.converter.adapters.ConversionsCategoriesListViewAdapter;
import com.unit.converter.adapters.ConversionsCategoriesRecyclerViewAdapter;
import com.unit.converter.conversions.Conversion;
import com.unit.converter.conversions.Conversions;
import com.unit.converter.enums.FragmentEnum;
import com.unit.converter.interfaces.IConstants;
import com.unit.converter.interfaces.IOnConversionClick;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BaseFragment implements IOnConversionClick, IConstants{

    private Conversions mConversions;
    private ConversionsCategoriesRecyclerViewAdapter mConversionsCategoriesRecyclerViewAdapter;
    private RecyclerView mCategoriesRecyclerView;
    private ListView mCategoriesListView;
    private ConversionsCategoriesListViewAdapter mConversionsCategoriesListViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCurrentView = inflater.inflate(R.layout.main_fragment, null);
        initView();
        return mCurrentView;
    }

    @Override
    protected void initView() {

        mMainActivity.setToolbarButtonsVisibility(false, true, true);

        mConversions = Conversions.getInstance();

        mCategoriesListView = mCurrentView.findViewById(R.id.categories_list_view);
        mConversionsCategoriesListViewAdapter = new ConversionsCategoriesListViewAdapter(mMainActivity, mConversions, this);
        mCategoriesListView.setAdapter(mConversionsCategoriesListViewAdapter);

        /*mCategoriesRecyclerView = mCurrentView.findViewById(R.id.categories_recycler_view);
        mConversionsCategoriesRecyclerViewAdapter = new ConversionsCategoriesRecyclerViewAdapter(mMainActivity, mConversions, this);
        mCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(mMainActivity, LinearLayoutManager.VERTICAL, false));
        mCategoriesRecyclerView.setAdapter(mConversionsCategoriesRecyclerViewAdapter);*/
    }

    @Override
    public void onConversionClick(Conversion conversion) {
        Bundle dateBundle = new Bundle();
        dateBundle.putSerializable(CONVERSION, conversion);
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(mMainActivity.getCurrentFragment().getTag()));
        dateBundle.putSerializable(FRAGMENT, (Serializable) list);
        mMainActivity.setFragmentBundle(dateBundle);
        switchFragment(FragmentEnum.CONVERTOR_FRAGMENT);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
