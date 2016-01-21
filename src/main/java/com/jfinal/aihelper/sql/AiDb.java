package com.jfinal.aihelper.sql;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * Created by Administrator on 2016/1/21.10:17
 */
public class AiDb {


    /**
     * 分页查询
     * @param sqlParam
     * @return
     */
    public static Page<Record> paginate(SqlParam sqlParam){
        return Db.paginate(sqlParam.pageNum(), sqlParam.pageSize(), sqlParam.getSelect(), sqlParam.getSqlStr(), sqlParam.argsAry());
    }
}
