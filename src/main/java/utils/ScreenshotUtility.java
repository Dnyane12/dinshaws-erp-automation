package utils;

import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {
	private ScreenshotUtility() {
	}

	public static String captureScreenshot(String testName, WebDriver driver) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotDir = ReportManager.getScreenshotFolderPath() + "/";
		String filePath = screenshotDir + testName + "_" + timestamp + ".png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			File destFile = new File(filePath);
			destFile.getParentFile().mkdirs();
			Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
}