package com.arnold.server.listener;

import java.util.Map;

import com.arnold.server.bean.StatisticsCallbackBean;
import com.arnold.server.model.StatisticsPovertyIncome;
import com.pallas.statistics.Event.StatisticsEvent;
import com.pallas.statistics.listener.StatisticsListener;

public class StatisticsPovertyIncomeListener extends StatisticsListener{

	@Override
	public void statisticsListener(StatisticsEvent event) {
		StatisticsCallbackBean bean=(StatisticsCallbackBean) event.getStatisticsBean();
		Map<String, Object> map=bean.getCallbackParas();
		StatisticsPovertyIncome income=StatisticsPovertyIncome.dao.findById(map.get("area"));
		if(income==null){
			income=new StatisticsPovertyIncome();
			income.setId((String) map.get("area"));
			income.setTotalIncome((double) map.get("income"));
			income.setYear((int) map.get("year"));
			income.save();
		}else{
			income.setTotalIncome((double) map.get("income"));
			income.setYear((int) map.get("year"));
			income.update();
		}
	}

}
