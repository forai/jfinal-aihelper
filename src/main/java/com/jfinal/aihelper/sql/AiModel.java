package com.jfinal.aihelper.sql;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by tangjing on 2016/1/21.10:22
 */
public abstract class AiModel<M extends AiModel<M>> extends Model<M> {

    public Page<M> paginate(SqlParam sqlParam){
        return super.paginate(sqlParam.pageNum(), sqlParam.pageSize(), sqlParam.getSelect(), sqlParam.getSqlStr(), sqlParam.argsAry());
    }

    public List<M> find(SqlParam sqlParam){
        return super.find(sqlParam.getFullSql(), sqlParam.argsAry());
    }
}
