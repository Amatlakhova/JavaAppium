package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            MY_SAVED_LIST,
            CLOSE_SYNC_SAVED_ARTICLE,
            ARTICLE_LIST_DESCRIPTION,
            SAVED_ARTICLE_ICON,
            REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click article to remove from saved",
                    10);
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void closePopUpSyncSavedArticle() {
        this.waitForElementAndClick(
                CLOSE_SYNC_SAVED_ARTICLE,
                "Cannot close pop-up to sync saved articles list",
                5
        );
    }

    public void goToMyLists()
    {
        this.waitForElementAndClick(
                MY_SAVED_LIST,
                "Cannot find navigation button to My List",
                5
        );
    }

    public void waitForArticleDescriptionPresent() {
        this.waitForElementPresent(
                ARTICLE_LIST_DESCRIPTION,
                "Cannot find description of article in My Lists",
                5
        );
    }

    public void verifyArticleSavedInMyListByIcon()
    {
        this.waitForElementPresent(
                SAVED_ARTICLE_ICON,
                "Cannot find saved article in my list by icon",
                5
        );
    }

}
