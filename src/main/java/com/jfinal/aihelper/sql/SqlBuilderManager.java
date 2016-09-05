package com.jfinal.aihelper.sql;

import java.util.HashMap;

/**
 * Created by tj on 2016/1/9.12:28
 * SqlBuilder管理类
 */
public class SqlBuilderManager {

    private static final SqlBuilderManager me = new SqlBuilderManager();

    private final HashMap<String, ISqlBuilder> sqlBuilderMap = new HashMap<String,ISqlBuilder>();

    public static final String MAIN_CONFIG_NAME = "main";
    public static final String ORACLE = "ORACLE";
    public static final String MYSQL = "MYSQL";
    public static final String MSSQL = "MSSQL";



    /**
     * 默认的sqlbuilder
     */
    private static ISqlBuilder sqlBuilder;

    public static SqlBuilderManager me(){
        return me;
    }

    public void addSqlBuilder(String name,ISqlBuilder sqlBuilder){
        if (MAIN_CONFIG_NAME.equals(name)) {
            me.sqlBuilder = sqlBuilder;
        }
        me.sqlBuilderMap.put(name,sqlBuilder);
    }

    public ISqlBuilder getSqlBuilder(String configName){
        return me.sqlBuilderMap.get(configName);
    }
}
