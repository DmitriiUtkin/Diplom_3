package pageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pageObjectModel.HomePage.FINISH_ORDER_BUTTON;
import static pageObjectModel.RegisterPage.*;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By EMAIL_FIELD = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    private static final By PASSWORD_FIELD = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль']");
    private static final By ENTER_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    public static final By REGISTER_BUTTON_LOGIN_PAGE = By.xpath(".//a[@href='/register']");

    public static final By RESTORE_PASSWORD_BUTTON = By.cssSelector("[href='/forgot-password']");
    private static final int TIME_OUT_IN_SECONDS = 10;
    private static final By ERROR_MESSAGE_INCORRECT_PASSWORD = By.xpath(".//p[text()='Некорректный пароль']");

    @Step("Нажать на кнопку Зарегистрироваться")
    public RegisterPage clickRegisterButtonLoginPage() {
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        driver.findElement(REGISTER_BUTTON_LOGIN_PAGE).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_FIELD));
        return new RegisterPage(driver);
    }

    @Step("Отображение формы для введения логина")
    public boolean isLoginPageDisplayed() {
        WebElement loginPage = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD));
        return loginPage.isDisplayed();
    }

    @Step("Отображение ошибки о некорректном пароле")
    public boolean isErrorMessageIncorrectPasswordDisplayed() {
        WebElement loginPage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_INCORRECT_PASSWORD));
        return loginPage.isDisplayed();
    }

    @Step("Заполнение полей формы авторизации")
    public LoginPage enterLoginFields(String emailUser, String passwordUser) {

        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(emailUser);
        driver.findElement(PASSWORD_FIELD).clear();
        driver.findElement(PASSWORD_FIELD).sendKeys(passwordUser);
        return new LoginPage(driver);
    }

    @Step("Нажать на кнопку Войти")
    public HomePage clickEnterButtonLoginPage() {
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        driver.findElement(ENTER_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_ORDER_BUTTON));
        return new HomePage(driver);
    }

    @Step("Нажать кнопку Восстановить пароль")
    public ForgotPasswordPage clickRestorePasswordButton () {
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        driver.findElement(RESTORE_PASSWORD_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ForgotPasswordPage.ENTER_BUTTON_FORGOT_PASSWORD_PAGE));
        return new ForgotPasswordPage (driver);
    }



}
