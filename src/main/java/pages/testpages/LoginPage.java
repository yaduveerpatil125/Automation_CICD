package pages.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;
	//private String url = "https://rahulshettyacademy.com/client";

	// constructor
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);//this step initializes 
		//all the page elements and gives driver knowledge to them
	}

//LoginPage Elements
	@FindBy(css = "input#userEmail")
	WebElement userName;
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id  = "login")
	WebElement loginBtn;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement invalidEmailPswd;
	
//	By userName = By.cssSelector("input#userEmail");
//	By passWord = By.id("userPassword");
//	By loginBtn = By.id("login");

//LoginPage Methods
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	public ProductsPage doLogin(String usrnm,String pswd) {
		userName.sendKeys(usrnm);
		password.sendKeys(pswd);
		loginBtn.click();
		return new ProductsPage(driver);
	}
	
	public String getLoginErrorMessage() {
		waitForWebElementToAppear(invalidEmailPswd);
		return invalidEmailPswd.getText();
	}

}
