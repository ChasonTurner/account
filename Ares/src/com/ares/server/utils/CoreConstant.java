package com.ares.server.utils;

public final class CoreConstant
{
	//访问Poseidon连接异常
	public static final int POSEIDON_CONNECTION_OUT = 1030001;
	/*-------微信错误码--------*/
	//发起预付订单失败
	public static final int UNIFIEDORDER_ERROR = 1030002;
	//退款失败
	public static final int REFUND_EEROR = 1030003;
	//企业付款失败
	public static final int TRANSFER_EEROR = 1030004;
	//查询详细订单失败
	public static final int QUERY_ORDER_DETAIL_EEROR = 1030005;
	//查询退款订单失败
	public static final int QUERY_REFUND_DETAIL_EEROR = 1030006;
	//保存用户关注失败
	public static final int SAVE_USER_ATTENTION_ERROR = 1030007;
	//用户绑定微信支付账户失败
	public static final int BIND_USER_ATTENTION_ERROR = 1030008;
	//用户没有绑定微信提现账户
	public static final int NOT_BIND_USER_ATTENTION = 1030009;
	//该项目没有开通企业付款功能
	public static final int NOT_AUTH_COMPANY_PAY = 1030010;
	//该项目没有开通APP付款款功能
	public static final int NOT_AUTH_APP_PAY = 1030011;
	//用户尚未关注该项目公众号
	public static final int USER_NOT_ATTENTION = 1030012;
	//用户余额修改失败
	public static final int USER_BALANCE_UPDATE_ERROR = 1030013;
	//用户余额不足
	public static final int USER_BALANCE_NOT_ENOUGH = 1030014;
	//余额修改类型错误
	public static final int USER_BALANCE_NO_ACTION = 1030015;
}
