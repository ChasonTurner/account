package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.TrainSpeaker;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class TrainSpeakerService extends BaseService {
	
	public Page<TrainSpeaker> pageTrainSpeakerByTrainid(int pageNumber, int pageSize,
			String trainid) {
		TrainSpeaker trainSpeaker = new TrainSpeaker();
		return trainSpeaker.pageTrainSpeakerByTrainid(pageNumber, pageSize, trainid);
	}
	
	public Page<TrainSpeaker> pageTrainSpeaker(int pageNumber, int pageSize,
			String id) {
		TrainSpeaker trainSpeaker = new TrainSpeaker();
		return trainSpeaker.pageTrainSpeaker(pageNumber, pageSize, id);
	}
	
	public void addTrainSpeaker(String trainid, String name, String sex, int age, String telephone, String company ,
			String position, String department, String address, String writer, String nowWriter) {
		TrainSpeaker model = new TrainSpeaker();
		model.setId(Utils.create36UUID());
		model.setTrainid(trainid);
		model.setName(name);
		model.setSexId(sex);
		model.setAge(age);
		model.setTelephone(telephone);
		model.setCompany(company);
		model.setPosition(position);
		model.setDepartment(department);
		model.setAddress(address);
		model.setWriter(writer);
		model.setCreateTime(new Date());
		model.setNowWriter(nowWriter);
		model.setNowCreateTime(new Date());
		model.save();
	}
	
	public Map<String, Object> updateTrainSpeaker(String id, String name, String sex, int age, String telephone, String company ,
			String position, String department, String address, String nowWriter) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{
			TrainSpeaker model = TrainSpeaker.dao.findById(id);
			
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个公共设施！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(name)){
				model.setName(name);
			}
			if(!Utils.isBlankOrEmpty(sex)){
				model.setId(sex);
			}
			if(age!=-1){
				model.setAge(age);
			}
			if(!Utils.isBlankOrEmpty(telephone)){
				model.setTelephone(telephone);
			}
			if(!Utils.isBlankOrEmpty(company)){
				model.setCompany(company);
			}
			if(!Utils.isBlankOrEmpty(position)){
				model.setPosition(position);
			}
			if(!Utils.isBlankOrEmpty(department)){
				model.setDepartment(department);
			}
			if(!Utils.isBlankOrEmpty(address)){
				model.setAddress(address);
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

	public Map<String, Object> delTrainSpeaker(String id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainSpeaker model = TrainSpeaker.dao.findById(id);
			
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个主讲人！");
				return resultMap;
			}
			
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

