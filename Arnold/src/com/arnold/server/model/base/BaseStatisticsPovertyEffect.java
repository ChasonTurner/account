package com.arnold.server.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseStatisticsPovertyEffect<M extends BaseStatisticsPovertyEffect<M>> extends Model<M> implements IBean {

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

	public void setTotalFamily(java.lang.Integer totalFamily) {
		set("totalFamily", totalFamily);
	}

	public java.lang.Integer getTotalFamily() {
		return get("totalFamily");
	}

	public void setPovertyFamily(java.lang.Integer povertyFamily) {
		set("povertyFamily", povertyFamily);
	}

	public java.lang.Integer getPovertyFamily() {
		return get("povertyFamily");
	}

	public void setTotalPopulation(java.lang.Integer totalPopulation) {
		set("totalPopulation", totalPopulation);
	}

	public java.lang.Integer getTotalPopulation() {
		return get("totalPopulation");
	}

	public void setPovertyPopulation(java.lang.Integer povertyPopulation) {
		set("povertyPopulation", povertyPopulation);
	}

	public java.lang.Integer getPovertyPopulation() {
		return get("povertyPopulation");
	}

	public void setLabour(java.lang.Integer labour) {
		set("labour", labour);
	}

	public java.lang.Integer getLabour() {
		return get("labour");
	}

	public void setLabourOther(java.lang.Integer labourOther) {
		set("labourOther", labourOther);
	}

	public java.lang.Integer getLabourOther() {
		return get("labourOther");
	}

	public void setTrainLabour(java.lang.Integer trainLabour) {
		set("trainLabour", trainLabour);
	}

	public java.lang.Integer getTrainLabour() {
		return get("trainLabour");
	}

	public void setObtainLabour(java.lang.Integer obtainLabour) {
		set("obtainLabour", obtainLabour);
	}

	public java.lang.Integer getObtainLabour() {
		return get("obtainLabour");
	}

	public void setAutonomyObtain(java.lang.Integer autonomyObtain) {
		set("autonomyObtain", autonomyObtain);
	}

	public java.lang.Integer getAutonomyObtain() {
		return get("autonomyObtain");
	}

	public void setLaborServiceConstruction(java.lang.Integer laborServiceConstruction) {
		set("laborServiceConstruction", laborServiceConstruction);
	}

	public java.lang.Integer getLaborServiceConstruction() {
		return get("laborServiceConstruction");
	}

	public void setInHomeIndustry(java.lang.Integer inHomeIndustry) {
		set("inHomeIndustry", inHomeIndustry);
	}

	public java.lang.Integer getInHomeIndustry() {
		return get("inHomeIndustry");
	}

	public void setEfficiency(java.lang.Integer efficiency) {
		set("efficiency", efficiency);
	}

	public java.lang.Integer getEfficiency() {
		return get("efficiency");
	}

	public void setFiveGuarantees(java.lang.Integer fiveGuarantees) {
		set("fiveGuarantees", fiveGuarantees);
	}

	public java.lang.Integer getFiveGuarantees() {
		return get("fiveGuarantees");
	}

	public void setTownInsurance(java.lang.Integer townInsurance) {
		set("townInsurance", townInsurance);
	}

	public java.lang.Integer getTownInsurance() {
		return get("townInsurance");
	}

	public void setMedicalAssistance(java.lang.Integer medicalAssistance) {
		set("medicalAssistance", medicalAssistance);
	}

	public java.lang.Integer getMedicalAssistance() {
		return get("medicalAssistance");
	}

	public void setPigHerds(java.lang.Integer pigHerds) {
		set("pigHerds", pigHerds);
	}

	public java.lang.Integer getPigHerds() {
		return get("pigHerds");
	}

	public void setOutPigHerds(java.lang.Integer outPigHerds) {
		set("outPigHerds", outPigHerds);
	}

	public java.lang.Integer getOutPigHerds() {
		return get("outPigHerds");
	}

	public void setOrderPigHerds(java.lang.Integer orderPigHerds) {
		set("orderPigHerds", orderPigHerds);
	}

	public java.lang.Integer getOrderPigHerds() {
		return get("orderPigHerds");
	}

}
