package pajeobject;

import constants.Url;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends Url {

    public MainPage(WebDriver driver) {
        super(driver);

        currentUrl = BASE_URL;
    }

    // кнопка "Войти"
    @FindBy(xpath = ".//button[text()='Войти в аккаунт']")
    private WebElement signInButton;
    //кнопка Оформить заказ
    @FindBy(xpath = ".//button[text() = 'Оформить заказ']")
    private WebElement getOrderButton;

    // Личный кабинет
    @FindBy(xpath = ".//p[text() = 'Личный Кабинет']")
    private WebElement personalAccount;

    //текущий раздел
    @FindBy(xpath = ".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span")
    private WebElement currentTab;

    //раздел Булки
    @FindBy(xpath = ".//div/span[text()='Булки']")
    private WebElement bunsTab;

    //раздел Соусы
    @FindBy(xpath = ".//div/span[text()='Соусы']")
    private WebElement saucesTab;

    //раздел Начинки
    @FindBy(xpath = ".//div/span[text()='Начинки']")
    private WebElement toppingTab;

    //логотип Космо-Бургерной
    @FindBy(xpath = ".//div[@class = 'AppHeader_header__logo__2D0X2']")
    private WebElement logo;
    //раздел Лента Заказов
    @FindBy(xpath = ".//p[text() = 'Лента Заказов']")
    private WebElement orderFeed;
    //раздел Конструктор
    @FindBy(xpath = ".//p[text() = 'Конструктор']")
    private WebElement constructor;
    //надпись Соберите бургер
    @FindBy(xpath = ".//h1[text() = 'Соберите бургер']")
    private WebElement makeBurger;


    //шаги
    @Step("Клик на кнопку Войти в аккаунт")
    public void clickSignInButton() {
        signInButton.click();
    }

    public String getSignInButton(){
        return signInButton.getText();
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickPersonalAccount() {
        personalAccount.click();
    }

    @Step("Получить название активного раздела ингредиентов")
    public String getCurrentTabText() {
        return currentTab.getText();
    }

    @Step("Клик на раздел Булки")
    public void clickBunsTab() {
        bunsTab.click();
    }

    @Step("Клик на раздел Лента заказов")
    public void clickOrderFeed() {
        orderFeed.click();
    }

    @Step("Клик на раздел Конструктор")
    public void clickConstructor() {
        constructor.click();
    }

    @Step("Клик на раздел Соусы")
    public void clickSaucesTab() {
        saucesTab.click();
    }

    @Step("Клик на раздел Начинки")
    public void clickToppingsTab() {
        toppingTab.click();
    }

    @Step("Чек, что кнопка Оформить заказ отображается")
    public boolean isDisplayedGetOrderButton() {
        return getOrderButton.isDisplayed();
    }

    public void waitButtonForMakeOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(getOrderButton));
    }

    @Step("Клик на лого stellarburgers")
    public void clickStellarBurgersLogo() {
        logo.click();
    }

    @Step("Получить текст Соберите бургер")
    public String getTextMakeBurger() {
        return makeBurger.getText();
    }

    public void waitForDisplayMakeBurgerText() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(makeBurger));
    }
}
