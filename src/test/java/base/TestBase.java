package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import baseUtils.SetUp;

public class TestBase {

	protected static WebDriver driver;
	
	@BeforeClass
    @Parameters("browserType")
    public void setUp(ITestContext context, SetUp.BrowserType browserType) {
        driver = SetUp.getWebDriver(browserType);
    }

    @AfterClass
    public void tearDown() {
        SetUp.closeWebDriver();
    }
}