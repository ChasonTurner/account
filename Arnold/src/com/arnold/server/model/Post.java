package com.arnold.server.model;

import com.arnold.server.model.base.BasePost;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Post extends BasePost<Post> {
	public static final Post dao = new Post();
	
	public Page<Post> pagePostByIds(int pageNumber, int pageSize, String ids){
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_post t1 ";
		
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

	public Page<Record> pagePostRecordByKeyword(int pageNumber, int pageSize, String typeId, String keyword) {

		String select = "SELECT p.*,e.`name` AS orgName,e.contact AS enContact,e.phone AS enPhone,e.email AS enEmail ";
		String sqlExceptSelect = " FROM tb_post p LEFT JOIN tb_enterprise e ON p.orgId=e.id WHERE 1=1 " ;
		if(!Utils.isBlankOrEmpty(typeId)){
			sqlExceptSelect+=" AND p.typeId='"+typeId+"' ";
		}
		if(!Utils.isBlankOrEmpty(keyword)){
			sqlExceptSelect+=" AND ( p.name LIKE '%"+keyword+"%' OR e.name  LIKE '%"+keyword+"%' ) ";
		}
		sqlExceptSelect+=" ORDER BY p.updateTime desc ";
		return Db.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}

	public Page<Record> pagePostByEId(int pageNumber, int pageSize, String enId) {
		String select = "SELECT p.*, COUNT(v1.id) AS llrs, COUNT(v2.id) AS bllrs ";
		String sqlExceptSelect = "FROM tb_post p LEFT JOIN tb_villager_post_happen v1 ON v1.postId = p.id AND v1.ralationType = 3 " +
				"LEFT JOIN tb_villager_post_happen v2 ON v2.postId = p.id AND v2.ralationType = 2 WHERE 1=1 AND p.orgId=? GROUP BY p.id " ;
		sqlExceptSelect+=" ORDER BY p.updateTime desc ";
		return Db.paginate(pageNumber, pageSize, select, sqlExceptSelect ,enId);
	}
}