package com.yzy.supercleanmaster.model;
import java.io.Serializable;
/**
 * 
 * @author 蒋璐
 * @描述	巡检
 * @返回值
 * 2017-7-13
 */
public class Inspection implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer unitId;
	private String id;	//机器的id
	private String name;//机器名字
	private String waterlevel;//水位
	private String pressure;//压力
	private String battery;//蓄电池
	private String transformer;//变压器温度
	private String voltage;//电压
	private String electric;//电流
	private String electricAA;//11#高压计量
	private String powerFactor;//功率因素
	private String chilledWater;//空调主机冷冻出水温度
	private String chilledWaterA;//冷冻进水温度
	private String freezingWaterPressure;//冷冻出水压力
	private String freezinginletPressure;//冷冻进水压力
	private String coolingWaterTemperature;//冷却出水温度
	private String coolingInletTemperature;//冷却进水温度
	private String coolingWaterPressure;//冷却出水压力
	private String coolingInletPressure;//冷却进水压力
	private String lineVoltage;//线电压
	private String lineCurrent;//线电流
	private String motorTemperature;//电机温度
	private String guideVane;//导叶开启度
	private String oilTemperature;//油温
	private String leaveOil;//压缩机(离心机)油压差
	private String leaveExhaustPressure;//压缩机(离心机)排气温度
	private String inspiratory;//吸气压力
	private String inspiratory1;//吸气压力1
	private String inspiratory2;//吸气压力2
	private String evaporating;//蒸发温度
	private String evaporating1;//蒸发温度1
	private String evaporating2;//蒸发温度2
	private String exhaustPressure;//排气压力
	private String exhaustPressure1;//排气压力1
	private String exhaustPressure2;//排气压力2
	private String condensing;//冷凝温度
	private String condensing1;//冷凝温度1
	private String condensing2;//冷凝温度2
	private String exhaustTemperature;//排气温度
	private String exhaustTemperature1;//排气温度1
	private String exhaustTemperature2;//排气温度2
	private String fuelPressure;//供油压力
	private String fuelPressure1;//供油压力1
	private String fuelPressure2;//供油压力2
	private String pressureDifference;//油压差
	private String pressureDifference1;//油压差1
	private String pressureDifference2;//油压差2
	private String temperature;//温度
	private String humidity;//湿度
	private String abnormalNormal;//是否正常
	private String reason;//原因
	private String patrolMan;//巡查人
	private String patrolTime;//巡查的时间
	private String a;//简写名
	private String b;//此次
	private String c;//空
	private String d;//空
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWaterlevel() {
		return waterlevel;
	}
	public void setWaterlevel(String waterlevel) {
		this.waterlevel = waterlevel;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getTransformer() {
		return transformer;
	}
	public void setTransformer(String transformer) {
		this.transformer = transformer;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getElectric() {
		return electric;
	}
	public void setElectric(String electric) {
		this.electric = electric;
	}
	public String getElectricAA() {
		return electricAA;
	}
	public void setElectricAA(String electricAA) {
		this.electricAA = electricAA;
	}
	public String getPowerFactor() {
		return powerFactor;
	}
	public void setPowerFactor(String powerFactor) {
		this.powerFactor = powerFactor;
	}
	public String getChilledWater() {
		return chilledWater;
	}
	public void setChilledWater(String chilledWater) {
		this.chilledWater = chilledWater;
	}
	public String getChilledWaterA() {
		return chilledWaterA;
	}
	public void setChilledWaterA(String chilledWaterA) {
		this.chilledWaterA = chilledWaterA;
	}
	public String getFreezingWaterPressure() {
		return freezingWaterPressure;
	}
	public void setFreezingWaterPressure(String freezingWaterPressure) {
		this.freezingWaterPressure = freezingWaterPressure;
	}
	public String getFreezinginletPressure() {
		return freezinginletPressure;
	}
	public void setFreezinginletPressure(String freezinginletPressure) {
		this.freezinginletPressure = freezinginletPressure;
	}
	public String getCoolingWaterTemperature() {
		return coolingWaterTemperature;
	}
	public void setCoolingWaterTemperature(String coolingWaterTemperature) {
		this.coolingWaterTemperature = coolingWaterTemperature;
	}
	public String getCoolingInletTemperature() {
		return coolingInletTemperature;
	}
	public void setCoolingInletTemperature(String coolingInletTemperature) {
		this.coolingInletTemperature = coolingInletTemperature;
	}
	public String getCoolingWaterPressure() {
		return coolingWaterPressure;
	}
	public void setCoolingWaterPressure(String coolingWaterPressure) {
		this.coolingWaterPressure = coolingWaterPressure;
	}
	public String getCoolingInletPressure() {
		return coolingInletPressure;
	}
	public void setCoolingInletPressure(String coolingInletPressure) {
		this.coolingInletPressure = coolingInletPressure;
	}
	public String getLineVoltage() {
		return lineVoltage;
	}
	public void setLineVoltage(String lineVoltage) {
		this.lineVoltage = lineVoltage;
	}
	public String getLineCurrent() {
		return lineCurrent;
	}
	public void setLineCurrent(String lineCurrent) {
		this.lineCurrent = lineCurrent;
	}
	public String getMotorTemperature() {
		return motorTemperature;
	}
	public void setMotorTemperature(String motorTemperature) {
		this.motorTemperature = motorTemperature;
	}
	public String getGuideVane() {
		return guideVane;
	}
	public void setGuideVane(String guideVane) {
		this.guideVane = guideVane;
	}
	public String getOilTemperature() {
		return oilTemperature;
	}
	public void setOilTemperature(String oilTemperature) {
		this.oilTemperature = oilTemperature;
	}
	public String getLeaveOil() {
		return leaveOil;
	}
	public void setLeaveOil(String leaveOil) {
		this.leaveOil = leaveOil;
	}
	public String getLeaveExhaustPressure() {
		return leaveExhaustPressure;
	}
	public void setLeaveExhaustPressure(String leaveExhaustPressure) {
		this.leaveExhaustPressure = leaveExhaustPressure;
	}
	public String getInspiratory() {
		return inspiratory;
	}
	public void setInspiratory(String inspiratory) {
		this.inspiratory = inspiratory;
	}
	public String getInspiratory1() {
		return inspiratory1;
	}
	public void setInspiratory1(String inspiratory1) {
		this.inspiratory1 = inspiratory1;
	}
	public String getInspiratory2() {
		return inspiratory2;
	}
	public void setInspiratory2(String inspiratory2) {
		this.inspiratory2 = inspiratory2;
	}
	public String getEvaporating() {
		return evaporating;
	}
	public void setEvaporating(String evaporating) {
		this.evaporating = evaporating;
	}
	public String getEvaporating1() {
		return evaporating1;
	}
	public void setEvaporating1(String evaporating1) {
		this.evaporating1 = evaporating1;
	}
	public String getEvaporating2() {
		return evaporating2;
	}
	public void setEvaporating2(String evaporating2) {
		this.evaporating2 = evaporating2;
	}
	public String getExhaustPressure() {
		return exhaustPressure;
	}
	public void setExhaustPressure(String exhaustPressure) {
		this.exhaustPressure = exhaustPressure;
	}
	public String getExhaustPressure1() {
		return exhaustPressure1;
	}
	public void setExhaustPressure1(String exhaustPressure1) {
		this.exhaustPressure1 = exhaustPressure1;
	}
	public String getExhaustPressure2() {
		return exhaustPressure2;
	}
	public void setExhaustPressure2(String exhaustPressure2) {
		this.exhaustPressure2 = exhaustPressure2;
	}
	public String getCondensing() {
		return condensing;
	}
	public void setCondensing(String condensing) {
		this.condensing = condensing;
	}
	public String getCondensing1() {
		return condensing1;
	}
	public void setCondensing1(String condensing1) {
		this.condensing1 = condensing1;
	}
	public String getCondensing2() {
		return condensing2;
	}
	public void setCondensing2(String condensing2) {
		this.condensing2 = condensing2;
	}
	public String getExhaustTemperature() {
		return exhaustTemperature;
	}
	public void setExhaustTemperature(String exhaustTemperature) {
		this.exhaustTemperature = exhaustTemperature;
	}
	public String getExhaustTemperature1() {
		return exhaustTemperature1;
	}
	public void setExhaustTemperature1(String exhaustTemperature1) {
		this.exhaustTemperature1 = exhaustTemperature1;
	}
	public String getExhaustTemperature2() {
		return exhaustTemperature2;
	}
	public void setExhaustTemperature2(String exhaustTemperature2) {
		this.exhaustTemperature2 = exhaustTemperature2;
	}
	public String getFuelPressure() {
		return fuelPressure;
	}
	public void setFuelPressure(String fuelPressure) {
		this.fuelPressure = fuelPressure;
	}
	public String getFuelPressure1() {
		return fuelPressure1;
	}
	public void setFuelPressure1(String fuelPressure1) {
		this.fuelPressure1 = fuelPressure1;
	}
	public String getFuelPressure2() {
		return fuelPressure2;
	}
	public void setFuelPressure2(String fuelPressure2) {
		this.fuelPressure2 = fuelPressure2;
	}
	public String getPressureDifference() {
		return pressureDifference;
	}
	public void setPressureDifference(String pressureDifference) {
		this.pressureDifference = pressureDifference;
	}
	public String getPressureDifference1() {
		return pressureDifference1;
	}
	public void setPressureDifference1(String pressureDifference1) {
		this.pressureDifference1 = pressureDifference1;
	}
	public String getPressureDifference2() {
		return pressureDifference2;
	}
	public void setPressureDifference2(String pressureDifference2) {
		this.pressureDifference2 = pressureDifference2;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getAbnormalNormal() {
		return abnormalNormal;
	}
	public void setAbnormalNormal(String abnormalNormal) {
		this.abnormalNormal = abnormalNormal;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPatrolMan() {
		return patrolMan;
	}
	public void setPatrolMan(String patrolMan) {
		this.patrolMan = patrolMan;
	}
	public String getPatrolTime() {
		return patrolTime;
	}
	public void setPatrolTime(String patrolTime) {
		this.patrolTime = patrolTime;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
