package main.java.com.kbteam.netcracker.inventory.service.impl;

import main.java.com.kbteam.netcracker.inventory.model.Device;
import main.java.com.kbteam.netcracker.inventory.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {
    @Override
    public boolean isCastableTo(Device device, Class clazz) {
        if(device != null && clazz != null)
            return clazz.isInstance(device);
        return false;
    }

    @Override
    public boolean isValidDeviceForInsertToRack(Device device) {
        return !(device == null || device.getIn() == 0 || device.getType() == null) ;
    }
}
