package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.ProjectInvestHappen;
import com.arnold.server.model.ProjectProgress;
import com.arnold.server.model.PublicService;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.DateUtil;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class PublicServiceService extends BaseService {

	public int addPublicService(String name, String cotentAndArea, String kind, String natureId, String villageName,
			Double fundation, Double departmentMoney, Double selfMoney, int year, String startTime, String realStartTime,
			String endTime, String realEndTime, String writer, String nowWriter){
		try {	
			PublicService publicServiceModel = new PublicService();
			publicServiceModel.setId(Utils.create36UUID());
			publicServiceModel.setName(name);
			publicServiceModel.setCotentAndArea(cotentAndArea);
			publicServiceModel.setKind(kind);
			publicServiceModel.setNatureId(natureId);
			publicServiceModel.setVillageName(villageName);
			publicServiceModel.setFundation(fundation);
			publicServiceModel.setDepartmentMoney(departmentMoney);
			publicServiceModel.setSelfMoney(selfMoney);
			publicServiceModel.setFinalMoney(fundation+departmentMoney+selfMoney);
			publicServiceModel.setPovertyCount(0);
			publicServiceModel.setPersonCount(0);
			publicServiceModel.setYear(year);
			publicServiceModel.setStartTime(DateUtil.string2Date(startTime, DateUtil.Y_M_D));
			publicServiceModel.setEndTime(DateUtil.string2Date(endTime, DateUtil.Y_M_D));
			publicServiceModel.setRealStartTime(DateUtil.string2Date(realStartTime, DateUtil.Y_M_D));
			publicServiceModel.setRealEndTime(DateUtil.string2Date(realEndTime, DateUtil.Y_M_D));
			publicServiceModel.setMontheMoney((double)0);
			publicServiceModel.setCompleteMoney((double) 0);
			publicServiceModel.setAllProgress(0);
			publicServiceModel.setWriter(writer);
			publicServiceModel.setCreateTime(new Date());
			publicServiceModel.setNowWriter(nowWriter);
			publicServiceModel.setNowCreateTime(new Date());
			publicServiceModel.setType("public");
			
			publicServiceModel.save();
			
			return ConstUtils.DEAL_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ConstUtils.PARAM_ERROR;
		}
	}
	
	public Map<String, Object> pagePublicService(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			PublicService publicService = new PublicService();
			
			Page<PublicService> publicServiceModel = publicService.pagePublicServiceByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, publicServiceModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pagePublicServiceByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			PublicService publicService = new PublicService();
			
			Page<PublicService> publicServiceModel = publicService.pagePublicServiceByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, publicServiceModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updatePublicService(String id,String name, String cotentAndArea, String kind, String natureId, String villageName,
			Double fundation, Double departmentMoney, Double selfMoney, int year, String startTime, String realStartTime,
			String endTime, String realEndTime, String writer, String nowWriter,int finalMoney,int povertyCount,
			int personCount,double montheMoney,int completeMoney,float allProgress){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			PublicService publicServiceModel = PublicService.dao.findById(id);
			
			if(publicServiceModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个公共设施！");
				return resultMap;
			}

			if(!Utils.isBlankOrEmpty(name)){
				publicServiceModel.setName(name);
			}
			if(!Utils.isBlankOrEmpty(cotentAndArea)){
				publicServiceModel.setCotentAndArea(cotentAndArea);
			}
			if(!Utils.isBlankOrEmpty(kind)){
				publicServiceModel.setKind(kind);
			}
			if(!Utils.isBlankOrEmpty(natureId)){
				publicServiceModel.setNatureId(natureId);
			}
			if(!Utils.isBlankOrEmpty(villageName)){
				publicServiceModel.setVillageName(villageName);
			}
			if(fundation != -1){
				publicServiceModel.setFundation(fundation);
			}
			if(departmentMoney != -1){
				publicServiceModel.setDepartmentMoney(departmentMoney);
			}
			if(selfMoney != -1){
				publicServiceModel.setSelfMoney(selfMoney);
			}
			publicServiceModel.setFinalMoney(fundation+departmentMoney+selfMoney);
			if(povertyCount != -1){
				publicServiceModel.setPovertyCount(povertyCount);
			}
			if(personCount != -1){
				publicServiceModel.setPersonCount(personCount);
			}
			if(year != -1){
				publicServiceModel.setYear(year);
			}
			if(!Utils.isBlankOrEmpty(startTime)){
				publicServiceModel.setStartTime(DateUtil.string2Date(startTime, DateUtil.Y_M_D));
			}
			if(!Utils.isBlankOrEmpty(endTime)){
				publicServiceModel.setEndTime(DateUtil.string2Date(endTime, DateUtil.Y_M_D));
			}
			if(!Utils.isBlankOrEmpty(realStartTime)){
				publicServiceModel.setRealStartTime(DateUtil.string2Date(realStartTime, DateUtil.Y_M_D));
			}
			if(!Utils.isBlankOrEmpty(realEndTime)){
				publicServiceModel.setRealEndTime(DateUtil.string2Date(realEndTime, DateUtil.Y_M_D));
			}
			if(montheMoney != -1){
				publicServiceModel.setMontheMoney(montheMoney);
			}
//			if(completeMoney != -1){
//				publicServiceModel.setCompleteMoney(completeMoney);
//			}
			if(allProgress != -1){
				publicServiceModel.setAllProgress((int)allProgress);
			}
			if(!Utils.isBlankOrEmpty(nowWriter)){
				publicServiceModel.setNowWriter(nowWriter);
			}
			publicServiceModel.setNowCreateTime(new Date());
			
			publicServiceModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		return resultMap;
	}
	
	public Map<String, Object> updateProjectInvest(String projectid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			PublicService publicServiceModel = PublicService.dao.findById(projectid);
			if(publicServiceModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个公共设施！");
				return resultMap;
			}
			
			ProjectInvestHappen projectInvestHappen = new ProjectInvestHappen();
			Page<ProjectInvestHappen> invests = projectInvestHappen.pageProjectInvestByProjectid(1, 10000, projectid);
			
			double price = 0;
			for(ProjectInvestHappen invest:invests.getList()){
				price = price+invest.getInvestPrice();
			}
			if(price!=0){
				publicServiceModel.setCompleteMoney(price);
			}
			
			publicServiceModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		return resultMap;
	}

	public Map<String, Object> updateProjectProgress(String projectid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			PublicService publicServiceModel = PublicService.dao.findById(projectid);
			if(publicServiceModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个公共设施！");
				return resultMap;
			}
			
			ProjectProgress projectProgress = new ProjectProgress();
			Page<ProjectProgress> progress = projectProgress.pageProjectProgressByProjectid(1, 10000, projectid);
			
			float tprogress = 0;
			long time = 0;
			for(ProjectProgress m:progress.getList()){
				if(m.getTime().getTime()>time){
					tprogress = m.getProgress();
				}
			}
			if(tprogress!=0){
				publicServiceModel.setAllProgress((int)tprogress);
			}
			
			publicServiceModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		return resultMap;
	}
	
	public Map<String, Object> delPublicService(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			PublicService publicServiceModel = PublicService.dao.findById(id);
			
			if(publicServiceModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个公共设施！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//publicServiceModel.delete();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
			
		}
		
		return resultMap;
		
	}
	
}
