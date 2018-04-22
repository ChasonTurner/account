package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMemberOrgHappen<M extends BaseMemberOrgHappen<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setMemberId(java.lang.String memberId) {
		set("memberId", memberId);
	}

	public java.lang.String getMemberId() {
		return get("memberId");
	}

	public void setOrgId(java.lang.String orgId) {
		set("orgId", orgId);
	}

	public java.lang.String getOrgId() {
		return get("orgId");
	}

	public void setPostId(java.lang.String postId) {
		set("postId", postId);
	}

	public java.lang.String getPostId() {
		return get("postId");
	}

	public void setRalationType(java.lang.Integer ralationType) {
		set("ralationType", ralationType);
	}

	public java.lang.Integer getRalationType() {
		return get("ralationType");
	}

	public void setCreateUserId(java.lang.String createUserId) {
		set("createUserId", createUserId);
	}

	public java.lang.String getCreateUserId() {
		return get("createUserId");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

}
