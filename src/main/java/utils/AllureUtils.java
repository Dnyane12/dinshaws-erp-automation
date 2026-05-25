package utils;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;

public class AllureUtils {
	private AllureUtils() {

	}

	@Attachment(value = "Failed Test Screenshot", type = "image/png")
	public static byte[] attachScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}