package pageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pageObjectModel.HomePage.FINISH_ORDER_BUTTON;

public class UserAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final int TIME_OUT_IN_SECONDS = 10;

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }

    private static final By EXIT_BUTTON = By.xpath(".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']");

    public static final By USER_PROFILE_BUTTON = By.xpath(("//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a"));

    public static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");

    private static final By LOGO_STELLAR_BURGERS_BUTTON = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");


    @Step("Отображение профиля пользователя")
    public boolean isUserProfileDisplayed() {
        WebElement homePage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(USER_PROFILE_BUTTON));
        return homePage.isDisplayed();
    }

    @Step("Нажать на кнопку Конструктор")
    public UserAccountPage clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
//        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_ORDER_BUTTON));
        return new UserAccountPage(driver);
    }

    @Step("Отображение кнопки Оформить заказ")
    public boolean isFinishOrderButtonDisplayed() {
        WebElement userAccountPage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(FINISH_ORDER_BUTTON));
        return userAccountPage.isDisplayed();
    }

    @Step("Нажать на логотип Stellar Burgers")
    public UserAccountPage clickLogoButton() {
        driver.findElement(LOGO_STELLAR_BURGERS_BUTTON).click();
//        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_ORDER_BUTTON));
        return new UserAccountPage(driver);
    }

    @Step("Нажать на кнопку Выйти в профиле пользователя")
    public LoginPage clickLogoutButton() {
        driver.findElement(EXIT_BUTTON).click();
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.EMAIL_FIELD));
        return new LoginPage(driver);
    }

}
