package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.model.Family;
import com.arnold.server.model.Region;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.AlphabetUtil;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class FamilyService extends BaseService {

	public Map<String, Object> addFamily(String number, String regionId,
			String aspirationId, int isSeriousPatient, int isDropout, int isOnlyChild, Date helpTime, Date planeTime,
			String writer, String addReason, String remark, int isMoveFamily, String ralationTypeId, String familyPropertyId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Family familyModel = new Family();
		familyModel.setId(Utils.create36UUID());//familyId
		if(!Utils.isBlankOrEmpty(number)&&Family.findFamilyByNumber(number)==null){
			familyModel.setNumber(number);
		}else{
			//采用自动生成的
			Region region=Region.dao.findById(regionId);
			if(region!=null){
				String numberPrefix=region.getParentName();
				//String numberPrefix=region.getParentName()+region.getShortName();
				numberPrefix= AlphabetUtil.getAllFirstLetter(numberPrefix).toUpperCase();
				String serialNumber=getSerialNumberByNumberPrefix(numberPrefix);
				familyModel.setNumber(numberPrefix+serialNumber);
			}
		}
		familyModel.setRegionId(regionId);
		familyModel.setTime(helpTime);
		familyModel.setOperatorTime(new Date());
		familyModel.setWriter(writer);
		familyModel.setCreateTime(new Date());
		familyModel.setPlaneTime(planeTime);
		familyModel.setIsMoveFamily(isMoveFamily);
		/*if(isSeriousPatient != -1){
			//model.setIs
			//表没字段
		}*/
		
		familyModel.setRalationTypeId(ralationTypeId);//贫困状态
		familyModel.setFamilyPropertyId(familyPropertyId);//贫困户属性
		
		familyModel.save();
		
		resultMap.put("id", familyModel.getId());//返回familyId
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		return resultMap;	
	}
	
	public Map<String, Object> updateHouseholder(String familyId, String householderId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Family model = Family.dao.findByFamilyId(familyId);
		if(model == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			return resultMap;
		}
		
		model.setVillagerId(householderId);	
		model.update();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		return resultMap;	
	}
	
	public Map<String, Object> findByFamilyId(String familyId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Family model = Family.dao.findByFamilyId(familyId);
		if(model == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			return resultMap;
		}
		resultMap.put(ConstUtils.R_MODEL, model);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		return resultMap;	
	}

	public Map<String, Object> pageFamily(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Family family = new Family();
			
			Page<Family> familyModel = family.pageFamilyByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, familyModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			
		}catch(Exception e){		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> updateFamily(String familyId, String number, String regionId,
			String ralationTypeId,
			String aspirationId,int isDropout, int isOnlyChild,int isMoveFamily,
			Date helpTime,Date planeTime,String addReason,
			String remark, String operator, String familyPropertyId, String phone){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Family model = Family.dao.findById(familyId);		
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
				return resultMap;
			}
			
			if(!Utils.isBlankOrEmpty(number)&&number.equals(model.getNumber())){
				if(Family.findFamilyByNumber(number)==null){
					model.setNumber(number);
				}
			}else if(Utils.isBlankOrEmpty(number)){
				//采用自动生成的
				Region region=Region.dao.findById(regionId);
				String numberPrefix=ArnoldUtils.EMPTY_STR,serialNumber=ArnoldUtils.EMPTY_STR;
				numberPrefix= AlphabetUtil.getAllFirstLetter(numberPrefix).toUpperCase();
				if(region!=null){
					 //numberPrefix=region.getParentName()+region.getShortName();
					numberPrefix=region.getParentName();
					serialNumber=getSerialNumberByNumberPrefix(numberPrefix);
					// 直接设置
					if (Utils.isBlankOrEmpty(model.getNumber())) {
						model.setNumber(numberPrefix + serialNumber);
					} else {
						String oldNumber = model.getNumber();
						String oldNumberPrefix = oldNumber.substring(0,
								oldNumber.length() - 4);
						if (!numberPrefix.equals(oldNumberPrefix)) {
							model.setNumber(numberPrefix + serialNumber);
						}
					}
				}
			}
			
			if(!Utils.isBlankOrEmpty(regionId)){
				model.setRegionId(regionId);
			}
			if(!Utils.isBlankOrEmpty(ralationTypeId)){
				model.setRalationTypeId(ralationTypeId);
			}		
			if(!Utils.isBlankOrEmpty(aspirationId)){
				model.setAspirationId(aspirationId);
			}
			
			if(isDropout != -1) {
				model.setIsDropout(isDropout);
			}
			if(isOnlyChild != -1) {
				model.setIsOnlyChild(isOnlyChild);
			}
			if(isMoveFamily != -1) {
				model.setIsMoveFamily(isMoveFamily);
			}
			
			if(helpTime != null){
				model.setTime(helpTime);
			}
			
			if(planeTime != null){
				model.setPlaneTime(planeTime);
			}
			
			if(!Utils.isBlankOrEmpty(addReason)){
				model.setAddReason(addReason);
			}
			
			if(!Utils.isBlankOrEmpty(remark)){
				model.setRemark(remark);
			}
			
			if(!Utils.isBlankOrEmpty(operator)){
				model.setOperator(operator);
			}
			
			if(!Utils.isBlankOrEmpty(familyPropertyId)){
				model.setFamilyPropertyId(familyPropertyId);
			}
			
			if(!Utils.isBlankOrEmpty(phone)){
				model.setPhone(phone);
			}
			
			model.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);			
		}
		
		return resultMap;		
	}
	
	public Map<String, Object> delFamily(String id){	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{		
			Family model = Family.dao.findById(id);		
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个家庭！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			model.setIsValid(-1);
			model.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");			
		}catch(Exception e){		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");		
		}
		
		return resultMap;		
	}
	
	/**
	 * @Description: 根据行政区域regionIds
	 * @author Li Bangming;
	 * @date 2017年8月19日 上午11:37:10
	 * @param regionIds
	 * @return
	 */
	public List<Family> queryFamilyByRegionIds(String regionIds){
		return Family.findByFamilyIdByRegionIds(regionIds);
	}
	
	public List<Family> findFamilyIdByRegionId(String regionId,String regionName){
		return Family.findFamilyIdByRegionId(regionId,regionName);
	}
	/**
	 * @Description: 获取最大编号的家庭信息
	 * @author Li Bangming;
	 * @date 2017年8月19日 上午11:31:41
	 * @return
	 */
	public  Family findFamilyByNumberPrefix(String numberPrefix) {
		return Family.findFamilyByNumberPrefix(numberPrefix);
	}
	
	/**
	 * @Description: 获取家庭编码序号
	 * @author Li Bangming;
	 * @date 2017年8月19日 上午11:31:41
	 * @return
	 */
	public String getSerialNumberByNumberPrefix(String numberPrefix) {
		Family maxFamily=findFamilyByNumberPrefix(numberPrefix);
		if(maxFamily==null){
			return "0001";
		}
		String numberStr=maxFamily.getNumber();
		numberStr=numberStr.substring(numberStr.length()- 4);
		Integer number =Integer.parseInt(numberStr);
		numberStr=ArnoldUtils.get4BitStrByNumber(number+1);
		return numberStr;
	}
	public List<Family> findAll(){
		return Family.findAllFamilys();	
	}

	/**
	 * @param familyPropertyId 
	 * @Author PanChangGui
	 * @Time 2017年9月28日 下午9:50:08
	 * @Description 
	 */
	public Map<String, Object> updateFamilyPovertyproperty(String familyId, String ralationTypeId, String familyPropertyId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Family model = Family.dao.findByFamilyId(familyId);
		if(model == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_INEXISTENCE_CODE_STR);	
			return resultMap;
		}
		
		if(!Utils.isBlankOrEmpty(ralationTypeId)) model.setRalationTypeId(ralationTypeId);
		if(!Utils.isBlankOrEmpty(familyPropertyId)) model.setFamilyPropertyId(familyPropertyId);
		model.update();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);	
		return resultMap;
	}

	/**
	 * @Author PanChangGui
	 * @Time 2017年10月1日 下午7:10:23
	 * @Description 
	 */
	public Map<String, Object> findFamilyRemoval(String familyId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		Family model = Family.dao.findFamilyRemoval(familyId);
		
		resultMap.put(ConstUtils.R_MODEL, model);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);	
		return resultMap;
	}

	/**
	 * @Author PanChangGui
	 * @Time 2017年10月2日 上午10:54:47
	 * @Description 
	 */
	public Map<String, Object> updateFamilyRemoval(String familyId, int isMoveFamily, String removalTypeId,
			String outRegion, String toRegion, String resettlementWayId, String difficult, Date stayInTime,
			int isStayIn, int isMoval) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		Family model = Family.dao.findFamilyRemoval(familyId);
		if(model == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_INEXISTENCE_CODE_STR);	
			return resultMap;
		}
		
		if(isMoveFamily != -1) model.setIsMoveFamily(isMoveFamily);
		if(!Utils.isBlankOrEmpty(removalTypeId)) model.setRemovalTypeId(removalTypeId);
		if(!Utils.isBlankOrEmpty(outRegion)) model.setOutRegion(outRegion);
		if(!Utils.isBlankOrEmpty(toRegion)) model.setToRegion(toRegion);
		if(!Utils.isBlankOrEmpty(resettlementWayId)) model.setResettlementWayId(resettlementWayId);
		if(!Utils.isBlankOrEmpty(difficult)) model.setDifficult(difficult);
		if(stayInTime != null) model.setStayInTime(stayInTime);
		if(isStayIn != -1) model.setIsStayIn(isStayIn);
		if(isMoval != -1) model.setIsMoval(isMoval);
		
		model.update();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
}
