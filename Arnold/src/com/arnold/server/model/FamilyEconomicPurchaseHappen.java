package com.arnold.server.model;

import java.util.List;

import com.arnold.server.model.base.BaseFamilyEconomicPurchaseHappen;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class FamilyEconomicPurchaseHappen extends BaseFamilyEconomicPurchaseHappen<FamilyEconomicPurchaseHappen> {
	public static final FamilyEconomicPurchaseHappen dao = new FamilyEconomicPurchaseHappen();
	public static final String TABLE_NAME = "tb_family_economic_purchase_happen";
	/**
	 * @Description:保存家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @collectiveEconomy 家庭经济采购信息对象
	 * @return
	 */
	public  boolean saveFamilyEconomicPurchaseHappen(FamilyEconomicPurchaseHappen familyEconomicPurchaseHappen) {
	    boolean isSave=	familyEconomicPurchaseHappen.save();
		return isSave;
	}
	/**
	 * @Description:根据id查询家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @collectiveEconomy 家庭经济采购信息对象
	 * @return
	 */
	public  static  FamilyEconomicPurchaseHappen queryFamilyEconomicPurchaseHappenById(String id) {
		return dao.findById(id);
	}
	
	/**
	 * @Description:保存家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @collectiveEconomy 家庭经济采购信息对象
	 * @return
	 */
	public  boolean updateFamilyEconomicPurchaseHappen(FamilyEconomicPurchaseHappen familyEconomicPurchaseHappen) {
	    boolean isUpdate=familyEconomicPurchaseHappen.update();
		return isUpdate;
	}
	/**
	 * @Description:分页家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @return
	 */
	public static Page<FamilyEconomicPurchaseHappen>  pageFamilyEconomicPurchaseHappen(int pageNumber,int pageSize,
			String familyEconomicId,String familyId,String parentTypeId){
		String select="SELECT eph.*,tm.`name` AS memberName ";
		String sqlExceptSelect="FROM tb_family_economic_purchase_happen eph "
				+ "LEFT JOIN tb_member tm ON eph.memberId = tm.id WHERE 1=1 and eph.familyId = ?";
		if(!Utils.isBlankOrEmpty(parentTypeId)){
			sqlExceptSelect += " and parentTypeId like'"+parentTypeId+"'";
		}
		return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect,familyId);
	}
	
	public static FamilyEconomicPurchaseHappen queryFamilyEconomicPurchaseHappen(String familyId,String typeId,String memberId) {
		String sql = "SELECT * FROM tb_family_economic_purchase_happen WHERE familyId like '"+familyId+"' "
				+ "AND typeId = '"+typeId+"' and memberId like'"+memberId+"'" ;
		return dao.findFirst(sql);
	}
	
	/**
	 * 获取家庭经济采购类型支出
	 * @param familyId
	 * @param searchYear
	 * @return
	 */
	public List<Record> getPurchasseOutcomeInfos(String familyId,
			String searchYear) {
		String sql = "SELECT "
				+ "   tfeph.familyId, "
				+ "   tfeph.parentTypeId, "
				+ "   tfeph.typeId, "
				+ "   SUM(tfeph.amount) AS amount,"
				+ "   CONVERT(SUM(tfeph.price), DECIMAL) AS price "
				+ " FROM "
				+ "   tb_family_economic_purchase_happen tfeph "
				+ " WHERE tfeph.familyId = ?  "
				+ "   AND tfeph.tradeTime LIKE '"+searchYear+"%' "
				+ " GROUP BY tfeph.typeId ";
		return Db.find(sql, familyId);
	}
	
	/**
	 * 获取家庭经济采购数量
	 * @param familyId
	 * @param searchYear
	 * @return
	 */
	public int getPurchasseOutcomeInfos(String familyId,
			String searchYear, String typeIds) {
		int result = 0;
		if(Utils.isBlankOrEmpty(typeIds)){
			String[] types = typeIds.split(",");
			StringBuffer sql = new StringBuffer("SELECT SUM(tfep.amount) AS amount "
					+ "FROM tb_family_economic_purchase_happen tfep "
					+ "WHERE tfep.familyId= ? AND tfep.tradeTime LIKE '"+searchYear+"%' AND (");
			for(int i=0;i<types.length;i++){
				if(i==0){
					sql.append("tfep.typeId = '"+types[i]+"'");
				}else{
					sql.append(" or tfep.typeId = '"+types[i]+"'");
				}
			}
			sql.append(")");
			Record info = Db.findFirst(sql.toString(), familyId);
			if(null!=info && null!=info.getInt("amount")){
				result = info.getInt("amount");
			}
		}
		return result;
	}
	
	/**
	 * 获取家庭生产性支出=购买支出+成本支出
	 * @param pFamilyId
	 * @param year
	 * @return
	 */
	public double countPurchaseOutcomeByFamilyId(String pFamilyId, String year) {
		double resCome = 0d;
		String sql = " SELECT  "
				+ " CONVERT(SUM(IFNULL(tfeph.price,0)+(IFNULL(ti.costUnitPrice, 0) * tfeph.amount)), DECIMAL) as income "
				+ " FROM "
				+ "   tb_family_economic_purchase_happen tfeph  "
				+ "  LEFT JOIN tb_industry ti ON ti.typeId = tfeph.typeId  "
				+ " WHERE tfeph.familyId = '"+pFamilyId+"'  "
				+ "   AND tfeph.tradeTime LIKE '"+year+"%'  ";
		Record info = Db.findFirst(sql);
		if(null!=info && null!=info.getBigDecimal("income")){
			resCome = info.getBigDecimal("income").doubleValue();
		}
		return resCome;
	}
	
	/**
	 * @description  获取家庭年度采购订单成本
	 * @author luzy
	 * @date 2017年12月26日
	 * @param pFamilyId
	 * @param pYear
	 * @return
	 */
	public Record listEconomicOutOrderInfos(String pFamilyId, String pYear) {
		String sql = " SELECT "
				+ "   IFNULL(SUM(tfeph.amount*IFNULL(ti.flUnitPrice, 0.00)),0.00) AS flUnitPrices,"
				+ "   IFNULL(SUM(tfeph.amount*IFNULL(ti.lyUnitPrice, 0.00)),0.00) AS lyUnitPrices,"
				+ "   IFNULL(SUM(tfeph.amount*IFNULL(ti.bmUnitPrice, 0.00)),0.00) AS bmUnitPrices,"
				+ "   IFNULL(SUM(tfeph.amount*IFNULL(ti.ggUnitPrice, 0.00)),0.00) AS ggUnitPrices,"
				+ "   IFNULL(SUM(tfeph.amount*IFNULL(ti.slUnitPrice, 0.00)),0.00) AS slUnitPrices,"
				+ "   IFNULL(SUM(tfeph.amount*IFNULL(ti.syUnitPrice, 0.00)),0.00) AS syUnitPrices,"
				+ "   IFNULL(SUM(tfeph.amount*IFNULL(ti.lsslUnitPrice, 0.00)),0.00) AS lsslUnitPrices,"
				+ "   IFNULL(SUM(tfeph.amount*IFNULL(ti.qtUnitPrice, 0.00)),0.00) AS qtUnitPrices"
				+ " FROM"
				+ "   tb_family_economic_purchase_happen tfeph "
				+ "   LEFT JOIN tb_industry ti ON ti.typeId = tfeph.typeId"
				+ " WHERE tfeph.familyId = ?"
				+ "   AND tfeph.tradeTime LIKE '"+pYear+"%'";
		return Db.findFirst(sql, pFamilyId);
	}
	
}
