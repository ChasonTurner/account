/** 
 * Copyright (c) 2016, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2016年12月5日下午3:39:19 
 */
package com.ares.server.utils;

import java.io.Serializable;

public class LoadReq implements Serializable {
	private static final long serialVersionUID = 1L;

	private String msgtype;
	private String institutionCode;
	private String algorithmId;
	private String random1;

	private String sessionId;
	private String clientCertificate;
	private String masterkey;
	private String signRandom;

	private String clientHMAC;

	private String billno;
	private String loadtype;
	private String instructionResps;

	private String accountId;
	private String money;

	private String cardArea;
	private String cardno;
	private String balance;

	private String paytype;
	private String tradetime;
	private String tradeno;
	private String payaccount;

	private String deviceno;
	private String userid;

	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getInstitutionCode() {
		return institutionCode;
	}
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	public String getAlgorithmId() {
		return algorithmId;
	}
	public void setAlgorithmId(String algorithmId) {
		this.algorithmId = algorithmId;
	}
	public String getRandom1() {
		return random1;
	}
	public void setRandom1(String random1) {
		this.random1 = random1;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getClientCertificate() {
		return clientCertificate;
	}
	public void setClientCertificate(String clientCertificate) {
		this.clientCertificate = clientCertificate;
	}
	public String getMasterkey() {
		return masterkey;
	}
	public void setMasterkey(String masterkey) {
		this.masterkey = masterkey;
	}
	public String getSignRandom() {
		return signRandom;
	}
	public void setSignRandom(String signRandom) {
		this.signRandom = signRandom;
	}
	public String getClientHMAC() {
		return clientHMAC;
	}
	public void setClientHMAC(String clientHMAC) {
		this.clientHMAC = clientHMAC;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getLoadtype() {
		return loadtype;
	}
	public void setLoadtype(String loadtype) {
		this.loadtype = loadtype;
	}
	public String getInstructionResps() {
		return instructionResps;
	}
	public void setInstructionResps(String instructionResps) {
		this.instructionResps = instructionResps;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCardArea() {
		return cardArea;
	}
	public void setCardArea(String cardArea) {
		this.cardArea = cardArea;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getTradetime() {
		return tradetime;
	}
	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}
	public String getTradeno() {
		return tradeno;
	}
	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}
	public String getPayaccount() {
		return payaccount;
	}
	public void setPayaccount(String payaccount) {
		this.payaccount = payaccount;
	}
	public String getDeviceno() {
		return deviceno;
	}
	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
