package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.CollectiveEconomy;
import com.arnold.server.model.FamilyEconomic;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 家庭经济Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class FamilyEconomicService {
	/**
	 * @Description:保存返回家庭经济id
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public String saveFamilyEconomic(String familyId,String createUserId){
		FamilyEconomic rih=FamilyEconomic.queryFamilyEconomicByFamilyId(familyId);
		if(rih!=null){
			return rih.getId();
		}else{
			rih=new FamilyEconomic();
		}
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setFamilyId(familyId);
		rih.setCreatorId(createUserId);
		rih.setCreateTime(new Date());
		try {
			rih.saveFamilyEconomic(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  String.valueOf(ConstUtils.DATA_OPERATE_ERROR);
		}
		return  rih.getId();
	}
	/**
	 * @Description:根据村id查询家庭经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public FamilyEconomic queryFamilyEconomicByFamilyId(String familyId){
		FamilyEconomic rih=FamilyEconomic.queryFamilyEconomicByFamilyId(familyId);
		return rih;
	}
	/**
	 * @Description:根据村id查询家庭经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public FamilyEconomic queryFamilyEconomicById(String id){
		FamilyEconomic rih=FamilyEconomic.queryFamilyEconomicById(id);
		return rih;
	}
	/**
	 * @Description:根据村id或者集体经济id查询集体经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public FamilyEconomic queryFamilyEconomyByFamilyIdOrFamilyEconomyId(String familyId,String familyEconomyId){
		FamilyEconomic familyEconomy=null;
		if(!Utils.isBlankOrEmpty(familyEconomyId)){
			familyEconomy=queryFamilyEconomicById(familyEconomyId);
		}
		if(familyEconomy==null&&!Utils.isBlankOrEmpty(familyId)){
			familyEconomy=queryFamilyEconomicByFamilyId(familyId);
		}
		return familyEconomy;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<FamilyEconomic>  pageFamilyEconomic(int pageNumber,int pageSize,String regionId ,String keyWord){
		return FamilyEconomic.pageFamilyEconomic(pageNumber, pageSize, regionId ,  keyWord);
	}


	public Page<FamilyEconomic>  pageFamilyEconomicByHam(int page,int pageSize,
			String hamletName,String groupName,String keyWord){
		return FamilyEconomic.pageFamilyEconomicByHam(page, pageSize, hamletName ,groupName,  keyWord);
	}
}

