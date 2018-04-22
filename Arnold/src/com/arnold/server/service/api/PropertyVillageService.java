package com.arnold.server.service.api;

import com.arnold.server.model.PropertyVillage;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PropertyVillageService extends BaseService {
	public void addpropertyVillage(String villageId, String regionId, String poulationCount, String partyMemberCount,
								   String percent, String fiveGuaranteedCount, String poorPopulation, String lowIncomeCount,
								   String removalPlan, Date removalTime, String houseTransformPlan, Date transformTime,
								   String isTapWater, String isSeriousPatient, String isPrimarySchool, String isVillageRoad,
								   String isGroupRoad, String childDropoutCount, String averageIncome, String economyScale,
								   String perCultivatedArea, String perWoodland, String difficulty, String informant, String writer, String operator) {
		PropertyVillage propertyVillage=new PropertyVillage();
		propertyVillage.setId(Utils.create36UUID());
		propertyVillage.setAddress(regionId);
		propertyVillage.setAverageIncome(averageIncome);
		propertyVillage.setChildDropoutCount(childDropoutCount);
		propertyVillage.setCreateTime(new Date());
		propertyVillage.setDifficulty(difficulty);
		propertyVillage.setEconomyScale(economyScale);
		propertyVillage.setFiveGuaranteedCount(fiveGuaranteedCount);
		propertyVillage.setHouseTransformPlan(houseTransformPlan);
		propertyVillage.setIsGroupRoad(isGroupRoad);
		propertyVillage.setIsPrimarySchool(isPrimarySchool);
		propertyVillage.setIsSeriousPatient(isSeriousPatient);
		propertyVillage.setIsTapWater(isTapWater);
		propertyVillage.setIsVillageRoad(isVillageRoad);
		propertyVillage.setLowIncomeCount(lowIncomeCount);
		propertyVillage.setOperatorId(operator);
		propertyVillage.setPartyMemberCount(partyMemberCount);
		propertyVillage.setPercent(percent);
		propertyVillage.setPerCultivatedArea(perCultivatedArea);
		propertyVillage.setPerWoodland(perWoodland);
		propertyVillage.setPoorPopulation(poorPopulation);
		propertyVillage.setPoulationCount(poulationCount);
		propertyVillage.setRegionId(villageId);
		propertyVillage.setRemovalPlan(removalPlan);
		propertyVillage.setRemovalTime(removalTime);
		propertyVillage.setTransformTime(transformTime);
		propertyVillage.setUpdateTime(new Date());
		propertyVillage.setWriterId(writer);
		propertyVillage.save();
	}

	public Map<String,Object> delStayingVillage(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			PropertyVillage propertyVillage = PropertyVillage.dao.findById(id);

			if(propertyVillage == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个驻村点！");
				return resultMap;
			}

			//TODO:逻辑删除
			propertyVillage.delete();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");

		}catch(Exception e){

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");

		}

		return resultMap;

	}

}
