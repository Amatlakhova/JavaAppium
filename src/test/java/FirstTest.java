import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testVerifyElementHasText()
    {
        MainPageObject.waitForElementAndClick(
                "xpath://*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia' input",
                5
        );
        MainPageObject.assertElementHasText(
                "id:org.wikipedia:id/search_src_text",
                "Search…",
                "Search field doesn't match 'Search…' text"
        );
    }

    @Test
    public void testSearchArticles()
    {
        MainPageObject.waitForElementAndClick(
                "xpath://*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                "xpath://*[contains(@text,'Search…')]",
                "Cyprus",
                "Cannot find 'Cyprus' input",
                5
        );
        MainPageObject.waitForElementPresent(
                "id:org.wikipedia:id/page_list_item_title",
                "Articles are not founded",
                5
        );
        MainPageObject.waitForElementAndClick(
                "id:org.wikipedia:id/search_close_btn",
                "Cannot find X to cancel search",
                5
        );
        MainPageObject.waitForElementNotPresent(
                "id:org.wikipedia:id/page_list_item_title",
                "Articles are still present on the page",
                5
        );
    }

    @Test
    public void testCheckWordsInSearch()
    {
        MainPageObject.waitForElementAndClick(
                "xpath://*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                "xpath://*[contains(@text,'Search…')]",
                "Java",
                "Cannot find 'Java' input",
                5
        );
        MainPageObject.assertElementsContainText(
                "id:org.wikipedia:id/page_list_item_title",
                "Java",
                "Not all articles contain 'Java'"
        );

    }
}
