package selenium.tests;

import java.io.IOException;
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

public class PlaceTheOrderTest extends BaseTest {

	public static WebDriver driver;
	String actualSuccessMessage = "Thankyou for the order.";
	LoginPage loginpage;
	ProductsPage product;
	CartPage cart;
	CheckOutPage checkout;
	ConfirmationPage confirmation;
	OrderPage order;

//added this line for webhook verification

	@Test
	public void SubmitOrder() throws IOException, InterruptedException {

		loginpage = new LoginPage(BaseTest.driver);

		loginpage.goTo(prop.getProperty("url"));

		product = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

		List<WebElement> products = product.getProductsList();

		product.addProductToCart(BaseTest.prop.getProperty("productName"));
		cart = product.goToCartPage();

		Boolean match = cart.VerifyProductDisplay(prop.getProperty("productName"));
		Assert.assertTrue(match);
		checkout = cart.gotoCheckout();

		checkout.selectCountry("India");
		confirmation = checkout.placeOrder();

		String orderSuccessMessage = confirmation.getConfirmationMessage();

		System.out.println(orderSuccessMessage);

		Assert.assertTrue(orderSuccessMessage.equalsIgnoreCase(actualSuccessMessage));
	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void CheckOrderHistory() {
		loginpage = new LoginPage(BaseTest.driver);

		loginpage.goTo(prop.getProperty("url"));

		product = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		order = product.gotoOrdersPage();
		
		Assert.assertTrue(order.VerifyOrderDisplay(prop.getProperty("productName")));
	}
	
}
