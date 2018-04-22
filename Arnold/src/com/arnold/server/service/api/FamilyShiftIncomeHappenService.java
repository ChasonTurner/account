package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.FamilyShiftIncomeHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 贫困户转移性收入流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class FamilyShiftIncomeHappenService {
	/**
	 * @Description:保存贫困户转移性收入流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int  addFamilyShiftIncomeHappen(String fmailyId,Date incomeTime,Double income,String incomTypeId,String createUserId){
		FamilyShiftIncomeHappen  fsih=new FamilyShiftIncomeHappen();
		String id=Utils.create36UUID();
		fsih.setId(id);
		fsih.setFmailyId(fmailyId);
		fsih.setIncomeTime(incomeTime);
		fsih.setIncome(income);
		fsih.setCreateUserId(createUserId);
		fsih.setIncomTypeId(incomTypeId);
		fsih.setCreateTime(new Date());
		try {
			fsih.saveFamilyShiftIncomeHappen(fsih);
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
	public Page<FamilyShiftIncomeHappen>  pageFamilyShiftIncomeHappen(int pageNumber,int pageSize,String fmailyId,String keyWord){
		return FamilyShiftIncomeHappen.pageFamilyShiftIncomeHappen(pageNumber, pageSize, fmailyId, keyWord);
	}
	
}

