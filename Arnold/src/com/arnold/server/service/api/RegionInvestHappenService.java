package com.arnold.server.service.api;

import java.util.Calendar;
import java.util.Date;

import com.arnold.server.model.RegionInvestHappen;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @Description: 村投资流水流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class RegionInvestHappenService {
	/**
	 * @Description:保存村投资流水流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int  addRegionInvestHappen(String projectId,String regionId,Date investDate,
			String company,double investPrice, String  userId){
	    Calendar cal =Calendar.getInstance();
	    cal.setTime(investDate);
		
		int year=cal.get(Calendar.MONTH)+1;
		int month=cal.get(Calendar.YEAR);
		if((RegionInvestHappen.findRegionInvestHappenByProjectIdAndRegionIdAndYearAndMonth(projectId, regionId, year, month,company))!=null){
			return ArnoldUtils.OPER_SAME_ERRO;
		}
		RegionInvestHappen  fsih=new RegionInvestHappen();

		String id=Utils.create36UUID();
		fsih.setId(id);
		fsih.setProjectId(projectId);
		fsih.setRegionId(regionId);
		fsih.setYear(year);
		fsih.setMonth(month);
		fsih.setInvestPrice(investPrice);
		fsih.setCompany(company);
		fsih.setWriter(userId);
		fsih.setCreateTime(new Date());
		fsih.setOperator(userId);
		fsih.setOperatorTime(new Date());
		try {
			fsih.saveRegionInvestHappen(fsih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	/**
	 * @Description:更新村投资流水流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int  updateRegionInvestHappen(String id,String projectId,String regionId,Date investDate,
			String company,double investPrice, String  userId){
		RegionInvestHappen  fsih=RegionInvestHappen.findRegionInvestHappenById(id);
		if(fsih==null){
			return ArnoldUtils.NOT_FIND_ERRO;
		}
	    Calendar cal =Calendar.getInstance();
	    cal.setTime(investDate);
		int year=cal.get(Calendar.MONTH)+1;
		int month=cal.get(Calendar.YEAR);
		if((fsih.getProjectId()==null||(fsih.getProjectId()!=null&&!fsih.getProjectId().equals(projectId)))
				||(fsih.getRegionId()==null||(fsih.getRegionId()!=null&&!fsih.getRegionId().equals(regionId)))
				||(fsih.getYear()!=year)
				||(fsih.getMonth()!=month)
				||(fsih.getCompany()==null||(fsih.getCompany()!=null&&!fsih.getCompany().equals(company)))
				){
			if((RegionInvestHappen.findRegionInvestHappenByProjectIdAndRegionIdAndYearAndMonth(projectId, regionId, year, month,company))!=null){
				return ArnoldUtils.OPER_SAME_ERRO;
			}
		}
		if(fsih.getProjectId()==null||(fsih.getProjectId()!=null&&!fsih.getProjectId().equals(projectId))){
			fsih.setProjectId(projectId);
		}
		if(fsih.getRegionId()==null||(fsih.getRegionId()!=null&&!fsih.getRegionId().equals(regionId))){
			fsih.setRegionId(regionId);
		}
		if(fsih.getYear()!=year){
			fsih.setYear(year);
		}
		if(fsih.getMonth()!=month){
			fsih.setMonth(month);
		}
		if(fsih.getCompany()==null||(fsih.getCompany()!=null&&!fsih.getCompany().equals(company))){
			fsih.setCompany(company);
		}

		if(fsih.getInvestPrice()!=investPrice){
			fsih.setInvestPrice(investPrice);
		}

		fsih.setOperator(userId);
		fsih.setOperatorTime(new Date());
		try {
			fsih.updateRegionInvestHappen(fsih);
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
	public Page<RegionInvestHappen>  pageRegionInvestHappen(int pageNumber,int pageSize,String projectId,String regionId){
		return RegionInvestHappen.pageRegionInvestHappen(pageNumber, pageSize, projectId,regionId);
	}
	
}

