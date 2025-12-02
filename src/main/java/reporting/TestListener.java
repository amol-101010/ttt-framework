package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverUtil;

public class TestListener implements ITestListener {
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        if (extentReports == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extentReport/testng-extent-report.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(spark);
        }
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );
        testThread.set(test);
        testThread.get().assignCategory(result.getTestClass().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("Test passed");
        testThread.get().addScreenCaptureFromPath(DriverUtil.takeScreenshot());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());
        testThread.get().addScreenCaptureFromPath(DriverUtil.takeScreenshot());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("Test skipped");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
