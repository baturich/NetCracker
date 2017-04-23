package main.java.com.kbteam.netcracker.inventory.service.impl;


import main.java.com.kbteam.netcracker.inventory.model.Device;
import main.java.com.kbteam.netcracker.inventory.service.DeviceService;
import main.java.com.kbteam.netcracker.inventory.service.Service;

import java.util.*;

public class ServiceImpl implements Service {

    @Override
    public DeviceService getDeviceService() {
        return new DeviceServiceImpl();
    }

    @Override
    public void sortByIN(Device[] devices) {
        List<Device> normalObjects = new ArrayList<>();
        List<Device> noINObjects = new ArrayList<>();
        List <Device> finalList = new ArrayList<>();
        Device[] finalArray = new Device[devices.length];

        for (Device d : devices){
            if(d != null) {
                if (d.getIn() == 0) noINObjects.add(d);
                else normalObjects.add(d);
            }
        }

        Collections.sort(normalObjects, new Comparator<Device>() {
            @Override
            public int compare(Device o1, Device o2) {
                return o1.getIn() - o2.getIn();
            }
        });

        finalList.addAll(normalObjects);
        finalList.addAll(noINObjects);

        finalArray =  finalList.toArray(finalArray);
        for(int i = 0; i < devices.length; i++)
            devices[i] = finalArray[i];
    }

    @Override
    public void filtrateByType(Device[] devices, String type) {
        if (devices == null || devices.length == 0) {
            return;
        }

        for (int i = 0; i < devices.length; i++) {
            if (devices[i] != null) {
                if (devices[i].getType() != null && type != null) {
                    if (!devices[i].getType().equals(type)) {
                        devices[i] = null;
                    }
                } else if ((devices[i].getType() != null && type == null) || (devices[i].getType() == null && type != null)) {
                    devices[i] = null;
                }
            }
        }

    }
}
