package com.ares.server.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.ares.server.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;

public class ServerConfig extends JFinalConfig
{
	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants constants)
	{
		loadPropertyFile("WebConfig.txt");
		FreeMarkerRender.setProperty("classic_compatible", "true");// 如果为null则转为空值,不需要再用!处理
		boolean devMode = getPropertyToBoolean("devMode", true);
		constants.setEncoding("UTF-8");
		constants.setDevMode(devMode);
	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes routes)
	{
		//routes.add("/weixin", WeixinController.class);
	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins plugins)
	{
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		{
			dp.addFilter(new StatFilter());
			WallFilter wall = new WallFilter();
			wall.setDbType("mysql");
			dp.addFilter(wall);
			dp.setTestOnBorrow(true);
			dp.setTestOnReturn(true);
			dp.setMaxWait(20000);
		}
		plugins.add(dp);

		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		boolean showSql = getPropertyToBoolean("showSql", false);
		arp.setShowSql(showSql);
		_MappingKit.mapping(arp);
		plugins.add(arp);

		// 配置ehcache缓存框架
		plugins.add(new EhCachePlugin());
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors interceptors)
	{
		//interceptors.add(new AuthInterceptor(getProperty("poseidon.url")));
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers handlers)
	{
		// 配置druid视图
		DruidStatViewHandler dvh = new DruidStatViewHandler("/mydruid");
		handlers.add(dvh);
	}

}
