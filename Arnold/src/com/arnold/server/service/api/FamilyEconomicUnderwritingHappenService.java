package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.FamilyEconomicUnderwritingHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 集体经济收入信息Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class FamilyEconomicUnderwritingHappenService {
	/**
	 * @Description:保存家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int saveFamilyEconomicUnderwritingHappen(String familyEconomicId,String familyId,
			String parentTypeId,String typeId,Double unitPrice,Integer amount,
			String memberId,Date tradeTime,String userId,Double sale_amount1,Double surplus){
		FamilyEconomicUnderwritingHappen rih=new FamilyEconomicUnderwritingHappen();
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
		rih.setSurplus(surplus);		
		rih.setSale_amount1(sale_amount1);
		
		try {
			rih.saveFamilyEconomicUnderwritingHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:更新家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int updateFamilyEconomicUnderwritingHappen(String id,
			String parentTypeId,String typeId,Double unitPrice,Integer amount,
			String memberId,Date tradeTime,String userId,Double sale_amount1,Double surplus){
		FamilyEconomicUnderwritingHappen rih=FamilyEconomicUnderwritingHappen.queryFamilyEconomicUnderwritingHappenById(id);
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
		rih.setSale_amount1(sale_amount1);
		try {
			rih.updateFamilyEconomicUnderwritingHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:根据村id查询家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public FamilyEconomicUnderwritingHappen queryFamilyEconomyById(String id){
		FamilyEconomicUnderwritingHappen rih=FamilyEconomicUnderwritingHappen.queryFamilyEconomicUnderwritingHappenById(id);
		return rih;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<FamilyEconomicUnderwritingHappen>  pageFamilyEconomicUnderwritingHappen(int pageNumber,int pageSize,
			String familyEconomyId,String familyId,String parentTypeId){
		return FamilyEconomicUnderwritingHappen.pageFamilyEconomicUnderwritingHappen(pageNumber, pageSize,familyEconomyId, familyId,parentTypeId);
	}
	
}

