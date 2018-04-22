package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBackstagecrew<M extends BaseBackstagecrew<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setSerialNumber(java.lang.Integer serialNumber) {
		set("serialNumber", serialNumber);
	}

	public java.lang.Integer getSerialNumber() {
		return get("serialNumber");
	}

	public void setUserId(java.lang.String userId) {
		set("userId", userId);
	}

	public java.lang.String getUserId() {
		return get("userId");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}

	public java.lang.String getUsername() {
		return get("username");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setSexId(java.lang.String sexId) {
		set("sexId", sexId);
	}

	public java.lang.String getSexId() {
		return get("sexId");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setRoleId(java.lang.String roleId) {
		set("roleId", roleId);
	}

	public java.lang.String getRoleId() {
		return get("roleId");
	}

	public void setRoleName(java.lang.String roleName) {
		set("roleName", roleName);
	}

	public java.lang.String getRoleName() {
		return get("roleName");
	}

	public void setOrgId(java.lang.String orgId) {
		set("orgId", orgId);
	}

	public java.lang.String getOrgId() {
		return get("orgId");
	}

	public void setRegionId(java.lang.String regionId) {
		set("regionId", regionId);
	}

	public java.lang.String getRegionId() {
		return get("regionId");
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		set("lastLoginTime", lastLoginTime);
	}

	public java.util.Date getLastLoginTime() {
		return get("lastLoginTime");
	}

	public void setIsValid(java.lang.Integer isValid) {
		set("isValid", isValid);
	}

	public java.lang.Integer getIsValid() {
		return get("isValid");
	}

}