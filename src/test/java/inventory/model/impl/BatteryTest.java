package test.java.inventory.model.impl;

import main.java.com.kbteam.netcracker.inventory.model.impl.Battery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by oleksandr on 05.10.16.
 */
public class BatteryTest {

    Battery battery;

    int chargeVolume = 0;

    @Before
    public void before() throws Exception {
        battery = new Battery();
    }

    @After
    public void after() throws Exception {
        battery = null;
    }

    @Test
    public void setGetChargeVolume() throws Exception {
        battery.setChargeVolume(chargeVolume);
        int result = battery.getChargeVolume();

        assertEquals(chargeVolume, result);
    }

}