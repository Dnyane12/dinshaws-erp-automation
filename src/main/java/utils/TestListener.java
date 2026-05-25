package utils;

import com.aventstack.extentreports.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestListener implements ITestListener {
	private final ExtentReports extent = ExtentReportManager.getInstance();
	private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		String className = result.getTestClass().getRealClass().getSimpleName();
		String methodName = result.getMethod().getMethodName();
		String testName = className + " :: " + methodName;
		ExtentTest extentTest = extent.createTest(testName);
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
		Object instance = result.getInstance();	

		if (instance instanceof DriverProvider) {
			WebDriver driver = ((DriverProvider) instance).getDriver();
			if (driver != null) {
				// Extent Screenshot
				String screenshotPath = ScreenshotUtility.captureScreenshot(result.getMethod().getMethodName(), driver);
				try {
					test.get().addScreenCaptureFromPath(screenshotPath);
					System.out.println("FAILURE LISTENER EXECUTED");
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Allure Screenshot
				AllureUtils.attachScreenshot(driver);
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}