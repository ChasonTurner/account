package com.arnold.server.service.api;

import com.arnold.server.model.Enterprise;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterpriseService extends BaseService {

	public int addEnterprise(String name, String contact, String userId, String address, String personNo, String orgId,
							String personId, String typeId, String writerId, 
							String operatorId, String remark, String phone, String email){
		
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Enterprise enterpriseModel = new Enterprise();
		enterpriseModel.setId(Utils.create36UUID());
		enterpriseModel.setName(name);
		enterpriseModel.setContact(contact);
		enterpriseModel.setAddress(address);
		enterpriseModel.setPersonNo(personNo);
		enterpriseModel.setOrgId(orgId);
		enterpriseModel.setPersonId(personId);
		enterpriseModel.setTypeId(typeId);
		enterpriseModel.setWriterId(writerId);
		enterpriseModel.setOperatorId(operatorId);
		enterpriseModel.setRemark(remark);
		enterpriseModel.setPhone(phone);
		enterpriseModel.setEmail(email);
		enterpriseModel.setCreateTime(new Date());
		enterpriseModel.setUpdateTime(new Date());
		enterpriseModel.save();

		return ConstUtils.DEAL_SUCCESS;
		
	}
	
	public Map<String, Object> pageEnterprise(int pageNumber, int pageSize,String keyWord){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Enterprise enterprise = new Enterprise();
			
			Page<Record> enterpriseModel = enterprise.pageEnterpriseByKeyWord(pageNumber, pageSize, keyWord);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_PAGE, enterpriseModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageEnterpriseByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Enterprise enterprise = new Enterprise();
			
			Page<Enterprise> enterpriseModel = enterprise.pageEnterpriseByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, enterpriseModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateEnterprise(String id,	String name, String contact, String userId, String address, String personNo, String orgId, 
			String personId, String typeId, String writerId, 
			String operatorId, String remark,String phone, String email){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Enterprise enterpriseModel = Enterprise.dao.findById(id);
			
			if(enterpriseModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个企业！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(name)){
				enterpriseModel.setName(name);;
			}
			if(!Utils.isBlankOrEmpty(contact)){
				enterpriseModel.setContact(contact);
			}
			if(!Utils.isBlankOrEmpty(address)){
				enterpriseModel.setAddress(address);;
			}
			if(!Utils.isBlankOrEmpty(personNo)){
				enterpriseModel.setPersonNo(personNo);;
			}
			if(!Utils.isBlankOrEmpty(orgId)){
				enterpriseModel.setOrgId(orgId);
			}
			if(!Utils.isBlankOrEmpty(personId)){
				enterpriseModel.setPersonId(personId);
			}
			if(!Utils.isBlankOrEmpty(typeId)){
				enterpriseModel.setTypeId(typeId);
			}

			if(!Utils.isBlankOrEmpty(userId)){
				enterpriseModel.setOperatorId(userId);
			}
			if(!Utils.isBlankOrEmpty(remark)){
				enterpriseModel.setRemark(remark);
			}

			if(!Utils.isBlankOrEmpty(phone)){
				enterpriseModel.setPhone(phone);
			}
			if(!Utils.isBlankOrEmpty(email)){
				enterpriseModel.setEmail(email);
			}
			enterpriseModel.setUpdateTime(new Date());
			enterpriseModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delEnterprise(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Enterprise enterpriseModel = Enterprise.dao.findById(id);
			
			if(enterpriseModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个企业！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			enterpriseModel.delete();
			
			Db.update("DELETE FROM tb_post WHERE orgId ='"+id+"'");
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
			
		}
		
		return resultMap;
		
	}
	
	public List<Enterprise> queryAllEnterprises(){
		return Enterprise.queryAllEnterprises();
	}

}
