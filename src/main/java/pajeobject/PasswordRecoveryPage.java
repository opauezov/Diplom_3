package pajeobject;

import constants.Url;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PasswordRecoveryPage extends Url {

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
        currentUrl = BASE_URL + FORGOT_PASSWORD;
    }

    @FindBy(xpath = ".//button[text()='Восстановить']")
    private WebElement buttonForUpdateButton;
    @FindBy(xpath = ".//a[@class = 'Auth_link__1fOlj']")
    private WebElement signIn;

    @Step("Нажать на кнопку 'Войти'")
    public void signIn() {
        signIn.click();
    }
}
