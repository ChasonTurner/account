package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.HouseConditionRelation;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class HouseConditionRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(HouseConditionRelationService.class);

	/**
	 * 增加房屋与房屋状况关系
	 * 
	 * @Method:createHouseConditionRelation
	 * @Date:2017年8月14日 上午10:14:58
	 * @Author:YangCheng
	 * @param houseId
	 * @param conditionId
	 * @return
	 */
	public Map<String, Object> createHouseConditionRelation(String houseId, String conditionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id  = "";
			
			HouseConditionRelation relation = HouseConditionRelation.dao.findBy(houseId, conditionId);
			if (relation == null) {
				id = addRelation(houseId, conditionId);
			} else {
				id = relation.getId();
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
	 * 删除房屋与房屋状况关系信息
	 * 
	 * @Method:deleteHouseConditonRelation
	 * @Date:2017年8月14日 上午10:17:02
	 * @Author:YangCheng
	 * @param houseId
	 * @param conditionId
	 * @return
	 */
	public Map<String, Object> deleteHouseConditonRelation(String houseId, String conditionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			HouseConditionRelation relation = HouseConditionRelation.dao.findBy(houseId, conditionId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(houseId, conditionId);

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
	 * 更新房屋与房屋状况的关系
	 * 
	 * @Method:updateHouseConditionRelation
	 * @Date:2017年8月14日 上午10:20:02
	 * @Author:YangCheng
	 * @param houseId
	 * @param oldConditionId
	 * @param newConditionId
	 * @return
	 */
	public Map<String, Object> updateHouseConditionRelation(String houseId, String oldConditionId,
			String newConditionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			HouseConditionRelation relation = HouseConditionRelation.dao.findBy(houseId, oldConditionId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(houseId, oldConditionId, newConditionId);

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
	 * 分页查询房屋与房屋状况的关联
	 * @Method:pageHouseConditionRelation
	 * @Date:2017年8月14日 下午6:28:57
	 * @Author:YangCheng
	 * @param houseId
	 * @param conditionId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageHouseConditionRelation(String houseId, String conditionId, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			Page<HouseConditionRelation> page = HouseConditionRelation.dao.pageHouseConditionRelation(houseId,
					conditionId, pageNumber, pageSize);

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

	private int deleteRelation(String houseId, String conditionId) {
		HouseConditionRelation relation = HouseConditionRelation.dao.findBy(houseId, conditionId);
		if (relation != null) {
			relation.setIsValid(0);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

	private int updateRelation(String houseId, String oldConditionId, String newConditionId) {
		HouseConditionRelation relation = HouseConditionRelation.dao.findBy(houseId, oldConditionId);
		if (relation != null) {
			relation.setConditionId(newConditionId);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

	private String addRelation(String houseId, String conditionId) {
		HouseConditionRelation relation = new HouseConditionRelation();

		relation.setId(Utils.create36UUID());
		relation.setHouseId(houseId);
		relation.setConditionId(conditionId);
		relation.setCreateTime(new Date());
		relation.setUpdateTime(new Date());
		relation.setIsValid(1);
		relation.save();

		return relation.getId();
	}
}
