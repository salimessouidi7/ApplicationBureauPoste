package client;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseUtils.SetUp;

import org.openqa.selenium.support.ui.Select;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class LoginClient {
	private static WebDriverWait wait;
	private WebDriver driver;
	// Constructor to initialize the WebDriver
    public LoginClient(SetUp.BrowserType browserType) {
        this.driver = SetUp.getWebDriver(browserType);
    }

	/*
	public void takeScreenshot(String beginfilename) {
		// Convert WebDriver object to TakesScreenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		// Capture the screenshot
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

		// Generate a timestamp for the filename
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		// Specify the directory and filename for the screenshot
		String destinationFilePath = "src/test/resources/screenshots/" + beginfilename + "_" + timestamp + ".png";

		// Copy the screenshot to the desired location
		try {
			org.apache.commons.io.FileUtils.copyFile(sourceFile, new File(destinationFilePath));
			// System.out.println("Screenshot saved: " + destinationFilePath);
		} catch (IOException e) {
			System.out.println("Error while taking screenshot: " + e.getMessage());
		}
	}*/
	
	public void captureFullScreen(String beginfilename) throws AWTException, IOException {
        // Create an instance of Robot class
        Robot robot = new Robot();

        // Get the default toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Get the screen size
        Rectangle screenSize = new Rectangle(toolkit.getScreenSize());

        // Capture the screen
        BufferedImage screenshot = robot.createScreenCapture(screenSize);

        // Generate a timestamp for the filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Specify the directory and filename for the screenshot
        String destinationFilePath = "src/test/resources/screenshots/" + beginfilename + "_" + timestamp + ".png";

        // Save the screenshot to the desired location
        ImageIO.write(screenshot, "png", new File(destinationFilePath));
    }
	
	public void loginClient(String username, String password) {
		driver.get("http://localhost/application/index.php");
        //driver.get("http://localhost/appBureauPoste/index.php");
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(2)); // Explicit wait of 10 seconds.
		driver.findElement(By.className("installbutton")).click();

		// Selenium will wait until the element is visible, with a timeout of 10 seconds.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_username")));

		driver.findElement(By.id("login_username")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);
		
		//takeScreenshot("before_login");
		 try {
			 // Capture the full screen
			 captureFullScreen("before_login");
	        } catch (AWTException | IOException e) {
	        	System.out.println("Error while capturing full screen: " + e.getMessage());
	        }
		driver.findElement(By.id("btn-submit")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"banner\"]/h2[1]")));

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//takeScreenshot("home_page");
		
		// Another way to test if the user is in the home page (more efficient)
        assertTrue(driver.getPageSource().contains("Soyez la bienvenue :) ! "), "Expected text not found in the page source.");

		/*
		//An other way to capture a screenshot using getScreenshotAs
		//driver.findElement(By.id("profil")).getScreenshotAs(OutputType.FILE);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		
		// Generate a timestamp for the filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Specify the directory and filename for the screenshot
        String destinationFilePath = "src/test/resources/screenshots/HomePage_" + timestamp + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(destinationFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
            // Capture the full screen
            captureFullScreen("home_page");
        } catch (AWTException | IOException e) {
            System.out.println("Error while capturing full screen: " + e.getMessage());
        }
	}

	public void detailsServices(String service) {
		Select dropdown = new Select(driver.findElement(By.name("service")));
		// dropdown.selectByIndex(2);
		dropdown.selectByVisibleText(service);
		driver.findElement(By.id("btn-recherche")).click();
		//takeScreenshot("recherche_service");
		try {
            // Capture the full screen
            captureFullScreen("recherche_service");
        } catch (AWTException | IOException e) {
            System.out.println("Error while capturing full screen: " + e.getMessage());
        }

	}
	
	public void sendMessage(String msg) {
		
		driver.findElement(By.className("dropbtn")).click();
		driver.findElement(By.id("message")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header")));
		
		driver.findElement(By.id("shout_message")).sendKeys(msg, Keys.RETURN);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//takeScreenshot("recherche_service");
		try {
            // Capture the full screen
            captureFullScreen("send_message");
        } catch (AWTException | IOException e) {
            System.out.println("Error while capturing full screen: " + e.getMessage());
        }

	}
	
	public void displayAllNotification() {
		driver.findElement(By.className("dropbtn")).click();
		driver.findElement(By.id(("notif"))).click();
	}
	
	public void deleteNotification() {
		displayAllNotification();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"table-content\"]/div/fieldset[1]/a/img")));
		driver.findElement(By.xpath("//*[@id=\"table-content\"]/div/fieldset[1]/a/img")).click();
		
		// Accepts (Click on OK) Chrome Alert Browser for CONFIRM button.
		Alert alertOK = driver.switchTo().alert();
        alertOK.accept();
        
        //Rejects (Click on Cancel) Chrome Browser Alert for RESET button.
        //Alert alertCancel = driver.switchTo().alert();
        //alertCancel.dismiss();
	}
	
	
	public void myProfil(String pwd_act, String pwd_new) {
		driver.findElement(By.className("button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-heading")));
		driver.findElement(By.name("pass_act")).sendKeys(pwd_act, Keys.TAB);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//driver.findElement(By.id("mdp1")).click();
		driver.findElement(By.id("mdp1")).sendKeys(pwd_new);
		driver.findElement(By.id("mdp2")).sendKeys(pwd_new);
		driver.findElement(By.id("btn")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]")));
		String confirmMessage = driver.findElement(By.xpath("/html/body/div[3]/p")).getText();
		assertEquals(confirmMessage, "Votre mot de passe a ete modifier !");
		driver.findElement(By.xpath("/html/body/div[3]/button[2]")).click();
		
		try {
            // Capture the full screen
            captureFullScreen("modif_password");
        } catch (AWTException | IOException e) {
            System.out.println("Error while capturing full screen: " + e.getMessage());
        }
	}
	
	public void takeTicket(String codePostal) {
		driver.findElement(By.linkText("Ticket Online")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nom")));
		driver.findElement(By.name("cod_post")).sendKeys(codePostal, Keys.TAB);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("deleg")));
		new Select(driver.findElement(By.name("loc"))).selectByIndex(1);
		//new Select(driver.findElement(By.name("loc"))).selectByVisibleText(localite);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shout_box")));
		new Select(driver.findElement(By.name("serv_post"))).selectByIndex(1);
		
		driver.findElement(By.id("submit-btn")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]")));
		String confirmMessage = driver.findElement(By.xpath("/html/body/div[3]/p")).getText();
		assertEquals(confirmMessage, "Nous avons envoye le numero de votre ticket dans une notification !");
		driver.findElement(By.xpath("/html/body/div[3]/button[2]")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("deleg")));
		displayAllNotification();
		
		
	}

}
