package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRegionIndustryIncomeHappen<M extends BaseRegionIndustryIncomeHappen<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setRegionId(java.lang.String regionId) {
		set("regionId", regionId);
	}

	public java.lang.String getRegionId() {
		return get("regionId");
	}

	public void setIndustryId(java.lang.String industryId) {
		set("industryId", industryId);
	}

	public java.lang.String getIndustryId() {
		return get("industryId");
	}

	public void setIncome(java.lang.Double income) {
		set("income", income);
	}

	public java.lang.Double getIncome() {
		return get("income");
	}

	public void setIncomeTime(java.util.Date incomeTime) {
		set("incomeTime", incomeTime);
	}

	public java.util.Date getIncomeTime() {
		return get("incomeTime");
	}

	public void setCreateUserId(java.lang.String createUserId) {
		set("createUserId", createUserId);
	}

	public java.lang.String getCreateUserId() {
		return get("createUserId");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

}
