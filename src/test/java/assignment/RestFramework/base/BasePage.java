package assignment.RestFramework.base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import RestFramework.Utilities.ExtentManager;
import RestFramework.Utilities.ReadingPropertiesFile;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;


public class BasePage {
	protected static ReadingPropertiesFile readingPropertiesFile = new ReadingPropertiesFile();
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Logger logger;
	protected static RequestSpecification httpRequest;

	@BeforeSuite
	public void init() throws MalformedURLException {
		extent = ExtentManager.getInstance("reports/ExtentReport.html");

	}

	@BeforeTest
	public void loadLog4j() {
		String log4jPath = System.getProperty("user.dir") + "//log4j.properties";
		PropertyConfigurator.configure(log4jPath);
	}

	@BeforeMethod
	public void startTest(Method method) {
		httpRequest = RestAssured.given();

		test = extent.startTest(method.getName());
	}

	@AfterMethod
	public void reportFlush(ITestResult result) {
		System.out.println(result.getMethod().getMethodName());
		if (result.getStatus() == ITestResult.FAILURE)
			test.log(LogStatus.FAIL, "Test Failed");
		else if (result.getStatus() == ITestResult.SKIP)
			test.log(LogStatus.SKIP, "Test Skiped");
		else
			test.log(LogStatus.PASS, "Test Passed.");

		extent.flush();
	}

}
