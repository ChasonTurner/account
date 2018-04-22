package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnold.server.model.ProjectRegionRelation;
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
public class ProjectRegionRelationService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(ProjectRegionRelationService.class);

	/**
	 * 项目与地域的关系
	 * 
	 * @Method:addProjectRegionRelation
	 * @Date:2017年8月14日 下午4:02:51
	 * @Author:YangCheng
	 * @param projectId
	 * @param regionId
	 * @return
	 */
	public Map<String, Object> addProjectRegionRelation(String projectId, String regionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			String id = "";

			ProjectRegionRelation relation = ProjectRegionRelation.findBy(projectId, regionId);
			if (relation == null) {
				id = addRelation(projectId, regionId);
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
	 * 删除项目与地区的关系
	 * 
	 * @Method:deleteProjectRegionRelation
	 * @Date:2017年8月14日 下午4:03:26
	 * @Author:YangCheng
	 * @param projectId
	 * @param regionId
	 * @return
	 */
	public Map<String, Object> deleteProjectRegionRelation(String projectId, String regionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			ProjectRegionRelation relation = ProjectRegionRelation.findBy(projectId, regionId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				dataMap.put(ConstUtils.R_MSG, "查不到该扶贫人员");
			}

			deleteRelation(projectId, regionId);

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
	 * 修改项目与地区的关系
	 * 
	 * @Method:updateProjectRegionRelation
	 * @Date:2017年8月14日 下午4:03:42
	 * @Author:YangCheng
	 * @param projectId
	 * @param regionId
	 * @param oldRegionId
	 * @return
	 */
	public Map<String, Object> updateProjectRegionRelation(String projectId, String regionId, String oldRegionId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			ProjectRegionRelation relation = ProjectRegionRelation.findBy(projectId, oldRegionId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				dataMap.put(ConstUtils.R_MSG, "查不到该扶贫人员");
			}

			updateRelation(projectId, regionId, oldRegionId);

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
	 * 分页查询项目与地域的关系
	 * 
	 * @Method:pageProjectRegionRelation
	 * @Date:2017年8月14日 下午6:36:17
	 * @Author:YangCheng
	 * @param projectId
	 * @param regionId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageProjectRegionRelation(String projectId, String regionId, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {

			Page<ProjectRegionRelation> relationPage = ProjectRegionRelation.dao.pageProjectRegionRelation(projectId,
					regionId, pageNumber, pageSize);

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

	private String addRelation(String projectId, String regionId) {
		ProjectRegionRelation relation = new ProjectRegionRelation();

		relation.setId(Utils.create36UUID());
		relation.setProjectId(projectId);
		relation.setRegionId(regionId);
		relation.setIsValid(1);// 1有效
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String projectId, String regionId) {
		ProjectRegionRelation relation = ProjectRegionRelation.findBy(projectId, regionId);
		if (relation == null) {
			return -1;
		}
		relation.setIsValid(0);// 0表示删除
		relation.setUpdateTime(new Date());
		relation.update();

		return 0;
	}

	private int updateRelation(String projectId, String regionId, String oldRegionId) {
		ProjectRegionRelation relation = ProjectRegionRelation.findBy(projectId, regionId);
		if (relation == null) {
			return -1;
		}
		relation.setRegionId(regionId);
		relation.setUpdateTime(new Date());
		relation.update();

		return 0;
	}
}
