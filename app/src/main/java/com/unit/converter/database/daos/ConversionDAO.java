package com.unit.converter.database.daos;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.unit.converter.conversions.Conversion;

import java.sql.SQLException;
import java.util.List;


/**
 * DAO для профиля
 */
public class ConversionDAO extends BaseDaoImpl<Conversion, Long> {

    protected ConversionDAO(Class<Conversion> dataClass) throws SQLException {
        super(dataClass);
    }

    public ConversionDAO(ConnectionSource connectionSource, Class<Conversion> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Conversion> getConversionModels() {
        List<Conversion> result = null;
        try {
            result = this.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isConversionExists(Conversion model) {
        List<Conversion> result = null;
        Conversion temp = null;
        try {
            result = this.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Conversion c : result) {
            if (c.getId() == model.getId()) {
                temp = c;
            }
        }
        if (temp != null){
           return true;
        }
        else {
           return false;
        }
    }

    public void deleteConversionModel(Conversion model) {
        DeleteBuilder<Conversion, Long> deleteBuilder = deleteBuilder();
        try {
            deleteBuilder.where().eq(Conversion.CONVERSION_ID, model.getId());
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
