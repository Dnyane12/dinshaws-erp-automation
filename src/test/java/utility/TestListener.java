package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.DriverProvider;
import utils.ExtentReportManager;



//import ;  // Assuming BaseTest contains WebDriver reference

public class TestListener implements ITestListener {

	public ExtentReports extent = ExtentReportManager.getInstance();
	public ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
    	System.out.println("======= LISTENER TRIGGERED =======");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed");
        test.get().log(Status.FAIL, result.getThrowable());


        Object instance = result.getInstance();
        if (instance instanceof DriverProvider) {
            WebDriver driver = ((DriverProvider) instance).getDriver();
            if (driver != null) {
                String screenshotPath = captureScreenshot(
                    result.getMethod().getMethodName(), driver);
                try {
                    test.get().addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Utility method to capture screenshot
    public String captureScreenshot(String methodName, WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
        String filePath = screenshotDir + methodName + "_" + timestamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File destFile = new File(filePath);
            destFile.getParentFile().mkdirs();
            FileHandler.copy(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}

