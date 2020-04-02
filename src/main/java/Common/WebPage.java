package Common;

import Configration.ConfigLoad;
import Configration.DBConfigrations;
import Configration.DriverConfigration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebPage {
    static WebDriver driver;

    static WebDriver getSeleniumDriver() {
        if (driver == null) {
            DriverConfigration driverConfig = new ConfigLoad().getDriverConfig();
            switch (driverConfig.getDrivertype()) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case EDGE:
                    driver = new EdgeDriver();
                    break;
                case IE:
                    driver = new InternetExplorerDriver();
                    break;
                case SAFARI:
                    driver = new SafariDriver();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
            }
        }
        return driver;
    }

    public static <T> T GetWebPage(String url, Class<T> pageClassToProxy) {
        driver = getSeleniumDriver();
        if (StringUtils.isNotEmpty(url))
            driver.get(url);
        return PageFactory.initElements(driver, pageClassToProxy);
    }

    public static <T> T GetWebPage(Class<T> pageClassToProxy) {
        return GetWebPage(null, pageClassToProxy);
    }

    public static void Close() {

        driver.close();
        driver = null;
    }

    public static void WaitForElementPresent(By by) {
        WaitForElementPresent(by, 15);
    }

    public static void WaitForElementPresent(By by, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
