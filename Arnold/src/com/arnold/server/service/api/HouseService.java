package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.House;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class HouseService extends BaseService {

	public Map<String, Object> addHouseService(String familyId, String createTime, int area,
						String houseStructId, String fuelId, int productArea, int liveArea, double lng,
						double lat, double height){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		try {
			
			House houseModel = new House();
			houseModel.setId(Utils.create36UUID());
			houseModel.setFamilyId(familyId);
			houseModel.setCreateTime(new Date());
			houseModel.setArea(area);
			houseModel.setHouseStructId(houseStructId);
			houseModel.setFuelId(fuelId);
			houseModel.setProductArea(productArea);
			houseModel.setLiveArea(liveArea);
			houseModel.setLng(lng);
			houseModel.setLat(lat);
			houseModel.setHeight(height);
			
			houseModel.save();
			
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
	
	public Map<String, Object> pageHouseService(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			House house = new House();
			
			Page<House> houseModel = house.pageHouseByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, houseModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageHouseServiceByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			House house = new House();
			
			Page<House> houseModel = house.pageHouseByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, houseModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateHouseService(String id, String familyId, String createTime, 
			int area, String houseStructId, String fuelId, int productArea, int liveArea, double lng,
			double lat, double height){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			House houseModel = House.dao.findById(id);
			
			if(houseModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个住房信息！");
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(familyId)){
				houseModel.setFamilyId(familyId);
			}
			
			if(area != -1){
				houseModel.setArea(area);
			}
			if(!Utils.isBlankOrEmpty(houseStructId)){
				houseModel.setHouseStructId(houseStructId);
			}
			if(!Utils.isBlankOrEmpty(fuelId)){
				houseModel.setFuelId(fuelId);
			}
			if(productArea != -1){
				houseModel.setProductArea(productArea);
			}
			if(liveArea != -1){
				houseModel.setLiveArea(liveArea);
			}
			if(lng != -1){
				houseModel.setLng(lng);
			}
			if(lat != -1){
				houseModel.setLat(lat);
			}
			if(height != -1){
				houseModel.setHeight(height);
			}
			
			houseModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delHouseService(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			House houseModel = House.dao.findById(id);
			
			if(houseModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个住房信息！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//houseModel.delete();
			
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
