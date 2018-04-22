package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.ProjectProgress;
import com.arnold.server.service.BaseService;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.DateUtil;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class ProjectProgressService extends BaseService {

	public Page<ProjectProgress> pageProjectProgressByProjectid(int pageNumber, int pageSize,
			String projectid) {
		ProjectProgress publicProjectProgress = new ProjectProgress();
		return publicProjectProgress.pageProjectProgressByProjectid(pageNumber, pageSize, projectid);
	}
	
	public int addProjectProgress(String projectid, float progress, String note, String writer, String time) {
		try{
			ProjectProgress model = new ProjectProgress();
			model.setId(Utils.create36UUID());
			model.setProjectId(projectid);
			model.setProgress(progress);
			model.setWriter(writer);
			model.setTime(DateUtil.string2Date(time, DateUtil.Y_M_D));
			model.setNote(note);
			model.save();
			return ConstUtils.DEAL_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ConstUtils.PARAM_ERROR;
		}
	}
}
