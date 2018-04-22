package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseArea<M extends BaseArea<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setLevel(java.lang.String level) {
		set("level", level);
	}

	public java.lang.String getLevel() {
		return get("level");
	}

	public void setPersonCount(java.lang.Integer personCount) {
		set("personCount", personCount);
	}

	public java.lang.Integer getPersonCount() {
		return get("personCount");
	}

	public void setPopulation(java.lang.Integer population) {
		set("population", population);
	}

	public java.lang.Integer getPopulation() {
		return get("population");
	}

	public void setRegionid(java.lang.String regionid) {
		set("regionid", regionid);
	}

	public java.lang.String getRegionid() {
		return get("regionid");
	}

	public void setIsValid(java.lang.Integer isValid) {
		set("isValid", isValid);
	}

	public java.lang.Integer getIsValid() {
		return get("isValid");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

}
