package com.jfinal.aihelper.sql.builer;

import com.jfinal.aihelper.sql.ISqlBuilder;
import com.jfinal.kit.StrKit;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tangjing on 2016/9/5.17:00
 */
public class OracleBuilder implements ISqlBuilder {
    @Override
    public void and(StringBuffer sql, String alias, String column, Object value, List<Object> args) {
        if (StringUtils.isBlank(column)) {
            throw new RuntimeException("column can not be null or blank !");
        }
        sql.append(" and ");
        sql.append(getAliasPrefix(alias));
        sql.append(column);
        sql.append("=?");
        args.add(value);
    }

    @Override
    public void and(StringBuffer sql, String alias, Map<String, Object> columValuesMap, List<Object> args) {
        if(columValuesMap==null){
            throw new RuntimeException("columValuesMap can not be null !");
        }
        Set<String> columns =  columValuesMap.keySet();
        for(String column:columns) {
            and(sql,alias,column,columValuesMap.get(column),args);
        }
    }

    @Override
    public void search(StringBuffer sql, String alias, String[] searchColumns, Object value, List<Object> args) {
        if(searchColumns==null){
            throw new RuntimeException("searchColumns can not be null !");
        }
        if(searchColumns.length==1){
            sql.append(" and "+getAliasPrefix(alias)+searchColumns[0]+" like ?");
            args.add(getLikeValue(value));
        }else if(searchColumns.length>1){
            String key1 = searchColumns[0];
            sql.append(" and ("+getAliasPrefix(alias)+key1+" like ? or");//第一个
            args.add(getLikeValue(value));
            for(int i = 1;i<searchColumns.length-1;i++){//遍历中间的
                String keyTmp = searchColumns[i];
                sql.append(" "+getAliasPrefix(alias)+keyTmp+"like ? or");//最后一个
                args.add(getLikeValue(value));
            }
            String keyEnd = searchColumns[searchColumns.length-1];//最后一个
            sql.append(" "+getAliasPrefix(alias)+keyEnd+" like ?)");
            args.add(getLikeValue(value));
        }
    }

    @Override
    public void dateBefore(StringBuffer sql, String alias, String column, Object value, List<Object> args) {
        if (StringUtils.isBlank(column)) {
            throw new RuntimeException("column can not be null or blank !");
        }
        sql.append(" and "+getAliasPrefix(alias)+column+"<?");
        args.add(value);
    }

    @Override
    public void dateAfter(StringBuffer sql, String alias, String column, Object value, List<Object> args) {
        if (StringUtils.isBlank(column)) {
            throw new RuntimeException("column can not be null or blank !");
        }
        sql.append(" and "+getAliasPrefix(alias)+column+">?");
        args.add(value);
    }

    @Override
    public void order(StringBuffer sql, String alias, String sort, String order) {
        if (StringUtils.isNotBlank(sort)) {
            sql.append(" order by " + getAliasPrefix(alias) + sort + " " + order);
        }
    }

    @Override
    public void order(StringBuffer sql, String[] orderStrs) {
        if (orderStrs.length>0) {
            sql.append(" order by ");
            sql.append(StringUtils.join(orderStrs, ","));
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

    private static String getLikeValue(Object value){
        return "%"+value+"%";
    }
}
