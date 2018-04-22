package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.WorkPost;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class WorkPostService extends BaseService {

	public int addWorkPost(String postId, String projectId){
	
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		
		WorkPost porkPostModel = new WorkPost();
		porkPostModel.setId(Utils.create36UUID());
		porkPostModel.setPostId(postId);
		porkPostModel.setProjectId(projectId);;
		
		porkPostModel.save();
			
		return ConstUtils.DEAL_SUCCESS;
		
	}
	
	public Map<String, Object> pageWorkPost(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			WorkPost porkPost = new WorkPost();
			
			Page<WorkPost> porkPostModel = porkPost.pageWorkPostByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, porkPostModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageWorkPostByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			WorkPost porkPost = new WorkPost();
			
			Page<WorkPost> porkPostModel = porkPost.pageWorkPostByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, porkPostModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateWorkPost(String id, String postId, String projectId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			WorkPost porkPostModel = WorkPost.dao.findById(id);
			
			if(porkPostModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个岗位！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(postId)){
				porkPostModel.setPostId(postId);
			}
			if(!Utils.isBlankOrEmpty(projectId)){
				porkPostModel.setProjectId(projectId);
			}
			
			
			porkPostModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delWorkPost(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			WorkPost porkPostModel = WorkPost.dao.findById(id);
			
			if(porkPostModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个岗位！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//porkPostModel.delete();
			
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
