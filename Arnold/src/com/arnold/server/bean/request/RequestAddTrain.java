package com.arnold.server.bean.request;

import java.io.Serializable;
import java.util.Date;

/**  
 * @description  增加培训
 * @author luzy
 * @date 2017年12月22日
 */
public class RequestAddTrain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2554810242636016379L;

	/** 培训名称 */
	private String name;
	
	/** 所属分类 */
	private String typeId;
	
	/** 岗位id */
	private String postId;
	
	/** 培训内容 */
	private String content;
	
	/** 培训人均身价 */
	private Double price;
	
	/** 培训总费用 */
	private Double finalMoney;
	
	/** 实际开始时间 */
	private Date realStartTime;
	
	/** 实际结束时间 */
	private Date realEndTime;
	
	/** 发起单位id */
	private String orgId;
	
	/** 参加人数 */
	private Integer count;
	
	/** 主讲人id */
	private String userId;
	
	/** 录入人 */
	private String writer;
	
	/** 最近修改人 */
	private String nowWriter;
	
	/** 专业技能 */
	private String skillTypeId;
	
	/** 培训开始时间 */
	private Date startTime;
	
	/** 培训结束时间 */
	private Date endTime;
	
	/** 培训地址 */
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getFinalMoney() {
		return finalMoney;
	}

	public void setFinalMoney(Double finalMoney) {
		this.finalMoney = finalMoney;
	}

	public Date getRealStartTime() {
		return realStartTime;
	}

	public void setRealStartTime(Date realStartTime) {
		this.realStartTime = realStartTime;
	}

	public Date getRealEndTime() {
		return realEndTime;
	}

	public void setRealEndTime(Date realEndTime) {
		this.realEndTime = realEndTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getNowWriter() {
		return nowWriter;
	}

	public void setNowWriter(String nowWriter) {
		this.nowWriter = nowWriter;
	}

	public String getSkillTypeId() {
		return skillTypeId;
	}

	public void setSkillTypeId(String skillTypeId) {
		this.skillTypeId = skillTypeId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
