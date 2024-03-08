package baseUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetUp {

    protected static WebDriver driver;

    static {
    	WebDriverManager.chromedriver().browserVersion("122.0.6261.69").setup();
    }

    public enum BrowserType {
        CHROME,
        EDGE
        // Add more browser types if needed
    }
    
    public static WebDriver getWebDriver(BrowserType browserType) {
        if (driver == null) {
        	initializeWebDriver(browserType);
        }
        return driver;
    }
    

    private static void initializeWebDriver(BrowserType browserType) {
    	switch (browserType) {
        case CHROME:
            driver = new ChromeDriver();
            break;
        case EDGE:
            driver = new EdgeDriver();
            break;
        // Add more cases for other browsers if needed
        default:
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
    }
        driver.manage().window().maximize();
        driver.get("http://localhost/application/index.php");
        //driver.get("http://localhost/appBureauPoste/index.php");
    }
    
    public static void closeWebDriver() {
    	if (driver != null) {
            driver.quit(); // Use quit() instead of close() to close all browser windows and end the WebDriver session.
            driver = null; // Set driver to null to indicate that the WebDriver session has ended.
        }
    }
}
