/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月4日上午9:29:55 
 */
package com.ares.server.service.account;

import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.model.Account;

public interface AccountService {
	/**
	 * 增加账户信息
	 * 
	 * @param accountInfo
	 * @return
	 */
	public boolean addAccount(Map<String, Object> accountInfo) throws AccountException;

	/**
	 * 删除账户信息
	 * 
	 * @param account
	 * @return
	 */
	public boolean deleteAccount(String accountId) throws AccountException;

	/**
	 * 根据账户id查找账户信息
	 * 
	 * @param accountId
	 * @return
	 */
	public Account findByAccount(String accountId) throws AccountException;

	/**
	 * 更新账户信息
	 * @param accountInfo
	 * @return
	 */
	public boolean updateAccount(Map<String, Object> accountInfo) throws AccountException;
	
	/**
	 * 更新账户类型
	 * @param accountId
	 * @return
	 * @throws AccountException
	 */
	public boolean updateAccountType(String accountId, Integer type) throws AccountException;
	
	/**
	 * 更新证件号码
	 * @param accountId
	 * @return
	 * @throws AccountException
	 */
	public boolean updateIdentiyCode(String accountId, String identiyCode) throws AccountException;
	
	/**
	 * 更新账户电话号码
	 * @param accountId
	 * @return
	 * @throws AccountException
	 */
	public boolean updatePhoneNo(String accountId, String phoneNo) throws AccountException;
	
	/**
	 * 更新账户支付密码
	 * @param accountId
	 * @return
	 * @throws AccountException
	 */
	public boolean updatePayCode(String accountId, String payCode) throws AccountException;
	
	/**
	 * 更新账户状态
	 * @param accountId
	 * @return
	 * @throws AccountException
	 */
	public boolean updateStatus(String accountId, Integer status) throws AccountException;
}
