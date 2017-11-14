package com.yzy.supercleanmaster.model;

import java.io.Serializable;

public class Source implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Id;
	private String unitId;
	private String sysId;
	private String name;
	private String project;
	private String model;
	private String belong;
	private String dateOfManufact;
	private String param;
	private String idOfManufact;
	private String manufactor;
	private String dateOfUse;
	private String usefulLife;
	private String relation;
	private String runState;
	private String personLiable;
	private String local;
	private String maintenanceRecord;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getDateOfManufact() {
		return dateOfManufact;
	}
	public void setDateOfManufact(String dateOfManufact) {
		this.dateOfManufact = dateOfManufact;
	}
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getIdOfManufact() {
		return idOfManufact;
	}
	public void setIdOfManufact(String idOfManufact) {
		this.idOfManufact = idOfManufact;
	}
	public String getManufactor() {
		return manufactor;
	}
	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}
	public String getDateOfUse() {
		return dateOfUse;
	}
	public void setDateOfUse(String dateOfUse) {
		this.dateOfUse = dateOfUse;
	}
	public String getUsefulLife() {
		return usefulLife;
	}
	public void setUsefulLife(String usefulLife) {
		this.usefulLife = usefulLife;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getRunState() {
		return runState;
	}
	public void setRunState(String runState) {
		this.runState = runState;
	}
	public String getPersonLiable() {
		return personLiable;
	}
	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}
	public String getMaintenanceRecord() {
		return maintenanceRecord;
	}
	public void setMaintenanceRecord(String maintenanceRecord) {
		this.maintenanceRecord = maintenanceRecord;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	

}
