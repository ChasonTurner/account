package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.FamilySoldierdepentantRelation;
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
public class FamilySoldierDepentantRelationService extends BaseService {
	
	private final Logger logger = LoggerFactory.getLogger(FamilySoldierDepentantRelationService.class);

	/**
	 * 增加家庭与军属信息关系
	 * 
	 * @Method:addFamilySoldierDepententsRelation
	 * @Date:2017年8月14日 上午11:13:56
	 * @Author:YangCheng
	 * @param familyId
	 * @param soldierDepentantId
	 * @return
	 */
	public Map<String, Object> addFamilySoldierDepententsRelation(String familyId, String soldierDepentantIds) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			//多个时用英文逗号隔开，这里解析
			String[] array = soldierDepentantIds.split(",");
			for(String soldierDepentantId : array) {	
				FamilySoldierdepentantRelation relation = FamilySoldierdepentantRelation.dao.findBy(familyId,
						soldierDepentantId);
				if (relation == null) {
					addRelation(familyId, soldierDepentantId);
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
	 * 删除家庭与军属信息的关系
	 * 
	 * @Method:deleteFamilySoldierDepentantRelation
	 * @Date:2017年8月14日 上午11:17:20
	 * @Author:YangCheng
	 * @param familyId
	 * @param soldierDepentantId
	 * @return
	 */
	public Map<String, Object> deleteFamilySoldierDepentantRelation(String familyId, String soldierDepentantId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			FamilySoldierdepentantRelation relation = FamilySoldierdepentantRelation.dao.findBy(familyId,
					soldierDepentantId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(familyId, soldierDepentantId);

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
	 * 更新家庭与军属信息的关系
	 * 
	 * @Method:updateFamilySoldierDepentantRelation
	 * @Date:2017年8月14日 上午11:19:53
	 * @Author:YangCheng
	 * @param familyId
	 * @param oldSoldierDepentantId
	 * @param newSoldierDepentantId
	 * @return
	 */
	public Map<String, Object> updateFamilySoldierDepentantRelation(String familyId, String oldSoldierDepentantId,
			String newSoldierDepentantId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			FamilySoldierdepentantRelation relation = FamilySoldierdepentantRelation.dao.findBy(familyId,
					oldSoldierDepentantId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(familyId, newSoldierDepentantId);

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
	 * 分页查询家庭与军属信息的关系
	 * @Method:pageSoldierDepentant
	 * @Date:2017年8月14日 下午6:27:34
	 * @Author:YangCheng
	 * @param familyId
	 * @param soldierDepentantId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageSoldierDepentant(String familyId,String soldierDepentantId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<FamilySoldierdepentantRelation> relationPage = FamilySoldierdepentantRelation.dao
					.pageSoldierDepentant(familyId,soldierDepentantId, pageNumber, pageSize);
			
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

	private String addRelation(String familyId, String soldierDepentantId) {
		FamilySoldierdepentantRelation relation = new FamilySoldierdepentantRelation();
		
		relation.setId(Utils.create36UUID());
		relation.setFamilyId(familyId);
		relation.setSoldierDepentantId(soldierDepentantId);
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String familyId, String soldierDepentantId) {
		FamilySoldierdepentantRelation relation = FamilySoldierdepentantRelation.dao.findBy(familyId,
				soldierDepentantId);
		if (relation != null) {
			relation.setIsValid(-1);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

	private int updateRelation(String familyId, String soldierDepentantId) {
		FamilySoldierdepentantRelation relation = FamilySoldierdepentantRelation.dao.findBy(familyId,
				soldierDepentantId);
		if (relation != null) {
			relation.setSoldierDepentantId(soldierDepentantId);
			;
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

}
