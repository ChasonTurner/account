package com.arnold.server.controller.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.controller.BaseController;
import com.arnold.server.service.api.MemberService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

/**
 * @author ChangGui Pan
 * @time 2017年8月9日 下午6:39:13
 * @description TODO
 */
public class MemberController extends BaseController {
	MemberService memberService = new MemberService();
	
	public void add_member() {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		//tb_member
		String name = getPara("name", "");
		Integer sex = getParaToInt("sex", -1);
		String phone = getPara("phone", "");
		String birthday = getPara("birthday", "");
		String iDnumber = getPara("IDnumber", "");
		Integer politicalStatus = getParaToInt("politicalStatus", -1);
		Integer educational = getParaToInt("educational", -1);
		
		//tb_arrcedited
		String organizationId = getPara("organizationId", "");
		String orgPostId = getPara("orgPostId", "");
		String accreditPlace = getPara("accreditPlace", "");
		String accreditPostId = getPara("accreditPostId", "");
		String accreditTaskId = getPara("accreditTaskId", "");
		String accreditStart = getPara("accreditStart", "");
		String accreditEnd = getPara("accreditEnd", "");
		
		//tb_org_member_relation
		// 可以为空
		String orgId = getPara("orgId", "");
		String remark = getPara("remark", "");
		
		if (Utils.isBlankOrEmpty(name)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.NAME_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.NAME_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (sex == -1) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SEX_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SEX_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		//电话号码正则检查，1开头
		if (Utils.isBlankOrEmpty(phone)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.PHONE_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.PHONE_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		//合法性
		if (Utils.isBlankOrEmpty(birthday)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.BIRTHDAY_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.BIRTHDAY_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(iDnumber)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.IDNUMBER_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.IDNUMBER_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (politicalStatus == -1) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.POLITICALSTATUS_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.POLITICALSTATUS_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (educational == -1) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.EDUCATIONAL_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.EDUCATIONAL_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(organizationId)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ORGANIZATIONID_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ORGANIZATIONID_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(orgPostId)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ORGPOSTID_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ORGPOSTID_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(accreditPlace)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ACCREDITPLACE_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ACCREDITPLACE_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(accreditPostId)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ACCREDITPOSTID_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ACCREDITPOSTID_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(accreditTaskId)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ACCREDITTASKID_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ACCREDITTASKID_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(accreditStart)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ACCREDITSTART_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ACCREDITSTART_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(accreditEnd)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ACCREDITEND_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ACCREDITEND_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}

		//dataMap = memberService.addMember(name,sex,phone,birthday,iDnumber,politicalStatus,educational,
			//	organizationId,orgPostId,accreditPlace,accreditPostId,accreditTaskId,accreditStart,accreditEnd,
			//	orgId, remark);
		dataMap.putAll(dataMap);
		renderJson(dataMap);
		return;
	}
	
	public void update_member() {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		String name = getPara("name", "");
		Integer sex = getParaToInt("sex", -1);
		String phone = getPara("phone", "");
		String birthday = getPara("birthday", "");
		String iDnumber = getPara("iDnumber", "");
		Integer politicalStatus = getParaToInt("politicalStatus", 0);
		Integer educational = getParaToInt("educational", 0);
		
		if (Utils.isBlankOrEmpty(name)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.NAME_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.NAME_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (sex == -1) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SEX_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.SEX_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(phone)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.PHONE_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.PHONE_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(birthday)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.BIRTHDAY_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.BIRTHDAY_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (Utils.isBlankOrEmpty(iDnumber)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.IDNUMBER_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.IDNUMBER_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (politicalStatus == -1) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.POLITICALSTATUS_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.POLITICALSTATUS_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (educational == -1) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.EDUCATIONAL_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.EDUCATIONAL_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		//dataMap = memberService.updateMember(name,sex,phone,birthday,iDnumber,politicalStatus,educational);
		dataMap.putAll(dataMap);
		renderJson(dataMap);
		return;
	}

	/**
	 * 分页查询 
	 */
	public void page_member() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		String beginTime = getPara("beginTime", "");
		String endTime = getPara("endTime", "");
		Integer pageNumber = getParaToInt("pageNumber", -1);
		Integer pageSize = getParaToInt("pageSize", -1);
		
		if (pageNumber == -1) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.PAGENUMBER_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.PAGENUMBER_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		if (pageSize == -1) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.PAGESIZE_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.PAGESIZE_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		final int isValid = 1;
		//dataMap = memberService.pageMember(isValid, beginTime, endTime, pageNumber, pageSize);
		
		dataMap.putAll(dataMap);
		renderJson(dataMap);
		return;
	}

	/**
	 * 查询扶贫人员详情
	 */
	public void find_member() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		String id = getPara("id", "");
		
		if (Utils.isBlankOrEmpty(id)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ID_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ID_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		//dataMap = memberService.findMember(id);
		
		dataMap.putAll(dataMap);
		renderJson(dataMap);
		return;
	}
	
	public void del_member() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		String id = getPara("id", "");
		
		if (Utils.isBlankOrEmpty(id)) {
			dataMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.ID_NULL_ERROR_CODE);
			dataMap.put(ConstUtils.R_MSG, ErrorCodeConst.ID_NULL_ERROR_CODE_STR);
			renderJson(dataMap);
			return;
		}
		
		dataMap = memberService.delMember(id);
		
		dataMap.putAll(dataMap);
		renderJson(dataMap);
		return;
	}

}
