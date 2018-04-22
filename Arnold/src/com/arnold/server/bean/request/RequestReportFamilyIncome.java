package com.arnold.server.bean.request;

import java.io.Serializable;

/**  
 * @description  report家庭年度收入
 * @author luzy
 * @date 2017年12月25日
 */
public class RequestReportFamilyIncome implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431880440123846835L;

	/** 家庭ID */
	private String familyId;
	
	/** 统计年度 */
	private String year;

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
