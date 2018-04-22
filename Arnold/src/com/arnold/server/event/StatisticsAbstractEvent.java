package com.arnold.server.event;

import com.pallas.statistics.StatisticsBean;
import com.pallas.statistics.Event.StatisticsEvent;
abstract
public class StatisticsAbstractEvent extends StatisticsEvent {
	private StatisticsBean statisticsBean;

	public void setStatisticsBean(StatisticsBean statisticsBean) {
		this.statisticsBean = statisticsBean;
	}

	@Override
	public StatisticsBean getStatisticsBean() {
		return statisticsBean;
	}

}
