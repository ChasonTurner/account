package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.LeaderDepartmentRelation;
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
public class LeaderDepartmentRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(LeaderDepartmentRelationService.class);

	/**
	 * 增加领导与部门的关系
	 * 
	 * @Method:addLeaderDepartmentRelation
	 * @Date:2017年8月14日 下午5:07:59
	 * @Author:YangCheng
	 * @param leaderId
	 * @param departmentId
	 * @param subjectId
	 * @return
	 */
	public Map<String, Object> addLeaderDepartmentRelation(String leaderId, String departmentId, String subjectId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id  = "";
			
			LeaderDepartmentRelation relation = LeaderDepartmentRelation.findBy(leaderId, subjectId);
			if (relation == null) {
				id = addRelation(leaderId, subjectId);
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
	 * 删除领导与部门的关系
	 * 
	 * @Method:delelteLeaderDepartmentRelation
	 * @Date:2017年8月14日 下午5:08:39
	 * @Author:YangCheng
	 * @param leaderId
	 * @param departmentId
	 * @param subjectId
	 * @return
	 */
	public Map<String, Object> delelteLeaderDepartmentRelation(String leaderId, String subjectId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			LeaderDepartmentRelation relation = LeaderDepartmentRelation.findBy(leaderId,subjectId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(leaderId, subjectId);

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
	 * 更新领导与部门的关系
	 * 
	 * @Method:updateLeaderDepartmentRelation
	 * @Date:2017年8月14日 下午5:08:50
	 * @Author:YangCheng
	 * @param leaderId
	 * @param departmentId
	 * @param subjectId
	 * @param oldDepartmentId
	 * @param oldSubjectId
	 * @return
	 */
	public Map<String, Object> updateLeaderDepartmentRelation(String leaderId, String departmentId, String subjectId,
			String oldDepartmentId, String oldSubjectId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			LeaderDepartmentRelation relation = LeaderDepartmentRelation.findBy(leaderId, subjectId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(leaderId, departmentId, subjectId, oldSubjectId);

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
	 * 分页查询领导与部门之间的关系
	 * @Method:pageLeaderDepartmentRelation
	 * @Date:2017年8月14日 下午6:31:06
	 * @Author:YangCheng
	 * @param leaderId
	 * @param departmentId
	 * @param subjectId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageLeaderDepartmentRelation(String leaderId, String departmentId, String subjectId,
			Integer pageNumber, Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<LeaderDepartmentRelation> relationPage = LeaderDepartmentRelation.dao
					.pageLeaderDepartmentRelation(leaderId, departmentId, subjectId, pageNumber, pageSize);

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

	private String addRelation(String leaderId, String subjectId) {
		LeaderDepartmentRelation relation = new LeaderDepartmentRelation();

		relation.setId(Utils.create36UUID());
		relation.setLeaderId(leaderId);
		relation.setSubjectId(subjectId);
		relation.setIsValid(0);// 1有效
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String leaderId, String subjectId) {
		LeaderDepartmentRelation relation = LeaderDepartmentRelation.findBy(leaderId, subjectId);
		if (relation == null) {
			return -1;
		}

		relation.setIsValid(0);
		relation.setUpdateTime(new Date());
		relation.update();

		return 0;
	}

	private int updateRelation(String leaderId, String departmentId, String subjectId,
			String oldSubjectId) {
		LeaderDepartmentRelation relation = LeaderDepartmentRelation.findBy(leaderId,oldSubjectId);
		if (relation == null) {
			return -1;
		}

		relation.setSubjectId(subjectId);
		relation.setUpdateTime(new Date());
		relation.update();

		return 0;
	}

}
