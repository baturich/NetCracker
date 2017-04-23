package main.java.com.kbteam.netcracker.inventory.exception;

import main.java.com.kbteam.netcracker.inventory.model.Device;

import java.util.logging.Logger;

public class DeviceValidationException extends RuntimeException {

    private static Logger LOGGER = Logger.getLogger(DeviceValidationException.class.getName());

    private Device device;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public DeviceValidationException(String message, Device device) {
        super(message);
        this.device = device;
        LOGGER.severe("Device is not valid for operation " + message);
    }
}
