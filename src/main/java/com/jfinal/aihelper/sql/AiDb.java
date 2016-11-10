package com.jfinal.aihelper.sql;

import com.jfinal.plugin.activerecord.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/1/21.10:17
 */
public class AiDb{


    /**
     * 分页查询
     * @param sqlParam
     * @return
     */
    public static Page<Record> paginate(SqlParam sqlParam){
        return Db.paginate(sqlParam.pageNum(), sqlParam.pageSize(), sqlParam.getSelect(), sqlParam.getSqlStr(), sqlParam.argsAry());
    }

    /**
     * 普通查询
     * @param sqlParam
     * @return
     */
    public static List<Record> find(SqlParam sqlParam){
        return Db.find(sqlParam.getFullSql(), sqlParam.argsAry());
    }


    /**
     * 批量删除
     * 适用单个ID的表
     * @param cls 类
     * @param ids id数组
     * @return
     */
    public static int batchDel(Class<? extends Model> cls,Object[] ids){
        Table table = TableMapping.me().getTable(cls);
        String sql = "delete from "+table.getName()+" where "+table.getPrimaryKey()[0]+"=?";
        Object paras[][] = new Object[ids.length][1];
        for (int i = 0; i < ids.length; i++) {
            paras[i][0] = ids[i];
        }
        return Db.batch(sql, paras, ids.length).length;
    }



}
