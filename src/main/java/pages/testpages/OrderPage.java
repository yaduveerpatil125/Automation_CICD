package pages.testpages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//WebElements
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderedItems;
	
	
	
	//methods
	public boolean VerifyOrderDisplay(String productName) {

		Boolean match = orderedItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}

}
