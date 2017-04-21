package main.java.com.kbteam.netcracker.inventory.model.impl;

import main.java.com.kbteam.netcracker.inventory.model.Device;
import main.java.com.kbteam.netcracker.inventory.model.Rack;

import java.util.Arrays;

public class RackArrayImpl implements Rack {

    private int size;
    private String type;
    private Device[] rackArray;

    public RackArrayImpl(int size, String type) {
        this.size = size;
        this.type = type;
        switch (type){
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
        for (Device i:rackArray)
            if(i == null)
                freeSize++;
        return freeSize;
    }

    @Override
    public Device getDevAtSlot(int index) {
        return rackArray[index];
    }

    @Override
    public boolean insertDevToSlot(Device device, int index) {
        try {
            rackArray[index] = device;
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Device removeDevFromSlot(int index) {
        if(rackArray[index] != null) {
            Device tempDev = rackArray[index];
            rackArray[index] = null;
            return tempDev;
        }
        return null;
    }

    @Override
    public Device getDevByIN(int in) {
        for(Device i:rackArray)
            if(i.getIn() == in)
                return i;
        return null;
    }

    @Override
    public Device[] getAllDeviceAsArray() {
        return rackArray;
    }
}
