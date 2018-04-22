package com.ares.server.exception;

public class WeixinException extends RuntimeException
{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8689921381272351289L;

	public WeixinException(String msg)
	{
		super(msg);
	}
}
