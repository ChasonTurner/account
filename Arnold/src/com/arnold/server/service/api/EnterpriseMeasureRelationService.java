package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.EnterpriseMeasureRelation;
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
public class EnterpriseMeasureRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(EnterpriseMeasureRelationService.class);

	/**
	 * 增加企业与帮扶措施的关系
	 * 
	 * @Method:addEnterpriseMeasureRelation
	 * @Date:2017年8月14日 上午11:47:16
	 * @Author:YangCheng
	 * @param enterpriseId
	 * @param measureId
	 * @return
	 */
	public Map<String, Object> addEnterpriseMeasureRelation(String enterpriseId, String measureId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id  = "";
			
			EnterpriseMeasureRelation relation = EnterpriseMeasureRelation.dao.findBy(enterpriseId, measureId);
			if (relation == null) {
				id = addRelation(enterpriseId, measureId);
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
	 * 删除企业与帮扶措施的关系
	 * 
	 * @Method:delEnterpriseMeasureRelation
	 * @Date:2017年8月14日 上午11:47:44
	 * @Author:YangCheng
	 * @param enterpriseId
	 * @param measureId
	 * @return
	 */
	public Map<String, Object> deleteEnterpriseMeasureRelation(String enterpriseId, String measureId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			EnterpriseMeasureRelation relation = EnterpriseMeasureRelation.dao.findBy(enterpriseId, measureId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(enterpriseId, measureId);

			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return dataMap;
		} catch (Exception e) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 更新企业与帮扶措施的关系
	 * 
	 * @Method:updateEnterpriseMeasureRelation
	 * @Date:2017年8月14日 上午11:48:02
	 * @Author:YangCheng
	 * @param enterpriseId
	 * @param measureId
	 * @return
	 */
	public Map<String, Object> updateEnterpriseMeasureRelation(String enterpriseId, String measureId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			EnterpriseMeasureRelation relation = EnterpriseMeasureRelation.dao.findBy(enterpriseId, measureId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(enterpriseId, measureId);

			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return dataMap;
		} catch (Exception e) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 查询企业与帮扶措施的关系
	 * @Method:pageMeasure
	 * @Date:2017年8月14日 下午6:16:23
	 * @Author:YangCheng
	 * @param enterpriseId
	 * @param measureId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageMeasure(String enterpriseId, String measureId, Integer pageNumber,
			Integer pageSize) {

		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<EnterpriseMeasureRelation> relationPage = EnterpriseMeasureRelation.dao.pageMeasure(enterpriseId,
					measureId, pageNumber, pageSize);

			dataMap.put(ConstUtils.R_PAGE, relationPage);
			dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			dataMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			return dataMap;
		} catch (Exception e) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	private String addRelation(String enterpriseId, String measureId) {
		EnterpriseMeasureRelation relation = new EnterpriseMeasureRelation();

		relation.setId(Utils.create36UUID());
		relation.setEnterpriseId(enterpriseId);
		relation.setMeasureId(measureId);
		relation.setIsValid(1);// 1有效
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int updateRelation(String enterpriseId, String measureId) {
		EnterpriseMeasureRelation relation = EnterpriseMeasureRelation.findFirstByEnterprise(enterpriseId);

		if (relation != null) {
			relation.setMeasureId(measureId);
			relation.setUpdateTime(new Date());
			relation.update();

			return 0;
		} else {
			return -1;
		}
	}

	private int deleteRelation(String enterpriseId, String measureId) {
		EnterpriseMeasureRelation relation = EnterpriseMeasureRelation.findFirstByEnterprise(enterpriseId);

		if (relation != null) {
			relation.setIsValid(0);
			relation.setUpdateTime(new Date());
			relation.update();

			return 0;
		} else {
			return -1;
		}
	}

}
