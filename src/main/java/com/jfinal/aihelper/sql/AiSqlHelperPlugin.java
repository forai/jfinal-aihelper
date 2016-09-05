package com.jfinal.aihelper.sql;

import com.jfinal.plugin.IPlugin;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by tangjing on 2016/8/31.9:34
 */
public class AiSqlHelperPlugin implements IPlugin {

    private String config;
    private ISqlBuilder sqlBuilder;

    public AiSqlHelperPlugin(ISqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
        this.config = SqlBuilderManager.MAIN_CONFIG_NAME;
    }

    public AiSqlHelperPlugin(String config, ISqlBuilder sqlBuilder) {
        this.config = config;
        this.sqlBuilder = sqlBuilder;
    }

    @Override
    public boolean start() {
        if (StringUtils.isBlank(config)) {
            config = SqlBuilderManager.MAIN_CONFIG_NAME;
        }
        if(sqlBuilder==null){
            throw new RuntimeException("sqlBuilder can not be null !");
        }
        SqlBuilderManager.me().addSqlBuilder(config,sqlBuilder);
        return true;
    }

    @Override
    public boolean stop() {
        return false;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public ISqlBuilder getSqlBuilder() {
        return sqlBuilder;
    }

    public void setSqlBuilder(ISqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }
}
