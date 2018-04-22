package com.arnold.server.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.arnold.server.controller.CallbackController;
import com.arnold.server.controller.api.ArnoldController;
import com.arnold.server.controller.api.ArnoldController1;
import com.arnold.server.controller.api.ArnoldController2;
import com.arnold.server.controller.api.CountController;
import com.arnold.server.controller.api.FamilyController;
import com.arnold.server.controller.api.HappenController;
import com.arnold.server.controller.api.MaintainController;
import com.arnold.server.controller.api.ReportController;
import com.arnold.server.controller.api.TrainController;
import com.arnold.server.event.StatisticsPoorIncomeEvent;
import com.arnold.server.listener.StatisticsPovertyIncomeListener;
import com.arnold.server.model.Accredited;
import com.arnold.server.model.Area;
import com.arnold.server.model.BreedIncome;
import com.arnold.server.model.BussinessOrg;
import com.arnold.server.model.BussinessType;
import com.arnold.server.model.CultivationIncome;
import com.arnold.server.model.Education;
import com.arnold.server.model.EducationParticipant;
import com.arnold.server.model.Enterprise;
import com.arnold.server.model.Family;
import com.arnold.server.model.FamilyPovertyHappen;
import com.arnold.server.model.FamilyShiftIncomeHappen;
import com.arnold.server.model.FamilyStatus;
import com.arnold.server.model.Group;
import com.arnold.server.model.Industry;
import com.arnold.server.model.Leader;
import com.arnold.server.model.Member;
import com.arnold.server.model.MemberOrgHappen;
import com.arnold.server.model.OrgMemberRelation;
import com.arnold.server.model.Post;
import com.arnold.server.model.PovertyCount;
import com.arnold.server.model.Project;
import com.arnold.server.model.ProjectHelp;
import com.arnold.server.model.ProjectRegionRelation;
import com.arnold.server.model.PublicService;
import com.arnold.server.model.RegionIndustryHappen;
import com.arnold.server.model.RegionIndustryIncomeHappen;
import com.arnold.server.model.RegionInvestHappen;
import com.arnold.server.model.Train;
import com.arnold.server.model.TrainHasJob;
import com.arnold.server.model.TrainParticipant;
import com.arnold.server.model.TrainSpeaker;
import com.arnold.server.model.Underwriter;
import com.arnold.server.model.ValigerEduHappen;
import com.arnold.server.model.ValigerTrainHappen;
import com.arnold.server.model.Villager;
import com.arnold.server.model.VillagerFamilyHappen;
import com.arnold.server.model.VillagerIndustryIncomeHappen;
import com.arnold.server.model.VillagerPostHappen;
import com.arnold.server.model.VillagerPostIncomeHappen;
import com.arnold.server.model.VisitCount;
import com.arnold.server.model._MappingKit;
import com.arnold.server.plugin.ArnoldPlugin;
import com.arnold.server.plugin.QuartzPlugin;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.pallas.listener.core.Core;
import com.pallas.listener.core.InitConfig;
import com.pallas.listener.core.InitParam;
import com.pallas.listener.core.RegisterListenerManager;
import com.pallas.log.LogContainer;
import com.pallas.log.LogInitConfig;
import com.pallas.statistics.StatisticsListenerManager;

/**
 * 
 * @ClassName: ServerConfig
 * @Description: 服务器总配置文件
 * @author: YangJun
 * @date: 2016年4月12日 上午11:21:10
 */
public class ServerConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants constants) {
		loadPropertyFile("WebConfig.properties");
		boolean devMode = getPropertyToBoolean("devMode", false);
		constants.setDevMode(devMode);
	    ConsConfig.init();
	    LogContainer.init(new LogInitConfig() {

			@Override
			public String getZtfsServiceHost() {
				return ConsConfig.ZTLS_HOST;
			}

			@Override
			public String getDeployId() {
				return ConsConfig.PROJECT_NAME;
			}
		});
		Core.init(new InitConfig() {
			
			@Override
			public void registerListenerManager(RegisterListenerManager manager) {
				InitParam initParam=new InitParam() {
					
					@Override
					public int getMaxThreadNumber() {
						return 2000;
					}
					
					@Override
					public int getMaxTaskNumber() {
						return 2000;
					}
					
					@Override
					public int getMaxBufferTaskNumber() {
						return 2000;
					}
				};
				manager.registerListenerManager(initParam, StatisticsListenerManager.class);
			}
		});
		StatisticsListenerManager listenerManager=(StatisticsListenerManager) Core.getListenerManager(StatisticsListenerManager.class);
		listenerManager.addListener(StatisticsPoorIncomeEvent.class, new StatisticsPovertyIncomeListener());
	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes routes) {
		routes.add("/family", FamilyController.class);
		routes.add("/count", CountController.class);
		routes.add("/callback", CallbackController.class);
		routes.add("/happen", HappenController.class);
		routes.add("/train", TrainController.class);
		routes.add("/arnold", ArnoldController.class);
		routes.add("/arnold1", ArnoldController1.class);
		routes.add("/arnold2", ArnoldController2.class);
		routes.add("/data", MaintainController.class);
		
		routes.add("/report", ReportController.class);//报表路由

	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins plugins) {
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		{
			dp.addFilter(new StatFilter());
			WallFilter wall = new WallFilter();
			wall.setDbType("mysql");
			dp.addFilter(wall);
			dp.setTestOnBorrow(true);
			dp.setTestOnReturn(true);
			dp.setMaxWait(50000);
		}
		plugins.add(dp);
		// 配置ActiveRecord插件
		// ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		boolean showSql = getPropertyToBoolean("showSql", false);
		arp.setShowSql(showSql);
		_MappingKit.mapping(arp);
		plugins.add(arp);

		// 配置ehcache缓存框架
		//plugins.add(new EhCachePlugin());
		//配置本项目初始化插件
		plugins.add(new ArnoldPlugin());
		
		QuartzPlugin quartz = new QuartzPlugin("job.properties");
        plugins.add(quartz);
		
		
		addMapping(arp);
		
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors interceptors) {
		//interceptors.add(new ExceptionInterceptor());
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers handlers) {
		// 配置druid视图
		DruidStatViewHandler dvh = new DruidStatViewHandler("/mydruid");
		handlers.add(dvh);
	}
	
	public void addMapping(ActiveRecordPlugin arp){
		
		arp.addMapping("tb_accredited", "id", Accredited.class);
		arp.addMapping("tb_area", "id", Area.class);
		arp.addMapping("tb_breed_income", "id", BreedIncome.class);
		arp.addMapping("tb_bussiness_org", "id", BussinessOrg.class);
		arp.addMapping("tb_bussiness_type", "id", BussinessType.class);
		arp.addMapping("tb_cultivation_income", "id", CultivationIncome.class);
		arp.addMapping("tb_education", "id", Education.class);
		arp.addMapping("tb_education_participant", "id", EducationParticipant.class);
		arp.addMapping("tb_enterprise", "id", Enterprise.class);
		arp.addMapping("tb_family", "id", Family.class);
		arp.addMapping("tb_family_poverty_happen", "id", FamilyPovertyHappen.class);
		arp.addMapping("tb_family_shift_income_happen", "id", FamilyShiftIncomeHappen.class);
		arp.addMapping("tb_family_status", "id", FamilyStatus.class);
		arp.addMapping("tb_group", "id", Group.class);
		arp.addMapping("tb_industry", "id", Industry.class);
		arp.addMapping("tb_leader", "id", Leader.class);
		arp.addMapping("tb_member", "id", Member.class);
		arp.addMapping("tb_member_org_happen", "id", MemberOrgHappen.class);
		arp.addMapping("tb_org_member_relation", "id", OrgMemberRelation.class);
		arp.addMapping("tb_post", "id", Post.class);
		arp.addMapping("tb_poverty_count", "id", PovertyCount.class);
		arp.addMapping("tb_project", "id", Project.class);
		arp.addMapping("tb_project", "id", PublicService.class);
		arp.addMapping("tb_project", "id", ProjectHelp.class);
		arp.addMapping("tb_project_region_relation", "id", ProjectRegionRelation.class);
//		arp.addMapping("tb_public_service", "id", PublicService.class);
		arp.addMapping("tb_region_industry_happen", "id", RegionIndustryHappen.class);
		arp.addMapping("tb_region_industry_income_happen", "id", RegionIndustryIncomeHappen.class);
		arp.addMapping("tb_region_invest_happen", "id", RegionInvestHappen.class);
		arp.addMapping("tb_train", "id", Train.class);
		arp.addMapping("tb_train_speaker", "id", TrainSpeaker.class);
		arp.addMapping("tb_train_participant", "id", TrainParticipant.class);
		arp.addMapping("tb_train_has_job", "id", TrainHasJob.class);
		arp.addMapping("tb_underwriter", "id", Underwriter.class);
		arp.addMapping("tb_valiger_edu_happen", "id", ValigerEduHappen.class);
		arp.addMapping("tb_valiger_train_happen", "id", ValigerTrainHappen.class);
		arp.addMapping("tb_villager", "id", Villager.class);
		arp.addMapping("tb_villager_family_happen", "id", VillagerFamilyHappen.class);
		arp.addMapping("tb_villager_industry_income_happen", "id", VillagerIndustryIncomeHappen.class);
		arp.addMapping("tb_villager_post_happen", "id", VillagerPostHappen.class);
		arp.addMapping("tb_villager_post_income_happen", "id", VillagerPostIncomeHappen.class);
		arp.addMapping("tb_visit_count", "id", VisitCount.class);
		
	}
	public void beforeJFinalStop(){
		Core.stop();
	}
}
