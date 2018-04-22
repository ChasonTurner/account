/** 
 * Copyright (c) 2016, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: ArsAppMerchantServer4Gz 
 * Author: YangCheng 
 * Date: 2016年11月23日下午1:08:42 
 */
package com.ares.server.bean;
/**
 * 易路通达平安支付银行卡信息
 * @ClassNmae:BankModel
 * @Description:
 */
public class BankModel
{
	private String masterId;//所属商户
	
	private String customerId;//所属会员
	
	private String OpenId;	//银行开通ID
	
	private String accNo;	//银行卡后4位
	
	private String bankType;//01是借记卡，02是信用卡
	
	private String telephone;//预留手机号码（中间四位为星号）
	
	private String plantId;	//支付平台ID
	
	private String plantBankId;	//支付平台银行ID
	
	private String bankName;	//银行名称
	
	private String date;	//开通日期
	
	private String status;	//开通状态
	
	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public String getOpenId()
	{
		return OpenId;
	}

	public void setOpenId(String openId)
	{
		OpenId = openId;
	}

	public String getAccNo()
	{
		return accNo;
	}

	public void setAccNo(String accNo)
	{
		this.accNo = accNo;
	}

	public String getBankType()
	{
		return bankType;
	}

	public void setBankType(String bankType)
	{
		this.bankType = bankType;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public String getPlantId()
	{
		return plantId;
	}

	public void setPlantId(String plantId)
	{
		this.plantId = plantId;
	}

	public String getPlantBankId()
	{
		return plantBankId;
	}

	public void setPlantBankId(String plantBankId)
	{
		this.plantBankId = plantBankId;
	}
}
