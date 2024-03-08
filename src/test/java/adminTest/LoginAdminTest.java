package adminTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.*;

import base.TestBase;
import admin.LoginAdmin;

public class LoginAdminTest extends TestBase{
	private void performLogin(String username, String password) {
        new LoginAdmin().loginAdmin(username, password);
        }
	
	@DataProvider(name = "credentials")
    public Object[][] provideCredentials() {
        return new Object[][]{
                {"admin", "1234"}
        };
    }
	
	//@Test(dataProvider = "credentials", priority = 1)
	public void loginAdminTest(String username, String password) {
		performLogin(username, password);
	}
	@Test(dataProvider = "credentials", priority = 1)
	public void replyMessageTest(String username, String password) {
		performLogin(username, password);
		new LoginAdmin().replyMessage("shut up");
	}
	
}
