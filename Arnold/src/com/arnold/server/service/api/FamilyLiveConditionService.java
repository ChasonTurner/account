package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.FamilyLivecondition;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;


public class FamilyLiveConditionService extends BaseService {

	public Map<String, Object> updateLiveCondition(String familyId, String cultivatedArea,
			String effectiveIrrigationArea, String field, String land, String forestryArea, String backForestryArea,
			String fruitTreeArea, String grassArea, String waterArea, String economicArea, int isDrinkDifficulty,
			int isDrinkSafety, String drinkConditionId, int isGalvanical, int isTelevision,
			String consumerGoodsCondition, String trunklineDistance, String nearestMarketDistance,
			String registerRoadTypeId, int roadCondition, String buildHouseYear, String housingArea,
			int hasHouse,String houseStructureId, int isDangerousBuilding, String fuelsTypeId, int isSanitaryToilet,
			String productionArea, String liveArea, String longitude, String latitude,
			String height, String operator, 
			String outstandingLoan, String operatingIncome, String receivedLowGold, String perCapitaIncome, String annualNetIncome, 
			String annualIncome, String receivedEndowmentInsurance, String allSubsidization, String claimMedicalExpenses, String wageIncome, 
			String productiveOutlays, String medicalAid, String propertyIncome, String receivedFamilyPlanningMoney, String ecologicalCompensation,
			Double trainingSubsidy, Double trainingExpenditure,
			Double cashPolicySubsidies, Double educationalExpenditure,
			Double educationalSubsidy, Double medicalExpenditure){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		boolean createFlag = false;
		//根据familyId判断是否存在记录
		FamilyLivecondition model = FamilyLivecondition.dao.findByFamilyId(familyId);
		if(model == null) {
			model = new FamilyLivecondition();
			model.setId(Utils.create36UUID());
			model.setFamilyId(familyId);
			model.setCreateTime(new Date());
			
			createFlag = true;
		}
		
		if(!Utils.isBlankOrEmpty(cultivatedArea)) model.setCultivatedArea(cultivatedArea);
		if(!Utils.isBlankOrEmpty(effectiveIrrigationArea)) model.setEffectiveIrrigationArea(effectiveIrrigationArea);
		if(!Utils.isBlankOrEmpty(field)) model.setField(field);
		if(!Utils.isBlankOrEmpty(land)) model.setLand(land);
		if(!Utils.isBlankOrEmpty(forestryArea)) model.setForestryArea(forestryArea);
		if(!Utils.isBlankOrEmpty(backForestryArea)) model.setBackForestryArea(backForestryArea);
		if(!Utils.isBlankOrEmpty(fruitTreeArea)) model.setFruitTreeArea(fruitTreeArea);
		if(!Utils.isBlankOrEmpty(grassArea)) model.setGrassArea(grassArea);
		if(!Utils.isBlankOrEmpty(waterArea)) model.setWaterArea(waterArea);
		if(!Utils.isBlankOrEmpty(economicArea)) model.setEconomicArea(economicArea);
		if(isDrinkDifficulty != -1) model.setIsDrinkDifficulty(isDrinkDifficulty);
		if(isDrinkSafety != -1) model.setIsDrinkSafety(isDrinkSafety);
		if(!Utils.isBlankOrEmpty(drinkConditionId)) model.setDrinkConditionId(drinkConditionId);
		if(isGalvanical != -1) model.setIsGalvanical(isGalvanical);
		if(isTelevision != -1) model.setIsTelevision(isTelevision);
		if(!Utils.isBlankOrEmpty(consumerGoodsCondition)) model.setConsumerGoodsCondition(consumerGoodsCondition);
		if(!Utils.isBlankOrEmpty(trunklineDistance)) model.setTrunklineDistance(trunklineDistance);
		if(!Utils.isBlankOrEmpty(nearestMarketDistance)) model.setNearestMarketDistance(nearestMarketDistance);
		if(!Utils.isBlankOrEmpty(registerRoadTypeId)) model.setRegisterRoadTypeId(registerRoadTypeId);
		if(roadCondition != -1) model.setRoadCondition(roadCondition);
		if(!Utils.isBlankOrEmpty(buildHouseYear)) model.setBuildHouseYear(buildHouseYear);
		if(!Utils.isBlankOrEmpty(housingArea)) model.setHousingArea(housingArea);
		if(hasHouse != -1) model.setHasHouse(hasHouse);
		if(!Utils.isBlankOrEmpty(houseStructureId)) model.setHouseStructureId(houseStructureId);
		if(isDangerousBuilding != -1) model.setIsDangerousBuilding(isDangerousBuilding);
		if(!Utils.isBlankOrEmpty(fuelsTypeId)) model.setFuelsTypeId(fuelsTypeId);
		if(isSanitaryToilet != -1) model.setIsSanitaryToilet(isSanitaryToilet);
		if(!Utils.isBlankOrEmpty(productionArea)) model.setProductionArea(productionArea);
		if(!Utils.isBlankOrEmpty(liveArea)) model.setLiveArea(liveArea);
		if(!Utils.isBlankOrEmpty(longitude)) model.setLongitude(longitude);
		if(!Utils.isBlankOrEmpty(latitude)) model.setLatitude(latitude);
		if(!Utils.isBlankOrEmpty(height)) model.setHeight(height);
		
		if(!Utils.isBlankOrEmpty(operator)) model.setOperator(operator);	
		
		if(!Utils.isBlankOrEmpty(outstandingLoan)) model.setOutstandingLoan(outstandingLoan);
		if(!Utils.isBlankOrEmpty(operatingIncome)) model.setOperatingIncome(operatingIncome);
		if(!Utils.isBlankOrEmpty(receivedLowGold)) model.setReceivedLowGold(receivedLowGold);
		if(!Utils.isBlankOrEmpty(perCapitaIncome)) model.setPerCapitaIncome(perCapitaIncome);
		if(!Utils.isBlankOrEmpty(annualNetIncome)) model.setAnnualNetIncome(annualNetIncome);
		if(!Utils.isBlankOrEmpty(annualIncome)) model.setAnnualIncome(annualIncome);
		if(!Utils.isBlankOrEmpty(receivedEndowmentInsurance)) model.setReceivedEndowmentInsurance(receivedEndowmentInsurance);
		if(!Utils.isBlankOrEmpty(allSubsidization)) model.setAllSubsidization(allSubsidization);
		if(!Utils.isBlankOrEmpty(claimMedicalExpenses)) model.setClaimMedicalExpenses(claimMedicalExpenses);
		if(!Utils.isBlankOrEmpty(wageIncome)) model.setWageIncome(wageIncome);
		if(!Utils.isBlankOrEmpty(productiveOutlays)) model.setProductiveOutlays(productiveOutlays);
		if(!Utils.isBlankOrEmpty(medicalAid)) model.setMedicalAid(medicalAid);
		if(!Utils.isBlankOrEmpty(propertyIncome)) model.setPropertyIncome(propertyIncome);
		if(!Utils.isBlankOrEmpty(receivedFamilyPlanningMoney)) model.setReceivedFamilyPlanningMoney(receivedFamilyPlanningMoney);
		if(!Utils.isBlankOrEmpty(ecologicalCompensation)) model.setEcologicalCompensation(ecologicalCompensation);
		
		if(null!=trainingSubsidy) model.setTrainingSubsidy(trainingSubsidy);
		if(null!=trainingExpenditure) model.setTrainingExpenditure(trainingExpenditure);
		if(null!=cashPolicySubsidies) model.setCashPolicySubsidies(cashPolicySubsidies);
		if(null!=educationalExpenditure) model.setEducationalExpenditure(educationalExpenditure);
		if(null!=educationalSubsidy) model.setEducationalSubsidy(educationalSubsidy);
		if(null!=medicalExpenditure) model.setMedicalExpenditure(medicalExpenditure);
		
		
		
		model.setUpdateTime(new Date());
		
		if(createFlag) {
			model.save();
		}else{
			model.update();
		}
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
					
		return resultMap;
	}
	
	public Map<String, Object> pageLiveCondition(int pageNumber, int pageSize){	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;	
	}
	
	public Map<String, Object> delLiveCondition(String familyId){	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyLivecondition model = FamilyLivecondition.dao.findByFamilyId(familyId);
		
		if(model == null){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}
		
		//TODO:逻辑删除 -1表示删除
		model.setIsValid(-1);
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		return resultMap;	
	}
	
	public Map<String, Object> findLiveCondition(String familyId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
			
		FamilyLivecondition model = FamilyLivecondition.dao.findByFamilyId(familyId);
		
		/*if(model == null){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}*/
		
		resultMap.put(ConstUtils.R_MODEL, model);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
}
