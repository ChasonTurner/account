package com.arnold.server.model;

import java.util.ArrayList;
import java.util.List;

import com.arnold.server.model.base.BaseFamilyMeasureRelation;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class FamilyMeasureRelation extends BaseFamilyMeasureRelation<FamilyMeasureRelation> {
	public static final FamilyMeasureRelation dao = new FamilyMeasureRelation();

	public static final String TABLE_NAME = "tb_family_measure_relation";

	public FamilyMeasureRelation findBy(String familyId, String measureId) {
		return dao.findFirst("select * from " + TABLE_NAME + " where familyId=? and measureId=? and isValid=?",
				familyId, measureId, 0);
	}

	public Page<FamilyMeasureRelation> pageFamilyMeasureRelation(String measureId,String familyId, Integer pageNumber, Integer pageSize) {
		String selectSQL = "SELECT * ";
		String fromSQL = " FROM " + TABLE_NAME;
		String whereSQL = "";
		List<Object> paras = new ArrayList<Object>();

		if (!Utils.isBlankOrEmpty(measureId)) {
			if (Utils.isBlankOrEmpty(whereSQL)) {
				whereSQL = "WHERE";
			}
			whereSQL += " AND measureId=?";
			paras.add(measureId);
		}
		if (!Utils.isBlankOrEmpty(familyId)) {
			if (Utils.isBlankOrEmpty(whereSQL)) {
				whereSQL = "WHERE";
			}
			whereSQL += " AND familyId=?";
			paras.add(familyId);
		}

		String orderBy = " ORDER BY createTime DESC";
		return dao.paginate(pageNumber, pageSize, selectSQL, fromSQL + whereSQL + orderBy, paras.toArray());
	}

	public FamilyMeasureRelation findByFamilyId(String familyId) {
		return dao.findFirst("select * from " + TABLE_NAME + " where familyId=? and isValid>=?",
				familyId, 0);
	}
}
