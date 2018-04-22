/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月7日下午5:11:36 
 */
package com.ares.server.exception;

public class AccountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5843880070486546433L;

	public AccountException(String msg)
	{
		super(msg);
	}
}
