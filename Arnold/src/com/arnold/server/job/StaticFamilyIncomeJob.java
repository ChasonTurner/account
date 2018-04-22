package com.arnold.server.job;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.arnold.server.arnoldService.StatisticsService;
import com.huntersun.tool.ConstUtils;

public class StaticFamilyIncomeJob implements Job {
	
	StatisticsService statisticsService = new StatisticsService();

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		long startTime = System.nanoTime();
		System.out.println("do start:"+startTime);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = statisticsService.statisticsFamilyIncome(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)), 
				null, null, null, null);
		long endTime = System.nanoTime();
		System.out.println(((endTime-startTime)/1000000000)+"s == "
				+endTime+"num of deal is "+resultMap.get(ConstUtils.R_MODEL));
	}

}
