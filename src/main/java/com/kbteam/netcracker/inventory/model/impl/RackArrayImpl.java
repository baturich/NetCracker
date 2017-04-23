package main.java.com.kbteam.netcracker.inventory.model.impl;

import main.java.com.kbteam.netcracker.inventory.exception.DeviceValidationException;
import main.java.com.kbteam.netcracker.inventory.model.Device;
import main.java.com.kbteam.netcracker.inventory.model.Rack;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RackArrayImpl implements Rack {

    private static Logger LOGGER = Logger.getLogger(RackArrayImpl.class.getName());

    private int size;
    private String type;
    private Device[] rackArray;

    public RackArrayImpl(int size, String type) {
        if(size < 0) {
            LOGGER.log(Level.SEVERE, "Rack size should not be negative", size);
            throw new IllegalArgumentException("Rack size should not be negative");
        }
        this.size = size;
        if(type == null) {
            LOGGER.log(Level.WARNING,"Device type for the rack set as null");
            rackArray = new Device[size];
        }
        else {
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
        checkOnRightIndex(index);
        try {
            return rackArray[index];
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean insertDevToSlot(Device device, int index) {
        checkOnRightIndex(index);
        if(device == null || device.getIn() == 0 || device.getType() == null) {
            LOGGER.log(Level.SEVERE, "Device is not valid for operation Rack.insertDevToSlot", device);
            throw new DeviceValidationException("Rack.insertDevToSlot", device);
        }
        if (rackArray[index] == null && device.getType().equals(this.type)) {
            rackArray[index] = device;
            return true;
        }
        return false;
    }

    @Override
    public Device removeDevFromSlot(int index) {
        checkOnRightIndex(index);
        if (rackArray[index] != null) {
            Device tempDev = rackArray[index];
            rackArray[index] = null;
            return tempDev;
        }
        else LOGGER.warning("«Can not remove from empty slot " + index);
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

    private void checkOnRightIndex(int index){
        if(index < 0 || index > rackArray.length - 1) {
            LOGGER.log(Level.SEVERE, "Wrong index. Choose between 0 and " + (rackArray.length - 1), index);
            throw new IndexOutOfBoundsException("Wrong index. Choose between 0 and " + (rackArray.length - 1));
        }
    }
}