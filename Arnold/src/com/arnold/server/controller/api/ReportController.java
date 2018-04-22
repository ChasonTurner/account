package com.arnold.server.controller.api;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.arnoldService.ReportService;
import com.arnold.server.bean.request.RequestReportFamilyIncome;
import com.arnold.server.controller.BaseController;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

/**
 * @author Luzy
 */
public class ReportController extends BaseController {
	
	ReportService reportService = new ReportService();
	
	Map<String, Object> checkMap = new HashMap<String, Object>();
	
	/**
	 * 贫困户一表一户信息
	 */
	public void report_family_info(){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String familyId = getPara("familyId");
		
		Map<String,String> chooseParamMap = new HashMap<String, String>();
		chooseParamMap.put("typeId", getPara("typeId"));
//		chooseParamMap.put("typeId", "529c33ce-3220-4814-b1f7-e4c96e05fd37");//香猪
		chooseParamMap.put("jobCategoryIds", getPara("jobCategoryIds"));
		//外出务工、帮扶就业
//		chooseParamMap.put("jobCategoryIds", "f3ce92b2-8e2d-4687-acff-bf1d201f57cd','b97b3358-dca5-4251-9216-8242de81469f");
		//默认查询当前年度
		chooseParamMap.put("year", !Utils.isBlankOrEmpty(getPara("year"))?getPara("year"):
			String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		
		//参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = reportService.reportFamilyInfo(familyId, chooseParamMap);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 贫困户家庭收入
	 */
	public void report_family_income_info(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		RequestReportFamilyIncome request = getRequestBean(RequestReportFamilyIncome.class, "familyId", "year");
		
		request.setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

		//参数组装检查
		checkMap.clear();
		checkMap.put("familyId", request.getFamilyId());

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = reportService.reportFamilyIncomeInfo(request);
		renderJson(resultMap);
		return;
	}

}
