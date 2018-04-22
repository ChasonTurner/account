package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.ProductCondition;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class ProductConditionService extends BaseService {

	public Map<String, Object> addProductCondition(String familyId, int noCredit, int familyIncome,
						int livingMoney, int familyYearIncome, int personYearIncome, int nursingMoney, 
						int yearIncome,
						int subsidy, int reimbursement, int salaryIncome, int productPay, int aimMoney,
						int propertyIncome, int familyPlanningMoney, int ecologyMoney, Double averageIncome){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		try {
			
			ProductCondition productConditionModel = new ProductCondition();
			
			productConditionModel.setId(Utils.create36UUID());
			productConditionModel.setFamilyId(familyId);
			productConditionModel.setNoCredit(noCredit);
			productConditionModel.setFamilyIncome(familyIncome);
			productConditionModel.setLivingMoney(livingMoney);
			productConditionModel.setFamilyYearIncome(familyYearIncome);
			productConditionModel.setPersonYearIncome(personYearIncome);
			productConditionModel.setNursingMoney(nursingMoney);
			productConditionModel.setYearIncome(yearIncome);
			productConditionModel.setSubsidy(subsidy);
			productConditionModel.setReimbursement(reimbursement);
			productConditionModel.setSalaryIncome(salaryIncome);
			productConditionModel.setProductPay(productPay);
			productConditionModel.setAimMoney(aimMoney);
			productConditionModel.setPropertyIncome(propertyIncome);
			productConditionModel.setFamilyPlanningMoney(familyPlanningMoney);
			productConditionModel.setEcologyMoney(ecologyMoney);
			productConditionModel.setAverageIncome(averageIncome);
			
			productConditionModel.save();
			
			resultMap.put(ConstUtils.R_PAGE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
			return resultMap;
			
		}
		
			
		return resultMap;
		
	}
	
	public Map<String, Object> pageProductCondition(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProductCondition productCondition = new ProductCondition();
			
			Page<ProductCondition> productConditionModel 
							= productCondition.pageProductConditionByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, productConditionModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageProductConditionByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProductCondition productCondition = new ProductCondition();
			
			Page<ProductCondition> productConditionModel 
							= productCondition.pageProductConditionByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, productConditionModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateProductCondition(String id, String familyId, int noCredit, int familyIncome,
			int livingMoney, int familyYearIncome, int personYearIncome, int nursingMoney, 
			int yearIncome,
			int subsidy, int reimbursement, int salaryIncome, int productPay, int aimMoney,
			int propertyIncome, int familyPlanningMoney, int ecologyMoney, Double averageIncome){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProductCondition productConditionModel = ProductCondition.dao.findById(id);
			
			if(productConditionModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个生产条件信息！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(familyId)){
				productConditionModel.setFamilyId(familyId);
			}
			if(noCredit != -1){
				productConditionModel.setNoCredit(noCredit);
			}
			if(familyIncome != -1){
				productConditionModel.setFamilyIncome(familyIncome);
			}
			if(livingMoney != -1){
				productConditionModel.setLivingMoney(livingMoney);
			}
			if(familyYearIncome != -1){
				productConditionModel.setFamilyYearIncome(familyYearIncome);
			}
			if(personYearIncome != -1){
				productConditionModel.setPersonYearIncome(personYearIncome);
			}
			if(nursingMoney != -1){
				productConditionModel.setNursingMoney(nursingMoney);
			}
			if(yearIncome != -1){
				productConditionModel.setYearIncome(yearIncome);
			}
			if(subsidy != -1){
				productConditionModel.setSubsidy(subsidy);
			}
			if(reimbursement != -1){
				productConditionModel.setReimbursement(reimbursement);
			}
			if(salaryIncome != -1){
				productConditionModel.setSalaryIncome(salaryIncome);
			}
			if(productPay != -1){
				productConditionModel.setProductPay(productPay);
			}
			if(aimMoney != -1){
				productConditionModel.setAimMoney(aimMoney);
			}
			if(propertyIncome != -1){
				productConditionModel.setPropertyIncome(propertyIncome);
			}
			if(familyPlanningMoney != -1){
				productConditionModel.setFamilyPlanningMoney(familyPlanningMoney);
			}
			if(ecologyMoney != -1){
				productConditionModel.setEcologyMoney(ecologyMoney);
			}
			if(averageIncome != -1){
				productConditionModel.setAverageIncome(averageIncome);
			}
			
			productConditionModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delProductCondition(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProductCondition productConditionModel = ProductCondition.dao.findById(id);
			
			if(productConditionModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个生产条件信息！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//productConditionModel.delete();
			
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
