package selenium.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import selenium.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentTest test;
	String filepath;
	ExtentReports extent = ExtentReporterNG.getReportObject();//since the method is static
	//we are directly accessing it using it's className
	
	ThreadLocal<ExtentTest> threadTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());//gives us method name being executed
		threadTest.set(test);//with this a unique thread id will be assiged to 'test'.
		//So, when tests are run in parallel, there won't be any sync issues.
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threadTest.get().log(Status.PASS, result.getMethod().getMethodName()+" is success Sharath");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threadTest.get().fail(result.getThrowable());//since the execution reached this block
		//we are failing the test and printing the failure logs
		
		//take screenshot and attach it to the report
		try {
			 filepath = getScreenshot(result.getMethod().getMethodName(),BaseTest.driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//attaching screenshot with the failed method name to report 
		threadTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();//this step is mandatory to for the extent reports to generate
	}
	
	
	

}
