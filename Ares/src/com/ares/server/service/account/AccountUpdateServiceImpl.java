/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月10日下午3:58:44 
 */
package com.ares.server.service.account;

import java.util.List;
import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.model.AccountUpdate;

public class AccountUpdateServiceImpl implements AccountUpdateService {
	@Override
	public boolean addAccountUpdate(Map<String, Object> accountUpdateInfo) throws AccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AccountUpdate> findUpdatesByAccount(String accountId, String beginDate, String endDate)
			throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

}
