package com.arnold.server.arnoldService;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.bean.request.RequestBackStageCrew;
import com.arnold.server.config.ConsConfig;
import com.arnold.server.model.Arnold;
import com.arnold.server.model.Backstagecrew;
import com.arnold.server.model.FamilyWarningHappen;
import com.arnold.server.model.Leader;
import com.arnold.server.model.Member;
import com.arnold.server.model.MemberCommendHappen;
import com.arnold.server.model.Region;
import com.arnold.server.model.StaticFamilyIncome;
import com.arnold.server.model.Villager;
import com.arnold.server.service.BaseService;
import com.arnold.server.service.api.AccreditedService;
import com.arnold.server.service.api.AreaService;
import com.arnold.server.service.api.BackstagecrewService;
import com.arnold.server.service.api.FamilyBurdenRelationService;
import com.arnold.server.service.api.FamilyLiveConditionService;
import com.arnold.server.service.api.FamilyMeasureRelationService;
import com.arnold.server.service.api.FamilyPovertyHappenService;
import com.arnold.server.service.api.FamilyRemovalHappenService;
import com.arnold.server.service.api.FamilyService;
import com.arnold.server.service.api.FamilySoldierDepentantRelationService;
import com.arnold.server.service.api.LeaderDepartmentRelationService;
import com.arnold.server.service.api.LeaderRegionRelationService;
import com.arnold.server.service.api.LeaderService;
import com.arnold.server.service.api.MemberCommendHappenService;
import com.arnold.server.service.api.MemberFamilyRelationService;
import com.arnold.server.service.api.MemberRegionRelationService;
import com.arnold.server.service.api.MemberService;
import com.arnold.server.service.api.PropertyVillageService;
import com.arnold.server.service.api.RegionInfoService;
import com.arnold.server.service.api.RegionService;
import com.arnold.server.service.api.VillagerService;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.arnold.server.util.QueryBaseUtil;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.MD5Util;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author ChangGui Pan
 * @time 2017年8月12日 上午9:36:23
 * @description TODO
 */
public class ArnoldService extends BaseService {
	MemberService memberService = new MemberService();
	AccreditedService accreditedService = new AccreditedService();
	AreaService areaService = new AreaService();
	MemberFamilyRelationService memberFamilyRelationService = new MemberFamilyRelationService();
	FamilyService familyService = new FamilyService();
	VillagerService villagerService = new VillagerService();
	LeaderService leaderService = new LeaderService();
	LeaderDepartmentRelationService leaderDepartmentRelationService = new LeaderDepartmentRelationService();
	LeaderRegionRelationService leaderRegionRelationService = new LeaderRegionRelationService();
	FamilyPovertyHappenService familyPovertyHappenService = new FamilyPovertyHappenService();
	FamilyLiveConditionService familyLiveConditionService = new FamilyLiveConditionService();
	MemberCommendHappenService memberCommendHappenService = new MemberCommendHappenService();
	FamilyRemovalHappenService familyRemovalHappenService = new FamilyRemovalHappenService();
	FamilyBurdenRelationService familyBurdenRelationService = new FamilyBurdenRelationService();
	FamilyMeasureRelationService familyMeasureRelationService = new FamilyMeasureRelationService();
	//FamilyInsuranceRelationService familyInsuranceRelationService = new FamilyInsuranceRelationService();
	FamilySoldierDepentantRelationService familySoldierDepentantRelationService = new FamilySoldierDepentantRelationService();
	BackstagecrewService backstageCrewService = new BackstagecrewService();
	RegionInfoService regionInfoService = new RegionInfoService();
	
	
	/*
	 * public Map<String, Object> addMember(String userName, String name, String
	 * sexId, String phone, Date birthday, String iDnumber, String
	 * politicalStatusId, String educationalId, int isValid, String raceId,
	 * String orgId, String postId, String groupId, String password, String
	 * appIdP, String roleOfGroupId, String writer, String operator){
	 * 
	 * 
	 * Map<String, Object> map = memberService.addMember(userName, name, sexId,
	 * phone, birthday, iDnumber, politicalStatusId, educationalId, isValid,
	 * raceId, orgId, postId, groupId, password, appIdP, roleOfGroupId, writer,
	 * operator);
	 * 
	 * return map; }
	 */

	public Map<String, Object> addMember(String memberId, String name, String sexId, String phone, Date birthday,
			String iDnumber, String politicalStatusId, String educationalId, String raceId, String orgId, String post,
			String groupId, String roleOfGroupId, String writer, String accreditDepartmentId,
			String orgPost, String accreditPlaceId, String accreditPostId, Date accreditStart, Date accreditEnd,
			String remark) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = memberService.addMember(memberId, name, sexId, phone, birthday, iDnumber,
				politicalStatusId, educationalId, raceId, orgId, post, groupId, roleOfGroupId, writer,
				accreditDepartmentId,orgPost,accreditPlaceId,accreditPostId,accreditStart, accreditEnd, remark);
		/*
		 * if(!map.get("rc").equals((int)0)) { return map; }
		 */

		/*accreditedService.addAccredit(memberId, accreditDepartmentId, orgPost, accreditPlaceId, accreditPostId,
				accreditStart, accreditEnd, remark);*/
		int rc=(Integer) resultMap.get("rc");
		if(rc!=0){
			return resultMap;
		}
		if(!ArnoldUtils.isMobileNO(phone)){
			return resultMap;
		}
		Member member=memberService.findByPhone(phone);

		BackstagecrewService backstageCrewService=new BackstagecrewService();
		String userName = null, userId = null, pwd = null, realName = null;int sex=0;
		if(member!=null){
			userName=member.getPhone();
			pwd=userName.substring(userName.length()-6);
			pwd=MD5Util.string2MD5(pwd);
			realName=member.getName();
			
			Map<String, Object> operMap=backstageCrewService.saveUserToPoseidon(userName, pwd, realName, sex, ConsConfig.APP_ID);
			int  rcA=(Integer) operMap.get("rc");
			if(rcA!=0){
				return resultMap;
			}
			userId=(String) resultMap.get("userId");
			resultMap=backstageCrewService.addBackstageCrew(userId, userName, member.getName(), member.getSexId(), phone,
						member.getRoleOfGroupId(), member.getOrgId(), null, null);
		}
		return resultMap;
	}
	
	public Map<String, Object> pageMember(Integer pageNumber, Integer pageSize,
			QueryBaseUtil queryBaseUtil) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		/*try {
			Page<Record> arnoldModel = Arnold.dao.pageMember(pageNumber, pageSize,
					accreditPostId,accreditPlace,organizationId,post,groupId,
					accreditStart,accreditEnd,birthday,politicalStatusId);
			resultMap.put(ConstUtils.R_PAGE, arnoldModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}*/
		
		MemberService memberService = new  MemberService();
		resultMap = memberService.pageMember(pageNumber, pageSize,queryBaseUtil);
		
		return resultMap;
	}

	public Map<String, Object> findMember(String memberId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();

		/*try {
			Record arnoldModel = Arnold.dao.findMember(memberId);
			resultMap.put(ConstUtils.R_MODEL, arnoldModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}*/
		
		MemberService memberService = new  MemberService();
		resultMap = memberService.findMember(memberId);
		
		return resultMap;
	}

	public Map<String, Object> pageArea(Integer pageNumber, Integer pageSize) {
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		resultMap = areaService.pageArea(pageNumber, pageSize);
		return resultMap;
	}

	public Map<String, Object> delMember(String memberId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap = memberService.delMember(memberId);
		//resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		//resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageMemberFamily(String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			Page<Record> page = Arnold.dao.pageMemberFamily(memberId, pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, page);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return resultMap;
		}

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	/**
	 * @Author PanChangGui
	 * @Time 2017年10月7日 上午10:43:32
	 * @Description 扶贫人员所帮扶的家庭，由关系表改成流水表
	 */
	public Map<String, Object> pageMemberFamilyHappen(String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			Page<Record> page = Arnold.dao.pageMemberFamilyHappen(memberId, pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	/**
	 * @param familyPropertyId 
	 * @Author PanChangGui
	 * @Time 2017年10月3日 下午2:33:51
	 * @Description update
	 */
	public Map<String, Object> addFamily(String number, String name, String iDnumber,
			String administrativeRegionId, String aspirationId, String phone, String raceId, String sexId,
			String educationalId, String healthConditionId, String maritalStatusId, String ralationTypeId,
			String measureId, String burdenId, int isSeriousPatient, String laborCapacityId, int isDropout,
			int isOnlyChild, String soldiersDependentsId, String insuranceId, Date helpTime, Date planeTime, String writer,
			String addReason, String remark, int isTrain, int isWorking, int isMoveFamily, Date birthday,
			String medicalInsuranceId, String familyPropertyId) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String familyId = null;
		String householderId = null;
		
		if(Villager.dao.isExistIDNumberOfOther(iDnumber, null)){
			//已经使用身份证号新增过家庭
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "身份证号已使用过,新增家庭失败");
			return resultMap;
		}
		
		Map<String, Object> familyReturnMap = familyService.addFamily(number,administrativeRegionId,aspirationId,
				isSeriousPatient,isDropout,isOnlyChild,
				helpTime,planeTime,writer,addReason,remark,isMoveFamily,ralationTypeId,familyPropertyId);
		
		familyId = (String) familyReturnMap.get("id");//得到familyId
		
		Map<String, Object> villagerReturnMap = villagerService.addHouseholder(familyId,name,iDnumber,phone,raceId,sexId,educationalId,healthConditionId,
				maritalStatusId,laborCapacityId,isTrain,isWorking,birthday, insuranceId, medicalInsuranceId,soldiersDependentsId);	
		
		householderId = (String) villagerReturnMap.get("id");//得到householderId
		
		familyService.updateHouseholder(familyId, householderId);//设置family的householder	
		
		//insuranceId保险关系表
		//measureId措施关系表
		//povertyStatusId贫困属性关系表
		//burdenId贫困原因关系表
		//soldiersDependentsId军属关系表
		familyBurdenRelationService.addFamilyBurdenRelation(familyId, burdenId);
		familyMeasureRelationService.addFamilyMeasureRelation(familyId, measureId);
		
		
		//familyInsuranceRelationService.addFamilyInsuranceRelation(familyId, insuranceId);//养老保险应该是针对个人的
		//familySoldierDepentantRelationService.addFamilySoldierDepententsRelation(familyId, soldierDependentsId);//军属关系应该是针对个人的
		
		//贫困户挂厅帮责任人的来源不是来自这里，所以不处理
		//贫困户和帮扶责任人的关系
		//MemberFamilyRelationService mfrService = new MemberFamilyRelationService();
		//mfrService.addMemberFamilyRelation(memberId, familyId);
		
		//新增贫困属性流水
		familyPovertyHappenService.addFamilyPovertyHappen(familyId, ralationTypeId, null, null, null,familyPropertyId, null);
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> updateFamily(String familyId, String number, String name, String iDnumber, String administrativeRegionId,
			String aspirationId, String phone, String raceId, String sexId, String educationalId,
			String healthConditionId, String maritalStatusId, String ralationTypeId, String measureId, String burdenId,
			int isSeriousPatient, String laborCapacityId, int isDropout, int isOnlyChild, String soldiersDependentsId,
			String insuranceId, Date helpTime, Date planeTime, String operator, String addReason, String remark, int isTrain,
			int isWorking, int isMoveFamily, Date birthday, String medicalInsuranceId, String familyPropertyId) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		familyService.updateFamily(familyId,number,administrativeRegionId,ralationTypeId,aspirationId,
				isDropout,isOnlyChild,isMoveFamily,
				helpTime,planeTime,addReason,remark,operator,familyPropertyId,phone);
		
		villagerService.updateHouseholder(familyId,name,iDnumber,phone,raceId,sexId,educationalId,healthConditionId,
				maritalStatusId,laborCapacityId,isTrain,isWorking,birthday, insuranceId, medicalInsuranceId,soldiersDependentsId
				,isSeriousPatient);
		
		//可能有多个
		//insuranceId保险关系表
		//measureId措施关系表
		//povertyStatusId贫困属性关系表
		//burdenId贫困原因关系表
		//soldiersDependentsId军属关系表
		
		familyBurdenRelationService.addFamilyBurdenRelation(familyId, burdenId);
		familyMeasureRelationService.addFamilyMeasureRelation(familyId, measureId);
		
		//更新贫困属性流水
		//更新操作不处理贫困属性
		//familyPovertyHappenService.addFamilyPovertyHappen(familyId, ralationTypeId, null, null, null,familyPropertyId, null);
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageFamily(Integer pageNumber, Integer pageSize,Map<String, Object> searchMaps) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			Page<Record> page = Arnold.dao.pageFamily(pageNumber, pageSize,searchMaps);
			List<Record> dataList = page.getList();
			if(null!=dataList && dataList.size()>0){
				//补充家庭年收入
				List<Record> newData = new ArrayList<Record>();
				for(Record re : dataList){
					if(!Utils.isBlankOrEmpty(re.getStr("id"))){
						double annualIncome = Arnold.dao.selectFamilyInComeByFId(re.getStr("id"));
						re.set("annualIncome", annualIncome);
					}else{
						re.set("annualIncome", 0d);
					}
					newData.add(re);
				}
				resultMap.put(ConstUtils.R_PAGE, new Page<Record>(newData, pageNumber, 
						pageSize, page.getTotalPage(), page.getTotalRow()));
			}else{
				resultMap.put(ConstUtils.R_PAGE, page);
			}
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> findFamily(String familyId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Record model = Arnold.dao.findFamily(familyId);
		if(model==null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}
		
		resultMap.put(ConstUtils.R_MODEL, model);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageFamilymember(Integer pageNumber, Integer pageSize, String familyId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Page<Record> page = Arnold.dao.pageFamilymember(pageNumber, pageSize, familyId);
		if(page==null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}
		
		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> delFamilymember(String familymemberId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		villagerService.delVillager(familymemberId);

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageLeader(Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Page<Record> page = Arnold.dao.pageLeader(pageNumber, pageSize);

		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> updateMember(String memberId, String name, String sexId, String phone, Date birthday, String iDnumber,
			String politicalStatusId, String educationalId, String raceId, String orgId, String post, String groupId,
			String roleOfGroupId, String operator, String accreditDepartmentId, String orgPost,
			String accreditPlaceId, String accreditPostId, Date accreditStart, Date accreditEnd, String remark) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Member member=memberService.findById(memberId);

		resultMap = memberService.updateMember(memberId, name, sexId, phone, birthday, iDnumber, politicalStatusId, educationalId,
				raceId, orgId, post, groupId, roleOfGroupId, operator,
				accreditDepartmentId,orgPost,accreditPlaceId,accreditPostId,accreditStart, accreditEnd, remark);

		//accreditedService.updateAccredit(memberId, accreditDepartmentId,orgPost,accreditPlaceId,accreditPostId,accreditStart,accreditEnd,remark);
		int rc=(Integer) resultMap.get("rc");
		if(rc!=0){
			return resultMap;
		}
		if(!ArnoldUtils.isMobileNO(phone)){
			return resultMap;
		}
		BackstagecrewService backstageCrewService=new BackstagecrewService();
		String userName = null, userId = null, pwd = null, realName = null;int sex=0;
		if(member!=null){
			if(member.getPhone()!=null&&member.getPhone().equals(phone)){
				return resultMap;
			}
			userName=member.getPhone();
			pwd=userName.substring(userName.length()-6);
			pwd=MD5Util.string2MD5(pwd);
			realName=member.getName();
			
			Map<String, Object> operMap=backstageCrewService.saveUserToPoseidon(userName, pwd, realName, sex, ConsConfig.APP_ID);
			int  rcA=(Integer) operMap.get("rc");
			if(rcA!=0){
				return resultMap;
			}
			userId=(String) resultMap.get("userId");
			resultMap=backstageCrewService.addBackstageCrew(userId, userName, member.getName(), member.getSexId(), phone,
						member.getRoleOfGroupId(), member.getOrgId(), null, null);
		}
		
		return resultMap;
	}

	public Map<String, Object> addLeader(String leaderId, String name, Date birthday, String sexId,
			String politicalStatusId, String educationalId, String raceId, String levelId, String phone,
			String iDnumber, String orgId, String remark, String writer, String helperTypeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap = leaderService.addLeader(leaderId, name, sexId, birthday, politicalStatusId, educationalId, raceId, levelId,
				phone,iDnumber,orgId,remark,writer,helperTypeId);
		//leaderDepartmentRelationService.addLeaderDepartmentRelation(leaderId, departmentId, subjectId);
		//leaderRelationService.addLeaderRelation(leaderId, regionId, groupId, memberId);
		int rc=(Integer) resultMap.get("rc");
		if(rc!=0){
			return resultMap;
		}
//		if(!ArnoldUtils.isMobileNO(phone)){
//			return resultMap;
//		}
//		Leader leader=leaderService.findByPhone(phone);
//		BackstagecrewService backstageCrewService=new BackstagecrewService();
//		String userName = null, userId = null, pwd = null, realName = null;int sex=0;
//		if(leader!=null){
//			userName=leader.getPhone();
//			pwd=userName.substring(userName.length()-6);
//			pwd=MD5Util.string2MD5(pwd);
//			realName=leader.getName();
//			
//			Map<String, Object> operMap=backstageCrewService.saveUserToPoseidon(userName, pwd, realName, sex, ConsConfig.APP_ID);
//			int  rcA=(Integer) operMap.get("rc");
//			if(rcA!=0){
//				return resultMap;
//			}
//			userId=(String) resultMap.get("userId");
//			resultMap=backstageCrewService.addBackstageCrew(userId, userName, leader.getName(), leader.getSexId(), phone,
//					leader.getRaceId(), leader.getOrgId(), null, null);
//		}
		
		return resultMap;
	}

	public Map<String, Object> updateLeader(String leaderId, String name, Date birthday, String sexId,
			String politicalStatusId, String educationalId, String raceId, String levelId, String phone, String orgId,
			String iDnumber, String remark, String operator) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		Leader leader=leaderService.findById(leaderId);

		resultMap = leaderService.updateLeader(leaderId,name,birthday,sexId,politicalStatusId,educationalId,
				raceId,levelId,phone,orgId,iDnumber,remark,operator);
		
		// leaderDepartmentRelationService.updateLeaderDepartmentRelation(leaderId,
		// departmentId, subjectId);
		//leaderRelationService.addLeaderRelation(leaderId, regionId, groupId, memberId);
//		int rc=(Integer) resultMap.get("rc");
//		if(rc!=0){
//			return resultMap;
//		}
//		if(!ArnoldUtils.isMobileNO(phone)){
//			return resultMap;
//		}
//		BackstagecrewService backstageCrewService=new BackstagecrewService();
//		String userName = null, userId = null, pwd = null, realName = null;int sex=0;
//		if(leader!=null){
//			if(leader.getPhone()!=null&&leader.getPhone().equals(phone)){
//				return resultMap;
//
//			}
//			userName=leader.getPhone();
//			pwd=userName.substring(userName.length()-6);
//			pwd=MD5Util.string2MD5(pwd);
//			realName=leader.getName();
//			
//			Map<String, Object> operMap=backstageCrewService.saveUserToPoseidon(userName, pwd, realName, sex, ConsConfig.APP_ID);
//			int  rcA=(Integer) operMap.get("rc");
//			if(rcA!=0){
//				return resultMap;
//			}
//			userId=(String) resultMap.get("userId");
//			resultMap=backstageCrewService.addBackstageCrew(userId, userName, leader.getName(), leader.getSexId(), phone,
//					leader.getRaceId(), leader.getOrgId(), null, null);
//		}
		
		return resultMap;
	}

	public Map<String, Object> addFamilyVillager(String familyId, String name, String sexId, String iDnumber,
			Date birthday, String raceId, String relationOfFamilyId, String schoolStatusId, String educationalId,
			String maritalStatusId, String healthConditionId, String laborId, String laborTime, String laborCapacityId,
			String soldiersDependentsId, String insuranceId, String inOutStatusId, int isWishEmployment,
			String expectProfessionId, String expectWorkplaceId, int isWishTrain, String expectTrainProfessionId,
			Date expectTrainTime, String remark, int isWorking, int isTrain, String writer, String phone, String medicalInsuranceId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//Map<String,Object> dataMap;
				
		villagerService.addFamilyVillager(familyId,name,sexId,iDnumber,birthday,raceId,relationOfFamilyId,
				schoolStatusId,educationalId,maritalStatusId,healthConditionId,laborId,laborTime,laborCapacityId,
				soldiersDependentsId,insuranceId,inOutStatusId,isWishEmployment,expectProfessionId,expectWorkplaceId,
				isWishTrain,expectTrainProfessionId,expectTrainTime,remark,isWorking,isTrain,writer,phone,medicalInsuranceId);
		
		//String villagerId = (String)dataMap.get("id");
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> updateFamilyMember(String villagerId,String familyId, String name, String sexId, String iDnumber,
			Date birthday, String raceId, String relationOfFamilyId, String schoolStatusId, String educationalId,
			String maritalStatusId, String healthConditionId, String laborId, String laborTime, String laborCapacityId,
			String soldiersDependentsId, String insuranceId, String inOutStatusId, int isWishEmployment,
			String expectProfessionId, String expectWorkplaceId, int isWishTrain, String expectTrainProfessionId,
			Date expectTrainTime, String remark, int isWorking, int isTrain, String operator, String phone, String medicalInsuranceId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap = villagerService.updateVillager(villagerId,familyId,name,sexId,iDnumber,birthday,raceId,relationOfFamilyId,
				schoolStatusId,educationalId,maritalStatusId,healthConditionId,laborId,laborTime,laborCapacityId,
				soldiersDependentsId,insuranceId,inOutStatusId,isWishEmployment,expectProfessionId,expectWorkplaceId,
				isWishTrain,expectTrainProfessionId,expectTrainTime,remark,isWorking,isTrain,operator,phone,medicalInsuranceId);

		//resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		//resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> forbidMember(String memberId, Integer isValid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap = memberService.forbidMember(memberId, isValid);

		//resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		//resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> forbidLeader(String leaderId, Integer isValid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap = leaderService.forbidLeader(leaderId, isValid);

		//resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		//resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageMemeberCommend(String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 记录流水
		Page<MemberCommendHappen> page = memberCommendHappenService.pageMemberCommendHappen(pageNumber, pageSize,
				memberId);

		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageMemberVisit(String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 记录资讯
		// 去调用资讯的接口

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageMemeberEffect(String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> page = Arnold.dao.pageMemeberEffect(memberId, pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, page);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return resultMap;
		}

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> updateFamilyPovertyproperty(String familyId, String ralationTypeId, String url,
			String remark, String createUserId, String familyPropertyId, Date statusTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		familyService.updateFamilyPovertyproperty(familyId, ralationTypeId,familyPropertyId);
		
		familyPovertyHappenService.addFamilyPovertyHappen(familyId, ralationTypeId, url, remark, createUserId,
				familyPropertyId,statusTime);

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageMemberEmployment(String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> arnoldModel = Arnold.dao.pageMemeberEmployment(memberId, pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, arnoldModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return resultMap;
		}
		return resultMap;
	}

	public Map<String, Object> pageMemberTrain(String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> arnoldModel = Arnold.dao.pageMemberTrain(memberId, pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, arnoldModel);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return resultMap;
		}

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> pageMemberEdu(String memberId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> arnoldModel = Arnold.dao.pageMemberEdu(memberId, pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, arnoldModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> pageIndustries(String memberId, Integer pageNumber, Integer pageSize, 
			Map<String, String> chooseParamMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> arnoldModel = Arnold.dao.pageIndustries(memberId, pageNumber, pageSize, 
					chooseParamMap.get("plantTypeId"), chooseParamMap.get("breedTypeId"));
			
			if(null!=arnoldModel && null!=arnoldModel.getList() && arnoldModel.getList().size()>0){
				for(Record info : arnoldModel.getList()){
					info.set("netIncomeToTal", 
							info.getBigDecimal("incomeToTal").subtract(info.getBigDecimal("costTotal")).doubleValue());
				}
			}
			
			resultMap.put(ConstUtils.R_PAGE, arnoldModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
			return resultMap;
		}
		return resultMap;
	}

	public Map<String, Object> findFamilyRemoval(String familyId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		FamilyService familyService = new FamilyService();
		resultMap = familyService.findFamilyRemoval(familyId);
		
		return resultMap;
	}

	public Map<String, Object> pageFamilyResponsible(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Page<Record> page = Arnold.dao.pageFamilyResponsible(familyId, pageNumber, pageSize);

		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageFamilyVisited(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 记录资讯
		// 去调用资讯的接口

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageFamilyPovertyPropertyRecord(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//Page<Record> page = Arnold.dao.pageFamilyPovertyPropertyRecord(familyId, pageNumber, pageSize);

		FamilyPovertyHappenService happen = new FamilyPovertyHappenService();
		
		resultMap = happen.pageFamilyPovertyPropertyRecord(familyId, pageNumber, pageSize);
		
		return resultMap;
	}

	public Map<String, Object> pageFamilyIndustryDevelopment(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageFamilyIncome(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageFamilyWarningRecord(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Page<FamilyWarningHappen> warnModel = new FamilyWarningHappen().pageFamilyWarnHappenByFId(pageNumber, pageSize,familyId);
			resultMap.put(ConstUtils.R_PAGE, warnModel);
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);		
		}catch(Exception e){		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);		
		}
		
		return resultMap;
	}

	public Map<String, Object> pageLeaderInterviewRecord(String leaderId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageLeaderMemberInfo(String villageId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Page<Record> page = Arnold.dao.pageLeaderMemberInfo(villageId, pageNumber, pageSize);

		resultMap.put(ConstUtils.R_PAGE, page);
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> addLeaderInterviewRecord(String leaderId, String theme, String data, String villagerId,
			String regionId, String url, String detail) {
		Map<String, Object> resultMap = new HashMap<String, Object>();


		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> addLeaderMemberInfo(String leaderId, String name, String subjectId, String levelId,
			String iDnumber, String birthday, String sexId, String phone, String educationalId, String raceId,
			String politicalStatusId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();


		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> addMemberFamily(String memberId, String familyId, String helpResult, Date startTime, Date endTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//流水
		//MemberFamilyHappenService happenService = new MemberFamilyHappenService();
		//happenService.addMemberFamilyHappen(memberId, familyId, helpResult, startTime, endTime);
		
		//关系
		MemberFamilyRelationService relationService = new MemberFamilyRelationService();
		relationService.addMemberFamilyRelation(memberId, familyId,startTime,endTime);
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> addMemberCommend(String memberId, String awardName, String commendLevelId, String commendObjectId,
			Date commendDate, String remark, String creator) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		memberCommendHappenService.addMemberCommendHappen(memberId, awardName, commendLevelId, commendObjectId, commendDate, remark, creator);
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> addMemberInterviewRecord(String memberId, String theme, String data, String villagerId,
			String regionId, String url, String remark) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//资讯
		//调用资讯接口
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> addMemberEmployment(String memberId, String name, String iDnumber, String sexId,
			String phone, String number, String regionId, String postId, String typeId, String companyId,
			String monthlyIncome, String isWorking, String workTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> addMemberTrain(String memberId, String name, String iDnumber, String sexId, String phone,
			String number, String regionId, String trainTheme, String trainPost, String trainContent, String startTime,
			String endTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> addMemberEffect(String memberId, String name, String propertyId, String burdenId,
			String regionId, String measureId, String income, int isStandards, String date) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	public Map<String, Object> updateHelpBranch(String id, String name, String regionid, String address, 
			String operator) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "UPDATE team_help SET name = '"+name+"',regionid = '"+regionid+
					 "',address='"+address+"',nowWriter='"+operator+
					 "',nowCreateTime='"+formatter.format(new Date())+"' WHERE id = '"+id+"'";
		Db.update(sql);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> findDesignatedHelpingVillageInfo(String villageId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Record arnoldModel = Arnold.dao.findDesignatedHelpingVillageInfo(villageId);
			resultMap.put(ConstUtils.R_MODEL, arnoldModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}

	public Map<String, Object> findDesignatedHelpingVillageInfo1(String villageId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Record arnoldModel = Arnold.dao.findDesignatedHelpingVillageInfo1(villageId);
			resultMap.put(ConstUtils.R_MODEL, arnoldModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> addFamilyResponsible(String familyId, String memberId, String iDnumber, String sexId,
			String phone, String politicalStatusId, String accreditPostId, String accreditPlaceId, String departmentId,
			String startTime, String endTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		memberFamilyRelationService.addMemberFamilyRelation(memberId, familyId);
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> findLeader(String leaderId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Record model = Arnold.dao.findLeader(leaderId);
		if(model==null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}
		
		resultMap.put(ConstUtils.R_MODEL, model);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/*public Map<String, Object> addLeaderVillageRelation(String leaderId, String regionId, String povertyTypeId,
			String memberId, String groupName, String remark) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// remark还没加
		
		//参数还有问题
		//leaderDepartmentRelationService.addLeaderDepartmentRelation(leaderId, null, null);
		//leaderRegionRelationService.addLeaderRegionRelation(leaderId, regionId, null, memberId);

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}*/

	public Map<String, Object> pageDesignatedHelpingVillage(Integer pageNumber, Integer pageSize, QueryBaseUtil queryBaseUtil) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> page = Arnold.dao.pageDesignatedHelpingVillage(pageNumber, pageSize, queryBaseUtil);
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}

	public Map<String, Object> pageHelpBranch(Integer pageNumber, Integer pageSize, String regionid, String keyWord) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> page = Arnold.dao.pageHelpBranch(pageNumber, pageSize, regionid, keyWord);
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}

	public Map<String, Object> addHelpBranch(String name, String regionid, String address, String writer,
			String operator) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "INSERT INTO team_help (id,name,regionid,address,nowWriter,nowCreateTime,writer,createTime) "+
					 "VALUES ('"+Utils.create36UUID()+"','"+name+"','"+regionid+"','"+address+"','"+writer+"','"+ 
					 formatter.format(new Date())+"','"+operator+"','"+formatter.format(new Date())+"')";
		Db.update(sql);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageStayingVillage(Integer pageNumber, Integer pageSize,String keyword) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> page = Arnold.dao.pageStayingVillage(pageNumber, pageSize,keyword);
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}

	public Map<String, Object> addStayingVillage(String villageId, String regionId, String poulationCount,
												 String partyMemberCount, String percent, String fiveGuaranteedCount, String poorPopulation, String lowIncomeCount,
												 String removalPlan, Date removalTime, String houseTransformPlan, Date transformTime, String isTapWater,
												 String isSeriousPatient, String isPrimarySchool, String isVillageRoad, String isGroupRoad, String childDropoutCount,
												 String averageIncome, String economyScale, String perCultivatedArea, String perWoodland, String difficulty,
												 String informant, String writer, String operator) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//未实现   未找到库表
		PropertyVillageService propertyVillageService=new PropertyVillageService();
		propertyVillageService.addpropertyVillage(villageId,regionId,poulationCount,partyMemberCount,percent,fiveGuaranteedCount,
				poorPopulation,lowIncomeCount,removalPlan,removalTime,houseTransformPlan,transformTime,isTapWater,
				isSeriousPatient,isPrimarySchool,isVillageRoad,isGroupRoad,childDropoutCount,averageIncome,economyScale,
				perCultivatedArea,perWoodland,difficulty,informant,writer,operator);
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pagePolicyDocument(Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//未实现   未找到库表
		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageStayingVillageDetail(String regionId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> page = Arnold.dao.pageStayingVillage(regionId, pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}

	public Map<String, Object> addPolicyDocument(String documentTypeId, String headline, String url, String remark,
			String operator) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//未实现   未找到库表
		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageResearchReport(Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> page = Arnold.dao.pageResearchReport(pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}

	public Map<String, Object> addResearchReport(String documentTypeId, String headline, String url, String remark,
			String operator) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//未实现   未找到库表
		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageSuggest(Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Page<Record> page = Arnold.dao.pageSuggest(pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}

	public Map<String, Object> addSuggest(String suggestTypeId, String headline, String remark, String operator) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//未实现   未找到库表
		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> updateFamilyLivecondition(String familyId, String cultivatedArea,
			String effectiveIrrigationArea, String field, String land, String forestryArea, String backForestryArea,
			String fruitTreeArea, String grassArea, String waterArea, String economicArea, int isDrinkDifficulty,
			int isDrinkSafety, String drinkConditionId, int isGalvanical, int isTelevision,
			String consumerGoodsCondition, String trunklineDistance, String nearestMarketDistance,
			String registerRoadTypeId, int roadCondition, String buildHouseYear, String housingArea, int hasHouse,
			String houseStructureId, int isDangerousBuilding, String fuelsTypeId, int isSanitaryToilet,
			String productionArea, String liveArea, String longitude,String latitude,String height, String operator,
			String outstandingLoan, String operatingIncome, String receivedLowGold, String perCapitaIncome,
			String annualNetIncome, String annualIncome, String receivedEndowmentInsurance, String allSubsidization,
			String claimMedicalExpenses, String wageIncome, String productiveOutlays, String medicalAid, String propertyIncome,
			String receivedFamilyPlanningMoney, String ecologicalCompensation,
			Double trainingSubsidy, Double trainingExpenditure,
			Double cashPolicySubsidies, Double educationalExpenditure,
			Double educationalSubsidy, Double medicalExpenditure) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = familyLiveConditionService.updateLiveCondition(familyId,cultivatedArea,effectiveIrrigationArea,field,land,forestryArea,
				backForestryArea,fruitTreeArea,grassArea,waterArea,economicArea,isDrinkDifficulty,isDrinkSafety,drinkConditionId,
				isGalvanical,isTelevision,consumerGoodsCondition,trunklineDistance,nearestMarketDistance,registerRoadTypeId,
				roadCondition,buildHouseYear,housingArea,hasHouse,houseStructureId,isDangerousBuilding,fuelsTypeId,isSanitaryToilet,
				productionArea,liveArea,longitude,latitude,height,operator,
				outstandingLoan,operatingIncome,receivedLowGold,perCapitaIncome,annualNetIncome,annualIncome,receivedEndowmentInsurance,
				allSubsidization,claimMedicalExpenses,wageIncome,productiveOutlays,medicalAid,propertyIncome,receivedFamilyPlanningMoney,
				ecologicalCompensation,trainingSubsidy,trainingExpenditure,cashPolicySubsidies,educationalExpenditure,educationalSubsidy,
				medicalExpenditure);
		
		return resultMap;
	}

	public Map<String, Object> findFamilyLiveCondition(String familyId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap = familyLiveConditionService.findLiveCondition(familyId);

		//resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		//resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> updateBackstageCrew(String userId, String username, String name, String sexId,
			String phone, String roleId, String orgId, String regionId, Date lastLoginTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = backstageCrewService.updateBackstageCrew(userId, username, name, sexId, phone, roleId, 
				orgId, regionId, lastLoginTime);
		
		return resultMap;
	}

/*	public Map<String, Object> pageBackstageCrew(Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = backstageCrewService.pageBackstageCrew(pageNumber,pageSize);
		
		return resultMap;
	}*/
	
	public Map<String, Object> pageBackstageCrew(Integer pageNumber,
			Integer pageSize, RequestBackStageCrew requestBean) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = backstageCrewService.pageBackstageCrew(pageNumber,pageSize, requestBean);
		
		return resultMap;
	}
	
	public Map<String, Object> addBackstageCrew(String userId, String username, String name, String sexId,
			String phone, String roleId, String orgId, String regionId, Date lastLoginTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = backstageCrewService.addBackstageCrew(userId, username, name, sexId, phone, roleId, 
				orgId, regionId, lastLoginTime);
		
		return resultMap;
	}

	public Map<String, Object> findAllLeader(String helperTypeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = leaderService.findAllLeader(helperTypeId);
		
		return resultMap;
	}

	public Map<String, Object> findAllMember(QueryBaseUtil queryConfig) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//List<Record> list = Arnold.dao.findAllMember();
		
		resultMap = memberService.listAllMemberName(queryConfig);		
		
		return resultMap;
	}

	public Map<String, Object> findRegionInfo(String regionId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Record model = Arnold.dao.findRegionInfo(regionId);
		
		
		Record model2 = Arnold.dao.selectRegionRelationalData(regionId);
		
		//获取第一书记信息
		if(null!=model){
			List<Member> firstSecretaryInfos = Arnold.dao.findMemberInfoByRIdAndRType(regionId, 1);
			if(null!=firstSecretaryInfos && firstSecretaryInfos.size()>0){
				model.set("firstSecretaryMemberId",firstSecretaryInfos.get(0).get("id", ""));
				model.set("firstSecretaryMemberName",firstSecretaryInfos.get(0).get("name", ""));
			}else{
				model.set("firstSecretaryMemberId","");
				model.set("firstSecretaryMemberName","");
			}
		}

		if(model.get("population")==null||"0".equals(model.get("population").toString())){
			model.set("population",model2.get("family_villager_count"));
		}
		if(model.get("count")==null||"0".equals(model.get("count").toString())){
			model.set("count",model2.get("family_count"));
		}
		if(model.get("cultivatedArea")==null){
			model.set("cultivatedArea",model2.get("cultivatedArea"));
		}
		if(model.get("irrigatedArea")==null){
			model.set("irrigatedArea",model2.get("effectiveIrrigationArea"));
		}
		if(model.get("fieldArea")==null){
			model.set("fieldArea",model2.get("fieldArea"));
		}
		if(model.get("landArea")==null){
			model.set("landArea",model2.get("landArea"));
		}
		if(model.get("forestryArea")==null){
			model.set("forestryArea",model2.get("forestryArea"));
		}
		if(model.get("backForestryArea")==null){
			model.set("backForestryArea",model2.get("backForestryArea"));
		}
		if(model.get("fruitTreeArea")==null){
			model.set("fruitTreeArea",model2.get("fruitTreeArea"));
		}
		if(model.get("grassArea")==null){
			model.set("grassArea",model2.get("grassArea"));
		}
		if(model.get("waterArea")==null){
			model.set("waterArea",model2.get("waterArea"));
		}
		if(model.get("economicArea")==null){
			model.set("economicArea",model2.get("economicArea"));
		}

		if(model.get("grossIncome")==null){
			model.set("grossIncome",model2.get("averageIncome"));
		}
		if(model.get("householdIncome")==null){
			model.set("householdIncome",model2.get("family_income"));
		}
		if(model.get("propertyIncome")==null){
			model.set("propertyIncome",model2.get("propertyIncome"));
		}
		if(model.get("transferIncome")==null){
			model.set("transferIncome",model2.get("shift_income"));
		}
		if(model.get("totalNetIncome")==null){
			model.set("totalNetIncome",model2.get("total_income"));
		}
		if(model.get("totalPerNetIncome")==null){
			model.set("totalPerNetIncome",model2.get("average_income"));
		}
		if(model.get("totalProductionExpenses")==null){
			model.set("totalProductionExpenses",model2.get("purchase_expend"));
		}
		if(model.get("totalOtherExpenses")==null){
			model.set("totalOtherExpenses",model2.get("other_expend"));
		}

		resultMap.put(ConstUtils.R_MODEL, model);		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> updateLeaderRegionRelation(String regionId, String povertyTypeId, String leaderId,
			Date assignDate, Date assignEndDate, String memberId, String remark, String shortDescription,
			String operator, String firstSecretaryMemberId, Map<String,String> chooseParamMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		regionInfoService.updateRegionInfo(regionId, povertyTypeId, shortDescription,remark,operator,assignDate,assignEndDate,chooseParamMap);
		
		//更新领导与贫困村/组的关系
		LeaderRegionRelationService lrRelation = new LeaderRegionRelationService();
		lrRelation.updateLeaderRegionRelation(regionId,leaderId);
		
		//更新指挥部定点联系队员与贫困村/组的关系
		MemberRegionRelationService mrRelation = new MemberRegionRelationService();
		mrRelation.updateMemberRegionRelation(regionId, memberId);
		
		//更新第一书记记录
		if(!Utils.isBlankOrEmpty(firstSecretaryMemberId)){
			mrRelation.updateFirstSecretaryInfo(regionId, firstSecretaryMemberId);
		}
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> findVillageNameList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		RegionService regionService = new RegionService();
		resultMap = regionService.findVillageNameList();
		
		return resultMap;
	}

	public Map<String, Object> addRegion(String regionId, String shortName, String fullName, String parentId,
			String parentName, int regionType, int property, String cityCode, String adCode, String longitude,
			String latitude) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		RegionService regionService = new RegionService();
		resultMap = regionService.addRegion(regionId,shortName,fullName,parentId,
				parentName,regionType,property,cityCode,adCode,longitude,latitude);

		return resultMap;
	}

	public Map<String, Object> updateRegion(String regionId, String shortName, String fullName, String parentId,
			String parentName, int regionType, int property, String cityCode, String adCode, String longitude,
			String latitude) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		RegionService regionService = new RegionService();
		resultMap = regionService.updateRegion(regionId,shortName,fullName,parentId,
				parentName,regionType,property,cityCode,adCode,longitude,latitude);
		
		return resultMap;
	}

	public Map<String, Object> delRegion(String regionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		RegionService regionService = new RegionService();
		resultMap = regionService.delRegion(regionId);

		return resultMap;
	}

	public Map<String, Object> findChildRegion(String regionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		RegionService regionService = new RegionService();
		resultMap = regionService.findChildRegion(regionId);

		return resultMap;
	}

	public Map<String, Object> pagePoorPersonByCondition(Integer pageNumber, Integer pageSize, String IDnumber) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT v.*,f.phone,f.townid,f.hamletid,f.hamletname ");
			sql.append("FROM tb_villager v ");
			sql.append("LEFT JOIN tb_family f ON f.id = v.familyId ");
			sql.append("LEFT JOIN tb_family_status fs ON fs.familyId = f.id ");
			sql.append("WHERE v.IDnumber = '"+IDnumber+"' ");
			sql.append("OR v.name = '"+IDnumber+"' ");
			sql.append("OR f.phone = '"+IDnumber+"' ");
//			sql.append("AND fs.povertyStatus =''");
			sql.append("LIMIT 0,1");
			List<Record> list = Db.find(sql.toString());
			if(pageSize == 1){
				if(!list.isEmpty()){
					resultMap.put(ConstUtils.R_MODEL, list.get(0));
				}
			}else{
				resultMap.put(ConstUtils.R_PAGE, list);
			}
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> pageMemberByCondition(Integer pageNumber, Integer pageSize, String IDnumber) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT m.* ");
			sql.append("FROM tb_member m ");
			if(IDnumber != null && !IDnumber.equals("")){
				sql.append("WHERE m.IDnumber = '"+IDnumber+"' ");
				sql.append("OR m.name = '"+IDnumber+"' ");
				sql.append("OR m.phone = '"+IDnumber+"' ");
				sql.append("LIMIT 0,1");
			}else{
				sql.append("LIMIT "+pageNumber*pageSize+","+pageSize);
			}
//			sql.append("AND fs.povertyStatus =''");
			List<Record> list = Db.find(sql.toString());
			if(pageSize == 1){
				if(!list.isEmpty()){
					resultMap.put(ConstUtils.R_MODEL, list.get(0));
				}
			}else{
				resultMap.put(ConstUtils.R_PAGE, list);
			}
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pagePoorPersonByid(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT v.*,f.phone,f.townid,f.hamletid,f.hamletname ");
			sql.append("FROM tb_villager v ");
			sql.append("LEFT JOIN tb_family f ON f.id = v.familyId ");
			sql.append("LEFT JOIN tb_family_status fs ON fs.familyId = f.id ");
			sql.append("WHERE v.id = '"+id+"' ");
//			sql.append("AND fs.povertyStatus =''");
			Record model = Db.findFirst(sql.toString());
			resultMap.put(ConstUtils.R_MODEL, model);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageMemberByid(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT m.* ");
			sql.append("FROM tb_member m ");
			sql.append("WHERE m.IDnumber = '"+id+"' ");
//			sql.append("AND fs.povertyStatus =''");
			Record model = Db.findFirst(sql.toString());
			resultMap.put(ConstUtils.R_MODEL, model);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;
	}

	public Map<String, Object> pageFamilyTrainingGuide(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Page<Record> page = Arnold.dao.pageFamilyTrainingGuide(familyId, pageNumber, pageSize);

		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		// TODO Auto-generated method stub
		return resultMap;
	}

	public Map<String, Object> pageFamilyEducationalGuide(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Page<Record> page = Arnold.dao.pageFamilyEducationalGuide(familyId, pageNumber, pageSize);

		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		// TODO Auto-generated method stub
		return resultMap;
	}

	public Map<String, Object> pageFamilyEmploymentInformation(String familyId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Page<Record> page = Arnold.dao.pageFamilyEmploymentInformation(familyId, pageNumber, pageSize);

		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		// TODO Auto-generated method stub
		return resultMap;
	}

	public Map<String, Object> pageFamilyList(String regionId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//判断区域，获取区域拥有的组Id
		Region regionInfo = Region.dao.findByRegionId(regionId);
		
		if(null!=regionInfo && null!=regionInfo.getRegionType()){
			List<String> groupIds = new ArrayList<String>();
			switch (regionInfo.getRegionType()) {
			case 6://组
				groupIds.add(regionInfo.getId());
				break;
			case 5://村
				List<Region> groupInfos = Region.dao.findChildRegion(regionInfo.getId());//组信息集合
				if(null!=groupInfos && groupInfos.size()>0){
					for(Region info : groupInfos){
						groupIds.add(info.getId());
					}
				}
				break;
			default:
				break;
			}
			
			Page<Record> page = Arnold.dao.pageFamilyList(regionId, groupIds, pageNumber, pageSize);
			
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		}else{
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
		}
		return resultMap;
	}

	public Map<String, Object> pageFamilyList1(String regionId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String sql = "SELECT tf.id AS familyId, tf.number, v.`name` AS villagerName, v.IDnumber, " +
					"v.raceId, GROUP_CONCAT(DISTINCT v1.id) AS villagerIds, tf.addReason," +
					" tf.ralationTypeId, m.`name` AS memberName ";
			String sqlExceptSelect = " FROM tb_family tf LEFT JOIN tb_region r ON tf.hamletId = r.id AND tf.isValid = 0 " +
					"LEFT JOIN tb_villager v ON tf.villagerId = v.id AND v.isValid = 0 " +
					"LEFT JOIN tb_villager v1 ON tf.id = v1.familyId AND v.isValid = 0 " +
					"LEFT JOIN tb_member_family_relation mf ON mf.familyId = tf.id AND mf.isValid >= 0 " +
					"LEFT JOIN tb_member m ON m.id = mf.memberId " +
					"WHERE r.id = ? GROUP BY tf.id";
			Page<Record> page = Db.paginate(pageNumber, pageSize, sql, sqlExceptSelect,  regionId);
			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> getPoolRegion() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String sql = "SELECT * FROM tb_region WHERE regionType = 5";
			List<Record> r = Db.find(sql);
			resultMap.put(ConstUtils.R_LIST, r);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		return resultMap;
	}
	
	public Map<String, Object> statisticsFamily(String countryId, String regionId, String administrativeRegionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			StringBuilder startSql = new StringBuilder("SELECT tf.ralationTypeId, COUNT(1) AS households, ");
			StringBuilder countSql = new StringBuilder("(SELECT COUNT(1) FROM tb_villager tv WHERE EXISTS "
					+ "(SELECT 1 FROM tb_family ttf WHERE tv.familyId = ttf.id "
					+ "AND ttf.isValid = tf.isValid AND ttf.ralationTypeId = tf.ralationTypeId ");
			StringBuilder conditionSql = new StringBuilder("FROM tb_family tf WHERE tf.isValid = 0 ");
			StringBuilder endSql = new StringBuilder(" GROUP BY tf.ralationTypeId");
			
			if(Utils.isBlankOrEmpty(regionId)){
				//乡查询
				countSql.append(" AND ttf.regionId in (SELECT tr.id FROM tb_region tr,tb_region str WHERE"
						+ " tr.parentId = str.id and str.parentId = '"
						+ countryId +"'))) AS villageCount ");
				conditionSql.append(" AND tf.regionId in (SELECT tr.id FROM tb_region tr,tb_region str WHERE"
						+ " tr.parentId = str.id and str.parentId = '"
						+ countryId +"') ");
			}else{
				if(Utils.isBlankOrEmpty(administrativeRegionId)){//村查询
					conditionSql.append(" AND tf.regionId in (SELECT tr.id FROM tb_region tr WHERE"
							+ " tr.parentId = '").append(regionId).append("' ");
					countSql.append(" AND ttf.regionId in (SELECT tr.id FROM tb_region tr WHERE"
							+ " tr.parentId = '").append(regionId).append("')) AS villageCount ");
				}else{//组查询
					countSql.append(" AND ttf.regionId=tf.regionId"
							+ ")) AS villageCount ");
					conditionSql.append(" AND tf.regionId='").append(administrativeRegionId).append("' ");
				}
			}
			
			List<Record> r = Db.find(startSql.append(countSql).append(conditionSql).append(endSql).toString());
			resultMap.put(ConstUtils.R_LIST, r);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		return resultMap;
	}

	public Map<String, Object> addFamilyWarningHappen(String familyId,
			String warnUserId, Date wtime, String warnContent, int status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyWarningHappen familyModel = new FamilyWarningHappen();
		
		//没有预警时间？
		
		familyModel.setId(Utils.create36UUID());//id
		familyModel.setFamilyId(familyId);
		familyModel.setWarnUserId(warnUserId);
		switch (status) {
		case 1://普通预警
			familyModel.setWarnTime(wtime);
			break;
		case 2://严重预警
			familyModel.setSeriousWarnTime(wtime);
			break;
		default:
			break;
		}
		familyModel.setWarnContent(warnContent);
		familyModel.setStatus(status);
		
		familyModel.save();
		
		resultMap.put("id", familyModel.getId());//返回Id
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	/**
	 * @Author PanChangGui
	 * @Email 823468425@qq.com
	 * @Time 2017年9月22日 下午2:55:44
	 * @Description 
	 */
	public Map<String, Object> listAllRegion() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		RegionService regionService = new RegionService();
		resultMap = regionService.listAllRegion();
		
		return resultMap;
	}

	public Map<String,Object> delStayingVillage(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		PropertyVillageService propertyVillageService = new PropertyVillageService();
		resultMap = propertyVillageService.delStayingVillage(id);

		return resultMap;
	}

	public Map<String,Object> q_stayingVillage(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String sql = "SELECT v.*,r.fullName FROM tb_property_village v LEFT JOIN tb_region r ON v.regionId=r.id where v.id=?";
			List<Record> r = Db.find(sql,id);
			resultMap.put(ConstUtils.R_MODEL, r.get(0));
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		return resultMap;
	}
	public Map<String,Object> queryBackCreawByLogin(String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		BackstagecrewService backService=new BackstagecrewService();
		Backstagecrew backstageCrew = backService.updateBackstageCrewByLogin(userId);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		resultMap.put(ConstUtils.R_MODEL, backstageCrew);
		return resultMap;
	}

	/**
	 * @Author PanChangGui
	 * @Time 2017年9月27日 下午6:21:23
	 * @Description 
	 */
	public Map<String, Object> listVillagerName(String regionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Record> list = Arnold.dao.listVillagerName(regionId);
		
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Author PanChangGui
	 * @Time 2017年10月2日 上午10:37:27
	 * @Description 
	 */
	public Map<String, Object> updateFamilyRemoval(String familyId, int isMoveFamily, String removalTypeId,
			String outRegion, String toRegion, String resettlementWayId, String difficult, Date stayInTime,
			int isStayIn, int isMoval) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		FamilyService familyService = new FamilyService();
		resultMap = familyService.updateFamilyRemoval(familyId,isMoveFamily,removalTypeId,outRegion,toRegion,
				resettlementWayId,difficult,stayInTime,isStayIn,isMoval);
		
		return resultMap;
	}

	/**
	 * @Author PanChangGui
	 * @Time 2017年10月5日 下午3:41:31
	 * @Description 
	 */
	public Map<String, Object> delLeader(String leaderId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		LeaderService leaderService = new LeaderService();
		resultMap = leaderService.delLeader(leaderId);
		
		return resultMap;
	}
	
	public Map<String, Object> pageStaticFamilyIncome(String familyId,
			Integer pageNumber, Integer pageSize, String year) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Page<StaticFamilyIncome> list = StaticFamilyIncome.dao.pageStaticFamilyIncomeByFamilyId(pageNumber, pageSize,familyId, year);
			resultMap.put(ConstUtils.R_PAGE, list);
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);		
		}catch(Exception e){		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);		
		}
		
		return resultMap;
	}

}
