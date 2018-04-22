package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.MemberFamilyRelation;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author ChangGui Pan
 * @time 2017年8月13日 下午8:24:31
 * @description TODO
 */
public class MemberFamilyRelationService extends BaseService {

	/**
	 * 增加扶贫人员与家庭的关系
	 * @Method:addMemberFamilyRelation
	 * @Date:2017年8月14日 下午2:42:03
	 * @Author:YangCheng
	 * @param memberId
	 * @param familyId
	 * @return
	 */
	public Map<String, Object> addMemberFamilyRelation(String memberIds, String familyId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {		
			//多个时用英文逗号隔开，这里解析
			String[] array = memberIds.split(",");
			for(String memberId : array) {				
				MemberFamilyRelation relation = MemberFamilyRelation.dao.findBy(familyId, memberId);
				if (relation == null) {
					addRelation(memberId,familyId);
				}
			}

			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return dataMap;
		} catch (Exception e) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 删除扶贫人员与家庭的关系
	 * @Method:deleteMemberFamilyRelation
	 * @Date:2017年8月14日 下午2:42:17
	 * @Author:YangCheng
	 * @param memberId
	 * @param familyId
	 * @return
	 */
	public Map<String, Object> deleteMemberFamilyRelation(String memberId, String familyId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			MemberFamilyRelation relation = MemberFamilyRelation.dao.findBy(familyId, memberId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			deleteRelation(familyId, memberId);

			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return dataMap;
		} catch (Exception e) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 更新成员与家庭的关系
	 * @Method:updateMemberFamilyRelation
	 * @Date:2017年8月14日 下午2:42:29
	 * @Author:YangCheng
	 * @param memberId
	 * @param oldMemberId
	 * @param familyId
	 * @return
	 */
	public Map<String, Object> updateMemberFamilyRelation(String memberId, String oldMemberId, String familyId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			MemberFamilyRelation relation = MemberFamilyRelation.dao.findBy(familyId, oldMemberId);
			if (relation == null) {
				dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.RELATION_NOT_EXIST_CODE);
				dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.RELATION_NOT_EXIST_CODE_STR);
				return dataMap;
			}

			updateRelation(familyId, oldMemberId, memberId);

			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return dataMap;
		} catch (Exception e) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	/**
	 * 分页查询家庭与成员的关系
	 * @Method:pageMemberFamilyRelation
	 * @Date:2017年8月14日 下午6:32:23
	 * @Author:YangCheng
	 * @param familyId
	 * @param memberId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageMemberFamilyRelation(String familyId, String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			Page<MemberFamilyRelation> page = MemberFamilyRelation.dao.pageMemberFamilyRelation(familyId,memberId, pageNumber,
					pageSize);

			dataMap.put(ConstUtils.R_PAGE, page);
			dataMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			dataMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			return dataMap;
		} catch (Exception e) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return dataMap;
		}
	}

	private String addRelation(String memberId, String familyId) {
		MemberFamilyRelation relation = new MemberFamilyRelation();

		relation.setId(Utils.create36UUID());
		relation.setMemberId(memberId);
		relation.setFamilyId(familyId);
		relation.setCreateTime(new Date());
		relation.save();

		return relation.getId();
	}

	private int deleteRelation(String memberId, String familyId) {
		MemberFamilyRelation relation = MemberFamilyRelation.dao.findBy(familyId, memberId);
		if (relation != null) {
			relation.setIsValid(-1);
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

	private int updateRelation(String memberId, String oldMemberId, String familyId) {
		MemberFamilyRelation relation = MemberFamilyRelation.dao.findBy(familyId, oldMemberId);
		if (relation != null) {
			relation.setMemberId(memberId);
			relation.update();
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * @Author PanChangGui
	 * @Time 2017年10月7日 下午3:58:33
	 * @Description 
	 */
	public Map<String, Object> addMemberFamilyRelation(String memberId, String familyIds, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		Map<String, Object> returnMap = new HashMap<String, Object>();

		try {		
			//多个时用英文逗号隔开，这里解析
			String[] array = familyIds.split(",");
			for(String familyId : array) {
				MemberFamilyRelation oldRelation = MemberFamilyRelation.dao.findBy(familyId, memberId);
				if (oldRelation == null) {
					MemberFamilyRelation relation = new MemberFamilyRelation();

					relation.setId(Utils.create36UUID());
					relation.setMemberId(memberId);
					relation.setFamilyId(familyId);
					relation.setStartTime(startTime);
					relation.setEndTime(endTime);
					relation.setCreateTime(new Date());
					
					relation.save();
				}
			}

			returnMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
			returnMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
			return returnMap;
		} catch (Exception e) {
			returnMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			returnMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return returnMap;
		}
		
	}
}
