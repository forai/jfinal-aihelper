package com.jfinal.aihelper.sql;

import com.jfinal.ext.kit.DateKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tj on 2015/8/14.15:53
 */
public class ParamMap extends HashMap<String, Object> {

	private Map<String, String[]> param;

	private int pageNum = 1;

	private int pageSize = 20;

	private String sort;

	private String order;

	public ParamMap() {

	}

	public ParamMap(Map<String, String[]> param) {
		this.putAll(param);
	}

	@Override
	public Object get(Object key) {
		Object o = super.get(key);
		if(o instanceof String[]){
			String[] ary =  (String[])o;
			return ary[0];
		}else {
			return super.get(key);
		}
	}

	public String getStr(String key) {
		return this.containsKey(key) ? this.get(key).toString() : null;
	}

	public Date getDate(String key) {
		return getDate(key, null);
	}

	public Date getDate(String key, Date defaultValue) {
		Object value = getStr(key);
		try {
			return DateUtils.parseDate(value.toString(), DateKit.dateFormat, DateKit.timeFormat);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ActiveRecordException("日期参数解析错误", e);
		}
	}

	public Integer getInt(String key, Integer defVal) {
		return this.containsKey(key) ? Integer.parseInt(this.getStr(key)) : defVal;
//		if (containsKey(key)) {
//			return Integer.parseInt(this.getStr(key));
//		}
//		return defVal;
	}

	public Integer getInt(String key) {
		return Integer.parseInt(get(key).toString());
	}

	/**
	 * 判断是否是正数
	 *
	 * @param key
	 * @return
	 */
	public boolean isPositive(String key) {
		try {
			return getInt(key, -1) >= 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否有效
	 *
	 * @param key
	 * @return
	 */
	public boolean isValidStr(String key) {
		try {
			return StrKit.notBlank(getStr(key));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isValid(String key) {
		if (key == null) {
			return false;
		}
		Object o = get(key);
		if (o == null) {
			return false;
		}
		if (StrKit.isBlank(o.toString())) {
			return false;
		}
		return true;
	}

	/**
	 * 获得页码
	 *
	 * @return
	 */
	public int pageNum() {
		return getPageNum();
	}

	/**
	 * 每页条数
	 *
	 * @return
	 */
	public int pageSize() {
		return getPageSize();
	}

	/**
	 * 排序字符串
	 *
	 * @return
	 */
	public String orderStr() {
		return orderStr("");
	}

	/**
	 * 排序字符串
	 *
	 * @param tableAlias
	 * @return
	 */
	public String orderStr(String tableAlias) {
		if (StrKit.notBlank(tableAlias)) {
			tableAlias += ".";
		}
		if (StrKit.isBlank(getSort())) {
			return "";
		}
		return " order by " + tableAlias + getSort() + " " + getOrder();
	}

	@Override
	public ParamMap put(String key, Object value) {
		if (value instanceof Date) {
			value = DateFormatUtils.format((Date) value, DateKit.timeFormat);
		}
		super.put(key, value);
		return this;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public ParamMap setSort(String sort) {
		this.sort = sort;
		return this;
	}

	public String getOrder() {
		return order;
	}

	public ParamMap setOrder(String order) {
		this.order = order;
		return this;
	}

	/**
	 * 降序排序
	 *
	 * @return
	 */
	public ParamMap setOrderDesc() {
		setOrder("DESC");
		return this;
	}

	/**
	 * 升序排序
	 *
	 * @return
	 */
	public ParamMap setOrderAsc() {
		setOrder("ASC");
		return this;
	}
}
