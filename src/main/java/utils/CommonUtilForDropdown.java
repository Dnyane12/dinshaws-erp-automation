package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtilForDropdown {
	private static By SPINNER = By.xpath("//your-spinner-xpath");

    public static void selectFromIgxDropdown(WebDriver driver,
                                             WebElement dropdownElement,
                                             String value) {

        WaitHelper.waitForClickable(driver, dropdownElement, 10);
        dropdownElement.click();

        dropdownElement.sendKeys(value);

        String xpath = "//igx-combo-item//span[contains(normalize-space(text()),'" + value + "')]";

      //span[contains(@class,'igx-drop-down__inner')]//span[contains(normalize-space(text()),'" + value + "')]
        WebElement option = driver.findElement(By.xpath(xpath));

        WaitHelper.waitForClickable(driver, option, 10);
        option.click();
    }
}
