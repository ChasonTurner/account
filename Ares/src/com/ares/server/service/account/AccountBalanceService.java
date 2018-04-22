/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月10日下午3:55:32 
 */
package com.ares.server.service.account;

import java.util.List;
import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.model.AccountBalance;

public interface AccountBalanceService {
	/**
	 * 根据帐号ID查找所有财富信息
	 * @param accountId
	 * @return
	 * @throws
	 */
	public List<AccountBalance> findBalancesByAccount(String accountId);

	/**
	 * 根据账户ID与财富类型查找财富信息
	 * @param accountId
	 * @param type
	 * @return
	 * @throws AccountException
	 */
	public AccountBalance findBalanceByAccountAndType(String accountId, Integer type);

	/**
	 * 给账户增加一种财富信息
	 * @param accountBalanceInfo
	 * @return
	 * @throws AccountException
	 */
	public boolean addAccountBalance(Map<String, Object> accountBalanceInfo) throws AccountException;

	/**
	 * 根据帐号ID、财富类型修改该财富信息的状态
	 * @param accountId
	 * @param type
	 * @param status
	 * @return
	 * @throws AccountException
	 */
	public boolean updateAccountBalanceStatus(String accountId, Integer type, Integer status) throws AccountException;

	/**
	 * 根据帐号ID、财富类型增加totalFee的金额
	 * @param accountId
	 * @param type
	 * @param totalFee
	 * @return
	 * @throws AccountException
	 */
	public boolean addAmount(String accountId, Integer type, Integer totalFee) throws AccountException;

	/**
	 * 根据帐号ID、财富类型减少totalFee的金额
	 * @param accountId
	 * @param type
	 * @param totalFee
	 * @return
	 * @throws AccountException
	 */
	public boolean decreaseAmount(String accountId, Integer type, Integer totalFee) throws AccountException;

	/**
	 * 根据帐号ID，财富类型修改支付密码
	 * @param accountId
	 * @param type
	 * @param payCode
	 * @return
	 * @throws AccountException
	 */
	public boolean updateBalancePayCode(String accountId,Integer type,String payCode) throws AccountException;
	
	/**
	 * 根据帐号ID，财富类型修改支付等级
	 * @param accountId
	 * @param type
	 * @param payLevel
	 * @return
	 * @throws AccountException
	 */
	public boolean updateBalancePayLevel(String accountId,Integer type,Integer payLevel) throws AccountException;
}
