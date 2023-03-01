package api_steps;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.SignInRequest;
import pojos.UserRequest;

import static constants.UrlForApi.*;
import static io.restassured.RestAssured.given;

public class UserSteps {
    public static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URL_API)
                    .setBasePath(BASE_AUTH_URL)
                    .setContentType(ContentType.JSON)
                    .build();

    @Step("Создаём уникального пользователя")
    public static Response createUniqueUser(UserRequest body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when()
                .post(BASE_REGISTER_URL);
    }

    @Step("Удаляем пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_USER_URL);
    }

    @Step("Выполняем авторизацию с помощью тела запроса на авторизацию")
    public static Response signInWithSignInRequest(SignInRequest signInRequest) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(signInRequest)
                .when()
                .post(BASE_LOGIN_URL);
    }
}
