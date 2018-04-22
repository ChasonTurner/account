package com.arnold.server.model;

import java.util.ArrayList;
import java.util.List;

import com.arnold.server.model.base.BaseOrgMemberRelation;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class OrgMemberRelation extends BaseOrgMemberRelation<OrgMemberRelation> {
	public static final OrgMemberRelation dao = new OrgMemberRelation();
	private static final String tableName = "tb_org_member_relation";
	private static final String sqlSelectAll = "select * from " + tableName + " ";

	public static OrgMemberRelation findFirstByMember(String memberId) {
		if (memberId == null)
			return null;

		String sql = sqlSelectAll + "WHERE memberId = ? and isValid=1";
		return dao.findFirst(sql, memberId);
	}

	public static List<OrgMemberRelation> findByName(String memberId) {
		if (memberId == null)
			return null;

		String sql = sqlSelectAll + "WHERE memberId = ? and isValid=1";
		return dao.find(sql, memberId);
	}

	public static List<OrgMemberRelation> findById(String id) {
		if (id == null)
			return null;

		String sql = sqlSelectAll + "WHERE id = ? and isValid=1";
		return dao.find(sql, id);
	}

	public static OrgMemberRelation findFirstById(String id) {
		if (id == null)
			return null;

		String sql = sqlSelectAll + "WHERE id = ? and isValid=1";
		return dao.findFirst(sql, id);
	}

	public static OrgMemberRelation findBy(String memberId, String orgId) {
		return dao.findFirst("select * from " + tableName + " where memberId=? and orgId=? and isValid=?", memberId,
				orgId, 1);
	}

	public Page<OrgMemberRelation> pageOrgMemberRelation(String orgId, String memberId, Integer pageNumber,
			Integer pageSize) {
		String selectSQL = "SELECT * ";
		String fromSQL = " FROM " + tableName;
		String whereSQL = "";
		List<Object> paras = new ArrayList<Object>();

		if (!Utils.isBlankOrEmpty(orgId)) {
			if (Utils.isBlankOrEmpty(whereSQL)) {
				whereSQL = "WHERE";
			}
			whereSQL += " AND orgId=?";
			paras.add(orgId);
		}
		if (!Utils.isBlankOrEmpty(memberId)) {
			if (Utils.isBlankOrEmpty(whereSQL)) {
				whereSQL = "WHERE";
			}
			whereSQL += " AND memberId=?";
			paras.add(memberId);
		}

		String orderBy = " ORDER BY createTime DESC";
		return dao.paginate(pageNumber, pageSize, selectSQL, fromSQL + whereSQL + orderBy, paras.toArray());
	}

}