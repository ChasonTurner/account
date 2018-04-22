package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.FamilyBurdenRelation;
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
public class FamilyBurdenRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(FamilyBurdenRelationService.class);

	/**
	 * 增加家庭与贫困原因关系
	 * 
	 * @Method:addFamilyBurdenRelation
	 * @return
	 */
	public Map<String, Object> addFamilyBurdenRelation(String familyId, String burdenIds) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			//多个时用英文逗号隔开，这里解析
			String[] array = burdenIds.split(",");
			for(String burdenId : array) {
				FamilyBurdenRelation relation = FamilyBurdenRelation.dao.findBy(familyId, burdenId);
				if (relation == null) {
					addRelation(familyId, burdenId);
				}
			}

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return resultMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return resultMap;
		}
	}
	
	/**
	 * @Date: 2017年9月21日 下午6:38:27
	 * @author ChangGui Pan
	 * @param 
	 */
	public Map<String, Object> deleteFamilyBurdenRelation(String familyId, String burdenId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			FamilyBurdenRelation relation = FamilyBurdenRelation.dao.findBy(familyId, burdenId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(familyId, burdenId);

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
	 * 更新家庭与贫困原因的关系
	 * 
	 * @Method:updateFamilyBurdenRelation
	 * @Date:2017年8月14日 下午1:27:46
	 * @Author:YangCheng
	 * @param familyId
	 * @param causeId
	 * @return
	 */
	public Map<String, Object> updateFamilyBurdenRelation(String familyId, String burdenId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			FamilyBurdenRelation relation = FamilyBurdenRelation.dao.findBy(familyId, burdenId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(familyId, burdenId);

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
	 * 分页查询贫困原因
	 * @Method:pageFamilyBurdenRelation
	 * @Date:2017年8月14日 下午6:21:18
	 * @Author:YangCheng
	 * @param familyId
	 * @param causeId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyBurdenRelation(String familyId, String burdenId, Integer pageNumber,
			Integer pageSize) {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<FamilyBurdenRelation> relationPage = FamilyBurdenRelation.dao.pageFamilyBurdenRelation(familyId,
					burdenId, pageNumber, pageSize);

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

	private String addRelation(String familyId, String burdenId) {
		FamilyBurdenRelation relation = new FamilyBurdenRelation();

		relation.setId(Utils.create36UUID());
		relation.setFamilyId(familyId);
		relation.setBurdenId(burdenId);
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String familyId, String burdenId) {
		FamilyBurdenRelation relation = FamilyBurdenRelation.dao.findBy(familyId, burdenId);
		if (relation == null) {
			return -1;
		}
		relation.setIsValid(-1);
		relation.setUpdateTime(new Date());
		relation.update();

		return 0;
	}

	private int updateRelation(String familyId, String burdenId) {
		FamilyBurdenRelation relation = FamilyBurdenRelation.dao.findBy(familyId, burdenId);
		if (relation == null) {
			return -1;
		}
		relation.setBurdenId(burdenId);
		relation.setUpdateTime(new Date());
		relation.update();

		return 0;
	}

}
