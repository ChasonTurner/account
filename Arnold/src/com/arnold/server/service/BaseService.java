package com.arnold.server.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.arnold.server.controller.BaseController;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.HttpRequestService;
import com.huntersun.tool.JSONUtil;

/**
 * @Description: service服务基类，提供一些公用的方法
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年5月27日 上午10:39:27
 */
public class BaseService {

	public static int UNKNOWN_ERROR_RETURNCCODE=-1000;//未知错误返回编码
	public static int SUCCESS=0;//
	public static final int SERVER_ERROR_RETURNCCODE=-3;
	public static String UNKNOWN_ERROR_RMSG="未知错误";
	public static final String TOKEN_ERROR_RMSG="token无效";
	public static final String SERVER_ERROR_RMSG="服务器异常";
	
	/**
	 * @Description: 向其它服务器发送请求
	 * @author Li Bangming;
	 * @date 2017年7月4日 下午7:31:22
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  Map<String,Object> requestResultPost(String url) {
		Map<String, Object> resultMap=new HashMap<String, Object> ();
		try {
			String requestStr=HttpRequestService.httpPost(url,new HashMap<String, Object> ());
			return JSONUtil.jackson2Object(requestStr,Map.class);
		} catch (Exception e) {
			resultMap.put(ConstUtils.R_MSG, SERVER_ERROR_RMSG);
			resultMap.put(ConstUtils.RETURN_CODE, SERVER_ERROR_RETURNCCODE);
			ArnoldUtils.logger.debug(e.getLocalizedMessage());
		}
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	public  Map<String,Object> requestResultPost(BaseController c,String url) {
		Map<String, Object> paras=c.getServerParas();
		try {
			String requestStr=HttpRequestService.httpPost(url,paras);
			JSONObject requestJson=new JSONObject(requestStr);
			int returnCode=requestJson.getInt(ConstUtils.RETURN_CODE);
			if(c.getReturnCode()!=UNKNOWN_ERROR_RETURNCCODE&&c.getReturnCode()!=SUCCESS){
				return JSONUtil.jackson2Object(requestStr,Map.class);
			}else{
				c.setReturnCode(returnCode);
			}
			if(returnCode>SUCCESS){
				String rmsg=requestJson.getString(ConstUtils.R_MSG);
				c.setRmsg(rmsg);
			}else if(returnCode==ConstUtils.TOKEN_ERROR){
				c.setRmsg(TOKEN_ERROR_RMSG);
			}else if(returnCode==ConstUtils.PARAM_ERROR){
				if(requestJson.isNull(ConstUtils.R_MSG)){
					c.setRmsg(ConstUtils.PARAM_ERROR_STR);
				}else{
					String rmsg=requestJson.getString(ConstUtils.R_MSG);
					c.setRmsg(rmsg);	
				}
			}
			return JSONUtil.jackson2Object(requestStr,Map.class);
		} catch (Exception e) {
			c.setRmsg(SERVER_ERROR_RMSG);
			c.setReturnCode(SERVER_ERROR_RETURNCCODE);
			System.out.println(e.getLocalizedMessage());
		}
		return new HashMap<String, Object>();
	}
	
	@SuppressWarnings("unchecked")
	public  Map<String,Object> requestResultGet(BaseController c,String url) {
		Map<String, Object> paras=c.getServerParas();
		try {
			String requestStr=HttpRequestService.httpGet(handParas(url,paras));
			JSONObject requestJson=new JSONObject(requestStr);
			int returnCode=requestJson.getInt(ConstUtils.RETURN_CODE);
			if(c.getReturnCode()!=UNKNOWN_ERROR_RETURNCCODE&&c.getReturnCode()!=SUCCESS){
				return JSONUtil.jackson2Object(requestStr,Map.class);
			}else{
				c.setReturnCode(returnCode);
			}
			if(returnCode>SUCCESS){
				String rmsg=requestJson.getString(ConstUtils.R_MSG);
				c.setRmsg(rmsg);
			}else if(returnCode==ConstUtils.TOKEN_ERROR){
				c.setRmsg(TOKEN_ERROR_RMSG);
			}else if(returnCode==ConstUtils.PARAM_ERROR){
				if(requestJson.isNull(ConstUtils.R_MSG)){
					c.setRmsg(ConstUtils.PARAM_ERROR_STR);
				}else{
					String rmsg=requestJson.getString(ConstUtils.R_MSG);
					c.setRmsg(rmsg);	
				}
			}
			return JSONUtil.jackson2Object(requestStr,Map.class);
		} catch (Exception e) {
			c.setRmsg(SERVER_ERROR_RMSG);
			c.setReturnCode(SERVER_ERROR_RETURNCCODE);
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}

	private  String handParas(String url, Map<String, Object> paras) {
		StringBuffer sb=new StringBuffer(url);
		sb.append("?");
		Set<String> keys=paras.keySet();
		Iterator<String> it=keys.iterator();
		while(it.hasNext()){
			if(sb.lastIndexOf("?")==-1){
				sb.append("?");
			}else{
				sb.append("&");
			}
			String key=it.next();
			sb.append(key);
			sb.append("=");
			sb.append(paras.get(key));
		}
		return sb.toString();
	}
	
}

