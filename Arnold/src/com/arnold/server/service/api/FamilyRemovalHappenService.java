package com.arnold.server.service.api;

import java.util.Date;
import com.arnold.server.model.FamilyRemovalHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 贫困户搬迁流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class FamilyRemovalHappenService {
	/**
	 * @Description:保存贫困户搬迁流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int addFamilyRemovalHappen(String familyId,String removalTypeId,String toRegion,
			Date  removalTime){
		FamilyRemovalHappen  rih=new FamilyRemovalHappen();
		String id=Utils.create36UUID();
		rih.setId(id);
		rih.setFamilyId(familyId);
		rih.setStayInTime(removalTime);
		rih.setToRegion(toRegion);
		rih.setRemovalTypeId(removalTypeId);
		try {
			rih.saveFamilyRemovalHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:保存贫困户搬迁流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int updateFamilyRemovalHappen(String id,String familyId,String removalTypeId,String toRegionId,
			String outRegionId,String resettlementWayId,String difficult,Date  removalTime){
		
		FamilyRemovalHappen  rih= FamilyRemovalHappen.findFamilyRemovalHappenByFamilyId(familyId);
		if(rih==null){
//			return ArnoldUtils.NOT_FIND_ERRO;
			rih=new FamilyRemovalHappen();
			 id=Utils.create36UUID();
			rih.setId(id);
		}
		if(rih.getFamilyId()==null||(rih.getFamilyId()!=null&&!rih.getFamilyId().equals(familyId))){
			rih.setFamilyId(familyId);
		}
		if(rih.getRemovalTypeId()==null||(rih.getRemovalTypeId()!=null&&!rih.getRemovalTypeId().equals(removalTypeId))){
			rih.setRemovalTypeId(removalTypeId);
		}	
		if(rih.getToRegion()==null||(rih.getToRegion()!=null&&!rih.getToRegion().equals(toRegionId))){
			rih.setToRegion(toRegionId);
		}
		if(rih.getOutRegion()==null||(rih.getOutRegion()!=null&&!rih.getOutRegion().equals(outRegionId))){
			rih.setOutRegion(outRegionId);
		}
		if(rih.getResettlementWayId()==null||(rih.getResettlementWayId()!=null&&!rih.getResettlementWayId().equals(resettlementWayId))){
			rih.setOutRegion(resettlementWayId);
		}
		if(rih.getDifficult()==null||(rih.getDifficult()!=null&&!rih.getDifficult().equals(difficult))){
			rih.setOutRegion(difficult);
		}
		if(rih.getStayInTime()!=removalTime){
			rih.setStayInTime(removalTime);
		}
		try {
			rih.updateFamilyRemovalHappen(rih);
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
	public FamilyRemovalHappen  queryFamilyRemovalHappenById(String familyId){
		return FamilyRemovalHappen.findFamilyRemovalHappenByFamilyId(familyId);
	}
	
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Page<FamilyRemovalHappen>  pageFamilyRemovalHappen(int pageNumber,int pageSize,String regionId,String industryId,String keyWord){
		return FamilyRemovalHappen.pageFamilyRemovalHappen(pageNumber, pageSize, regionId, industryId, keyWord);
	}
	
}

