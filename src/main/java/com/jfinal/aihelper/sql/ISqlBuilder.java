package com.jfinal.aihelper.sql;

import java.util.List;
import java.util.Map;

/**
 * Created by tj on 2016/1/8.23:38
 * sql查询条件语句工具类
 */
public interface ISqlBuilder {

    /**
     * 添加查询条件
     * @param value 查询的值
     * @param column 数据库字段
     * @param args 查询参数集合
     * @param sql 查询语句对象
     * @param alias 别名
     */
    void and(StringBuffer sql, String alias,String column, Object value, List<Object> args);


    /**
     * 添加查询条件
     * @param columValuesMap 字段、值 对应的map
     * @param args 查询参数集合
     * @param sql 查询语句对象
     * @param alias 别名
     */
     void and(StringBuffer sql, String alias, Map<String,Object> columValuesMap, List<Object> args);

    /**
     *
     * @param sql
     * @param alias
     * @param value 查询的值
     * @param args 查询参数集合
     * @param searchColumns 需要查询的字段（对应数据库）
     */
    void search(StringBuffer sql, String alias, String[] searchColumns, Object value, List<Object> args);

    /**
     * 简单排序规则 order by alias.sort order
     * @param sql
     * @param alias
     * @param sort
     * @param order
     */
    void order(StringBuffer sql, String alias, String sort, String order);

    /**
     * 日期排序
     * @param sql
     * @param alias
     * @param column
     * @param value
     * @param args
     */
    void dateBefore(StringBuffer sql, String alias, String column, Object value, List<Object> args);

    /**
     * 日期排序
     * @param sql
     * @param alias
     * @param column
     * @param value
     * @param args
     */
    void dateAfter(StringBuffer sql, String alias, String column, Object value, List<Object> args);


}
