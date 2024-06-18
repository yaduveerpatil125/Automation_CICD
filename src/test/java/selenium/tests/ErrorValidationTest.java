package selenium.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.testpages.CartPage;
import pages.testpages.CheckOutPage;
import pages.testpages.ConfirmationPage;
import pages.testpages.LoginPage;
import pages.testpages.ProductsPage;
import selenium.testComponents.BaseTest;
import selenium.testComponents.RetryFailedTests;

public class ErrorValidationTest extends BaseTest {

	public static WebDriver driver;
	LoginPage loginpage;
	ProductsPage product;
	CartPage cart;

	@Test(groups = {"ErrorValidation"}, retryAnalyzer = RetryFailedTests.class)//retry logic can't go inside xml file
	//hence it has to be given at the test level
	public void LoginErrorValidation() throws IOException, InterruptedException {

		loginpage = new LoginPage(BaseTest.driver);

		loginpage.goTo(prop.getProperty("url"));

		loginpage.doLogin("yaduveerpatil@yahoo.com", "sharath");

		Assert.assertEquals(loginpage.getLoginErrorMessage(), "Correct email or password.");

	}

//added this comment for webhook testing

	@Test (groups = {"ErrorValidation"})
	public void ProductErrorValidation() {
		loginpage = new LoginPage(BaseTest.driver);

		loginpage.goTo(prop.getProperty("url"));

		product = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

		List<WebElement> products = product.getProductsList();

		product.addProductToCart(BaseTest.prop.getProperty("productName"));
		cart = product.goToCartPage();

		Boolean match = cart.VerifyProductDisplay(prop.getProperty("productName"));
		Assert.assertTrue(match);

	}

}
