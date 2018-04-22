package com.arnold.server.model;

import com.arnold.server.model.base.BaseRegionIndustryHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class RegionIndustryHappen extends BaseRegionIndustryHappen<RegionIndustryHappen> {
	public static final RegionIndustryHappen dao = new RegionIndustryHappen();
	public static final String tableName = "tb_region_industry_happen";
	
	/**
	 * @Description:保存村产业 
	 * @author Li Bangming;
	 * @date 2017年6月8日 下午3:38:41
	 * @villagerPostIncome 村产业收入对象
	 * @return
	 */
	public  boolean saveRegionIndustryHappen(RegionIndustryHappen villagerPostIncome) {
	    boolean isSave=	villagerPostIncome.save();
		return isSave;
	}

	/**
	 * @Description:分页
	 * @author Li Bangming;
	 * @date 2017年7月5日 下午3:38:41
	 * @return
	 */
	public static Page<RegionIndustryHappen>  pageRegionIndustryHappen(int pageNumber,int pageSize,String regionId,String industryId,String keyWord){
		String keyWordExpress=ArnoldUtils.EMPTY_STR;
		if(!Utils.isBlankOrEmpty(keyWord)){
			keyWord=ArnoldUtils.decodeSpecialChars(keyWord);
			keyWordExpress=" where trip.industryId in(select ti.id from tb_industry ti where ti.name like '%"+keyWord+"%')";
		}
		String select=" select trip.* ";
		String sqlExceptSelect="  from "+tableName+" trip "+keyWordExpress;
		return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}
}