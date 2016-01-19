package com.jfinal.aihelper.sql;

import com.jfinal.kit.StrKit;

import java.util.List;

/**
 * Created by tj on 2016/1/8.23:38
 * sql查询条件语句工具类
 */
public class MySqlBuilder implements ISqlBuilder{

    /**
     * 添加查询条件
     * @param pm 参数集合
     * @param key 数据库字段集合同时也要存在于pm内
     * @param args 查询参数集合
     * @param sql 查询语句对象
     * @param alias 别名
     */
    public void and(StringBuffer sql,String key, ParamMap pm, List<Object> args,  String alias){
        if (pm.isValid(key)) {
            // and t.key = ?
            sql.append(" and ");
            sql.append(getAliasPrefix(alias));
            sql.append(key);
            sql.append("=?");
            args.add(pm.get(key));
        }
    }

    /**
     * 添加查询条件
     * @param pm 参数集合
     * @param keys 匹配字段数组
     * @param args 查询参数集合
     * @param sql 查询语句对象
     * @param alias 别名
     */
    public void and(StringBuffer sql,String[] keys, ParamMap pm, List<Object> args,  String alias){
        for(int i=0;i<keys.length;i++){
            and(sql,keys[i],pm,args,alias);
        }
    }

    /**
     *
     * @param sql
     * @param alias
     * @param key pm里面的需要查询的key
     * @param pm 参数集合
     * @param args 查询参数集合
     * @param searchColumns 需要查询的字段（对应数据库）
     */
    public void search(StringBuffer sql, String alias,String key,ParamMap pm,List<Object> args,String[] searchColumns){
        if (pm.isValid(key)) {
            if(searchColumns.length==1){
                sql.append(" and instr("+getAliasPrefix(alias)+searchColumns[0]+",?)>0");
                args.add(pm.get(key));
            }else if(searchColumns.length>1){
                String key1 = searchColumns[0];
                sql.append(" and (instr("+getAliasPrefix(alias)+key1+",?)>0 or");//第一个
                args.add(pm.get(key));
                for(int i = 1;i<searchColumns.length-1;i++){//遍历中间的
                    String keyTmp = searchColumns[i];
                    sql.append(" instr("+getAliasPrefix(alias)+keyTmp+",?)>0 or");//最后一个
                    args.add(pm.get(key));
                }
                String keyEnd = searchColumns[searchColumns.length-1];//最后一个
                sql.append(" instr("+getAliasPrefix(alias)+keyEnd+",?)>0)");
                args.add(pm.get(key));
            }
        }
    }

    public void order(StringBuffer sql, String alias, String sort, String order) {
        if (StrKit.notBlank(sort)) {
            sql.append(" order by " + getAliasPrefix(alias) + sort + " " + order);
        }
    }

    public void dateBefore(StringBuffer sql, String alias,String key,ParamMap pm,List<Object> args){
        if (pm.isValid(key)){
            sql.append(" and "+getAliasPrefix(alias)+key+"<?");
            args.add(pm.getDate(key));
        }
    }

    public void dateAfter(StringBuffer sql, String alias,String key,ParamMap pm,List<Object> args){
        if (pm.isValid(key)){
            sql.append(" and "+getAliasPrefix(alias)+key+">?");
            args.add(pm.getDate(key));
        }
    }

    /**
     * 加工别名的前缀   t 加工成t.
     * @param alias
     * @return
     */
    private static String getAliasPrefix(String alias){
        if (StrKit.isBlank(alias)) {
            return "";
        }else{
            return alias + ".";
        }
    }
}
