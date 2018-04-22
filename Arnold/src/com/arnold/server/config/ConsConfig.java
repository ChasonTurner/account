package com.arnold.server.config;

import com.jfinal.kit.Prop;

/**
 * @Description: 常量配置类
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年6月6日 上午11:24:33
 */
public class ConsConfig {
	private ConsConfig(){}
	
	public static String PROJECT_NAME;
	public static boolean IS_SAVE_LOG ;//是否保存日志
	/**
	 * 外部服务地址常量
	 */
	public static String POSEIDON_HOST;
	public static String FILE_HOST;
	public static String ZTLS_HOST;
	public static String APP_ID;
	public static String PROJECT_ID;

	/**
	 * @Description: 初始化
	 * @author Li Bangming;
	 * @date 2017年6月6日 上午11:26:47
	 */
	public static void init(){
		Prop prop = new Prop("constant.properties");
		IS_SAVE_LOG = true;//是否保存日志
		POSEIDON_HOST = prop.get("POSEIDON_HOST").trim();
		FILE_HOST = prop.get("FILE_HOST").trim();
		ZTLS_HOST=prop.get("ZTLS_HOST").trim();
		PROJECT_NAME="Arnold";
		APP_ID = prop.get("APP_ID").trim();
		PROJECT_ID=prop.get("PROJECT_ID").trim();
	}
}

