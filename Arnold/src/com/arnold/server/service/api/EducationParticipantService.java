package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.model.EducationParticipant;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class EducationParticipantService extends BaseService {
	
	public List<Record> pageEducationParticipantByEducationid(int pageNumber, int pageSize,
			String trainid) {
		EducationParticipant educationParticipant = new EducationParticipant();
		return educationParticipant.pageEducationParticipantByEducationid(pageNumber, pageSize, trainid);
	}
	
	public Page<EducationParticipant> pageEducationParticipant(int pageNumber, int pageSize,
			String id) {
		EducationParticipant educationParticipant = new EducationParticipant();
		return educationParticipant.pageEducationParticipant(pageNumber, pageSize, id);
	}
	
	public void addEducationParticipant(String educationid, String joinPersonid,
			String helpPersonid,String joinMoneyType, String writer, String nowWriter) {
		EducationParticipant model = new EducationParticipant();
		model.setId(Utils.create36UUID());
		model.setEducationid(educationid);
		model.setJoinPersonid(joinPersonid);
		model.setHelpPersonid(helpPersonid);
		model.setJoinMoneyType(joinMoneyType);
		model.setWriter(writer);
		model.setCreateTime(new Date());
		model.setNowWriter(nowWriter);
		model.setNowCreateTime(new Date());
		model.save();
	}
	
	public Map<String, Object> delEducationParticipant(String id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			EducationParticipant model = EducationParticipant.dao.findById(id);
			
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个主讲人！");
				return resultMap;
			}
			
			model.delete();
			
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

