package Test.setUpTests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.DriverProvider;
import utils.PropertyReader;

public class SetUp implements DriverProvider {

    public static WebDriver driver;
    public static PropertyReader prop;

    private static final Logger logger = LogManager.getLogger(SetUp.class);

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void basicSetting() {

        logger.info("Starting Test Execution");

        prop = new PropertyReader("SetUp/application.properties");

        String browser = prop.getProperty("browser").trim().toLowerCase();
        String url = prop.getProperty("url").trim();

        driver = launchBrowser(browser);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        driver.get(url);

        logger.info("Application opened successfully");
    }

    public WebDriver launchBrowser(String browser) {

        switch (browser) {

        case "chrome":
            WebDriverManager.chromedriver().setup();
            logger.info("Launching Chrome Browser");
            return new ChromeDriver();

        case "firefox":
            WebDriverManager.firefoxdriver().setup();
            logger.info("Launching Firefox Browser");
            return new FirefoxDriver();

        case "edge":
            WebDriverManager.edgedriver().setup();
            logger.info("Launching Edge Browser");
            return new EdgeDriver();

        default:
            logger.error("Invalid Browser Name: " + browser);
            throw new RuntimeException("Browser not supported");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            logger.info("Closing Browser");
            driver.quit();
        }
    }
}