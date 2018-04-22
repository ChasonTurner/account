package com.arnold.server.service.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Help;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class HelpService extends BaseService {

	public Map<String, Object> addHelp(int year){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		try {
			
			Help helpModel = new Help();
			
			helpModel.setId(Utils.create36UUID());
			helpModel.setYear(year);

			helpModel.save();
			
			resultMap.put(ConstUtils.R_PAGE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
			return resultMap;
			
		}
		
			
		return resultMap;
		
	}
	
	public Map<String, Object> pageHelp(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Help help = new Help();
			
			Page<Help> helpModel = help.pageHelpByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, helpModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageHelpByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Help help = new Help();
			
			Page<Help> helpModel = help.pageHelpByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, helpModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateHelp(String id,int year){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Help helpModel = Help.dao.findById(id);
			
			if(helpModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个帮扶项目！");
				return resultMap;
			}
			
			if(year != -1){
				helpModel.setYear(year);;
			}
			
			helpModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> delHelp(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Help helpModel = Help.dao.findById(id);
			
			if(helpModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个帮扶项目！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//helpModel.delete();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
			
		}
		
		return resultMap;
		
	}
	
}
