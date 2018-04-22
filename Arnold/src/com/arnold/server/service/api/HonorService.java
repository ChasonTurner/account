package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Honor;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class HonorService extends BaseService {

	public Map<String, Object> addHonor(String name, String typeId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		try {
			
			Honor honorModel = new Honor();
			honorModel.setId(Utils.create36UUID());
			honorModel.setName(name);
			honorModel.setTypeId(typeId);
			
			honorModel.save();
			
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
	
	public Map<String, Object> pageHonor(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Honor honor = new Honor();

			Page<Honor> honorModel = honor.pageHonorByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, honorModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageHonorByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Honor honor = new Honor();
			
			Page<Honor> honorModel = honor.pageHonorByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, honorModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateHonor(String id, String name, String typeId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Honor honorModel = Honor.dao.findById(id);
			
			if(honorModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个奖项！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(name)){
				honorModel.setName(name);
			}
			if(!Utils.isBlankOrEmpty(typeId)){
				honorModel.setTypeId(typeId);
			}
			
			honorModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delHonor(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Honor honorModel = Honor.dao.findById(id);
			
			if(honorModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个奖项！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//honorModel.delete();
			
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
