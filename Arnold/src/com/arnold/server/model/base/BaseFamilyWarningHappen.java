package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFamilyWarningHappen<M extends BaseFamilyWarningHappen<M>> extends Model<M> implements IBean {

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

	public void setWarnUserId(java.lang.String warnUserId) {
		set("warnUserId", warnUserId);
	}

	public java.lang.String getWarnUserId() {
		return get("warnUserId");
	}

	public void setWarnTime(java.util.Date warnTime) {
		set("warnTime", warnTime);
	}

	public java.util.Date getWarnTime() {
		return get("warnTime");
	}

	public void setSeriousWarnTime(java.util.Date seriousWarnTime) {
		set("seriousWarnTime", seriousWarnTime);
	}

	public java.util.Date getSeriousWarnTime() {
		return get("seriousWarnTime");
	}

	public void setDisWarnTime(java.lang.String disWarnTime) {
		set("disWarnTime", disWarnTime);
	}

	public java.lang.String getDisWarnTime() {
		return get("disWarnTime");
	}

	public void setWarnContent(java.lang.String warnContent) {
		set("warnContent", warnContent);
	}

	public java.lang.String getWarnContent() {
		return get("warnContent");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public void setIsValid(java.lang.Integer isValid) {
		set("isValid", isValid);
	}

	public java.lang.Integer getIsValid() {
		return get("isValid");
	}

}
