package com.jfinal.aihelper.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tj on 2016/1/9.11:27
 */
public class SqlParam{

    private StringBuffer sql;
    private ParamMap paramMap;
    private List<Object> args;
    private ISqlBuilder sqlBuilder;
    /**
     *表的别名 alias方法的时候设置，静态变量，不设置就是上一次设置的值
     */
    private static String alias;
    /**
     * 如果是paginate，这个属性有用 主要是我用了2.1以后，2.2马上发布，我append到sql里面的select要独立出来
     */
    private String select;


    /**
     * 初始化新实例
     * @param paramMap
     * @return
     */
    public static SqlParam create(ParamMap paramMap){
        SqlParam sqlParam = new SqlParam();
        sqlParam.setParamMap(paramMap);
        sqlParam.setSql(new StringBuffer(300));
        sqlParam.setArgs(new ArrayList<Object>());
        alias = null;
        return sqlParam;
    }

    /**
     * 新实例
     * @param paramMap
     * @param sql
     * @return
     */
    public static SqlParam create(ParamMap paramMap, StringBuffer sql){
        SqlParam sqlParam = new SqlParam();
        sqlParam.setParamMap(paramMap);
        sqlParam.setArgs(new ArrayList<Object>());
        sqlParam.setSql(sql);
        alias = null;
        return sqlParam;
    }

    /**
     * 新实例
     * @param paramMap
     * @param sql
     * @return
     */
    public static SqlParam create(ParamMap paramMap, StringBuffer sql, List<Object> args){
        SqlParam sqlParam = new SqlParam();
        sqlParam.setParamMap(paramMap);
        sqlParam.setSql(sql);
        sqlParam.setArgs(args);
        alias = null;
        return sqlParam;
    }

    /**
     *添加sql
     * @param sql
     */
    public void append(Object sql){
        getSql().append(sql);
    }

    /**
     * 新增参数
     * @param obj
     */
    public void add(Object obj){
        getArgs().add(obj);
    }

    /**
     * 页码
     * @return
     */
    public int pageNum(){
        return getParamMap().pageNum();
    }

    /**
     * 每页数
     * @return
     */
    public int pageSize(){
        return getParamMap().pageSize();
    }

    /**
     * 返回sql对象
     * @return
     */
    public String getSqlStr(){
        return getSql().toString();
    }

    public String getFullSql(){
        return getSelect()+" "+getSqlStr();
    }

    /**
     * 返回参数数组
     * @return
     */
    public Object[] argsAry(){
        return getArgs().toArray();
    }

    /**
     * 设置别名
     * 静态变量，不设置就是上一次设置的值
     * @param alias
     * @return
     */
    public SqlParam alias(String alias){
        this.alias = alias;
        return this;
    }

//    public void and(String key){
//        getSqlBuilder().and(getSql(),key,getParamMap(),getArgs(),alias);
//    }
//
//    public void and(String[] keys){
//        getSqlBuilder().and(getSql(),keys,getParamMap(),getArgs(),alias);
//    }
//
//    public void search(String key, String[] searchColumns){
//        getSqlBuilder().search(getSql(),alias,key,getParamMap(),getArgs(),searchColumns);
//    }

    /**
     * 根据sort降序
     * @param sort
     */
    public void orderDesc(String sort){
        order(sort,"DESC");
    }

    /**
     * 根据sort升序
     * @param sort
     */
    public void orderAsc(String sort){
        order(sort,"ASC");
    }

    /**
     * 排序
     * @param sort
     * @param order
     */
    public void order(String sort, String order){
        getSqlBuilder().order(getSql(),alias,sort,order);
    }

    public void order(){
        getSqlBuilder().order(getSql(),alias,getParamMap().getSort(),getParamMap().getOrder());
    }

    public void orderBy(String orderByStr){
        append(" "+orderByStr);
    }

    public void dateBefore(String key){
        getSqlBuilder().dateBefore(getSql(),alias,key,getParamMap(),getArgs());
    }

    public void dateAfter(String key){
        getSqlBuilder().dateAfter(getSql(),alias,key,getParamMap(),getArgs());
    }


    public StringBuffer getSql() {
        return sql;
    }

    public void setSql(StringBuffer sql) {
        this.sql = sql;
    }

    public ParamMap getParamMap() {
        return paramMap;
    }

    public void setParamMap(ParamMap paramMap) {
        this.paramMap = paramMap;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }

    public ISqlBuilder getSqlBuilder() {
        if(sqlBuilder==null){
            use();
        }
        return sqlBuilder;
    }

    public SqlParam use(String configName){
        setSqlBuilder(SqlBuilderManager.me().getSqlBuilder(configName));
        return this;
    }

    public SqlParam use(){
        return use(SqlBuilderManager.MAIN_CONFIG_NAME);
    }

    public void setSqlBuilder(ISqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }


    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }
}
