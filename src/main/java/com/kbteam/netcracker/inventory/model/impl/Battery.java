package main.java.com.kbteam.netcracker.inventory.model.impl;

import main.java.com.kbteam.netcracker.inventory.model.Device;

public class Battery extends AbstractDevice implements Device{
    private int chargeVolume;

    public int getChargeVolume() {
        return chargeVolume;
    }

    public void setChargeVolume(int chargeVolume) {
        this.chargeVolume = chargeVolume;
    }
}
