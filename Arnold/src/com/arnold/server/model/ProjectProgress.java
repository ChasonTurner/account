package com.arnold.server.model;

import com.arnold.server.model.base.BaseProjectProgress;
import com.jfinal.plugin.activerecord.Page;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ProjectProgress extends BaseProjectProgress<ProjectProgress> {
	public static final ProjectProgress dao = new ProjectProgress();

	public Page<ProjectProgress> pageProjectProgressByProjectid(int pageNumber, int pageSize,
			String projectid) {
		String sql = "SELECT *";
		String sqlExceptSelect = " FROM tb_project_progress t1 ";
		if(!Utils.isBlankOrEmpty(projectid)){
			sqlExceptSelect += " WHERE t1.projectId = '" + projectid + "'";
		}
		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
		
	}
}
