package com.arnold.server.bean;

import java.util.Map;

import com.pallas.statistics.StatisticsBean;

public class StatisticsCallbackBean extends StatisticsBean {
	private Map<String, Object> callbackParas;

	public Map<String, Object> getCallbackParas() {
		return callbackParas;
	}

	public void setCallbackParas(Map<String, Object> callbackParas) {
		this.callbackParas = callbackParas;
	}
}
