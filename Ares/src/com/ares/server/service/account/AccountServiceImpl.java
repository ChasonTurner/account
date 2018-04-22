/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月4日上午9:30:22 
 */
package com.ares.server.service.account;

import java.util.Date;
import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.model.Account;
import com.huntersun.tool.Utils;

public class AccountServiceImpl implements AccountService {

	@Override
	public boolean addAccount(Map<String, Object> accountInfo) throws AccountException {
		Account account = Account.findByAccount((String) accountInfo.get("identiyId"));
		if (account == null) {
			account = new Account();
			account.setId(Utils.create36UUID());
			account.setType((Integer) accountInfo.get("type"));
			account.setIdentiyId((String) accountInfo.get("identiyId"));
			account.setIdentiyCode((String) accountInfo.get("identiyCode"));
			account.setPhoneNo((String) accountInfo.get("phoneNo"));
			account.setPayCode((String) accountInfo.get("payCode"));
			account.setStatus((Integer) accountInfo.get("status"));
			account.setCreateTime(new Date());

			account.save();
		} else {
			throw new AccountException("账户已存在");
		}
		return true;
	}

	@Override
	public boolean deleteAccount(String accountId) throws AccountException {
		Account account = Account.findByAccount(accountId);
		if (account != null) {
			account.delete();
		} else {
			throw new AccountException("账户不存在");
		}
		return true;
	}

	@Override
	public Account findByAccount(String accountId) throws AccountException {
		return Account.findByAccount(accountId);
	}

	@Override
	public boolean updateAccount(Map<String, Object> accountInfo) throws AccountException {
		Account account = Account.findByAccount((String) accountInfo.get("identiyId"));
		if (account != null) {
			account.setType((Integer) accountInfo.get("type"));
			account.setIdentiyId((String) accountInfo.get("identiyId"));
			account.setIdentiyCode((String) accountInfo.get("identiyCode"));
			account.setPhoneNo((String) accountInfo.get("phoneNo"));

			account.update();
		} else {
			throw new AccountException("账户不存在");
		}

		return true;
	}

	@Override
	public boolean updateAccountType(String accountId, Integer type) throws AccountException {
		Account account = Account.findByAccount(accountId);
		if (account != null) {
			account.setType(type);

			account.update();
		} else {
			throw new AccountException("账户不存在");
		}

		return true;
	}

	@Override
	public boolean updateIdentiyCode(String accountId, String identiyCode) throws AccountException {
		Account account = Account.findByAccount(accountId);
		if (account != null) {
			account.setIdentiyCode(identiyCode);

			account.update();
		} else {
			throw new AccountException("账户不存在");
		}

		return true;
	}

	@Override
	public boolean updatePhoneNo(String accountId, String phoneNo) throws AccountException {
		Account account = Account.findByAccount(accountId);
		if (account != null) {
			account.setPhoneNo(phoneNo);

			account.update();
		} else {
			throw new AccountException("账户不存在");
		}

		return true;
	}

	@Override
	public boolean updatePayCode(String accountId, String payCode) throws AccountException {
		Account account = Account.findByAccount(accountId);
		if (account != null) {
			account.setPayCode(payCode);

			account.update();
		} else {
			throw new AccountException("账户不存在");
		}

		return true;
	}

	@Override
	public boolean updateStatus(String accountId, Integer status) throws AccountException {
		Account account = Account.findByAccount(accountId);
		if (account != null) {
			account.setStatus(status);

			account.update();
		} else {
			throw new AccountException("账户不存在");
		}

		return true;
	}

}
