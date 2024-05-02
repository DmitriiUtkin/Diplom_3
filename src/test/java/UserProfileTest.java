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

public class UserProfileTest {
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
    @DisplayName("Вход в личный кабинет авторизованного пользователя по кнопке «Личный кабинет")
    public void enterAccountByEnterAccountButtonOnMainPageTest() {

        HomePage homePage = new HomePage(driver);
        homePage.clickEnterAccountButton()
                .enterLoginFields(emailUser, passwordUser)
                .clickEnterButtonLoginPage()
                .clickUserAccountButtonAfterAuthorization()
                .isUserProfileDisplayed();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void enterConstructorFromUserAccountProfileTest() {

        HomePage homePage = new HomePage(driver);
        boolean isFinishOrderButtonDisplayed = homePage.clickEnterAccountButton()
                .enterLoginFields(emailUser, passwordUser)
                .clickEnterButtonLoginPage()
                .clickUserAccountButtonAfterAuthorization()
                .clickConstructorButton()
                .isFinishOrderButtonDisplayed();
    }


    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers")
    public void enterLogoFromUserAccountProfileTest() {

        HomePage homePage = new HomePage(driver);
        boolean isFinishOrderButtonDisplayed = homePage.clickEnterAccountButton()
                .enterLoginFields(emailUser, passwordUser)
                .clickEnterButtonLoginPage()
                .clickUserAccountButtonAfterAuthorization()
                .clickLogoButton()
                .isFinishOrderButtonDisplayed();
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void logoutFromUserAccountProfileTest() {

        HomePage homePage = new HomePage(driver);
        boolean isLoginPageDisplayed = homePage.clickEnterAccountButton()
                .enterLoginFields(emailUser, passwordUser)
                .clickEnterButtonLoginPage()
                .clickUserAccountButtonAfterAuthorization()
                .clickLogoutButton()
                .isLoginPageDisplayed();
    }

}
