package com.jfinal.aihelper.sql;

/**
 * Created by tj on 2016/1/9.12:28
 * SqlBuilder管理类
 */
public class SqlBuilderManager {

    private static final SqlBuilderManager me = new SqlBuilderManager();

    private ISqlBuilder sqlBuilder;

    public static SqlBuilderManager me(){
        return me;
    }

    public void setSqlBuilder(ISqlBuilder sqlBuilder){
        me.sqlBuilder = sqlBuilder;
    }

    public ISqlBuilder getSqlBuilder() {
        return sqlBuilder;
    }

    public static ISqlBuilder defaultSqlBuilder(){
        return me.getSqlBuilder();
    }
}
