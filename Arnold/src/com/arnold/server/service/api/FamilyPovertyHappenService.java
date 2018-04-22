package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.FamilyPovertyHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 贫困户脱贫流水流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class FamilyPovertyHappenService {
	/**
	 * @Description:保存贫困村产业关系
	 * @author Li Bangming;
	 * @param familyPropertyId 
	 * @param statusTime 
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Map<String, Object> addFamilyPovertyHappen(String familyId,String ralationTypeId,String url,
			String remark,String createUserId, String familyPropertyId, Date statusTime) {
		Map<String, Object> operMap = new HashMap<String, Object>();
		FamilyPovertyHappen  rih=new FamilyPovertyHappen();
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setFamilyId(familyId);
		if(!Utils.isBlankOrEmpty(ralationTypeId)) rih.setRalationTypeId(ralationTypeId);
		if(!Utils.isBlankOrEmpty(familyPropertyId)) rih.setFamilyPropertyId(familyPropertyId);
		if(!Utils.isBlankOrEmpty(url)) rih.setUrl(url);
		if(!Utils.isBlankOrEmpty(remark)) rih.setRemark(remark);
		if(!Utils.isBlankOrEmpty(createUserId)) rih.setCreateUserId(createUserId);
		if(statusTime != null) rih.setStatusTime(statusTime);
		
		rih.setCreateTime(new Date());
		try {
			rih.saveFamilyPovertyHappen(rih);
			operMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
			operMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			operMap.put(ConstUtils.R_MSG,  ConstUtils.DATA_OPERATE_ERROR_STR);
			operMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			ArnoldUtils.logger.error(e.getLocalizedMessage());
		}
		return operMap;
	}
	
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Page<FamilyPovertyHappen>  pageFamilyPovertyHappen(int pageNumber,int pageSize,String regionId,String industryId,String keyWord){
		return FamilyPovertyHappen.pageFamilyPovertyHappen(pageNumber, pageSize, regionId, industryId, keyWord);
	}

	/**
	 * @Author PanChangGui
	 * @Time 2017年10月5日 下午12:43:55
	 * @Description 
	 */
	public Map<String, Object> pageFamilyPovertyPropertyRecord(String familyId, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//Page<Record> page = Arnold.dao.pageFamilyPovertyPropertyRecord(familyId, pageNumber, pageSize);
		
		Page<FamilyPovertyHappen> page = FamilyPovertyHappen.dao.pageFamilyPovertyPropertyRecord(familyId,pageNumber,pageSize);
		
		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
}

