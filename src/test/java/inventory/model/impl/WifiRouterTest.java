package test.java.inventory.model.impl;

import main.java.com.kbteam.netcracker.inventory.model.impl.WifiRouter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by oleksandr on 05.10.16.
 */
public class WifiRouterTest {

    WifiRouter wifiRouter;

    String securityProtocol = "";

    @Before
    public void before() throws Exception {
        wifiRouter = new WifiRouter();
    }

    @After
    public void after() throws Exception {
        wifiRouter = null;
    }

    @Test
    public void setGetSecurityProtocol() throws Exception {
        wifiRouter.setSecurityProtocol(securityProtocol);
        String result = wifiRouter.getSecurityProtocol();

        assertEquals(securityProtocol, result);
    }

}