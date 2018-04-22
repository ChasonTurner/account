package com.arnold.server.controller.api;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.arnoldService.StatisticsService;
import com.arnold.server.controller.BaseController;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

/**
 * @Description: 统计Controller
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年7月13日 下午2:55:40
 */
public class CountController extends BaseController{
	
	StatisticsService statisticsService = new StatisticsService();

	/**
	 * @Description: 统计贫困户
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void count_poverty(){
		
	}

	/**
	 * @Description: 统计走访
	 * @author Li Bangming;
	 * @date 2017年7月11日 上午9:17:53
	 */
	public void  count_visit(){

	}
	
	/**
	 * 贫困户收入统计
	 */
	public void statistics_family_income(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String year = !Utils.isBlankOrEmpty(getPara("year"))?
				getPara("year"):String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String familyId = getPara("familyId");//家庭ID
		String groupId = getPara("groupId");//组Id
		String hamletId = getPara("hamletId");//村Id
		String countryId = getPara("countryId");//乡Id
		
		//未指定信息，默认全部
//		if (Utils.isBlankOrEmpty(familyId) && Utils.isBlankOrEmpty(groupId) 
//				&& Utils.isBlankOrEmpty(hamletId) && Utils.isBlankOrEmpty(countryId)) {
//			resultMap.clear();
//			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
//			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
//			renderJson(resultMap);
//			return;
//		}
		
		resultMap = statisticsService.statisticsFamilyIncome(year, familyId, groupId, hamletId, countryId);
		
		renderJson(resultMap);
		
		return;
	}
	
}

