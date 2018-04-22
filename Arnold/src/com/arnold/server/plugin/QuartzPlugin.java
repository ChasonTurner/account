package com.arnold.server.plugin;

import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.jfinal.plugin.IPlugin;

public class QuartzPlugin implements IPlugin {

	private SchedulerFactory sf = null;
	private Scheduler sched = null;
	private String config = "job.properties";
	private Properties properties;

	public QuartzPlugin(String config) {
		this.config = config;
	}

	public QuartzPlugin() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean start() {
		sf = new StdSchedulerFactory();
		try {
			sched = sf.getScheduler();
		} catch (SchedulerException e) {
			new RuntimeException(e);
		}
		loadProperties();
		Enumeration enums = properties.keys();
		while (enums.hasMoreElements()) {
			String key = enums.nextElement() + "";
			if (!key.endsWith("job")) {
				continue;
			}
			String cronKey = key.substring(0, key.indexOf("job")) + "cron";
			String enable = key.substring(0, key.indexOf("job")) + "enable";
			if (isDisableJob(enable)) {
				continue;//未启用的job
			}
			String jobClassName = properties.get(key) + "";
			String jobCronExp = properties.getProperty(cronKey) + "";
			Class clazz;
			try {
				clazz = Class.forName(jobClassName);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			JobDetail job = JobBuilder.newJob(clazz)
					.withIdentity(jobClassName, jobClassName).build();
			CronTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(jobClassName, jobClassName)
					.withSchedule(CronScheduleBuilder.cronSchedule(jobCronExp))
					.build();
			try {
				sched.scheduleJob(job, trigger);
				sched.start();
			} catch (SchedulerException ee) {
				new RuntimeException(ee);
			}
		}
		return true;
	}

	private boolean isDisableJob(String enable) {
		return Boolean.valueOf(properties.get(enable) + "") == false;
	}

	private void loadProperties() {
		properties = new Properties();
		InputStream is = QuartzPlugin.class.getClassLoader()
				.getResourceAsStream(config);
		try {
			properties.load(is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean stop() {
		try {
			sched.shutdown();
		} catch (SchedulerException e) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {  
        QuartzPlugin plugin = new QuartzPlugin();  
        plugin.start();  
        System.out.println("执行成功！！！");  
  
    }  

}
