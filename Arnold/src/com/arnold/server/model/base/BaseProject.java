package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseProject<M extends BaseProject<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}

	public java.lang.String getType() {
		return get("type");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setCotentAndArea(java.lang.String cotentAndArea) {
		set("cotentAndArea", cotentAndArea);
	}

	public java.lang.String getCotentAndArea() {
		return get("cotentAndArea");
	}

	public void setKind(java.lang.String kind) {
		set("kind", kind);
	}

	public java.lang.String getKind() {
		return get("kind");
	}

	public void setHelpType(java.lang.String helpType) {
		set("helpType", helpType);
	}

	public java.lang.String getHelpType() {
		return get("helpType");
	}

	public void setNatureId(java.lang.String natureId) {
		set("natureId", natureId);
	}

	public java.lang.String getNatureId() {
		return get("natureId");
	}

	public void setVillageName(java.lang.String villageName) {
		set("villageName", villageName);
	}

	public java.lang.String getVillageName() {
		return get("villageName");
	}

	public void setFundation(java.lang.Double fundation) {
		set("fundation", fundation);
	}

	public java.lang.Double getFundation() {
		return get("fundation");
	}

	public void setDepartmentMoney(java.lang.Double departmentMoney) {
		set("departmentMoney", departmentMoney);
	}

	public java.lang.Double getDepartmentMoney() {
		return get("departmentMoney");
	}

	public void setSelfMoney(java.lang.Double selfMoney) {
		set("selfMoney", selfMoney);
	}

	public java.lang.Double getSelfMoney() {
		return get("selfMoney");
	}

	public void setFinalMoney(java.lang.Double finalMoney) {
		set("finalMoney", finalMoney);
	}

	public java.lang.Double getFinalMoney() {
		return get("finalMoney");
	}

	public void setPovertyCount(java.lang.Integer povertyCount) {
		set("povertyCount", povertyCount);
	}

	public java.lang.Integer getPovertyCount() {
		return get("povertyCount");
	}

	public void setPersonCount(java.lang.Integer personCount) {
		set("personCount", personCount);
	}

	public java.lang.Integer getPersonCount() {
		return get("personCount");
	}

	public void setYear(java.lang.Integer year) {
		set("year", year);
	}

	public java.lang.Integer getYear() {
		return get("year");
	}

	public void setStartTime(java.util.Date startTime) {
		set("startTime", startTime);
	}

	public java.util.Date getStartTime() {
		return get("startTime");
	}

	public void setRealStartTime(java.util.Date realStartTime) {
		set("realStartTime", realStartTime);
	}

	public java.util.Date getRealStartTime() {
		return get("realStartTime");
	}

	public void setEndTime(java.util.Date endTime) {
		set("endTime", endTime);
	}

	public java.util.Date getEndTime() {
		return get("endTime");
	}

	public void setRealEndTime(java.util.Date realEndTime) {
		set("realEndTime", realEndTime);
	}

	public java.util.Date getRealEndTime() {
		return get("realEndTime");
	}

	public void setMontheMoney(java.lang.Double montheMoney) {
		set("montheMoney", montheMoney);
	}

	public java.lang.Double getMontheMoney() {
		return get("montheMoney");
	}

	public void setCompleteMoney(java.lang.Double completeMoney) {
		set("completeMoney", completeMoney);
	}

	public java.lang.Double getCompleteMoney() {
		return get("completeMoney");
	}

	public void setAllProgress(java.lang.Integer allProgress) {
		set("allProgress", allProgress);
	}

	public java.lang.Integer getAllProgress() {
		return get("allProgress");
	}

	public void setWriter(java.lang.String writer) {
		set("writer", writer);
	}

	public java.lang.String getWriter() {
		return get("writer");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

	public void setNowWriter(java.lang.String nowWriter) {
		set("nowWriter", nowWriter);
	}

	public java.lang.String getNowWriter() {
		return get("nowWriter");
	}

	public void setNowCreateTime(java.util.Date nowCreateTime) {
		set("nowCreateTime", nowCreateTime);
	}

	public java.util.Date getNowCreateTime() {
		return get("nowCreateTime");
	}

}
