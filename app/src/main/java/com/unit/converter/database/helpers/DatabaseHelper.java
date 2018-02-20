package com.unit.converter.database.helpers;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.unit.converter.conversions.Conversion;
import com.unit.converter.database.daos.ConversionDAO;

import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "Conversions.db"; //Название БД
    private static final int DATABASE_VERSION = 1;                  //Текущая версия БД

    private ConversionDAO mConversionDAO = null;                          //DAO для профиля

    /**
     * Конструктор с параметрами
     *
     * @param context Context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Метод создания таблиц
     *
     * @param database         БД
     * @param connectionSource ConnectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Conversion.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод при обновлении версии БД
     *
     * @param database         БД
     * @param connectionSource ConnectionSource
     * @param oldVersion       старая версия БД
     * @param newVersion       новая версия БД
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    /**
     * Закрытие соединения с БД и очистка ссылок на все DAO
     */
    @Override
    public void close() {
        super.close();
        mConversionDAO = null;
    }

    /**
     * Удаление данных из всех таблиц
     */
    public void clearAllData() {

        try {
            TableUtils.clearTable(getConnectionSource(), Conversion.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ConversionDAO getConversionDAO() {
        if (mConversionDAO == null) {
            try {
                mConversionDAO = new ConversionDAO(getConnectionSource(), Conversion.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mConversionDAO;
    }

}
