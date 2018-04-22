package com.arnold.server.model;

import com.arnold.server.model.base.BaseFamilyEconomic;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class FamilyEconomic extends BaseFamilyEconomic<FamilyEconomic> {
	public static final FamilyEconomic dao = new FamilyEconomic();
	public static final String TABLE_NAME = "tb_family_economic";

	/**
	 * @Description:保存家庭经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @familyEconomic 家庭经济对象
	 * @return
	 */
	public boolean saveFamilyEconomic(FamilyEconomic familyEconomic) {
		boolean isSave = familyEconomic.save();
		return isSave;
	}

	/**
	 * @Description:根据id查询家庭经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @familyEconomic 家庭经济对象
	 * @return
	 */
	public static FamilyEconomic queryFamilyEconomicById(String id) {
		return dao.findById(id);
	}

	/**
	 * @Description:根据familyId查询家庭经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @familyEconomic 家庭经济对象
	 * @return
	 */
	public static FamilyEconomic queryFamilyEconomicByFamilyId(String familyId) {
		String sql = "select * from " + TABLE_NAME + " where familyId=?";
		return dao.findFirst(sql, familyId);
	}

	/**
	 * @Description:分页家庭经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @return
	 */
	public static Page<FamilyEconomic> pageFamilyEconomic(int pageNumber, int pageSize, String regionId,
			String keyWord) {
		String keyWordExpress = ArnoldUtils.EMPTY_STR;
		if (!Utils.isBlankOrEmpty(keyWord)) {
			keyWord = ArnoldUtils.decodeSpecialChars(keyWord);
			keyWordExpress = " where tvpi.familyId in(select tfa.id from tb_family tfa where tfa.villagerId in(select tp.id from tb_villager tp where tp.name like '%"
					+ keyWord + "%'))";
		}
		String select = " SELECT tvpi.*,tf.regionId,tf.number,tf.villagerId,(SELECT tv.name from tb_villager tv where tv.id=tf.villagerId) as villagerName"
				+ " ,tf.number as plantTypeIds,tf.number as plantTypeIds ,tf.number as plantArea,tf.number as breedTypeIds ,tf.number as breedAmount ,tf.number as purchasePrice"
				+ " ,tf.number as earningPrice";
		String sqlExceptSelect = "   from " + TABLE_NAME + " tvpi left join tb_family tf on tf.id=tvpi.familyId "
				+ keyWordExpress;
		return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}

	public static Page<FamilyEconomic> pageFamilyEconomicByHam(int page, int pageSize, String hamletName,
			String groupName, String keyWord) {
		String select = "SELECT fe.*,tf.hamletId as hamletName,tf.groupName,tf.number,tv.`name` AS villagerName,GROUP_CONCAT(ph.typeId) AS typeIds,"
				+ "GROUP_CONCAT(ph.parentTypeId) AS parentTypeIds,"
				+ "GROUP_CONCAT(CONCAT_WS('##',ph.parentTypeId,ph.underwritingAmount)) AS typeAmount,"
				+ "ifnull((SELECT SUM(ph1.price) FROM tb_family_economic_purchase_happen ph1 WHERE ph1.familyId = ph.familyId),0) AS phPrice,"
				+ "SUM(ph.unitPrice*ph.amount) AS purchasePrice,"
				+ "ifnull((SELECT SUM(sh1.price) FROM tb_family_economic_self_sale_happen sh1 WHERE sh1.familyId = sh.familyId),0) AS earningPrice ";
		String sqlExceptSelect = " FROM tb_family_economic fe " + "LEFT JOIN tb_family tf ON tf.id = fe.familyId "
				+ " LEFT JOIN tb_villager tv ON tv.id = tf.villagerId "
				+ " LEFT JOIN tb_family_economic_purchase_happen ph ON ph.familyEconomicId = fe.id"
				+ " LEFT JOIN tb_family_economic_underwriting_happen AS under_h ON under_h.familyEconomicId = fe.id"
				+ " LEFT JOIN tb_family_economic_self_sale_happen as sh on sh.familyEconomicId=fe.id " 
				+ " WHERE 1=1 ";
		if (!Utils.isBlankOrEmpty(keyWord)) {
			keyWord = ArnoldUtils.decodeSpecialChars(keyWord);
			sqlExceptSelect += " AND tv.`name` LIKE '%" + keyWord + "%'";
		}
		if (!Utils.isBlankOrEmpty(hamletName)) {
			hamletName = ArnoldUtils.decodeSpecialChars(hamletName);
			sqlExceptSelect += " AND tf.hamletName LIKE '%" + hamletName + "%'";
		}
		if (!Utils.isBlankOrEmpty(groupName)) {
			groupName = ArnoldUtils.decodeSpecialChars(groupName);
			sqlExceptSelect += " AND tf.groupName LIKE '%" + groupName + "%'";
		}
		sqlExceptSelect += " GROUP BY fe.id ORDER BY fe.createTime DESC";
		return dao.paginate(page, pageSize, select, sqlExceptSelect);
	}

	/**
	 * @Description:根据帮扶人员查询帮扶产业
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午3:38:41
	 * @return
	 */
	public Page<FamilyEconomic> pageFamilyEconomicByMemberId(int pageNumber, int pageSize, String memberId) {
		String select = "select ";
		String sqlExceptSelect = "   from " + TABLE_NAME + " tvpi left join tb_family tf on tf.id=tvpi.familyId ";
		return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}

}