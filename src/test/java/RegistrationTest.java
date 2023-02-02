import api_steps.UserSteps;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pajeobject.RegistrationPage;
import pojos.SignInRequest;
import pojos.SuccessSignInSignUpResponse;

public class RegistrationTest extends BaseTest {
    String password;
    String name = RandomStringUtils.randomAlphanumeric(10);
    String email;
    RegistrationPage registrationPage;

    @Before
    public void start() {
        registrationPage = new RegistrationPage(driver);
        PageFactory.initElements(driver, registrationPage);
        driver.get(registrationPage.getCurrentUrl());

        email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
    }

    @Test
    @DisplayName("Создание валидного пользователя")
    public void createValidUserSuccessfullyCreated() {
        password = RandomStringUtils.randomAlphanumeric(10);
        boolean displayed = registrationPage.isVisibleSigInButton();

        registrationPage.setNameInput(name);
        registrationPage.setEmailInput(email);
        registrationPage.setPasswordInput(password);
        registrationPage.clickSignUpButton();
        registrationPage.waitSigInButton();
        Assert.assertTrue("Регистрация не была выполнена", displayed);

        Response response = UserSteps.signInWithSignInRequest(new SignInRequest(email, password));

        if (response.getStatusCode() == 200) {
            String accessToken = response.then().extract().as(SuccessSignInSignUpResponse.class).getAccessToken();
            UserSteps.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Невреный пароль при создание нового пользователя")
    public void createUserWithIncorrectPasswordGetError() {

        password = RandomStringUtils.randomAlphanumeric(5);

        registrationPage.setNameInput(name);
        registrationPage.setEmailInput(email);
        registrationPage.setPasswordInput(password);
        registrationPage.clickSignUpButton();
        registrationPage.waitForIncorrectPasswordError();
        Assert.assertEquals("Некорректный пароль", registrationPage.getIncorrectPasswordErrorText());

        boolean displayed = registrationPage.isVisibleIncorrectPasswordErrorText();
        if (!displayed) {
            Response response = UserSteps.signInWithSignInRequest(new SignInRequest(email, password));

            if (response.getStatusCode() == 200) {
                String accessToken = response.then().extract().as(SuccessSignInSignUpResponse.class).getAccessToken();
                UserSteps.deleteUser(accessToken);
            }
        }
        Assert.assertTrue("Не была выведена ошибка о некорректном пароле", displayed);
    }
}
