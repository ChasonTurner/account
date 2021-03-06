package com.ares.server.model;

import com.ares.server.model.base.BaseAccount;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Account extends BaseAccount<Account> {
	public static final Account dao = new Account();
	
	public static final String TABLE_NAME = "tb_account";
	
	public static Account findByAccount(String account){
		return dao.findFirst("select * from "+TABLE_NAME+" where identiyId=?",account);
	}
	
	public static Account findByIdentiyCode(String identiyCode){
		return dao.findFirst("select * from "+TABLE_NAME+" where identiyCode=?",identiyCode);
	}
	
	public static Account findByPhoneNo(String phoneNo){
		return dao.findFirst("select * from "+TABLE_NAME+" where phoneNo=?",phoneNo);
	}
}
