package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.RegionIndustryIncomeHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 村产业收入流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class RegionIndustryIncomeHappenService {
	/**
	 * @Description:保存村产业收入流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int  addRegionIndustryIncomeHappen(String regionId,String industryId,Date incomeTime,Double income,String createUserId){
		RegionIndustryIncomeHappen  fsih=new RegionIndustryIncomeHappen();
		String id=Utils.create36UUID();
		fsih.setId(id);
		fsih.setIndustryId(industryId);
		fsih.setIncomeTime(incomeTime);
		fsih.setIncome(income);
		fsih.setCreateUserId(createUserId);
		fsih.setCreateTime(new Date());
		try {
			fsih.saveRegionIndustryIncomeHappen(fsih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Page<RegionIndustryIncomeHappen>  pageRegionIndustryIncomeHappen(int pageNumber,int pageSize,String regionId,String industryId,String keyWord){
		return RegionIndustryIncomeHappen.pageRegionIndustryIncomeHappen(pageNumber, pageSize, regionId,industryId, keyWord);
	}
	
}

