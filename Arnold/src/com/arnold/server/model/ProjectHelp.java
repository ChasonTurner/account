package com.arnold.server.model;

import com.arnold.server.model.base.BaseProject;
import com.arnold.server.model.base.BasePublicService;
import com.jfinal.plugin.activerecord.Page;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ProjectHelp extends BaseProject<ProjectHelp> {
	public static final ProjectHelp dao = new ProjectHelp();

//	public Page<ProjectHelp> pagePublicServiceByIds(int pageNumber, int pageSize, String ids){
//		
//		String sql = "SELECT *";
//		
//		String sqlExceptSelect = " FROM tb_project t1 WHERE 1=1 ";
//		
//		if(!Utils.isBlankOrEmpty(ids)){
//			
//			String[] idStrArr = ids.split(",");
//			int i = 0;
//			for(String str : idStrArr){
//				if(i == 0){
//					sqlExceptSelect += " WHERE t1.id = '" + str + "'";
//				}else{
//					sqlExceptSelect += " OR t1.id = '" + str + "'";
//				}
//				i++;
//			}
//			
//		}
//		
//		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
//				
//	}
	
}
