/** 
 * Copyright (c) 2016, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: ArsAppMerchantServer4Gz 
 * Author: YangCheng 
 * Date: 2016年11月23日下午1:08:42 
 */
package com.ares.server.bean;

/**
 * 易路通达平安支付记录
 * 
 * @ClassNmae:PingAnPayRecord
 * @Description:
 */
public class PingAnPayRecord {
	private Long id; // 主键
	private String masterId; // 商户号
	private Integer userId; // 会员号
	private String rechargeOrderNo; // 充值订单号（华软创建）
	private String paymentNo; // 平安支付订单号
	private String openId; // 银行卡快捷支付ID
	private Integer amount; // 金额
	private byte status; // 订单状态
	private String paydate; // 订单创建时间
	private String finishDate; // 订单完成时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRechargeOrderNo() {
		return rechargeOrderNo;
	}

	public void setRechargeOrderNo(String rechargeOrderNo) {
		this.rechargeOrderNo = rechargeOrderNo;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getPaydate() {
		return paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}
