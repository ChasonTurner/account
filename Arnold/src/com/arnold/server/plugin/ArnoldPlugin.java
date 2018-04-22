package com.arnold.server.plugin;

import com.jfinal.plugin.IPlugin;

/**
 * @Description: 本项目的plugin插件
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年7月9日 上午7:43:37
 */
public class ArnoldPlugin  implements IPlugin{

	public boolean start() {
		
		return true;
	}

	public boolean stop() {
		return true;
	}

}

