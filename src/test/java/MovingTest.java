import api_steps.UserSteps;
import constants.UserDataApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pajeobject.AccountPage;
import pajeobject.LoginPage;
import pajeobject.MainPage;
import pojos.SignInRequest;
import pojos.SuccessSignInSignUpResponse;
import pojos.UserRequest;

public class MovingTest extends BaseTest {

    LoginPage loginPage;
    MainPage mainPage;
    AccountPage accountPage;
    UserRequest testUser;
    String accessToken;
    SignInRequest signInRequest;

    @Before
    public void startTest() {
        testUser = UserDataApi.getUniqueUser();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        SuccessSignInSignUpResponse signUpResponse = UserSteps.createUniqueUser(testUser)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignInSignUpResponse.class);
        accessToken = signUpResponse.getAccessToken();
        signInRequest = new SignInRequest(testUser.getEmail(), testUser.getPassword());
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        driver.get(mainPage.getCurrentUrl());
        mainPage.clickSignInButton();
        loginPage = new LoginPage(driver);
        PageFactory.initElements(driver, loginPage);
        loginPage.loginWithCredentials(signInRequest);
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        mainPage.clickPersonalAccount();
        accountPage = new AccountPage(driver);
        PageFactory.initElements(driver, accountPage);
        accountPage.waitForDisplayProfileText();
    }

    @After
    public void end() {
        UserSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Переход в личный кабинет с главной страницы")
    public void goToProfileFromMainPage() {
        Assert.assertEquals("Профиль", accountPage.getProfileText());
    }

    @Test
    @DisplayName("Переход на главную страницу нажав на Конструтор")
    public void goToMainPageFromProfileWithConstructor() {
        accountPage.clickConstructor();
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        mainPage.waitForDisplayMakeBurgerText();
        Assert.assertEquals("Соберите бургер", mainPage.getTextMakeBurger());
    }

    @Test
    @DisplayName("Переход на главную страницу из личного кабинета при нажатие на логотип")
    public void goToMainPageFromProfile() {
        accountPage.waitForDisplayProfileText();
        accountPage.clickOnLogo();
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        mainPage.waitForDisplayMakeBurgerText();
        Assert.assertEquals("Соберите бургер", mainPage.getTextMakeBurger());
    }

    @Test
    @DisplayName("Выход из личного кабинет")
    public void exitFromProfile() {
        accountPage.waitForDisplayProfileText();
        accountPage.clickLogOutButton();
        Assert.assertEquals("Выход", accountPage.getTextLogout());
        accountPage.clickOnLogo();
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, mainPage);
        Assert.assertEquals("Войти в аккаунт", mainPage.getSignInButton());
    }
}
