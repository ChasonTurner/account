package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Underwriter;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class UnderWriterService extends BaseService {

	public int addUnderWriter(String orgId, String administrativeRegionId, String familyId, String userId,
								String writer, String operator, 
								String typeId, String contactPerson, int remain){
		
		
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Underwriter underwriterModel = new Underwriter();
		underwriterModel.setId(Utils.create36UUID());
		underwriterModel.setOrgId(orgId);
		underwriterModel.setAdministrativeRegionId(administrativeRegionId);
		underwriterModel.setFamilyId(familyId);
		underwriterModel.setUserId(userId);
		underwriterModel.setWriter(writer);
		underwriterModel.setCreateTime(new Date());
		underwriterModel.setOperator(operator);
		underwriterModel.setOperatorTime(new Date());
		underwriterModel.setTypeId(typeId);
		underwriterModel.setContactPerson(contactPerson);
		underwriterModel.setRemain(remain);
		
		underwriterModel.save();
			
		return ConstUtils.DEAL_SUCCESS;
		
	}
	
	public Map<String, Object> pageUnderWriter(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Underwriter underwriter = new Underwriter();
			
			Page<Underwriter> underwriterModel = underwriter.pageUnderwriterByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, underwriterModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageUnderWriterByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Underwriter underwriter = new Underwriter();
			
			Page<Underwriter> underwriterModel = underwriter.pageUnderwriterByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, underwriterModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateUnderWriter(String id, String orgId, String administrativeRegionId, 
			String familyId, String userId,
			String writer, String operator, 
			String typeId, String contactPerson, int remain){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Underwriter underwriterModel = Underwriter.dao.findById(id);
			
			if(underwriterModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个包销产业！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(orgId)){
				underwriterModel.setOrgId(orgId);
			}
			if(!Utils.isBlankOrEmpty(administrativeRegionId)){
				underwriterModel.setAdministrativeRegionId(administrativeRegionId);
			}
			if(!Utils.isBlankOrEmpty(familyId)){
				underwriterModel.setFamilyId(familyId);
			}
			if(!Utils.isBlankOrEmpty(userId)){
				underwriterModel.setUserId(userId);
			}
			if(!Utils.isBlankOrEmpty(writer)){
				underwriterModel.setWriter(writer);
			}
			
			//underwriterModel.setCreateTime(new Date());
			
			if(!Utils.isBlankOrEmpty(operator)){
				underwriterModel.setOperator(operator);
			}
			
			underwriterModel.setOperatorTime(new Date());
			
			if(!Utils.isBlankOrEmpty(typeId)){
				underwriterModel.setTypeId(typeId);
			}
			
			if(!Utils.isBlankOrEmpty(contactPerson)){
				underwriterModel.setContactPerson(contactPerson);
			}
			
			if(remain != -1){
				underwriterModel.setRemain(remain);;
			}
			
			underwriterModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delUnderWriter(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Underwriter underwriterModel = Underwriter.dao.findById(id);
			
			if(underwriterModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个包销产业！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//underwriterModel.delete();
			
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
