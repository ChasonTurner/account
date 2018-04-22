package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Area;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class AreaService extends BaseService {

	public int addArea(String level, int personCount, int population, String regionId){
		
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Area areaModel = new Area();
		areaModel.setId(Utils.create36UUID());
		areaModel.setLevel(level);
		areaModel.setPersonCount(personCount);
		areaModel.setPopulation(population);
		areaModel.setRegionid(regionId);
		areaModel.setIsValid(0);
		
		areaModel.save();
			
		return ConstUtils.DEAL_SUCCESS;
		
	}
	
	public Map<String, Object> pageArea(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			Area area = new Area();
			
			Page<Area> areaModel = area.pageArea(pageNumber, pageSize);
			
			resultMap.put(ConstUtils.R_PAGE, areaModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);			
		}catch(Exception e){		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);			
		}
		
		return resultMap;	
	}
	
	public Map<String, Object> pageAreaByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Area area = new Area();
			
			Page<Area> areaModel = area.pageAreaByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, areaModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateArea(String id, String level, int personCount, 
										int population, String regionId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Area areaModel = Area.dao.findById(id);
			
			if(areaModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个地区！");
				return resultMap;
			}
		
			if(!Utils.isBlankOrEmpty(level)){
				areaModel.setLevel(level);
			}
			
			if(personCount != -1){
				areaModel.setPersonCount(personCount);
			}
			
			if(population != -1){
				areaModel.setPopulation(population);
			}

			if(!Utils.isBlankOrEmpty(regionId)){
				areaModel.setRegionid(regionId);
			}
			
			areaModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delArea(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Area areaModel = Area.dao.findById(id);
			
			if(areaModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个地区！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//areaModel.delete();
			
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
