import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pajeobject.MainPage;

public class MainPageTest extends BaseTest {
    MainPage mainPage;

    @Before
    public void start() {
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        driver.get(mainPage.getCurrentUrl());
    }

    @Test
    @DisplayName("Открыть раздел Булки")
    public void switchToBunsTabTabSwitched() {
        mainPage.clickOrderFeed();
        mainPage.clickConstructor();
        mainPage.clickToppingsTab();
        mainPage.clickBunsTab();
        Assert.assertEquals("Булки", mainPage.getCurrentTabText());
    }

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    public void switchToSaucesTabTabSwitched() {
        mainPage.clickOrderFeed();
        mainPage.clickConstructor();
        mainPage.clickToppingsTab();
        mainPage.clickSaucesTab();
        Assert.assertEquals("Соусы", mainPage.getCurrentTabText());
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    public void switchToToppingsTabTabSwitched() {
        mainPage.clickOrderFeed();
        mainPage.clickConstructor();
        mainPage.clickSaucesTab();
        mainPage.clickToppingsTab();
        Assert.assertEquals("Начинки", mainPage.getCurrentTabText());
    }

}
