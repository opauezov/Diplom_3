package pajeobject;

import constants.Url;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pojos.SignInRequest;

import java.time.Duration;

public class LoginPage extends Url {

    public LoginPage(WebDriver driver) {
        super(driver);
        String currentUrl = BASE_URL + LOGIN;
    }

    //поле ввода Email
    @FindBy(xpath = ".//input[@class='text input__textfield text_type_main-default']")
    private WebElement inputEmail;
    //поле ввода Пароля
    @FindBy(xpath = ".//input[@type = 'password']")
    private WebElement inputPassword;
    //кнопка Войти
    @FindBy(xpath = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private WebElement buttonEnter;

    //шаги для WebElements
    @Step("Ввести почту")
    public void setEmailForLogin(String email) {
        inputEmail.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPasswordForLogin(String password) {
        inputPassword.sendKeys(password);
    }

    @Step("Клик на кнопку Войти")
    public void clickEnter() {
        buttonEnter.click();
    }

    @Step("Получить текст кнопки Войти")
    public String getTextEnter() {
        return buttonEnter.getText();
    }
    public void waitEnterButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(buttonEnter));
    }

    public void loginWithCredentials(SignInRequest signInRequest) {
        setEmailForLogin(signInRequest.getEmail());
        setPasswordForLogin(signInRequest.getPassword());
        clickEnter();
    }
}
