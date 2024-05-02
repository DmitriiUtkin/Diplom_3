package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {

    private static final String USER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String DELETE_PATH = "/api/auth/user";

    @Step("Создание пользователя")
    public ValidatableResponse addUser(User user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .when()
                .post(USER_PATH)
                .then()
                .spec(getResponseSpec());
    }

    @Step("Авторизация")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getRequestSpec())
                .body(credentials)
                .post(LOGIN_PATH)
                .then()
                .spec(getResponseSpec());
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getRequestSpec())
                .header("Authorization", accessToken)
                .delete(DELETE_PATH)
                .then()
                .spec(getResponseSpec());
    }

}
