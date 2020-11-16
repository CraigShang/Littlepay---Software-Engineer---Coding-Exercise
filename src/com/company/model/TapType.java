package com.company.model;

public enum TapType {
    ON,
    OFF;

    public boolean isOn(){
        return this.equals(TapType.ON);
    }

    public boolean isOff(){
        return this.equals(TapType.OFF);
    }
}
