package com.ares.server.interceptor;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ares.server.utils.CoreConstant;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.HttpRequestService;
import com.huntersun.tool.exception.HttpRequestException;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 授权验证中心
* @ClassName: AuthInterceptor 
* @Description:
* @author wanda-huntersun 
* @date 2016年6月27日 上午9:58:17 
*
 */
public class AuthInterceptor implements Interceptor
{
	private final String poseidonUrl;
	
	public AuthInterceptor(String poseidonUrl)
	{
		this.poseidonUrl = poseidonUrl;
	}
	
	@Override
	public void intercept(Invocation inv)
	{
		Controller controller = inv.getController();
		
		String userId = controller.getPara("userId");
		String token = controller.getPara("token");
		String appId = controller.getPara("appId");
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("userId", userId);
		params.put("token", token);
		params.put("appId", appId);
		
		String result;
		
		try
		{
			result = HttpRequestService.httpPost(poseidonUrl+"/auth/check_token", params);
		} catch (HttpRequestException e)
		{
			result = "{rc:-3}";
			e.printStackTrace();
		}
		
		params.clear();
		
		JSONObject json = JSONObject.parseObject(result);
		
		if(0 == json.getInteger("rc"))
		{
			inv.invoke();
			return;
		}else if(-1 == json.getInteger("rc"))
		{
			params.put(ConstUtils.RETURN_CODE, ConstUtils.TOKEN_ERROR);
		}else if(-2 == json.getInteger("rc"))
		{
			params.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
		}else if(-3 == json.getInteger("rc"))
		{
			params.put(ConstUtils.RETURN_CODE, CoreConstant.POSEIDON_CONNECTION_OUT);
		}
		
		controller.renderJson(params);
	}

}
