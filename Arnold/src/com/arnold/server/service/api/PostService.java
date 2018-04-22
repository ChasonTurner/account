package com.arnold.server.service.api;

import com.arnold.server.model.Post;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostService extends BaseService {

	public int addPost(String userId,String name, String typeId, String orgId, int number, Double averageIncome,
					   String department, String content,int isFiveFund,int isEatEncase,int isEat,
					   int isEncase,int isLunch,int isOther,String attachment){
		
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Post postModel = new Post();
		postModel.setId(Utils.create36UUID());
		postModel.setName(name);
		postModel.setTypeId(typeId);
		postModel.setOrgId(orgId);
		postModel.setNumber(number);
		postModel.setContent(content);
		postModel.setAverageIncome(averageIncome);
		postModel.setDepartment(department);
		postModel.setCreateTime(new Date());
		postModel.setWriterId(userId);
		postModel.setOperatorId(userId);
		postModel.setUpdateTime(new Date());
		postModel.setIsEat(isEat);
		postModel.setIsEatEncase(isEatEncase);
		postModel.setIsEncase(isEncase);
		postModel.setIsFiveFund(isFiveFund);
		postModel.setIsLunch(isLunch);
		postModel.setIsOther(isOther);
		postModel.setAttachment(attachment);
		postModel.save();
			
		return ConstUtils.DEAL_SUCCESS;
		
	}
	
	public Map<String, Object> pagePost(int pageNumber, int pageSize,String postId,String keyWord){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Post post = new Post();
			
			//Page<Post> postModel = post.pagePostByIds(pageNumber, pageSize, "");
			Page<Record> postModel = post.pagePostRecordByKeyword(pageNumber, pageSize, postId,keyWord);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_PAGE, postModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pagePostByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Post post = new Post();
			
			Page<Post> postModel = post.pagePostByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, postModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updatePost(String id, String name, String typeId,
										  String orgId, int number, Double averageIncome,
										  String department, String content, String userId,
										  int isFiveFund, int isEatEncase, int isEat, int isEncase,
										  int isLunch, int isOther, String attachment){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Post postModel = Post.dao.findById(id);
			if(postModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个岗位！");
				return resultMap;
			}
			if(!Utils.isBlankOrEmpty(name)){
				postModel.setName(name);
			}
			if(!Utils.isBlankOrEmpty(typeId)){
				postModel.setTypeId(typeId);
			}
			if(!Utils.isBlankOrEmpty(orgId)){
				postModel.setOrgId(orgId);
			}
			if(number != -1){
				postModel.setNumber(number);
			}
			if((averageIncome)>0){
				postModel.setAverageIncome(averageIncome);
			}
			if(!Utils.isBlankOrEmpty(department)){
				postModel.setDepartment(department);
			}
			if(!Utils.isBlankOrEmpty(content)){
				postModel.setContent(content);
			}
			if(!Utils.isBlankOrEmpty(attachment)){
				postModel.setAttachment(attachment);
			}
			postModel.setIsOther(isOther);
			postModel.setIsLunch(isLunch);
			postModel.setIsFiveFund(isFiveFund);
			postModel.setIsEncase(isEncase);
			postModel.setIsEatEncase(isEatEncase);
			postModel.setIsEat(isEat);
			postModel.setOperatorId(userId);
			postModel.setUpdateTime(new Date());
			postModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delPost(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Post postModel = Post.dao.findById(id);
			
			if(postModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个岗位！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			postModel.delete();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
			
		}
		
		return resultMap;
		
	}

	public Map<String,Object> pagePostByEId(int pageNumber, int pageSize, String enId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			Post post = new Post();

			//Page<Post> postModel = post.pagePostByIds(pageNumber, pageSize, "");
			Page<Record> postModel = post.pagePostByEId(pageNumber, pageSize,enId);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_PAGE, postModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");

		}catch(Exception e){

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");

		}

		return resultMap;
	}

	public Post queryPostById(String postId) {
		Post postModel = Post.dao.findById(postId);
		return postModel;
	}
}
