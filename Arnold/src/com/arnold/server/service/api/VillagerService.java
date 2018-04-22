package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.arnold.server.model.Villager;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class VillagerService extends BaseService {

	/**
	 * 增加贫困户时有部分数据是户主的信息，调用此接口
	 * @param medicalInsuranceId 
	 * @param insuranceId 
	 */
	public Map<String, Object> addHouseholder(String familyId, String name, String iDnumber, String phone,
			String raceId, String sexId, String educationalId, String healthConditionId, String maritalStatusId,
			String laborCapacityId, int isTrain, int isWorking, Date birthday, String insuranceId,
			String medicalInsuranceId,String soldiersDependentsId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Villager model = Villager.dao.getVillagerByIDNumber(iDnumber);

		if (model != null) {
			resultMap.put("id", model.getId());
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.MEMBER_EXIST_CODE);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_EXIST_CODE_STR);
			return resultMap;
		}

		Villager villagerModel = new Villager();
		villagerModel.setId(Utils.create36UUID());
		if(!Utils.isBlankOrEmpty(familyId)) villagerModel.setFamilyId(familyId);
		if(!Utils.isBlankOrEmpty(name)) villagerModel.setName(name);
		if(!Utils.isBlankOrEmpty(iDnumber)) villagerModel.setIDnumber(iDnumber);
		if(!Utils.isBlankOrEmpty(phone)) villagerModel.setPhone(phone);
		if(!Utils.isBlankOrEmpty(raceId)) villagerModel.setRaceId(raceId);
		if(!Utils.isBlankOrEmpty(sexId)) villagerModel.setSexId(sexId);
		if(!Utils.isBlankOrEmpty(educationalId)) villagerModel.setEducationalId(educationalId);
		if(!Utils.isBlankOrEmpty(healthConditionId)) villagerModel.setHealthConditionId(healthConditionId);
		if(!Utils.isBlankOrEmpty(maritalStatusId)) villagerModel.setMaritalStatusId(maritalStatusId);
		if(!Utils.isBlankOrEmpty(laborCapacityId)) villagerModel.setLaborCapacityId(laborCapacityId);
		if(isTrain != -1) villagerModel.setIsTrain(isTrain);
		if(isWorking != -1) villagerModel.setIsWorking(isWorking);
		
		if(birthday != null) {
			villagerModel.setBirthday(birthday);
		} else {
			birthday = ArnoldUtils.getBirthdayByIDnumber(iDnumber);
			villagerModel.setBirthday(birthday);
		}
		
		if(!Utils.isBlankOrEmpty(insuranceId)) villagerModel.setInsuranceId(insuranceId);//可能有多个，若有需要再建立关系表，这里暂不处理
		if(!Utils.isBlankOrEmpty(medicalInsuranceId)) villagerModel.setMedicalInsuranceId(medicalInsuranceId);//可能有多个，若有需要再建立关系表，这里暂不处理
		if(!Utils.isBlankOrEmpty(soldiersDependentsId)) villagerModel.setSoldiersDependentsId(soldiersDependentsId);//可能有多个，若有需要再建立关系表，这里暂不处理
		
		villagerModel.setCreateTime(new Date());
		
		villagerModel.save();

		resultMap.put("id", villagerModel.getId());
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		return resultMap;
	}

	public Map<String, Object> pageVillager(int pageNumber, int pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Villager villager = new Villager();
			Page<Villager> villagerModel = villager.pageVillagerByIds(pageNumber, pageSize, "");

			resultMap.put(ConstUtils.R_PAGE, villagerModel);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> pageVillagerByIds(int pageNumber, int pageSize, String ids) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Villager villager = new Villager();
			Page<Villager> villagerModel = villager.pageVillagerByIds(pageNumber, pageSize, ids);
			resultMap.put(ConstUtils.R_PAGE, villagerModel);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;

	}

	/**
	 * 
	 * @Description: 更新村名状态
	 * @author Li Bangming;
	 * @date 2017年8月19日 上午6:50:47
	 * @param villagerId
	 * @param isWorking
	 * @return
	 */
	public Map<String, Object> updateVillagerIsWorking(String villagerId, int isWorking, Map<String, Object> paramMap) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Villager villagerModel = Villager.dao.findById(villagerId).set("isWorking", isWorking);

			if (villagerModel == null) {
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			if (paramMap != null && !paramMap.isEmpty()) {
				for (Entry<String, Object> e : paramMap.entrySet()) {
					villagerModel.set(e.getKey(), e.getValue());
				}
			}
			villagerModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> updateVillagerPhoneAndIsWork(String villagerId, int isWorking, String phone,
			Map<String, Object> paramMap) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Villager villagerModel = Villager.dao.findById(villagerId).set("isWorking", isWorking).set("phone", phone);
			

			if (villagerModel == null) {
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			if (paramMap != null && !paramMap.isEmpty()) {
				for (Entry<String, Object> e : paramMap.entrySet()) {
					villagerModel.set(e.getKey(), e.getValue());
				}
			}
			villagerModel.update();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> updateVillager(String villagerId, String familyId, String name, String sexId,
			String iDnumber, Date birthday, String raceId, String relationOfHouseholderId, String schoolStatusId,
			String educationalId, String maritalStatusId, String healthConditionId, String laborStatusId,
			String laborTime, String laborCapacityId, String soldiersDependentsId, String insurance, String inOutStatusId,
			int isWishEmployment, String expectProfessionId, String expectWorkplaceId, int isWishTrain,
			String expectTrainProfessionId, Date expectTrainTime, String remark, int isWorking, int isTrain,
			String operator, String phone, String medicalInsuranceId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Villager villager = Villager.dao.findById(villagerId);

			if (villager == null) {
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			
			//判断非VillagerId用户是否使用过身份证号iDnumber
			if(Villager.dao.isExistIDNumberOfOther(iDnumber, villagerId)){
				resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.MEMBER_EXIST_CODE);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_EXIST_CODE_STR);
				return resultMap;
			}

			if (!Utils.isBlankOrEmpty(familyId))
				villager.setFamilyId(familyId);
			if (!Utils.isBlankOrEmpty(name))
				villager.setName(name);
			if (!Utils.isBlankOrEmpty(sexId))
				villager.setSexId(sexId);
			if (!Utils.isBlankOrEmpty(iDnumber))
				villager.setIDnumber(iDnumber);
			if (birthday != null)
				villager.setBirthday(birthday);
			if (!Utils.isBlankOrEmpty(raceId))
				villager.setRaceId(raceId);
			if (!Utils.isBlankOrEmpty(relationOfHouseholderId))
				villager.setRelationOfHouseholderId(relationOfHouseholderId);
			if (!Utils.isBlankOrEmpty(schoolStatusId))
				villager.setSchoolStatusId(schoolStatusId);
			if (!Utils.isBlankOrEmpty(educationalId))
				villager.setEducationalId(educationalId);
			if (!Utils.isBlankOrEmpty(maritalStatusId))
				villager.setMaritalStatusId(maritalStatusId);
			if (!Utils.isBlankOrEmpty(healthConditionId))
				villager.setHealthConditionId(healthConditionId);
			if (!Utils.isBlankOrEmpty(laborStatusId))
				villager.setLaborStatusId(laborStatusId);
			if (!Utils.isBlankOrEmpty(laborTime))
				villager.setLaborTimeId(laborTime);
			if (!Utils.isBlankOrEmpty(laborCapacityId))
				villager.setLaborCapacityId(laborCapacityId);
			if (!Utils.isBlankOrEmpty(soldiersDependentsId))
				villager.setSoldiersDependentsId(soldiersDependentsId);
			if (!Utils.isBlankOrEmpty(insurance))
				villager.setInsuranceId(insurance);
			if (!Utils.isBlankOrEmpty(inOutStatusId))
				villager.setInOutStatusId(inOutStatusId);
			if (isWishEmployment != -1)
				villager.setIsWishEmployment(isWishEmployment);
			if (!Utils.isBlankOrEmpty(expectProfessionId))
				villager.setExpectProfessionId(expectProfessionId);
			if (!Utils.isBlankOrEmpty(expectWorkplaceId))
				villager.setExpectWorkplaceId(expectWorkplaceId);
			if (isWishTrain != -1)
				villager.setIsWishTrain(isWishTrain);
			if (!Utils.isBlankOrEmpty(expectTrainProfessionId))
				villager.setExpectTrainProfessionId(expectTrainProfessionId);
			if (expectTrainTime != null)
				villager.setExpectTrainTime(expectTrainTime);
			if (!Utils.isBlankOrEmpty(remark))
				villager.setRemark(remark);
			if (isWorking != -1)
				villager.setIsWorking(isWorking);
			if (isTrain != -1)
				villager.setIsTrain(isTrain);
			if (!Utils.isBlankOrEmpty(operator))
				villager.setOperator(operator);

			if (!Utils.isBlankOrEmpty(phone))
				villager.setPhone(phone);
			
			if (!Utils.isBlankOrEmpty(medicalInsuranceId))
				villager.setMedicalInsuranceId(medicalInsuranceId);
			
			villager.setUpdateTime(new Date());

			villager.update();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> delVillager(String familymemberId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			Villager villagerModel = Villager.dao.findById(familymemberId);
			if (villagerModel == null) {
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}

			// TODO:逻辑删除,-1删除
			villagerModel.setIsValid(-1);
			villagerModel.update();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);

		}

		return resultMap;

	}

	public Villager queryValligerById(String id) {
		Villager villagerModel = Villager.dao.queryVillagerById(id);
		return villagerModel;

	}

	/**
	 * @Description: 根据身份证号
	 * @author Li Bangming;
	 * @date 2017年8月17日 上午10:49:22
	 * @param id
	 * @return
	 */
	public List<Villager> queryValligerByKeyWord(String keyWord) {

		return Villager.dao.getVillagerByKeyWord(keyWord);
	}

	public Map<String, Object> addFamilyVillager(String familyId, String name, String sexId, String iDnumber,
			Date birthday, String raceId, String relationOfHouseholderId, String schoolStatusId, String educationalId,
			String maritalStatusId, String healthConditionId, String laborStatusId, String laborTime,
			String laborCapacityId, String soldiersDependentsId, String insuranceId, String inOutStatusId,
			int isWishEmployment, String expectProfessionId, String expectWorkplaceId, int isWishTrain,
			String expectTrainProfessionId, Date expectTrainTime, String remark, int isWorking, int isTrain,
			String writer, String phone, String medicalInsuranceId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Villager villager = new Villager();
		villager.setId(Utils.create36UUID());
		if(!Utils.isBlankOrEmpty(familyId)) villager.setFamilyId(familyId);
		if(!Utils.isBlankOrEmpty(name)) villager.setName(name);
		if(!Utils.isBlankOrEmpty(sexId)) villager.setSexId(sexId);
		if(!Utils.isBlankOrEmpty(iDnumber)) villager.setIDnumber(iDnumber);
		
		if(birthday != null) {
			villager.setBirthday(birthday);
		} else {
			birthday = ArnoldUtils.getBirthdayByIDnumber(iDnumber);
			villager.setBirthday(birthday);
		}
		
		if(!Utils.isBlankOrEmpty(raceId)) villager.setRaceId(raceId);
		if(!Utils.isBlankOrEmpty(relationOfHouseholderId)) villager.setRelationOfHouseholderId(relationOfHouseholderId);
		if(!Utils.isBlankOrEmpty(schoolStatusId)) villager.setSchoolStatusId(schoolStatusId);
		if(!Utils.isBlankOrEmpty(educationalId)) villager.setEducationalId(educationalId);
		if(!Utils.isBlankOrEmpty(maritalStatusId)) villager.setMaritalStatusId(maritalStatusId);
		if(!Utils.isBlankOrEmpty(healthConditionId)) villager.setHealthConditionId(healthConditionId);
		if(!Utils.isBlankOrEmpty(laborStatusId)) villager.setLaborStatusId(laborStatusId);
		if(laborTime != null) villager.setLaborTimeId(laborTime);
		if(!Utils.isBlankOrEmpty(laborCapacityId)) villager.setLaborCapacityId(laborCapacityId);
		if(!Utils.isBlankOrEmpty(soldiersDependentsId)) villager.setSoldiersDependentsId(soldiersDependentsId);
		if(!Utils.isBlankOrEmpty(insuranceId)) villager.setInsuranceId(insuranceId);
		if(!Utils.isBlankOrEmpty(inOutStatusId)) villager.setInOutStatusId(inOutStatusId);
		if (isWishEmployment != -1)
			villager.setIsWishEmployment(isWishEmployment);
		if(!Utils.isBlankOrEmpty(expectProfessionId)) villager.setExpectProfessionId(expectProfessionId);
		if(!Utils.isBlankOrEmpty(expectWorkplaceId)) villager.setExpectWorkplaceId(expectWorkplaceId);
		if (isWishTrain != -1)
			villager.setIsWishTrain(isWishTrain);
		if(!Utils.isBlankOrEmpty(expectTrainProfessionId)) villager.setExpectTrainProfessionId(expectTrainProfessionId);
		if(expectTrainTime != null) villager.setExpectTrainTime(expectTrainTime);
		if(!Utils.isBlankOrEmpty(remark)) villager.setRemark(remark);
		if (isWorking != -1)
			villager.setIsWorking(isWorking);
		if (isTrain != -1)
			villager.setIsTrain(isTrain);
		if(!Utils.isBlankOrEmpty(writer)) villager.setWriter(writer);

		villager.setCreateTime(new Date());

		if(!Utils.isBlankOrEmpty(phone)) villager.setPhone(phone);
		if(!Utils.isBlankOrEmpty(medicalInsuranceId)) villager.setMedicalInsuranceId(medicalInsuranceId);
		
		villager.save();

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @param soldiersDependentsId 
	 * @Author PanChangGui
	 * @Time 2017年10月3日 下午2:44:31
	 * @Description 更新户主
	 */
	public Map<String, Object> updateHouseholder(String familyId, String name, String iDnumber, String phone, String raceId,
			String sexId, String educationalId, String healthConditionId, String maritalStatusId,
			String laborCapacityId, int isTrain, int isWorking, Date birthday, String insuranceId,
			String medicalInsuranceId, String soldiersDependentsId, int isSeriousPatient) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();

		
		//先根据familyId得到户主		
//		Villager model = Villager.dao.getHouseholderByFamilyId(familyId);
		
		//不使用写死的relationOfHouseholderId处理，改成家庭表记录的villagerId查询
		Villager model = Villager.dao.findFamilyVillageInfoByFId(familyId);
		
		if(model == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.MEMBER_INEXISTENCE_CODE);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}
		
		if(!Utils.isBlankOrEmpty(name)) model.setName(name);
		if(!Utils.isBlankOrEmpty(iDnumber)) model.setIDnumber(iDnumber);
		if(!Utils.isBlankOrEmpty(phone)) model.setPhone(phone);
		if(!Utils.isBlankOrEmpty(raceId)) model.setRaceId(raceId);
		if(!Utils.isBlankOrEmpty(sexId)) model.setSexId(sexId);
		if(!Utils.isBlankOrEmpty(educationalId)) model.setEducationalId(educationalId);
		if(!Utils.isBlankOrEmpty(healthConditionId)) model.setHealthConditionId(healthConditionId);
		if(!Utils.isBlankOrEmpty(maritalStatusId)) model.setMaritalStatusId(maritalStatusId);
		if(!Utils.isBlankOrEmpty(laborCapacityId)) model.setLaborCapacityId(laborCapacityId);
		if(isTrain != -1) model.setIsTrain(isTrain);
		if(isWorking != -1) model.setIsWorking(isWorking);
		if(birthday != null) model.setBirthday(birthday);
		if(!Utils.isBlankOrEmpty(insuranceId)) model.setInsuranceId(insuranceId);//可能有多个，若有需要再建立关系表，这里暂不处理
		if(!Utils.isBlankOrEmpty(medicalInsuranceId)) model.setMedicalInsuranceId(medicalInsuranceId);//可能有多个，若有需要再建立关系表，这里暂不处理
		if(!Utils.isBlankOrEmpty(soldiersDependentsId)) model.setSoldiersDependentsId(soldiersDependentsId);//可能有多个，若有需要再建立关系表，这里暂不处理		
		
		if(isSeriousPatient != -1){//有无重病患者
			model.setIsSeriousPatient(isSeriousPatient);
		}
		
		model.update();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		return resultMap;
	}
	
	/**
	 * 依据成员Id获取成员信息
	 * 		贫困户详情 --> 家庭成员编辑查询
	 * @param villagerId
	 * @return
	 */
	public Map<String, Object> findVillagerInfoById(String villagerId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Villager villager = Villager.dao.findById(villagerId);
		
		if(null==villager || Utils.isBlankOrEmpty(villager.getId())) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}
		
		resultMap.put(ConstUtils.R_MODEL, villager);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

}
