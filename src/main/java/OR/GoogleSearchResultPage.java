package OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GoogleSearchResultPage {
    @FindBy(how = How.NAME, using = "q")
    WebElement searchBox;

    @FindBy(className = "g")
    List<WebElement> searchResults;

    public int getSearchItemsCount() {
        System.out.println("Size of items: " + searchResults.size());
        return searchResults.size();
    }

}
