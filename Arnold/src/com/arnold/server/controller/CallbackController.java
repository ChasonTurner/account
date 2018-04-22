package com.arnold.server.controller;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.bean.StatisticsCallbackBean;
import com.arnold.server.event.StatisticsAbstractEvent;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.JSONUtil;
import com.huntersun.tool.Utils;
import com.pallas.listener.core.Core;
import com.pallas.statistics.StatisticsListenerManager;


public class CallbackController extends BaseController{
	//统计回调
	public void statistics_callback() throws Exception{
		
		String eventClassNameSource = this.getPara(0);
		if(Utils.isBlankOrEmpty(eventClassNameSource)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "eventClassName不能为空！");
			renderJson(resultMap);
			return;
		}
		String eventClassName=eventClassNameSource.replace("@", ".");
		StatisticsCallbackBean statistcsBean=new StatisticsCallbackBean();
		StatisticsListenerManager listenerManager = (StatisticsListenerManager) Core
				.getListenerManager(StatisticsListenerManager.class);
		Class<?> clzz=Class.forName(eventClassName);
		StatisticsAbstractEvent event=(StatisticsAbstractEvent)(clzz.newInstance());
		@SuppressWarnings("unchecked")
		Map<String, Object> callbackParas=JSONUtil.jackson2Object(getPara("callbackParas"), Map.class);
		statistcsBean.setCallbackParas(callbackParas);
		event.setStatisticsBean(statistcsBean);
		listenerManager.publishEvent(event);
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, "操作成功");
		renderJson(resultMap);
	}
}
