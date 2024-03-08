package baseUtils;

import org.openqa.selenium.WebDriver;

public class BrowserSetUp {
	public static void setUpChrome() {
		WebDriver driver = SetUp.getWebDriver(SetUp.BrowserType.CHROME);
	}

	public static void setUpEdge() {
		WebDriver driver = SetUp.getWebDriver(SetUp.BrowserType.EDGE);
	}

}
