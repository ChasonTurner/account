package com.arnold.server.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.arnoldService.MaintainService;
import com.arnold.server.controller.BaseController;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

/**
 * @Description: 数据维护Controller
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年6月21日 上午9:59:31
 */
public class MaintainController extends BaseController {
	/**
	 * 
	 * @Description: 从poseidon更新家庭编码number
	 * @author Li Bangming;
	 * @date 2017年6月21日 上午10:08:27
	 */
	public void update_family_number(){
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MaintainService maintainService=new MaintainService();
		maintainService.updateFamliyNumber();
		resultMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		this.renderJson(resultMap);
		return;
	}
	/**
	 * 
	 * @Description: 从创建后台人员
	 * @author Li Bangming;
	 * @date 2017年6月21日 上午10:08:27
	 */
	public void create_back_user(){
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MaintainService maintainService=new MaintainService();
		maintainService.createBackUser();
		resultMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		this.renderJson(resultMap);
		return;
	}
	/**
	 * 
	 * @Description: 从创建后台人员
	 * @author Li Bangming;
	 * @date 2017年6月21日 上午10:08:27
	 */
	public void create_back_user_by_phone(){
		String phone=this.getPara("phone");
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(Utils.isBlankOrEmpty(phone)){
			resultMap.put(ConstUtils.RETURN_CODE,ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			this.renderJson(resultMap);
			return;
		}
		if(!ArnoldUtils.isMobileNO(phone)){
			resultMap.put(ConstUtils.RETURN_CODE,ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			this.renderJson(resultMap);
			return;
		}
		MaintainService maintainService=new MaintainService();
		resultMap=maintainService.createBackUserByPhone(phone);
		this.renderJson(resultMap);
		return;
	}

}

