package main.java.com.kbteam.netcracker.inventory.model.impl;

import main.java.com.kbteam.netcracker.inventory.model.Device;
import main.java.com.kbteam.netcracker.inventory.model.Rack;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RackArrayImpl implements Rack {

    private int size;
    private String type;
    private Device[] rackArray;

    public RackArrayImpl(int size, String type) {
        this.size = size;
        this.type = type;
        switch (type) {
            case "Battery":
                rackArray = new Battery[size];
                break;
            case "Router":
                rackArray = new Router[size];
                break;
            case "Switch":
                rackArray = new Switch[size];
                break;
            case "WifiRouter":
                rackArray = new WifiRouter[size];
                break;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getFreeSize() {
        int freeSize = 0;
        for (Device i : rackArray)
            if (i == null)
                freeSize++;
        return freeSize;
    }

    @Override
    public Device getDevAtSlot(int index) {
        try {
            return rackArray[index];
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean insertDevToSlot(Device device, int index) {
        if (index > -1 && index < rackArray.length && rackArray[index] == null && device != null && device.getType().equals(this.type)) {
            rackArray[index] = device;
            return true;
        }
        return false;
    }

    @Override
    public Device removeDevFromSlot(int index) {
        if (index > -1 && index < rackArray.length) {
            if (rackArray[index] != null) {
                Device tempDev = rackArray[index];
                rackArray[index] = null;
                return tempDev;
            }
        }
        return null;
    }

    @Override
    public Device getDevByIN(int in) {
        for (Device i : rackArray)
            if (i != null)
                if (i.getIn() == in)
                    return i;
        return null;
    }

    @Override
    public Device[] getAllDeviceAsArray() {
        return rackArray;
    }
}