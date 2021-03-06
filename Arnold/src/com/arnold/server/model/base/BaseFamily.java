package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFamily<M extends BaseFamily<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setNumber(java.lang.String number) {
		set("number", number);
	}

	public java.lang.String getNumber() {
		return get("number");
	}

	public void setTownId(java.lang.String townId) {
		set("townId", townId);
	}

	public java.lang.String getTownId() {
		return get("townId");
	}

	public void setHamletId(java.lang.String hamletId) {
		set("hamletId", hamletId);
	}

	public java.lang.String getHamletId() {
		return get("hamletId");
	}

	public void setHamletName(java.lang.String hamletName) {
		set("hamletName", hamletName);
	}

	public java.lang.String getHamletName() {
		return get("hamletName");
	}

	public void setGroupName(java.lang.String groupName) {
		set("groupName", groupName);
	}

	public java.lang.String getGroupName() {
		return get("groupName");
	}

	public void setRegionId(java.lang.String regionId) {
		set("regionId", regionId);
	}

	public java.lang.String getRegionId() {
		return get("regionId");
	}

	public void setVillagerId(java.lang.String villagerId) {
		set("villagerId", villagerId);
	}

	public java.lang.String getVillagerId() {
		return get("villagerId");
	}

	public void setTime(java.util.Date time) {
		set("time", time);
	}

	public java.util.Date getTime() {
		return get("time");
	}

	public void setOperator(java.lang.String operator) {
		set("operator", operator);
	}

	public java.lang.String getOperator() {
		return get("operator");
	}

	public void setOperatorTime(java.util.Date operatorTime) {
		set("operatorTime", operatorTime);
	}

	public java.util.Date getOperatorTime() {
		return get("operatorTime");
	}

	public void setWriter(java.lang.String writer) {
		set("writer", writer);
	}

	public java.lang.String getWriter() {
		return get("writer");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

	public void setPlaneTime(java.util.Date planeTime) {
		set("planeTime", planeTime);
	}

	public java.util.Date getPlaneTime() {
		return get("planeTime");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setEndTime(java.util.Date endTime) {
		set("endTime", endTime);
	}

	public java.util.Date getEndTime() {
		return get("endTime");
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

	public void setAspirationId(java.lang.String aspirationId) {
		set("aspirationId", aspirationId);
	}

	public java.lang.String getAspirationId() {
		return get("aspirationId");
	}

	public void setIsDropout(java.lang.Integer isDropout) {
		set("isDropout", isDropout);
	}

	public java.lang.Integer getIsDropout() {
		return get("isDropout");
	}

	public void setIsOnlyChild(java.lang.Integer isOnlyChild) {
		set("isOnlyChild", isOnlyChild);
	}

	public java.lang.Integer getIsOnlyChild() {
		return get("isOnlyChild");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setAddReason(java.lang.String addReason) {
		set("addReason", addReason);
	}

	public java.lang.String getAddReason() {
		return get("addReason");
	}

	public void setRalationTypeId(java.lang.String ralationTypeId) {
		set("ralationTypeId", ralationTypeId);
	}

	public java.lang.String getRalationTypeId() {
		return get("ralationTypeId");
	}

	public void setFamilyPropertyId(java.lang.String familyPropertyId) {
		set("familyPropertyId", familyPropertyId);
	}

	public java.lang.String getFamilyPropertyId() {
		return get("familyPropertyId");
	}

	public void setLastModifyTime(java.util.Date lastModifyTime) {
		set("lastModifyTime", lastModifyTime);
	}

	public java.util.Date getLastModifyTime() {
		return get("lastModifyTime");
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
