package pajeobject;

import constants.Url;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends Url {

    public RegistrationPage(WebDriver driver) {
        super(driver);
        currentUrl = BASE_URL + REGISTER;
    }

    //поле ввода Имени
    @FindBy(xpath = ".//fieldset[1]//input")
    private WebElement inputRegisterName;
    //поле ввода Email
    @FindBy(xpath = ".//fieldset[2]//input")
    private WebElement inputRegisterEmail;
    //поле ввода Пароля
    @FindBy(xpath = ".//fieldset[3]//input")
    private WebElement inputRegisterPassword;
    //кнопка Зарегестрироваться
    @FindBy(xpath = ".//button[text() = 'Зарегистрироваться']")
    private WebElement buttonRegistry;
    //текст ошибки "Некорректный пароль"
    @FindBy(xpath = ".//p[text()='Некорректный пароль']")
    private WebElement messageIncorrectPasswordError;
    //кнопка Войти
    @FindBy(xpath = ".//a[text() = 'Войти']")
    private WebElement signIn;

    //метод для ожидания отображение ошибки для пароля
    public void waitForIncorrectPasswordError() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(messageIncorrectPasswordError));
    }

    @Step("Нажать на кнопку Войти")
    public void clickSignIn() {
        signIn.click();

    }

    @Step("Ввести имя")
    public void setNameInput(String name) {
        inputRegisterName.sendKeys(name);
    }

    @Step("Ввести Email")
    public void setEmailInput(String email) {
        inputRegisterEmail.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPasswordInput(String password) {
        inputRegisterPassword.sendKeys(password);
    }

    @Step("Клик на кнопку Зарегистрироваться")
    public void clickSignUpButton() {
        buttonRegistry.click();
    }

    @Step("Получить текст ошибки Некорректный пароль")
    public String getIncorrectPasswordErrorText() {
        return messageIncorrectPasswordError.getText();
    }

    @Step("Отображение информации об ошибке Некорректный пароль")
    public boolean isVisibleIncorrectPasswordErrorText() {
         return messageIncorrectPasswordError.isDisplayed();
    }

    @Step("Отображение кнопки Войти")
    public boolean isVisibleSigInButton(){
        return signIn.isDisplayed();
    }

    @Step()
    public void waitSigInButton (){
        new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.visibilityOf(signIn));
}
}
