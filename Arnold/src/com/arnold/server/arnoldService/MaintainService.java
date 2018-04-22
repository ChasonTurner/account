package com.arnold.server.arnoldService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.config.ConsConfig;
import com.arnold.server.model.Family;
import com.arnold.server.model.Leader;
import com.arnold.server.model.Member;
import com.arnold.server.model.Region;
import com.arnold.server.service.api.BackstagecrewService;
import com.arnold.server.service.api.FamilyService;
import com.arnold.server.service.api.LeaderService;
import com.arnold.server.service.api.MemberService;
import com.arnold.server.service.api.RegionService;
import com.arnold.server.util.AlphabetUtil;
import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.MD5Util;
import com.huntersun.tool.Utils;

/**
 * @Description: 运维Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年9月20日 上午10:50:12
 */
public class MaintainService {
	/**
	 * @Description: 更新famliy number
	 * @author Li Bangming;
	 * @date 2017年9月20日 下午12:53:43
	 */
	public void updateFamliyNumber(){
		FamilyService familyService=new FamilyService();
		RegionService regionService=new RegionService();
		List<Family> famliys=familyService.findAll();
		for(Family familyModel:famliys){
			//采用自动生成的
			Region region=regionService.findRegionById(familyModel.getRegionId());
			String numberPrefix=ArnoldUtils.EMPTY_STR,serialNumber=ArnoldUtils.EMPTY_STR;
			if(region!=null){
				 numberPrefix=region.getParentName()+region.getShortName();
				 numberPrefix= AlphabetUtil.getAllFirstLetter(numberPrefix).toUpperCase();
				 serialNumber=familyService.getSerialNumberByNumberPrefix(numberPrefix);
				// 直接设置
				if (Utils.isBlankOrEmpty(familyModel.getNumber())) {
					familyModel.setNumber(numberPrefix + serialNumber);
				} else {
					String oldNumber = familyModel.getNumber();
					String oldNumberPrefix = oldNumber.substring(0,
							oldNumber.length() - 4);
					if (!numberPrefix.equals(oldNumberPrefix)) {
						familyModel.setNumber(numberPrefix + serialNumber);
					}
				}
				familyModel.update();
			}
		}
	}
	/**
	 * @Description: 创建后台人员
	 * @author Li Bangming;
	 * @date 2017年9月20日 下午12:53:43
	 */
	public Map<String, Object> createBackUser() {
		Map<String, Object> operMap = new HashMap<String, Object>();
		MemberService memberService=new MemberService();
		BackstagecrewService backstageCrewService=new BackstagecrewService();
		String userName = null, userId = null, pwd = null, realName = null;int sex=0;
		List<Member> members=memberService.findAllMembers();
		for(Member member :members){
			if(Utils.isBlankOrEmpty(member.getPhone())){
				continue;
			}
			if(!ArnoldUtils.isMobileNO(member.getPhone())){
				continue;
			}
			userName=member.getPhone();
			pwd=userName.substring(userName.length()-6);
			pwd=MD5Util.string2MD5(pwd);
			realName=member.getName();
			
			Map<String, Object> resultMap=backstageCrewService.saveUserToPoseidon(userName, pwd, realName, sex, ConsConfig.APP_ID);
			int  rc=(Integer) resultMap.get("rc");
			if(rc!=0){
				continue;
			}
			userId=(String) resultMap.get("userId");
			resultMap=backstageCrewService.addBackstageCrew(userId, userName, member.getName(), member.getSexId(), member.getPhone(),
					member.getRoleOfGroupId(), member.getOrgId(), null, null);
		}
		
		LeaderService leaderService=new LeaderService();
		List<Leader> leaders=leaderService.findAllLeaders();
		for(Leader leader :leaders){
			if(Utils.isBlankOrEmpty(leader.getPhone())){
				continue;
			}
			if(!ArnoldUtils.isMobileNO(leader.getPhone())){
				continue;
			}
			userName=leader.getPhone();
			pwd=userName.substring(userName.length()-6);
			pwd=MD5Util.string2MD5(pwd);
			realName=leader.getName();
			
			Map<String, Object> resultMap=backstageCrewService.saveUserToPoseidon(userName, pwd, realName, sex, ConsConfig.APP_ID);
			int  rc=(Integer) resultMap.get("rc");
			if(rc!=0){
				continue;
			}
			userId=(String) resultMap.get("userId");
			resultMap=backstageCrewService.addBackstageCrew(userId, userName, leader.getName(), leader.getSexId(), leader.getPhone(),
			leader.getRaceId(), leader.getOrgId(), null, null);
		}
		operMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		operMap.put(ConstUtils.RETURN_CODE,ConstUtils.DEAL_SUCCESS);
		return operMap;
	}
	/**
	 * 
	 * @Description: 保存后台人员
	 * @author Li Bangming;
	 * @date 2017年6月5日 上午9:48:42
	 * @param id
	 * @return
	 */
	public Map<String, Object> createBackUserByPhone(String phone) {
		Map<String, Object> operMap = new HashMap<String, Object>();
		MemberService memberService=new MemberService();
		Member member=memberService.findByPhone(phone);
		BackstagecrewService backstageCrewService=new BackstagecrewService();
		String userName = null, userId = null, pwd = null, realName = null;int sex=0;
		if(member!=null){
			userName=member.getPhone();
			pwd=userName.substring(userName.length()-6);
			pwd=MD5Util.string2MD5(pwd);
			realName=member.getName();
			
			Map<String, Object> resultMap=backstageCrewService.saveUserToPoseidon(userName, pwd, realName, sex, ConsConfig.APP_ID);
			int  rc=(Integer) resultMap.get("rc");
			if(rc!=0){
				return resultMap;
			}
			userId=(String) resultMap.get("userId");
			try { 
				resultMap=backstageCrewService.addBackstageCrew(userId, userName, member.getName(), member.getSexId(), phone,
						member.getRoleOfGroupId(), member.getOrgId(), null, null);
			} catch (Exception e) {
				operMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
				operMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			}
			operMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			operMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			return resultMap;
		}
		
		LeaderService leaderService=new LeaderService();
		Leader leader=leaderService.findByPhone(phone);
		if(leader!=null){
			userName=leader.getPhone();
			pwd=userName.substring(userName.length()-6);
			pwd=MD5Util.string2MD5(pwd);
			realName=leader.getName();
			
			Map<String, Object> resultMap=backstageCrewService.saveUserToPoseidon(userName, pwd, realName, sex, ConsConfig.APP_ID);
			int  rc=(Integer) resultMap.get("rc");
			if(rc!=0){
				return resultMap;
			}
			userId=(String) resultMap.get("userId");
			try { 
				resultMap=backstageCrewService.addBackstageCrew(userId, userName, leader.getName(), leader.getSexId(), phone,
						leader.getRaceId(), leader.getOrgId(), null, null);
			} catch (Exception e) {
				operMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
				operMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			}
			operMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
			operMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			return resultMap;
		}
		operMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
		operMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
		return operMap;
	}
}

