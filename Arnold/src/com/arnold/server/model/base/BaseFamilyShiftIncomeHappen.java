package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFamilyShiftIncomeHappen<M extends BaseFamilyShiftIncomeHappen<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setFmailyId(java.lang.String fmailyId) {
		set("fmailyId", fmailyId);
	}

	public java.lang.String getFmailyId() {
		return get("fmailyId");
	}

	public void setIncomTypeId(java.lang.String incomTypeId) {
		set("incomTypeId", incomTypeId);
	}

	public java.lang.String getIncomTypeId() {
		return get("incomTypeId");
	}

	public void setIncomeTime(java.util.Date incomeTime) {
		set("incomeTime", incomeTime);
	}

	public java.util.Date getIncomeTime() {
		return get("incomeTime");
	}

	public void setIncome(java.lang.Double income) {
		set("income", income);
	}

	public java.lang.Double getIncome() {
		return get("income");
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
