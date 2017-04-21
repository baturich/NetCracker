package main.java.com.kbteam.netcracker.inventory.service;

import main.java.com.kbteam.netcracker.inventory.model.Device;

/**The interface Service describe list of services of Inventory component
 *
 * Created by makovetskyi on 05.10.2016.
 */
public interface Service {

    /**
     * Return DeviceService implementation
     *
     * @return implementation of DeviceService interface
     */
    //DeviceService getDeviceService();

    /**
     * Sort array of Device-s by identification number.
     *
     * @param devices - array of Device-s, that need to be sorted
     */
    void sortByIN(Device[] devices);

    /**
     * Filtrate array of Device-s by type
     *
     * @param devices - array of Device-s, that need to be filtrated
     * @param type - type of Devices, that will remain in the array after filtering
     */
    void filtrateByType(Device[] devices, String type);
}
