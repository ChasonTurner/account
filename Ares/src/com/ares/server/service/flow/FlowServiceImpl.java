/** 
 * Copyright (c) 2017, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2017年2月4日上午9:31:03 
 */
package com.ares.server.service.flow;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ares.server.exception.AccountException;
import com.ares.server.exception.FlowException;
import com.ares.server.model.Flow;
import com.huntersun.tool.Utils;

public class FlowServiceImpl implements FlowService {

	@Override
	public boolean addFlow(Map<String, Object> flowInfo) throws FlowException {
		Flow flow = Flow.findByOrderId((String)flowInfo.get("orderId"));
		if(flow == null){
			flow = new Flow();
			flow.setId(Utils.create36UUID());
			flow.setAccountId((String)flowInfo.get("accountId"));
			flow.setChannelType((Integer)flowInfo.get("channelType"));
			flow.setOrderId((String)flowInfo.get("orderId"));
			flow.setBalanceType((Integer)flowInfo.get("balanceType"));
			flow.setTotalFee((Integer)flowInfo.get("totalFee"));
			flow.setTransferId((String)flowInfo.get("transferId"));
			flow.setFreezeId((String)flowInfo.get("freezeId"));
			flow.setStatus((Integer)flowInfo.get("status"));
			flow.setCreateTime(new Date());
			
			flow.save();
		} else {
			throw new FlowException("尝试创建一个已存在的账单流水");
		}
		return true;
	}

	@Override
	public boolean deleteFlow(String orderId) throws FlowException {
		Flow flow = Flow.findByOrderId(orderId);
		if(flow != null){
			flow.delete();
		} else {
			throw new FlowException("尝试删除一个不存在的账单流水");
		}
		return false;
	}

	@Override
	public List<Flow> findByAccountId(String accountId, Integer balanceType) throws FlowException {
		return Flow.findByAccountIdAndBalanceType(accountId, balanceType);
	}

	@Override
	public boolean updateFlow(Map<String, Object> flowInfo) throws FlowException {
		Flow flow = Flow.findByOrderId((String)flowInfo.get("identiyId"));
		if(flow != null){
			flow.setAccountId((String)flowInfo.get("accountId"));
			flow.setChannelType((Integer)flowInfo.get("channelType"));
			flow.setOrderId((String)flowInfo.get("orderId"));
			flow.setBalanceType((Integer)flowInfo.get("balanceType"));
			flow.setTotalFee((Integer)flowInfo.get("totalFee"));
			flow.setTransferId((String)flowInfo.get("transferId"));
			flow.setFreezeId((String)flowInfo.get("freezeId"));
			flow.setStatus((Integer)flowInfo.get("status"));
			flow.setUpdateTime(new Date());
			
			flow.save();
		} else {
			throw new AccountException("尝试修改一个不存在用户信息");
		}
		
		return true;
	}

}
