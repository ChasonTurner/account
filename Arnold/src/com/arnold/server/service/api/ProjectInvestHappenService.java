package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.ProjectInvestHappen;
import com.arnold.server.service.BaseService;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 项目投资流水流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class ProjectInvestHappenService extends BaseService {
	
	public Page<ProjectInvestHappen> pageProjectInvestByProjectid(int pageNumber, int pageSize,
			String publicProjectid) {
		ProjectInvestHappen projectInvestHappen = new ProjectInvestHappen();
		return projectInvestHappen.pageProjectInvestByProjectid(pageNumber, pageSize, publicProjectid);
	}
	
	public Page<ProjectInvestHappen> pageProjectInvestByid(int pageNumber, int pageSize,
			String id) {
		ProjectInvestHappen projectInvestHappen = new ProjectInvestHappen();
		return projectInvestHappen.pageProjectInvestByid(pageNumber, pageSize, id);
	}
	
	public void addProjectInvest(String projectid, double investPrice, String investTypeId, Date investDate,
			String company, String writer, String nowWriter, String attachment) {
		ProjectInvestHappen model = new ProjectInvestHappen();
		model.setId(Utils.create36UUID());
		model.setProjectId(projectid);
		model.setInvestPrice(investPrice);
		model.setInvestTypeId(investTypeId);
		model.setInvestDate(investDate);
		model.setCompany(company);
		model.setWriter(writer);
		model.setCreateTime(new Date());
		model.setNowWriter(nowWriter);
		model.setNowCreateTime(new Date());
		model.setAttachment(attachment);
		model.save();
	}
	
	public void updateProjectInvest(String id, String projectid, double investPrice, String investTypeId,
			Date investDate, String company, String nowWriter, String attachment,ProjectInvestHappen model) {
		if(!Utils.isBlankOrEmpty(projectid)){
			model.setProjectId(projectid);
		}
		if(investPrice!=-1){
			model.setInvestPrice(investPrice);
		}
		if(!Utils.isBlankOrEmpty(investTypeId)){
			model.setInvestTypeId(investTypeId);
		}
		if(investDate!=null){
			model.setInvestDate(investDate);
		}
		if(!Utils.isBlankOrEmpty(company)){
			model.setCompany(company);
		}
		if(!Utils.isBlankOrEmpty(nowWriter)){
			model.setNowWriter(nowWriter);
		}
		model.setNowCreateTime(new Date());
		if(!Utils.isBlankOrEmpty(attachment)){
			model.setAttachment(attachment);
		}
		
		model.update();
	}

	public void delProjectInvest(String id,ProjectInvestHappen model){
		model.delete();
	}
}

