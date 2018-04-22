package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.ProjectHelp;
import com.arnold.server.model.PublicService;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.DateUtil;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class ProjectHelpService extends BaseService {

	public int addHelpProject(String name, String cotentAndArea, String kind, String helpType, String villageName,
			Double fundation, Double departmentMoney, Double selfMoney, Double finalMoney, int year, String startTime, String realStartTime,
			String endTime, String realEndTime, String writer, String nowWriter){
		try {	
			ProjectHelp model = new ProjectHelp();
			model.setId(Utils.create36UUID());
			model.setName(name);
			model.setCotentAndArea(cotentAndArea);
			model.setKind(kind);
			model.setHelpType(helpType);
			model.setVillageName(villageName);
			model.setFundation((double)fundation);
			model.setDepartmentMoney((double)departmentMoney);
			model.setSelfMoney((double)selfMoney);
			model.setFinalMoney((double)finalMoney);
			model.setPovertyCount(0);
			model.setPersonCount(0);
			model.setYear(year);
			model.setStartTime(DateUtil.string2Date(startTime, DateUtil.Y_M_D));
			model.setEndTime(DateUtil.string2Date(endTime, DateUtil.Y_M_D));
			model.setRealStartTime(DateUtil.string2Date(realStartTime, DateUtil.Y_M_D));
			model.setRealEndTime(DateUtil.string2Date(realEndTime, DateUtil.Y_M_D));
			model.setMontheMoney(0D);
			model.setCompleteMoney(0D);
			model.setAllProgress(0);
			model.setWriter(writer);
			model.setCreateTime(new Date());
			model.setNowWriter(nowWriter);
			model.setNowCreateTime(new Date());
			model.setType("help");
			
			model.save();
			
			return ConstUtils.DEAL_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ConstUtils.PARAM_ERROR;
		}
	}
	
	public Map<String, Object> pageHelpProject(int pageNumber, int pageSize){
		
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
	
	public Map<String, Object> pageHelpProjectByIds(int pageNumber, int pageSize, String ids){
		
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
	
	public Map<String, Object> updateHelpProject(String id,String name, String cotentAndArea, String kind, String helpType, String villageName,
			double fundation, double departmentMoney, double selfMoney, int year, String startTime, String realStartTime,
			String endTime, String realEndTime, String writer, String nowWriter,Double finalMoney,int povertyCount,
			int personCount,double montheMoney,int completeMoney,float allProgress){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProjectHelp model = ProjectHelp.dao.findById(id);
			
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个公共设施！");
				return resultMap;
			}

			if(!Utils.isBlankOrEmpty(name)){
				model.setName(name);
			}
			if(!Utils.isBlankOrEmpty(cotentAndArea)){
				model.setCotentAndArea(cotentAndArea);
			}
			if(!Utils.isBlankOrEmpty(kind)){
				model.setKind(kind);
			}
			if(!Utils.isBlankOrEmpty(helpType)){
				model.setNatureId(helpType);
			}
			if(!Utils.isBlankOrEmpty(villageName)){
				model.setVillageName(villageName);
			}
			if(fundation != -1){
				model.setFundation((double)fundation);
			}
			if(departmentMoney != -1){
				model.setDepartmentMoney((double)departmentMoney);
			}
			if(selfMoney != -1){
				model.setSelfMoney((double)selfMoney);
			}
			if(finalMoney != -1){
				model.setFinalMoney((double)finalMoney);
			}
			if(povertyCount != -1){
				model.setPovertyCount(povertyCount);
			}
			if(personCount != -1){
				model.setPersonCount(personCount);
			}
			if(year != -1){
				model.setYear(year);
			}
			if(!Utils.isBlankOrEmpty(startTime)){
				model.setStartTime(DateUtil.string2Date(startTime, DateUtil.Y_M_D));
			}
			if(!Utils.isBlankOrEmpty(endTime)){
				model.setEndTime(DateUtil.string2Date(endTime, DateUtil.Y_M_D));
			}
			if(!Utils.isBlankOrEmpty(realStartTime)){
				model.setRealStartTime(DateUtil.string2Date(realStartTime, DateUtil.Y_M_D));
			}
			if(!Utils.isBlankOrEmpty(realEndTime)){
				model.setRealEndTime(DateUtil.string2Date(realEndTime, DateUtil.Y_M_D));
			}
			if(montheMoney != -1){
				model.setMontheMoney(montheMoney);
			}
//			if(completeMoney != -1){
//				model.setCompleteMoney(completeMoney);
//			}
			if(allProgress != -1){
				model.setAllProgress((int)allProgress);
			}
			if(!Utils.isBlankOrEmpty(nowWriter)){
				model.setNowWriter(nowWriter);
			}
			model.setNowCreateTime(new Date());
			
			model.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		return resultMap;
	}
	
	public Map<String, Object> delHelpProject(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProjectHelp model = ProjectHelp.dao.findById(id);
			
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个公共设施！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			model.delete();
			
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
