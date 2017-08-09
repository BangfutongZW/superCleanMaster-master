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
	private Integer UnitId;
	private String Id;	//机器的id
	private String Name;//机器名字
	private String Waterlevel;//水位
	private String Pressure;//压力
	private String Battery;//蓄电池
	private String Transformer;//变压器温度
	private String Voltage;//电压
	private String Electric;//电流
	private String ElectricAA;//高压计量
	private String PowerFactor;//功率因素
	private String ChilledWater;//空调主机冷冻出水温度
	private String ChilledWaterA;//冷冻进水温度
	private String FreezingWaterPressure;//冷冻出水压力
	private String FreezinginletPressure;//冷冻进水压力
	private String CoolingWaterTemperature;//冷却出水温度
	private String CoolingInletTemperature;//冷却进水温度
	private String CoolingWaterPressure;//冷却出水压力
	private String CoolingInletPressure;//冷却进水压力
	private String LineVoltage;//线电压
	private String LineCurrent;//线电流
	private String MotorTemperature;//电机温度
	private String GuideVane;//导叶开启度
	private String OilTemperature;//油温
	private String LeaveOil;//压缩机(离心机)油压差
	private String LeaveExhaustPressure;//压缩机(离心机)排气温度
	private String Inspiratory;//吸气压力
	private String Inspiratory1;//吸气压力1
	private String Inspiratory2;//吸气压力2
	private String Evaporating;//蒸发温度
	private String Evaporating1;//蒸发温度1
	private String Evaporating2;//蒸发温度2
	private String ExhaustPressure;//排气压力
	private String ExhaustPressure1;//排气压力1
	private String ExhaustPressure2;//排气压力2
	private String Condensing;//冷凝温度
	private String Condensing1;//冷凝温度1
	private String Condensing2;//冷凝温度2
	private String ExhaustTemperature;//排气温度
	private String ExhaustTemperature1;//排气温度1
	private String ExhaustTemperature2;//排气温度2
	private String FuelPressure;//供油压力
	private String FuelPressure1;//供油压力1
	private String FuelPressure2;//供油压力2
	private String PressureDifference;//油压差
	private String PressureDifference1;//油压差1
	private String PressureDifference2;//油压差2
	private String Temperature;//温度
	private String Humidity;//湿度
	private String AbnormalNormal;//是否正常
	private String Reason;//原因
	private String PatrolMan;//巡查人
	private String PatrolTime;//巡查的时间
	private String A;//简写名
	private String B;//此次
	private String C;//空
	private String D;//空
	
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

	public String getPatrolMan() {
		return PatrolMan;
	}
	public void setPatrolMan(String patrolMan) {
		PatrolMan = patrolMan;
	}
	public String getPatrolTime() {
		return PatrolTime;
	}
	public void setPatrolTime(String patrolTime) {
		PatrolTime = patrolTime;
	}
	public Integer getUnitId() {
		return UnitId;
	}
	public void setUnitId(Integer unitId) {
		UnitId = unitId;
	}
	public String getInspiratory2() {
		return Inspiratory2;
	}
	public void setInspiratory2(String inspiratory2) {
		Inspiratory2 = inspiratory2;
	}
	public String getInspiratory1() {
		return Inspiratory1;
	}
	public void setInspiratory1(String inspiratory1) {
		Inspiratory1 = inspiratory1;
	}
	public String getEvaporating2() {
		return Evaporating2;
	}
	public void setEvaporating2(String evaporating2) {
		Evaporating2 = evaporating2;
	}
	public String getEvaporating1() {
		return Evaporating1;
	}
	public void setEvaporating1(String evaporating1) {
		Evaporating1 = evaporating1;
	}
	public String getExhaustPressure1() {
		return ExhaustPressure1;
	}
	public void setExhaustPressure1(String exhaustPressure1) {
		ExhaustPressure1 = exhaustPressure1;
	}
	public String getExhaustPressure2() {
		return ExhaustPressure2;
	}
	public void setExhaustPressure2(String exhaustPressure2) {
		ExhaustPressure2 = exhaustPressure2;
	}
	public String getCondensing2() {
		return Condensing2;
	}
	public void setCondensing2(String condensing2) {
		Condensing2 = condensing2;
	}
	public String getCondensing1() {
		return Condensing1;
	}
	public void setCondensing1(String condensing1) {
		Condensing1 = condensing1;
	}
	public String getExhaustTemperature2() {
		return ExhaustTemperature2;
	}
	public void setExhaustTemperature2(String exhaustTemperature2) {
		ExhaustTemperature2 = exhaustTemperature2;
	}
	public String getExhaustTemperature1() {
		return ExhaustTemperature1;
	}
	public void setExhaustTemperature1(String exhaustTemperature1) {
		ExhaustTemperature1 = exhaustTemperature1;
	}
	public String getFuelPressure2() {
		return FuelPressure2;
	}
	public void setFuelPressure2(String fuelPressure2) {
		FuelPressure2 = fuelPressure2;
	}
	public String getFuelPressure1() {
		return FuelPressure1;
	}
	public void setFuelPressure1(String fuelPressure1) {
		FuelPressure1 = fuelPressure1;
	}
	public String getPressureDifference1() {
		return PressureDifference1;
	}
	public void setPressureDifference1(String pressureDifference1) {
		PressureDifference1 = pressureDifference1;
	}
	public String getPressureDifference2() {
		return PressureDifference2;
	}
	public void setPressureDifference2(String pressureDifference2) {
		PressureDifference2 = pressureDifference2;
	}
	public String getLeaveExhaustPressure() {
		return LeaveExhaustPressure;
	}
	public void setLeaveExhaustPressure(String leaveExhaustPressure) {
		LeaveExhaustPressure = leaveExhaustPressure;
	}
	public String getLineVoltage() {
		return LineVoltage;
	}
	public void setLineVoltage(String lineVoltage) {
		LineVoltage = lineVoltage;
	}
	public String getLineCurrent() {
		return LineCurrent;
	}
	public void setLineCurrent(String lineCurrent) {
		LineCurrent = lineCurrent;
	}
	public String getMotorTemperature() {
		return MotorTemperature;
	}
	public void setMotorTemperature(String motorTemperature) {
		MotorTemperature = motorTemperature;
	}
	public String getGuideVane() {
		return GuideVane;
	}
	public void setGuideVane(String guideVane) {
		GuideVane = guideVane;
	}
	public String getOilTemperature() {
		return OilTemperature;
	}
	public void setOilTemperature(String oilTemperature) {
		OilTemperature = oilTemperature;
	}
	public String getLeaveOil() {
		return LeaveOil;
	}
	public void setLeaveOil(String leaveOil) {
		LeaveOil = leaveOil;
	}
	public String getElectricAA() {
		return ElectricAA;
	}
	public void setElectricAA(String electricAA) {
		ElectricAA = electricAA;
	}
	
	@Override
	public String toString() {
		return "Inspection [UnitId=" + UnitId + ", Id=" + Id + ", Name=" + Name
				+ ", Waterlevel=" + Waterlevel + ", Pressure=" + Pressure
				+ ", Battery=" + Battery + ", Transformer=" + Transformer
				+ ", Voltage=" + Voltage + ", Electric=" + Electric
				+ ", ElectricAA=" + ElectricAA + ", ElectricAAA=" 
				+ ", PowerFactor=" + PowerFactor + ", ChilledWater="
				+ ChilledWater + ", ChilledWaterA=" + ChilledWaterA
				+ ", FreezingWaterPressure=" + FreezingWaterPressure
				+ ", FreezinginletPressure=" + FreezinginletPressure
				+ ", CoolingWaterTemperature=" + CoolingWaterTemperature
				+ ", CoolingInletTemperature=" + CoolingInletTemperature
				+ ", CoolingWaterPressure=" + CoolingWaterPressure
				+ ", CoolingInletPressure=" + CoolingInletPressure
				+ ", LineVoltage=" + LineVoltage + ", LineCurrent="
				+ LineCurrent + ", MotorTemperature=" + MotorTemperature
				+ ", GuideVane=" + GuideVane + ", OilTemperature="
				+ OilTemperature + ", LeaveOil=" + LeaveOil
				+ ", LeaveExhaustPressure=" + LeaveExhaustPressure
				+ ", Inspiratory=" + Inspiratory + ", Inspiratory1="
				+ Inspiratory1 + ", Inspiratory2=" + Inspiratory2
				+ ", Evaporating=" + Evaporating + ", Evaporating1="
				+ Evaporating1 + ", Evaporating2=" + Evaporating2
				+ ", ExhaustPressure=" + ExhaustPressure
				+ ", ExhaustPressure1=" + ExhaustPressure1
				+ ", ExhaustPressure2=" + ExhaustPressure2 + ", Condensing="
				+ Condensing + ", Condensing1=" + Condensing1
				+ ", Condensing2=" + Condensing2 + ", ExhaustTemperature="
				+ ExhaustTemperature + ", ExhaustTemperature1="
				+ ExhaustTemperature1 + ", ExhaustTemperature2="
				+ ExhaustTemperature2 + ", FuelPressure=" + FuelPressure
				+ ", FuelPressure1=" + FuelPressure1 + ", FuelPressure2="
				+ FuelPressure2 + ", PressureDifference=" + PressureDifference
				+ ", PressureDifference1=" + PressureDifference1
				+ ", PressureDifference2=" + PressureDifference2
				+ ", Temperature=" + Temperature + ", Humidity=" + Humidity
				+ ", AbnormalNormal=" + AbnormalNormal + ", Reason=" + Reason
				+ ", PatrolMan=" + PatrolMan + ", PatrolTime=" + PatrolTime
				+ ", A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + "]";
	}
}
