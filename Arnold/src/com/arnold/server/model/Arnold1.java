package com.arnold.server.model;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.base.BaseArnold;
import com.huntersun.tool.ConstUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Arnold1 extends BaseArnold<Arnold1> {
	public static final Arnold1 dao = new Arnold1();
	
	public Page<Record> pageCollectiveEconomic(Integer pageNumber, Integer pageSize, String ids) {
		
		String sql = "SELECT t2.*, t3.cotentAndArea,t3.completeMoney,t3.fundation,t3.selfMoney,t3.departmentMoney";
		
		String sqlExceptSelect = " FROM(SELECT * FROM tb_project t1 where t1.IsValid=0 and) t2 "
				+ "LEFT JOIN tb_public_service t3 ON t2.id = t3.id";
		
		if(!Utils.isBlankOrEmpty(ids)){
			
			String[] idArr = ids.split(",");
			for(String id : idArr){
				
				sqlExceptSelect += " OR t2.id = '" + id + "'";
				
			}
			
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
	
	}
	
	public Page<Record> pagePublicProject(Integer pageNumber, Integer pageSize, String ids) {
		
		String sql = "SELECT t3.* , "
				+ "(SELECT SUM(i1.investPrice) FROM tb_project_invest_happen i1 WHERE i1.projectId = t3.id) as completeMoney1, "
				+ "(SELECT SUM(i2.investPrice) FROM "
				+ "(SELECT i3.* FROM tb_project_invest_happen i3 WHERE date_format(i3.investDate,'%Y-%m')=date_format(now(),'%Y-%m')) i2 "
				+ "WHERE i2.projectId = t3.id ) as montheMoney1 , "
				+ "(SELECT MAX(p.progress) FROM tb_project_progress p WHERE p.projectId = t3.id) as allProgress1 ";
		
		String sqlExceptSelect = " FROM tb_project t3 "
				+ "WHERE  t3.type = 'public' ";
		
		if(!Utils.isBlankOrEmpty(ids)){
			sqlExceptSelect += " AND t3.id IN (";
			String[] idArr = ids.split(",");
			for(String id : idArr){
				sqlExceptSelect += "'" + id + "',";
			}
			sqlExceptSelect = sqlExceptSelect.substring(0,sqlExceptSelect.length()-1)+")";
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
	
	}

	public Page<Record> pagePublicProjectByKindAndKey(int pageNumber, int pageSize, String kind,String keyWord) {
		
		String sql = "SELECT t3.* , "
				+ "(SELECT SUM(i1.investPrice) FROM tb_project_invest_happen i1 WHERE i1.projectId = t3.id) as completeMoney1, "
				+ "(SELECT SUM(i2.investPrice) FROM "
				+ "(SELECT i3.* FROM tb_project_invest_happen i3 WHERE date_format(i3.investDate,'%Y-%m')=date_format(now(),'%Y-%m')) i2 "
				+ "WHERE i2.projectId = t3.id ) as montheMoney1 , "
				+ "(SELECT MAX(p.progress) FROM tb_project_progress p WHERE p.projectId = t3.id) as allProgress1 ";
		
		String sqlExceptSelect = " FROM tb_project t3 "
				+ "WHERE  t3.type = 'public' ";
		
		if(kind!=null&&!Utils.isBlankOrEmpty(kind)){
			sqlExceptSelect += " AND t3.kind = '" + kind + "'";
		}
		if(keyWord!=null&&!Utils.isBlankOrEmpty(keyWord)){
			sqlExceptSelect += " AND t3.name like '%" + keyWord + "%'";
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
		
	}
	
	public Page<Record> pagePublicProjectByKind(Integer pageNumber, Integer pageSize, Integer kind) {
		
		String sql = "SELECT t3.* , "
				+ "(SELECT SUM(i1.investPrice) FROM tb_project_invest_happen i1 WHERE i1.projectId = t3.id) as completeMoney1, "
				+ "(SELECT SUM(i2.investPrice) FROM "
				+ "(SELECT i3.* FROM tb_project_invest_happen i3 WHERE date_format(i3.investDate,'%Y-%m')=date_format(now(),'%Y-%m')) i2 "
				+ "WHERE i2.projectId = t3.id ) as montheMoney1 , "
				+ "(SELECT MAX(p.progress) FROM tb_project_progress p WHERE p.projectId = t3.id) as allProgress1 ";
		
		String sqlExceptSelect = " FROM tb_project t3 "
				+ "WHERE t3.type = 'public' ";
		
		if(kind != -1){
			sqlExceptSelect += " AND t3.kind = "+kind;
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
	
	}
	
	public Page<Record> pageHelpProject(Integer pageNumber, Integer pageSize, String ids) {
		String sql = "SELECT t3.* , "
				+ "IFNULL((SELECT SUM(i1.investPrice) FROM tb_project_invest_happen i1 WHERE i1.projectId = t3.id),0) as completeMoney1, "
				+ "IFNULL((SELECT SUM(i2.investPrice) FROM "
				+ "(SELECT i3.* FROM tb_project_invest_happen i3 WHERE date_format(i3.investDate,'%Y-%m')=date_format(now(),'%Y-%m')) i2 "
				+ "WHERE i2.projectId = t3.id ),0) as montheMoney1 , "
				+ "IFNULL((SELECT MAX(p.progress) FROM tb_project_progress p WHERE p.projectId = t3.id),0) as allProgress1 ";
		
		String sqlExceptSelect = " FROM tb_project t3 "
				+ "WHERE t3.type = 'help' ";
		
		if(!Utils.isBlankOrEmpty(ids)){
			sqlExceptSelect += " AND t3.id IN (";
			String[] idArr = ids.split(",");
			for(String id : idArr){
				sqlExceptSelect += "'" + id + "',";
			}
			sqlExceptSelect = sqlExceptSelect.substring(0,sqlExceptSelect.length()-1)+")";
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
	}
	
	public Page<Record> pageHelpProjectByKindAndKey(int pageNumber, int pageSize, String kind,String keyWord) {
		String sql = "SELECT t3.* , "
				+ "IFNULL((SELECT SUM(i1.investPrice) FROM tb_project_invest_happen i1 WHERE i1.projectId = t3.id),0) as completeMoney1, "
				+ "IFNULL((SELECT SUM(i2.investPrice) FROM "
				+ "(SELECT i3.* FROM tb_project_invest_happen i3 WHERE date_format(i3.investDate,'%Y-%m')=date_format(now(),'%Y-%m')) i2 "
				+ "WHERE i2.projectId = t3.id ),0) as montheMoney1 , "
				+ "IFNULL((SELECT MAX(p.progress) FROM tb_project_progress p WHERE p.projectId = t3.id),0) as allProgress1 ";
		
		String sqlExceptSelect = " FROM tb_project t3 "
				+ "WHERE t3.type = 'help' ";
		
		if(kind!=null&&!Utils.isBlankOrEmpty(kind)){
			sqlExceptSelect += " AND t3.helpType = '" + kind + "'";
		}
		if(keyWord!=null&&!Utils.isBlankOrEmpty(keyWord)){
			sqlExceptSelect += " AND t3.name like '%" + keyWord + "%'";
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
		
	}
	
	public Page<Record> pageHelpProjectByKind(Integer pageNumber, Integer pageSize, Integer kind) {
		String sql = "SELECT t3.* , "
				+ "(SELECT SUM(i1.investPrice) FROM tb_project_invest_happen i1 WHERE i1.projectId = t3.id) as completeMoney1, "
				+ "(SELECT SUM(i2.investPrice) FROM "
				+ "(SELECT i3.* FROM tb_project_invest_happen i3 WHERE date_format(i3.investDate,'%Y-%m')=date_format(now(),'%Y-%m')) i2 "
				+ "WHERE i2.projectId = t3.id ) as montheMoney1 , "
				+ "(SELECT MAX(p.progress) FROM tb_project_progress p WHERE p.projectId = t3.id) as allProgress1 ";
		
		String sqlExceptSelect = " FROM tb_project t3 "
				+ "WHERE t3.type = 'help' ";
		
		if(kind != -1){
			sqlExceptSelect += " AND t3.helpType = "+kind;
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
	
	}
	
	public Page<Record> pageTrain(Integer pageNumber, Integer pageSize, String ids) {
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_train t1 WHERE t1.IsValid=0";
		
		if(!Utils.isBlankOrEmpty(ids)){
			
			String[] idArr = ids.split(",");
			for(String id : idArr){
				
				sqlExceptSelect += " AND t1.id = '" + id + "'";
				
			}
			
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
	
	}
	
	public Page<Record> pageTrainBySkillTypeAndKey(int pageNumber, int pageSize, String skillTypeId, String keyWord){
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_train t1 WHERE t1.IsValid=0 ";
		
		if(skillTypeId!=null&&!Utils.isBlankOrEmpty(skillTypeId)){
			sqlExceptSelect += " AND t1.skillTypeId = '" + skillTypeId + "'";
		}
		if(keyWord!=null&&!Utils.isBlankOrEmpty(keyWord)){
			sqlExceptSelect += " AND t1.name like '%" + keyWord + "%'";
		}
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
		
	}
	
	public Record getTrainByid(String id) {
		String sql = "select * from tb_train t1 where t1.id = '" + id + "'";
		Record model = Db.findFirst(sql);
		return model;
	}
	
	/**
	 * 
	 * @Description: 查询村名
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:50:12
	 * @param pageNumber
	 * @param pageSize
	 * @param typeId
	 * @param postId
	 * @param isWorking
	 * @param keyWord
	 * @return
	 */
	public Page<Record> pageEmploymentValliager(int pageNumber, int pageSize, String typeId,String postId,int isWorking,String keyWord) {
		
		String sql = "select rs.* ";
		
		String sqlExceptSelect = " from (select tv.*,tp.typeId,tp.name as postName,tp.orgId,tvph.postId,tvph.memberId ,tvph.ralationType,tvph.salary,tvph.createTime as mountGuardDate"
				+ ",tvph.jobCategory,tvph.companyProject,tvph.averageIncome,tvph.isFiveFund "
				+ ",tvph.isEatEncase ,tvph.isEat ,tvph.isEncase ,tvph.isOther  from tb_villager tv "
				+ " LEFT JOIN (select * from tb_villager_post_happen tvph where tvph.createTime=(select MAX(createTime) from tb_villager_post_happen )) tvph "
				+ "  on tv.id=tvph.villagerId "
 + " LEFT JOIN (select * from tb_post ) tp "
 + " on tp.id=tvph.postId where tv.isWorking=?"
 + " ) as rs where rs.typeId like '%"+typeId+"%' and rs.postId like '%"+postId+"%'";
		
		return Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect,isWorking);
	
	}
	/**
	 * @Description: 根据id查询
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午3:39:50
	 * @param pageNumber
	 * @param pageSize
	 * @param ids
	 * @return
	 */
	public Record queryPublicProjectById( String id) {
		
		String sql = "SELECT t2.*, t3.cotentAndArea,t3.completeMoney,t3.fundation,t3.selfMoney,t3.departmentMoney"+" FROM(SELECT * FROM tb_project t1) t2 "
				+ "LEFT JOIN tb_public_service t3 ON t2.id = t3.id and t2.id=?";		
		return Db.findFirst(sql, id);
	
	}
}
