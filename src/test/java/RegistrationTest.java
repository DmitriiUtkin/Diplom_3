import api.RestClient;
import api.UserClient;
import api.UserCredentials;
import com.github.javafaker.Faker;
import configuration.SelectingBrowsers;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjectModel.HomePage;

public class RegistrationTest {
    private WebDriver driver;
    private UserClient userClient;
    private String accessToken;

    Faker faker = new Faker();
    String nameUser = faker.name().lastName();
    String emailUser = faker.internet().emailAddress();
    String passwordUser = faker.internet().emailAddress();

    @Before
    public void setUp() {
        driver = SelectingBrowsers.createDriver();
        driver.manage().window().maximize();
        driver.get(RestClient.BASE_URL);
        userClient = new UserClient();
    }

    @After
    public void tearDown() {

        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registerUserSuccessTest() {

        HomePage homePage = new HomePage(driver);
        homePage.clickUserAccountButton()
                .clickRegisterButtonLoginPage()
                .enterRegistrationFields(nameUser, emailUser, passwordUser)
                .clickRegisterButton()
                .isLoginPageDisplayed();

        ValidatableResponse loginResponse = userClient.login(new UserCredentials(emailUser, passwordUser));
        accessToken =loginResponse.extract().path("accessToken");
        userClient.deleteUser(accessToken);
            }

    @Test
    @DisplayName("Ошибка при регистрации пользователя с паролем менее 6 символов")
    public void registerErrorPasswordLess6CharactersTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickUserAccountButton()
                .clickRegisterButtonLoginPage()
                .enterRegistrationFields(nameUser, emailUser, "12345")
                .clickRegisterButton()
                .isErrorMessageIncorrectPasswordDisplayed();

    }

    }
