package assignment.RestFramework.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import RestFramework.Utilities.ReadingPropertiesFile;
import RestFramework.Utilities.Resources;
import RestFramework.Utilities.RestClientWrapper;
import RestFramework.Utilities.Utils;
import assignment.RestFramework.base.BasePage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Lebels extends BasePage {
	private static RestClientWrapper restClient;
	private static String baseUrl;
	public static String id = "";
	

	public Lebels() {
		logger = Logger.getLogger(Board.class.getName());
	}

	@BeforeClass
	public void SetupTest() throws Exception {
		baseUrl = ReadingPropertiesFile.getProperty("baseurl");
		//test.log(LogStatus.INFO, "Setup Done.");
		logger.info("SetupTest Method is Excuted.");
	}

	@BeforeMethod
	public void setUpRequest() {
		restClient = new RestClientWrapper(baseUrl, httpRequest);
		//test.log(LogStatus.INFO, "Request Completed.");
		logger.info("Request Method is Excuted.");
	}

	@Test(priority = 1)
	public void Create_a_Lebel() throws Exception {
		String bodyString = Utils
				.generateStringFromResource("//assignment//RestFramework//testData//LebelCreateData.json");
		System.out.println(bodyString);

		Response serverResponse = restClient.post(Resources.LebelpostEndPoint, bodyString);

		System.out.println();
		serverResponse.prettyPeek();

		// Fetching data form the response
		String s = serverResponse.asString();
		JsonPath js = new JsonPath(s);

		id = js.get("id");
		System.out.println("ID->" + id);

		// Assertions
		Assert.assertEquals(js.get("name"), "Green Lebels");
		Assert.assertEquals(js.get("color"), "green");
		Assert.assertEquals(serverResponse.statusCode(), 200);

		// for ExtentReports
		test.log(LogStatus.INFO, "Create Method is Excuted.");

		// logger via log4j
		logger.info("Create Method is Executed");
	}

	@Test(priority = 2)
	public void get_a_Lebel() throws Exception {

		Response serverResponse = restClient.get("/1/labels/" + id + Resources.EndPoints);

		System.out.println();
		serverResponse.prettyPeek();

		// Fetching data form the response
		String s = serverResponse.asString();
		JsonPath js = new JsonPath(s);

		id = js.get("id");
		System.out.println("ID->" + id);

		// Assertions
		Assert.assertEquals(js.get("name"), "Green Lebels");
		Assert.assertEquals(js.get("color"), "green");
		Assert.assertEquals(serverResponse.statusCode(), 200);

		// for ExtentReports
		test.log(LogStatus.INFO, "Get Method is Excuted.");

		// logger via log4j
		logger.info("Get Method is Executed");
	}

	@Test(priority = 3)
	public void update_a_Lebel() throws Exception {

		String bodyString = Utils
				.generateStringFromResource("//assignment//RestFramework//testData//LebelUpdateData.json");

		System.out.println(bodyString);

		Response serverResponse = restClient.put("/1/labels/" + id + Resources.EndPoints, bodyString);

		System.out.println();
		serverResponse.prettyPeek();

		// Fetching data form the response
		String s = serverResponse.asString();
		JsonPath js = new JsonPath(s);

		id = js.get("id");
		System.out.println("ID->" + id);

		// Assertions
		Assert.assertEquals(js.get("name"), "Red Lebels");
		Assert.assertEquals(js.get("color"), "red");
		Assert.assertEquals(serverResponse.statusCode(), 200);

		// for ExtentReports
		test.log(LogStatus.INFO, "Update Method is Excuted.");

		// logger via log4j
		logger.info("Update Method is Executed");
	}

	@Test(priority = 4)
	public void delete_a_Lebel() throws Exception {

		Response serverResponse = restClient.delete("/1/labels/" + id + Resources.EndPoints);

		System.out.println();
		serverResponse.prettyPeek();

		// for ExtentReports
		test.log(LogStatus.INFO, "Delete Method is Excuted.");

		// logger via log4j
		logger.info("Delete Method is Executed");

		// Assertions
		Assert.assertEquals(serverResponse.statusCode(), 200);
	}
	//This is for Negative Scenario
		@Test(priority = 5)
		public void negative_scenario() throws Exception {

			Response serverResponse = restClient.delete("/1/cards/"  + Resources.EndPoints);

			System.out.println();
			serverResponse.prettyPeek();

			// for ExtentReports
			test.log(LogStatus.INFO, "Negative Scenario is Executed");

			// logger via log4j
			logger.info("Negative scenario is Executed");

			// Assertions
			Assert.assertNotEquals(serverResponse.statusCode(), 200);
		}
	

}
