package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.MemberOrgHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 人员组织关系流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class MemberOrgHappenService {
	/**
	 * @Description:保存人员组织关系流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int  addMemberOrgHappen(String memberId,String orgId,String posId,int ralationType,String createUserId){
		MemberOrgHappen  mop=new MemberOrgHappen();
		String id=Utils.create36UUID();
		mop.setId(id);
		mop.setMemberId(memberId);
		mop.setOrgId(orgId);
		mop.setPostId(posId);
		mop.setRalationType(ralationType);
		mop.setCreateUserId(createUserId);
		mop.setCreateTime(new Date());
		try {
			mop.saveMemberOrgHappen(mop);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Page<MemberOrgHappen>  pageMemberOrgHappen(int pageNumber,int pageSize,String memberId,String orgId,String posId,String ralationType,String keyWord){
		return MemberOrgHappen.pageMemberOrgHappen(pageNumber, pageSize, memberId, orgId, posId, ralationType, keyWord);
	}
	
}

