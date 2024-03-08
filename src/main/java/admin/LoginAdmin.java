package admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseUtils.SetUp;

public class LoginAdmin {
	private static WebDriverWait wait;
	WebDriver driver = SetUp.getWebDriver(SetUp.BrowserType.CHROME);
	
	public void loginAdmin(String username, String password) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Attente explicite de 10 secondes
		driver.findElement(By.className("adminbutton")).click();

		// Selenium attendra jusqu'à ce que l'élément soit visible, avec une limite de
		// 10 secondes.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_username")));

		driver.findElement(By.id("login_username")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
	}
	
	public void replyMessage(String msg) {
		driver.findElement(By.linkText("Messages")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("chat_box")));
		driver.findElement(By.className("user")).click();
		driver.findElement(By.id("shout_message")).sendKeys(msg, Keys.RETURN);
	}

}
