package com.arnold.server.model;

import com.arnold.server.model.base.BaseHouse;
import com.jfinal.plugin.activerecord.Page;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class House extends BaseHouse<House> {
	public static final House dao = new House();
	
	public Page<House> pageHouseByIds(int pageNumber, int pageSize, String ids){
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_house t1 ";
		
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
