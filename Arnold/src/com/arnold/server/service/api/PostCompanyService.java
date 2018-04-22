package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.PostCompany;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class PostCompanyService extends BaseService {

	public Map<String, Object> addPostCompany(String postId, String orgId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			PostCompany postCompanyModel = new PostCompany();
			
			postCompanyModel.setId(Utils.create36UUID());
			postCompanyModel.setPostId(postId);
			postCompanyModel.setOrgId(orgId);
		
			postCompanyModel.save();
			
			resultMap.put(ConstUtils.R_PAGE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
			return resultMap;
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pagePostCompany(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			PostCompany postCompany = new PostCompany();
			
			Page<PostCompany> postCompanyModel = postCompany.pagePostCompanyByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, postCompanyModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pagePostCompanyByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			PostCompany postCompany = new PostCompany();
			
			Page<PostCompany> postCompanyModel = postCompany.pagePostCompanyByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, postCompanyModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updatePostCompany(String id, String postId, String orgId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			PostCompany postCompanyModel = PostCompany.dao.findById(id);
			
			if(postCompanyModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个岗位/职位！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(postId)){
				postCompanyModel.setPostId(postId);
			}
			if(!Utils.isBlankOrEmpty(orgId)){
				postCompanyModel.setOrgId(orgId);
			}

			
			postCompanyModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delPostCompany(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			PostCompany postCompanyModel = PostCompany.dao.findById(id);
			
			if(postCompanyModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个教育岗位/职位！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//postCompanyModel.delete();
			
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
