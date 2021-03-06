package com.arnold.server.model;

import com.arnold.server.model.base.BaseFamilyRemovalHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class FamilyRemovalHappen extends BaseFamilyRemovalHappen<FamilyRemovalHappen> {
	public static final FamilyRemovalHappen dao = new FamilyRemovalHappen();
	public static final String tableName = "tb_family_removal_happen";
	
	/**
	 * @Description:保存贫困户搬迁流水
	 * @author Li Bangming;
	 * @date 2017年8月12日 下午3:38:41
	 * @familyRemovalHappen 贫困户搬迁流水
	 * @return
	 */
	public  boolean saveFamilyRemovalHappen(FamilyRemovalHappen familyRemovalHappen) {
	    boolean isSave=	familyRemovalHappen.save();
		return isSave;
	}
	
	/**
	 * @Description:查询贫困户搬迁流水
	 * @author Li Bangming;
	 * @date 2017年8月12日 下午3:38:41
	 * @familyRemovalHappen 贫困户搬迁流水
	 * @return
	 */
	public static FamilyRemovalHappen findFamilyRemovalHappenByFamilyId(String familyId) {
		String sql="select * from "+tableName+"  where familyId=?";
		return dao.findFirst(sql, familyId);
	}
	/**
	 * @Description:更新贫困户搬迁流水
	 * @author Li Bangming;
	 * @date 2017年8月12日 下午3:38:41
	 * @familyRemovalHappen 贫困户搬迁流水
	 * @return
	 */
	public  boolean updateFamilyRemovalHappen(FamilyRemovalHappen familyRemovalHappen) {
	    boolean isUpdate=	familyRemovalHappen.update();
		return isUpdate;
	}
	/**
	 * @Description:分页
	 * @author Li Bangming;
	 * @date 2017年8月12日 下午3:38:41
	 * @return
	 */
	public static Page<FamilyRemovalHappen>  pageFamilyRemovalHappen(int pageNumber,int pageSize,String fmailyId,String ralationTypeId,String keyWord){
		String keyWordExpress=ArnoldUtils.EMPTY_STR;
		if(!Utils.isBlankOrEmpty(keyWord)){
			keyWord=ArnoldUtils.decodeSpecialChars(keyWord);
			keyWordExpress="  and  tvpi.fmailyId in(select tp.id from tb_post tp where tp.number like '%"+keyWord+"%')";
		}
		String select=" select tvpi.* ,(select tp.number from tb_family tp where tp.id=tvpi.fmailyId) as number";
		String sqlExceptSelect="   from "+tableName+" tvpi where fmailyId like '%"+fmailyId+"%' and  ralationTypeId like '%"+ralationTypeId+"%' "+keyWordExpress;
		return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect,fmailyId,ralationTypeId);
	}
}
