package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Centralized waiting and action helper used across tests/pages.
 * Provides safe click/send methods that handle stale elements and click interception.
 */
public class WaitHelper {

    private WaitHelper() { /* prevent instantiation */ }

    // ------------------- WebElement-based waits -------------------
    public static void waitForVisible(WebDriver driver, WebElement element, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickable(WebDriver driver, WebElement element, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForVisibilityOfAllElements(WebDriver driver, List<WebElement> elements, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    // ------------------- By-based waits -------------------
    public static void waitForVisible(WebDriver driver, By locator, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForPresenceOfElementLocated(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static boolean waitForTextToBePresentInElement(WebDriver driver, WebElement textTocheck,String textToWaitFor,int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.textToBePresentInElement(textTocheck, textToWaitFor));
    }
  
    public static void waitForVisibilityOfAllElements(WebDriver driver, By locator, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static boolean isElementVisible(WebDriver driver, By locator, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitForInvisibilityOfElementLocated(WebDriver driver, By locator, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ------------------- Robust click helpers -------------------
    /**
     * Waits for an element to be clickable (refreshed), then attempts to click it.
     * Retries on stale/timeout and falls back to Actions/JS click when click is intercepted.
     */
    public static void waitForRefreshAndClick(WebDriver driver, By locator, int seconds) {
        int attempts = 0;
        final int maxAttempts = 3;
        while (attempts < maxAttempts) {
            attempts++;
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
                // refreshed ensures we get a fresh reference for the element
                WebElement element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));

                // scroll into view relative to viewport center
                scrollIntoView(driver, element);

                try {
                    element.click();
                    return; // success
                } catch (ElementClickInterceptedException intercepted) {
                    // try Actions click
                    try {
                        new Actions(driver).moveToElement(element).pause(Duration.ofMillis(150)).click().build().perform();
                        return;
                    } catch (Exception ex) {
                        // final fallback: JS click
                        try {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                            return;
                        } catch (Exception jsEx) {
                            // fall through to retry logic
                        }
                    }
                }

            } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
                // element changed or not present yet - retry
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not clickable after retries: " + locator, e);
                }
                sleep(200);
            } catch (Exception e) {
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Failed to click element: " + locator, e);
                }
                sleep(200);
            }
        }
    }

    
    public static void waitForRefreshAndClick1(WebDriver driver, By locator, int timeoutInSeconds) {
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            // Wait until fresh clickable element is available
            WebElement element = wait.until(
                    ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable(locator)));

            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", element);

            // Click element
            element.click();

        } catch (ElementClickInterceptedException e) {
            // Fallback to JS click
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        } catch (TimeoutException e) {
            throw new RuntimeException("Element not clickable within timeout: " + locator);

        } catch (Exception e) {
            throw new RuntimeException("Unable to click element: " + locator, e);
        }
    }
    
    
    
    
    /**
     * Safe click using a By locator. Will wait for clickable and use the robust click strategy.
     */
    public static void safeClick(WebDriver driver, By locator, int timeoutInSeconds) {
        waitForRefreshAndClick(driver, locator, timeoutInSeconds);
    }

    /**
     * Convenience overload used by older code: safeClick without explicit timeout.
     */
    public static void safeClick(WebDriver driver, WebElement element) {
        safeClick(driver, element, 10);
    }

    /**
     * Safe click on a WebElement: waits for visible & clickable, scrolls into view then clicks (JS fallback)
     */
    public static void safeClick(WebDriver driver, WebElement element, int timeoutInSeconds) {
        int attempts = 0;
        final int maxAttempts = 3;
        while (attempts < maxAttempts) {
            attempts++;
            try {
                scrollIntoView(driver, element);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                try {
                    element.click();
                    return;
                } catch (ElementClickInterceptedException intercepted) {
                    try {
                        new Actions(driver).moveToElement(element).pause(Duration.ofMillis(150)).click().build().perform();
                        return;
                    } catch (Exception ex) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                        return;
                    }
                }

            } catch (StaleElementReferenceException | TimeoutException e) {
                if (attempts >= maxAttempts) throw new RuntimeException("Failed to safeClick element after retries", e);
                sleep(200);
            } catch (Exception e) {
                if (attempts >= maxAttempts) throw new RuntimeException("Failed to safeClick element", e);
                sleep(200);
            }
        }
    }

    // ------------------- Other helpers -------------------
    public static void safeSendKeys(WebDriver driver, By locator, String text, int seconds) {
        waitForClickable(driver, locator, seconds);
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(text);
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
        } catch (Exception ignored) {
            // ignore scroll failures
        }
    }

    public static void waitForNumberOfWindowsToBe(WebDriver driver, int numberOfWindows, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
    }

    public static void selectDropDownOption(WebDriver driver, String dropOption, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement dropOptField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(normalize-space(.),'" + dropOption + "')])[1]")));
        scrollIntoView(driver, dropOptField);
        dropOptField.click();
    }

    // small util
    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
    }
    
    
    public static void waitToDealWithCatche(WebDriver driver, String previousUrl) {
    	 // Wait for URL to actually change
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(previousUrl)));
    }
    
    public static void waitUntilEnabled(WebDriver driver, WebElement element, int sec) {
	    new WebDriverWait(driver, Duration.ofSeconds(sec))
	        .until(driver1 -> element.isEnabled());
	}
    
    public static void normalWait(WebDriver driver,int seconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    
    public static String waitForValidNumericValueAfterColon(WebDriver driver, WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(d -> {
            String text = element.getText().trim();
            System.out.println("Polling element text: " + text);

            if (text.contains(":")) {
                String afterColon = text.substring(text.indexOf(":") + 1).trim();
                String cleaned = afterColon.replaceAll("[^\\d.]", "");

                if (!cleaned.isEmpty()) {
                    try {
                        double value = Double.parseDouble(cleaned);
                        if (value > 0) {
                            System.out.println("Valid numeric value found: " + afterColon);
                            return afterColon;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Not yet a valid number: " + cleaned);
                    }
                }
            }
            return null; // keeps polling
        });
    }
}