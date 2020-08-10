package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'watched')]";
        MY_SAVED_LIST = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        CLOSE_SYNC_SAVED_ARTICLE = "xpath://XCUIElementTypeButton[@name='Close']";
        ARTICLE_LIST_DESCRIPTION = "id:Set of several computer software products and specifications";
        SAVED_ARTICLE_ICON = "css:#ca-watch.watched";
    }

    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
