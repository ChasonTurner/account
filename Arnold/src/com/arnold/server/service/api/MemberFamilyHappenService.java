package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.FamilyBurdenRelation;
import com.arnold.server.model.MemberFamilyHappen;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;


/**
 * @Author PanChangGui
 * @Time 2017年10月7日 上午11:29:22
 * @Description 扶贫人员与帮扶家庭的流水表
 */
public class MemberFamilyHappenService {
	
	public Map<String, Object> addMemberFamilyHappen(String memberId, String familyIds, String helpResult, Date startTime, Date endTime){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//多个时用英文逗号隔开，这里解析
		String[] array = familyIds.split(",");
		for(String familyId : array) {
			MemberFamilyHappen happen = new MemberFamilyHappen();
			String id=Utils.create36UUID();
			happen.setId(id);
			happen.setMemberId(memberId);
			happen.setFamilyId(familyId);
			happen.setHelpResult(helpResult);
			happen.setStartTime(startTime);
			happen.setEndTime(endTime);
			
			happen.setCreateTime(new Date());
			happen.setIsValid(0);
			
			happen.save();	
		}
		
		resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SUCCESS_CODE);
		resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SUCCESS_CODE_STR);
		return resultMap;
	}
	
	
}

