package com.arnold.server.model;

import com.arnold.server.model.base.BaseCollectiveEconomy;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class CollectiveEconomy extends BaseCollectiveEconomy<CollectiveEconomy> {
	public static final CollectiveEconomy dao = new CollectiveEconomy();
	public static final String TABLE_NAME = "tb_collective_economy";
	/**
	 * @Description:保存集体经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @collectiveEconomy 集体经济对象
	 * @return
	 */
	public  boolean saveCollectiveEconomy(CollectiveEconomy collectiveEconomy) {
	    boolean isSave=	collectiveEconomy.save();
		return isSave;
	}
	/**
	 * @Description:根据id查询集体经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @collectiveEconomy 集体经济对象
	 * @return
	 */
	public  static  CollectiveEconomy queryCollectiveEconomyById(String id) {
		return dao.findById(id);
	}
	
	/**
	 * @Description:根据regionid查询集体经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @collectiveEconomy 集体经济对象
	 * @return
	 */
	public  static  CollectiveEconomy queryCollectiveEconomyByRegionId(String regionId) {
		String sql="select * from "+TABLE_NAME+" where regionId=?";
		return dao.findFirst(sql, regionId);
	}

	public  static  CollectiveEconomy queryCollectiveEconomyByRegionIdAndVID(String regionId,String valligerId) {
		String sql="select * from "+TABLE_NAME+" where regionId=? AND valligerId=?";
		return dao.findFirst(sql, regionId,valligerId);
	}
	/**
	 * @Description:分页集体经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @return
	 */
	public static Page<Record>  pageCollectiveEconomy(int pageNumber, int pageSize, String keyWord,String rName){
		String select=" SELECT c.*,r.fullName AS regionName " ;
		String sqlExceptSelect="  FROM tb_collective_economy c LEFT JOIN tb_region r ON r.id=c.regionId WHERE 1=1  ";

		if(!Utils.isBlankOrEmpty(keyWord)){
			keyWord=ArnoldUtils.decodeSpecialChars(keyWord);
			sqlExceptSelect+=" AND (r.fullName like '%"+keyWord+"%' OR c.phone like '%"+keyWord+"%' OR c.valligerName like '%"+keyWord+"%' )";
		}
		if(!Utils.isBlankOrEmpty(rName)){
			rName=ArnoldUtils.decodeSpecialChars(rName);
			sqlExceptSelect+=" AND c.regionId=  '"+rName+"' ";
		}
		sqlExceptSelect+=" ORDER BY c.createTime DESC ";
		return Db.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}
}
