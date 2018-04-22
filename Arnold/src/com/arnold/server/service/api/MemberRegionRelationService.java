package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.model.MemberRegionRelation;
import com.arnold.server.service.BaseService;
//import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

/**
 * @author ChangGui Pan
 * @time 2017年8月10日 下午7:03:28
 * @description TODO
 */
public class MemberRegionRelationService extends BaseService {

	public Map<String, Object> addMemberRegionRelation(String regionId, String memberId) {
		//Map<String, Object> dataMap = new HashMap<String, Object>();

		return null;
	}

	public Map<String, Object> deleteMemberRegionRelation(String regionId, String memberId) {
		
		return null;
	}

	
	public Map<String, Object> updateMemberRegionRelation(String regionId, String memberId, String oldMemberId) {
		//Map<String, Object> dataMap = new HashMap<String, Object>();
		
		return null;
	}


	public Map<String, Object> pageMemberRegionRelation(Integer pageNumber, Integer pageSize) {
		//Map<String, Object> dataMap = new HashMap<String, Object>();
	
		return null;
	}
	
	public Map<String, Object>  updateMemberRegionRelation(String regionId,String memberIds) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		//先删除原来的关系，再重新增加
//		List<MemberRegionRelation> oldRelations = MemberRegionRelation.dao.findByRegion(regionId);
		List<MemberRegionRelation> oldRelations = MemberRegionRelation.dao.findByRegion(regionId, 0);
		
		for(MemberRegionRelation oldRelation : oldRelations) {
			oldRelation.setIsValid(-1);//逻辑删除，0正常，-1删除
			oldRelation.update();
		}
		
		//增加新的关系
		//memberIds可能有包含多个memberId
		//多个时是用英文逗号','隔开
		//在此拆分memberIds
		//分别处理
		//String memberId = memberIds;
		String[] memberIdArray = memberIds.split(",");
		for(String memberId : memberIdArray) {
//			MemberRegionRelation newRelation = new MemberRegionRelation();
//			newRelation.setId(Utils.create36UUID());
//			newRelation.setMemberId(memberId);
//			newRelation.setRegionId(regionId);
//			
//			newRelation.setCreateTime(new Date());
//			newRelation.setIsValid(0);
//			
//			newRelation.save();	
			MemberRegionRelation.dao.saveRecord(memberId,regionId, 0);
		}	
		
		dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		dataMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return dataMap;
	}
	
	/**
	 * 更新第一书记记录
	 * @param regionId
	 * @param firstSecretaryMemberId
	 * @return
	 */
	public boolean  updateFirstSecretaryInfo(String regionId,String firstSecretaryMemberId) {
		boolean result = false;
		
		//失效历史
		List<MemberRegionRelation> oldRelations = MemberRegionRelation.dao.findByRegion(regionId, 1);
		if(null!=oldRelations && oldRelations.size()>0){
			for(MemberRegionRelation oldRelation : oldRelations) {
				oldRelation.setIsValid(-1);//逻辑删除，0正常，-1删除
				oldRelation.setUpdateTime(new Date());
				oldRelation.update();
			}
		}
		
		//新增第一书记记录
		result = MemberRegionRelation.dao.saveRecord(firstSecretaryMemberId, regionId, 1);
		
		return result;
	}
	
}
