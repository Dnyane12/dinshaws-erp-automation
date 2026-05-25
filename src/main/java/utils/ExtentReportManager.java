package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    public static ExtentReports extent;
    public static ExtentReports getInstance() {
        if (extent == null) {
            String path = ReportManager.getReportFolderPath()+ "/ExtentReport.html";
            ExtentSparkReporter spark =new ExtentSparkReporter(path);

            spark.config().setReportName("Automation Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Dnyaneshwari");
            extent.setSystemInfo("Framework", "Hybrid Framework");
        }

        return extent;
    }
}