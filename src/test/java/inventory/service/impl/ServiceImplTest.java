package test.java.inventory.service.impl;

import main.java.com.kbteam.netcracker.inventory.service.Service;
import main.java.com.kbteam.netcracker.inventory.service.impl.ServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by oleksandr on 05.10.16.
 */
public class ServiceImplTest {
    @Test
    public void sortByIN() throws Exception {
        Service service = new ServiceImpl();
        service.sortByIN(null);
        System.err.println("expected 1 exception \"Operation filtrateByType not supported yet\" (sortByIN())");
    }

    @Test
    public void filtrateByType() throws Exception {
        Service service = new ServiceImpl();
        service.filtrateByType(null, null);
        System.err.println("expected 1 exception \"Operation filtrateByType not supported yet\" (filtrateByType())");
    }

}