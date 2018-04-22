package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseEnterpriseVillageRelation<M extends BaseEnterpriseVillageRelation<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setEnterpriseId(java.lang.String enterpriseId) {
		set("enterpriseId", enterpriseId);
	}

	public java.lang.String getEnterpriseId() {
		return get("enterpriseId");
	}

	public void setVillageId(java.lang.String villageId) {
		set("villageId", villageId);
	}

	public java.lang.String getVillageId() {
		return get("villageId");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("updateTime", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("updateTime");
	}

	public void setIsValid(java.lang.Integer isValid) {
		set("isValid", isValid);
	}

	public java.lang.Integer getIsValid() {
		return get("isValid");
	}

}