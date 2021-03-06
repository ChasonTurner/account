package com.ares.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFlowDetail<M extends BaseFlowDetail<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setTradeType(java.lang.Integer tradeType) {
		set("tradeType", tradeType);
	}

	public java.lang.Integer getTradeType() {
		return get("tradeType");
	}

	public void setOrderId(java.lang.String orderId) {
		set("orderId", orderId);
	}

	public java.lang.String getOrderId() {
		return get("orderId");
	}

	public void setTotalFee(java.lang.Integer totalFee) {
		set("totalFee", totalFee);
	}

	public java.lang.Integer getTotalFee() {
		return get("totalFee");
	}

	public void setOutTradeNo(java.lang.String outTradeNo) {
		set("outTradeNo", outTradeNo);
	}

	public java.lang.String getOutTradeNo() {
		return get("outTradeNo");
	}

	public void setAttach(java.lang.String attach) {
		set("attach", attach);
	}

	public java.lang.String getAttach() {
		return get("attach");
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
