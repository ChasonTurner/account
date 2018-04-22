package com.arnold.server.service.api;

import java.util.Date;

import com.arnold.server.model.LocateVillageHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 驻村点流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月18日 下午8:26:45
 */
public class LocateVillageHappenService {
	/**
	 * @Description:保存驻村点流水
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int saveLocateVillageHappen(String locateVillageId,int peopleNum,int partyNum,String povertyOccurrence,int infirmNum
			,int povertyNum,String yearPlan,String dangerousHouse,String tapWater,String roadHarde,String treatedPersonnel
			,String groupRoad,String havePrimarySchool,int outSchoolChildrenNum,String lastYearAverage,String collectiveEconomyScale,
			String perArableLand,String perLand
			,String mainProblems,String userId){
		LocateVillageHappen rih=new LocateVillageHappen();
		rih.setId(Utils.create36UUID());
		rih.setLocateVillageId(locateVillageId);
		rih.setPeopleNum(peopleNum);
		rih.setPartyNum(partyNum);
		rih.setPovertyOccurrence(povertyOccurrence);
		rih.setInfirmNum(infirmNum);
		rih.setPovertyNum(povertyNum);
		rih.setYearPlan(yearPlan);
		rih.setDangerousHouse(dangerousHouse);
		rih.setTapWater(tapWater);
		rih.setRoadHarde(roadHarde);
		rih.setTreatedPersonnel(treatedPersonnel);
		rih.setGroupRoad(groupRoad);
		rih.setHavePrimarySchool(havePrimarySchool);
		rih.setOutSchoolChildrenNum(outSchoolChildrenNum);
		rih.setLastYearAverage(lastYearAverage);
		rih.setCollectiveEconomyScale(collectiveEconomyScale);
		rih.setPerArableLand(perArableLand);
		rih.setPerLand(perLand);
		rih.setMainProblems(mainProblems);
		rih.setOperatorId(userId);
		rih.setOperateTime(new Date());
		try {
			rih.saveLocateVillageHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return  ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:删除
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public int deleteLocateVillageHappen(String id){
		LocateVillageHappen rih=queryLocateVillageHappenById(id);
		if(rih==null){
			return ArnoldUtils.NOT_FIND_ERRO;
		}
		try {
			rih.deleteLocateVillageHappen(rih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return  ConstUtils.DATA_OPERATE_ERROR;
		}
		return  ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:根据村id查询驻村点流水
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public LocateVillageHappen queryLocateVillageHappenById(String id){
		LocateVillageHappen rih=LocateVillageHappen.queryLocateVillageHappenById(id);
		return rih;
	}
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午2:39:39
	 * @return
	 */
	public Page<LocateVillageHappen>  pageLocateVillageHappen(int pageNumber,int pageSize,String locateVillageId ){
		return LocateVillageHappen.pageLocateVillageHappen(pageNumber, pageSize, locateVillageId );
	}
	
}

