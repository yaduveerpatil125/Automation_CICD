package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.testpages.CartPage;
import pages.testpages.CheckOutPage;
import pages.testpages.ConfirmationPage;
import pages.testpages.LoginPage;
import pages.testpages.ProductsPage;
import selenium.testComponents.BaseTest;

public class PurchaseOrder extends BaseTest{
	
	public LoginPage login;
	public ProductsPage product;
	public CartPage cart;
	public CheckOutPage checkout;
	public ConfirmationPage confirmation;
	
	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
		initializeDriver();
		login = new LoginPage(BaseTest.driver);
		login.goTo(prop.getProperty("url"));
	}
	
	@Given("^Login with (.+) and (.+)$")
	public void login_with_username_password(String username,String password) {
		product = login.doLogin(username, password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		product.addProductToCart(productName);
	}
	
	@And("^checkout (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String productName) throws InterruptedException {
		cart = product.goToCartPage();
		Boolean match = cart.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkout = cart.gotoCheckout();
		checkout.selectCountry("India");
		confirmation = checkout.placeOrder();
	}

	@Then("verify if {string} is displayed on the confirmation page")
	public void verify_order_success_message(String message) {
		String orderSuccessMessage = confirmation.getConfirmationMessage();
		Assert.assertTrue(orderSuccessMessage.equalsIgnoreCase(message));
		BaseTest.driver.close();
	}
}
