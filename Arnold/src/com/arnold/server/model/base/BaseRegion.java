package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRegion<M extends BaseRegion<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setShortName(java.lang.String shortName) {
		set("shortName", shortName);
	}

	public java.lang.String getShortName() {
		return get("shortName");
	}

	public void setFullName(java.lang.String fullName) {
		set("fullName", fullName);
	}

	public java.lang.String getFullName() {
		return get("fullName");
	}

	public void setParentId(java.lang.String parentId) {
		set("parentId", parentId);
	}

	public java.lang.String getParentId() {
		return get("parentId");
	}

	public void setParentName(java.lang.String parentName) {
		set("parentName", parentName);
	}

	public java.lang.String getParentName() {
		return get("parentName");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("updateTime", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("updateTime");
	}

	public void setIsValid(java.lang.Integer isValid) {
		set("isValid", isValid);
	}

	public java.lang.Integer getIsValid() {
		return get("isValid");
	}

	public void setRegionType(java.lang.Integer regionType) {
		set("regionType", regionType);
	}

	public java.lang.Integer getRegionType() {
		return get("regionType");
	}

	public void setProperty(java.lang.Integer property) {
		set("property", property);
	}

	public java.lang.Integer getProperty() {
		return get("property");
	}

	public void setCityCode(java.lang.String cityCode) {
		set("cityCode", cityCode);
	}

	public java.lang.String getCityCode() {
		return get("cityCode");
	}

	public void setAdCode(java.lang.String adCode) {
		set("adCode", adCode);
	}

	public java.lang.String getAdCode() {
		return get("adCode");
	}

	public void setLongitude(java.lang.String longitude) {
		set("longitude", longitude);
	}

	public java.lang.String getLongitude() {
		return get("longitude");
	}

	public void setLatitude(java.lang.String latitude) {
		set("latitude", latitude);
	}

	public java.lang.String getLatitude() {
		return get("latitude");
	}

}
