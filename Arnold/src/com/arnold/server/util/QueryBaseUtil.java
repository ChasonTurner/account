package com.arnold.server.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.util.StringUtils;

public class QueryBaseUtil implements Serializable{
	private static final long serialVersionUID = 4553330260946335091L;
	private String key;
	private String[] keyFiled;
	private List<QueryUtil> queryUtils;
	private List<Object> param=new ArrayList<>();
	
	public QueryBaseUtil( List<QueryUtil> queryUtils,String key, String... keyFiled) {
		super();
		this.key = key;
		this.keyFiled = keyFiled;
		this.queryUtils = queryUtils;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String[] getKeyFiled() {
		return keyFiled;
	}
	public void setKeyFiled(String[] keyFiled) {
		this.keyFiled = keyFiled;
	}
	public List<QueryUtil> getQueryUtils() {
		return queryUtils;
	}
	public void setQueryUtils(List<QueryUtil> queryUtils) {
		this.queryUtils = queryUtils;
	} 
	
	public String getSpliceSql(){
		StringBuffer sql=new StringBuffer();
		//查询条件拼接，占位符方式
		if(queryUtils!=null&&!queryUtils.isEmpty()){
			for (QueryUtil query : queryUtils) {
				if(!StringUtils.isEmpty(query.getField())&&query.getValue()!=null){
					sql.append(" ").append(query.getJoiner()).append(" ").append(query.getField()).append(query.getOperator()).append("? ");
					param.add(query.getValue());
				}
			}
		}
		//模糊关键词查询
		if(keyFiled!=null&&keyFiled.length>0&&!StringUtils.isEmpty(key)){
			sql.append(" and (");
			for (int i = 0; i < keyFiled.length; i++) {
				sql.append(" "+keyFiled[i]).append(" like '%").append(key).append("%' ");
				if(i!=keyFiled.length-1){
					sql.append(" or ");
				}
			}
			sql.append(" )");
		}
		return sql.toString();
	}
	
	public List<Object> getParam(){
		return param;
	}

}
