package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.OrgMemberRelation;
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
public class OrgMemberRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(OrgMemberRelationService.class);

	/**
	 * 增加成员与组织的关系
	 * 
	 * @Method:delOrgMemberRelation
	 * @Date:2017年8月14日 下午3:46:11
	 * @Author:YangCheng
	 * @param memberId
	 * @param orgId
	 * @return
	 */
	public Map<String, Object> addOrgMemberRelation(String memberId, String orgId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id  = "";
			
			OrgMemberRelation relation = OrgMemberRelation.findBy(memberId, orgId);
			if (relation == null) {
				id = addRelation(memberId, orgId);
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
	 * 删除成员与组织的关系
	 * 
	 * @Method:deleteOrgMemberRelation
	 * @Date:2017年8月14日 下午3:47:33
	 * @Author:YangCheng
	 * @param memberId
	 * @param orgId
	 * @return
	 */
	public Map<String, Object> deleteOrgMemberRelation(String memberId, String orgId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			OrgMemberRelation relation = OrgMemberRelation.findBy(memberId, orgId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				dataMap.put(ConstUtils.R_MSG, "查不到该扶贫人员");
			}

			deleteRelation(memberId, orgId);

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
	 * 更新成员与组织的关系
	 * 
	 * @Method:updateOrgMemberRelation
	 * @Date:2017年8月14日 下午3:48:45
	 * @Author:YangCheng
	 * @param memberId
	 * @param orgId
	 * @param oldOrgId
	 * @return
	 */
	public Map<String, Object> updateOrgMemberRelation(String memberId, String orgId, String oldOrgId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			OrgMemberRelation relation = OrgMemberRelation.findBy(memberId, oldOrgId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				dataMap.put(ConstUtils.R_MSG, "查不到该扶贫人员");
			}

			updateRelation(memberId, orgId, oldOrgId);

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
	 * 分页查询成员与组织的关系
	 * 
	 * @Method:pageOrgMemberRelation
	 * @Date:2017年8月14日 下午6:33:18
	 * @Author:YangCheng
	 * @param orgId
	 * @param memberId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageOrgMemberRelation(String orgId, String memberId, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<OrgMemberRelation> relationPage = OrgMemberRelation.dao.pageOrgMemberRelation(orgId, memberId, pageNumber,
					pageSize);

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

	private String addRelation(String memberId, String orgId) {
		OrgMemberRelation relation = new OrgMemberRelation();

		relation.setId(Utils.create36UUID());
		relation.setMemberId(memberId);
		relation.setOrgId(orgId);
		relation.setIsValid(1);// 1有效
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String memberId, String orgId) {
		OrgMemberRelation relation = OrgMemberRelation.findBy(memberId, orgId);

		if (relation != null) {
			relation.setIsValid(0);// 0表示删除
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return -1;
		}
	}

	private int updateRelation(String memberId, String orgId, String oldOrgId) {
		OrgMemberRelation relation = OrgMemberRelation.findBy(memberId, oldOrgId);

		if (relation != null) {
			relation.setOrgId(oldOrgId);
			relation.setUpdateTime(new Date());
			relation.update();
			return 0;
		} else {
			return -1;
		}
	}
}
