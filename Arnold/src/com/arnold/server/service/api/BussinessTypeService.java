package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.BussinessType;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class BussinessTypeService extends BaseService {

	public int addBussinessType(String typeId, int bussinessType){
		
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		
		BussinessType bussinessTypeModel = new BussinessType();
		bussinessTypeModel.setId(Utils.create36UUID());
		bussinessTypeModel.setTypeId(typeId);
		bussinessTypeModel.setBussinessType(bussinessType);
		
		bussinessTypeModel.save();
			
		return ConstUtils.DEAL_SUCCESS;
		
	}
	
	public Map<String, Object> pageBussinessType(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			BussinessType bussinessType = new BussinessType();
			
			Page<BussinessType> bussinessTypeModel = bussinessType.pageBussinessTypeByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, bussinessTypeModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageBussinessTypeByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			BussinessType bussinessType = new BussinessType();
			
			Page<BussinessType> bussinessTypeModel = bussinessType.pageBussinessTypeByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, bussinessTypeModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateBussinessType(String id, String typeId, int bussinessType){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			BussinessType bussinessTypeModel = BussinessType.dao.findById(id);
			
			if(bussinessTypeModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个业务类型！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(typeId)){
				bussinessTypeModel.setTypeId(typeId);
			}
			if(bussinessType != -1){
				bussinessTypeModel.setBussinessType(bussinessType);
			}
			
			bussinessTypeModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delBussinessType(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			BussinessType bussinessTypeModel = BussinessType.dao.findById(id);
			
			if(bussinessTypeModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个业务类型！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//bussinessTypeModel.delete();
			
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
