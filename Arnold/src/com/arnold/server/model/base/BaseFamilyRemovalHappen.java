package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFamilyRemovalHappen<M extends BaseFamilyRemovalHappen<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setFamilyId(java.lang.String familyId) {
		set("familyId", familyId);
	}

	public java.lang.String getFamilyId() {
		return get("familyId");
	}

	public void setIsMoveFamily(java.lang.Integer isMoveFamily) {
		set("isMoveFamily", isMoveFamily);
	}

	public java.lang.Integer getIsMoveFamily() {
		return get("isMoveFamily");
	}

	public void setRemovalTypeId(java.lang.String removalTypeId) {
		set("removalTypeId", removalTypeId);
	}

	public java.lang.String getRemovalTypeId() {
		return get("removalTypeId");
	}

	public void setOutRegion(java.lang.String outRegion) {
		set("outRegion", outRegion);
	}

	public java.lang.String getOutRegion() {
		return get("outRegion");
	}

	public void setToRegion(java.lang.String toRegion) {
		set("toRegion", toRegion);
	}

	public java.lang.String getToRegion() {
		return get("toRegion");
	}

	public void setResettlementWayId(java.lang.String resettlementWayId) {
		set("resettlementWayId", resettlementWayId);
	}

	public java.lang.String getResettlementWayId() {
		return get("resettlementWayId");
	}

	public void setDifficult(java.lang.String difficult) {
		set("difficult", difficult);
	}

	public java.lang.String getDifficult() {
		return get("difficult");
	}

	public void setStayInTime(java.util.Date stayInTime) {
		set("stayInTime", stayInTime);
	}

	public java.util.Date getStayInTime() {
		return get("stayInTime");
	}

	public void setIsStayIn(java.lang.Integer isStayIn) {
		set("isStayIn", isStayIn);
	}

	public java.lang.Integer getIsStayIn() {
		return get("isStayIn");
	}

	public void setIsMoval(java.lang.Integer isMoval) {
		set("isMoval", isMoval);
	}

	public java.lang.Integer getIsMoval() {
		return get("isMoval");
	}

}
