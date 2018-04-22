package com.arnold.server.service.api;

import com.arnold.server.model.Member;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.arnold.server.util.QueryBaseUtil;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.huntersun.tool.DateUtil;
//import com.huntersun.tool.HttpRequestService;
//import com.huntersun.tool.exception.HttpRequestException;

public class MemberService extends BaseService {

	public Map<String, Object> addMember(String memberId, String name, String sexId, String phone, Date birthday, 
							String iDnumber, String politicalStatusId, String educationalId,String raceId, 
							String orgId, String post, String groupId, String roleOfGroupId, String writer, 
							String accreditDepartmentId, String orgPost, String accreditPlaceId,
							String accreditPostId,Date accreditStart, Date accreditEnd, String remark){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String id = memberId;
		Member model = Member.dao.findById(id);
		if(model != null){					
			if(!Utils.isBlankOrEmpty(sexId)) model.setSexId(sexId);
			if(!Utils.isBlankOrEmpty(phone)) model.setPhone(phone);
			if(!Utils.isBlankOrEmpty(orgPost)) model.setOrgPost(orgPost);
			
			if(birthday != null) {
				model.setBirthday(birthday);
			} else {
				birthday = ArnoldUtils.getBirthdayByIDnumber(iDnumber);
				model.setBirthday(birthday);
			}
			
			if(!Utils.isBlankOrEmpty(iDnumber)) model.setIDnumber(iDnumber);
			if(!Utils.isBlankOrEmpty(politicalStatusId)) model.setPoliticalStatusId(politicalStatusId);
			if(!Utils.isBlankOrEmpty(educationalId)) model.setEducationalId(educationalId);
			
			if(!Utils.isBlankOrEmpty(raceId)) model.setRaceId(raceId);
			if(!Utils.isBlankOrEmpty(orgId)) model.setOrgId(orgId);
			if(!Utils.isBlankOrEmpty(sexId)) model.setPost(post);
			if(!Utils.isBlankOrEmpty(post)) model.setOrgId(orgId);
			if(!Utils.isBlankOrEmpty(groupId)) model.setGroupId(groupId);
			if(!Utils.isBlankOrEmpty(roleOfGroupId)) model.setRoleOfGroupId(roleOfGroupId);
			if(!Utils.isBlankOrEmpty(writer)) model.setWriter(writer);
			
			if(!Utils.isBlankOrEmpty(accreditDepartmentId)) model.setAccreditDepartmentId(accreditDepartmentId);
			if(!Utils.isBlankOrEmpty(accreditPlaceId)) model.setAccreditPlaceId(accreditPlaceId);
			if(!Utils.isBlankOrEmpty(accreditPostId)) model.setAccreditPostId(accreditPostId);
			if(accreditEnd != null) model.setAccreditEnd(accreditEnd);
			if(accreditStart != null) model.setAccreditStart(accreditStart);
			if(!Utils.isBlankOrEmpty(orgId)) model.setOrgId(orgId);
			if(!Utils.isBlankOrEmpty(remark)) model.setRemark(remark);
			
			model.setUpdateTime(new Date());
			
			model.update();
			
			resultMap.put("memberId", model.getId());
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
				
			return resultMap;
		}
		
		/*Member member2 = new Member();
		Member model = member2.getMemberByIDNumber(iDnumber);
		
		if(model != null){
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.MEMBER_EXIST_CODE);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_EXIST_CODE_STR);
			return resultMap;	
		}*/
		
		Member memberModel = new Member();
		memberModel.setId(id);
		memberModel.setName(name);
		memberModel.setSexId(sexId);
		memberModel.setPhone(phone);
		
		if(birthday != null) memberModel.setBirthday(birthday);
		memberModel.setIDnumber(iDnumber);
		memberModel.setPoliticalStatusId(politicalStatusId);
		memberModel.setEducationalId(educationalId);
		memberModel.setCreateTime(new Date());
		memberModel.setIsValid(0);
		memberModel.setRaceId(raceId);
		memberModel.setOrgId(orgId);
		memberModel.setPost(post);
		memberModel.setOrgId(orgId);
		memberModel.setGroupId(groupId);
		memberModel.setRoleOfGroupId(roleOfGroupId);
		memberModel.setWriter(writer);
		memberModel.setOrgPost(orgPost);
		
		memberModel.setAccreditDepartmentId(accreditDepartmentId);
		memberModel.setAccreditPlaceId(accreditPlaceId);
		memberModel.setAccreditPostId(accreditPostId);
		memberModel.setAccreditEnd(accreditEnd);
		memberModel.setAccreditStart(accreditStart);
		memberModel.setOrgId(orgId);
		memberModel.setRemark(remark);
		
		
		memberModel.setCreateTime(new Date());
		//memberModel.setUpdateTime(new Date());
		
		memberModel.save();
		
		resultMap.put("memberId", memberModel.getId());
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			
		return resultMap;		
	}
	
	public Map<String, Object> pageMember(int pageNumber, int pageSize,QueryBaseUtil queryBaseUtil){		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Member member = new Member();			
			Page<Member> memberModel = member.pageMember(pageNumber, pageSize,queryBaseUtil);
			resultMap.put(ConstUtils.R_PAGE, memberModel);
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);		
		}catch(Exception e){		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);		
		}
		
		return resultMap;		
	}
	
	public Map<String, Object> pageMemberByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			Member member = new Member();			
			Page<Member> memberModel = member.pageMemberByIds(pageNumber, pageSize, ids);		
			resultMap.put(ConstUtils.R_PAGE, memberModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}
		
		return resultMap;	
	}
	
	public Map<String, Object> updateMember(String id, String name, String sexId, String phone, Date birthday, 
			String iDnumber, String politicalStatusId, String educationalId,String raceId, 
			String orgId, String post, String groupId, String roleOfGroupId, String operator,
			String accreditDepartmentId, String orgPost, String accreditPlaceId, String accreditPostId, 
			Date accreditStart, Date accreditEnd, String remark){		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Member memberModel = Member.dao.findById(id);		
			if(memberModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			
			/*Member member2 = new Member();
			Member model = member2.getMemberByIDNumber(iDnumber);
			
			if(model != null){		
				resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.MEMBER_EXIST_CODE);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.MEMBER_EXIST_CODE_STR);
				return resultMap;		
			}*/
			
			if(!Utils.isBlankOrEmpty(name)){
				memberModel.setName(name);
			}
			if(!Utils.isBlankOrEmpty(sexId)){
				memberModel.setSexId(sexId);
			}
			if(!Utils.isBlankOrEmpty(phone)){
				memberModel.setPhone(phone);
			}
			if(birthday != null){
				memberModel.setBirthday(birthday);
			}
			if(!Utils.isBlankOrEmpty(iDnumber)){
				memberModel.setIDnumber(iDnumber);
			}
			if(!Utils.isBlankOrEmpty(politicalStatusId)){
				memberModel.setPoliticalStatusId(politicalStatusId);
			}
			if(!Utils.isBlankOrEmpty(educationalId)){
				memberModel.setEducationalId(educationalId);
			}

			memberModel.setUpdateTime(new Date());		
			
			if(!Utils.isBlankOrEmpty(raceId)){
				memberModel.setRaceId(raceId);
			}
			if(!Utils.isBlankOrEmpty(orgId)){
				memberModel.setOrgId(orgId);
			}
			if(!Utils.isBlankOrEmpty(post)){
				memberModel.setPost(post);
			}
			if(!Utils.isBlankOrEmpty(orgPost)){
				memberModel.setOrgPost(orgPost);
			}
			if(!Utils.isBlankOrEmpty(groupId)){
				memberModel.setGroupId(groupId);
			}
			if(!Utils.isBlankOrEmpty(orgId)){
				memberModel.setOrgId(orgId);
			}
			if(!Utils.isBlankOrEmpty(roleOfGroupId)){
				memberModel.setRoleOfGroupId(roleOfGroupId);
			}
					
			if(!Utils.isBlankOrEmpty(operator)){
				memberModel.setOperator(operator);
			}
		
			if(!Utils.isBlankOrEmpty(accreditDepartmentId)) memberModel.setAccreditDepartmentId(accreditDepartmentId);
			if(!Utils.isBlankOrEmpty(accreditPlaceId)) memberModel.setAccreditPlaceId(accreditPlaceId);
			if(!Utils.isBlankOrEmpty(accreditPostId)) memberModel.setAccreditPostId(accreditPostId);
			if(accreditEnd != null) memberModel.setAccreditEnd(accreditEnd);
			if(accreditStart != null) memberModel.setAccreditStart(accreditStart);
			if(!Utils.isBlankOrEmpty(orgId)) memberModel.setOrgId(orgId);
			if(!Utils.isBlankOrEmpty(remark)) memberModel.setRemark(remark);		
			
			memberModel.setUpdateTime(new Date());
			
			memberModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			
		}catch(Exception e){		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);			
		}
		
		return resultMap;	
	}
	
	public Map<String, Object> delMember(String id){		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{		
			Member memberModel = Member.dao.findById(id);	
			if(memberModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			
			//TODO:逻辑删除
			memberModel.setIsValid(-1);//-1表示刪除，0正常
			memberModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);		
		}	
		return resultMap;	
	}

	public Map<String, Object> forbidMember(String memberId, Integer isValid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Member memberModel = Member.dao.findById(memberId);
			if(memberModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}
			
			//TODO:0表示正常，1表示禁用
			memberModel.setIsValid(isValid);
			memberModel.update();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		}catch(Exception e){	
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);		
		}
		
		return resultMap;	
	}

	public Map<String, Object> findMember(String memberId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Member model = Member.dao.findMember(memberId);
		
		resultMap.put(ConstUtils.R_MODEL, model);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		return resultMap;
	}
	public Member findById(String memberId) {
		// TODO Auto-generated method stub
		
		Member model = Member.dao.findMember(memberId);
		
		
		return model;
	}
	public Map<String, Object> listAllMemberName(QueryBaseUtil queryConfig) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
//		Member model = Member.dao.listAllMemberName();
//		List<Member> list=Member.dao.findAll();
		List<Member> list=Member.dao.findMemberByCondition(queryConfig);
//		resultMap.put(ConstUtils.R_MODEL, model);
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		return resultMap;
	}

	public Map<String, Object> pageContactList(String regionId,
			Integer pageNumber, Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Member member = new Member();
			Page<Member> page = member.pageContactListByRId(pageNumber, pageSize, regionId);
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
	public List<Member> findAllMembers() {
		List<Member> list = Member.findAllMembers();
		return list;
	}
	public Member findByPhone(String phone) {
		Member member = Member.findLeaderByPhone(phone);
		return member;
	}
}
