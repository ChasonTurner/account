package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.FamilyInsuranceRelation;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author ChangGui Pan
 * @time 2017年8月14日 上午9:33:27
 * @description TODO
 */
public class FamilyInsuranceRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(FamilyInsuranceRelationService.class);

	/**
	 * 增加家庭与保险的关系
	 * 
	 * @Method:addFamilyInsuranceRelation
	 * @Date:2017年8月14日 下午2:06:43
	 * @Author:YangCheng
	 * @param familyId
	 * @param insuranceId
	 * @return
	 */
	public Map<String, Object> addFamilyInsuranceRelation(String familyId, String insuranceIds) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			//多个时用英文逗号隔开，这里解析
			String[] array = insuranceIds.split(",");
			for(String insuranceId : array) {					
				FamilyInsuranceRelation relation = FamilyInsuranceRelation.dao.findBy(familyId, insuranceId);
				if (relation == null) {
					addRelation(familyId, insuranceId);
				}
			}
			
			dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			dataMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			return dataMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 删除家庭与保险的关系
	 * 
	 * @Method:deleteFamilyInsuranceRelation
	 * @Date:2017年8月14日 下午2:08:39
	 * @Author:YangCheng
	 * @param familyId
	 * @param insuranceId
	 * @return
	 */
	public Map<String, Object> deleteFamilyInsuranceRelation(String familyId, String insuranceId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			FamilyInsuranceRelation relation = FamilyInsuranceRelation.dao.findBy(familyId, insuranceId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(familyId, insuranceId);

			dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			dataMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			return dataMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 修改家庭与保险的关系
	 * 
	 * @Method:updateFamilyInsuranceRelation
	 * @Date:2017年8月14日 下午2:07:12
	 * @Author:YangCheng
	 * @param familyId
	 * @param insuranceId
	 * @param oldInsuranceId
	 * @return
	 */
	public Map<String, Object> updateFamilyInsuranceRelation(String familyId, String insuranceId,
			String oldInsuranceId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			FamilyInsuranceRelation relation = FamilyInsuranceRelation.dao.findBy(familyId, oldInsuranceId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(familyId, oldInsuranceId, insuranceId);

			dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			dataMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			return dataMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 根据家庭ID分页查询家庭与保险的关系
	 * 
	 * @Method:pageFamilyInsuranceRelationByFamily
	 * @Date:2017年8月14日 下午2:08:56
	 * @Author:YangCheng
	 * @param familyId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyInsuranceRelation(String familyId, String insuranceId, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			Page<FamilyInsuranceRelation> page = FamilyInsuranceRelation.dao.pageFamilyInsuranceRelation(familyId,
					insuranceId, pageNumber, pageSize);
			dataMap.put(ConstUtils.R_PAGE, page);
			dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			dataMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			return dataMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	private String addRelation(String familyId, String insuranceId) {
		FamilyInsuranceRelation relation = new FamilyInsuranceRelation();
		
		relation.setId(Utils.create36UUID());
		relation.setFamilyId(familyId);
		relation.setInsuranceId(insuranceId);
		relation.setCreateTime(new Date());
		relation.save();
		
		return relation.getId();
	}

	private int deleteRelation(String familyId, String insuranceId) {
		FamilyInsuranceRelation relation = FamilyInsuranceRelation.dao.findBy(familyId, insuranceId);

		if (relation != null) {
			relation.setIsValid(-1);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return -1;
		}
	}

	private int updateRelation(String familyId, String insuranceId, String oldInsuranceId) {
		FamilyInsuranceRelation relation = FamilyInsuranceRelation.dao.findBy(familyId, oldInsuranceId);

		if (relation != null) {
			relation.setInsuranceId(insuranceId);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return -1;
		}
	}

}
