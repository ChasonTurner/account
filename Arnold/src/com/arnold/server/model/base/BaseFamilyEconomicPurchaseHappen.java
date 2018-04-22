package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFamilyEconomicPurchaseHappen<M extends BaseFamilyEconomicPurchaseHappen<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setFamilyEconomicId(java.lang.String familyEconomicId) {
		set("familyEconomicId", familyEconomicId);
	}

	public java.lang.String getFamilyEconomicId() {
		return get("familyEconomicId");
	}

	public void setFamilyId(java.lang.String familyId) {
		set("familyId", familyId);
	}

	public java.lang.String getFamilyId() {
		return get("familyId");
	}

	public void setParentTypeId(java.lang.String parentTypeId) {
		set("parentTypeId", parentTypeId);
	}

	public java.lang.String getParentTypeId() {
		return get("parentTypeId");
	}

	public void setTypeId(java.lang.String typeId) {
		set("typeId", typeId);
	}

	public java.lang.String getTypeId() {
		return get("typeId");
	}

	public void setUnitPrice(java.lang.Double unitPrice) {
		set("unitPrice", unitPrice);
	}

	public java.lang.Double getUnitPrice() {
		return get("unitPrice");
	}

	public void setAmount(java.lang.Integer amount) {
		set("amount", amount);
	}

	public java.lang.Integer getAmount() {
		return get("amount");
	}

	public void setUnderwritingAmount(java.lang.Integer underwritingAmount) {
		set("underwritingAmount", underwritingAmount);
	}

	public java.lang.Integer getUnderwritingAmount() {
		return get("underwritingAmount");
	}

	public void setPrice(java.lang.Double price) {
		set("price", price);
	}

	public java.lang.Double getPrice() {
		return get("price");
	}

	public void setMemberId(java.lang.String memberId) {
		set("memberId", memberId);
	}

	public java.lang.String getMemberId() {
		return get("memberId");
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