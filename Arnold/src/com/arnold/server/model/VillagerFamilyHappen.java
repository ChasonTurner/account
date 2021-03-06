package com.arnold.server.model;

import com.arnold.server.model.base.BaseVillagerFamilyHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class VillagerFamilyHappen extends BaseVillagerFamilyHappen<VillagerFamilyHappen> {
	public static final VillagerFamilyHappen dao = new VillagerFamilyHappen();
	public static final String tableName = "tb_villager_family_happen";
	
	/**
	 * @Description:区域收入产生表
	 * @author Li Bangming;
	 * @date 2017年6月8日 下午3:38:41
	 * @villagerPostIncome 区域收入产生表对象
	 * @return
	 */
	public  boolean saveVillagerFamilyHappen(VillagerFamilyHappen villagerPostIncome) {
	    boolean isSave=	villagerPostIncome.save();
		return isSave;
	}

	/**
	 * @Description:分页
	 * @author Li Bangming;
	 * @date 2017年7月5日 下午3:38:41
	 * @return
	 */
	public static Page<VillagerFamilyHappen>  pageVillagerFamilyHappen(int pageNumber,int pageSize,String villagerId,String postId,String keyWord){
		String keyWordExpress=ArnoldUtils.EMPTY_STR;
		if(!Utils.isBlankOrEmpty(keyWord)){
			keyWord=ArnoldUtils.decodeSpecialChars(keyWord);
			keyWordExpress=" where tvpi.postId in(select tp.id from tb_post tp where tp.name like '%"+keyWord+"%')";
		}
		String select=" select tvpi.* ,(select tp.name from tb_post tp where tp.id=tvpi.postId) as postName";
		String sqlExceptSelect="   from "+tableName+" tvpi "+keyWordExpress;
		return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}
}
