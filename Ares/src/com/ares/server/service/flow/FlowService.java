/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月4日上午9:30:38 
 */
package com.ares.server.service.flow;

import java.util.List;
import java.util.Map;

import com.ares.server.exception.FlowException;
import com.ares.server.model.Flow;


public interface FlowService {
	/**
	 * 增加流水
	 * @param flowInfo
	 * @return
	 */
	public boolean addFlow(Map<String,Object> flowInfo) throws FlowException;
	
	/**
	 * 删除流水信息
	 * @param orderId
	 * @return
	 */
	public boolean deleteFlow(String orderId) throws FlowException;
	
	/**
	 * 根据账户id查找流水信息
	 * @param accountId
	 * @return
	 */
	public List<Flow> findByAccountId(String accountId, Integer balanceType) throws FlowException;
	
	/**
	 * 更新流水信息
	 * @param flowInfo
	 * @return
	 */
	public boolean updateFlow(Map<String,Object> flowInfo) throws FlowException;
}
