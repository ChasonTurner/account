package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseStaticFamilyIncome<M extends BaseStaticFamilyIncome<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setYear(java.lang.String year) {
		set("year", year);
	}

	public java.lang.String getYear() {
		return get("year");
	}

	public void setFamilyId(java.lang.String familyId) {
		set("familyId", familyId);
	}

	public java.lang.String getFamilyId() {
		return get("familyId");
	}

	public void setRegionId(java.lang.String regionId) {
		set("regionId", regionId);
	}

	public java.lang.String getRegionId() {
		return get("regionId");
	}

	public void setCountryId(java.lang.String countryId) {
		set("countryId", countryId);
	}

	public java.lang.String getCountryId() {
		return get("countryId");
	}

	public void setCountryName(java.lang.String countryName) {
		set("countryName", countryName);
	}

	public java.lang.String getCountryName() {
		return get("countryName");
	}

	public void setHamletId(java.lang.String hamletId) {
		set("hamletId", hamletId);
	}

	public java.lang.String getHamletId() {
		return get("hamletId");
	}

	public void setHamletName(java.lang.String hamletName) {
		set("hamletName", hamletName);
	}

	public java.lang.String getHamletName() {
		return get("hamletName");
	}

	public void setGroupId(java.lang.String groupId) {
		set("groupId", groupId);
	}

	public java.lang.String getGroupId() {
		return get("groupId");
	}

	public void setGroupName(java.lang.String groupName) {
		set("groupName", groupName);
	}

	public java.lang.String getGroupName() {
		return get("groupName");
	}

	public void setVillagerId(java.lang.String villagerId) {
		set("villagerId", villagerId);
	}

	public java.lang.String getVillagerId() {
		return get("villagerId");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setVillagerCount(java.lang.Integer villagerCount) {
		set("villagerCount", villagerCount);
	}

	public java.lang.Integer getVillagerCount() {
		return get("villagerCount");
	}

	public void setYearIncome(java.lang.Double yearIncome) {
		set("yearIncome", yearIncome);
	}

	public java.lang.Double getYearIncome() {
		return get("yearIncome");
	}

	public void setYearAnalyseIncome(java.lang.Double yearAnalyseIncome) {
		set("yearAnalyseIncome", yearAnalyseIncome);
	}

	public java.lang.Double getYearAnalyseIncome() {
		return get("yearAnalyseIncome");
	}

	public void setFamilyYearIncome(java.lang.Double familyYearIncome) {
		set("familyYearIncome", familyYearIncome);
	}

	public java.lang.Double getFamilyYearIncome() {
		return get("familyYearIncome");
	}

	public void setAverageIncome(java.lang.Double averageIncome) {
		set("averageIncome", averageIncome);
	}

	public java.lang.Double getAverageIncome() {
		return get("averageIncome");
	}

	public void setAverageAnalyseIncome(java.lang.Double averageAnalyseIncome) {
		set("averageAnalyseIncome", averageAnalyseIncome);
	}

	public java.lang.Double getAverageAnalyseIncome() {
		return get("averageAnalyseIncome");
	}

	public void setPersonYearIncome(java.lang.Double personYearIncome) {
		set("personYearIncome", personYearIncome);
	}

	public java.lang.Double getPersonYearIncome() {
		return get("personYearIncome");
	}

	public void setSalaryIncome(java.lang.Double salaryIncome) {
		set("salaryIncome", salaryIncome);
	}

	public java.lang.Double getSalaryIncome() {
		return get("salaryIncome");
	}

	public void setFamilyIncome(java.lang.Double familyIncome) {
		set("familyIncome", familyIncome);
	}

	public java.lang.Double getFamilyIncome() {
		return get("familyIncome");
	}

	public void setFamilyAnalyseIncome(java.lang.Double familyAnalyseIncome) {
		set("familyAnalyseIncome", familyAnalyseIncome);
	}

	public java.lang.Double getFamilyAnalyseIncome() {
		return get("familyAnalyseIncome");
	}

	public void setPropertyIncome(java.lang.Double propertyIncome) {
		set("propertyIncome", propertyIncome);
	}

	public java.lang.Double getPropertyIncome() {
		return get("propertyIncome");
	}

	public void setSubsidy(java.lang.Double subsidy) {
		set("subsidy", subsidy);
	}

	public java.lang.Double getSubsidy() {
		return get("subsidy");
	}

	public void setProductPay(java.lang.Double productPay) {
		set("productPay", productPay);
	}

	public java.lang.Double getProductPay() {
		return get("productPay");
	}

	public void setOtherPay(java.lang.Double otherPay) {
		set("otherPay", otherPay);
	}

	public java.lang.Double getOtherPay() {
		return get("otherPay");
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