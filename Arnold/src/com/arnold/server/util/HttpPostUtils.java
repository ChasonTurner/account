package com.arnold.server.util;

import java.util.HashMap;
import java.util.Map;


import com.huntersun.tool.HttpRequestService;
import com.huntersun.tool.JSONUtil;

/**
 * @Description: http请求工具类
 * @author Li Bangming;Email:1715592561@qq.com
 * @date 2017年6月1日 上午11:08:39
 */
public class HttpPostUtils {
	/**
	 * @Description: 发送http请求
	 * @author Li Bangming;
	 * @date 2017年8月13日 下午7:43:54
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  Map<String, Object> requestHttpPostByUrl(String url) {
		Map<String, Object> resultMap=new HashMap<String, Object> ();
		try {
			String requestStr=HttpRequestService.httpPost(url,new HashMap<String, Object> ());
			resultMap=JSONUtil.jackson2Object(requestStr,Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
