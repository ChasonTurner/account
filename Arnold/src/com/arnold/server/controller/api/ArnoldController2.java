package com.arnold.server.controller.api;

import com.arnold.server.arnoldService.ArnoldService2;
import com.arnold.server.bean.request.RequestDelPostVillagerRalationType;
import com.arnold.server.controller.BaseController;
import com.arnold.server.model.Family;
import com.arnold.server.model.FamilyEconomicHerds;
import com.arnold.server.service.api.FamilyEconomicHerdsService;
import com.arnold.server.service.api.FamilyEconomicPurchaseHappenService;
import com.arnold.server.service.api.FamilyService;
import com.arnold.server.service.api.ValigerPostHappenService;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @description TODO
 */
public class ArnoldController2 extends BaseController {

	Map<String, Object> resultMap = new HashMap<String, Object>();

	/**
	 * @Description: 分页查询就业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_employment_valliager() {

		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		int isWorking = 0;
		String typeId = this.getPara("typeId", ArnoldUtils.EMPTY_STR);
		String postId = this.getPara("postId", ArnoldUtils.EMPTY_STR);
		String keyWord = this.getPara("keyWord");

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.pageValliager(pageNumber, pageSize, typeId, postId, isWorking, keyWord);

		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 分页查询失业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */

	/**
	 * @Description: 增加就业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_employment_valliager() {

		String villagerId = this.getPara("villagerId");
		String postId = this.getPara("postId");
		String memberId = this.getPara("memberId");
		String salaryStr = this.getPara("salary");
		String userId = this.getPara("userId");
		String phone = this.getPara("phone");

		String department = this.getPara("department");
		Date postLeaveDate = this.getParaToDate("postLeaveDate", new Date());
		memberId = Utils.isBlankOrEmpty(memberId) ? userId : memberId;
		Double salary = 0.0;
		if (!Utils.isBlankOrEmpty(salaryStr)) {
			salary = Double.valueOf(salaryStr);
		}
		String jobCategory = this.getPara("jobCategory");
		String companyProject = this.getPara("companyProject");
		Double averageIncome = this.getParaToDouble("averageIncome");

		Integer isFiveFund = this.getParaToInt("isFiveFund", 0);
		Integer isEatEncase = this.getParaToInt("isEatEncase", 0);
		Integer isEat = this.getParaToInt("isEat", 0);
		Integer isEncase = this.getParaToInt("isEncase", 0);
		Integer isLunch = this.getParaToInt("isLunch", 0);
		Integer isOther = this.getParaToInt("isOther", 0);
		Boolean isEdit = this.getParaToBoolean("isEdit", false);
		String jobAdress = this.getPara("jobAddress");
		Boolean isTrain = this.getParaToBoolean("isTrain");
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(villagerId) || Utils.isBlankOrEmpty(postId) || Utils.isBlankOrEmpty(memberId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		Integer ralationType = 3;

		resultMap = service.addEmploymenValliager(villagerId, postId, memberId, ralationType, salary, averageIncome,
				companyProject, jobCategory, postLeaveDate, null, userId, isEat, isEatEncase, isEncase, isFiveFund,
				isLunch, isOther, department, isEdit, jobAdress, id, isTrain, phone);

		renderJson(resultMap);
		return;
	}
	
	public void update_employment_valliager() {

		String villagerId = this.getPara("villagerId");
		String postId = this.getPara("postId");
		String memberId = this.getPara("memberId");
		String salaryStr = this.getPara("salary");
		String userId = this.getPara("userId");
		String phone = this.getPara("phone");

		String department = this.getPara("department");
		Date postLeaveDate = this.getParaToDate("postLeaveDate", new Date());
		memberId = Utils.isBlankOrEmpty(memberId) ? userId : memberId;
		Double salary = 0.0;
		if (!Utils.isBlankOrEmpty(salaryStr)) {
			salary = Double.valueOf(salaryStr);
		}
		String jobCategory = this.getPara("jobCategory");
		String companyProject = this.getPara("companyProject");
		Double averageIncome = this.getParaToDouble("averageIncome");

		Integer isFiveFund = this.getParaToInt("isFiveFund", 0);
		Integer isEatEncase = this.getParaToInt("isEatEncase", 0);
		Integer isEat = this.getParaToInt("isEat", 0);
		Integer isEncase = this.getParaToInt("isEncase", 0);
		Integer isLunch = this.getParaToInt("isLunch", 0);
		Integer isOther = this.getParaToInt("isOther", 0);
		Boolean isEdit = this.getParaToBoolean("isEdit", false);
		String jobAdress = this.getPara("jobAddress");
		Boolean isTrain = this.getParaToBoolean("isTrain");
		String id = this.getPara("pid");

		if (Utils.isBlankOrEmpty(villagerId) || Utils.isBlankOrEmpty(postId) || Utils.isBlankOrEmpty(memberId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		Integer ralationType = 3;

		resultMap = service.updateEmploymenValliager(villagerId, postId, memberId, ralationType, salary, averageIncome,
				companyProject, jobCategory, postLeaveDate, null, userId, isEat, isEatEncase, isEncase, isFiveFund,
				isLunch, isOther, department, isEdit, jobAdress, id, isTrain, phone);

		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据村名ID查询就业记录
	 */
	public void query_villager_post_happen_by_villagerId() {
		String villagerId = this.getPara("villagerId");

		if (Utils.isBlankOrEmpty(villagerId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.queryVillagerPostHappenByVillagerId(villagerId);

		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据村id获取
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_employment_by_villagerId() {

		String villagerId = this.getPara("villagerId");

		if (Utils.isBlankOrEmpty(villagerId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.queryEmploymenValliagerByVillagerId(villagerId);

		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 改变为失业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void change_employment_no_by_villagerId() {
		String userId = this.getPara("userId");
		String villagerId = this.getPara("villagerId");
		String reason = this.getPara("reason");
		Date postLeaveDate = this.getParaToDate("postLeaveDate", new Date());

		int isWorking = 2;
		if (Utils.isBlankOrEmpty(villagerId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		Integer ralationType = 5;
		resultMap = service.updateEmploymenValliager(villagerId, userId, postLeaveDate, reason, isWorking,
				ralationType);

		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据村id删除
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void delete_employment_by_villagerId() {
		String userId = this.getPara("userId");
		String villagerId = this.getPara("villagerId");
		Date postLeaveDate = this.getParaToDate("postLeaveDate", new Date());

		int isWorking = 2;
		if (Utils.isBlankOrEmpty(villagerId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		Integer ralationType = 5;
		resultMap = service.updateEmploymenValliager(villagerId, userId, postLeaveDate, null, isWorking, ralationType);

		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询失业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_unemployment_valliager() {

		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		int isWorking = 1;
		String keyWord = this.getPara("keyWord");

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.pageValliager(pageNumber, pageSize, "", "", isWorking, keyWord);

		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 根据id删除失业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void delete_unemployment_by_villagerId() {
		String userId = this.getPara("userId");
		String villagerId = this.getPara("villagerId");
		Date postLeaveDate = this.getParaToDate("postLeaveDate", new Date());

		int isWorking = 2;
		if (Utils.isBlankOrEmpty(villagerId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		Integer ralationType = 5;

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.updateEmploymenValliager(villagerId, userId, postLeaveDate, null, isWorking, ralationType);

		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 增加岗位
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_post() {
		String name = this.getPara("name");
		String typeId = this.getPara("typeId");
		String orgId = this.getPara("orgId");
		int number = this.getParaToInt("number", 0);
		Double averageIncome = this.getParaToDouble("averageIncome");
		String department = this.getPara("department");
		String content = this.getPara("content");
		String userId = this.getPara("userId");
		String attachment = this.getPara("attachment");

		int isFiveFund = this.getParaToInt("isFiveFund", 0);
		int isEatEncase = this.getParaToInt("isEatEncase", 0);
		int isEat = this.getParaToInt("isEat", 0);
		int isEncase = this.getParaToInt("isEncase", 0);
		int isLunch = this.getParaToInt("isLunch", 0);
		int isOther = this.getParaToInt("isOther", 0);
		BigDecimal b = new BigDecimal(averageIncome);
		averageIncome = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		if (Utils.isBlankOrEmpty(name) || Utils.isBlankOrEmpty(typeId) || Utils.isBlankOrEmpty(orgId)
				|| averageIncome <= 0 || Utils.isBlankOrEmpty(department) || Utils.isBlankOrEmpty(orgId)
				|| Utils.isBlankOrEmpty(content)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.addPost(userId, name, typeId, orgId, number, averageIncome, department, content, isFiveFund,
				isEatEncase, isEat, isEncase, isLunch, isOther, attachment);

		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据岗位id查询
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_post_by_id() {
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(id)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.queryPostById(id);

		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询就业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_post() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String keyWord = this.getPara("keyWord");
		String postId = this.getPara("postId");

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pagePost(pageNumber, pageSize, postId, keyWord);
		renderJson(resultMap);
		return;

	}

	// 根据单位id分页查询该单位岗位信息
	public void page_post_json_by_eid() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String enId = this.getPara("enId");

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pagePostByEId(pageNumber, pageSize, enId);
		renderJson(resultMap);
		return;

	}

	// 根据岗位id分页查询该岗位申请人、招聘人、不招聘人信息
	public void page_post_villager_by_postId_json() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String postId = this.getPara("postId");
		int ralationType = this.getParaToInt("ralationType", 1);

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pagePostVillagerByPostIdJson(pageNumber, pageSize, postId, ralationType);
		renderJson(resultMap);
		return;

	}

	// 岗位新增申请人
	public void add_post_villager() {
		String villagerId = this.getPara("villagerId");
		String postId = this.getPara("postId");
		String memberId = this.getPara("memberId");
		String userId = this.getPara("userId");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.addPostVillager(villagerId, postId, userId, memberId);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 更新岗位信息
	 * @author lxz;
	 * @date 2017年9月6日 下午11:24:31
	 */
	public void update_post() {
		String id = this.getPara("id");
		String name = this.getPara("name");
		String typeId = this.getPara("typeId");
		String orgId = this.getPara("orgId");
		int number = this.getParaToInt("number", 0);
		Double averageIncome = this.getParaToDouble("averageIncome");
		String department = this.getPara("department");
		String content = this.getPara("content");
		String userId = this.getPara("userId");
		String attachment = this.getPara("attachment");
		BigDecimal b = new BigDecimal(averageIncome);
		averageIncome = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		int isFiveFund = this.getParaToInt("isFiveFund", 0);
		int isEatEncase = this.getParaToInt("isEatEncase", 0);
		int isEat = this.getParaToInt("isEat", 0);
		int isEncase = this.getParaToInt("isEncase", 0);
		int isLunch = this.getParaToInt("isLunch", 0);
		int isOther = this.getParaToInt("isOther", 0);
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.updatePost(id, name, typeId, orgId, number, averageIncome, department, content, userId,
				isFiveFund, isEatEncase, isEat, isEncase, isLunch, isOther, attachment);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 根据id删除招聘信息
	 * @author lxz
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void delete_post_by_id() {
		String id = this.getPara("postId");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.deletePostById(id);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 增加企业信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_enterprise() {
		String name = this.getPara("name");
		String userId = this.getPara("userId");
		String contact = this.getPara("contact");
		String address = this.getPara("address");
		String phone = this.getPara("phone");
		String email = this.getPara("email");
		String personNo = this.getPara("personNo");
		String typeId = this.getPara("typeId");
		String orgId = this.getPara("orgId");
		String personId = this.getPara("personId");
		String remark = this.getPara("remark");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.addEnterprise(name, contact, userId, address, personNo, orgId, personId, typeId, userId,
				userId, remark, phone, email);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 更新企业信息
	 * @author lxz;
	 * @date 2017年9月6日 下午11:24:31
	 */
	public void update_enterprise() {
		String id = this.getPara("id");
		String name = this.getPara("name");
		String userId = this.getPara("userId");
		String contact = this.getPara("contact");
		String address = this.getPara("address");
		String phone = this.getPara("phone");
		String email = this.getPara("email");
		String personNo = this.getPara("personNo");
		String typeId = this.getPara("typeId");
		String orgId = this.getPara("orgId");
		String personId = this.getPara("personId");
		String remark = this.getPara("remark");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.updateEnterprise(id, name, contact, userId, address, personNo, orgId, personId, typeId,
				userId, userId, remark, phone, email);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 分页查询企业信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_enterprise() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String keyWord = this.getPara("keyWord");

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageEnterprise(pageNumber, pageSize, keyWord);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 查询企业所有信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void getAllEnterprise() {
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.getAllEnterprise();
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 根据id查询企业信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_enterprise_by_id() {
		String id = this.getPara("id");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryEnterpriseById(id);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 根据id删除企业信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void delete_enterprise_by_id() {
		String id = this.getPara("id");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.deleteEnterpriseById(id);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 根据身份证或姓名查询村名
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void find_valligers() {
		String keyWord = this.getPara("keyWord", ArnoldUtils.EMPTY_STR);
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.findValligerByKeyWord(keyWord);
		renderJson(resultMap);
		return;

	}

	public void page_price_log_json() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 15);
		String startdate = this.getPara("startdate", null);
		String enddate = this.getPara("enddate", null);
		String keyWord = this.getPara("keyWord", null);
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pagePriceLog(pageNumber, pageSize, startdate, enddate, keyWord);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 查询类别管理产业信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_industry_by_typeId() {
		String typeId = this.getPara("typeId");
		if (Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryIndustryByTypeId(typeId);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 更新类别管理产业信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void update_industry_info() {
		String typeId = this.getPara("typeId");
		String ptypeId = this.getPara("ptypeId");
		String typeName = this.getPara("typeName");
		String ptypeName = this.getPara("ptypeName");
		String unitName = this.getPara("unitName", null);
		Double costUnitPrice = this.getParaToDouble("costUnitPrice");
		Double underwriteUnitPrice = this.getParaToDouble("underwriteUnitPrice");
		Double guidePrice = this.getParaToDouble("guidePrice");
		Integer predictAmount = this.getParaToInt("predictAmount");
		String guidePriceFile = this.getPara("guidePriceFile");
		Double flUnitPrice = this.getParaToDouble("flUnitPrice", 0);
		Double lyUnitPrice = this.getParaToDouble("lyUnitPrice", 0);
		Double bmUnitPrice = this.getParaToDouble("bmUnitPrice", 0);
		Double ggUnitPrice = this.getParaToDouble("ggUnitPrice", 0);
		Double qtUnitPrice = this.getParaToDouble("qtUnitPrice", 0);
		Double slUnitPrice = this.getParaToDouble("slUnitPrice", 0);
		Double syUnitPrice = this.getParaToDouble("syUnitPrice", 0);
		Double lsslUnitPrice = this.getParaToDouble("lsslUnitPrice", 0);
		Double expected_amount = this.getParaToDouble("expected_amount", 0.0);

		String userId = this.getPara("userId");
		if (Utils.isBlankOrEmpty(typeId)/* ||Utils.isBlankOrEmpty(unitName) */) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.updateIndustry(typeId, ptypeId, typeName, ptypeName, unitName, costUnitPrice,
				underwriteUnitPrice, guidePrice, predictAmount, guidePriceFile, userId, flUnitPrice, lyUnitPrice,
				bmUnitPrice, ggUnitPrice, qtUnitPrice, slUnitPrice, syUnitPrice, lsslUnitPrice, expected_amount);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 分页查询价格日志
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_industry_price() {

		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);

		String keyWord = this.getPara("keyWord");
		String startDate = this.getPara("startDate");
		String endDate = this.getPara("endDate");

		String userId = this.getPara("userId");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageIndustryPrice(pageNumber, pageSize, keyWord, startDate, endDate, userId);
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 分页查询集体经济
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_collective_economy() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String keyWord = this.getPara("keyWord");
		String rName = this.getPara("rName");

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageCollectiveEconomy(pageNumber, pageSize, keyWord, rName);
		renderJson(resultMap);
		return;
	}

	// 根据id查询集体经济
	public void query_collective_economy_by_id() {
		String id = this.getPara("id");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryCollectiveEconomyById(id);
		renderJson(resultMap);
		return;
	}

	// 根据id查询村
	public void query_region_by_id() {
		String id = this.getPara("id");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryRegionById(id);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 保存集体经济信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void save_collective_economy() {
		String regionId = this.getPara("regionId");
		String valligerId = this.getPara("valligerId");
		String phone = this.getPara("phone");
		String userId = this.getPara("userId");
		Double totalPrice = this.getParaToDouble("totalPrice", 0.0);
		String valligerName = this.getPara("valligerName");

		if (Utils.isBlankOrEmpty(regionId) || Utils.isBlankOrEmpty(valligerId) || Utils.isBlankOrEmpty(valligerName)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.saveCollectiveEconomy(regionId, valligerId, phone, userId, valligerName, totalPrice);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:增加集体经济信息采购信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_collective_economy_purchase() {
		String userId = this.getPara("userId");
		String collectiveEconomyId = this.getPara("collectiveEconomyId");
		String regionId = this.getPara("regionId");

		String purchaseContent = this.getPara("purchaseContent");
		Double totalPrice = this.getParaToDouble("totalPrice", 0.0);
		String orgId = this.getPara("orgId");
		Date tradeTime = this.getParaToDate("tradeTime");

		if ((Utils.isBlankOrEmpty(collectiveEconomyId) && Utils.isBlankOrEmpty(regionId))
				|| Utils.isBlankOrEmpty(purchaseContent) || Utils.isBlankOrEmpty(orgId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.saveCollectiveEconomyPurachase(collectiveEconomyId, regionId, "", purchaseContent,
				totalPrice, orgId, tradeTime, userId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询集体经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_collective_economy_purchase() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String collectiveEconomyId = this.getPara("collectiveEconomyId", ArnoldUtils.EMPTY_STR);
		String regionId = this.getPara("regionId", ArnoldUtils.EMPTY_STR);

		if ((Utils.isBlankOrEmpty(collectiveEconomyId) && Utils.isBlankOrEmpty(regionId))) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageCollectiveEconomyPurchase(pageNumber, pageSize, collectiveEconomyId, regionId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询企业列表
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_enterprises() {
		ArnoldService2 service = new ArnoldService2();
		resultMap.put(ConstUtils.R_LIST, service.queryAllEnterprises());
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:增加集体经济信息收入信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_collective_economy_sell() {
		String userId = this.getPara("userId");
		String collectiveEconomyId = this.getPara("collectiveEconomyId");
		String regionId = this.getPara("regionId");
		String purchaseContent = this.getPara("purchaseContent");
		Double totalPrice = this.getParaToDouble("totalPrice", 0.0);
		String orgId = this.getPara("orgId");
		Date tradeTime = this.getParaToDate("tradeTime", new Date());

		if ((Utils.isBlankOrEmpty(collectiveEconomyId) && Utils.isBlankOrEmpty(regionId))
				|| Utils.isBlankOrEmpty(purchaseContent) || Utils.isBlankOrEmpty(orgId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.saveCollectiveEconomySell(collectiveEconomyId, regionId, "", purchaseContent, totalPrice,
				orgId, tradeTime, userId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询集体经济收入信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_collective_economy_sell() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String collectiveEconomyId = this.getPara("collectiveEconomyId");
		String regionId = this.getPara("regionId");

		if ((Utils.isBlankOrEmpty(collectiveEconomyId) && Utils.isBlankOrEmpty(regionId))) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageCollectiveEconomySell(pageNumber, pageSize, collectiveEconomyId, regionId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:更新集体经济信息采购信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void update_collective_economy_purchase() {
		String id = this.getPara("id");
		String userId = this.getPara("userId");

		String purchaseContent = this.getPara("purchaseContent");
		Double totalPrice = this.getParaToDouble("totalPrice", 0.0);
		String orgId = this.getPara("orgId");
		Date tradeTime = this.getParaToDate("tradeTime");

		if (Utils.isBlankOrEmpty(id) || Utils.isBlankOrEmpty(purchaseContent) || Utils.isBlankOrEmpty(orgId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		int result = service.updateCollectiveEconomyPurachase(id, purchaseContent, totalPrice, orgId, tradeTime,
				userId);

		if (result == ArnoldUtils.NOT_FIND_ERRO) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		if (result == ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:更新集体经济信息收入信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void update_collective_economy_sell() {
		String id = this.getPara("id");
		String userId = this.getPara("userId");
		String purchaseContent = this.getPara("purchaseContent");
		Double totalPrice = this.getParaToDouble("totalPrice", 0.0);
		String orgId = this.getPara("orgId");
		Date tradeTime = this.getParaToDate("tradeTime", new Date());
		if (Utils.isBlankOrEmpty(id) || Utils.isBlankOrEmpty(purchaseContent) || Utils.isBlankOrEmpty(orgId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		int result = service.updateCollectiveEconomySell(id, purchaseContent, totalPrice, orgId, tradeTime, userId);
		if (result == ArnoldUtils.NOT_FIND_ERRO) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		if (result == ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据id查询集体经济收入信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_ce_sell_by_id() {
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(id)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryCollectiveEconomySellById(id);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据id查询集体经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_ce_purchase_by_id() {
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(id)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryCollectiveEconomyPurchaseById(id);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据行政区域id查询户主信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_family_by_region_id() {
		String regionId = this.getPara("regionId");

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryFamilysByRegionId(regionId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 保存家庭经济信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void save_family_economy() {
		String familyId = this.getPara("familyId");
		String userId = this.getPara("userId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		String purchaseJsonStr = this.getPara("purchaseJson");
		String underwritingJsonStr = this.getPara("underwritingJson");
		String selfSaleJsonStr = this.getPara("selfSaleJson");
		String economyHeaders = this.getPara("economyHeaders");

		if (Utils.isBlankOrEmpty(familyId) || Utils.isBlankOrEmpty(userId) || Utils.isBlankOrEmpty(memberId)
				|| Utils.isBlankOrEmpty(parentTypeId) || Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		JSONObject purchaseJson = null, underwritingJson = null, selfSaleJson = null, headerJson = null;
		try {
			if (!Utils.isBlankOrEmpty(purchaseJsonStr)) {
				purchaseJson = new JSONObject(purchaseJsonStr);
			}
			if (!Utils.isBlankOrEmpty(underwritingJsonStr)) {
				underwritingJson = new JSONObject(underwritingJsonStr);
			}
			if (!Utils.isBlankOrEmpty(selfSaleJsonStr)) {
				selfSaleJson = new JSONObject(selfSaleJsonStr);
			}
			if (!Utils.isBlankOrEmpty(economyHeaders)) {
				headerJson = new JSONObject(economyHeaders);
			}
		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();

		if (!service.checkPurchasesJson(purchaseJson) || !service.checkCeSellJsons(selfSaleJson)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		if (purchaseJson != null || underwritingJson != null || selfSaleJson != null || headerJson != null) {
			resultMap = service.saveFamilyEconomy(familyId, userId, memberId, parentTypeId, typeId, purchaseJson,
					underwritingJson, selfSaleJson, headerJson);
		} else {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
		}
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询家庭经济
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_family_economy() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String regionId = this.getPara("regionId");
		String keyWord = this.getPara("keyWord");

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageFamilyconomy(pageNumber, pageSize, regionId, keyWord);
		renderJson(resultMap);
		return;
	}

	public void page_family_economyByHam() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String keyWord = this.getPara("keyWord");
		String hamletName = this.getPara("hamletName");
		String groupName = this.getPara("groupName");

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageFamilyEconomicByHam(pageNumber, pageSize, hamletName, groupName, keyWord);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:增加家庭经济信息采购信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_family_economy_purchase() {
		String userId = this.getPara("userId");
		String familyEconomyId = this.getPara("familyEconomyId");
		String familyId = this.getPara("familyId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		Double unitPrice = this.getParaToDouble("unitPrice", 0.0);
		Integer amount = this.getParaToInt("amount", 0);
		Integer underwritingAmount = this.getParaToInt("underwritingAmount", 0);
		Date tradeTime = this.getParaToDate("tradeTime", new Date());
		String economyHeaders = this.getPara("economyHeaders");

		if ((Utils.isBlankOrEmpty(familyId) && Utils.isBlankOrEmpty(familyEconomyId)) || Utils.isBlankOrEmpty(memberId)
				|| Utils.isBlankOrEmpty(parentTypeId) || Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.saveFamilyEconomyPurachase(familyEconomyId, familyId, memberId, parentTypeId, typeId,
				unitPrice, amount, underwritingAmount, tradeTime, userId);
		renderJson(resultMap);

		if (!Utils.isBlankOrEmpty(economyHeaders)) {
			JSONObject headerJson = new JSONObject(economyHeaders);
			FamilyEconomicHerdsService familyEconomicHerdsService = new FamilyEconomicHerdsService();
			Integer pupsAmount = 0;
			if (headerJson.has("pupsAmount")) {
				pupsAmount = headerJson.getInt("pupsAmount");
			}
			Integer adultsAmount = 0;
			if (headerJson.has("adultsAmount")) {
				adultsAmount = headerJson.getInt("adultsAmount");
			}
			Integer malesAmount = 0;
			if (headerJson.has("malesAmount")) {
				malesAmount = headerJson.getInt("malesAmount");
			}
			Page<FamilyEconomicHerds> pageHeads = familyEconomicHerdsService.pageFamilyEconomicHerds(1, 1, "",
					familyId);
			if (pageHeads != null && !pageHeads.getList().isEmpty()) {
				String id = pageHeads.getList().get(0).getId();
				familyEconomicHerdsService.updateFamilyEconomicHerds(id, typeId, underwritingAmount, pupsAmount,
						adultsAmount, malesAmount, userId, headerJson.getString("remark"));
			} else {
				familyEconomicHerdsService.saveFamilyEconomicHerds(familyEconomyId, familyId, typeId,
						underwritingAmount, pupsAmount, adultsAmount, malesAmount, userId,
						headerJson.get("remark") + "");
			}
		}
		return;
	}

	/**
	 * @Description: 分页查询家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_family_economy_purchase() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String familyEconomyId = this.getPara("familyEconomyId", ArnoldUtils.EMPTY_STR);
		String familyId = this.getPara("familyId", ArnoldUtils.EMPTY_STR);
		String parentTypeId = this.getPara("parentTypeId", ArnoldUtils.EMPTY_STR);

		if ((Utils.isBlankOrEmpty(familyEconomyId) && Utils.isBlankOrEmpty(familyId))) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageFamilyEconomyPurchase(pageNumber, pageSize, familyEconomyId, familyId, parentTypeId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据id查询家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_fe_purchase_by_id() {
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(id)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryFamilyEconomyPurchaseById(id);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:更新家庭经济信息采购信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void update_family_economy_purchase() {
		String id = this.getPara("id");
		String userId = this.getPara("userId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		Double unitPrice = this.getParaToDouble("unitPrice", 0.0);
		Integer amount = this.getParaToInt("amount", 0);
		Integer underwritingAmount = this.getParaToInt("underwritingAmount", 0);
		Date tradeTime = this.getParaToDate("tradeTime");
		// String economyHeaders = this.getPara("economyHeaders");

		if (Utils.isBlankOrEmpty(id) || Utils.isBlankOrEmpty(memberId) || Utils.isBlankOrEmpty(parentTypeId)
				|| Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		int result = service.updateFamilyEconomyPurachase(id, parentTypeId, typeId, unitPrice, amount,
				underwritingAmount, memberId, tradeTime, userId);

		if (result == ArnoldUtils.NOT_FIND_ERRO) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		if (result == ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:增加家庭经济信息包销信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_family_economy_underwriting() {
		String userId = this.getPara("userId");
		String familyEconomyId = this.getPara("familyEconomyId");
		String familyId = this.getPara("familyId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		Double unitPrice = this.getParaToDouble("unitPrice", 0.0);
		Integer amount = this.getParaToInt("amount", 0);
		Date tradeTime = this.getParaToDate("tradeTime", new Date());

		Double sale_amount1 = this.getParaToDouble("sale_amount1", 0.0);

		if ((Utils.isBlankOrEmpty(familyId) && Utils.isBlankOrEmpty(familyEconomyId)) || Utils.isBlankOrEmpty(memberId)
				|| Utils.isBlankOrEmpty(parentTypeId) || Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.saveFamilyEconomyUnderwriting(familyEconomyId, familyId, memberId, parentTypeId, typeId,
				unitPrice, amount, tradeTime, userId, sale_amount1);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_family_economy_underwriting() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String familyEconomyId = this.getPara("familyEconomyId", ArnoldUtils.EMPTY_STR);
		String familyId = this.getPara("familyId", ArnoldUtils.EMPTY_STR);
		String parentTypeId = this.getPara("parentTypeId", ArnoldUtils.EMPTY_STR);

		if ((Utils.isBlankOrEmpty(familyEconomyId) && Utils.isBlankOrEmpty(familyId))) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageFamilyEconomyUnderwriting(pageNumber, pageSize, familyEconomyId, familyId,
				parentTypeId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据id查询家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_fe_underwriting_by_id() {
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(id)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryFamilyEconomyUnderwritingById(id);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:更新家庭经济信息包销信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void update_family_economy_underwriting() {
		String id = this.getPara("id");
		String userId = this.getPara("userId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		Double unitPrice = this.getParaToDouble("unitPrice", 0.0);
		Integer amount = this.getParaToInt("amount", 0);
		Date tradeTime = this.getParaToDate("tradeTime");
		Double sale_amount1 = this.getParaToDouble("sale_amount1", 0.0);
		Double surplus = this.getParaToDouble("surplus", 0.0);
		
		if (Utils.isBlankOrEmpty(id) || Utils.isBlankOrEmpty(memberId) || Utils.isBlankOrEmpty(parentTypeId)
				|| Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		int result = service.updateFamilyEconomyUnderwriting(id, parentTypeId, typeId, unitPrice, amount, memberId,
				tradeTime, userId, sale_amount1,surplus);

		if (result == ArnoldUtils.NOT_FIND_ERRO) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		if (result == ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:增加家庭经济信息自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_family_economy_self_sale() {
		String userId = this.getPara("userId");
		String familyEconomyId = this.getPara("familyEconomyId");
		String familyId = this.getPara("familyId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		Double unitPrice = this.getParaToDouble("unitPrice", 0.0);
		Integer amount = this.getParaToInt("amount", 0);
		Date tradeTime = this.getParaToDate("tradeTime", new Date());
		Double sale_amount2 = this.getParaToDouble("sale_amount2", 0.0);
		if ((Utils.isBlankOrEmpty(familyId) && Utils.isBlankOrEmpty(familyEconomyId)) || Utils.isBlankOrEmpty(memberId)
				|| Utils.isBlankOrEmpty(parentTypeId) || Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.saveFamilyEconomySelfSale(familyEconomyId, familyId, memberId, parentTypeId, typeId,
				unitPrice, amount, tradeTime, userId, sale_amount2);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_family_economy_self_sale() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String familyEconomyId = this.getPara("familyEconomyId", ArnoldUtils.EMPTY_STR);
		String familyId = this.getPara("familyId", ArnoldUtils.EMPTY_STR);
		String parentTypeId = this.getPara("parentTypeId", ArnoldUtils.EMPTY_STR);

		if ((Utils.isBlankOrEmpty(familyEconomyId) && Utils.isBlankOrEmpty(familyId))) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageFamilyEconomySelfSale(pageNumber, pageSize, familyEconomyId, familyId, parentTypeId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据id查询家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_fe_self_sale_by_id() {
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(id)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryFamilyEconomySelfSaleById(id);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:更新家庭经济信息自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void update_family_economy_self_sale() {
		String id = this.getPara("id");
		String userId = this.getPara("userId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		Double unitPrice = this.getParaToDouble("unitPrice", 0.0);
		Integer amount = this.getParaToInt("amount", 0);
		Date tradeTime = this.getParaToDate("tradeTime");

		Double sale_amount2 = this.getParaToDouble("sale_amount2", 0.0);

		if (Utils.isBlankOrEmpty(id) || Utils.isBlankOrEmpty(memberId) || Utils.isBlankOrEmpty(parentTypeId)
				|| Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		int result = service.updateFamilyEconomySelfSale(id, parentTypeId, typeId, unitPrice, amount, memberId,
				tradeTime, userId, sale_amount2);

		if (result == ArnoldUtils.NOT_FIND_ERRO) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		if (result == ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:增加家庭经济信息特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void add_family_economy_special_situation() {
		String userId = this.getPara("userId");
		String familyEconomyId = this.getPara("familyEconomyId");
		String familyId = this.getPara("familyId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");
		String remark = this.getPara("remark");

		Integer increaseDecrease = this.getParaToInt("increaseDecrease", 0);
		Integer amount = this.getParaToInt("amount", 0);

		if ((Utils.isBlankOrEmpty(familyId) && Utils.isBlankOrEmpty(familyEconomyId))
				|| Utils.isBlankOrEmpty(parentTypeId) || Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();

		resultMap = service.saveFamilyEconomySpecialSituation(familyEconomyId, familyId, remark, parentTypeId, typeId,
				amount, increaseDecrease, userId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_family_economy_special_situation() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String familyEconomyId = this.getPara("familyEconomyId", ArnoldUtils.EMPTY_STR);
		String familyId = this.getPara("familyId", ArnoldUtils.EMPTY_STR);
		String parentTypeId = this.getPara("parentTypeId", ArnoldUtils.EMPTY_STR);

		if ((Utils.isBlankOrEmpty(familyEconomyId) && Utils.isBlankOrEmpty(familyId))) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageFamilyEconomySpecialSituation(pageNumber, pageSize, familyEconomyId, familyId,
				parentTypeId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据id查询家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_fe_special_situation_by_id() {
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(id)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryFamilyEconomySpecialSituationById(id);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:更新家庭经济信息特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void update_family_economy_special_situation() {
		String id = this.getPara("id");
		String userId = this.getPara("userId");

		String remark = this.getPara("remark");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		Integer increaseDecrease = this.getParaToInt("increaseDecrease", 0);

		Integer amount = this.getParaToInt("amount", 0);

		if (Utils.isBlankOrEmpty(id) || Utils.isBlankOrEmpty(parentTypeId) || Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		int result = service.updateFamilyEconomySpecialSituation(id, parentTypeId, typeId, amount, increaseDecrease,
				userId, remark);
		if (result == ArnoldUtils.NOT_FIND_ERRO) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		if (result == ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 分页查询家庭经济存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void page_family_economy_herds() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String familyEconomyId = this.getPara("familyEconomyId", ArnoldUtils.EMPTY_STR);
		String familyId = this.getPara("familyId", ArnoldUtils.EMPTY_STR);

		if ((Utils.isBlankOrEmpty(familyEconomyId) && Utils.isBlankOrEmpty(familyId))) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageFamilyEconomyHerds(pageNumber, pageSize, familyEconomyId, familyId);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 根据id查询家庭经济存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void query_fe_herds_by_id() {
		String id = this.getPara("id");

		if (Utils.isBlankOrEmpty(id)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.queryFamilyEconomyHerdsById(id);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description:更新家庭经济信息存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void update_family_economy_herds() {
		String id = this.getPara("id");
		String userId = this.getPara("userId");

		String memberId = this.getPara("memberId");
		String parentTypeId = this.getPara("parentTypeId");
		String typeId = this.getPara("typeId");

		Integer pupsAmount = this.getParaToInt("pupsAmount", 0);
		Integer adultsAmount = this.getParaToInt("adultsAmount", 0);
		Integer malesAmount = this.getParaToInt("malesAmount", 0);
		Integer amount = this.getParaToInt("amount", 0);

		if (Utils.isBlankOrEmpty(memberId) || Utils.isBlankOrEmpty(parentTypeId) || Utils.isBlankOrEmpty(typeId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}

		ArnoldService2 service = new ArnoldService2();
		int result = service.updateFamilyEconomyHerds(id, typeId, amount, pupsAmount, adultsAmount, malesAmount,
				userId);
		if (result == ArnoldUtils.NOT_FIND_ERRO) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		if (result == ConstUtils.DATA_OPERATE_ERROR) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	// 查询就失业人员流水记录
	public void page_villager_post_happen() {
		String villagerId = this.getPara("villagerId");
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);

		if (Utils.isBlankOrEmpty(villagerId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageVillagerPostHappen(pageNumber, pageSize, villagerId);
		renderJson(resultMap);
		return;
	}

	public void findFamilyIdByRegionId() {
		String regionId = this.getPara("regionId");
		String regionName = this.getPara("regionName");
		FamilyService familyService = new FamilyService();
		List<Family> families = familyService.findFamilyIdByRegionId(regionId, regionName);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(ConstUtils.R_LIST, families);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		renderJson(resultMap);
		return;
	}

	/**
	 * @Description: 更新岗位申请人信息
	 * @author lxz;
	 * @date 2017年9月6日 下午11:24:31
	 */
	public void update_post_villager_ralationType() {
		String id = this.getPara("vpId");
		int ralationType = this.getParaToInt("ralationType", 0);
		String reason = this.getPara("reason");
		String userId = this.getPara("userId");

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.updatePostVillagerRalationType(id, ralationType, reason, userId);
		renderJson(resultMap);
		return;

	}
	
	/** 删除就业信息 */
	public void del_post_villager_ralationType() {
		ValigerPostHappenService service = new ValigerPostHappenService();
		
		resultMap = service.delPostVillagerRelationType(
				getRequestBean(RequestDelPostVillagerRalationType.class, "happenId"));
		
		renderJson(resultMap);
		return;

	}

	/**
	 * @Description: 根据id删除岗位申请人
	 * @author lxz
	 * @date 2017年8月15日 上午11:24:31
	 */
	public void delete_post_village_by_id() {
		String id = this.getPara("id");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.deletePostVillageById(id);
		renderJson(resultMap);
		return;

	}

	public void queryFamilyEconomicPurchaseHappen() {
		String typeId = this.getPara("typeId", ArnoldUtils.EMPTY_STR);
		String familyId = this.getPara("familyId", ArnoldUtils.EMPTY_STR);
		String memberId = this.getPara("memberId", ArnoldUtils.EMPTY_STR);

		if (Utils.isBlankOrEmpty(typeId) || Utils.isBlankOrEmpty(familyId) || Utils.isBlankOrEmpty(memberId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		FamilyEconomicPurchaseHappenService service = new FamilyEconomicPurchaseHappenService();
		resultMap = service.queryFamilyEconomicPurchaseHappen(familyId, typeId, memberId);
		renderJson(resultMap);
		return;
	}

	public void getVilagerPostHappen() {
		String villagerId = this.getPara("villagerId", ArnoldUtils.EMPTY_STR);
		if (Utils.isBlankOrEmpty(villagerId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;
		}
		ValigerPostHappenService service = new ValigerPostHappenService();
		resultMap = service.getVilagerPostHappen(villagerId);
		renderJson(resultMap);
		return;
	}

	public void json_report1() {

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.jsonReport1();
		renderJson(resultMap);
		return;
	}

	public void json_report2() {

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.jsonReport2();
		renderJson(resultMap);
		return;
	}

	public void json_report3() {

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.jsonReport3();
		renderJson(resultMap);
		return;
	}

	public void json_report4() {

		ArnoldService2 service = new ArnoldService2();
		resultMap = service.jsonReport4();
		renderJson(resultMap);
		return;
	}

	public void page_EffectTPGJ_json() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageEffectTPGJJson(pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}

	public void page_EffectJCSS_json() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageEffectJCSSJson(pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}

	public void page_EffectXZYZ_json() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageEffectXZYZJson(pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}

	/***
	 * 根据家庭Id查找香猪及相关信息
	 * 
	 * 
	 * @param familyId
	 */
	public void query_XzyzByFamliyIdTo_Json() {

		ArnoldService2 service = new ArnoldService2();
		String familyId = this.getPara("familyId");
		resultMap = service.queryXzyzByFamliyIdToJson(familyId);

		renderJson(resultMap);
		return;
	}

	public void page_EffectJYWG_json() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.pageEffectJYWGJson(pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}

	public void page_getRegionByPId_json() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", ConstUtils.PAGE_SIZE_DEFULT);
		String parentId = this.getPara("parentId");
		ArnoldService2 service = new ArnoldService2();
		resultMap = service.page_getRegionByPId_json(parentId, pageNumber, pageSize);
		renderJson(resultMap);
		return;
	}

}
