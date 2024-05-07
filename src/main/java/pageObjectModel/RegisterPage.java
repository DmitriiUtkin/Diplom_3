package pageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By NAME_FIELD = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    public static final By EMAIL_FIELD = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    public static final By PASSWORD_FIELD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    public static final By REGISTER_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    private static final By ENTER_BUTTON = By.xpath(".//a[@class='Auth_link__1fOlj']");


    @Step("Заполнение полей формы регистрации")
    public RegisterPage enterRegistrationFields(String nameUser, String emailUser, String passwordUser) {

        driver.findElement(NAME_FIELD).sendKeys(nameUser);
        driver.findElement(EMAIL_FIELD).sendKeys(emailUser);
        driver.findElement(PASSWORD_FIELD).sendKeys(passwordUser);
        return new RegisterPage(driver);
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public LoginPage clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
        return new LoginPage(driver);
    }

    @Step("Нажать кнопку Войти")
    public LoginPage clickEnterButtonInRegistrationForm() {
        driver.findElement(ENTER_BUTTON).click();
        WebElement loginPage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(LoginPage.EMAIL_FIELD));
        return new LoginPage(driver);
    }

}

