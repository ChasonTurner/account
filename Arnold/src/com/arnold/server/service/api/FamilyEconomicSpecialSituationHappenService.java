package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.FamilyEconomicSpecialSituationHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 集体经济特殊情况信息Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class FamilyEconomicSpecialSituationHappenService {
	/**
	 * @Description:保存家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int saveFamilyEconomicSpecialSituationHappen(String familyEconomicId,String familyId,
			String parentTypeId,String typeId,Integer amount,
			Integer increaseDecrease,String userId,String remark){
		FamilyEconomicSpecialSituationHappen rih=new FamilyEconomicSpecialSituationHappen();
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setFamilyEconomicId(familyEconomicId);
		rih.setFamilyId(familyId);
		rih.setParentTypeId(parentTypeId);
		rih.setTypeId(typeId);
		rih.setAmount(amount);
		rih.setCreatorId(userId);
		rih.setIncreaseDecrease(increaseDecrease);
		Date operatorTime=new Date();
		rih.setCreateTime(operatorTime);
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		rih.setRemark(remark);
		try {
			rih.saveFamilyEconomicSpecialSituationHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:更新家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int updateFamilyEconomicSpecialSituationHappen(String id,
			String parentTypeId,String typeId,Integer amount,
			Integer increaseDecrease,String userId,String remark){
		FamilyEconomicSpecialSituationHappen rih=FamilyEconomicSpecialSituationHappen.queryFamilyEconomicSpecialSituationHappenById(id);
		if(rih==null){
			return ArnoldUtils.NOT_FIND_ERRO;
		}
		if(rih.getParentTypeId()==null||(rih.getParentTypeId()!=null&&!rih.getParentTypeId().equals(parentTypeId))){
			rih.setParentTypeId(parentTypeId);
		}
		if(rih.getTypeId()==null||(rih.getTypeId()!=null&&!rih.getTypeId().equals(typeId))){
			rih.setTypeId(typeId);
		}
		if(rih.getIncreaseDecrease()==null||(rih.getIncreaseDecrease()!=null&&!rih.getIncreaseDecrease().equals(increaseDecrease))){
			rih.setIncreaseDecrease(increaseDecrease);
		}
		if(rih.getAmount()==null||(rih.getAmount()!=null&&!rih.getAmount().equals(amount))){
			rih.setAmount(amount);
		}
		Date operatorTime=new Date();
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		rih.setRemark(remark);
		try {
			rih.updateFamilyEconomicSpecialSituationHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:删除家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int updateFamilyEconomicSpecialSituationHappen(String id){
		FamilyEconomicSpecialSituationHappen rih=FamilyEconomicSpecialSituationHappen.queryFamilyEconomicSpecialSituationHappenById(id);
		if(rih==null){
			return ArnoldUtils.NOT_FIND_ERRO;
		}
		try {
			rih.deleteFamilyEconomicSpecialSituationHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:根据村id查询家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public FamilyEconomicSpecialSituationHappen queryFamilyEconomySpecialSituationById(String id){
		FamilyEconomicSpecialSituationHappen rih=FamilyEconomicSpecialSituationHappen.queryFamilyEconomicSpecialSituationHappenById(id);
		return rih;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<FamilyEconomicSpecialSituationHappen>  pageFamilyEconomicSpecialSituationHappen(int pageNumber,int pageSize,
			String collectiveEconomyId,String familyId,String parentTypeId){
		return FamilyEconomicSpecialSituationHappen.pageFamilyEconomicSpecialSituationHappen(pageNumber, 
				pageSize,collectiveEconomyId, familyId,parentTypeId);
	}
	
}

