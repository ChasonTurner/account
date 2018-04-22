/** 
 * Copyright (c) 2016, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: ArsAppMerchantServer4Gz 
 * Author: YangCheng 
 * Date: 2016年11月29日下午11:12:34 
 */
package com.ares.server.bean;

/**
 * 平安支付订单信息
 * @ClassNmae:PingAnOrderBean
 * @Description:
 */
public class PingAnOrderBean {
	private byte status; 		// 订单状态 1：成功 2：失败 0：未成功
	private String date; 		// 支付完成时间yyyymmddhhmmss
	private float charge; 		// 订单手续费，12位整数，2位小数
	private String masterId; 	// 商户号
	private String orderId; 	// 订单号
	private String currency; 	// 币种
	private float amount; 		// 订单金额，12位整数，2位小数
	private String objectName; 	// 款项描述
	private String paydate; 	// 下单时间yyyymmddhhmmss
	private Integer validtime; 	// 订单有效期（毫秒），0不生效
	private String remark; 		// 备注字段
	private String errorCode; 	// 错误返回相应的错误码，正常返回为空
	private String errorMsg; 	// 错误码对应的错误说明，正常返回为空
	private String settleflg;	// 本金清算标志
	private String settletime;	// 本金清算时间
	private String chargeflg;	// 手续费清算标志
	private String chargetime;	// 手续费清算时间

	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getCharge() {
		return charge;
	}
	public void setCharge(float charge) {
		this.charge = charge;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public Integer getValidtime() {
		return validtime;
	}
	public void setValidtime(Integer validtime) {
		this.validtime = validtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getSettleflg() {
		return settleflg;
	}
	public void setSettleflg(String settleflg) {
		this.settleflg = settleflg;
	}
	public String getSettletime() {
		return settletime;
	}
	public void setSettletime(String settletime) {
		this.settletime = settletime;
	}
	public String getChargeflg() {
		return chargeflg;
	}
	public void setChargeflg(String chargeflg) {
		this.chargeflg = chargeflg;
	}
	public String getChargetime() {
		return chargetime;
	}
	public void setChargetime(String chargetime) {
		this.chargetime = chargetime;
	}
	
	
}
