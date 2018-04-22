/** 
 * Copyright (c) 2016, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2016年12月5日下午2:54:40 
 */
package com.ares.server.utils;

public final class RechargeConstant {
    public static final String ALIPAY = "alipay";
    public static final String WXPAY = "wxpay";
    public static final String ICBCPAY = "icbcpay";
    public static final String PINGANPAY = "pinganpay";

    public static final String WXPAY_CHANNEL_APP = "app";
    public static final String WXPAY_CHANNEL_JS = "js";

    public static final int PAYORG_ICBC = 1;
    public static final int PAYORG_WXPAY = 2;
    public static final int PAYORG_ALIPAY = 3;
    public static final int PAYORG_PINGAN = 4;


    // 充值单状态
    public static final byte TRADE_CLOSED = -2;  // 交易关闭(充值关闭)
    public static final byte TRADE_WAITING = -1;  // 交易创建,等待买家付款（未付款）
    public static final byte TRADE_SUCCESS = 0;  // 交易成功 （已付款）
    public static final byte TRADE_NOTIFIED = 1;  // 完成转账通知 （已到帐）

    // 消费单状态
    public static final byte RECHARGE_POSITIVE = -2;  // 已充正（消费关闭）
    public static final byte RECHARGE_WAITING = -1; // 已经请求了初始化指令（待写卡）
    public static final byte RECHARGE_APPLIED = 0; // 已经进行了圈存申请，但是是否写卡成卡未知 （写卡中断）
    public static final byte RECHARGE_CONFIRMED = 1; // 圈存确认成功 (已写卡)

}
