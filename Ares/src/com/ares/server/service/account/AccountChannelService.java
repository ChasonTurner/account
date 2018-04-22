/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月10日下午3:57:21 
 */
package com.ares.server.service.account;

import java.util.List;
import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.model.AccountChannel;

public interface AccountChannelService {
	/**
	 * 根据账户ID查找该用户所有的渠道
	 * @param accountId
	 * @param channelType
	 * @return
	 * @throws AccountException
	 */
	public List<AccountChannel> findChannelsByAccount(String accountId) throws AccountException;

	/**
	 * 根据帐号ID与渠道类型查找用户渠道信息
	 * @param accountId
	 * @param type
	 * @return
	 * @throws AccountException
	 */
	public AccountChannel findChannelByAccountAndType(String accountId, Integer channelType) throws AccountException;

	/**
	 * 增加一种账户渠道
	 * @param accountChannelInfo
	 * @return
	 * @throws AccountException
	 */
	public boolean addAccountChannel(Map<String, Object> accountChannelInfo) throws AccountException;

	/**
	 * 更新账户渠道的状态
	 * @param accountId
	 * @param channelType
	 * @param status
	 * @return
	 * @throws AccountException
	 */
	public boolean updateAccountChannelStatus(String accountId, Integer channelType, Integer status)
			throws AccountException;

	/**
	 * 修改账户渠道的outId
	 * @param accountId
	 * @param channelType
	 * @param outId
	 * @return
	 * @throws AccountException
	 */
	public boolean updateAccountChannelOutId(String accountId, Integer channelType, String outId)
			throws AccountException;
}
