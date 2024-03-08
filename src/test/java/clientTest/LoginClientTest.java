package clientTest;

import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.*;

import client.LoginClient;
import dataBase.DatabaseUtils;

import base.TestBase;
import baseUtils.SetUp;
import baseUtils.SetUp.BrowserType;

public class LoginClientTest extends TestBase {
	@DataProvider(name = "userData")
    public Object[][] userData() throws SQLException {
        List<List<String>> users = DatabaseUtils.retrieveAllUserFromDatabase();
        Object[][] data = new Object[users.size()][3];

        for (int i = 0; i < users.size(); i++) {
            List<String> userData = users.get(i);
            data[i][0] = userData.get(0); // username
            data[i][1] = userData.get(1); // password
            data[i][2] = i; // index
        }

        return data;
    }
	
	private void performLogin(SetUp.BrowserType browserType, String username, String password) {
        new LoginClient(browserType).loginClient(username, password);
    }
	
	    //@Test
	    public void testSqlInjection() {
	    	String username = "salimsouidi7";
	    	String password = "MrUnknown' or 'x'='x";
	    	
	    	
	    	//SetUp.BrowserType browserType = SetUp.BrowserType.valueOf(System.getProperty("browserType", "CHROME"));
	        // If "browserType" is not provided, default to CHROME.
			performLogin(SetUp.BrowserType.CHROME, username, password);
	    }

	//@Test(dataProvider = "userData")
    public void loginClientTest(SetUp.BrowserType browserType, String username, String password) {
        performLogin(browserType, username, password);
    }
    
    @Parameters("browserType")
    @Test(dataProvider = "userData", priority = 1)
    public void testOnBrowser(String username, String password, int index) {
    	
    	SetUp.BrowserType browserType = SetUp.BrowserType.valueOf(System.getProperty("browserType", "CHROME"));
        // If "browserType" is not provided, default to CHROME.
		performLogin(browserType, username, password);
    }

    //@Test(dataProvider = "credentials", priority = 2)
    public void detailsServicesTest(SetUp.BrowserType browserType, String username, String password) {
        performLogin(browserType, username, password);
        new LoginClient(browserType).detailsServices("financier");
    }

    //@Test(dataProvider = "credentials", priority = 3)
    public void sendMessageTest(SetUp.BrowserType browserType, String username, String password) {
        performLogin(browserType, username, password);
        new LoginClient(browserType).sendMessage("hello salim");
    }
    
    //@Test(dataProvider = "credentials", priority = 1)
    public void displayAllNotificationTest(SetUp.BrowserType browserType, String username, String password) {
        performLogin(browserType, username, password);
        new LoginClient(browserType).displayAllNotification();
    }
    
    //@Test(dataProvider = "credentials", priority = 1)
    public void deleteNotificationTest(SetUp.BrowserType browserType, String username, String password) {
        performLogin(browserType, username, password);
        new LoginClient(browserType).deleteNotification();
    }

    //@Test(dataProvider = "credentials", priority = 1)
    public void myProfilTest(SetUp.BrowserType browserType, String username, String password) {
        performLogin(browserType, username, password);
        new LoginClient(browserType).myProfil("1234", "souidi");
    }
    
    //@Test(dataProvider = "credentials", priority = 1)
    public void takeTicketTest(SetUp.BrowserType browserType, String username, String password) {
        performLogin(browserType, username, password);
        new LoginClient(browserType).takeTicket("1234");
    }


}

