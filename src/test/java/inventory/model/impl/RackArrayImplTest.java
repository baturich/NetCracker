package test.java.inventory.model.impl;


import main.java.com.kbteam.netcracker.inventory.exception.DeviceValidationException;
import main.java.com.kbteam.netcracker.inventory.model.*;
import main.java.com.kbteam.netcracker.inventory.model.impl.*;
import main.java.com.kbteam.netcracker.inventory.service.impl.ServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by oleksandr on 05.10.16.
 */
public class RackArrayImplTest {

    String batteryName = new StringBuilder("Bat").append("tery").toString();
    String routerName = new StringBuilder("Rou").append("ter").toString();
    String switchName = new StringBuilder("Swi").append("tch").toString();
    String wifiRouterName = new StringBuilder("Wifi").append("Router").toString();

    RackArrayImpl rackSize0;
    RackArrayImpl rackSize1;
    RackArrayImpl rackSize3;
    RackArrayImpl rackEmpty;
    RackArrayImpl rackPartlyFilled;
    RackArrayImpl rackFullFilled;

    Battery battery;

//    @BeforeClass
//    public static void setUp() throws Exception {
//        Main.initLogger("/logging.properties");
//    }

    @Before
    public void before() throws Exception {
        rackSize0 = new RackArrayImpl(0, Battery.class);
        rackSize1 = new RackArrayImpl(1, Battery.class);
        rackSize3 = new RackArrayImpl(3, Battery.class);
        rackEmpty = new RackArrayImpl(10, Battery.class);
        rackPartlyFilled = new RackArrayImpl(10, Battery.class);
        rackFullFilled = new RackArrayImpl(10, Battery.class);

        battery = new Battery();
        battery.setIn(12);

        for (int i = 0; i < 10; i++) {
            rackFullFilled.insertDevToSlot(battery, i);
            if ((i % 2) == 0) {
                rackPartlyFilled.insertDevToSlot(battery, i);
            }
        }
    }

    @After
    public void after() throws Exception {

    }

    @Deprecated
    @Test
    public void constructorDeprecated() throws Exception {
        RackArrayImpl rack0 = new RackArrayImpl(5, batteryName);
        rack0.insertDevToSlot(battery, 0);
        rack0 = new RackArrayImpl(5, routerName);
        Router router = new Router();
        router.setIn(1);
        rack0.insertDevToSlot(router, 0);
        rack0 = new RackArrayImpl(5, switchName);
        Switch aSwitch = new Switch();
        aSwitch.setIn(2);
        rack0.insertDevToSlot(aSwitch, 0);
        rack0 = new RackArrayImpl(5, wifiRouterName);
        WifiRouter wifiRouter = new WifiRouter();
        wifiRouter.setIn(3);
        rack0.insertDevToSlot(wifiRouter, 0);
    }

    @Test
    public void constructor() throws Exception {
        RackArrayImpl rack0 = new RackArrayImpl(5, Battery.class);
        rack0.insertDevToSlot(battery, 0);
        Device result1 = rack0.getDevAtSlot(0);

        rack0 = new RackArrayImpl(5, Router.class);
        Router router = new Router();
        router.setIn(1);
        rack0.insertDevToSlot(router, 0);
        Device result2 = rack0.getDevAtSlot(0);

        rack0 = new RackArrayImpl(5, WifiRouter.class);
        WifiRouter wifiRouter = new WifiRouter();
        wifiRouter.setIn(1);
        rack0.insertDevToSlot(wifiRouter, 0);
        Device result3 = rack0.getDevAtSlot(0);

        rack0 = new RackArrayImpl(5, Switch.class);
        Switch aSwitch = new Switch();;
        aSwitch.setIn(1);
        rack0.insertDevToSlot(aSwitch, 0);
        Device result4 = rack0.getDevAtSlot(0);

        assertEquals(battery, result1);
        assertEquals(router, result2);
        assertEquals(wifiRouter, result3);
        assertEquals(aSwitch, result4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorSizeNegative() throws Exception {
        RackArrayImpl rack0 = new RackArrayImpl(-5, Battery.class);
    }

    @Test
    public void constructorSize0() throws Exception {
        RackArrayImpl rack0 = new RackArrayImpl(0, Battery.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTypeNull() throws Exception {
        RackArrayImpl rack0 = new RackArrayImpl(5, (Class) null);
    }

    @Test
    public void constructorTypeDevice() throws Exception {
        RackArrayImpl rack0 = new RackArrayImpl(5, Device.class);
        rack0.insertDevToSlot(battery, 0);
        Device result1 = rack0.getDevAtSlot(0);

        Router router = new Router();
        router.setIn(1);
        rack0.insertDevToSlot(router, 1);
        Device result2 = rack0.getDevAtSlot(1);

        WifiRouter wifiRouter = new WifiRouter();
        wifiRouter.setIn(1);
        rack0.insertDevToSlot(wifiRouter, 2);
        Device result3 = rack0.getDevAtSlot(2);

        Switch aSwitch = new Switch();;
        aSwitch.setIn(1);
        rack0.insertDevToSlot(aSwitch, 3);
        Device result4 = rack0.getDevAtSlot(3);

        assertEquals(battery, result1);
        assertEquals(router, result2);
        assertEquals(wifiRouter, result3);
        assertEquals(aSwitch, result4);
    }

    @Test
    public void getSize0() throws Exception {
        int expResult0 = 0;

        int result0 = rackSize0.getSize();

        assertEquals(expResult0, result0);
    }

    @Test
    public void getSize3() throws Exception {
        int expResult3 = 3;

        int result3 = rackSize3.getSize();

        assertEquals(expResult3, result3);
    }

    @Test
    public void getFreeSize() throws Exception {
        int rackSize = 10;
        RackArrayImpl rackForWoodpecker = new RackArrayImpl(rackSize, "Battery");
        for (int i = 0; i < rackSize; i++) {
            rackForWoodpecker.insertDevToSlot(battery, 1);
        }
        int expResult0 = 0;
        int expResult1 = 0;
        int expResult2 = 5;
        int expResult3 = 10;
        int expResult4 = 9;

        int result0 = rackSize0.getFreeSize();
        int result1 = rackFullFilled.getFreeSize();
        int result2 = rackPartlyFilled.getFreeSize();
        int result3 = rackEmpty.getFreeSize();
        int result4 = rackForWoodpecker.getFreeSize();

        assertEquals(expResult0, result0);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    @Test
    public void getDevAtSlot() throws Exception {
        Device expResultDev = battery;

        Device result1 = rackPartlyFilled.getDevAtSlot(0);
        Device result2 = rackPartlyFilled.getDevAtSlot(2);
        Device result3 = rackPartlyFilled.getDevAtSlot(8);
        Device result4 = rackEmpty.getDevAtSlot(0);
        Device result5 = rackEmpty.getDevAtSlot(2);
        Device result6 = rackEmpty.getDevAtSlot(8);

        assertEquals(expResultDev, result1);
        assertEquals(expResultDev, result2);
        assertEquals(expResultDev, result3);
        assertNull(result4);
        assertNull(result5);
        assertNull(result6);

    }

    @Test
    public void getDevAtSlotIndexOutOfBounds() throws Exception {
        int[] indexesFor0 = new int[] {-5, -1, 0, 5};
        int[] indexesFor1 = new int[] {-5, -1, 1, 5};
        int[] indexesFor3 = new int[] {-5, -1, 3, 5};
        int[][] allIndexes = new int[][] {indexesFor0, indexesFor1, indexesFor3};
        Rack[] racks = new Rack[] {rackSize0, rackSize1, rackSize3};
        int count = 0;
        int expCount = indexesFor0.length + indexesFor1.length + indexesFor3.length;

        for (int i = 0; i < racks.length; i++) {
            for (int j = 0; j < allIndexes[i].length; j++) {
                try {
                    racks[i].getDevAtSlot(allIndexes[i][j]);
                } catch (IndexOutOfBoundsException e) {
                    if (e.getClass().equals(IndexOutOfBoundsException.class)) {
                        count++;
                    }
                }
            }
        }

        assertEquals(expCount, count);
    }

    @Deprecated
    @Test
    public void insertDevToSlotDeprecated() throws Exception {

        Rack rackEmpty = new RackArrayImpl(10, "Battery");
        Rack rackPartlyFilled = new RackArrayImpl(10, "Battery");
        Rack rackFullFilled = new RackArrayImpl(10, "Battery");

        Battery battery = new Battery();
        battery.setIn(12);

        for (int i = 0; i < 10; i++) {
            rackFullFilled.insertDevToSlot(battery, i);
            if ((i % 2) == 0) {
                rackPartlyFilled.insertDevToSlot(battery, i);
            }
        }

        Battery anotherBattery = new Battery();
        anotherBattery.setIn(1);
        Router batteryBadType = new Router();
        batteryBadType.setIn(1);

        Device expResultDev = anotherBattery;

        boolean result1 = rackPartlyFilled.insertDevToSlot(anotherBattery, 0);
        boolean result2 = rackPartlyFilled.insertDevToSlot(anotherBattery, 2);
        boolean result3 = rackPartlyFilled.insertDevToSlot(anotherBattery, 8);
        boolean result4 = rackEmpty.insertDevToSlot(anotherBattery, 0);
        Device result4a = rackEmpty.getDevAtSlot(0);
        boolean result5 = rackEmpty.insertDevToSlot(anotherBattery, 3);
        Device result5a = rackEmpty.getDevAtSlot(3);
        boolean result6 = rackEmpty.insertDevToSlot(anotherBattery, 9);
        Device result6a = rackEmpty.getDevAtSlot(9);
        boolean result7 = rackPartlyFilled.insertDevToSlot(batteryBadType, 7); //not filled

        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertTrue(result4);
        assertEquals(expResultDev, result4a);
        assertTrue(result5);
        assertEquals(expResultDev, result5a);
        assertTrue(result6);
        assertEquals(expResultDev, result6a);
        assertFalse(result7);
    }

    @Test
    public void insertDevToSlot() throws Exception {
        Battery anotherBattery = new Battery();
        anotherBattery.setIn(1);
        Switch batteryBadType = new Switch();
        batteryBadType.setIn(1);
        Rack routerRack = new RackArrayImpl(1, Router.class);

        Device expResultDev = anotherBattery;

        boolean result1 = rackPartlyFilled.insertDevToSlot(anotherBattery, 0);
        boolean result2 = rackPartlyFilled.insertDevToSlot(anotherBattery, 2);
        boolean result3 = rackPartlyFilled.insertDevToSlot(anotherBattery, 8);
        boolean result4 = rackEmpty.insertDevToSlot(anotherBattery, 0);
        Device result4a = rackEmpty.getDevAtSlot(0);
        boolean result5 = rackEmpty.insertDevToSlot(anotherBattery, 3);
        Device result5a = rackEmpty.getDevAtSlot(3);
        boolean result6 = rackEmpty.insertDevToSlot(anotherBattery, 9);
        Device result6a = rackEmpty.getDevAtSlot(9);
        boolean result7 = rackPartlyFilled.insertDevToSlot(batteryBadType, 7); //not filled
        boolean result8 = routerRack.insertDevToSlot(batteryBadType, 0);

        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertTrue(result4);
        assertEquals(expResultDev, result4a);
        assertTrue(result5);
        assertEquals(expResultDev, result5a);
        assertTrue(result6);
        assertEquals(expResultDev, result6a);
        assertFalse(result7);
        assertTrue(result8);
    }

    @Test(expected = DeviceValidationException.class)
    public void insertDevToSlotDeviceValidationDeviceNullToFilled() throws Exception {
        rackPartlyFilled.insertDevToSlot(null, 4);
    }

    @Test(expected = DeviceValidationException.class)
    public void insertDevToSlotDeviceValidationDeviceNullToNotFilled() throws Exception {
        rackPartlyFilled.insertDevToSlot(null, 5);
    }

    @Test(expected = DeviceValidationException.class)
    public void insertDevToSlotDeviceValidationDeviceWithoutIN() throws Exception {
        Battery batteryNoIN = new Battery();

        rackPartlyFilled.insertDevToSlot(batteryNoIN, 7); //not filled
    }

    @Test(expected = DeviceValidationException.class)
    public void insertDevToSlotDeviceValidationDeviceWithoutType() throws Exception {
        Device deviceNoType = new AbstractDevice() {
            @Override
            public String getType() {
                return null;
            }
        };
        deviceNoType.setIn(1);

        rackPartlyFilled.insertDevToSlot(deviceNoType, 7); //not filled
    }

    @Test
    public void insertDevToSlotIndexOutOfBounds() throws Exception {
        int[] indexesFor0 = new int[] {-5, -1, 0, 5};
        int[] indexesFor1 = new int[] {-5, -1, 1, 5};
        int[] indexesFor3 = new int[] {-5, -1, 3, 5};
        int[][] allIndexes = new int[][] {indexesFor0, indexesFor1, indexesFor3};
        Rack[] racks = new Rack[] {rackSize0, rackSize1, rackSize3};
        int count = 0;
        int expCount = indexesFor0.length + indexesFor1.length + indexesFor3.length;

        for (int i = 0; i < racks.length; i++) {
            for (int j = 0; j < allIndexes[i].length; j++) {
                try {
                    racks[i].insertDevToSlot(battery, allIndexes[i][j]);
                } catch (IndexOutOfBoundsException e) {
                    if (e.getClass().equals(IndexOutOfBoundsException.class)) {
                        count++;
                    }
                }
            }
        }

        assertEquals(expCount, count);
    }

    @Test
    public void removeDevFromSlot() throws Exception {
        Device expResultDev = battery;

        Device result1 = rackPartlyFilled.removeDevFromSlot(0);
        Device result1a = rackPartlyFilled.getDevAtSlot(0);
        Device result2 = rackPartlyFilled.removeDevFromSlot(2);
        Device result2a = rackPartlyFilled.getDevAtSlot(2);
        Device result3 = rackPartlyFilled.removeDevFromSlot(8);
        Device result3a = rackPartlyFilled.getDevAtSlot(8);
        Device result4 = rackEmpty.removeDevFromSlot(0);
        Device result5 = rackEmpty.removeDevFromSlot(3);
        Device result6 = rackEmpty.removeDevFromSlot(9);

        assertEquals(expResultDev, result1);
        assertNull(result1a);
        assertEquals(expResultDev, result2);
        assertNull(result2a);
        assertEquals(expResultDev, result3);
        assertNull(result3a);
        assertNull(result4);
        assertNull(result5);
        assertNull(result6);
    }

    @Test
    public void removeDevFromSlotIndexOutOfBounds() throws Exception {
        int[] indexesFor0 = new int[] {-5, -1, 0, 5};
        int[] indexesFor1 = new int[] {-5, -1, 1, 5};
        int[] indexesFor3 = new int[] {-5, -1, 3, 5};
        int[][] allIndexes = new int[][] {indexesFor0, indexesFor1, indexesFor3};
        Rack[] racks = new Rack[] {rackSize0, rackSize1, rackSize3};
        int count = 0;
        int expCount = indexesFor0.length + indexesFor1.length + indexesFor3.length;

        for (int i = 0; i < racks.length; i++) {
            for (int j = 0; j < allIndexes[i].length; j++) {
                try {
                    racks[i].removeDevFromSlot(allIndexes[i][j]);
                } catch (IndexOutOfBoundsException e) {
                    if (e.getClass().equals(IndexOutOfBoundsException.class)) {
                        count++;
                    }
                }
            }
        }
    }

    @Test
    public void getDevByIN() throws Exception {
        RackArrayImpl rack0 = new RackArrayImpl(0, Battery.class);
        RackArrayImpl rack1Full = new RackArrayImpl(1, Battery.class);
        RackArrayImpl rack1Empty = new RackArrayImpl(1, Battery.class);
        RackArrayImpl rackEmpty = new RackArrayImpl(5, Battery.class);
        RackArrayImpl rackPartly = new RackArrayImpl(6, Battery.class);
        RackArrayImpl rackFull = new RackArrayImpl(5, Battery.class);
        Battery battery1 = new Battery();
        battery1.setIn(1);
        Battery battery2 = new Battery();
        battery2.setIn(2);
        Battery battery3 = new Battery();
        battery3.setIn(3);
        Battery battery4 = new Battery();
        battery4.setIn(4);
        Device[] devicesForRackPartly = new Device[] {battery1, battery4, battery2, battery4, null, battery3};
        Device[] devicesForRackFull = new Device[] {battery3, battery1, battery2, battery3, battery4};
        rack1Full.insertDevToSlot(battery1, 0);
        int counter = 0;
        for (Device device: devicesForRackPartly ) {
            if (device != null) {
                rackPartly.insertDevToSlot(device, counter++);
            }
        }
        counter = 0;
        for (Device device: devicesForRackFull ) {
            rackFull.insertDevToSlot(device, counter++);
        }

        Device expResultDev1 = battery1;
        Device expResultDev2 = battery2;
        Device expResultDev3 = battery3;
        Device expResultDev4 = battery4;

        Device result1 = rack0.getDevByIN(16);
        Device result2 = rack1Full.getDevByIN(16);
        Device result3 = rack1Full.getDevByIN(1);
        Device result4 = rack1Empty.getDevByIN(16);
        Device result5 = rackEmpty.getDevByIN(16);
        Device result6 = rackPartly.getDevByIN(16);
        Device result7 = rackPartly.getDevByIN(1);
        Device result8 = rackPartly.getDevByIN(2);
        Device result9 = rackPartly.getDevByIN(3);
        Device result10 = rackPartly.getDevByIN(4);
        Device result11 = rackFull.getDevByIN(16);
        Device result12 = rackFull.getDevByIN(1);
        Device result13 = rackFull.getDevByIN(2);

        assertNull(result1);
        assertNull(result2);
        assertEquals(expResultDev1, result3);
        assertNull(result4);
        assertNull(result5);
        assertNull(result6);
        assertEquals(expResultDev1, result7);
        assertEquals(expResultDev2, result8);
        assertEquals(expResultDev3, result9);
        assertEquals(expResultDev4, result10);
        assertNull(result11);
        assertEquals(expResultDev1, result12);
        assertEquals(expResultDev2, result13);
    }

    @Test
    public void getAllDeviceAsArray() throws Exception {
        RackArrayImpl rackPartly = new RackArrayImpl(6, Battery.class);
        Battery battery1 = new Battery();
        battery1.setIn(1);
        Battery battery2 = new Battery();
        battery2.setIn(2);
        Battery battery3 = new Battery();
        battery3.setIn(3);
        Battery battery4 = new Battery();
        battery4.setIn(4);
        Device[] devicesForRackPartly = new Device[] {battery1, battery4, battery2, battery4, null, battery3};
        int counter = 0;
        for (Device device: devicesForRackPartly ) {
            if (device != null) {
                rackPartly.insertDevToSlot(device, counter++);
            }
        }
        Device[] expResult = new Device[] {battery1, battery2, battery3, battery4, battery4};
        int expSize = 5;

        Device[] result = rackPartly.getAllDeviceAsArray();

        new ServiceImpl().sortByIN(result);
        assertEquals(expSize, result.length);
        assertArrayEquals(expResult, result);
    }


}