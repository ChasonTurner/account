package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.config.ConsConfig;
import com.arnold.server.model.Region;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.arnold.server.util.HttpPostUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Record;

/**
 * @Description: 行政区域Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年7月8日 下午2:49:00
 */
public class RegionService {
	
	/**
	 * @Description: 从poseidon中获取所有孩子节点及本机行政区域id
	 * @author Li Bangming;
	 * @date 2017年6月18日 下午12:57:54
	 * @param c
	 * @param appId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String ,Object>> getAllChildRegionByRegionId(String regionId){
		List<Map<String ,Object>> regionMaps=null;
		String url=ConsConfig.POSEIDON_HOST+"/administrative/list_children_all?parentId="+regionId+"";
		try {
			Map<String, Object> result=HttpPostUtils.requestHttpPostByUrl(url);
			int rc=Integer.parseInt(result.get("rc").toString()) ;
			regionMaps=(List<Map<String, Object>>) result.get("rl");
			if(rc!=ConstUtils.DEAL_SUCCESS){
				return null;
			}
			if(regionMaps!=null&&regionMaps.size()>0){
				return regionMaps;
			}else{
				return null;
			}
		} catch (Exception e) {
			ArnoldUtils.logger.debug(e.getLocalizedMessage());
		}
		return regionMaps;
	}
	
	/**
	 * 
	 * @Description: 根据行政区域及孩子节点信息或获取
	 * @author Li Bangming;
	 * @date 2017年7月6日 上午9:24:34
	 * @param regionId
	 * @param chilRegionMap
	 * @return
	 */
	public String  getRegionIdStrsByRegionIdAndRegionMaps(String regionId,List<Map<String, Object>> regionMaps) {
		String regionIds=ArnoldUtils.EMPTY_STR;
		//两者都为空直接返回
		if(Utils.isBlankOrEmpty(regionId)&&regionMaps==null){
			return regionIds;
		}
		if(regionMaps!=null&&regionMaps.size()>0){
			for(int i=0;i<regionMaps.size();i++){
				if(i==0){
					regionIds="'"+regionMaps.get(i).get("id")+"'";
				}else{
					regionIds+=",'"+regionMaps.get(i).get("id")+"'";
				}
			}
		}
		if(Utils.isBlankOrEmpty(regionIds)&&!Utils.isBlankOrEmpty(regionId)){
			regionIds="'"+regionId+"'";
		}else if(!Utils.isBlankOrEmpty(regionIds)&&!Utils.isBlankOrEmpty(regionId)){
			regionIds+=",'"+regionId+"'";
		}
		return regionIds;
	}
	
	/**
	 * 
	 * @Description: 根据行政区域及孩子节点信息或获取
	 * @author Li Bangming;
	 * @date 2017年7月6日 上午9:24:34
	 * @param regionId
	 * @return
	 */
	public String getAllChildAndSelfRegionIdStrsByRegionId(String regionId) {
		String regionIds=ArnoldUtils.EMPTY_STR;
		if(Utils.isBlankOrEmpty(regionId)){
			return null;
		}
		List<Map<String ,Object>> childRegionMaps=getAllChildRegionByRegionId(regionId);
		regionIds=getRegionIdStrsByRegionIdAndRegionMaps(regionId,childRegionMaps);
		return regionIds;
	}
	
	public Map<String, Object> addRegion(String regionId, String shortName, String fullName, String parentId,
			String parentName, int regionType, int property, String cityCode, String adCode, String longitude,
			String latitude) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Region oldModel = Region.dao.findByRegionId(regionId);
		if(oldModel != null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_EXIST_CODE_STR);
			return resultMap;
		}
		
		Region model = new Region();
		model.setId(regionId);
		model.setShortName(shortName);
		model.setFullName(fullName);
		model.setParentId(parentId);
		model.setParentName(parentName);
		model.setRegionType(regionType);
		if(property != -1) model.setProperty(property);
		model.setCityCode(cityCode);
		model.setAdCode(adCode);
		model.setLongitude(longitude);
		model.setLatitude(latitude);
		
		model.setIsValid(0);
		
		model.setCreateTime(new Date());
		
		model.save();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> updateRegion(String regionId, String shortName, String fullName, String parentId,
			String parentName, int regionType, int property, String cityCode, String adCode, String longitude,
			String latitude) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Region model = Region.dao.findByRegionId(regionId);
		if(model == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}
		
		if(!Utils.isBlankOrEmpty(shortName)) model.setShortName(shortName);
		if(!Utils.isBlankOrEmpty(fullName)) model.setShortName(fullName);
		if(!Utils.isBlankOrEmpty(parentId)) model.setParentId(parentId);
		if(!Utils.isBlankOrEmpty(parentName)) model.setParentName(parentName);
		if(regionType != -1) model.setRegionType(regionType);
		if(property != -1) model.setProperty(property);
		if(!Utils.isBlankOrEmpty(cityCode)) model.setCityCode(cityCode);
		if(!Utils.isBlankOrEmpty(adCode)) model.setAdCode(adCode);
		if(!Utils.isBlankOrEmpty(longitude)) model.setLongitude(longitude);
		if(!Utils.isBlankOrEmpty(latitude)) model.setLatitude(latitude);
		
		model.setUpdateTime(new Date());
		
		model.update();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> delRegion(String regionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Region model = Region.dao.findByRegionId(regionId);
		if(model == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}
		
		model.setIsValid(-1);//-1逻辑删除，0正常
		
		model.update();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> findRegion(int regionLevel) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Region> list = Region.dao.findRegion(regionLevel);
		
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> findPovertyRegion(int regionLevel) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Region> list = Region.dao.findPovertyRegion(regionLevel);
		
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> findVillageNameList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Region> list = Region.dao.findVillageNameList();
		
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> findChildRegion(String parentId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Region> list = Region.dao.findChildRegion(parentId);
		
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> findChildPovertyRegion(String parentId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Region> list = Region.dao.findChildPovertyRegion(parentId);
		
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

    public Region findRegionById(String id) {
		return Region.dao.findByRegionId(id);
    }

	/**
	 * @Author PanChangGui
	 * @Email 823468425@qq.com
	 * @Time 2017年9月22日 下午2:50:28
	 * @Description 
	 */
	public Map<String, Object> listAllRegion() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Region> list = Region.dao.listAllRegion();
		
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Record getCountryDetailInfoById(String familyRegionId) {
		Record result = new Record();
		Region info = Region.dao.findByRegionId(familyRegionId);
		if(null!=info && !Utils.isBlankOrEmpty(info.getId()) && !Utils.isBlankOrEmpty(info.getRegionType().toString())){
			switch (info.getRegionType()) {
				case 6://组
					result.set("groupId", info.getId());
					result.set("groupName", info.getShortName());
					result.set("hamletId", info.getParentId());
					result.set("hamletName", info.getParentName());
					if(!Utils.isBlankOrEmpty(info.getParentId())){
						Region parentInfo = Region.dao.findByRegionId(info.getParentId());//村信息
						result.set("countryId", parentInfo.getParentId());
						result.set("countryName", parentInfo.getParentName());
					}
					break;
				default:
					break;
			}
		}
		return result;
		
	}
}

