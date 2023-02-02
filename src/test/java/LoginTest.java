import api_steps.UserSteps;
import constants.UserDataApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pajeobject.LoginPage;
import pajeobject.MainPage;
import pajeobject.PasswordRecoveryPage;
import pajeobject.RegistrationPage;
import pojos.SignInRequest;
import pojos.SuccessSignInSignUpResponse;
import pojos.UserRequest;


public class LoginTest extends BaseTest {

    MainPage mainPage;
    RegistrationPage registrationPage;

    PasswordRecoveryPage passwordRecoveryPage;
    LoginPage loginPage;
    String accessToken;
    UserRequest testUser;
    SignInRequest signInRequest;

    @Before
    public void start() {
        testUser = UserDataApi.getUniqueUser();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        SuccessSignInSignUpResponse signUpResponse = UserSteps.createUniqueUser(testUser)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignInSignUpResponse.class);
        accessToken = signUpResponse.getAccessToken();
        signInRequest = new SignInRequest(testUser.getEmail(), testUser.getPassword());

    }

    @After
    public void end() {
        loginPage = new LoginPage(driver);
        PageFactory.initElements(driver, loginPage);
        loginPage.loginWithCredentials(signInRequest);
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        mainPage.waitButtonForMakeOrder();
        Assert.assertTrue(mainPage.isDisplayedGetOrderButton());
        UserSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вход в приложение по кнопке «Войти в аккаунт» на главной странице")
    public void clickLogInOnMainPageUserLoggedIn() {
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        driver.get(mainPage.getCurrentUrl());
        mainPage.clickSignInButton();
    }

    @Test
    @DisplayName("Вход в приложение через кнопку «Личный кабинет»  на главной странице")
    public void clickPersonalAccountButtonOnMainPageUserLoggedIn() {
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        driver.get(mainPage.getCurrentUrl());
        mainPage.clickPersonalAccount();
    }

    @Test
    @DisplayName("Вход в приложение через кнопку Войти (Уже зарегестрированы?) на форме регистрации")
    public void clickSignInLinkOnRegistrationPageUserLoggedIn() {
        registrationPage = new RegistrationPage(driver);
        PageFactory.initElements(driver, registrationPage);
        driver.get(registrationPage.getCurrentUrl());
        registrationPage.clickSignIn();
    }

    @Test
    @DisplayName("Вход в приложение через кнопку Войти (Вспомнили пароль?) на странице восстановления пароля")
    public void clickSignInLinkOnPasswordRecoveryPageUserLoggedIn() {
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        PageFactory.initElements(driver, passwordRecoveryPage);
        driver.get(passwordRecoveryPage.getCurrentUrl());
        passwordRecoveryPage.signIn();
    }
}
