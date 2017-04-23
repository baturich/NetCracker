package main.java.com.kbteam.netcracker.inventory.model.impl;

import main.java.com.kbteam.netcracker.inventory.model.Device;

import java.util.Date;
import java.util.logging.Logger;

public abstract class AbstractDevice implements Device {

    private static Logger LOGGER = Logger.getLogger(AbstractDevice.class.getName());

    private int in;
    private final String type = this.getClass().getSimpleName();
    private String manufacturer;
    private String model;
    private Date productionDate;

    @Override
    public int getIn() {
        return in;
    }

    @Override
    public void setIn(int in) {
        this.in = in;
    }

    @Override
    public String getType() {
        return type;
    }

    @Deprecated
    @Override
    public void setType(String type) {
        LOGGER.warning("Deprecated method called setType.");
//        this.type = type;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public Date getProductionDate() {
        return productionDate;
    }

    @Override
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}
