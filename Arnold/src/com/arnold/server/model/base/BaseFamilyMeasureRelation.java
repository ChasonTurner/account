package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFamilyMeasureRelation<M extends BaseFamilyMeasureRelation<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setFamilyId(java.lang.String familyId) {
		set("familyId", familyId);
	}

	public java.lang.String getFamilyId() {
		return get("familyId");
	}

	public void setMeasureId(java.lang.String measureId) {
		set("measureId", measureId);
	}

	public java.lang.String getMeasureId() {
		return get("measureId");
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

}
