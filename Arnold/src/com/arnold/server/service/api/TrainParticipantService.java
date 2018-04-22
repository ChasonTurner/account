package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.model.TrainParticipant;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class TrainParticipantService extends BaseService {
	
	public List<Record> pageTrainParticipantByTrainid(int pageNumber, int pageSize,
			String trainid) {
		TrainParticipant trainParticipant = new TrainParticipant();
		return trainParticipant.pageTrainParticipantByTrainid(pageNumber, pageSize, trainid);
	}
	
	public Page<TrainParticipant> pageTrainParticipant(int pageNumber, int pageSize,
			String id) {
		TrainParticipant trainParticipant = new TrainParticipant();
		return trainParticipant.pageTrainParticipant(pageNumber, pageSize, id);
	}
	
	public void addTrainParticipant(String trainid, String joinPersonid,
			String helpPersonid, String joinMoneyType, String writer, String nowWriter) {
		TrainParticipant model = new TrainParticipant();
		model.setId(Utils.create36UUID());
		model.setTrainid(trainid);
		model.setJoinPersonid(joinPersonid);
		model.setHelpPersonid(helpPersonid);
		model.setJoinMoneyType(joinMoneyType);
		model.setWriter(writer);
		model.setCreateTime(new Date());
		model.setNowWriter(nowWriter);
		model.setNowCreateTime(new Date());
		model.save();
	}
	
	public Map<String, Object> delTrainParticipant(String id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainParticipant model = TrainParticipant.dao.findById(id);
			
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

