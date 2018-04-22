package com.arnold.server.service.api;

import com.arnold.server.model.CollectiveEconomy;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;

/**
 * @Description: 集体经济Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class CollectiveEconomyService {
	/**
	 * @Description:保存返回集体经济id
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public String saveCollectiveEconomy(String regionId,String valligerId,String createUserId,String phone,String valligerName,
										Double totalPrice){
		CollectiveEconomy rih=CollectiveEconomy.queryCollectiveEconomyByRegionIdAndVID(regionId,valligerId);
		if(rih!=null){
			return rih.getId();
		}else{
			rih=new CollectiveEconomy();
		}
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setRegionId(regionId);
		rih.setValligerId(valligerId);
		rih.setValligerName(valligerName);
		rih.setTotalPrice(totalPrice);
		rih.setCreatorId(createUserId);
		rih.setCreateTime(new Date());
		rih.setPhone(phone);
		try {
			rih.saveCollectiveEconomy(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  String.valueOf(ConstUtils.DATA_OPERATE_ERROR);
		}
		return  rih.getId();
	}
	/**
	 * @Description:根据村id查询集体经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public CollectiveEconomy queryCollectiveEconomyByRegionId(String regionId){
		CollectiveEconomy rih=CollectiveEconomy.queryCollectiveEconomyByRegionId(regionId);
		return rih;
	}
	/**
	 * @Description:根据村id或者集体经济id查询集体经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public CollectiveEconomy queryCollectiveEconomyByRegionIdOrCollectiveEconomyId(String regionId,String collectiveEconomyId){
		CollectiveEconomy collectiveEconomy=null;
		if(!Utils.isBlankOrEmpty(collectiveEconomyId)){
			collectiveEconomy=queryCollectiveEconomyById(collectiveEconomyId);
		}
		if(collectiveEconomy==null&&!Utils.isBlankOrEmpty(regionId)){
			collectiveEconomy=queryCollectiveEconomyByRegionId(regionId);
		}
		return collectiveEconomy;
	}
	/**
	 * @Description:根据村id查询集体经济
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public CollectiveEconomy queryCollectiveEconomyById(String id){
		CollectiveEconomy rih=CollectiveEconomy.queryCollectiveEconomyById(id);
		return rih;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<Record>  pageCollectiveEconomy(int pageNumber, int pageSize, String keyWord,String rName){
		return CollectiveEconomy.pageCollectiveEconomy(pageNumber, pageSize,  keyWord,rName);
	}
	
}

