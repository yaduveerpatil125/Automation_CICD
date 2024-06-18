package cucumber.selenium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src\\test\\java\\cucumber\\selenium\\PurchaseOrder.feature",

		glue="stepDefinitions",
		monochrome = true,
		tags="@purchaseOrder",
		plugin= {"html:target/cucumber.html"}
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
