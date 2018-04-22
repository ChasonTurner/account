package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.MemberCommendHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 扶贫人员表彰流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class MemberCommendHappenService {
	
	/**
	 * @Description:保存 扶贫人员表彰流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int  addMemberCommendHappen(String memberId,String awardName,String commendLevelId,String commendObjectId,Date commendDate,String remark,String createUserId){
		MemberCommendHappen mop=new MemberCommendHappen();
		String id=Utils.create36UUID();
		mop.setId(id);
		mop.setMemberId(memberId);
		mop.setAwardName(awardName);
		mop.setCommendLevelId(commendLevelId);
		mop.setCommendObjectId(commendObjectId);
		mop.setCommendDate(commendDate);
		mop.setRemark(remark);
		mop.setCreatorId(createUserId);
		mop.setCreateTime(new Date());
		mop.setUpdateTime(new Date());
		mop.setIsValid(0);
		
		try {
			mop.saveMemberCommendHappen(mop);
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
	public Page<MemberCommendHappen> pageMemberCommendHappen(int pageNumber,int pageSize,String memberId){
		return MemberCommendHappen.pageMemberCommendHappen(pageNumber, pageSize, memberId);
	}
	
}

