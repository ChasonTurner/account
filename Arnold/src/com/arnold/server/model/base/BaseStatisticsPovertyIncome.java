package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseStatisticsPovertyIncome<M extends BaseStatisticsPovertyIncome<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setYear(java.lang.Integer year) {
		set("year", year);
	}

	public java.lang.Integer getYear() {
		return get("year");
	}

	public void setTotalIncome(java.lang.Double totalIncome) {
		set("totalIncome", totalIncome);
	}

	public java.lang.Double getTotalIncome() {
		return get("totalIncome");
	}

}
