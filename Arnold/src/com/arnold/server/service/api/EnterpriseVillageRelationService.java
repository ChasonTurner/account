package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.EnterpriseVillageRelation;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class EnterpriseVillageRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(EnterpriseVillageRelationService.class);

	/**
	 * 增加企业与贫困村的关系
	 * 
	 * @Method:addEnterpriseVillageRelation
	 * @Date:2017年8月14日 下午4:31:19
	 * @Author:YangCheng
	 * @param enterpriseId
	 * @param villageId
	 * @return
	 */
	public Map<String, Object> addEnterpriseVillageRelation(String enterpriseId, String villageId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id  = "";
			
			EnterpriseVillageRelation relation = EnterpriseVillageRelation.findBy(enterpriseId, villageId);
			if (relation == null) {
				id = addRelation(enterpriseId, villageId);
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
	 * 删除企业与贫困村的关系
	 * 
	 * @Method:deleteEnterpriseVillageRelation
	 * @Date:2017年8月14日 下午4:31:48
	 * @Author:YangCheng
	 * @param enterpriseId
	 * @param villageId
	 * @return
	 */
	public Map<String, Object> deleteEnterpriseVillageRelation(String enterpriseId, String villageId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			EnterpriseVillageRelation relation = EnterpriseVillageRelation.findBy(enterpriseId, villageId);
			if (relation != null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(enterpriseId, villageId);

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
	 * 修改企业与贫困村的关系
	 * 
	 * @Method:updateEnterpriseVillageRelation
	 * @Date:2017年8月14日 下午4:32:03
	 * @Author:YangCheng
	 * @param enterpriseId
	 * @param villageId
	 * @return
	 */
	public Map<String, Object> updateEnterpriseVillageRelation(String enterpriseId, String villageId,
			String oldVillageId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			EnterpriseVillageRelation relation = EnterpriseVillageRelation.findBy(enterpriseId, oldVillageId);
			if (relation != null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(enterpriseId, villageId, oldVillageId);

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
	 * 分页查询企业与贫困村的关系
	 * @Method:pageEnterpriseVillageRelation
	 * @Date:2017年8月14日 下午6:19:10
	 * @Author:YangCheng
	 * @param villageId
	 * @param enterpriseId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageEnterpriseVillageRelation(String villageId, String enterpriseId, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<EnterpriseVillageRelation> relationPage = EnterpriseVillageRelation.dao
					.pageEnterpriseVillageRelation(villageId, enterpriseId, pageNumber, pageSize);

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

	private String addRelation(String enterpriseId, String villageId) {
		EnterpriseVillageRelation relation = new EnterpriseVillageRelation();

		relation.setId(Utils.create36UUID());
		relation.setEnterpriseId(enterpriseId);
		relation.setVillageId(villageId);
		relation.setIsValid(1);// 1有效
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String enterpriseId, String villageId) {
		EnterpriseVillageRelation relation = EnterpriseVillageRelation.findBy(enterpriseId, villageId);

		if (relation != null) {
			relation.setIsValid(0);// 0表示删除
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return -1;
		}
	}

	private int updateRelation(String enterpriseId, String villageId, String oldVillageId) {
		EnterpriseVillageRelation relation = EnterpriseVillageRelation.findBy(enterpriseId, oldVillageId);

		if (relation != null) {
			relation.setVillageId(oldVillageId);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return -1;
		}
	}
}
