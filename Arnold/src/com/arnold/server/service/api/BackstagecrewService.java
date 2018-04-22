package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.bean.request.RequestBackStageCrew;
import com.arnold.server.config.ConsConfig;
import com.arnold.server.model.Backstagecrew;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author ChangGui Pan
 * @time 2017年8月20日 下午2:35:40
 * @description TODO
 */
public class BackstagecrewService extends BaseService {

	public Map<String, Object> addBackstageCrew(String userId, String username,
			String name, String sexId, String phone, String roleId,
			String orgId, String regionId, Date lastLoginTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Backstagecrew model = Backstagecrew.dao.findByUserId(userId);
		if (model != null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG,
					ErrorCodeConst.MEMBER_EXIST_CODE_STR);
			return resultMap;
		}

		Backstagecrew backstageCrew = new Backstagecrew();
		backstageCrew.setId(Utils.create36UUID());
		backstageCrew.setUserId(userId);
		backstageCrew.setUsername(username);
		backstageCrew.setName(name);
		backstageCrew.setSexId(sexId);
		backstageCrew.setPhone(phone);
		backstageCrew.setRoleId(roleId);
		backstageCrew.setOrgId(orgId);
		backstageCrew.setRegionId(regionId);
		backstageCrew.setLastLoginTime(lastLoginTime);
		backstageCrew.setIsValid(0);

		backstageCrew.save();

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> updateBackstageCrew(String userId,
			String username, String name, String sexId, String phone,
			String roleId, String orgId, String regionId, Date lastLoginTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Backstagecrew backstageCrew = Backstagecrew.dao.findByUserId(userId);
		if (backstageCrew == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG,
					ErrorCodeConst.MEMBER_INEXISTENCE_CODE_STR);
			return resultMap;
		}

		// backstageCrew.setUserId(userId);
		if (!Utils.isBlankOrEmpty(username))
			backstageCrew.setUsername(username);
		if (!Utils.isBlankOrEmpty(name))
			backstageCrew.setName(name);
		if (!Utils.isBlankOrEmpty(sexId))
			backstageCrew.setSexId(sexId);
		if (!Utils.isBlankOrEmpty(phone))
			backstageCrew.setPhone(phone);
		if (!Utils.isBlankOrEmpty(roleId))
			backstageCrew.setRoleId(roleId);
		if (!Utils.isBlankOrEmpty(orgId))
			backstageCrew.setOrgId(orgId);
		if (!Utils.isBlankOrEmpty(regionId))
			backstageCrew.setRegionId(regionId);
		if (lastLoginTime != null)
			backstageCrew.setLastLoginTime(lastLoginTime);

		backstageCrew.update();

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/*public Map<String, Object> pageBackstageCrew(int pageNumber, int pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Page<Backstagecrew> page = Backstagecrew.dao.pageBackstageCrew(
				pageNumber, pageSize);

		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		return resultMap;
	}*/
	
	public Map<String, Object> pageBackstageCrew(Integer pageNumber,
			Integer pageSize, RequestBackStageCrew requestBean) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Page<Backstagecrew> page = Backstagecrew.dao.pageBackstageCrew(
				pageNumber, pageSize, requestBean);

		resultMap.put(ConstUtils.R_PAGE, page);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		return resultMap;
	}

	public Map<String, Object> saveUserToPoseidon(String userName, String pwd,
			String realName, int sex, String appId) {
		Map<String, Object> operMap = new HashMap<String, Object>();
		operMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
		String url = ConsConfig.POSEIDON_HOST + "/iuser/add_user?sex=" + sex
				+ "&realName=" + realName + "&pwd=" + pwd + "&userName="
				+ userName + "&appIdP=" + appId;
		Map<String, Object> result = null;
		try {
			result = requestResultPost(url);
		} catch (Exception e) {
			operMap.put(ConstUtils.RETURN_CODE,
					ArnoldUtils.RMOTE_POSEIDON_SERVER_ERRO);
			operMap.put(ConstUtils.R_MSG,
					ArnoldUtils.RMOTE_POSEIDON_SERVER_ERRO_STR);
			return operMap;
		}
		if (result == null || (result != null && result.size() == 0)) {
			operMap.put(ConstUtils.RETURN_CODE,
					ArnoldUtils.RMOTE_POSEIDON_SERVER_ERRO);
			operMap.put(ConstUtils.R_MSG,
					ArnoldUtils.RMOTE_POSEIDON_SERVER_ERRO_STR);
			return operMap;
		}
		return result;
	}

	public Backstagecrew updateBackstageCrewByLogin(String userId) {

		Backstagecrew backstageCrew = Backstagecrew.dao.findByUserId(userId);
		if (backstageCrew != null) {
			backstageCrew.setLastLoginTime(new Date());
			backstageCrew.update();
		}

		return backstageCrew;
	}

	public Backstagecrew findById(String userId) {
		Backstagecrew backstageCrew = Backstagecrew.dao.findByUserId(userId);
		return backstageCrew;
	}

	public static Map<String, Object> updateBackstagecrew(String id,
			String roleId, String roleName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Backstagecrew backstageCrew = Backstagecrew.dao.findById(id);
		if(backstageCrew != null){
			backstageCrew.setRoleId(roleId);
			backstageCrew.setRoleName(roleName);
			backstageCrew.update();
		}
		
		
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		return resultMap;
	}

}
