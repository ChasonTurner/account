package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.FamilyConditionRelation;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class FamilyConditionRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(FamilyConditionRelationService.class);

	/**
	 * 增加家庭与家庭情况的关系
	 * 
	 * @Method:createFamilyConditionRelation
	 * @Date:2017年8月14日 上午10:39:59
	 * @Author:YangCheng
	 * @param familyId
	 * @param conditionId
	 * @return
	 */
	public Map<String, Object> addFamilyConditionRelation(String familyId, String conditionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id  = "";
			
			FamilyConditionRelation relation = FamilyConditionRelation.dao.findBy(familyId, conditionId);
			if (relation == null) {
				addRelation(familyId, conditionId);
			} else {
				relation.getId();
			}

			dataMap.put("id", id);
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return dataMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 删除家庭与家庭状况的关系
	 * 
	 * @Method:deleteFamilyConditionRelation
	 * @Date:2017年8月14日 上午10:43:34
	 * @Author:YangCheng
	 * @param familyId
	 * @param conditionId
	 * @return
	 */
	public Map<String, Object> deleteFamilyConditionRelation(String familyId, String conditionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			FamilyConditionRelation relation = FamilyConditionRelation.dao.findBy(familyId, conditionId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(familyId, conditionId);

			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return dataMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 更新家庭与家庭状况的关系
	 * 
	 * @Method:updateFamilyConditionRelation
	 * @Date:2017年8月14日 上午10:44:12
	 * @Author:YangCheng
	 * @param familyId
	 * @param oldConditionId
	 * @param newConditionId
	 * @return
	 */
	public Map<String, Object> updateFamilyConditionRelation(String familyId, String oldConditionId,
			String newConditionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			FamilyConditionRelation relation = FamilyConditionRelation.dao.findBy(familyId, oldConditionId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(familyId, oldConditionId, newConditionId);

			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return dataMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 分页查询家庭状况
	 * @Method:pageFamilyConditionRelation
	 * @Date:2017年8月14日 下午6:22:46
	 * @Author:YangCheng
	 * @param familyId
	 * @param conditionId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyConditionRelation(String familyId, String conditionId, Integer pageNumber,
			Integer pageSize) {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<FamilyConditionRelation> relationPage = FamilyConditionRelation.dao
					.pageFamilyConditionRelation(familyId, conditionId, pageNumber, pageSize);

			dataMap.put(ConstUtils.R_PAGE, relationPage);
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

	private String addRelation(String familyId, String conditionId) {
		FamilyConditionRelation relation = new FamilyConditionRelation();

		relation.setId(Utils.create36UUID());
		relation.setFamilyId(familyId);
		relation.setConditionId(conditionId);
		relation.setCreateTime(new Date());
		relation.setUpdateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String familyId, String conditionId) {
		FamilyConditionRelation relation = FamilyConditionRelation.dao.findBy(familyId, conditionId);
		if (relation != null) {
			relation.setIsValid(0);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

	private int updateRelation(String familyId, String oldConditionId, String newConditionId) {
		FamilyConditionRelation relation = FamilyConditionRelation.dao.findBy(familyId, oldConditionId);
		if (relation != null) {
			relation.setConditionId(newConditionId);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

}
