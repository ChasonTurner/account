package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.VillagerPostIncomeHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: (业务)流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 上午11:02:19
 */
public class VillagerIncomeHappenService {
	/**
	 * @Description:保存村名收入
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Map<String, Object>  addVillagerIncome(String villagerId,String postId,Date incomeTime,Double income,String createUserId){
		Map<String, Object> operMap = new HashMap<String, Object>();
		VillagerPostIncomeHappen  vpih=new VillagerPostIncomeHappen();
		String id=Utils.create36UUID();
		vpih.setId(id);
		vpih.setVillagerId(villagerId);
		vpih.setPostId(postId);
		vpih.setIncomeTime(incomeTime);
		vpih.setIncome(income);
		vpih.setCreateUserId(createUserId);
		vpih.setCreateTime(new Date());
		try {
			vpih.saveVillagerPostIncomeHappen(vpih);
			operMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
			operMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			operMap.put(ConstUtils.R_MSG,  ConstUtils.DATA_OPERATE_ERROR_STR);
			operMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			ArnoldUtils.logger.error(e.getLocalizedMessage());
		}
		return operMap;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Page<VillagerPostIncomeHappen>  pageVillagerIncome(int pageNumber,int pageSize,String villagerId,String postId,String keyWord){
		return VillagerPostIncomeHappen.pageVillagerPostIncomeHappen(pageNumber, pageSize, villagerId, postId, keyWord);
	}

}

