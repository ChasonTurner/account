package com.ares.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAccountBalance<M extends BaseAccountBalance<M>> extends Model<M> implements IBean {

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

	public void setType(java.lang.Integer type) {
		set("type", type);
	}

	public java.lang.Integer getType() {
		return get("type");
	}

	public void setAmount(java.lang.Integer amount) {
		set("amount", amount);
	}

	public java.lang.Integer getAmount() {
		return get("amount");
	}

	public void setPayCode(java.lang.String payCode) {
		set("payCode", payCode);
	}

	public java.lang.String getPayCode() {
		return get("payCode");
	}

	public void setPayLevel(java.lang.Integer payLevel) {
		set("payLevel", payLevel);
	}

	public java.lang.Integer getPayLevel() {
		return get("payLevel");
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

	public void setUpdataTime(java.util.Date updataTime) {
		set("updataTime", updataTime);
	}

	public java.util.Date getUpdataTime() {
		return get("updataTime");
	}

}