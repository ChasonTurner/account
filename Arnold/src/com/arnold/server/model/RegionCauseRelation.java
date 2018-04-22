package com.arnold.server.model;

import java.util.ArrayList;
import java.util.List;

import com.arnold.server.model.base.BaseRegionCauseRelation;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class RegionCauseRelation extends BaseRegionCauseRelation<RegionCauseRelation> {
	public static final RegionCauseRelation dao = new RegionCauseRelation();
	private static final String tableName = "tb_region_cause_relation";
	private static final String sqlSelectAll = "select * from " + tableName + " ";

	/**
	 * 
	 */
	public static RegionCauseRelation findFirstByRegion(String regionId) {
		if (regionId == null)
			return null;

		String sql = sqlSelectAll + "WHERE regionId = ? and isValid=1";
		return dao.findFirst(sql, regionId);
	}

	public static RegionCauseRelation findBy(String regionId, String causeId) {
		return dao.findFirst("select * from " + tableName + " where regionId=? and causeId=? and isValid=?", regionId,
				causeId, 1);
	}

	public Page<RegionCauseRelation> pageRegionCauseRelation(String regionId,String causeId, Integer pageNumber, Integer pageSize) {
		String selectSQL = "SELECT * ";
		String fromSQL = " FROM " + tableName;
		String whereSQL = "";
		List<Object> paras = new ArrayList<Object>();

		if (!Utils.isBlankOrEmpty(regionId)) {
			if (Utils.isBlankOrEmpty(whereSQL)) {
				whereSQL = "WHERE";
			}
			whereSQL += " AND regionId=?";
			paras.add(regionId);
		}
		if (!Utils.isBlankOrEmpty(causeId)) {
			if (Utils.isBlankOrEmpty(whereSQL)) {
				whereSQL = "WHERE";
			}
			whereSQL += " AND causeId=?";
			paras.add(causeId);
		}

		String orderBy = " ORDER BY createTime DESC";
		return dao.paginate(pageNumber, pageSize, selectSQL, fromSQL + whereSQL + orderBy, paras.toArray());
	}

}
