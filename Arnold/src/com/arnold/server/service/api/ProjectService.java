package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Project;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.DateUtil;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class ProjectService extends BaseService {

	public Map<String, Object> addProject(String name, String typeId, double finalMoney, int year, String startTime,
			String endTime, String realStartTime, String realEndTime, String kind, String natureId, int personCount,
			int povertyCount) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Project projectModel = new Project();

			projectModel.setId(Utils.create36UUID());
			projectModel.setName(name);
			// projectModel.setTypeId(typeId);
			projectModel.setFinalMoney((double) finalMoney);
			projectModel.setYear(year);
			projectModel.setStartTime(DateUtil.string2Date(startTime, DateUtil.Y_M_D));
			projectModel.setEndTime(DateUtil.string2Date(endTime, DateUtil.Y_M_D));
			projectModel.setRealStartTime(DateUtil.string2Date(realStartTime, DateUtil.Y_M_D));
			projectModel.setRealEndTime(DateUtil.string2Date(realEndTime, DateUtil.Y_M_D));
			projectModel.setKind(kind);
			projectModel.setNatureId(natureId);
			projectModel.setPersonCount(personCount);
			projectModel.setPovertyCount(povertyCount);

			projectModel.save();

			resultMap.put(ConstUtils.R_PAGE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");

			return resultMap;

		}

		return resultMap;

	}

	public Map<String, Object> pageProject(int pageNumber, int pageSize) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Project project = new Project();

			Page<Project> projectModel = project.pageProjectByIds(pageNumber, pageSize, "");

			resultMap.put(ConstUtils.R_PAGE, projectModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");

		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");

		}

		return resultMap;

	}

	public Map<String, Object> pageProjectByIds(int pageNumber, int pageSize, String ids) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Project project = new Project();

			Page<Project> projectModel = project.pageProjectByIds(pageNumber, pageSize, ids);

			resultMap.put(ConstUtils.R_PAGE, projectModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");

		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");

		}

		return resultMap;

	}

	public Map<String, Object> updateProject(String id, String name, String typeId, double finalMoney, int year,
			String startTime, String endTime, String realStartTime, String realEndTime, String kind, String natureId,
			int personCount, int povertyCount) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Project projectModel = Project.dao.findById(id);

			if (projectModel == null) {
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个项目！");
				return resultMap;
			}

			if (!Utils.isBlankOrEmpty(name)) {
				projectModel.setName(name);
			}
			// if(!Utils.isBlankOrEmpty(typeId)){
			// projectModel.setTypeId(typeId);
			// }

			if (finalMoney == -1)
				finalMoney = 0;
			projectModel.setFinalMoney((double) finalMoney);

			if (year != -1) {
				projectModel.setYear(year);
			}

			if (!Utils.isBlankOrEmpty(startTime)) {
				projectModel.setStartTime(DateUtil.string2Date(startTime, DateUtil.Y_M_D));
			}
			if (!Utils.isBlankOrEmpty(endTime)) {
				projectModel.setEndTime(DateUtil.string2Date(endTime, DateUtil.Y_M_D));
			}
			if (!Utils.isBlankOrEmpty(realStartTime)) {
				projectModel.setRealStartTime(DateUtil.string2Date(realStartTime, DateUtil.Y_M_D));
			}
			if (!Utils.isBlankOrEmpty(realEndTime)) {
				projectModel.setRealEndTime(DateUtil.string2Date(realEndTime, DateUtil.Y_M_D));
			}

			if (!Utils.isBlankOrEmpty(kind)) {
				projectModel.setKind(kind);
			}
			if (!Utils.isBlankOrEmpty(natureId)) {
				projectModel.setNatureId(natureId);
			}
			if (personCount != -1) {
				projectModel.setPersonCount(personCount);
			}
			if (povertyCount != -1) {
				projectModel.setPovertyCount(povertyCount);
			}

			projectModel.update();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");

		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");

		}

		return resultMap;

	}

	public Map<String, Object> delProject(String id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Project projectModel = Project.dao.findById(id);

			if (projectModel == null) {
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个项目！");
				return resultMap;
			}

			// TODO:逻辑删除
			// projectModel.delete();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");

		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");

		}

		return resultMap;

	}

}
