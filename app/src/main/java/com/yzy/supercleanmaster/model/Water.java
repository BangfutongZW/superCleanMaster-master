package com.yzy.supercleanmaster.model;

import java.io.Serializable;
import java.util.Date;

public class Water implements Serializable{
	private static final long serialVersionUID = 1L;
	private int ID;
	private String lifePump1;
	private String lifePump2;
	private String lifePump3;
	private String lifePump4;
	private float lifePa;
	private float lifewaterLevel;
	private float firewaterLevel;
	private String date;
	private String time;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLifePump1() {
		return lifePump1;
	}
	public void setLifePump1(String lifePump1) {
		this.lifePump1 = lifePump1;
	}
	public String getLifePump2() {
		return lifePump2;
	}
	public void setLifePump2(String lifePump2) {
		this.lifePump2 = lifePump2;
	}
	public String getLifePump3() {
		return lifePump3;
	}
	public void setLifePump3(String lifePump3) {
		this.lifePump3 = lifePump3;
	}
	public String getLifePump4() {
		return lifePump4;
	}
	public void setLifePump4(String lifePump4) {
		this.lifePump4 = lifePump4;
	}
	public float getLifePa() {
		return lifePa;
	}
	public void setLifePa(float lifePa) {
		this.lifePa = lifePa;
	}
	public float getLifewaterLevel() {
		return lifewaterLevel;
	}
	public void setLifewaterLevel(float lifewaterLevel) {
		this.lifewaterLevel = lifewaterLevel;
	}
	public float getFirewaterLevel() {
		return firewaterLevel;
	}
	public void setFirewaterLevel(float firewaterLevel) {
		this.firewaterLevel = firewaterLevel;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Water{" +
				"ID=" + ID +
				", lifePump1='" + lifePump1 + '\'' +
				", lifePump2='" + lifePump2 + '\'' +
				", lifePump3='" + lifePump3 + '\'' +
				", lifePump4='" + lifePump4 + '\'' +
				", lifePa=" + lifePa +
				", lifewaterLevel=" + lifewaterLevel +
				", firewaterLevel=" + firewaterLevel +
				", date='" + date + '\'' +
				", time='" + time + '\'' +
				'}';
	}
}
