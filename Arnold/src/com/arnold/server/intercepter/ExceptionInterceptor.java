package com.arnold.server.intercepter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.controller.BaseController;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ExceptionInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

	@Override
	public void intercept(Invocation inv) {
		BaseController baseController = (BaseController) inv.getController();
		try {
			
			inv.invoke();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			baseController.renderJson(resultMap);
			return;
		}
	}

}
