package com.jfinal.aihelper.sql;

import java.util.List;

/**
 * Created by tj on 2016/1/8.23:38
 * sql查询条件语句工具类
 */
public interface ISqlBuilder {

    /**
     * 添加查询条件
     * @param pm 参数集合
     * @param key 数据库字段集合同时也要存在于pm内
     * @param args 查询参数集合
     * @param sql 查询语句对象
     * @param alias 别名
     */
    void and(StringBuffer sql, String key, ParamMap pm, List<Object> args, String alias);


    /**
     * 添加查询条件
     * @param pm 参数集合
     * @param keys 匹配字段数组
     * @param args 查询参数集合
     * @param sql 查询语句对象
     * @param alias 别名
     */
     void and(StringBuffer sql, String[] keys, ParamMap pm, List<Object> args, String alias);

    /**
     *
     * @param sql
     * @param alias
     * @param key pm里面的需要查询的key
     * @param pm 参数集合
     * @param args 查询参数集合
     * @param searchColumns 需要查询的字段（对应数据库）
     */
    void search(StringBuffer sql, String alias, String key, ParamMap pm, List<Object> args, String[] searchColumns);

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
     * @param key
     * @param pm
     * @param args
     */
    void dateBefore(StringBuffer sql, String alias, String key, ParamMap pm, List<Object> args);

    /**
     * 日期排序
     * @param sql
     * @param alias
     * @param key
     * @param pm
     * @param args
     */
    void dateAfter(StringBuffer sql, String alias, String key, ParamMap pm, List<Object> args);
}
