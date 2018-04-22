package com.arnold.server.model;

import com.arnold.server.model.base.BaseArea;
import com.jfinal.plugin.activerecord.Page;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Area extends BaseArea<Area> {
	public static final Area dao = new Area();
	
	public Page<Area> pageAreaByIds(int pageNumber, int pageSize, String ids){
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_area t1 ";
		
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

	public Page<Area> pageArea(int pageNumber, int pageSize) {
		String tableName = "tb_area";
		String sql = "select *";
		String sqlExceptSelect = " from " + tableName + " where isValid=?";
		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect, 0);
	}
}