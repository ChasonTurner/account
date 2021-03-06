package com.arnold.server.model;

import com.arnold.server.model.base.BaseMember;
import com.arnold.server.util.QueryBaseUtil;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;
import java.util.Map;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Member extends BaseMember<Member> {
	public static final Member dao = new Member();
	final static String tableName = " tb_member";
	
	public Page<Member> pageMemberByIds(int pageNumber, int pageSize, String ids){		
		String sql = "SELECT *";	
		String sqlExceptSelect = " FROM tb_member t1 ";
		
		if(!Utils.isBlankOrEmpty(ids)){
			
			String[] idStrArr = ids.split(",");
			int i = 0;
			for(String str : idStrArr){
				if(i == 0){
					sqlExceptSelect += " WHERE t1.id = '" + str + "'";
				}else{
					sqlExceptSelect += " OR t1.id = '" + str + "'";
				}
				i++;
			}		
		}	
		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect);			
	}
	
	public Member getMemberByIDNumber(String id){
		
		String sql = "SELECT * FROM tb_member t1 WHERE t1.IDnumber = ?";		
		return dao.findFirst(sql, id);			
	}

	public Page<Member> pageMember(int pageNumber, int pageSize,QueryBaseUtil queryBaseUtil) {
		String select = "SELECT *";	
		StringBuffer sqlExceptSelect =new StringBuffer(" FROM " + tableName + " where isValid>=0 ");
		sqlExceptSelect.append(queryBaseUtil.getSpliceSql());
		sqlExceptSelect.append(" ORDER BY createTime DESC");
		List<Object> param=queryBaseUtil.getParam();
		return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect.toString(), param.toArray());
	}
	
	public Page<Member> pageMember(int pageNumber, int pageSize,Map<String,Object> params) {
		String select = "SELECT *";	
		StringBuffer sqlExceptSelect =new StringBuffer(" FROM " + tableName +" where 1=1 ");
		 
		sqlExceptSelect.append(" where isValid>=?");
		
		return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect.toString(), 0);
	}

	public Member findMember(String memberId) {
		String sql = "select * from " + tableName + " where id=? and isValid>=?";
		
		return dao.findFirst(sql, memberId,0);
	}
	
	public Member listAllMemberName() {
		String sql = "select id,name,accreditDepartmentId,accreditPostId from " + tableName + " where isValid>=?";
		
		return dao.findFirst(sql, 0);
	}

	public List<Member> findAll() {
		String sql = "select * from " + tableName + " where isValid>=?";
		
		return dao.find(sql, 0);
	}
	
	public List<Member> findMemberByCondition(QueryBaseUtil queryConfig) {
		StringBuffer sql = new StringBuffer("select * from " + tableName + " where isValid>=0"
				).append(queryConfig.getSpliceSql());
		return dao.find(sql.toString(), queryConfig.getParam().toArray());
	}
	
	public Page<Member> pageContactListByRId(int pageNumber, int pageSize, String regionId){		
		/*String sql = "SELECT tm.id,tm.name,tm.phone,tm.IDnumber,tm.raceId,tm.politicalStatusId,"
				+ "tm.accreditPostId,tm.accreditDepartmentId,tm.accreditDepartmentName,tm.orgPost,tm.post ";	
		String sqlExceptSelect = " FROM tb_member tm WHERE EXISTS ( "
				+ "SELECT 1 FROM tb_member_region_relation tmrr WHERE tmrr.memberId = tm.id AND tmrr.isValid = ?"
				+ " AND tmrr.regionId = ?"
				+ ")";*/
		String sql = "SELECT m.*";
		String sqlExceptSelect = "FROM tb_member m LEFT JOIN tb_member_region_relation r ON m.id = r.memberId AND r.isValid >= 0 " +
				"WHERE r.regionId = ? ";
		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect, regionId);
	}
	
	public static Member findLeaderByPhone(String phone) {
		String sql = null;
		sql = "select * from " + tableName +" where phone=?";
		return dao.findFirst(sql,phone);
	}
	public static List<Member> findAllMembers() {
		String sql = "select * from " + tableName ;
		
		return dao.find(sql);
	}
}
