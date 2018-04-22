package com.arnold.server.service.api;

import com.arnold.server.model.RegionInfo;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegionInfoService extends BaseService {

	public Map<String, Object> addRegionInfo(){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
					
		return resultMap;		
	}
	
	public Map<String, Object> delRegionInfo(String regionId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{		
			RegionInfo model = RegionInfo.dao.findByRegionId(regionId);		
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			
			//TODO:逻辑删除
			model.setIsValid(-1);
			
			model.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);			
		}catch(Exception e){			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);			
		}
		
		return resultMap;	
	}
	
	public Map<String, Object> updateRegionInfo(String regionId,String povertyTypeId,String shortDescription,
			String remark,String operator, Date assignDate, Date assignEndDate, Map<String,String> chooseParamMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//统计部分不更新
		//Record model2 = Arnold.dao.selectRegionRelationalData(regionId);



/*
		if(model.get("population")==null){
			model.set("population",model2.get("family_villager_count"));
		}
		if(model.get("count")==null){
			model.set("count",model2.get("family_count"));
		}
		if(model.get("cultivatedArea")==null){
			model.set("cultivatedArea",model2.get("cultivatedArea"));
		}
		if(model.get("irrigatedArea")==null){
			model.set("irrigatedArea",model2.get("effectiveIrrigationArea"));
		}
		if(model.get("fieldArea")==null){
			model.set("fieldArea",model2.get("fieldArea"));
		}
		if(model.get("landArea")==null){
			model.set("landArea",model2.get("landArea"));
		}
		if(model.get("forestryArea")==null){
			model.set("forestryArea",model2.get("forestryArea"));
		}
		if(model.get("backForestryArea")==null){
			model.set("backForestryArea",model2.get("backForestryArea"));
		}
		if(model.get("fruitTreeArea")==null){
			model.set("fruitTreeArea",model2.get("fruitTreeArea"));
		}
		if(model.get("grassArea")==null){
			model.set("grassArea",model2.get("grassArea"));
		}
		if(model.get("waterArea")==null){
			model.set("waterArea",model2.get("waterArea"));
		}
		if(model.get("economicArea")==null){
			model.set("economicArea",model2.get("economicArea"));
		}

		if(model.get("grossIncome")==null){
			model.set("grossIncome",model2.get("averageIncome"));
		}
		if(model.get("householdIncome")==null){
			model.set("householdIncome",model2.get("family_income"));
		}
		if(model.get("propertyIncome")==null){
			model.set("propertyIncome",model2.get("propertyIncome"));
		}
		if(model.get("transferIncome")==null){
			model.set("transferIncome",model2.get("shift_income"));
		}
		if(model.get("totalNetIncome")==null){
			model.set("totalNetIncome",model2.get("total_income"));
		}
		if(model.get("totalPerNetIncome")==null){
			model.set("totalPerNetIncome",model2.get("average_income"));
		}
		if(model.get("totalProductionExpenses")==null){
			model.set("totalProductionExpenses",model2.get("purchase_expend"));
		}
		if(model.get("totalOtherExpenses")==null){
			model.set("totalOtherExpenses",model2.get("other_expend"));
		}*/


		RegionInfo model = RegionInfo.dao.findByRegionId(regionId);
		if(model==null) {
			model=new RegionInfo();
			if(!Utils.isBlankOrEmpty(povertyTypeId)) model.setPovertyTypeId(povertyTypeId);
			if(!Utils.isBlankOrEmpty(shortDescription)) model.setShortDescription(shortDescription);
			if(!Utils.isBlankOrEmpty(remark)) model.setRemark(remark);
			if(!Utils.isBlankOrEmpty(operator)) model.setOperator(operator);
			if(assignDate != null) model.setAssignDate(assignDate);
			if(assignEndDate != null) model.setAssignEndDate(assignEndDate);
			model.setCreateTime(new Date());
			model.setIsValid(0);
			model.setId(Utils.create36UUID());
			model.setRegionId(regionId);
			//model.setPopulation(model2.get("family_villager_count")==null?0:Integer.parseInt(model2.get("family_villager_count").toString()));
			//model.setCount(model2.get("family_count")==null?0:Integer.parseInt(model2.get("family_count").toString()));
			
			model.setHeadOfVillageCommitteeId(chooseParamMap.get("headOfVillageCommitteeId"));
			model.setHeadOfVillageCommitteeName(chooseParamMap.get("headOfVillageCommitteeName"));
			model.setVillageBranchSecretaryId(chooseParamMap.get("villageBranchSecretaryId"));
			model.setVillageBranchSecretaryName(chooseParamMap.get("villageBranchSecretaryName"));
			model.setPoorFamilyLeaderId(chooseParamMap.get("poorFamilyLeaderId"));
			model.setPoorFamilyLeaderName(chooseParamMap.get("poorFamilyLeaderName"));
			
			model.save();

		}else{
			if(!Utils.isBlankOrEmpty(povertyTypeId)) model.setPovertyTypeId(povertyTypeId);
			if(!Utils.isBlankOrEmpty(shortDescription)) model.setShortDescription(shortDescription);
			if(!Utils.isBlankOrEmpty(remark)) model.setRemark(remark);
			if(!Utils.isBlankOrEmpty(operator)) model.setOperator(operator);
			if(assignDate != null) model.setAssignDate(assignDate);
			if(assignEndDate != null) model.setAssignEndDate(assignEndDate);
			//model.setPopulation(model2.get("family_villager_count")==null?0:Integer.parseInt(model2.get("family_villager_count").toString()));
			//model.setCount(model2.get("family_count")==null?0:Integer.parseInt(model2.get("family_count").toString()));
			model.setUpdateTime(new Date());
			
			model.setHeadOfVillageCommitteeId(chooseParamMap.get("headOfVillageCommitteeId"));
			model.setHeadOfVillageCommitteeName(chooseParamMap.get("headOfVillageCommitteeName"));
			model.setVillageBranchSecretaryId(chooseParamMap.get("villageBranchSecretaryId"));
			model.setVillageBranchSecretaryName(chooseParamMap.get("villageBranchSecretaryName"));
			model.setPoorFamilyLeaderId(chooseParamMap.get("poorFamilyLeaderId"));
			model.setPoorFamilyLeaderName(chooseParamMap.get("poorFamilyLeaderName"));

			model.update();
		}
			
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
}
