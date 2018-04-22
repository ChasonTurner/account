package com.arnold.server.model;

import com.arnold.server.model.base.BasePostCompany;
import com.jfinal.plugin.activerecord.Page;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class PostCompany extends BasePostCompany<PostCompany> {
	public static final PostCompany dao = new PostCompany();
	
	public Page<PostCompany> pagePostCompanyByIds(int pageNumber, int pageSize, String ids){
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_post_company t1 ";
		
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
	
}