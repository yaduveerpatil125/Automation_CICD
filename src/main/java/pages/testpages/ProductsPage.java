package pages.testpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ProductsPage extends AbstractComponent {

	WebDriver driver;

	// constructor
	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // or just 'this' also works
	}

	// WebElements
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By products_by = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By animator = By.cssSelector(".ng-animating");

	// Products page methods

	public List<WebElement> getProductsList() {
		waitForElementToAppear(products_by);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		return getProductsList().stream().filter(prod->prod.findElement(By.cssSelector("b")).getText()
				.equalsIgnoreCase(productName)).findFirst().orElse(null);
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(animator);
	}

}
