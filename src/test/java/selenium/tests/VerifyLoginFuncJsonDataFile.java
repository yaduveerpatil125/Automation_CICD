package selenium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.testpages.CartPage;
import pages.testpages.CheckOutPage;
import pages.testpages.ConfirmationPage;
import pages.testpages.LoginPage;
import pages.testpages.OrderPage;
import pages.testpages.ProductsPage;
import selenium.testComponents.BaseTest;

public class VerifyLoginFuncJsonDataFile extends BaseTest {

	public static WebDriver driver;
	LoginPage loginpage;
	
	@Test(dataProvider = "getData",groups = "loginverify")
	public void VerifyLoginFunctionality(HashMap<String,String> input) throws IOException, InterruptedException {

		loginpage = new LoginPage(BaseTest.driver);

		loginpage.goTo(prop.getProperty("url"));

		loginpage.doLogin(input.get("email"), input.get("password"));

		Assert.assertEquals(loginpage.getLoginErrorMessage(), "Incorrect email or password.");
	}
		
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\selenium\\data\\loginCredentials.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
