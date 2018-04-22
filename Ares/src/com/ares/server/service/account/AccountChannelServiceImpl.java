/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月10日下午3:57:47 
 */
package com.ares.server.service.account;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.model.AccountChannel;
import com.huntersun.tool.Utils;

public class AccountChannelServiceImpl implements AccountChannelService {
	@Override
	public List<AccountChannel> findChannelsByAccount(String accountId) throws AccountException {
		return AccountChannel.findChannelByAccount(accountId);
	}

	@Override
	public AccountChannel findChannelByAccountAndType(String accountId, Integer channelType) throws AccountException {
		return AccountChannel.findChannelByAccountAndType(accountId, channelType);
	}

	@Override
	public boolean addAccountChannel(Map<String, Object> accountChannelInfo) throws AccountException {
		AccountChannel accountChannel = AccountChannel.findChannelByAccountAndType(
				(String) accountChannelInfo.get("accountId"), (Integer) accountChannelInfo.get("type"));
		if(accountChannel == null){
			accountChannel = new AccountChannel();
			accountChannel.setId(Utils.create36UUID());
			accountChannel.setAccountId((String)accountChannelInfo.get("accountId"));
			accountChannel.setType((Integer)accountChannelInfo.get("type"));
			accountChannel.setOutId((String)accountChannelInfo.get("outId"));
			accountChannel.setStatus((Integer)accountChannelInfo.get("status"));
			accountChannel.setCreateTime(new Date());
		} else {
			throw new AccountException("账户渠道信息已存在");
		}
		return true;
	}

	@Override
	public boolean updateAccountChannelStatus(String accountId, Integer channelType, Integer status)
			throws AccountException {
		AccountChannel accountChannel = AccountChannel.findChannelByAccountAndType(accountId, channelType);
		if(accountChannel != null){
			accountChannel.setStatus(status);
			accountChannel.update();
		} else {
			throw new AccountException("账户渠道信息不存在");
		}
		return true;
	}

	@Override
	public boolean updateAccountChannelOutId(String accountId, Integer channelType, String outId)
			throws AccountException {
		AccountChannel accountChannel = AccountChannel.findChannelByAccountAndType(accountId, channelType);
		if(accountChannel != null){
			accountChannel.setOutId(outId);
			accountChannel.update();
		} else {
			throw new AccountException("账户渠道信息不存在");
		}
		return true;
	}
}
