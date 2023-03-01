package pajeobject;

import constants.Url;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends Url {

    public AccountPage(WebDriver driver) {
        super(driver);
        currentUrl = BASE_URL + ACCOUNT;
    }

    //текст Конструктор
    @FindBy(xpath = ".//p[text() = 'Конструктор']")
    private WebElement constructorButton;

    //текст Профиль
    @FindBy(xpath = ".//a[text() = 'Профиль']")
    private WebElement profile;

    //кнопка Выход
    @FindBy(xpath = ".//button[text()='Выход']")
    private WebElement logOutButton;

    //логотип Космо-Бургерной
    @FindBy(xpath = ".//div[@class = 'AppHeader_header__logo__2D0X2']")
    private WebElement logo;

    //шаги
    @Step("Нажать на кнопку Конструктор из Личного кабинета")
    public void clickConstructor() {
        constructorButton.click();
    }

    @Step("Клик на кнопку Выход")
    public void clickLogOutButton() {
        logOutButton.click();
    }

    public String  getTextLogout(){
        return logOutButton.getText();
    }

    @Step("Кликнуть на 'Профиль'")
    public void clickOnProfile() {
        profile.click();
    }

    @Step("Получить текст 'Профиль в личном кабинете'")
    public String getProfileText() {
        return profile.getText();
    }

    @Step("Нажать на логотип находясь на странице Личного кабинета")
    public void clickOnLogo() {
        logo.click();
    }

    public void waitForDisplayProfileText() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(profile));
    }
}