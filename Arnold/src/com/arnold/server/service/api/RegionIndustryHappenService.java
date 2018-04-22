package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.RegionIndustryHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 贫困村产业关系流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class RegionIndustryHappenService {
	/**
	 * @Description:保存村产业关系
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Map<String, Object>  addRegionIndustry(String regionId,String industryId,int ralationType,int amount,String createUserId){
		Map<String, Object> operMap = new HashMap<String, Object>();
		RegionIndustryHappen  rih=new RegionIndustryHappen();
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setRegionId(regionId);
		rih.setIndustryId(industryId);
		rih.setRalationType(ralationType);
		rih.setAmount(amount);
		rih.setCreateUserId(createUserId);
		rih.setCreateTime(new Date());
		try {
			rih.saveRegionIndustryHappen(rih);
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
	public Page<RegionIndustryHappen>  pageRegionIndustry(int pageNumber,int pageSize,String regionId,String industryId,String keyWord){
		return RegionIndustryHappen.pageRegionIndustryHappen(pageNumber, pageSize, regionId, industryId, keyWord);
	}
	
}

