package com.yzy.supercleanmaster.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author 蒋璐
 * @描述	巡检
 * @返回值
 * 2017-7-13
 */
public class Inspection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int UnitId;//自增id
	private String Id;	//机器的id
	private String Name;//机器名字
	private String Waterlevel;//水位
	private String Pressure;//压力
	private String Battery;//蓄电池
	private String Transformer;//变压器温度
	private String Voltage;//电压
	private String Electric;//电流
	private String PowerFactor;//功率因素
	private String ChilledWater;//空调主机冷冻出水温度
	private String ChilledWaterA;//冷冻进水温度
	private String FreezingWaterPressure;//冷冻出水压力
	private String FreezinginletPressure;//冷冻进水压力
	private String CoolingWaterTemperature;//冷却出水温度
	private String CoolingInletTemperature;//冷却进水温度
	private String CoolingWaterPressure;//冷却出水压力
	private String CoolingInletPressure;//冷却进水压力
	private String Inspiratory;//吸气压力
	private String Evaporating;//蒸发温度
	private String ExhaustPressure;//排气压力
	private String Condensing;//冷凝温度
	private String ExhaustTemperature;//排气温度
	private String Superheat;//过热度
	private String FuelPressure;//供油压力
	private String PressureDifference;//油压差
	private String Temperature;//温度
	private String Humidity;//湿度
	private String AbnormalNormal;//是否正常
	private String Reason;//原因
	private String PatrolMan;//巡查人
	private String PatrolTime;//巡查的时间
	private String A;//空
	private String B;//空
	private String C;//空
	private String D;//空
	
	
	public int getUnitId() {
		return UnitId;
	}
	public void setUnitId(int unitId) {
		UnitId = unitId;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getWaterlevel() {
		return Waterlevel;
	}
	public void setWaterlevel(String waterlevel) {
		Waterlevel = waterlevel;
	}
	public String getPressure() {
		return Pressure;
	}
	public void setPressure(String pressure) {
		Pressure = pressure;
	}
	public String getBattery() {
		return Battery;
	}
	public void setBattery(String battery) {
		Battery = battery;
	}
	public String getTransformer() {
		return Transformer;
	}
	public void setTransformer(String transformer) {
		Transformer = transformer;
	}
	public String getVoltage() {
		return Voltage;
	}
	public void setVoltage(String voltage) {
		Voltage = voltage;
	}
	public String getElectric() {
		return Electric;
	}
	public void setElectric(String electric) {
		Electric = electric;
	}
	public String getPowerFactor() {
		return PowerFactor;
	}
	public void setPowerFactor(String powerFactor) {
		PowerFactor = powerFactor;
	}
	public String getChilledWater() {
		return ChilledWater;
	}
	public void setChilledWater(String chilledWater) {
		ChilledWater = chilledWater;
	}
	public String getChilledWaterA() {
		return ChilledWaterA;
	}
	public void setChilledWaterA(String chilledWaterA) {
		ChilledWaterA = chilledWaterA;
	}
	public String getFreezingWaterPressure() {
		return FreezingWaterPressure;
	}
	public void setFreezingWaterPressure(String freezingWaterPressure) {
		FreezingWaterPressure = freezingWaterPressure;
	}
	public String getFreezinginletPressure() {
		return FreezinginletPressure;
	}
	public void setFreezinginletPressure(String freezinginletPressure) {
		FreezinginletPressure = freezinginletPressure;
	}
	public String getCoolingWaterTemperature() {
		return CoolingWaterTemperature;
	}
	public void setCoolingWaterTemperature(String coolingWaterTemperature) {
		CoolingWaterTemperature = coolingWaterTemperature;
	}
	public String getCoolingInletTemperature() {
		return CoolingInletTemperature;
	}
	public void setCoolingInletTemperature(String coolingInletTemperature) {
		CoolingInletTemperature = coolingInletTemperature;
	}
	public String getCoolingWaterPressure() {
		return CoolingWaterPressure;
	}
	public void setCoolingWaterPressure(String coolingWaterPressure) {
		CoolingWaterPressure = coolingWaterPressure;
	}
	public String getCoolingInletPressure() {
		return CoolingInletPressure;
	}
	public void setCoolingInletPressure(String coolingInletPressure) {
		CoolingInletPressure = coolingInletPressure;
	}
	public String getInspiratory() {
		return Inspiratory;
	}
	public void setInspiratory(String inspiratory) {
		Inspiratory = inspiratory;
	}
	public String getEvaporating() {
		return Evaporating;
	}
	public void setEvaporating(String evaporating) {
		Evaporating = evaporating;
	}
	public String getExhaustPressure() {
		return ExhaustPressure;
	}
	public void setExhaustPressure(String exhaustPressure) {
		ExhaustPressure = exhaustPressure;
	}
	public String getCondensing() {
		return Condensing;
	}
	public void setCondensing(String condensing) {
		Condensing = condensing;
	}
	public String getExhaustTemperature() {
		return ExhaustTemperature;
	}
	public void setExhaustTemperature(String exhaustTemperature) {
		ExhaustTemperature = exhaustTemperature;
	}
	public String getSuperheat() {
		return Superheat;
	}
	public void setSuperheat(String superheat) {
		Superheat = superheat;
	}
	public String getFuelPressure() {
		return FuelPressure;
	}
	public void setFuelPressure(String fuelPressure) {
		FuelPressure = fuelPressure;
	}
	public String getPressureDifference() {
		return PressureDifference;
	}
	public void setPressureDifference(String pressureDifference) {
		PressureDifference = pressureDifference;
	}
	public String getTemperature() {
		return Temperature;
	}
	public void setTemperature(String temperature) {
		Temperature = temperature;
	}
	public String getHumidity() {
		return Humidity;
	}
	public void setHumidity(String humidity) {
		Humidity = humidity;
	}
	public String getAbnormalNormal() {
		return AbnormalNormal;
	}
	public void setAbnormalNormal(String abnormalNormal) {
		AbnormalNormal = abnormalNormal;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String  getPatrolTime() {
		return PatrolTime;
	}
	public void setPatrolTime(String patrolTime) {
		PatrolTime = patrolTime;
	}
	public String getPatrolMan() {
		return PatrolMan;
	}
	public void setPatrolMan(String patrolMan) {
		PatrolMan = patrolMan;
	}
	
}
