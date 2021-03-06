package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseStatisticsProject<M extends BaseStatisticsProject<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setYear(java.lang.Integer year) {
		set("year", year);
	}

	public java.lang.Integer getYear() {
		return get("year");
	}

	public void setTotalPlanCount(java.lang.Integer totalPlanCount) {
		set("totalPlanCount", totalPlanCount);
	}

	public java.lang.Integer getTotalPlanCount() {
		return get("totalPlanCount");
	}

	public void setPlanStartCount(java.lang.Integer planStartCount) {
		set("planStartCount", planStartCount);
	}

	public java.lang.Integer getPlanStartCount() {
		return get("planStartCount");
	}

	public void setPlanFinishedCount(java.lang.Integer planFinishedCount) {
		set("planFinishedCount", planFinishedCount);
	}

	public java.lang.Integer getPlanFinishedCount() {
		return get("planFinishedCount");
	}

	public void setStartCount(java.lang.Integer startCount) {
		set("startCount", startCount);
	}

	public java.lang.Integer getStartCount() {
		return get("startCount");
	}

	public void setBuildingCount(java.lang.Integer buildingCount) {
		set("buildingCount", buildingCount);
	}

	public java.lang.Integer getBuildingCount() {
		return get("buildingCount");
	}

	public void setPlanMoney(java.lang.Double planMoney) {
		set("planMoney", planMoney);
	}

	public java.lang.Double getPlanMoney() {
		return get("planMoney");
	}

	public void setYearPlanMoney(java.lang.Double yearPlanMoney) {
		set("yearPlanMoney", yearPlanMoney);
	}

	public java.lang.Double getYearPlanMoney() {
		return get("yearPlanMoney");
	}

	public void setFinishedMoney(java.lang.Double finishedMoney) {
		set("finishedMoney", finishedMoney);
	}

	public java.lang.Double getFinishedMoney() {
		return get("finishedMoney");
	}

	public void setTotalFinishedMoney(java.lang.Double totalFinishedMoney) {
		set("totalFinishedMoney", totalFinishedMoney);
	}

	public java.lang.Double getTotalFinishedMoney() {
		return get("totalFinishedMoney");
	}

}
