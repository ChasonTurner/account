/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月10日下午3:58:17 
 */
package com.ares.server.service.account;

import java.util.List;
import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.model.AccountUpdate;

public interface AccountUpdateService {
	/**
	 * 增加一条账户信息更新记录
	 * @param accountUpdateInfo
	 * @return
	 * @throws AccountException
	 */
	public boolean addAccountUpdate(Map<String, Object> accountUpdateInfo) throws AccountException;

	/**
	 * 根据帐号ID与时间段查询账户信息更新记录
	 * @param accountId
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws AccountException
	 */
	public List<AccountUpdate> findUpdatesByAccount(String accountId, String beginDate, String endDate)
			throws AccountException;
}
