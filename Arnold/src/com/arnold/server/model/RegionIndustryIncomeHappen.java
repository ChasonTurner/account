package com.arnold.server.model;

import com.arnold.server.model.base.BaseRegionIndustryIncomeHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class RegionIndustryIncomeHappen extends BaseRegionIndustryIncomeHappen<RegionIndustryIncomeHappen> {
	public static final RegionIndustryIncomeHappen dao = new RegionIndustryIncomeHappen();
	public static final String tableName = "tb_region_industry_income_happen";
	
	/**
	 * @Description:保存村产业 收入
	 * @author Li Bangming;
	 * @date 2017年6月8日 下午3:38:41
	 * @villagerPostIncome 村产业收入对象
	 * @return
	 */
	public  boolean saveRegionIndustryIncomeHappen(RegionIndustryIncomeHappen regionIndustryIncomeHappen) {
	    boolean isSave=	regionIndustryIncomeHappen.save();
		return isSave;
	}

	/**
	 * @Description:分页
	 * @author Li Bangming;
	 * @date 2017年7月5日 下午3:38:41
	 * @return
	 */
	public static Page<RegionIndustryIncomeHappen>  pageRegionIndustryIncomeHappen(int pageNumber,int pageSize,String regionId,String industryId,String keyWord){
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
