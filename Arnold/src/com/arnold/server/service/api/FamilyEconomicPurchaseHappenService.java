package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.FamilyEconomicPurchaseHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 家庭经济支出信息Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class FamilyEconomicPurchaseHappenService {
	/**
	 * @Description:保存家庭经济支出信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int saveFamilyEconomicPurchaseHappen(String familyEconomicId,String familyId,
			String parentTypeId,String typeId,Double unitPrice,Integer amount,
			Integer underwritingAmount,String memberId,Date tradeTime,String userId){
		FamilyEconomicPurchaseHappen rih=new FamilyEconomicPurchaseHappen();
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setFamilyEconomicId(familyEconomicId);
		rih.setFamilyId(familyId);
		rih.setParentTypeId(parentTypeId);
		rih.setTypeId(typeId);
		rih.setUnitPrice(unitPrice);
		rih.setAmount(amount);
		rih.setUnderwritingAmount(underwritingAmount);
		rih.setPrice(unitPrice*amount);
		rih.setMemberId(memberId);
		rih.setCreatorId(userId);
		rih.setTradeTime(tradeTime);
		Date operatorTime=new Date();
		rih.setCreateTime(operatorTime);
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		try {
			rih.saveFamilyEconomicPurchaseHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:更新家庭经济支出信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int updateFamilyEconomicPurchaseHappen(String id,
			String parentTypeId,String typeId,Double unitPrice,Integer amount,
			Integer underwritingAmount,String memberId,Date tradeTime,String userId){
		FamilyEconomicPurchaseHappen rih=FamilyEconomicPurchaseHappen.queryFamilyEconomicPurchaseHappenById(id);
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
		if(rih.getUnderwritingAmount()==null||(rih.getUnderwritingAmount()!=null&&!rih.getUnderwritingAmount().equals(underwritingAmount))){
			rih.setUnderwritingAmount(underwritingAmount);
		}
		Date operatorTime=new Date();
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		try {
			rih.updateFamilyEconomicPurchaseHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:根据村id查询家庭经济支出信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public FamilyEconomicPurchaseHappen queryCollectiveEconomyById(String id){
		FamilyEconomicPurchaseHappen rih=FamilyEconomicPurchaseHappen.queryFamilyEconomicPurchaseHappenById(id);
		return rih;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<FamilyEconomicPurchaseHappen>  pageFamilyEconomicPurchaseHappen(int pageNumber,int pageSize,
			String familyEconomyId,String familyId,String parentTypeId){
		return FamilyEconomicPurchaseHappen.pageFamilyEconomicPurchaseHappen(pageNumber, pageSize,familyEconomyId,familyId,parentTypeId);
	}
	
	public Map<String, Object> queryFamilyEconomicPurchaseHappen(String familyId,String typeId,String memberId){
		FamilyEconomicPurchaseHappen rih=FamilyEconomicPurchaseHappen.queryFamilyEconomicPurchaseHappen(familyId,typeId,memberId);
		Map<String, Object> resultMap = new HashMap<>();
		if (rih != null) {
			resultMap.put(ConstUtils.R_MODEL,rih);
		}
		return resultMap;
	}
}

