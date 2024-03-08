package clientTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import client.SignUpClient;
import base.TestBase;
import baseUtils.SetUp;

public class SignUpTest extends TestBase{
	WebDriver driver;
	
	String first_name = "Amira";
	String last_name = "kh";
	String username = "amirakh7";
	String email = "amira.kh@gmail.com";
	String password = "amira";
	String secret_code = "metier";
	String resp_code = "professeur";
	String gender = "Femme";
	
	@Test(priority = 1)
	public void signUpTest() {
		SignUpClient signup = new SignUpClient();
		signup.signUpClient(first_name, last_name, username, email, password, secret_code, resp_code, gender);
		
	}
	
	@Test(priority=2)
	public void signInAfterSignUp() {
		LoginClientTest login = new LoginClientTest();
		//login.loginClientTest("amirakh7", "amira");
	}

}
