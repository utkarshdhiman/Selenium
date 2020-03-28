import Common.WebPage;
import OR.GoogleHomeWebPage;
import OR.GoogleSearchResultPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class GoogleHomeWebPageTest {

    @Test
    public void TestGoogleSearch() {
        SoftAssertions softAssertions = new SoftAssertions();

        //Get the Home page instance
        GoogleHomeWebPage homeWebPage = WebPage.GetWebPage(GoogleHomeWebPage.GetHomeUrl(), GoogleHomeWebPage.class);

        //Perform the search and wait for results
        homeWebPage.Search("Test");
        homeWebPage.WaitForSearchHomeLogo();

        //Get the Search Results page
        GoogleSearchResultPage resultPage = WebPage.GetWebPage(GoogleSearchResultPage.class);

        //Verify the results count
        softAssertions.assertThat(resultPage.getSearchItemsCount()).isGreaterThan(0);

        //Clean up
        softAssertions.assertAll();
        WebPage.Close();
    }
}