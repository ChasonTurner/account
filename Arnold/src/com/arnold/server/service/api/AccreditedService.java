package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Accredited;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
//import com.huntersun.tool.DateUtil;
import com.huntersun.tool.Utils;

/**
 * @author ChangGui Pan
 * @time 2017年8月10日 下午6:50:40
 * @description TODO
 */
public class AccreditedService extends BaseService {
	
	public Map<String, Object> addAccredit(String memberId, String accreditDepartmentId, String orgPost, String accreditPlaceId,		
			String accreditPostId, Date accreditStart, Date accreditEnd, String remark) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Accredited model = Accredited.dao.findByMemberId(memberId);
		if(model != null) {
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.MEMBER_EXIST_CODE);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_EXIST_CODE_STR);
			return resultMap;
		}
		
		Accredited accredited = new Accredited();
		accredited.setId(Utils.create36UUID());
		accredited.setMemberId(memberId);
		accredited.setAccreditDepartmentId(accreditDepartmentId);
		accredited.setOrgPost(orgPost);
		accredited.setAccreditPlaceId(accreditPlaceId);
		accredited.setAccreditPostId(accreditPostId);
		accredited.setAccreditStart(accreditStart);
		accredited.setAccreditEnd(accreditEnd);
		accredited.setRemark(remark);
		accredited.setCreateTime(new Date());
		accredited.setIsValid(0);
		accredited.save();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);	
		return resultMap;
	}

	public Map<String, Object> updateAccredit(String memberId, String accreditDepartmentId, String orgPost,
			String accreditPlaceId,
			String accreditPostId, Date accreditStart, Date accreditEnd, String remark) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Accredited model = Accredited.dao.findByMemberId(memberId);
		if(model == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.MEMBER_EXIST_CODE);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_EXIST_CODE_STR);
			return resultMap;
		}
		
		if(!Utils.isBlankOrEmpty(accreditDepartmentId)) model.setAccreditDepartmentId(accreditDepartmentId);
		if(!Utils.isBlankOrEmpty(orgPost)) model.setOrgPost(orgPost);
		if(!Utils.isBlankOrEmpty(accreditPlaceId)) model.setAccreditPlaceId(accreditPlaceId);
		if(!Utils.isBlankOrEmpty(accreditPostId)) model.setAccreditPostId(accreditPostId);
		if(null != accreditStart) model.setAccreditStart(accreditStart);
		if(null != accreditEnd) model.setAccreditEnd(accreditEnd);
		if(!Utils.isBlankOrEmpty(remark)) model.setRemark(remark);
			
		model.setUpdateTime(new Date());
		
		model.update();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);	
		return resultMap;
	}
	
}
