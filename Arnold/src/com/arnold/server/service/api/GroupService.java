package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Group;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class GroupService extends BaseService {

	public int addGroup(String userId, String areaId){
		
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Group groupModel = new Group();
		groupModel.setId(Utils.create36UUID());
		groupModel.setUserId(userId);
		groupModel.setAreaId(areaId);

		groupModel.save();
			
		return ConstUtils.DEAL_SUCCESS;
		
	}
	
	public Map<String, Object> pageGroup(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Group group = new Group();
			
			Page<Group> groupModel = group.pageGroupByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, groupModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageGroupByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Group group = new Group();
			
			Page<Group> groupModel = group.pageGroupByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, groupModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateGroup(String id, String userId, String areaId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Group groupModel = Group.dao.findById(id);
			
			if(groupModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个工作组！");
				return resultMap;
			}
		
			if(!Utils.isBlankOrEmpty(userId)){
				groupModel.setUserId(userId);
			}
			
			if(!Utils.isBlankOrEmpty(areaId)){
				groupModel.setAreaId(areaId);
			}

			groupModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delGroup(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Group groupModel = Group.dao.findById(id);
			
			if(groupModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个工作组！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//groupModel.delete();
			
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
