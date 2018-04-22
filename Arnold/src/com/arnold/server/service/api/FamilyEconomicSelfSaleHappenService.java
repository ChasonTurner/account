package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.FamilyEconomicSelfSaleHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 家庭经济自主收入信息Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class FamilyEconomicSelfSaleHappenService {
	/**
	 * @Description:保存家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int saveFamilyEconomicSelfSaleHappen(String familyEconomicId,String familyId,
			String parentTypeId,String typeId,Double unitPrice,Integer amount,
			String memberId,Date tradeTime,String userId,Integer surplus,Double sale_amount2){
		FamilyEconomicSelfSaleHappen rih=new FamilyEconomicSelfSaleHappen();
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setFamilyEconomicId(familyEconomicId);
		rih.setFamilyId(familyId);
		rih.setParentTypeId(parentTypeId);
		rih.setTypeId(typeId);
		rih.setUnitPrice(unitPrice);
		rih.setAmount(amount);
		rih.setPrice(unitPrice*amount);
		rih.setMemberId(memberId);
		rih.setCreatorId(userId);
		rih.setTradeTime(tradeTime);
		Date operatorTime=new Date();
		rih.setCreateTime(operatorTime);
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		rih.setSale_amount2(sale_amount2);
		if (surplus != null) {
			rih.setSurplus(surplus);
		}
		try {
			rih.saveFamilyEconomicSelfSaleHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:更新家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int updateFamilyEconomicSelfSaleHappen(String id,
			String parentTypeId,String typeId,Double unitPrice,Integer amount,
			String memberId,Date tradeTime,String userId,Integer surplus,Double sale_amount2){
		FamilyEconomicSelfSaleHappen rih=FamilyEconomicSelfSaleHappen.queryFamilyEconomicSelfSaleHappenById(id);
		if(rih==null){
			return ArnoldUtils.NOT_FIND_ERRO;
		}
		if(rih.getParentTypeId()==null||(rih.getParentTypeId()!=null&&!rih.getParentTypeId().equals(parentTypeId))){
			rih.setParentTypeId(parentTypeId);
		}
		if(rih.getTypeId()==null||(rih.getTypeId()!=null&&!rih.getTypeId().equals(typeId))){
			rih.setTypeId(typeId);
		}
		if(rih.getUnitPrice()==null||(rih.getUnitPrice()!=null&&!rih.getUnitPrice().equals(unitPrice))){
			rih.setUnitPrice(unitPrice);
		}
		if(rih.getAmount()==null||(rih.getAmount()!=null&&!rih.getAmount().equals(amount))){
			rih.setAmount(amount);
		}
		Date operatorTime=new Date();
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		rih.setSurplus(surplus);
		rih.setSale_amount2(sale_amount2);
		try {
			rih.updateFamilyEconomicSelfSaleHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:根据村id查询家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public FamilyEconomicSelfSaleHappen queryFamilyEconomySelfSaleById(String id){
		FamilyEconomicSelfSaleHappen rih=FamilyEconomicSelfSaleHappen.queryFamilyEconomicSelfSaleHappenById(id);
		return rih;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<FamilyEconomicSelfSaleHappen>  pageFamilyEconomicSelfSaleHappen(int pageNumber,int pageSize,
			String familyEconomyId,String familyId,String parentTypeId){
		return FamilyEconomicSelfSaleHappen.pageFamilyEconomicSelfSaleHappen(pageNumber, pageSize,familyEconomyId, familyId,parentTypeId);
	}
	
}

