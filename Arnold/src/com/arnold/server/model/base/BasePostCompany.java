package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePostCompany<M extends BasePostCompany<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setPostId(java.lang.String postId) {
		set("postId", postId);
	}

	public java.lang.String getPostId() {
		return get("postId");
	}

	public void setOrgId(java.lang.String orgId) {
		set("orgId", orgId);
	}

	public java.lang.String getOrgId() {
		return get("orgId");
	}

}
