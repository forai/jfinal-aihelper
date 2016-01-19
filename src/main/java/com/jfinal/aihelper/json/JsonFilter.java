package com.jfinal.aihelper.json;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * Created by tj on 2016/1/12.10:47
 * 自定义的过滤对象属性的类
 * fastjson搞了N久，还是有问题。
 * 还是用这个吧....
 */
public class JsonFilter {

    /**
     * 过滤list里面的对象，留下指定字段。输出json的时候进行过滤用
     * @param list
     * @param columns ,分隔
     * @return
     */
    public static List keep(List list, String columns){
        String[] columnAry = columns.split(",");
        for(Object o:list){
            if(o instanceof Record){
                ((Record) o).keep(columnAry);
            }
            if(o instanceof Model){
                ((Model) o).keep(columnAry);
            }
        }
        return list;
    }

    public static Record keep(Record r,String columns){
        if(r!=null){
            r.keep(columns.split(","));
        }
        return r;
    }

    public static Model keep(Model r,String columns){
        if(r!=null){
            r.keep(columns.split(","));
        }
        return r;
    }

}
