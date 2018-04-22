package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.VillagerPostRelation;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class VillagerPostRelationService extends BaseService {
	private final Logger logger = LoggerFactory.getLogger(VillagerPostRelationService.class);

	/**
	 * 增加村名与岗位的关系
	 * 
	 * @Method:addVillagerPostRelation
	 * @Date:2017年8月14日 上午10:39:59
	 * @Author:YangCheng
	 * @param villagerId
	 * @param postId
	 * @return
	 */
	public Map<String, Object> addVillagerPostRelation(String villagerId, String postId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id = "";

			VillagerPostRelation relation = VillagerPostRelation.dao.findBy(villagerId, postId);
			if (relation == null) {
				addRelation(villagerId, postId);
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
	 * 删除村名与岗位的关系
	 * 
	 * @Method:deleteVillagerPostRelation
	 * @Date:2017年8月14日 上午10:43:34
	 * @Author:YangCheng
	 * @param villagerId
	 * @param postId
	 * @return
	 */
	public Map<String, Object> deleteVillagerPostRelation(String villagerId, String postId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			VillagerPostRelation relation = VillagerPostRelation.dao.findBy(villagerId, postId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(villagerId, postId);

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
	 * 更新村名与岗位的关系
	 * 
	 * @Method:updateVillagerPostRelation
	 * @Date:2017年8月14日 上午10:44:12
	 * @Author:YangCheng
	 * @param villagerId
	 * @param postId
	 * @param oldPostId
	 * @return
	 */
	public Map<String, Object> updateVillagerPostRelation(String villagerId, String postId, String oldPostId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			VillagerPostRelation relation = VillagerPostRelation.dao.findBy(villagerId, oldPostId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(villagerId, oldPostId, postId);

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
	 * 分页查询村名与岗位的关系
	 * 
	 * @Method:pageVillagerPostRelation
	 * @Date:2017年8月14日 下午6:22:46
	 * @Author:YangCheng
	 * @param villagerId
	 * @param postId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageVillagerPostRelation(String villagerId, String postId, Integer pageNumber,
			Integer pageSize) {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<VillagerPostRelation> relationPage = VillagerPostRelation.dao.pageVillagerPostRelation(villagerId,
					postId, pageNumber, pageSize);

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

	private String addRelation(String villagerId, String postId) {
		VillagerPostRelation relation = new VillagerPostRelation();

		relation.setId(Utils.create36UUID());
		relation.setVillagerId(villagerId);
		relation.setPostId(postId);
		relation.setCreateTime(new Date());
		relation.setUpdateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String villagerId, String postId) {
		VillagerPostRelation relation = VillagerPostRelation.dao.findBy(villagerId, postId);
		if (relation != null) {
			relation.setIsValid(0);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

	private int updateRelation(String villagerId, String postId, String oldPostId) {
		VillagerPostRelation relation = VillagerPostRelation.dao.findBy(villagerId, oldPostId);
		if (relation != null) {
			relation.setPostId(postId);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}
}
