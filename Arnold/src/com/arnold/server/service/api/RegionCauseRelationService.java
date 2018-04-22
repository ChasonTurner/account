package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.RegionCauseRelation;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author ChangGui Pan
 * @time 2017年8月10日 下午7:03:28
 * @description TODO
 */
public class RegionCauseRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(RegionCauseRelationService.class);

	/**
	 * 增加地区与平困原因的关系
	 * 
	 * @Method:addRegionCauseRelation
	 * @Date:2017年8月14日 下午3:22:45
	 * @Author:YangCheng
	 * @param regionId
	 * @param causeId
	 * @return
	 */
	public Map<String, Object> addRegionCauseRelation(String regionId, String causeId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id = "";

			RegionCauseRelation relation = RegionCauseRelation.findBy(regionId, causeId);
			if (relation == null) {
				id = addRelation(regionId, causeId);
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
	 * 删除地区与贫困原因的关系
	 * 
	 * @Method:deleteRegionCauseRelation
	 * @Date:2017年8月14日 下午3:28:57
	 * @Author:YangCheng
	 * @param regionId
	 * @param causeId
	 * @return
	 */
	public Map<String, Object> deleteRegionCauseRelation(String regionId, String causeId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			RegionCauseRelation relation = RegionCauseRelation.findBy(regionId, causeId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(regionId, causeId);

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
	 * 修改地区与地区贫困原因的关系
	 * 
	 * @Method:updateRegionCauseRelation
	 * @Date:2017年8月14日 下午3:30:40
	 * @Author:YangCheng
	 * @param regionId
	 * @param causeId
	 * @return
	 */
	public Map<String, Object> updateRegionCauseRelation(String regionId, String causeId, String oldCauseId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			RegionCauseRelation relation = RegionCauseRelation.findBy(regionId, oldCauseId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(regionId, causeId, oldCauseId);

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
	 * 分页查询地区与贫困原因的关系
	 * 
	 * @Method:pageRegionCauseRelation
	 * @Date:2017年8月14日 下午6:37:12
	 * @Author:YangCheng
	 * @param regionId
	 * @param causeId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageRegionCauseRelation(String regionId, String causeId, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<RegionCauseRelation> relationPage = RegionCauseRelation.dao.pageRegionCauseRelation(regionId, causeId,
					pageNumber, pageSize);

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

	private String addRelation(String regionId, String causeId) {
		RegionCauseRelation relation = new RegionCauseRelation();

		relation.setId(Utils.create36UUID());
		relation.setRegionId(regionId);
		relation.setCauseId(causeId);
		relation.setIsValid(1);// 1有效
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String regionId, String causeId) {
		RegionCauseRelation relation = RegionCauseRelation.findBy(regionId, causeId);
		if (relation != null) {
			relation.setIsValid(0);// 0表示删除
			relation.setUpdateTime(new Date());
			relation.update();

			return 0;
		} else {
			return -1;
		}

	}

	private int updateRelation(String regionId, String causeId, String oldCauseId) {
		RegionCauseRelation relation = RegionCauseRelation.findBy(regionId, oldCauseId);
		if (relation == null) {
			return -1;
		}

		relation.setCauseId(causeId);
		relation.setUpdateTime(new Date());
		relation.update();

		return 0;
	}

}
