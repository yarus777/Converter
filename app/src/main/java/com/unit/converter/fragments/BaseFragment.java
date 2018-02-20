package com.unit.converter.fragments;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unit.converter.activities.MainActivity;
import com.unit.converter.enums.FragmentEnum;
import com.unit.converter.interfaces.IConstants;


public abstract class BaseFragment extends Fragment implements View.OnClickListener, IConstants {

    protected View mCurrentView;             //View текущего фрагмента
    protected MainActivity mMainActivity;    //Ссылка на MainActivity
    protected Resources mResources;

    @Override
    public void onAttach(Context context) {
        if (context instanceof MainActivity)
            mMainActivity = (MainActivity) context;
        super.onAttach(context);
    }

    /**
     * Создание фрагмента
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return CURRENT_VIEW
     */
    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * Инициализация фрагмента
     */
    protected abstract void initView();

    /**
     * Обработка нажатия на кнопку назад
     *
     * @return true - выход из приложения, иначе false
     */
    public boolean onBackPressed() {
        switchFragment(FragmentEnum.MAIN_FRAGMENT);
        return true;
    }

    /**
     * Метод для переключения фрагментов
     *
     * @param fragmentEnum Информация о фрагменте
     */
    protected void switchFragment(FragmentEnum fragmentEnum) {
        if (mMainActivity != null) {
            mMainActivity.switchFragment(fragmentEnum);
        } else if (getActivity() != null && getActivity() instanceof MainActivity) {
            mMainActivity = (MainActivity) getActivity();
            mMainActivity.switchFragment(fragmentEnum);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
