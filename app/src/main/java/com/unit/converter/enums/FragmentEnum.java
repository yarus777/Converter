package com.unit.converter.enums;


import com.unit.converter.R;

/**
 * Перечисление для работы с фрагментами
 * имя_фрагмента(заголовок_фрагмента, ID_фрагмента)
 */
public enum FragmentEnum {
    SPLASH_FRAGMENT(R.string.splash_fragment_title, 0),
    MAIN_FRAGMENT(R.string.main_fragment_title, 1),
    CONVERTOR_FRAGMENT(R.string.converter_fragment_title, 2),
    SETTINGS_FRAGMENT(R.string.settings_fragment_title, 3),
    FAVORITES_FRAGMENT(R.string.favorites_fragment_title,4);


    private int mFragmentTitleId; //Ссылка на строку в ресурсах (заголовок фрагмента)
    private int mFragmentId; //Id фрагмента

    FragmentEnum(int mFragmentTitleId, int mFragmentId) {
        this.mFragmentTitleId = mFragmentTitleId;
        this.mFragmentId = mFragmentId;
    }

    /**
     * Метод получения данных о фрагменте по его ID
     *
     * @param id ID фрагмента
     * @return Иинформация о фрагменте
     */
    public static FragmentEnum getEnum(int id) {
        FragmentEnum[] values = FragmentEnum.values();
        if (id >= 0 && id < values.length) {
            return values[id];
        }

        return null;
    }

    public int getFragmentTitleId() {
        return mFragmentTitleId;
    }

    public int getFragmentId() {
        return mFragmentId;
    }
}
