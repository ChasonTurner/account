/** 
 * Copyright (c) 2016, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2016年12月5日下午2:57:06 
 */
package com.ares.server.utils;

import java.io.Serializable;

public class LoadResp implements Serializable {
	 private static final long serialVersionUID = 1L;

	    private String code;
	    private String sessionId;
	    private String serverCertificate;

	    private String signServerCer;
	    private String random2;

	    private String serverHMAC;

	    private String instructions;

	    private String billno;

	    private Integer rechargeUpperLimit;
	    private Integer rechargeDownLimit;
	    private String amount;

	    private String message;

	    private String type;

	    public LoadResp() {
	    }

	    // Handshake1
	    public LoadResp(String sessionId, String serverCertificate, String code, String random2) {
	        this.sessionId = sessionId;
	        this.serverCertificate = serverCertificate;
	        this.code = code;
	        this.random2 = random2;
	    }

	    public static long getSerialversionuid() {
	        return serialVersionUID;
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getSessionId() {
	        return sessionId;
	    }

	    public void setSessionId(String sessionId) {
	        this.sessionId = sessionId;
	    }

	    public String getServerCertificate() {
	        return serverCertificate;
	    }

	    public void setServerCertificate(String serverCertificate) {
	        this.serverCertificate = serverCertificate;
	    }

	    public String getSignServerCer() {
	        return signServerCer;
	    }

	    public void setSignServerCer(String signServerCer) {
	        this.signServerCer = signServerCer;
	    }

	    public String getRandom2() {
	        return random2;
	    }

	    public void setRandom2(String random2) {
	        this.random2 = random2;
	    }

	    public String getServerHMAC() {
	        return serverHMAC;
	    }

	    public void setServerHMAC(String serverHMAC) {
	        this.serverHMAC = serverHMAC;
	    }

	    public String getInstructions() {
	        return instructions;
	    }

	    public void setInstructions(String instructions) {
	        this.instructions = instructions;
	    }

	    public String getBillno() {
	        return billno;
	    }

	    public void setBillno(String billno) {
	        this.billno = billno;
	    }

	    public Integer getRechargeUpperLimit() {
	        return rechargeUpperLimit;
	    }

	    public void setRechargeUpperLimit(Integer rechargeUpperLimit) {
	        this.rechargeUpperLimit = rechargeUpperLimit;
	    }

	    public Integer getRechargeDownLimit() {
	        return rechargeDownLimit;
	    }

	    public void setRechargeDownLimit(Integer rechargeDownLimit) {
	        this.rechargeDownLimit = rechargeDownLimit;
	    }

	    public String getAmount() {
	        return amount;
	    }

	    public void setAmount(String amount) {
	        this.amount = amount;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }
}
