package com.jfinal.aihelper.sql.builer;

import com.jfinal.aihelper.sql.ISqlBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by tangjing on 2016/9/5.17:00
 */
public class OracleBuilder implements ISqlBuilder {
    @Override
    public void and(StringBuffer sql, String alias, String column, Object value, List<Object> args) {

    }

    @Override
    public void and(StringBuffer sql, String alias, Map<String, Object> columValuesMap, List<Object> args) {

    }

    @Override
    public void search(StringBuffer sql, String alias, String[] searchColumns, Object value, List<Object> args) {

    }

    @Override
    public void order(StringBuffer sql, String alias, String sort, String order) {

    }

    @Override
    public void dateBefore(StringBuffer sql, String alias, String column, Object value, List<Object> args) {

    }

    @Override
    public void dateAfter(StringBuffer sql, String alias, String column, Object value, List<Object> args) {

    }
}
