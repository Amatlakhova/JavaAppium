package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private final static String name_of_folder = "Learning programming";
    private final static String
            login = "amatlakhova",
            password = "petra121314wqe";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        }
        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }
        if (Platform.getInstance().isMW()) {
            ArticlePageObject.addArticleToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement("Java (programming language)");

            assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle("Java (programming language)")
            );
            ArticlePageObject.addArticleToMySaved();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
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
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        }
        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }
        if (Platform.getInstance().isMW()) {
            ArticlePageObject.addArticleToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement("Java (programming language)");
            ArticlePageObject.addArticleToMySaved();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("et of several computer software products and specifications");

        ArticlePageObject.waitForTitleElement("Java (software platform)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addSecondArticleToMyList();
            ArticlePageObject.closeArticle();
        }
        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }
        if (Platform.getInstance().isMW()) {
            ArticlePageObject.addArticleToMySaved();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.goToMyLists();
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closePopUpSyncSavedArticle();
        }

        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS())) {
            MyListsPageObject.waitForArticleDescriptionPresent();
        } else {
            MyListsPageObject.verifyArticleSavedInMyListByIcon();
        }
    }

}
