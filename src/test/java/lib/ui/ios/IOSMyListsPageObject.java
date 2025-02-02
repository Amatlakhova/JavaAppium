package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        MY_SAVED_LIST = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        CLOSE_SYNC_SAVED_ARTICLE = "xpath://XCUIElementTypeButton[@name='Close']";
        ARTICLE_LIST_DESCRIPTION = "id:Set of several computer software products and specifications";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
