package api.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;  //UI of the report
	public ExtentReports extent;  // populate common info of the report
    public ExtentTest test; // creating test cases entries in the report and update status of the test methods

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports and attach the SparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("use.dir")+"reports/myReport.html");//specify the location of the report
        sparkReporter.config().setDocumentTitle("Automation Report"); //Title of the report
        sparkReporter.config().setReportName("Functional Testing"); //Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Tester");
        extent.setSystemInfo("os","Windows10");
        extent.setSystemInfo("Browser name", "chrome");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log the test as passed
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log the test as failed
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // Optionally, take a screenshot and attach it to the report
        // String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
        // test.addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log the test as skipped
        test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the report at the end
        extent.flush();
    }

    // Optional: Add other methods for different statuses
}
