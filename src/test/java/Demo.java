import com.jfinal.aihelper.sql.AiSqlHelperPlugin;
import com.jfinal.aihelper.sql.ISqlBuilder;
import com.jfinal.aihelper.sql.ParamMap;
import com.jfinal.aihelper.sql.SqlParam;
import com.jfinal.aihelper.sql.builer.MySqlBuilder;

import java.util.Date;

/**
 * Created by tangjing on 2016/8/31.11:25
 */
public class Demo {
    public static void main(String[] args) {
        ISqlBuilder sqlBuilder = new MySqlBuilder();
//        ISqlBuilder sqlBuilder = new OracleBuilder();
        AiSqlHelperPlugin p = new AiSqlHelperPlugin(sqlBuilder);
        p.start();
        SqlParam sp = SqlParam.create(new ParamMap().put("age",17).put("addr","北京路").put("time",new Date()));
        sp.setSelect("select t1.* where 1=1");
        sp.alias("t1");
        sp.and("age","age");
        sqlBuilder.and(sp.getSql(),"t1","name","tj",sp.getArgs());//直接使用sqlbuilder也可以
        sp.search(new String[]{"home_addr","company_addr"},"addr");
        sp.search("addr", "addr");
        sp.dateAfter("start_time","time");
        sp.dateBefore("end_time","time");
//        sp.orderAsc("create_time");
        sp.orderBy("t1.start_time,t1.age asc");
        System.out.println(sp.getFullSql());
        for(Object ags:sp.argsAry()){
            System.out.println(ags);
        }
//        System.out.println(sp.argsAry().toString());
    }

}
