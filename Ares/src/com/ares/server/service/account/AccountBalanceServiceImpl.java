/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月10日下午3:56:10 
 */
package com.ares.server.service.account;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.model.AccountBalance;
import com.huntersun.tool.Utils;

public class AccountBalanceServiceImpl implements AccountBalanceService {
	@Override
	public List<AccountBalance> findBalancesByAccount(String accountId) {
		return AccountBalance.findByAccount(accountId);
	}

	@Override
	public AccountBalance findBalanceByAccountAndType(String accountId, Integer type) {
		return AccountBalance.findByBalanceType(accountId, type);
	}

	@Override
	public boolean addAccountBalance(Map<String, Object> accountBalanceInfo) throws AccountException {
		AccountBalance accountBalance = AccountBalance.findByBalanceType((String) accountBalanceInfo.get("accountId"),
				(Integer) accountBalanceInfo.get("type"));
		if (accountBalance == null) {
			accountBalance = new AccountBalance();
			accountBalance.setId(Utils.create36UUID());
			accountBalance.setAccountId((String) accountBalanceInfo.get("accountId"));
			accountBalance.setType((Integer) accountBalanceInfo.get("type"));
			accountBalance.setAmount((Integer) accountBalanceInfo.get("amount"));
			accountBalance.setPayCode((String) accountBalanceInfo.get("payCode"));
			accountBalance.setPayLevel((Integer) accountBalanceInfo.get("payCode"));
			accountBalance.setStatus((Integer) accountBalanceInfo.get("payCode"));
			accountBalance.setCreateTime(new Date());

			accountBalance.save();
		} else {
			throw new AccountException("财富信息已存在");
		}

		return true;
	}

	@Override
	public boolean updateAccountBalanceStatus(String accountId, Integer type, Integer status) throws AccountException {
		AccountBalance accountBalance = AccountBalance.findByBalanceType(accountId, type);
		if (accountBalance != null) {
			accountBalance.setStatus(status);

			accountBalance.update();
		} else {
			throw new AccountException("财富信息不存在");
		}

		return true;
	}

	@Override
	public boolean addAmount(String accountId, Integer type, Integer totalFee) throws AccountException {
		AccountBalance accountBalance = AccountBalance.findByBalanceType(accountId, type);
		if (accountBalance != null) {
			accountBalance.setAmount(accountBalance.getAmount() + totalFee);
			;

			accountBalance.update();
		} else {
			throw new AccountException("财富信息不存在");
		}

		return true;
	}

	@Override
	public boolean decreaseAmount(String accountId, Integer type, Integer totalFee) throws AccountException {
		AccountBalance accountBalance = AccountBalance.findByBalanceType(accountId, type);
		if (accountBalance != null) {
			Integer amount = accountBalance.getAmount();
			if (amount < totalFee) {
				accountBalance.setAmount(accountBalance.getAmount() - totalFee);
				;
				accountBalance.update();
			} else {
				throw new AccountException("余额不足");
			}

		} else {
			throw new AccountException("财富信息不存在");
		}

		return true;
	}

	@Override
	public boolean updateBalancePayCode(String accountId, Integer type, String payCode) throws AccountException {
		AccountBalance accountBalance = AccountBalance.findByBalanceType(accountId, type);
		if (accountBalance != null) {
			accountBalance.setPayCode(payCode);

			accountBalance.update();
		} else {
			throw new AccountException("财富信息不存在");
		}

		return true;
	}
	
	@Override
	public boolean updateBalancePayLevel(String accountId, Integer type, Integer payLevel) throws AccountException {
		AccountBalance accountBalance = AccountBalance.findByBalanceType(accountId, type);
		if (accountBalance != null) {
			accountBalance.setPayLevel(payLevel);;

			accountBalance.update();
		} else {
			throw new AccountException("财富信息不存在");
		}

		return true;
	}
}
