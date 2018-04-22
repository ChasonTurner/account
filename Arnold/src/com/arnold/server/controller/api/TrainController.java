package com.arnold.server.controller.api;

import com.arnold.server.controller.BaseController;
import com.arnold.server.service.api.PostService;
import com.arnold.server.service.api.TrainService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

import java.util.HashMap;
import java.util.Map;

public class TrainController extends BaseController{
	//创建培训
	public void add_train(){
		
		String name = this.getPara("name");
		String typeId = this.getPara("typeId");
		
		if(Utils.isBlankOrEmpty(name)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "name不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(typeId)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "typeId不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TrainService service = new TrainService();
		//int result = service.addTrain(name, typeId);
		int result =1;
		if(result == 1){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "添加成功!");
			
		}else if(result == 2){
			
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "添加失败!");
			
		}
		
		renderJson(resultMap);
	}
	
	//分页查询培训
	public void page_train(){
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TrainService service = new TrainService();
		resultMap = service.pageTrain(pageNumber, pageSize);
		
		renderJson(resultMap);
			
	}
	
	//修改培训
	public void update_train(){
		
		String id = this.getPara("id");
		String name = this.getPara("name");
		String typeId = this.getPara("typeId");
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TrainService service = new TrainService();
		//resultMap = service.updateTrain(id, name, typeId);
		
		renderJson(resultMap);
		
	}
	
	//删除培训
	public void del_train(){
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TrainService service = new TrainService();
		resultMap = service.delTrain(id);
		
		renderJson(resultMap);
		
	}
	
	//创建培训
	public void add_post(){
		
		String name = this.getPara("name");
		String typeId = this.getPara("typeId");
		String orgId = this.getPara("orgId");
		
		if(Utils.isBlankOrEmpty(name)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "name不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(typeId)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "typeId不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(orgId)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "orgId不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PostService service = new PostService();
		//int result = service.addPost(name, typeId, orgId);
		int result =1;
		if(result == 1){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "添加成功!");
			
		}else if(result == 2){
			
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "添加失败!");
			
		}
		
		renderJson(resultMap);
	}
	
	//分页查询培训
	public void page_post(){
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PostService service = new PostService();
		resultMap = service.pagePost(pageNumber, pageSize,"","");
		
		renderJson(resultMap);
			
	}
	
	//修改培训
	public void update_post(){
		
		String id = this.getPara("id");
		String name = this.getPara("name");
		String typeId = this.getPara("typeId");
		String orgId = this.getPara("orgId");
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PostService service = new PostService();
		//resultMap = service.updatePost(id, name, typeId, orgId);
		
		renderJson(resultMap);
		
	}
	
	//删除培训
	public void del_post(){
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PostService service = new PostService();
		resultMap = service.delPost(id);
		
		renderJson(resultMap);
		
	}
}
