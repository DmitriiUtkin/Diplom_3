import api.*;
import com.github.javafaker.Faker;
import configuration.SelectingBrowsers;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjectModel.HomePage;

public class EnterAccountTest {

        private WebDriver driver;
        private UserClient userClient;
        private User user;
        private String accessToken;

    Faker faker = new Faker();
    String nameUser = faker.name().lastName();
    String emailUser = faker.internet().emailAddress();
    String passwordUser = faker.internet().emailAddress();


        @Before
        public void setUp() {

            userClient = new UserClient();
            user = UserGenerator.base();
            ValidatableResponse response = userClient.addUser(user);
            ValidatableResponse loginResponse = userClient.login(UserCredentials.from(user));
            accessToken =loginResponse.extract().path("accessToken");

            driver = SelectingBrowsers.createDriver();
            driver.manage().window().maximize();
            driver.get(RestClient.BASE_URL);
            userClient = new UserClient();

            emailUser = user.getEmail();
            passwordUser = user.getPassword();
        }

        @After
        public void tearDown() {

            driver.quit();

            if (accessToken != null) {
                userClient.deleteUser(accessToken);
            }
        }

        @Test
        @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
        public void enterAccountByEnterAccountButtonOnMainPageTest() {

            HomePage homePage = new HomePage(driver);
            boolean isFinishOrderButtonDisplayed = homePage.clickEnterAccountButton()
                    .enterLoginFields(emailUser, passwordUser)
                    .clickEnterButtonLoginPage()
                    .isFinishOrderButtonDisplayed();
        }

        @Test
        @DisplayName("Вход через кнопку «Личный кабинет")
        public void enterAccountByEnterUserAccountButtonOnMainPageTest() {

        HomePage homePage = new HomePage(driver);
        boolean isFinishOrderButtonDisplayed = homePage.clickUserAccountButton()
                .enterLoginFields(emailUser, passwordUser)
                .clickEnterButtonLoginPage()
                .isFinishOrderButtonDisplayed();
    }

        @Test
        @DisplayName("Вход через кнопку в форме регистрации")
        public void enterAccountInRegistrationFormTest() {

        HomePage homePage = new HomePage(driver);
        boolean isFinishOrderButtonDisplayed = homePage.clickUserAccountButton()
                .clickRegisterButtonLoginPage()
                .clickEnterButtonInRegistrationForm()
                .enterLoginFields(emailUser, passwordUser)
                .clickEnterButtonLoginPage()
                .isFinishOrderButtonDisplayed();
    }

        @Test
        @DisplayName("Вход через кнопку в форме восстановления пароля")
        public void enterAccountInForgottenPasswordFormTest() {

        HomePage homePage = new HomePage(driver);
        boolean isFinishOrderButtonDisplayed = homePage
                .clickUserAccountButton()
                .clickRestorePasswordButton()
                .clickEnterButtonForgotPasswordPage()
                .enterLoginFields(emailUser, passwordUser)
                .clickEnterButtonLoginPage()
                .isFinishOrderButtonDisplayed();
    }

}




