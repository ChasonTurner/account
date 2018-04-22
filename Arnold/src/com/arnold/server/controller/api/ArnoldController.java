package com.arnold.server.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.arnold.server.arnoldService.ArnoldService;
import com.arnold.server.arnoldService.ArnoldService1;
import com.arnold.server.bean.request.RequestBackStageCrew;
import com.arnold.server.controller.BaseController;
import com.arnold.server.model.Member;
import com.arnold.server.model.Villager;
import com.arnold.server.service.api.AccreditedService;
import com.arnold.server.service.api.BackstagecrewService;
import com.arnold.server.service.api.MemberService;
import com.arnold.server.service.api.VillagerService;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.arnold.server.util.QueryBaseUtil;
import com.arnold.server.util.QueryUtil;
import com.huntersun.tool.ConstUtils;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author ChangGui Pan
 * @time 2017年8月12日 下午2:49:25
 * @description TODO
 */
public class ArnoldController extends BaseController {
	private final int defaultNumber = 1;
	ArnoldService arnoldService = new ArnoldService();
	MemberService memberService = new MemberService();
	AccreditedService accreditedService = new AccreditedService();
	Map<String, Object> checkMap = new HashMap<String, Object>();
	
	private static VillagerService villagerService = new VillagerService();

	/**
	 * 增加扶贫人员
	 */
	public void add_member() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String memberId = getPara("memberId");
				
		// tb_member
		String name = getPara("name");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		Date birthday = getParaToDate("birthday", null);
		String IDnumber = getPara("IDnumber");// 身份证号
		//System.out.println("IDnumber: " + IDnumber);
		
		String politicalStatusId = getPara("politicalStatusId");// 政治面貌
		String educationalId = getPara("educationalId");
		String raceId = getPara("raceId");// 民族
		String orgId = getPara("orgId");// 现工作单位
		String post = getPara("post");// 职位， 现任职务
		
		String groupId = getPara("groupId");// 流水？
		String roleOfGroupId = getPara("roleOfGroupId", "");// 工作组角色

		String writer = getPara("writer");// 录入人

		// tb_arrcedited
		String accreditDepartmentId = getPara("accreditDepartmentId");// 派驻单位
		String orgPost = getPara("orgPost");// 原单位职务
		String accreditPlaceId = getPara("accreditPlaceId");// 派驻地点
		String accreditPostId = getPara("accreditPostId");// 派驻期间职务
		Date accreditStart = getParaToDate("accreditStart", null);
		Date accreditEnd = getParaToDate("accreditEnd", null);
		String remark = getPara("remark");

		// 后台人员 
		String username = getPara("username");//用户名
		
		
		/*
		 * //波塞冬 String userName = getPara("userName"); String password =
		 * getPara("password"); String appIdP = getPara("appIdP");
		 */

		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);
		checkMap.put("name", name);
		checkMap.put("sexId", sexId);
		// checkMap.put("phone", phone);
		// checkMap.put("birthday", birthday);
		//checkMap.put("IDnumber", IDnumber);
		// checkMap.put("politicalStatusId", politicalStatusId);
		// checkMap.put("educationalId", educationalId);
		// checkMap.put("raceId", raceId);
		// checkMap.put("orgId", orgId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addMember(memberId, name, sexId, phone, birthday, IDnumber, politicalStatusId,
				educationalId, raceId, orgId, post, groupId, roleOfGroupId, writer, accreditDepartmentId,
				orgPost, accreditPlaceId, accreditPostId, accreditStart, accreditEnd, remark);

		String roleId = accreditPostId;
		String regionId = null;
		Date lastLoginTime = null;
		arnoldService.addBackstageCrew(memberId, username, name, sexId, phone, roleId, orgId, regionId, lastLoginTime);
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 修改后台人员所属角色
	 */
	public void update_backstage_crew_role() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String id = getPara("id");
		String roleId = getPara("roleId");
		String roleName = getPara("roleName");
		checkMap.clear();
		checkMap.put("id", id);
		checkMap.put("roleId", roleId);
		checkMap.put("roleName", roleName);
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
	
		resultMap = BackstagecrewService.updateBackstagecrew(id, roleId, roleName);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 更新扶贫人员
	 */
	public void update_member() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String memberId = getPara("memberId");// 扶贫人员Id
		
		// tb_member
		String name = getPara("name");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		Date birthday = getParaToDate("birthday", null);
		String iDnumber = getPara("iDnumber");// 身份证号
		String politicalStatusId = getPara("politicalStatusId");// 政治面貌
		String educationalId = getPara("educationalId");
		String raceId = getPara("raceId");// 民族
		String orgId = getPara("orgId");// 现工作单位
		String post = getPara("postId");// 职位， 现任职务
		String groupId = getPara("groupId");// 工作组 是流水？
		
		String operator = getPara("operator");// 操作人
		
		String accreditDepartmentId = getPara("accreditDepartmentId");// 派驻单位
		String orgPost = getPara("orgPostId", "");// 原单位职务
		String accreditPlaceId = getPara("accreditPlaceId");// 派驻地点
		String accreditPostId = getPara("accreditPostId");// 派驻期间职务
		Date accreditStart = getParaToDate("accreditStart", null);
		Date accreditEnd = getParaToDate("accreditEnd", null);
		String remark = getPara("remark");// 可以为空
		String roleOfGroupId = getPara("roleOfGroupId", "");// 工作组角色
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.updateMember(memberId, name, sexId, phone, birthday, iDnumber, politicalStatusId, educationalId,
				raceId, orgId, post, groupId, roleOfGroupId, operator, accreditDepartmentId, orgPost,
				accreditPlaceId, accreditPostId, accreditStart, accreditEnd, remark);
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 分页查询扶贫人员信息
	 * 
	 * 多条件查询
	 */
	public void page_member() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int pageNumber = getParaToInt("pageNumber", defaultNumber);
		int pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);

		//多条件查询
		String accreditPostId = getPara("accreditPostId");//派驻职务
		String accreditPlace = getPara("accreditPlace");//派驻点
		String accreditDepartmentId = getPara("accreditDepartmentId");//派驻单位
		String post = getPara("postId");//现任职务
		String groupId = getPara("groupId");//所属分组
		String accreditStart =StringUtils.isEmpty(getPara("accreditStart"))?null:getPara("accreditStart") ;//开始时间
		String accreditEnd = StringUtils.isEmpty(getPara("accreditEnd"))?null:getPara("accreditEnd");//结束时间
		//Date birthday = getParaToDate("birthday", null);
		String politicalStatusId = getPara("politicalStatusId");//政治面貌
		
		List<QueryUtil> queryList=new ArrayList<>();
		queryList.add(new QueryUtil("accreditPostId", accreditPostId, "="));
		queryList.add(new QueryUtil("accreditPlace", accreditPlace, "="));
		queryList.add(new QueryUtil("accreditDepartmentId", accreditDepartmentId, "="));
		queryList.add(new QueryUtil("postId", post, "="));
		queryList.add(new QueryUtil("groupId", groupId, "="));
		queryList.add(new QueryUtil("accreditStart", accreditStart, ">="));
		queryList.add(new QueryUtil("accreditEnd", accreditEnd, "<="));
		queryList.add(new QueryUtil("politicalStatusId", politicalStatusId, "="));
		QueryBaseUtil queryBaseUtil=new QueryBaseUtil(queryList,getPara("key"),"name","phone");
		resultMap = arnoldService.pageMember(pageNumber, pageSize, queryBaseUtil);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查询扶贫人员详情基本信息
	 */
	public void find_member() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String memberId = getPara("memberId");
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.findMember(memberId);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查询所有扶贫人员
	 */
	public void find_allMember() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String accreditPostId = getPara("accreditPostId");//选派期间职务ID
		
		List<QueryUtil> queryList = new ArrayList<QueryUtil>();
		queryList.add(new QueryUtil("accreditPostId", accreditPostId, "="));
		
		QueryBaseUtil queryConfig = new QueryBaseUtil(queryList, "", "");
		
		resultMap = arnoldService.findAllMember(queryConfig);
		renderJson(resultMap);
		return;
	}

	/**
	 * 删除扶贫人员
	 */
	public void del_member() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String memberId = getPara("memberId");
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.delMember(memberId);
		renderJson(resultMap);
		return;
	}

	/**
	 * 查找扶贫人员所帮扶的家庭
	 */
	public void page_memberFamilyRelation() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		String memberId = getPara("memberId");
		checkMap.clear();
		checkMap.put("memberId", memberId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.pageMemberFamily(memberId, pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Author PanChangGui
	 * @Time 2017年10月7日 上午10:46:52
	 * @Description
	 */
	public void page_memberFamilyHappen() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		String memberId = getPara("memberId");
		checkMap.clear();
		checkMap.put("memberId", memberId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.pageMemberFamily(memberId, pageNumber, pageSize);
		//resultMap = arnoldService.pageMemberFamilyHappen(memberId, pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加贫困户
	 */
	public void add_family() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String number = getPara("number","");// 家庭编号
		String administrativeRegionId = this.getPara("administrativeRegionId","");//家庭所在地
		String name = getPara("name","");//户主姓名
		String IDnumber = getPara("IDnumber","");
		String aspirationId = getPara("aspirationId","");//贫困户发展意愿
		String phone = getPara("phone","");
		String raceId = getPara("raceId","");
		String sexId = getPara("sexId","");
		String educationalId = getPara("educationalId","");
		
		String healthConditionId = getPara("healthConditionId","");//健康状况
		String maritalStatusId = getPara("maritalStatusId","");//婚姻状况
		String ralationTypeId = getPara("ralationTypeId","");//贫困状态
		String measureId = getPara("measureId","");//帮扶措施，可能多个
		String burdenId = getPara("burdenId","");//致贫原因，可能多个
		int isSeriousPatient = getParaToInt("isSeriousPatient", -1);//是否有重病患者	
		String laborCapacityId = getPara("laborCapacityId","");//劳动能力	
		int isDropout = getParaToInt("isDropout", -1);//是否有因贫辍学学生
		int isOnlyChild = getParaToInt("isOnlyChild", -1);//是否独生子女	
		String soldiersDependentsId = getPara("soldiersDependentsId","");//军属	
		String insuranceId = getPara("insuranceId","");//养老保险，可能多个
		String medicalInsuranceId = getPara("medicalInsuranceId","");//医疗保险，可能多个
		Date planeTime = getParaToDate("planeTime", null);//计划脱贫年份	
		String writer = getPara("writer","");//录入人
		//String operator = getPara("operator","");//操作人
		String addReason = getPara("addReason","");//增加原因
		String remark = getPara("remark","");
		
		//下面的PRD没有，但后期可能需要
		int isTrain = getParaToInt("isTrain", -1);
		int isWorking = getParaToInt("isWorking", -1);
		int isMoveFamily = getParaToInt("isMoveFamily", -1);//是否是拆迁户
		Date birthday = getParaToDate("birthday", null);
		
		//prd增加字段
		Date helpTime = getParaToDate("helpTime", null);//帮扶时间	
		String familyPropertyId = getPara("familyPropertyId","");//贫困户属性
		
		resultMap = arnoldService.addFamily(number,name,IDnumber,administrativeRegionId,aspirationId,
				phone,raceId,sexId,educationalId,healthConditionId,maritalStatusId,ralationTypeId,measureId,burdenId,
				isSeriousPatient,laborCapacityId,isDropout,isOnlyChild,soldiersDependentsId,insuranceId,helpTime,
				planeTime,writer,addReason,remark,isTrain,isWorking,isMoveFamily,birthday,medicalInsuranceId,familyPropertyId);
		renderJson(resultMap);
		return;
	}

	/**
	 * 更新贫困户
	 */
	public void update_family() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");// 家庭id
		String number = getPara("number");// 家庭编号
		String administrativeRegionId = this.getPara("administrativeRegionId");//家庭所在地
		String name = getPara("name");//户主姓名
		String IDnumber = getPara("IDnumber");
		String aspirationId = getPara("aspirationId");//贫困户发展意愿
		String phone = getPara("phone");
		String raceId = getPara("raceId");
		String sexId = getPara("sexId");
		String educationalId = getPara("educationalId");
		
		String healthConditionId = getPara("healthConditionId");//健康状况
		String maritalStatusId = getPara("maritalStatusId");//婚姻状况
		String ralationTypeId = getPara("ralationTypeId");//贫困状态
		String measureId = getPara("measureId");//帮扶措施，可能多个
		String burdenId = getPara("burdenId");//致贫原因，可能多个
		int isSeriousPatient = getParaToInt("isSeriousPatient", -1);//是否有重病患者	
		String laborCapacityId = getPara("laborCapacityId");//劳动能力	
		int isDropout = getParaToInt("isDropout", -1);//是否有因贫辍学学生
		int isOnlyChild = getParaToInt("isOnlyChild", -1);//是否独生子女	
		String soldiersDependentsId = getPara("soldiersDependentsId");//军属	
		String insuranceId = getPara("insuranceId","");//养老保险，可能多个
		String medicalInsuranceId = getPara("medicalInsuranceId","");//医疗保险，可能多个
		Date planeTime = getParaToDate("planeTime", null);//计划脱贫年份	
		//String writer = getPara("writer");//录入人
		String operator = getPara("operator","");//操作人
		String addReason = getPara("addReason");//增加原因
		String remark = getPara("remark");
		
		//下面的PRD没有，但后期可能需要
		int isTrain = getParaToInt("isTrain", -1);
		int isWorking = getParaToInt("isWorking", -1);
		int isMoveFamily = getParaToInt("isMoveFamily", -1);//是否是拆迁户
		Date birthday = getParaToDate("birthday", null);	
		
		//prd增加字段
		Date helpTime = getParaToDate("helpTime", null);//帮扶时间
		String familyPropertyId = getPara("familyPropertyId","");//贫困户属性
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.updateFamily(familyId,number,name,IDnumber,administrativeRegionId,aspirationId,
				phone,raceId,sexId,educationalId,healthConditionId,maritalStatusId,ralationTypeId,measureId,burdenId,
				isSeriousPatient,laborCapacityId,isDropout,isOnlyChild,soldiersDependentsId,insuranceId,helpTime,
				planeTime,operator,addReason,remark,isTrain,isWorking,isMoveFamily,birthday,medicalInsuranceId,familyPropertyId);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 分页查看贫困户
	 * 
	 * 多条件查询
	 * 
	 */
	public void page_family() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		String burdenId = getPara("burdenId");//致贫原因
		String measureId = getPara("measureId");//帮扶措施
		String ralationTypeId = getPara("ralationTypeId");//贫困状态
		String raceId = getPara("raceId");//名族
		Date birthday = getParaToDate("birthday");//出生日期
		String keyWord = getPara("keyWord");//关键字搜索
		String hamletId = getPara("hamletId");//村
		String regionId = getPara("administrativeRegionId");//组
		
		Map<String, Object> searchMaps = new HashMap<>();
		
		if(ralationTypeId != null && ralationTypeId.trim().length() > 0){
			searchMaps.put("tf.ralationTypeId", " = '"+ralationTypeId+"'");
		}
		if(raceId != null && raceId.trim().length() > 0){
			searchMaps.put("tv.raceId",  " = '"+raceId+"'");
		}
		if(burdenId != null && burdenId.trim().length() > 0){
			searchMaps.put("burdenId", " like '%"+burdenId+"%'");
		}
		if(measureId != null && measureId.trim().length() > 0){
			searchMaps.put("measureId", " like '%"+measureId+"%'");
		}
		if(birthday != null){
			searchMaps.put("tv.birthday", " = '"+birthday+"'");
		}
		if(keyWord != null && keyWord.trim().length() > 0){
			searchMaps.put("(", " tv.name like '%"+keyWord+"%' or tv.IDnumber like '%" + keyWord + "%')");
		}
		if(hamletId != null && hamletId.trim().length() > 0){
			searchMaps.put("tr.parentId",  " = '"+hamletId+"'");
		}
		if(regionId != null && regionId.trim().length() > 0){
			searchMaps.put("tf.regionId",  " = '"+regionId+"'");
		}
		resultMap = arnoldService.pageFamily(pageNumber, pageSize,searchMaps);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 贫困户统计查询
	 */
	public void statistics_family(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String countryId = getPara("countryId");
		String regionId = getPara("regionId");
		String administrativeRegionId = getPara("administrativeRegionId");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("countryId", countryId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.statisticsFamily(countryId, regionId,administrativeRegionId);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 预警处理：指定预警类型进行预警添加
	 */
	public void early_warning_processing(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String familyId = getPara("familyId");
		String warnUserId = getPara("warnUserId");
		String warnContent = getPara("warnContent");
		int status = getParaToInt("status", 1);
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);
		checkMap.put("warnUserId", warnUserId);
		checkMap.put("warnContent", warnContent);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.addFamilyWarningHappen(familyId,warnUserId, new Date(),warnContent,status);

		renderJson(resultMap);
		return;
	}

	/**
	 * 查看贫困户基本信息
	 */
	public void find_family() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String familyId = getPara("familyId");// 家庭id
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.findFamily(familyId);

		renderJson(resultMap);
		return;
	}

	/**
	 * 分页查看家庭成员
	 */
	public void page_familymember() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		String familyId = getPara("familyId");// 家庭id
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.pageFamilymember(pageNumber, pageSize, familyId);
		renderJson(resultMap);
		return;
	}
	
	/** 获取家庭成员信息 */
	public void find_family_villager_info() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String villagerId = getPara("villagerId");//成员Id
		// 参数组装检查
		checkMap.clear();
		checkMap.put("villagerId", villagerId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = villagerService.findVillagerInfoById(villagerId);
		
		renderJson(resultMap);
		return;
	}

	/**
	 * 更新家庭成员
	 */
	public void update_familyVillager() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String villagerId = getPara("villagerId", "");//家庭成员Id
		
		String familyId = getPara("familyId", "");// 家庭ID
		
		String name = getPara("name", "");
		String sexId = getPara("sexId", "");
		String IDnumber = getPara("IDnumber", "");//
		Date birthday = getParaToDate("birthday", null);//
		String raceId = getPara("raceId", "");//
		String relationOfFamilyId = getPara("relationOfHouseholderId", "");// 与户主的关系
		String schoolStatusId = getPara("schoolStatusId", "");// 在校状况
		String educationalId = getPara("educationalId", "");
		String maritalStatusId = getPara("maritalStatusId", "");// 婚姻状况
		String healthConditionId = getPara("healthConditionId", "");// 健康状况
		String laborId = getPara("laborStatusId", "");//务工状况
		String laborTime = getPara("laborTime", "");//务工时间
		String laborCapacityId = getPara("laborCapacityId", "");//劳动能力
		String soldiersDependentsId = getPara("soldiersDependentsId", "");//军属
		String insuranceId = getPara("insuranceId", "");//养老保险
		String inOutStatusId = getPara("inOutStatusId", "");//迁入迁出状态
	
		int isWishEmployment = getParaToInt("isWishEmployment",-1);//有无就业意愿
		String expectProfessionId = getPara("expectProfessionId", "");//期望从事职业
		String expectWorkplaceId = getPara("expectWorkplaceId", "");//期望就业地点
		int isWishTrain = getParaToInt("isWishTrain",-1);//有无培训意愿
		String expectTrainProfessionId = getPara("expectTrainProfessionId", "");//期望培训职业
		Date expectTrainTime = getParaToDate("expectTrainTime", null);//期望培训时间
		String remark = getPara("remark", "");
		int isWorking = getParaToInt("isWorking", -1);//是否正在就业
		int isTrain = getParaToInt("isTrain", -1);//是否参加了培训
		String operator = getPara("operator", "");//操作人
		
		String phone = getPara("phone" ,"");
		String medicalInsuranceId = getPara("medicalInsuranceId","");//医疗保险

		// 参数组装检查
		checkMap.clear();
		checkMap.put("villagerId", villagerId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.updateFamilyMember(villagerId,familyId,name,sexId,IDnumber,birthday,raceId,relationOfFamilyId,
				schoolStatusId,educationalId,maritalStatusId,healthConditionId,laborId,laborTime,laborCapacityId,
				soldiersDependentsId,insuranceId,inOutStatusId,isWishEmployment,expectProfessionId,expectWorkplaceId,
				isWishTrain,expectTrainProfessionId,expectTrainTime,remark,isWorking,isTrain,operator,phone,medicalInsuranceId);

		renderJson(resultMap);
		return;

	}
	
	/**
	 * 增加家庭成员
	 */
	public void add_familyVillager() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String familyId = getPara("familyId");// 家庭ID
		
		String name = getPara("name" ,"");
		String sexId = getPara("sexId" ,"");
		String IDnumber = getPara("IDnumber" ,"");//
		Date birthday = getParaToDate("birthday", null);//
		String raceId = getPara("raceId" ,"");//
		String relationOfFamilyId = getPara("relationOfHouseholderId" ,"");// 与户主的关系
		String schoolStatusId = getPara("schoolStatusId" ,"");// 在校状况
		String educationalId = getPara("educationalId" ,"");//文化程度
		String maritalStatusId = getPara("maritalStatusId" ,"");// 婚姻状况
		String healthConditionId = getPara("healthConditionId" ,"");// 健康状况
		String laborId = getPara("laborStatusId" ,"");//务工状况
		String laborTime = getPara("laborTime" ,"");//务工时间
		String laborCapacityId = getPara("laborCapacityId" ,"");//劳动能力
		String soldiersDependentsId = getPara("soldierDependant" ,"");//军属
		String insuranceId = getPara("insurance" ,"");//养老保险
		String inOutStatusId = getPara("inOutStatusId" ,"");//迁入迁出状态
	
		int isWishEmployment = getParaToInt("isWishEmployment",-1);//有无就业意愿
		String expectProfessionId = getPara("expectProfessionId" ,"");//期望从事职业
		String expectWorkplaceId = getPara("expectWorkplaceId" ,"");//期望就业地点
		int isWishTrain = getParaToInt("isWishTrain",-1);//有无培训意愿
		String expectTrainProfessionId = getPara("expectTrainProfessionId" ,"");//期望培训职业
		Date expectTrainTime = getParaToDate("expectTrainTime" ,null);//期望培训时间
		String remark = getPara("remark" ,"");
		int isWorking = getParaToInt("isWorking",-1);//是否正在就业
		int isTrain = getParaToInt("isTrain",-1);//是否参加了培训
		String writer = getPara("writer" ,"");//录入人
		
		String phone = getPara("phone" ,"");
		String medicalInsuranceId = getPara("medicalInsurance","");//医疗保险
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);
		//checkMap.put("relationOfFamilyId", relationOfFamilyId);
		checkMap.put("name", name);
		//checkMap.put("sexId", sexId);
		//checkMap.put("IDnumber", IDnumber);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.addFamilyVillager(familyId,name,sexId,IDnumber,birthday,raceId,relationOfFamilyId,
				schoolStatusId,educationalId,maritalStatusId,healthConditionId,laborId,laborTime,laborCapacityId,
				soldiersDependentsId,insuranceId,inOutStatusId,isWishEmployment,expectProfessionId,expectWorkplaceId,
				isWishTrain,expectTrainProfessionId,expectTrainTime,remark,isWorking,isTrain,writer,phone,medicalInsuranceId);

		renderJson(resultMap);
		return;
	}

	/**
	 * 删除家庭成员
	 */
	public void del_familymember() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String familymemberId = getPara("familymemberId");// 家庭成员id
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familymemberId", familymemberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.delFamilymember(familymemberId);

		renderJson(resultMap);
		return;
	}

	/**
	 * 增加帮扶领导基本信息
	 */
	public void add_leader() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String leaderId = getPara("leaderId");
		String name = getPara("name");
		String orgId = getPara("orgId");//现任单位，所属单位
		String levelId = getPara("levelId");
		String IDnumber = getPara("IDnumber");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		String educationalId = getPara("educationalId");
		String raceId = getPara("raceId");
		String politicalStatusId = getPara("politicalStatusId");
		String remark = getPara("remark");
		
		Date birthday = getParaToDate("birthday", null);
		String writer = getPara("writer");//录入人
		
		String helperTypeId = getPara("helperTypeId");
		
		//String departmentId = getPara("departmentId");
		//String subjectId = getPara("subjectId");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);
		//checkMap.put("name", name);
		//checkMap.put("orgId", orgId);
		//checkMap.put("levelId", levelId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.addLeader(leaderId, name, birthday, sexId, politicalStatusId, educationalId, raceId,
				levelId, phone,IDnumber,orgId,remark,writer,helperTypeId);
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加帮扶领导与贫困村关系
	 */
	public void add_leaderVillageRelation() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//##########################PRD
		String leaderId = getPara("leaderId");// 会有多个
		String regionId = getPara("regionId");//定点帮扶村
		String povertyTypeId = getPara("povertyTypeId");//贫困类别
		//String groupName = getPara("groupName");// 组名
		//String memberId = getPara("memberId");// 指挥部定点联系队员
		//String remark = getPara("remark");//备注
		
		//String postId = getPara("subjectId");// 现任单位
		//Integer groupCount = getParaToInt("groupCount");
		//Integer count = getParaToInt("count");// 贫困户数
		//Integer population = getParaToInt("population");// 贫困人口
		
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);
		checkMap.put("regionId", regionId);
		checkMap.put("povertyTypeId", povertyTypeId);
		// checkMap.put("educationalId", educationalId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		//resultMap = arnoldService.addLeaderVillageRelation(leaderId, regionId, povertyTypeId, memberId,groupName,remark);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 分页查看帮扶领导
	 */
	public void page_leader() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数

		loggerDebugRequestUrl();

		//多条件查询用
		//String villageId = getPara("villageId");//村id
		
		resultMap = arnoldService.pageLeader(pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}

	/**
	 * 分页查询定点帮扶村
	 */
	public void page_designatedHelpingVillage() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		//多条件查询用
		//String villageId = getPara("villageId");//村id
		
		/*
		 * AND tlrr.regionId = '' AND tl.helperTypeId = '' AND tri.povertyTypeId = '' AND tri.assignDate = '';
		 */
		//多条件查询
		String villageId = getPara("villageId");
		String helperTypeId = getPara("helperTypeId");
		String povertyTypeId = getPara("povertyTypeId");
//		String assignDateStr = StringUtils.isEmpty(getPara("assignDate"))?null:getPara("assignDate");
		
		List<QueryUtil> queryList=new ArrayList<>();
		queryList.add(new QueryUtil("tri.regionId", villageId, "="));
		queryList.add(new QueryUtil("tri.helperTypeId", helperTypeId, "="));
		queryList.add(new QueryUtil("tri.povertyTypeId", povertyTypeId, "="));
		QueryBaseUtil queryBaseUtil=new QueryBaseUtil(queryList,getPara("key"),"name","phone");
		
		resultMap = arnoldService.pageDesignatedHelpingVillage(pageNumber, pageSize, queryBaseUtil);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 定点帮扶村-->贫困户列表
	 */
	public void page_family_list(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String regionId = getPara("regionId");//村Id
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		//校验必填
		checkMap.clear();
		checkMap.put("regionId", regionId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageFamilyList(regionId, pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}

	/**
	 * 定点帮扶村-->贫困户列表
	 */
	public void page_family_list1(){
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String regionId = getPara("regionId");//村Id
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数

		//校验必填
		checkMap.clear();
		checkMap.put("regionId", regionId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.pageFamilyList1(regionId, pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 定点帮扶村-->联系队员列表 
	 */
	public void page_contact_list(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String regionId = getPara("regionId");//村Id
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		//校验必填
		checkMap.clear();
		checkMap.put("regionId", regionId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = memberService.pageContactList(regionId, pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 更新帮扶领导 基本信息
	 */
	public void update_leader() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String leaderId = getPara("leaderId");
		String name = getPara("name");
		String orgId = getPara("orgId");//现任单位
		String levelId = getPara("levelId");
		String IDnumber = getPara("IDnumber");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		String educationalId = getPara("educationalId");
		String raceId = getPara("raceId");
		String politicalStatusId = getPara("politicalStatusId");
		String remark = getPara("remark");
		
		Date birthday = getParaToDate("birthday", null);
		String operator = getPara("writer");//操作人

		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.updateLeader(leaderId, name, birthday, sexId, politicalStatusId, educationalId,
				raceId, levelId, phone,orgId,IDnumber,remark,operator);

		renderJson(resultMap);
		return;
	}

	/**
	 * 删除帮扶领导
	 */
	public void del_leader() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String leaderId = getPara("leaderId");// 家庭成员id
		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.delLeader(leaderId);

		renderJson(resultMap);
		return;
	}

	/**
	 * 扶贫人员 禁启用
	 */
	public void forbid_member() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String memberId = getPara("memberId");// 扶贫人员id
		Integer isValid = getParaToInt("isValid", 0);//1禁用，0正常

		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.forbidMember(memberId, isValid);

		renderJson(resultMap);
		return;
	}

	/**
	 * 帮扶领导 禁启用
	 */
	public void forbid_leader() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String leaderId = getPara("leaderId");// 帮扶领导id
		Integer isValid = getParaToInt("isValid", 0);// 0表示启用，1表示禁用
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.forbidLeader(leaderId, isValid);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看扶贫人员表彰情况
	 */
	public void page_memeberCommend() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageMemeberCommend(memberId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 扶贫人员帮扶成效
	 * 
	 */
	public void page_memeberEffect() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageMemeberEffect(memberId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 扶贫人员的走访记录
	 * 
	 * -1
	 * 
	 * 走访记录调用hades，不需要了
	 */
	public void page_memberVisit() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		//
		resultMap = arnoldService.pageMemberVisit(memberId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看扶贫人员的帮扶就业
	 */
	public void page_memberEmployment() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageMemberEmployment(memberId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 扶贫人员的帮扶培训
	 */
	public void page_memberTrain() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageMemberTrain(memberId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * @Author PanChangGui
	 * @Time 2017年10月8日 下午2:38:01
	 * @Description 扶贫人员帮扶教育
	 */
	public void page_memberEdu() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageMemberEdu(memberId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * @Author PanChangGui
	 * @Time 2017年10月6日 下午3:33:00
	 * @Description 查询扶贫人员帮扶产业
	 */
	public void page_industries() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		Map<String, String> chooseParamMap = new HashMap<String, String>();
		chooseParamMap.put("plantTypeId", getPara("plantTypeId", "50770539-7dc8-4338-8573-e02de79ac1c0"));//种植
		chooseParamMap.put("breedTypeId", getPara("breedTypeId", "f987e100-8a75-4404-a39f-050f69fe09f5"));//养殖
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageIndustries(memberId, pageNumber, pageSize, chooseParamMap);

		renderJson(resultMap);
		return;
	}
	
	
	/**
	 * 更新贫困户贫困属性
	 */
	public void update_familyPovertyproperty() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		String ralationTypeId = getPara("ralationTypeId","");//贫困状态
		String familyPropertyId = getPara("familyPropertyId","");//贫困户属性
		Date statusTime = getParaToDate("statusTime", null);//贫困/脱贫/返贫日期
		
		String url = getPara("url","");
		String remark = getPara("remark","");
		String createUserId = getPara("createUserId","");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);
		//checkMap.put("ralationTypeId", ralationTypeId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.updateFamilyPovertyproperty(familyId,ralationTypeId,url,remark,createUserId,familyPropertyId,statusTime);

		renderJson(resultMap);
		return;
	}	
	
	/**
	 * 查看贫困户异地搬迁
	 */
	public void find_familyRemoval() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.findFamilyRemoval(familyId);
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * @Author PanChangGui
	 * @Time 2017年10月2日 上午10:23:07
	 * @Description 
	 */
	public void update_familyRemoval() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		
		int isMoveFamily = getParaToInt("isMoveFamily", -1);
		String removalTypeId = getPara("removalTypeId", "");
		String outRegion = getPara("outRegion", "");
		String toRegion = getPara("toRegion", "");
		String resettlementWayId = getPara("resettlementWayId", "");
		String difficult = getPara("difficult", "");
		Date stayInTime = getParaToDate("stayInTime", null);
		int isStayIn = getParaToInt("isStayIn", -1);
		int isMoval = getParaToInt("isMoval", -1);
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.updateFamilyRemoval(familyId,isMoveFamily,removalTypeId,outRegion,toRegion,
				resettlementWayId,difficult,stayInTime,isStayIn,isMoval);
		
		renderJson(resultMap);
		return;
	}
	
	
	/**
	 * 查看贫困户挂帮责任人
	 */
	public void page_familyResponsible() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageFamilyResponsible(familyId,pageNumber,pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看贫困户收入分析
	 * 
	 * 0
	 * 收入分析属于统计
	 */
	public void page_familyIncome() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageFamilyIncome(familyId,pageNumber,pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 贫困户管理-->收入分析
	 */
	public void page_static_family_income() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		String year = getPara("year");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageStaticFamilyIncome(familyId,pageNumber,pageSize,year);

		renderJson(resultMap);
		return;
	}
		
	/**
	 * 查看贫困户展业培育发展
	 */
	public void page_familyIndustryDevelopment() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageFamilyIndustryDevelopment(familyId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看贫困户被走访记录
	 * 
	 * -1
	 * 
	 * 走访记录走hades，不需要了
	 */
	public void page_familyVisited() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageFamilyVisited(familyId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看贫困户脱贫返贫记录
	 * 
	 */
	public void page_familyPovertyPropertyRecord() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageFamilyPovertyPropertyRecord(familyId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看贫困户预警记录
	 * 
	 * bangming 表还没建 
	 * 
	 * ？
	 * 
	 */
	public void page_familyWarningRecord() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageFamilyWarningRecord(familyId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看帮扶领导走访记录
	 * 
	 * -1
	 * 
	 * 走访记录走hades，不需要了
	 */
	public void page_leaderInterviewRecord() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String leaderId = getPara("leaderId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageLeaderInterviewRecord(leaderId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看帮扶领导成员信息
	 * 
	 * -1
	 * PRD改动，接口去掉了
	 */
	public void page_leaderMemberInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//String leaderId = getPara("leaderId");//帮扶领导会有多个，后面要改
		
		String villageId = getPara("villageId");//贫困村id
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("villageId", villageId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageLeaderMemberInfo(villageId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加领导走访记录
	 * 
	 * -1
	 * 
	 * 走hades
	 */
	public void add_leaderInterviewRecord() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String leaderId = getPara("leaderId");
		String theme = getPara("theme");
		String data = getPara("data");
		String villagerId = getPara("villagerId");
		String regionId = getPara("regionId");
		String url = getPara("url");
		String detail = getPara("detail");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addLeaderInterviewRecord(leaderId,theme,data,villagerId,regionId,url,detail);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加领导成员信息
	 */
	public void add_leaderMemberInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String leaderId = getPara("leaderId");
		String name = getPara("name");
		String subjectId = getPara("subjectId");//单位科室
		String levelId = getPara("levelId");
		String IDnumber = getPara("IDnumber");
		String birthday = getPara("birthday");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		String educationalId = getPara("educationalId");
		String raceId = getPara("raceId");
		String politicalStatusId = getPara("politicalStatusId");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addLeaderMemberInfo(leaderId,name,subjectId,levelId,IDnumber,birthday,sexId,
				phone,educationalId,raceId,politicalStatusId);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 扶贫人员 增加帮扶家庭
	 */
	public void add_memberFamily() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId", "");
		String familyId = getPara("familyId", "");
		String helpResult = getPara("helpResult", "");//帮扶结果
		Date startTime = getParaToDate("startTime", null);//帮扶开始时间
		Date endTime = getParaToDate("endTime", null);//帮扶结束时间
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);
		checkMap.put("familyId", familyId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addMemberFamily(memberId,familyId,
				helpResult,startTime,endTime);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加扶贫人员表彰情况
	 */
	public void add_memberCommend() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		String awardName = getPara("awardName");//奖项名称
		String commendLevelId = getPara("commendLevel");//表彰级别
		String commendObjectId = getPara("commendObj");//表彰对象
		Date commendDate = getParaToDate("commendDate", null);//表彰日期
		String remark = getPara("remark");//备注
		String creator = getPara("userId");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addMemberCommend(memberId,awardName,commendLevelId,commendObjectId,commendDate,remark,creator);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加扶贫人员走访记录
	 * 
	 * -1
	 * 
	 * 走hades
	 */
	public void add_memberInterviewRecord() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		String theme = getPara("theme");//主题
		String data = getPara("data");//走访时间
		String villagerId = getPara("villagerId");//走访人
		String regionId = getPara("regionId");//走访地
		String url = getPara("url");
		String remark = getPara("remark");
		
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addMemberInterviewRecord(memberId,theme,data,villagerId,regionId,url,remark);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加扶贫人员帮扶就业
	 */
	public void add_memberEmployment() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		String name = getPara("name");//姓名
		String IDnumber = getPara("IDnumber");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		String number = getPara("number");
		String regionId = getPara("regionId");
		String postId = getPara("postId");
		String typeId = getPara("typeId");
		String companyId = getPara("companyId");
		String monthlyIncome = getPara("monthlyIncome");
		String isWorking = getPara("isWorking");
		String workTime = getPara("workTime");
		
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addMemberEmployment(memberId,name,IDnumber,sexId,phone,number,regionId,
				postId,typeId,companyId,monthlyIncome,isWorking,workTime);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加扶贫人员帮扶培训
	 */
	public void add_memberTrain() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		String name = getPara("name");//姓名
		String IDnumber = getPara("IDnumber");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		String number = getPara("number");
		String regionId = getPara("regionId");
		
		String trainTheme = getPara("trainTheme");//培训主题，培训名称
		String trainPost = getPara("trainPost");//培训岗位
		String trainContent = getPara("trainContent");//培训内容
		String startTime = getPara("startTime");
		String endTime = getPara("isWorking");
				
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addMemberTrain(memberId,name,IDnumber,sexId,phone,number,regionId,
				trainTheme,trainPost,trainContent,startTime,endTime);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加扶贫人员帮扶培训
	 */
	public void add_memberEffect() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String memberId = getPara("memberId");
		String name = getPara("name");//姓名
		String propertyId = getPara("propertyId");//贫困属性
		String burdenId = getPara("burdenId");//致贫原因
		String regionId = getPara("regionId");//所在地
		String measureId = getPara("measureId");//帮扶措施
		String income = getPara("income");//平均收入
		int  isStandards = getParaToInt("isStandards");//是否达标，一达标两不愁三保障
		String date = getPara("date");//帮扶日期
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("memberId", memberId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addMemberEffect(memberId,name,propertyId,burdenId,regionId,measureId,
				income,isStandards,date);

		renderJson(resultMap);
		return;
	}
		
	/**
	 * 查看定点帮扶村基本信息
	 */
	public void find_designatedHelpingVillageInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String villageId = getPara("villageId");
		// 参数组装检查
		checkMap.clear();
		checkMap.put("villageId", villageId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.findDesignatedHelpingVillageInfo(villageId);
		renderJson(resultMap);
		return;
	}

	/**
	 * 查看定点帮扶村基本信息
	 */
	public void find_designatedHelpingVillageInfo1() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String villageId = getPara("villageId");
		// 参数组装检查
		checkMap.clear();
		checkMap.put("villageId", villageId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.findDesignatedHelpingVillageInfo1(villageId);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看领导基本信息
	 */
	public void find_leader() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String leaderId = getPara("leaderId");
		// 参数组装检查
		checkMap.clear();
		checkMap.put("leaderId", leaderId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.findLeader(leaderId);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查找所有领导
	 * id,name,orgId,levelId
	 * 可根据扶贫人类别获取不同类别的领导信息
	 */
	public void find_allLeader() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String helperTypeId = getPara("helperTypeId");
		
		resultMap = arnoldService.findAllLeader(helperTypeId);
		renderJson(resultMap);
		return;
	}
		
	/**
	 * 增加贫困户帮扶责任人
	 */
	public void add_familyResponsible() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		String memberId = getPara("memberId");//帮扶责任人
		String IDnumber = getPara("IDnumber");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		String politicalStatusId = getPara("politicalStatusId");
		String accreditPostId = getPara("accreditPostId");//派驻期间职务
		String  accreditPlaceId = getPara("accreditPlaceId");
		String departmentId = getPara("departmentId");//帮扶单位
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addFamilyResponsible(familyId,memberId,IDnumber,sexId,phone,politicalStatusId,
				accreditPostId,accreditPlaceId,departmentId, startTime, endTime);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * @Time 2017年10月9日 下午3:48:41
	 * @Description 扶贫人员所帮扶的家庭的关系
	 */
	public void add_familyRelationByMerber() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		String memberId = getPara("memberId");//帮扶责任人
		Date time = getParaToDate("time", null);//帮扶开始时间
		Date endTime = getParaToDate("endTime", null);//帮扶结束时间
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);
		checkMap.put("memberId", memberId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		/*String[] fams = familyId.split(",");
		for(String famId:fams){
			if (famId.trim().length() == 0) {
				continue;
			}
			resultMap = arnoldService.addFamilyResponsible(famId,memberId,null,null,null,null,
					null,null,null, null, null);

			//FamilyService familyService = new FamilyService();
			//familyService.updateFamily(famId, null, null, endTime, time, null, null, null, -1, endTime);
		}*/
		
		resultMap = arnoldService.addMemberFamily(memberId,familyId,
				null,time,endTime);
		
		renderJson(resultMap);
		return;
	}
	
	
	/**
	 * 增加结对帮扶支部
	 * 
	 */
	public void add_helpBranch() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String name = getPara("name");//党支部
		String regionid = getPara("regionid");//对应贫困村
		String address = getPara("address");//地址
		String writer = getPara("writer");//录入人
		String operator = getPara("nowWriter");//操作人
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("name", name);
		checkMap.put("regionid", regionid);
		checkMap.put("address", address);
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addHelpBranch(name, regionid, address, writer, operator);

		renderJson(resultMap);
		return;
	}

	public void update_helpBranch() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String id = getPara("id");//党支部
		String name = getPara("name");//党支部
		String regionid = getPara("regionid");//对应贫困村
		String address = getPara("address");//地址
		String operator = getPara("nowWriter");//操作人
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("id", id);
		checkMap.put("name", name);
		checkMap.put("regionid", regionid);
		checkMap.put("address", address);
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.updateHelpBranch(id, name, regionid, address, operator);
		
		renderJson(resultMap);
		return;
	}
	
	
	/**
	 * 查看驻村点
	 * 
	 * -1
	 */
	public void page_stayingVillage() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		String keyword = getPara("keyword");//
		
		resultMap = arnoldService.pageStayingVillage(pageNumber, pageSize,keyword);

		renderJson(resultMap);
		return;
	}

	public void q_stayingVillage() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String id = getPara("id");//

		resultMap = arnoldService.q_stayingVillage(id);

		renderJson(resultMap);
		return;
	}


	public void del_StayingVillage() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String id = getPara("id");//

		resultMap = arnoldService.delStayingVillage(id);

		renderJson(resultMap);
		return;
	}

	
	/**
	 * 增加驻村点
	 * 
	 * -1
	 */
	public void add_stayingVillage() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String villageId = getPara("villageId");//对应贫困村
		String regionId = getPara("regionId");//地址
		int poulationCount = getParaToInt("poulationCount");//人口总数
		int partyMemberCount = getParaToInt("partyMemberCount");//党员数量	
		String percent = getPara("percent");//贫困发生率 ,10%
		int fiveGuaranteedCount = getParaToInt("fiveGuaranteedCount");//五保户
		String poorPopulation = getPara("poorPopulation");//贫困人口总数
		int lowIncomeCount = getParaToInt("lowIncomeCount");//低保户数				
		String removalPlan = getPara("removalPlan");//本年异地搬迁计划
		String removalTime = getPara("removalTime");//计划搬迁时间	
		String houseTransformPlan = getPara("houseTransformPlan");//危房改造计划	
		String transformTime = getPara("transformTime");//计划改造时间	
		int isTapWater = getParaToInt("isTapWater");//是否通自来水	
		int isSeriousPatient = getParaToInt("isSeriousPatient");//是否有疾病人员	
		int isPrimarySchool = getParaToInt("isPrimarySchool");//是否有小学	
		int isVillageRoad = getParaToInt("isVillageRoad");//通村路是否硬化
		int isGroupRoad = getParaToInt("partyMemberCount");//通组路是否硬化
		int childDropoutCount = getParaToInt("childDropoutCount");//辍学儿童数量
		Double averageIncome = getParaToDouble("averageIncome");//上年度平均收入	
		float economyScale = getParaToInt("economyScale");//村集体经济规模	
		float perCultivatedArea = getParaToInt("perCultivatedArea");//人均耕地面积
		float perWoodland = getParaToInt("perWoodland");//人均林地
		String difficulty = getPara("difficulty");//目前的困难及原因
			
		String informant = getPara("informant");//填报人
		String writer = getPara("writer");//录入人
		String operator = getPara("operator");//操作人
			
		// 参数组装检查
		checkMap.clear();
		checkMap.put("villageId", villageId);
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		/*resultMap = arnoldService.addStayingVillage(villageId,regionId,poulationCount,partyMemberCount,percent,fiveGuaranteedCount,
				poorPopulation,lowIncomeCount,removalPlan,removalTime,houseTransformPlan,transformTime,isTapWater,
				isSeriousPatient,isPrimarySchool,isVillageRoad,isGroupRoad,childDropoutCount,averageIncome,economyScale,
				perCultivatedArea,perWoodland,difficulty,informant,writer,operator);*/

		renderJson(resultMap);
		return;
	}

	public void add_property_village() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String villageId = getPara("regionId");//对应贫困村
		String regionId = getPara("address");//地址
		String poulationCount = getPara("rkzs");//人口总数
		String partyMemberCount = getPara("dysl");//党员数量
		String percent = getPara("pkfsl");//贫困发生率 ,10%
		String fiveGuaranteedCount = getPara("wbhrs");//五保户
		String poorPopulation = getPara("pkrksl");//贫困人口总数
		String lowIncomeCount = getPara("dbhrs");//低保户数
		String removalPlan = getPara("bndydbqjh");//本年异地搬迁计划
		Date removalTime = getParaToDate("jhbqsj",new Date());//计划搬迁时间
		String houseTransformPlan = getPara("whgzjh");//危房改造计划
		Date transformTime = getParaToDate("transformTime",new Date());//计划改造时间
		String isTapWater = getPara("sftzls");//是否通自来水
		String isSeriousPatient = getPara("sfydbwddjzry");//是否有疾病人员
		String isPrimarySchool = getPara("sfyxx");//是否有小学
		String isVillageRoad = getPara("tclsfyh");//通村路是否硬化
		String isGroupRoad = getPara("tzlsfyh");//通组路是否硬化
		String childDropoutCount = getPara("cxetsl");//辍学儿童数量
		String averageIncome = getPara("sndcmpjsr");//上年度平均收入
		String economyScale = getPara("cjtjjgm");//村集体经济规模
		String perCultivatedArea = getPara("rjgdmj");//人均耕地面积
		String perWoodland = getPara("rjld");//人均林地
		String difficulty = getPara("mqwt");//目前的困难及原因

		String informant = getPara("writer");//填报人
		String writer = getPara("writer");//录入人
		String operator = getPara("writer");//操作人

		// 参数组装检查
		checkMap.clear();
		checkMap.put("villageId", villageId);
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		resultMap = arnoldService.addStayingVillage(villageId,regionId,poulationCount,partyMemberCount,percent,fiveGuaranteedCount,
				poorPopulation,lowIncomeCount,removalPlan,removalTime,houseTransformPlan,transformTime,isTapWater,
				isSeriousPatient,isPrimarySchool,isVillageRoad,isGroupRoad,childDropoutCount,averageIncome,economyScale,
				perCultivatedArea,perWoodland,difficulty,informant,writer,operator);

		renderJson(resultMap);
		return;
	}



	
	/**
	 * 查看驻村点详情
	 */
	public void page_stayingVillageDetail() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		String regionId = getPara("regionId");//驻村点

		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("regionId", regionId);
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.pageStayingVillageDetail(regionId, pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	
	
	/**
	 * 查看政策文件
	 */
	public void page_policyDocument() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		//多条件查询？
		
		
		resultMap = arnoldService.pagePolicyDocument(pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加政策文件
	 */
	public void add_policyDocument() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String documentTypeId = getPara("documentTypeId");//文件类型
		String headline = getPara("headline");//标题
		String url = getPara("url");//上传文件的url
		String remark = getPara("remark");
		String operator = getPara("operator");
		
		
		resultMap = arnoldService.addPolicyDocument(documentTypeId, headline,url,remark,operator);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看调研报告
	 */
	public void page_researchReport() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		//多条件查询？
		
		
		resultMap = arnoldService.pageResearchReport(pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加调研报告
	 */
	public void add_researchReport() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String documentTypeId = getPara("documentTypeId");//文件类型
		String headline = getPara("headline");//标题
		String url = getPara("url");//上传文件的url
		String remark = getPara("remark");
		String operator = getPara("operator");
		
		
		resultMap = arnoldService.addResearchReport(documentTypeId, headline,url,remark,operator);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看建议和意见
	 */
	public void page_suggest() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		//多条件查询？
		
		
		resultMap = arnoldService.pageSuggest(pageNumber, pageSize);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加建议和意见
	 */
	public void add_suggest() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String suggestTypeId = getPara("documentTypeId");//类型
		String headline = getPara("headline");//标题
		String remark = getPara("remark");
		String operator = getPara("operator");		
		
		resultMap = arnoldService.addSuggest(suggestTypeId,headline,remark,operator);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 更新贫困户生活条件
	 */
	public void update_familyLiveCondition() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String familyId = getPara("familyId");//家庭Id
		String cultivatedArea = getPara("cultivatedArea");//耕地面积
		String effectiveIrrigationArea = getPara("effectiveIrrigationArea");//有效灌溉面积
		String field = getPara("field");//田面积
		String land = getPara("land");//土面积
		String forestryArea = getPara("forestryArea");//林地面积
		String backForestryArea = getPara("backForestryArea");//退耕还林面积
		String fruitTreeArea = getPara("fruitTreeArea");//林果地面积
		String grassArea = getPara("grassArea");//草地面积
		String waterArea = getPara("waterArea");//水面面积
		
		String economicArea = getPara("economicArea");//商品经济作物面积
		int isDrinkDifficulty = getParaToInt("isDrinkDifficulty", -1);//是否饮水困难
		int isDrinkSafety = getParaToInt("isDrinkSafety", -1);//是否饮水安全
		String drinkConditionId = getPara("drinkConditionId", "");//饮水情况类型
		int isGalvanical = getParaToInt("isGalvanical", -1);//是否通电
		int isTelevision = getParaToInt("isTelevision", -1);//是否通电视
		String consumerGoodsCondition = getPara("consumerGoodsCondition", "");//拥有的消费品情况
		String trunklineDistance = getPara("trunklineDistance");//离村主干路距离
		String nearestMarketDistance = getPara("nearestMarketDistance");//离最近集市的距离
		String registerRoadTypeId = getPara("registerRoadTypeId", "");//入户路类型
		int roadCondition = getParaToInt("roadCondition", -1);//通路情况，0正常通路，1不通路
		String buildHouseYear = getPara("buildHouseYear", "");//建房年份
		String housingArea = getPara("housingArea");//住房面积
		int hasHouse = getParaToInt("hasHouse", -1);//是否有住房
		String houseStructureId = getPara("houseStructureId", "");//房屋主要结构类型
		int isDangerousBuilding = getParaToInt("isDangerousBuilding", -1);//是否危房
		String fuelsTypeId = getPara("fuelsTypeId", "");//主要燃料类型
		int isSanitaryToilet = getParaToInt("isSanitaryToilet", -1);//有无卫生厕所
		String productionArea = getPara("productionArea");//生产用户面积
		String liveArea = getPara("liveArea");//生活用户面积
		String longitude = getPara("longitude", "");//经度
		String latitude = getPara("latitude", "");//纬度
		String height = getPara("height", "");//高度
		String operator = getPara("operator", "");//操作人
		
		//下面是生产条件
		String outstandingLoan = getPara("outstandingLoan", "");//未偿还贷款
		String operatingIncome = getPara("operatingIncome", "");//全家生产经营性收入
		String receivedLowGold = getPara("receivedLowGold", "");//领取的低保金
		String perCapitaIncome = getPara("perCapitaIncome", "");//家庭年人均收入
		String annualNetIncome = getPara("annualNetIncome", "");//家庭年纯收入
		String annualIncome = getPara("annualIncome", "");//家庭年收入
		String receivedEndowmentInsurance = getPara("receivedEndowmentInsurance", "");//领取的养老保险金
		String allSubsidization = getPara("allSubsidization", "");//各类补贴
		String claimMedicalExpenses = getPara("claimMedicalExpenses", "");//新农合报销的医疗费
		String wageIncome = getPara("wageIncome", "");//全家工资性收入
		String productiveOutlays = getPara("productiveOutlays", "");//全家生产性支出
		String medicalAid = getPara("medicalAid", "");//医疗救助金
		String propertyIncome = getPara("propertyIncome", "");//财产性收入
		String receivedFamilyPlanningMoney = getPara("receivedFamilyPlanningMoney", "");//领取的计划生育救助金
		String ecologicalCompensation = getPara("ecologicalCompensation", "");//生态补偿金

		Double trainingSubsidy = getParaToDouble("trainingSubsidy", 0d);//培训补助（元）
		Double trainingExpenditure = getParaToDouble("trainingExpenditure", 0d);//培训支出（元）
		Double cashPolicySubsidies = getParaToDouble("cashPolicySubsidies", 0d);//现金政策性惠农补贴（粮食直补等生产综合补贴）（元）
		Double educationalExpenditure = getParaToDouble("educationalExpenditure", 0d);//教育支出（元）
		Double educationalSubsidy = getParaToDouble("educationalSubsidy", 0d);//教育补助（元）
		Double medicalExpenditure = getParaToDouble("medicalExpenditure", 0d);//医疗费用支出（元）
		
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
			
		resultMap = arnoldService.updateFamilyLivecondition(familyId,cultivatedArea,effectiveIrrigationArea,field,land,forestryArea,
				backForestryArea,fruitTreeArea,grassArea,waterArea,economicArea,isDrinkDifficulty,isDrinkSafety,drinkConditionId,
				isGalvanical,isTelevision,consumerGoodsCondition,trunklineDistance,nearestMarketDistance,registerRoadTypeId,
				roadCondition,buildHouseYear,housingArea,hasHouse,houseStructureId,isDangerousBuilding,fuelsTypeId,isSanitaryToilet,
				productionArea,liveArea,longitude,latitude,height,operator,
				outstandingLoan,operatingIncome,receivedLowGold,perCapitaIncome,annualNetIncome,annualIncome,receivedEndowmentInsurance,
				allSubsidization,claimMedicalExpenses,wageIncome,productiveOutlays,medicalAid,propertyIncome,receivedFamilyPlanningMoney,
				ecologicalCompensation,trainingSubsidy,trainingExpenditure,cashPolicySubsidies,educationalExpenditure,educationalSubsidy,
				medicalExpenditure);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看贫困户生活条件
	 */
	public void find_familyLiveCondition() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.findFamilyLiveCondition(familyId);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 更新后台人员信息
	 */
	public void update_backstageCrew() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String userId = getPara("userId");
		String username = getPara("username");
		String name = getPara("name");
		String sexId = getPara("sexId");
		String phone = getPara("phone");
		String roleId = getPara("roleId");
		String orgId = getPara("orgId");
		String regionId = getPara("regionId");
		Date lastLoginTime = getParaToDate("lastLoginTime", null);
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("userId", userId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.updateBackstageCrew(userId,username,name,sexId,phone,
				roleId,orgId,regionId,lastLoginTime);
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 分页查看后台人员
	 */
	public void page_backstageCrew() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		
		//多条件查询？
		
//		resultMap = arnoldService.pageBackstageCrew(pageNumber, pageSize);
		
		//lzy添加参数参数
		resultMap = arnoldService.pageBackstageCrew(pageNumber, pageSize, 
				getRequestBean(RequestBackStageCrew.class, "loginTime", "keyWord"));
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 更新领导和帮扶地区（村，组）的关系
	 */
	public void update_leaderRegionRelation() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String regionId = getPara("regionId");//区域id，村id	
		String povertyTypeId = getPara("povertyTypeId");//贫困类别
		
		String remark = getPara("remark");
		String shortDescription = getPara("shortDescription");
		String operator = getPara("operator");
		
		String leaderId = getPara("leaderId");//领导id，可选多个
		Date assignDate = getParaToDate("assignDate", null);//指派日期
		Date assignEndDate = getParaToDate("assignEndDate", null);//指派结束日期
		String memberId = getPara("memberIds");//指挥部定点联系对队员，即扶贫人员，可选多个
		
		
		String firstSecretaryMemberId = getPara("firstSecretaryMemberId");//第一书记ID
		
		Map<String,String> chooseParamMap = new HashMap<String, String>();
		chooseParamMap.put("headOfVillageCommitteeId", getPara("headOfVillageCommitteeId"));
		chooseParamMap.put("headOfVillageCommitteeName", getPara("headOfVillageCommitteeName"));
		chooseParamMap.put("villageBranchSecretaryId", getPara("villageBranchSecretaryId"));
		chooseParamMap.put("villageBranchSecretaryName", getPara("villageBranchSecretaryName"));
		chooseParamMap.put("poorFamilyLeaderId", getPara("poorFamilyLeaderId"));
		chooseParamMap.put("poorFamilyLeaderName", getPara("poorFamilyLeaderName"));
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("regionId", regionId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.updateLeaderRegionRelation(regionId,povertyTypeId,leaderId,assignDate,assignEndDate,
				memberId,remark,shortDescription,operator,firstSecretaryMemberId, chooseParamMap);
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * @Author PanChangGui
	 * @Email 823468425@qq.com
	 * @Time 2017年9月22日 下午2:45:55
	 * @Descriptor 查询所有地区
	 */
	public void list_allRegion() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = arnoldService.listAllRegion();
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查看地区详细信息
	 */
	public void find_regionDetailInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String regionId = getPara("regionId");//区域id
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("regionId", regionId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.findRegionInfo(regionId);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查询所有贫困村名列表
	 * 返回所有id
	 */
	public void find_villageNameList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = arnoldService.findVillageNameList();
		
		renderJson(resultMap);
		return;
	}
	
	public void findAllMember() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Member> list = Member.dao.findAll();
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 根据regionId查询其childId
	 * 
	 */
	public void find_childRegion() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String regionId = getPara("regionId");
		// 参数组装检查
		checkMap.clear();
		checkMap.put("regionId", regionId);
		
		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.findChildRegion(regionId);
		
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 查找贫困地区?????????
	 * 已不用待删除
	 */
	public void page_area() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		//Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数

		resultMap = arnoldService.getPoolRegion();
		/*if (!resultMap.get("rc").equals(0)) {
			renderJson(resultMap);
			return;
		}*/
		renderJson(resultMap);

		return;
	}
	
	/**
	 * 查看结对帮扶支部
	 * 
	 * -1
	 */
	public void page_helpBranch() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		String regionid = this.getPara("regionid",null);
		String keyWord = this.getPara("keyWord",null);
		
		resultMap = arnoldService.pageHelpBranch(pageNumber, pageSize,regionid,keyWord);

		renderJson(resultMap);
		return;
	}
	
	/**
	 * 增加行政地区
	 */
	public void add_region() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String regionId = getPara("regionId");//区域id
		String shortName = getPara("shortName");
		String fullName = getPara("fullName");
		String parentId = getPara("parentId");
		String parentName = getPara("parentName");
		int regionType = getParaToInt("regionType",-1);
		int property = getParaToInt("property",-1);
		String cityCode = getPara("cityCode");
		String adCode = getPara("adCode");
		String longitude = getPara("longitude");
		String latitude = getPara("latitude");
		
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("regionId", regionId);
		checkMap.put("parentId", parentId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}

		//必须指定region的type
		if(regionType == -1) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.REGION_Type_NULL_ERROR_CODE_STR);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.addRegion(regionId,shortName,fullName,parentId,
				parentName,regionType,property,cityCode,adCode,longitude,latitude);
			
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 更新行政地区
	 */
	public void update_region() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String regionId = getPara("regionId");//区域id
		String shortName = getPara("shortName");
		String fullName = getPara("fullName");
		String parentId = getPara("regionId");
		String parentName = getPara("regionId");
		int regionType = getParaToInt("regionType",-1);
		int property = getParaToInt("property",-1);
		String cityCode = getPara("cityCode");
		String adCode = getPara("adCode");
		String longitude = getPara("longitude");
		String latitude = getPara("latitude");
		
		// 参数组装检查
		checkMap.clear();
		checkMap.put("regionId", regionId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		//必须指定region的type
		if(regionType == -1) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.REGION_Type_NULL_ERROR_CODE_STR);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.updateRegion(regionId,shortName,fullName,parentId,
				parentName,regionType,property,cityCode,adCode,longitude,latitude);
			
		renderJson(resultMap);
		return;
	}
	
	/**
	 * 删除某个行政地区
	 */
	public void del_region() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String regionId = getPara("regionId");//区域id
		// 参数组装检查
		checkMap.clear();
		checkMap.put("regionId", regionId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.delRegion(regionId);
		
		renderJson(resultMap);
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***********************************************************
	 * 调试用的接口
	 * 有时需要修改数据库数据
	 * 
	 * *********************************************************/
	public void update_database() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		System.out.println("starting...");
		/*List<Family> list = Family.listFamily();
		for(Family model : list) {
			FamilyBurdenRelation fmodel = new FamilyBurdenRelation();
			fmodel.setId(Utils.create36UUID());
			fmodel.setFamilyId(model.getId());
			fmodel.setBurdenId(model.getAddReason());
			fmodel.save();
			
			//自动生成家庭编码
			Region region=Region.dao.findById(model.getRegionId());
			if(region!=null){
				//String numberPrefix=region.getParentName()+region.getShortName();
				String numberPrefix=region.getParentName();
				 numberPrefix= AlphabetUtil.getAllFirstLetter(numberPrefix).toUpperCase();
				String serialNumber;
				Family maxFamily=Family.findFamilyByNumberPrefix(numberPrefix);
				if(maxFamily==null){
					serialNumber = "0001";
				}
				else{
					serialNumber=maxFamily.getNumber();
					serialNumber=serialNumber.substring(serialNumber.length()- 4);
					Integer number =Integer.parseInt(serialNumber);
					serialNumber=ArnoldUtils.get4BitStrByNumber(number+1);
				}
				model.setNumber(numberPrefix+serialNumber);
				
				model.update();
				
				System.out.println("update ing...");
			}
		}*/
		
		
		/*List<Member> list = Member.dao.findAll();
		
		System.out.println("size=" + list.size());
		
		String userName=null;
		String pwd=null;
		String name = null;
		String phone = null;
		String appId = null;
		
		//appId=943b64cd-7460-49a6-9483-68c0f7d870c2
		//projectId=8f66b8c0-f6f2-4e74-bc4e-0258582a388c
		
		for(Member model : list) {
			//String id = Utils.create36UUID();
			//model.setId(id);
			
			name = model.getName();
			phone = model.getPhone();
			
			try {
				String url = ConsConfig.POSEIDON_HOST + "/iuser/add_user";
				
				Map<String, Object> dataValue = new HashMap<String, Object>();
						
				dataValue.put("userName", userName);
				dataValue.put("pwd", pwd);
				dataValue.put("realName", name);
				dataValue.put("phone", phone);
				dataValue.put("appIdP", appId);
				
				String strResult = HttpRequestService.httpPost(url, dataValue);
				
				JSONObject jsonResult = new JSONObject(strResult);
			
				String id = "";
				
				if(jsonResult.getInt("rc") == 0 || jsonResult.getInt("rc") == 10010){
					
					id = jsonResult.getString("userId");
					
				}else if(jsonResult.getInt("rc") == 10011){					
					resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ERROR_APPID);
					resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.ERROR_APPID_STR);
					renderJson(resultMap);
					return;		
				}
			} catch (HttpRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
				resultMap.put(ConstUtils.R_MSG, "查询失败！");
				renderJson(resultMap);
				return;
			}
			
			
			model.update();
			
			System.out.println("update ing...");
		}*/
		
		
		/**
		 * 根据身份证设置生日
		 */
		List<Villager> list = Villager.dao.listVillager();
		//int i = 0;
		for(Villager model : list) {
			Date bir = model.getBirthday();
			String b = null;
			String d = "1970-01-01";
			if(bir != null) b = bir.toString();
			
//			if(bir == null || b == null || b.equals(d) ) {
			if(bir == null) {
			
			String IDnumber = model.getIDnumber();
			System.out.println("IDnumber: " + IDnumber);
			Date birthday = ArnoldUtils.getBirthdayByIDnumber(IDnumber);
			
			if(model != null) model.setBirthday(birthday);
			
			model.update();
			
			System.out.println("update ing...");
			
			}
		}
		
		
		System.out.println("finish...");
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}
	
	public void page_poor_person_by_condition_json() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize");// 页显示条数
		String IDnumber = getPara("IDnumber");

		//String measureId = getPara("measureId");//帮扶措施，多个？
		//String povertyStatus = getPara("povertyStatus");//贫困状态
		
		
		resultMap = arnoldService.pagePoorPersonByCondition(pageNumber, pageSize, IDnumber);
		renderJson(resultMap);
		return;
	}

	public void page_member_by_condition_json() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize");// 页显示条数
		String IDnumber = getPara("IDnumber");
		
		resultMap = arnoldService.pageMemberByCondition(pageNumber, pageSize, IDnumber);
		renderJson(resultMap);
		return;
	}

	public void page_poor_person_by_id_json() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String id = getPara("id");
		resultMap = arnoldService.pagePoorPersonByid(id);
		renderJson(resultMap);
		return;
	}
	
	public void page_member_by_id_json() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String id = getPara("id");
		resultMap = arnoldService.pageMemberByid(id);
		renderJson(resultMap);
		return;
	}
	
	public void page_family_trainingGuide() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);
		
		resultMap = arnoldService.pageFamilyTrainingGuide(familyId,pageNumber,pageSize);
		renderJson(resultMap);
		return;
	}
	
	public void page_family_educationalGuide() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);		
		
		resultMap = arnoldService.pageFamilyEducationalGuide(familyId,pageNumber,pageSize);
		renderJson(resultMap);
		return;
	}
	
	public void page_family_employmentInformation() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String familyId = getPara("familyId");
		Integer pageNumber = getParaToInt("pageNumber", defaultNumber);// 页数
		Integer pageSize = getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);// 页显示条数
		// 参数组装检查
		checkMap.clear();
		checkMap.put("familyId", familyId);	
		
		resultMap = arnoldService.pageFamilyEmploymentInformation(familyId,pageNumber,pageSize);
		renderJson(resultMap);
		return;
	}
	
	public void page_help_info() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize",15 );
		String type = this.getPara("type",null);
		String keyWord = this.getPara("keyWord",null);
		
		ArnoldService1 service = new ArnoldService1();
		
		Page<Record> recordModel = service.pageHelpInfo(pageNumber, pageSize, type, keyWord);
		resultMap.put(ConstUtils.R_PAGE, recordModel);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, "查询成功!");
		
		renderJson(resultMap);
		return;
	}
	public void page_team_info_detail() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize",15 );
		String type = this.getPara("typeid",null);
		String createUserId = this.getPara("createUserId",null);
		String keyWord = this.getPara("keyWord",null);
		
		ArnoldService1 service = new ArnoldService1();
		
		Page<Record> recordModel = service.pageHelpInfoDetail(pageNumber, pageSize, type,createUserId, keyWord);
		resultMap.put(ConstUtils.R_PAGE, recordModel);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, "查询成功!");
		
		renderJson(resultMap);
		return;
	}
	
	public void page_team_info_by_user() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize",15 );
		String createUserId = this.getPara("createUserId",null);
		String keyWord = this.getPara("keyWord",null);
		
		ArnoldService1 service = new ArnoldService1();
		
		Page<Record> recordModel = service.pageHelpInfoDetailByUser(pageNumber, pageSize,createUserId, keyWord);
		resultMap.put(ConstUtils.R_PAGE, recordModel);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, "查询成功!");
		
		renderJson(resultMap);
		return;
	}
	
	public void add_help_info() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String type = this.getPara("orgId",null);
		String user = this.getPara("member",null);
		String title = this.getPara("title",null);
		String content = this.getPara("content",null);
		String attachment = this.getPara("fileList",null);
		ArnoldService1 service = new ArnoldService1();
		
		int result = service.addHelpInfo(type,user,title,content,attachment);
		if(result > 0){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
		}
		renderJson(resultMap);
		return;
	}
	public void update_help_info() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String id = this.getPara("id",null);
		String title = this.getPara("title",null);
		String content = this.getPara("content",null);
		String attachment = this.getPara("fileList",null);
		if (attachment.startsWith("\"")) {
			attachment = attachment.substring(1, attachment.length()-1);
		}
		ArnoldService1 service = new ArnoldService1();
		
		int result = service.updateHelpInfo(id,title,content,attachment);
		if(result > 0){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功!");
		}
		renderJson(resultMap);
		return;
	}
	public void del_help_info() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String id = this.getPara("id",null);
		ArnoldService1 service = new ArnoldService1();
		
		int result = service.delHelpInfo(id);
		if(result > 0){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
		}
		renderJson(resultMap);
		return;
	}
	
	public void query_backCreaw_by_login() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userId = this.getPara("userId",null);
		ArnoldService service = new ArnoldService();
		resultMap = service.queryBackCreawByLogin(userId);
		renderJson(resultMap);
		return;
	}
	
	/**
	 * @Author PanChangGui
	 * @Time 2017年9月27日 下午6:19:48
	 * @Description 查找regionId下所有村名
	 */
	public void list_villagerName() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String regionId = getPara("regionId");//区域id
		// 参数组装检查
		checkMap.clear();
		checkMap.put("regionId", regionId);

		String checkResult = checkPara(checkMap);
		if (!("true".equals(checkResult))) {
			resultMap.clear();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, checkResult);
			renderJson(resultMap);
			return;
		}
		
		resultMap = arnoldService.listVillagerName(regionId);
		
		renderJson(resultMap);
		return;
	}
	
	
}
