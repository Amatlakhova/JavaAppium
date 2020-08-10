package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "css:#content h1";
        TITLE_TPL = "id:{TITLE}";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#ca-watch:not(.watched)";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#ca-watch.watched";
        MY_EXISTING_READING_LIST = "id:Saved";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
