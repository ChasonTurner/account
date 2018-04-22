package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCollectiveEconomyPurchaseHappen<M extends BaseCollectiveEconomyPurchaseHappen<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setCollectiveEconomyId(java.lang.String collectiveEconomyId) {
		set("collectiveEconomyId", collectiveEconomyId);
	}

	public java.lang.String getCollectiveEconomyId() {
		return get("collectiveEconomyId");
	}

	public void setRegionId(java.lang.String regionId) {
		set("regionId", regionId);
	}

	public java.lang.String getRegionId() {
		return get("regionId");
	}

	public void setIndustryTypeId(java.lang.String industryTypeId) {
		set("industryTypeId", industryTypeId);
	}

	public java.lang.String getIndustryTypeId() {
		return get("industryTypeId");
	}

	public void setPurchaseContent(java.lang.String purchaseContent) {
		set("purchaseContent", purchaseContent);
	}

	public java.lang.String getPurchaseContent() {
		return get("purchaseContent");
	}

	public void setTotalPrice(java.lang.Double totalPrice) {
		set("totalPrice", totalPrice);
	}

	public java.lang.Double getTotalPrice() {
		return get("totalPrice");
	}

	public void setOrgId(java.lang.String orgId) {
		set("orgId", orgId);
	}

	public java.lang.String getOrgId() {
		return get("orgId");
	}

	public void setCreatorId(java.lang.String creatorId) {
		set("creatorId", creatorId);
	}

	public java.lang.String getCreatorId() {
		return get("creatorId");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

	public void setOperatorId(java.lang.String operatorId) {
		set("operatorId", operatorId);
	}

	public java.lang.String getOperatorId() {
		return get("operatorId");
	}

	public void setOperatorTime(java.util.Date operatorTime) {
		set("operatorTime", operatorTime);
	}

	public java.util.Date getOperatorTime() {
		return get("operatorTime");
	}

	public void setTradeTime(java.util.Date tradeTime) {
		set("tradeTime", tradeTime);
	}

	public java.util.Date getTradeTime() {
		return get("tradeTime");
	}

}
