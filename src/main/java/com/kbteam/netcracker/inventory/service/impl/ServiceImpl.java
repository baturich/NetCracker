package main.java.com.kbteam.netcracker.inventory.service.impl;


import main.java.com.kbteam.netcracker.inventory.model.Device;
import main.java.com.kbteam.netcracker.inventory.service.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceImpl implements Service {

    private static Logger LOGGER = Logger.getLogger(ServiceImpl.class.getName());

    @Override
    public void sortByIN(Device[] devices) {
        LOGGER.log(Level.SEVERE, "Operation sortByIN not supported yet", devices);
        throw new NotImplementedException();
    }

    @Override
    public void filtrateByType(Device[] devices, String type) {
        LOGGER.log(Level.SEVERE, "Operation filtrateByType not supported yet", devices);
        throw new NotImplementedException();
    }
}
