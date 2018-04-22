package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.CollectiveEconomyPurchaseHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 集体经济采购信息Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class CollectiveEconomyPurchaseHappenService {
	/**
	 * @Description:保存集体经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int saveCollectiveEconomyPurchaseHappen(String collectiveEconomyId,String regionId,String industryTypeId,String purchaseContent,
			Double totalPrice,String orgId,Date tradeTime,String createUserId){
		CollectiveEconomyPurchaseHappen rih=new CollectiveEconomyPurchaseHappen();
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setRegionId(regionId);
		rih.setIndustryTypeId(industryTypeId);
		rih.setCollectiveEconomyId(collectiveEconomyId);
		rih.setPurchaseContent(purchaseContent);
		rih.setTotalPrice(totalPrice);
		rih.setOrgId(orgId);
		rih.setTradeTime(tradeTime);
		rih.setCreatorId(createUserId);
		Date operatorTime=new Date();
		rih.setCreateTime(operatorTime);
		rih.setOperatorId(createUserId);
		rih.setOperatorTime(operatorTime);
		try {
			rih.saveCollectiveEconomyPurchaseHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:更新集体经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int updateCollectiveEconomyPurchaseHappen(String id,String purchaseContent,
			Double totalPrice,String orgId,Date tradeTime,String userId){
		CollectiveEconomyPurchaseHappen rih=CollectiveEconomyPurchaseHappen.queryCollectiveEconomyPurchaseHappenById(id);
		if(rih==null){
			return ArnoldUtils.NOT_FIND_ERRO;
		}
		if(rih.getPurchaseContent()==null||(rih.getPurchaseContent()!=null&&!rih.getPurchaseContent().equals(purchaseContent))){
			rih.setPurchaseContent(purchaseContent);
		}
		if(rih.getTotalPrice()==null||(rih.getTotalPrice()!=null&&!rih.getTotalPrice().equals(totalPrice))){
			rih.setTotalPrice(totalPrice);
		}
		if(rih.getOrgId()==null||(rih.getOrgId()!=null&&!rih.getOrgId().equals(orgId))){
			rih.setOrgId(orgId);
		}
		if(rih.getTradeTime()==null||(rih.getTradeTime()!=null&&!rih.getTradeTime().equals(tradeTime))){
			rih.setTradeTime(tradeTime);
		}
		Date operatorTime=new Date();
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		try {
			rih.updateCollectiveEconomyPurchaseHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:根据村id查询集体经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public CollectiveEconomyPurchaseHappen queryCollectiveEconomyPurchaseById(String id){
		CollectiveEconomyPurchaseHappen rih=CollectiveEconomyPurchaseHappen.queryCollectiveEconomyPurchaseHappenById(id);
		return rih;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<CollectiveEconomyPurchaseHappen>  pageCollectiveEconomyPurchaseHappen(int pageNumber,int pageSize,String collectiveEconomyId,String regionId){
		return CollectiveEconomyPurchaseHappen.pageCollectiveEconomyPurchaseHappen(pageNumber, pageSize,collectiveEconomyId,regionId);
	}
	
}

