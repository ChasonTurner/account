package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.FamilyEconomicHerds;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 家庭经济存栏量信息Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class FamilyEconomicHerdsService {
	/**
	 * @Description:保存家庭经济存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int saveFamilyEconomicHerds(String familyEconomicId,String familyId,
			String typeId,Integer amount,Integer pupsAmount,Integer adultsAmount,Integer malesAmount,
			String userId,String remark){
		FamilyEconomicHerds rih=new FamilyEconomicHerds();
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setFamilyEconomicId(familyEconomicId);
		rih.setFamilyId(familyId);
		rih.setTypeId(typeId);
		rih.setAmount(amount);
		rih.setPupsAmount(pupsAmount);
		rih.setAdultsAmount(adultsAmount);
		rih.setMalesAmount(malesAmount);
		Date operatorTime=new Date();
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		rih.setRemark(remark);
		try {
			rih.saveFamilyEconomicHerds(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:更新家庭经济存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int updateFamilyEconomicHerds(String id,
			String typeId,Integer amount,Integer pupsAmount,Integer adultsAmount,Integer malesAmount,
			String userId,String remark){
		FamilyEconomicHerds rih = null;
		if (id == null || id.trim().length() == 0) {
			rih = new FamilyEconomicHerds();
			rih.setId(Utils.create36UUID());
		}else{
			rih=FamilyEconomicHerds.queryFamilyEconomicHerdsById(id);
		}
		if(rih==null){
			return ArnoldUtils.NOT_FIND_ERRO;
		}
		if(rih.getTypeId()==null||(rih.getTypeId()!=null&&!rih.getTypeId().equals(typeId))){
			rih.setTypeId(typeId);
		}
		if(amount != null){
			Integer pup = rih.getInt("amount");
			pup = pup == null?0:pup;
			if(amount != null){
				rih.setPupsAmount(pup + amount);
			}
		}
		if(pupsAmount != null){
			Integer pup = rih.getInt("pupsAmount");
			pup = pup == null?0:pup;
			if(pupsAmount != null){
				rih.setPupsAmount(pup + pupsAmount);
			}
		}
		
		if(adultsAmount != null){
			Integer pup = rih.getInt("adultsAmount");
			pup = pup == null?0:pup;
			if(adultsAmount != null){
				rih.setAdultsAmount(pup + adultsAmount);
			}
		}
		if(malesAmount != null){
			Integer pup = rih.getInt("malesAmount");
			pup = pup == null?0:pup;
			if(malesAmount != null){
				rih.setMalesAmount(pup + pupsAmount);
			}
		}
		
		Date operatorTime=new Date();
		rih.setOperatorId(userId);
		rih.setOperatorTime(operatorTime);
		rih.setRemark(remark);
		try {
			rih.updateFamilyEconomicHerds(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:根据村id查询家庭经济存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public FamilyEconomicHerds queryFamilyEconomyHerdsById(String id){
		FamilyEconomicHerds rih=FamilyEconomicHerds.queryFamilyEconomicHerdsById(id);
		return rih;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<FamilyEconomicHerds>  pageFamilyEconomicHerds(int pageNumber,int pageSize,String collectiveEconomyId,String familyId){
		return FamilyEconomicHerds.pageFamilyEconomicHerds(pageNumber, pageSize,collectiveEconomyId, familyId);
	}
	
}

