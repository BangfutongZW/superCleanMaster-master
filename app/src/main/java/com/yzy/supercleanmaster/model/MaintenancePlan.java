package com.yzy.supercleanmaster.model;

import java.io.Serializable;

public class MaintenancePlan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int number;					//序号
	private String unitid;				//id
	private String data;
	private String category;			//类别
	private String name;				//名称
	private String running;				//运行时间
	private String executioncycle;		//执行周期
	private String executor;			//执行人
	private String mechanism;			//执行机构
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRunning() {
		return running;
	}
	public void setRunning(String running) {
		this.running = running;
	}
	public String getExecutioncycle() {
		return executioncycle;
	}
	public void setExecutioncycle(String executioncycle) {
		this.executioncycle = executioncycle;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	public String getMechanism() {
		return mechanism;
	}
	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
