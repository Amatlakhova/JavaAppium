package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private final static String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else {
            MyListsPageObject.closePopUpSyncSavedArticle();
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testDeleteArticleFromMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Set of several computer software products and specifications");

        ArticlePageObject.waitForTitleElement("Java (software platform)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addSecondArticleToMyList();
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.goToMyLists();
            MyListsPageObject.openFolderByName(name_of_folder);
        } else {
            MyListsPageObject.closePopUpSyncSavedArticle();
        }

        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
        MyListsPageObject.waitForArticleDescriptionPresent();
    }

}
