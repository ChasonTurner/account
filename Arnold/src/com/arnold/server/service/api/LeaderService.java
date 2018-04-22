package com.arnold.server.service.api;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import com.arnold.server.config.ConsConfig;
import com.arnold.server.model.Leader;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.DateUtil;
import com.huntersun.tool.HttpRequestService;
import com.huntersun.tool.Utils;
import com.huntersun.tool.exception.HttpRequestException;
import com.jfinal.plugin.activerecord.Page;

public class LeaderService extends BaseService {

	public Map<String, Object> addLeader(String leaderId, String name, String sexId, 
			Date birthday, String politicalStatusId, String educationalId, 
			String raceId, String levelId, String phone, final String iDnumber, String orgId, String remark, String writer, String helperTypeId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String id = leaderId;
		
		Leader model = Leader.dao.findById(leaderId);
		if(model != null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_EXIST_CODE_STR);
			return resultMap;
		}
		
		Leader leaderModel = new Leader();
		leaderModel.setId(id);
		if(!Utils.isBlankOrEmpty(name)) leaderModel.setName(name);
		if(!Utils.isBlankOrEmpty(sexId)) leaderModel.setSexId(sexId);
		
		if(birthday != null) {
			leaderModel.setBirthday(birthday);	
		} else {
			birthday = ArnoldUtils.getBirthdayByIDnumber(iDnumber);
			leaderModel.setBirthday(birthday);
		}
		
		if(!Utils.isBlankOrEmpty(politicalStatusId)) leaderModel.setPoliticalStatusId(politicalStatusId);
		if(!Utils.isBlankOrEmpty(educationalId)) leaderModel.setEducationalId(educationalId);
		if(!Utils.isBlankOrEmpty(raceId)) leaderModel.setRaceId(raceId);
		if(!Utils.isBlankOrEmpty(levelId)) leaderModel.setLevelId(levelId);
		if(!Utils.isBlankOrEmpty(phone)) leaderModel.setPhone(phone);
		if(!Utils.isBlankOrEmpty(iDnumber)) leaderModel.setIDnumber(iDnumber);
		if(!Utils.isBlankOrEmpty(orgId)) leaderModel.setOrgId(orgId);
		if(!Utils.isBlankOrEmpty(remark)) leaderModel.setRemark(remark);
		if(!Utils.isBlankOrEmpty(writer)) leaderModel.setWriter(writer);
		if(!Utils.isBlankOrEmpty(helperTypeId)) leaderModel.setHelperTypeId(helperTypeId);
		leaderModel.setCreateTime(new Date());
		leaderModel.save();
		
		//resultMap.put("id", id);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
				
		return resultMap;	
	}
	
	public Map<String, Object> addLeader2(String userName, String name, String sexId, 
			String birthday, String politicalStatusId, String educationalId, 
			String raceId, String levelId, String pwd, String appId, String phone){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			String url = ConsConfig.POSEIDON_HOST + "/iuser/add_user";
			
			Map<String, Object> dataValue = new HashMap<String, Object>();
			
			dataValue.put("userName", userName);
			dataValue.put("pwd", pwd);
			dataValue.put("realName", name);
			dataValue.put("phone", phone);
			dataValue.put("appIdP", appId);
			
			String strResult = HttpRequestService.httpPost(url, dataValue);
			
			JSONObject jsonResult = new JSONObject(strResult);
		
			String id = "";
			
			if(jsonResult.getInt("rc") == 0 || jsonResult.getInt("rc") == 10010){
				
				id = jsonResult.getString("userId");
				
			}else if(jsonResult.getInt("rc") == 10011){
				
				resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ERROR_APPID);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.ERROR_APPID_STR);
				
				return resultMap;
				
			}
			
			//Map<String, Object> resultMap = new HashMap<String, Object>();
			
			Leader leaderModel = new Leader();
			leaderModel.setId(id);
			leaderModel.setName(name);
			leaderModel.setSexId(sexId);
		
			leaderModel.setBirthday(DateUtil.string2Date(birthday, DateUtil.Y_M_D));
		
			leaderModel.setPoliticalStatusId(politicalStatusId);
			leaderModel.setEducationalId(educationalId);
			leaderModel.setRaceId(raceId);
			leaderModel.setLevelId(levelId);
			leaderModel.setPhone(phone);
			
			leaderModel.save();
			
			resultMap.put("memberId", id);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功！");		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败！");
			
			return resultMap;
			
		} catch (HttpRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败！");
			
			return resultMap;
		}
				
		return resultMap;		
	}
	
	public Map<String, Object> pageLeader(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{			
			Leader leader = new Leader();
			
			Page<Leader> leaderModel = leader.pageLeaderByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, leaderModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");		
		}catch(Exception e){		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;		
	}
	
	public Map<String, Object> pageLeaderByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Leader leader = new Leader();
			
			Page<Leader> leaderModel = leader.pageLeaderByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, leaderModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;		
	}
	
	public Map<String, Object> updateLeader(String id, String name, Date birthday, String sexId,
			String politicalStatusId, String educationalId, String raceId, String levelId, String phone,
			String orgId, String iDnumber, String remark, String operator){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Leader leaderModel = Leader.dao.findById(id);
			
			if(leaderModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
		
			if(!Utils.isBlankOrEmpty(name)){
				leaderModel.setName(name);
			}
			if(!Utils.isBlankOrEmpty(sexId)){
				leaderModel.setSexId(sexId);
			}
			if(birthday != null){
				leaderModel.setBirthday(birthday);
			}
			if(!Utils.isBlankOrEmpty(politicalStatusId)){
				leaderModel.setPoliticalStatusId(politicalStatusId);
			}
			if(!Utils.isBlankOrEmpty(educationalId)){
				leaderModel.setEducationalId(educationalId);
			}
			if(!Utils.isBlankOrEmpty(raceId)){
				leaderModel.setRaceId(raceId);
			}
			if(!Utils.isBlankOrEmpty(levelId)){
				leaderModel.setLevelId(levelId);
			}
			if(!Utils.isBlankOrEmpty(phone)){
				leaderModel.setPhone(phone);
			}
			if(!Utils.isBlankOrEmpty(iDnumber)){
				leaderModel.setIDnumber(iDnumber);
			}
			if(!Utils.isBlankOrEmpty(orgId)){
				leaderModel.setOrgId(orgId);
			}
			if(!Utils.isBlankOrEmpty(remark)){
				leaderModel.setRemark(remark);
			}
			if(!Utils.isBlankOrEmpty(operator)){
				leaderModel.setOperator(operator);
			}
			
			leaderModel.setUpdateTime(new Date());
			
			leaderModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			
		}catch(Exception e){			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			
		}
		
		return resultMap;	
	}
	
	public Map<String, Object> delLeader(String leaderId){	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Leader leaderModel = Leader.dao.findById(leaderId);
			if(leaderModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			
			//TODO:逻辑删除
			leaderModel.setIsValid(-1);
			leaderModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
		
	}

	public Map<String, Object> forbidLeader(String id, Integer isValid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Leader leaderModel = Leader.dao.findById(id);
			if(leaderModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			
			//TODO:逻辑删除 0正常，1禁用，-1删除
			leaderModel.setIsValid(isValid);
			leaderModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}
	
	public Map<String, Object> findAllLeader(String helperTypeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Leader> list = Leader.dao.findAllLeader(helperTypeId);
		
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public List<Leader> findAllLeaders() {
		List<Leader> list = Leader.findAllLeaders();
		return list;
	}
	public Leader findByPhone(String phone) {
		Leader leader = Leader.findLeaderByPhone(phone);
		return leader;
	}
	public Leader findById(String id) {
		Leader leader = Leader.dao.findById(id);
		return leader;
	}
}
