package com.arnold.server.bean.request;

import java.io.Serializable;

/**  
 * @description  删除贫困人员就业信息请求
 * @author luzy
 * @date 2017年12月21日
 */
public class RequestDelPostVillagerRalationType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2710751599537724040L;
	
	/** 关系表Id */
	private String happenId;

	public String getHappenId() {
		return happenId;
	}

	public void setHappenId(String happenId) {
		this.happenId = happenId;
	}

}
