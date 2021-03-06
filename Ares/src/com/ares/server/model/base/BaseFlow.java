package com.ares.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFlow<M extends BaseFlow<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setAccountId(java.lang.String accountId) {
		set("accountId", accountId);
	}

	public java.lang.String getAccountId() {
		return get("accountId");
	}

	public void setChannelType(java.lang.Integer channelType) {
		set("channelType", channelType);
	}

	public java.lang.Integer getChannelType() {
		return get("channelType");
	}

	public void setOrderId(java.lang.String orderId) {
		set("orderId", orderId);
	}

	public java.lang.String getOrderId() {
		return get("orderId");
	}

	public void setBalanceType(java.lang.Integer balanceType) {
		set("balanceType", balanceType);
	}

	public java.lang.Integer getBalanceType() {
		return get("balanceType");
	}

	public void setTotalFee(java.lang.Integer totalFee) {
		set("totalFee", totalFee);
	}

	public java.lang.Integer getTotalFee() {
		return get("totalFee");
	}

	public void setTransferId(java.lang.String transferId) {
		set("transferId", transferId);
	}

	public java.lang.String getTransferId() {
		return get("transferId");
	}

	public void setFreezeId(java.lang.String freezeId) {
		set("freezeId", freezeId);
	}

	public java.lang.String getFreezeId() {
		return get("freezeId");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
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
