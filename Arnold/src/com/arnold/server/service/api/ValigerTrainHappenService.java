package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.ValigerTrainHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 村名培训流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class ValigerTrainHappenService {
	/**
	 * @Description:保存村名培训流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int  addValigerTrainHappen(String valigerId,String memberId,String trainId,int ralationType,Date trainTime,
			String createUserId){
		ValigerTrainHappen  fsih=new ValigerTrainHappen();
		String id=Utils.create36UUID();
		fsih.setId(id);
		fsih.setValigerId(valigerId);
		fsih.setMemberId(memberId);

		fsih.setTrainId(trainId);
		fsih.setRalationType(ralationType);
		fsih.setTrainTime(trainTime);
		fsih.setCreateUser(createUserId);
		try {
			fsih.saveValigerTrainHappen(fsih);
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
	public Page<ValigerTrainHappen>  pageValigerTrainHappen(int pageNumber,int pageSize,String valigerId,String trainId,int ralationType){
		return ValigerTrainHappen.pageValigerTrainHappen(pageNumber, pageSize, valigerId,trainId, ralationType);
	}
	
}

