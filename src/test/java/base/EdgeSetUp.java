package base;

import org.testng.annotations.BeforeClass;

import baseUtils.SetUp;

public class EdgeSetUp extends TestBase {

    public static void setUp() {
    	
        driver = SetUp.getWebDriver(SetUp.BrowserType.EDGE);
    }
}
