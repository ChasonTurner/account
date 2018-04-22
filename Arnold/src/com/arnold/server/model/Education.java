package com.arnold.server.model;

import com.arnold.server.model.base.BaseEducation;
import com.jfinal.plugin.activerecord.Page;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Education extends BaseEducation<Education> {
	public static final Education dao = new Education();
	
	public Page<Education> pageEducationByIds(int pageNumber, int pageSize, String ids){
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_education t1 ";
		
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

	public Page<Education> pageEducationByTypeAndKey(int pageNumber, int pageSize, String type, String keyWord){
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_education t1 WHERE 1=1 ";
		
		if(type!=null&&!Utils.isBlankOrEmpty(type)){
			sqlExceptSelect += " AND t1.typeId = '" + type + "'";
		}
		if(keyWord!=null&&!Utils.isBlankOrEmpty(keyWord)){
			sqlExceptSelect += " AND t1.className like '%" + keyWord + "%'";
		}
		
		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
		
	}
	
}
