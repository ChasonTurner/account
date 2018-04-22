package com.arnold.server.service.api;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Education;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.DateUtil;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class EducationService extends BaseService {

	public Map<String, Object> addEducation(String typeId, String orgId, int number, String startTime, 
			String endTime, int personCount, String className, String name, String major, String address,
			String writer, String nowWriter){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			Education educationModel = new Education();
			educationModel.setId(Utils.create36UUID());
			educationModel.setTypeId(typeId);
			educationModel.setOrgId(orgId);
			
			educationModel.setStartTime(DateUtil.string2Date(startTime, DateUtil.Y_M_D));
			educationModel.setEndTime(DateUtil.string2Date(endTime, DateUtil.Y_M_D));
			educationModel.setPersonCount(0);
			educationModel.setName(name);
			educationModel.setMajor(major);
			educationModel.setAddress(address);
			educationModel.setClassName(className);
			
			educationModel.setWriter(writer);
			educationModel.setCreateTime(new Date());
			educationModel.setNowWriter(nowWriter);
			educationModel.setNowCreateTime(new Date());
		
			educationModel.save();
			
			resultMap.put(ConstUtils.R_PAGE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		} catch (ParseException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
			return resultMap;
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageEducation(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Education education = new Education();
			
			Page<Education> educationModel = education.pageEducationByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, educationModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageEducationByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Education education = new Education();
			
			Page<Education> educationModel = education.pageEducationByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, educationModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageEducationByTypeAndKey(int pageNumber, int pageSize, String typeid, String KeyWord){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Education education = new Education();
			
			Page<Education> educationModel = education.pageEducationByTypeAndKey(pageNumber, pageSize ,typeid, KeyWord);
			
			resultMap.put(ConstUtils.R_PAGE, educationModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateEducation(String id, String typeId, String orgId, int number, 
			String startTime, String endTime, int personCount, String className, 
			String name, String major, String address, String nowWriter){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Education educationModel = Education.dao.findById(id);
			
			if(educationModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个教育！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(typeId)){
				educationModel.setTypeId(typeId);
			}
			if(!Utils.isBlankOrEmpty(orgId)){
				educationModel.setOrgId(orgId);
			}
			if(number != -1){
				educationModel.setNumber(number);
			}
			if(!Utils.isBlankOrEmpty(startTime)){
				educationModel.setStartTime(DateUtil.string2Date(startTime, DateUtil.Y_M_D));
			}
			if(!Utils.isBlankOrEmpty(endTime)){
				educationModel.setEndTime(DateUtil.string2Date(endTime, DateUtil.Y_M_D));
			}
			if(personCount != -1){
				educationModel.setPersonCount(personCount);
			}
			if(!Utils.isBlankOrEmpty(name)){
				educationModel.setName(name);
			}
			if(!Utils.isBlankOrEmpty(major)){
				educationModel.setMajor(major);
			}
			if(!Utils.isBlankOrEmpty(address)){
				educationModel.setAddress(address);
			}
			if(!Utils.isBlankOrEmpty(className)){
				educationModel.setClassName(className);
			}
			if(!Utils.isBlankOrEmpty(nowWriter)){
				educationModel.setNowWriter(nowWriter);
			}
			educationModel.setNowCreateTime(new Date());
			
			educationModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delEducation(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Education educationModel = Education.dao.findById(id);
			
			if(educationModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个教育！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			educationModel.delete();
			
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
