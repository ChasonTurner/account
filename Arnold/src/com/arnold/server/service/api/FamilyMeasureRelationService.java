package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.FamilyMeasureRelation;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class FamilyMeasureRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(FamilyMeasureRelationService.class);

	/**
	 * 增加家庭与帮扶措施的关系
	 * 
	 * @Method:createFamilyMeasureRelation
	 * @Date:2017年8月14日 上午10:47:25
	 * @Author:YangCheng
	 * @param familyId
	 * @param measureId
	 * @return
	 */
	public Map<String, Object> addFamilyMeasureRelation(String familyId, String measureIds) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			
			//多个时用英文逗号隔开，这里解析
			String[] array = measureIds.split(",");
			for(String measureId : array) {		
				FamilyMeasureRelation relation = FamilyMeasureRelation.dao.findBy(familyId, measureId);
				if (relation == null) {
					addRelation(familyId, measureId);
				}
			}
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
	 * 删除家庭与帮扶措施的关系
	 * 
	 * @Method:deleteFamilyMeasureRelation
	 * @Date:2017年8月14日 上午10:47:52
	 * @Author:YangCheng
	 * @param houseId
	 * @param measureId
	 * @return
	 */
	public Map<String, Object> deleteFamilyMeasureRelation(String familyId, String measureId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			FamilyMeasureRelation relation = FamilyMeasureRelation.dao.findBy(familyId, measureId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(familyId, measureId);

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
	 * 更新家庭与帮扶措施关系
	 * 
	 * @Method:updateFamilyMeasureRelation
	 * @Date:2017年8月14日 上午10:48:05
	 * @Author:YangCheng
	 * @param houseId
	 * @param oldMeasureId
	 * @param newMeasureId
	 * @return
	 */
	public Map<String, Object> updateFamilyMeasureRelation(String familyId, String oldMeasureId, String newMeasureId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			FamilyMeasureRelation relation = FamilyMeasureRelation.dao.findBy(familyId, oldMeasureId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(familyId, oldMeasureId, newMeasureId);

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
	 * 分页查询家庭与帮扶措施的关系
	 * @Method:pageFamilyMeasureRelation
	 * @Date:2017年8月14日 下午6:26:04
	 * @Author:YangCheng
	 * @param measureId
	 * @param familyId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyMeasureRelation(String measureId, String familyId, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			Page<FamilyMeasureRelation> page = FamilyMeasureRelation.dao.pageFamilyMeasureRelation(measureId, familyId,
					pageNumber, pageSize);

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

	private String addRelation(String familyId, String measureId) {
		FamilyMeasureRelation relation = new FamilyMeasureRelation();

		relation.setId(Utils.create36UUID());
		relation.setFamilyId(familyId);
		relation.setMeasureId(measureId);
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();

	}

	private int deleteRelation(String familyId, String measureId) {
		FamilyMeasureRelation relation = FamilyMeasureRelation.dao.findBy(familyId, measureId);
		if (relation != null) {
			relation.setIsValid(-1);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

	private int updateRelation(String familyId, String oldMeasureId, String newMeasureId) {
		FamilyMeasureRelation relation = FamilyMeasureRelation.dao.findBy(familyId, oldMeasureId);
		if (relation != null) {
			relation.setMeasureId(newMeasureId);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}
}
