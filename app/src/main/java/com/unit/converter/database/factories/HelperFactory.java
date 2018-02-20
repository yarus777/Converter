package com.unit.converter.database.factories;


import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.unit.converter.database.helpers.DatabaseHelper;


/**
 * Синглтон для работы с БД (синглентон)
 */
public class HelperFactory {
    private static DatabaseHelper databaseHelper;

    /**
     * Метод для получения helper'а
     *
     * @return helper для работы с бд
     */
    public static DatabaseHelper getHelper() {
        return databaseHelper;
    }

    /**
     * Метод для инициализации helper'а
     *
     * @param context контекст
     */
    public static void setHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
    }

    /**
     * Метод для освобождения ресурсов
     */
    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }
}