package OR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleHomeWebPage {

    public static String GetHomeUrl() {
        return "http://www.google.com";
    }

    @FindBy(how = How.NAME, using = "q")
    WebElement searchBox;

    @FindBy(how = How.ID, using = "logo")
    WebElement searchHomeLogo;

    public void Search(String text) {
        searchBox.sendKeys(text);
        searchBox.submit();
    }

    public void WaitForSearchHomeLogo() {
        ExpectedConditions.visibilityOf(searchHomeLogo);
    }
}
