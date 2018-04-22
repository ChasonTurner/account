package com.arnold.server.util;

import java.io.Serializable;

import com.alibaba.druid.util.StringUtils;
/**
 * <b>Title:</b>查询辅助工具类<br>
 * <b>Description:</b>查询辅助工具类<br>
 * <b>Copyright:</b>Copyright (c) 2017  <br>
 * <b>Company:</b>贵州趣租网络科技有限公司<br>
 * @author 葛传艺 QQ:876292931
 * @version 1.0.0 
 */
public class QueryUtil implements Serializable{
	private static final long serialVersionUID = 5956302908469834409L;
	/**字段*/
	private String field;
	/**值*/
	private Object value;
	/**查询条件如:>  <  >=   =  like 等，当为like时，应做特殊处理 */
	private String operator;
	/**链接条件 and or 默认为and */
	private String joiner="and";
	
	public QueryUtil(String field, Object value, String operator) {
		super();
		this.field = field;
		this.value = value;
		if(value!=null&& value instanceof String){
		  if(StringUtils.isEmpty(value.toString())){
			  this.value=null;
		  }
		}
		this.operator = operator;
		this.joiner="and";
	}
	
	public QueryUtil(String field, Object value, String operator,String joiner) {
		super();
		this.field = field;
		this.value = value;
		this.operator = operator;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getJoiner() {
		return joiner;
	}
	public void setJoiner(String joiner) {
		this.joiner = joiner;
	}
}
