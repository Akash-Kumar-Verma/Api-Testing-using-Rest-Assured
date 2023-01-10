package RestFramework.Utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extend;

	public static ExtentReports getInstance(String filename) {
		if (extend == null) {

			extend = new ExtentReports(filename, true, DisplayOrder.NEWEST_FIRST);

			extend.loadConfig(new File(System.getProperty("user.dir") + "//reportConfig.xml"));

			extend.addSystemInfo("RestAssured Version", "4.4.0").addSystemInfo("Environment", "QA");

		}
		return extend;

	}

}

