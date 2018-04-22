/** 
 * Copyright (c) 2016, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2016年12月6日上午10:14:42 
 */
package com.ares.server.service.loadReqServiceI;

import javax.jws.WebService;

@WebService(name="LoadReqServiceI")
public interface LoadReqServiceI {
	
	public String loadReq(String msg);
}
