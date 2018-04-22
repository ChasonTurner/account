package com.arnold.server.model;

import com.arnold.server.model.base.BaseTrain;
import com.arnold.server.model.base.BaseTrainSpeaker;
import com.jfinal.plugin.activerecord.Page;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class TrainSpeaker extends BaseTrainSpeaker<TrainSpeaker> {
	public static final TrainSpeaker dao = new TrainSpeaker();
	
	public Page<TrainSpeaker> pageTrainSpeaker(int pageNumber, int pageSize, String ids){
		
		String sql = "SELECT *";
		
		String sqlExceptSelect = " FROM tb_train_speaker t1 WHERE 1=1 ";
		
		if(!Utils.isBlankOrEmpty(ids)){
			sqlExceptSelect += " AND t1.id IN (";
			String[] idArr = ids.split(",");
			for(String id : idArr){
				sqlExceptSelect += "'" + id + "',";
			}
			sqlExceptSelect = sqlExceptSelect.substring(0,sqlExceptSelect.length()-1)+")";
		}
		
		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
				
	}
	
	public Page<TrainSpeaker> pageTrainSpeakerByTrainid(int pageNumber, int pageSize, String trainid) {
		String sql = "SELECT * ";
		
		String sqlExceptSelect = " FROM tb_train_speaker t1 ";
		
		if(!Utils.isBlankOrEmpty(trainid)){
			sqlExceptSelect += " WHERE t1.trainid = '" + trainid + "'";
		}
		
		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect);
	}

}
