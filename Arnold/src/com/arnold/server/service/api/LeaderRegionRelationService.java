package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.model.LeaderRegionRelation;
import com.arnold.server.service.BaseService;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

/**
 * @author ChangGui Pan
 * @time 2017年8月10日 下午7:03:28
 * @description TODO
 */
public class LeaderRegionRelationService extends BaseService {


	public Map<String, Object> addLeaderRelation(String leaderId, String regionId) {
		//Map<String, Object> dataMap = new HashMap<String, Object>();
		
		return null;
	}


	public Map<String, Object> deleteLeaderRegionRelation(String leaderId, String regionId) {
		//Map<String, Object> dataMap = new HashMap<String, Object>();

		return null;
	}


	public Map<String, Object> updateLeaderRegionRelation(String leaderId, String regionId, String groupId, String memberId,
			String oldRegionId, String oldGroupId, String oldMemberId) {
		//Map<String, Object> dataMap = new HashMap<String, Object>();

		return null;
	}

	public Map<String, Object> pageLeaderRegionRelation(Integer pageNumber, Integer pageSize) {
		
		return null;
	}
	
	public Map<String, Object>  updateLeaderRegionRelation(String regionId,String leaderIds) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		//先删除原来的关系，再重新增加
		List<LeaderRegionRelation> oldRelations = LeaderRegionRelation.dao.findByRegion(regionId);
		for(LeaderRegionRelation oldRelation : oldRelations) {
			oldRelation.setIsValid(-1);//逻辑删除，0正常，-1删除
			//oldRelation.delete();
			oldRelation.update();
		}
		
		//增加新的关系
		//leaderIds可能有包含多个leaderId
		//多个时是用英文逗号','隔开
		//在此拆分leaderIds
		//分别处理
		String[] leaderIdArray = leaderIds.split(",");
		for(String leaderId : leaderIdArray) {
			LeaderRegionRelation newRelation = new LeaderRegionRelation();
			newRelation.setId(Utils.create36UUID());
			newRelation.setLeaderId(leaderId);
			newRelation.setRegionId(regionId);
			
			newRelation.setCreateTime(new Date());
			newRelation.setIsValid(0);
			
			newRelation.save();	
		}	
		
		dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		dataMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return dataMap;
	}
	
}
