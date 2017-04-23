package test.java.inventory.service.impl;

import main.java.com.kbteam.netcracker.inventory.service.Service;
import main.java.com.kbteam.netcracker.inventory.service.impl.ServiceImpl;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by oleksandr on 05.10.16.
 */
public class ServiceImplTest {

    Service service;

    @Before
    public void before() throws Exception {
        service = new ServiceImpl();
    }

    @Test(expected = NotImplementedException.class)
    public void sortByIN() throws Exception {
        service.sortByIN(null);
    }

    @Test(expected = NotImplementedException.class)
    public void filtrateByType() throws Exception {
        service.filtrateByType(null, null);
    }

}