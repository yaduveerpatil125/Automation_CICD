package selenium.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pages.testpages.LoginPage;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	public LoginPage loginpage;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		// FileInputStream fis = new
		// FileInputStream(System.getProperty("user.dir")+"/src/main/java/selenium/resources");
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\yaduv\\eclipse-workspace\\sample\\SeleniumFramework\\src\\main\\java\\selenium\\resources\\globalData.properties");
		prop.load(fis);

		// the below command checks mvn system property "browser". If it is null then it
		// reads browser
		// value from property file.
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();//to run tests in headless mode we need this
			if (browser.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));//helps to run in fullScreen

		} else if (browser.contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			if (browser.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else {
			System.out.println("Select a valid browser for test execution!");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeDriver();
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	// utility to read data from json file
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// read the json file as String input
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert String to Hashmap using Jackson databind

		ObjectMapper mapper = new ObjectMapper();

		// the below command gives us List of hashMaps
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	// utility for taking a screenshot and returns the path of the screen shot saved
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File screenShot = ts.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(screenShot, DestFile);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		// here we are returning the path where the screenshot is stored
	}

	// Extent reports utility

}
