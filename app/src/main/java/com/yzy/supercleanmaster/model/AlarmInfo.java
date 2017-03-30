package com.yzy.supercleanmaster.model;

/**
 * Created by lkzw on 2017/3/28.
 */

public class AlarmInfo {
    private String TagName;

    private String AlarmValue;

    private String EventType;

    private String EventTime;

    private String AlarmText;

    private String State;

    private String Location;

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public String getAlarmValue() {
        return AlarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        AlarmValue = alarmValue;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getEventTime() {
        return EventTime;
    }

    public void setEventTime(String eventTime) {
        EventTime = eventTime;
    }

    public String getAlarmText() {
        return AlarmText;
    }

    public void setAlarmText(String alarmText) {
        AlarmText = alarmText;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
