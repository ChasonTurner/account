package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePropertyVillage<M extends BasePropertyVillage<M>> extends Model<M> implements IBean {

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

	public void setAddress(java.lang.String address) {
		set("address", address);
	}

	public java.lang.String getAddress() {
		return get("address");
	}

	public void setPercent(java.lang.String percent) {
		set("percent", percent);
	}

	public java.lang.String getPercent() {
		return get("percent");
	}

	public void setPartyMemberCount(java.lang.String partyMemberCount) {
		set("partyMemberCount", partyMemberCount);
	}

	public java.lang.String getPartyMemberCount() {
		return get("partyMemberCount");
	}

	public void setPoulationCount(java.lang.String poulationCount) {
		set("poulationCount", poulationCount);
	}

	public java.lang.String getPoulationCount() {
		return get("poulationCount");
	}

	public void setFiveGuaranteedCount(java.lang.String fiveGuaranteedCount) {
		set("fiveGuaranteedCount", fiveGuaranteedCount);
	}

	public java.lang.String getFiveGuaranteedCount() {
		return get("fiveGuaranteedCount");
	}

	public void setPoorPopulation(java.lang.String poorPopulation) {
		set("poorPopulation", poorPopulation);
	}

	public java.lang.String getPoorPopulation() {
		return get("poorPopulation");
	}

	public void setLowIncomeCount(java.lang.String lowIncomeCount) {
		set("lowIncomeCount", lowIncomeCount);
	}

	public java.lang.String getLowIncomeCount() {
		return get("lowIncomeCount");
	}

	public void setRemovalPlan(java.lang.String removalPlan) {
		set("removalPlan", removalPlan);
	}

	public java.lang.String getRemovalPlan() {
		return get("removalPlan");
	}

	public void setHouseTransformPlan(java.lang.String houseTransformPlan) {
		set("houseTransformPlan", houseTransformPlan);
	}

	public java.lang.String getHouseTransformPlan() {
		return get("houseTransformPlan");
	}

	public void setRemovalTime(java.util.Date removalTime) {
		set("removalTime", removalTime);
	}

	public java.util.Date getRemovalTime() {
		return get("removalTime");
	}

	public void setTransformTime(java.util.Date transformTime) {
		set("transformTime", transformTime);
	}

	public java.util.Date getTransformTime() {
		return get("transformTime");
	}

	public void setIsTapWater(java.lang.String isTapWater) {
		set("isTapWater", isTapWater);
	}

	public java.lang.String getIsTapWater() {
		return get("isTapWater");
	}

	public void setIsVillageRoad(java.lang.String isVillageRoad) {
		set("isVillageRoad", isVillageRoad);
	}

	public java.lang.String getIsVillageRoad() {
		return get("isVillageRoad");
	}

	public void setIsSeriousPatient(java.lang.String isSeriousPatient) {
		set("isSeriousPatient", isSeriousPatient);
	}

	public java.lang.String getIsSeriousPatient() {
		return get("isSeriousPatient");
	}

	public void setIsGroupRoad(java.lang.String isGroupRoad) {
		set("isGroupRoad", isGroupRoad);
	}

	public java.lang.String getIsGroupRoad() {
		return get("isGroupRoad");
	}

	public void setIsPrimarySchool(java.lang.String isPrimarySchool) {
		set("isPrimarySchool", isPrimarySchool);
	}

	public java.lang.String getIsPrimarySchool() {
		return get("isPrimarySchool");
	}

	public void setChildDropoutCount(java.lang.String childDropoutCount) {
		set("childDropoutCount", childDropoutCount);
	}

	public java.lang.String getChildDropoutCount() {
		return get("childDropoutCount");
	}

	public void setAverageIncome(java.lang.String averageIncome) {
		set("averageIncome", averageIncome);
	}

	public java.lang.String getAverageIncome() {
		return get("averageIncome");
	}

	public void setEconomyScale(java.lang.String economyScale) {
		set("economyScale", economyScale);
	}

	public java.lang.String getEconomyScale() {
		return get("economyScale");
	}

	public void setPerCultivatedArea(java.lang.String perCultivatedArea) {
		set("perCultivatedArea", perCultivatedArea);
	}

	public java.lang.String getPerCultivatedArea() {
		return get("perCultivatedArea");
	}

	public void setPerWoodland(java.lang.String perWoodland) {
		set("perWoodland", perWoodland);
	}

	public java.lang.String getPerWoodland() {
		return get("perWoodland");
	}

	public void setDifficulty(java.lang.String difficulty) {
		set("difficulty", difficulty);
	}

	public java.lang.String getDifficulty() {
		return get("difficulty");
	}

	public void setWriterId(java.lang.String writerId) {
		set("writerId", writerId);
	}

	public java.lang.String getWriterId() {
		return get("writerId");
	}

	public void setOperatorId(java.lang.String operatorId) {
		set("operatorId", operatorId);
	}

	public java.lang.String getOperatorId() {
		return get("operatorId");
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

}