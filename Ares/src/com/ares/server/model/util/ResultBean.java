package com.ares.server.model.util;

public class ResultBean {
	private Object data;
	
	private String message;
	
	private String except;
	
	private int status;

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getExcept()
	{
		return except;
	}

	public void setExcept(String except)
	{
		this.except = except;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
