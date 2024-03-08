package client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseUtils.SetUp;

import java.time.Duration;

public class SignUpClient {

    private static WebDriverWait wait;

    public void signUpClient(String first_name, String last_name, String username, String email, String password,
                             String secret_code, String resp_code, String gender) {
        WebDriver driver = SetUp.getWebDriver(SetUp.BrowserType.CHROME);

        try {
            // Explicit wait for the "installbutton" element to be clickable
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.className("installbutton")));

            driver.findElement(By.className("installbutton")).click();

            // Explicit wait for the "inscription" element to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inscription")));
            driver.findElement(By.id("inscription")).click();

            // Fill in the form fields
            driver.findElement(By.id("first_name")).sendKeys(first_name);
            driver.findElement(By.id("last_name")).sendKeys(last_name);
            driver.findElement(By.id("user")).sendKeys(username);
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("mdp1")).sendKeys(password);
            driver.findElement(By.id("mdp2")).sendKeys(password);
            driver.findElement(By.id("code_s")).sendKeys(secret_code);
            driver.findElement(By.id("code_sec")).sendKeys(resp_code);

            // Find the radio button element based on the parameter
            WebElement radioButton = driver.findElement(
                    By.xpath("//input[@type='radio' and translate(@value, 'A-Z', 'a-z')='" + gender.toLowerCase() + "']"));

            // Check if the radio button is not already selected
            if (!radioButton.isSelected()) {
                // Click on the radio button to select it
                radioButton.click();
            }

            driver.findElement(By.id("enregistrer")).click();
        } catch (Exception e) {
            // Handle exceptions or log the error
            e.printStackTrace();
        }
    }
}
