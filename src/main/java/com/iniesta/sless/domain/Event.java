package com.iniesta.sless.domain;

import java.util.Date;

public class Event {

    private String user;
    private String device;
    private String signal;
    private float value;
    private Date timestamp;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "user='" + user + '\'' +
                ", device='" + device + '\'' +
                ", signal='" + signal + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
