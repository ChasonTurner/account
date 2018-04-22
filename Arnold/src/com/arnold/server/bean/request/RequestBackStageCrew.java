package com.arnold.server.bean.request;

import java.io.Serializable;

/**  
 * @description  后台人员查询请求bean
 * @author luzy
 * @date 2017年12月20日
 */
public class RequestBackStageCrew implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3737678662023968637L;

	/** 登录时间 */
	private String loginTime;
	
	/** 关键字 */
	private String keyWord;

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
