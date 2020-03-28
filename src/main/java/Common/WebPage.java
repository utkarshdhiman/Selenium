package Common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebPage {
    static WebDriver driver;

    static WebDriver getSeleniumDriver() {
        WebDriverManager.chromedriver().setup();
        if (driver == null)
            driver = new ChromeDriver();
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
    }

    public static void WaitForElementPresent(By by) {
        WaitForElementPresent(by, 15);
    }

    public static void WaitForElementPresent(By by, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
