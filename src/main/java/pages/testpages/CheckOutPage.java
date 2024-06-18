package pages.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='coupon']")
	WebElement couponBox;
	
	@FindBy(xpath = "(//*[contains(text(),'Apply Coupon')])[2]")
	WebElement applyCouponBtn;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countrySelectionBtn;

	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	WebElement placeOrderBtn;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCntry;
	
	By toastMessage = By.cssSelector("#toast-container");
	By animator = By.cssSelector(".ng-animating");

	public void selectCountry(String countryName) throws InterruptedException {

		selectMeThisCountry(countryName, countrySelectionBtn);
		selectCntry.click();
		Thread.sleep(3000);
	}

	public void applyCouponCode(String coupon) {
		couponBox.sendKeys(coupon);
		applyCouponBtn.click();
		waitForElementToDisappear(animator);
		
	}

	public ConfirmationPage placeOrder() throws InterruptedException {
		scrollIntoView(placeOrderBtn);
		//waitForElementToBeClickable(placeOrderBtn);
		//Actions a = new Actions(driver);
		//a.moveToElement(placeOrderBtn).build();
		//countrySelectionBtn.sendKeys(Keys.DOWN);
		//placeOrderBtn.sendKeys(Keys.ENTER);
		clickElement(placeOrderBtn);
		//Thread.sleep(5000);
		//placeOrderBtn.click();
		return new ConfirmationPage(driver);
	}
	
	

}
