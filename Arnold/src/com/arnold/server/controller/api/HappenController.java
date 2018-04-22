package com.arnold.server.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.controller.BaseController;
import com.arnold.server.service.api.FamilyShiftIncomeHappenService;
import com.arnold.server.service.api.MemberOrgHappenService;
import com.arnold.server.service.api.RegionIndustryHappenService;
import com.arnold.server.service.api.VillagerIncomeHappenService;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

/**
 * @Description: (业务)流水Controller
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年7月13日 下午2:55:40
 */
public class HappenController extends BaseController{
	/**
	 * @Description: 新增村名收入
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  add_villager_income(){
		String userId =getPara("userId");
		String villagerId =getPara("villagerId"); 
		String postId =getPara("villagerId"); 
		Date incomeTime =getParaToDate("incomeTime",new Date()); 
		String incomeStr=getPara("income"); 
		Double income=0.0;
		if(ArnoldUtils.isNumber(incomeStr)){
			income=Double.valueOf(incomeStr); 
		}
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (Utils.isBlankOrEmpty(userId)||Utils.isBlankOrEmpty(villagerId)||Utils.isBlankOrEmpty(postId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return ;
		}
		
		VillagerIncomeHappenService happenService=new VillagerIncomeHappenService();
		
		Map<String ,Object> operMap=happenService.addVillagerIncome(villagerId, postId, incomeTime, income, userId);
		resultMap.put(ConstUtils.RETURN_CODE,operMap.get(ConstUtils.RETURN_CODE));
		resultMap.put(ConstUtils.R_MSG, operMap.get(ConstUtils.R_MSG));
		renderJson(resultMap);
	}
	/**
	 * @Description: 根据条件分页查询村名收入
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  page_villager_income(){
		String keyWord = getPara("keyWord");
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String villagerId =getPara("villagerId"); 
		String postId =getPara("villagerId"); 
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		VillagerIncomeHappenService happenService=new VillagerIncomeHappenService();
		resultMap.put(ConstUtils.R_PAGE,happenService.pageVillagerIncome(pageNumber, pageSize, villagerId, postId, keyWord));
		resultMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
	}
	/**
	 * @Description: 新增村与产业关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  add_region_industry(){
		String userId =getPara("userId");
		String regionId =getPara("regionId"); 
		String industryId =getPara("industryId"); 
		int ralationType =getParaToInt("ralationType",0); 
		String amountStr=getPara("amount"); 
		int amount=0;
		if(ArnoldUtils.isNumber(amountStr)){
			amount=Integer.valueOf(amountStr); 
		}
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (Utils.isBlankOrEmpty(regionId)||Utils.isBlankOrEmpty(industryId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return ;
		}
		
		RegionIndustryHappenService happenService=new RegionIndustryHappenService();
		
		Map<String ,Object> operMap=happenService.addRegionIndustry(regionId, industryId, ralationType, amount, userId);
		resultMap.put(ConstUtils.RETURN_CODE,operMap.get(ConstUtils.RETURN_CODE));
		resultMap.put(ConstUtils.R_MSG, operMap.get(ConstUtils.R_MSG));
		renderJson(resultMap);
	}
	
	/**
	 * @Description: 根据条件分页查询村与产业关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  page_region_industry(){
		String keyWord = getPara("keyWord");
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String regionId =getPara("regionId"); 
		String industryId =getPara("industryId"); 
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		RegionIndustryHappenService happenService=new RegionIndustryHappenService();
		resultMap.put(ConstUtils.R_PAGE,happenService.pageRegionIndustry(pageNumber, pageSize, regionId, industryId, keyWord));
		resultMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
	}
	/**
	 * @Description: 新增村的产业收入
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  add_region_industry_income(){
		
	}
	/**
	 * @Description: 根据条件查询村的产业收入
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  page_region_industry_income(){
	
	} 
	
	/**
	 * @Description: 新增贫困户转移性收入
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  add_fmaily_shift_income(){
		String userId =getPara("userId");
		String fmailyId =getPara("fmailyId"); 
		String incomTypeId =getPara("incomTypeId"); 

		Date incomeTime =getParaToDate("incomeTime",new Date()); 
		String incomeStr=getPara("income"); 
		Double income=0.0;
		if(ArnoldUtils.isNumber(incomeStr)){
			income=Double.valueOf(incomeStr); 
		}
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (Utils.isBlankOrEmpty(fmailyId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return ;
		}
		
		FamilyShiftIncomeHappenService happenService=new FamilyShiftIncomeHappenService();
		
		int resultCode=happenService.addFamilyShiftIncomeHappen(fmailyId, incomeTime, income,incomTypeId, userId);
		if (resultCode==ConstUtils.DEAL_SUCCESS) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			renderJson(resultMap);
			return ;
		}
		if (resultCode==ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return ;
		}
		renderJson(resultMap);
	}
	/**
	 * @Description: 根据条件查询贫困户转移性收入
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  page_fmaily_shift_income(){
		String keyWord = getPara("keyWord");
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String fmailyId =getPara("fmailyId"); 
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyShiftIncomeHappenService happenService=new FamilyShiftIncomeHappenService();
		resultMap.put(ConstUtils.R_PAGE,happenService.pageFamilyShiftIncomeHappen(pageNumber, pageSize, fmailyId, keyWord));
		resultMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
	}
	/**
	 * @Description: 新增人员岗位关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  add_member_post(){
		
	}
	/**
	 * @Description: 根据条件分页查询人员岗位关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  page_member_post(){
		
	}

	/**
	 * @Description: 新增人与家庭关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  add_villager_family(){
		
	}
	/**
	 * @Description: 根据条件分页查询人与家庭关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  page_villager_family(){
		
	}
	/**
	 * @Description: 新增人员与原单位关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  add_member_org(){
		String userId =getPara("userId");
		String memberId =getPara("memberId"); 
		String orgId =getPara("orgId"); 
		String posId =getPara("posId"); 
		String ralationTypeStr =getPara("ralationType"); 
		int ralationType=0;
		if(ArnoldUtils.isNumber(ralationTypeStr)){
			ralationType=Integer.valueOf(ralationTypeStr); 
		}
		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (Utils.isBlankOrEmpty(memberId)||Utils.isBlankOrEmpty(orgId)||Utils.isBlankOrEmpty(posId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return ;
		}
		
		MemberOrgHappenService happenService=new MemberOrgHappenService();
		
		int resultCode=happenService.addMemberOrgHappen( memberId, orgId, posId, ralationType,  userId);
		if (resultCode==ConstUtils.DEAL_SUCCESS) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			renderJson(resultMap);
			return ;
		}
		if (resultCode==ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return ;
		}
		renderJson(resultMap);
	}
	/**
	 * @Description: 根据条件分页查询人员与原单位关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 上午9:17:53
	 */
	public void  page_member_org(){
		String keyWord = getPara("keyWord");
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String memberId =getPara("memberId"); 
		String orgId =getPara("orgId"); 
		String posId =getPara("posId"); 
		String ralationType =getPara("ralationType"); 

		loggerDebugRequestUrl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MemberOrgHappenService happenService=new MemberOrgHappenService();
		resultMap.put(ConstUtils.R_PAGE,happenService.pageMemberOrgHappen(pageNumber, pageSize,  memberId, orgId, posId, ralationType, keyWord));
		resultMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}
}

