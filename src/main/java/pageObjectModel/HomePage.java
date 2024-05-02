package pageObjectModel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pageObjectModel.RegisterPage.*;
import static pageObjectModel.UserAccountPage.USER_PROFILE_BUTTON;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIME_OUT_IN_SECONDS = 30;


    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        this.driver = driver;
    }

    public static final By USER_ACCOUNT_BUTTON = By.xpath(".//p[text()='Личный Кабинет']"); //Кнопка "Личный кабинет"
    private static final By ENTER_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']"); //Кнопка "Войти в аккаунт"
    private static final By BUNS_BUTTON = By.xpath(".//div[span[text()='Булки']]");
    private static final By SAUCES_BUTTON = By.xpath(".//span[text()='Соусы']");
    private static final By FILLINGS_BUTTON = By.xpath(".//span[text()='Начинки']");

    public static final By FINISH_ORDER_BUTTON = By.xpath(".//button[text()='Оформить заказ']");

    public static final By BUNS_ARE_SELECTED = By.xpath(".//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[text()='Булки']");


    public static final By SAUCES_ARE_SELECTED = By.xpath(".//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[text()='Соусы']");

    public static final By FILLINGS_ARE_SELECTED = By.xpath(".//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[text()='Начинки']");

    @Step("Нажать на кнопку Личный кабинет")
    public LoginPage clickUserAccountButton() {
        driver.findElement(USER_ACCOUNT_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
        return new LoginPage(driver);
            }

    @Step("Нажать на кнопку Войти в аккаунт")
    public LoginPage clickEnterAccountButton() {
        driver.findElement(ENTER_ACCOUNT_BUTTON).click();
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
        return new LoginPage(driver);
            }

    @Step("Отображение кнопки Оформить заказ")
    public boolean isFinishOrderButtonDisplayed() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_ORDER_BUTTON));
        return element.isDisplayed();
    }

    @Step("Нажать на кнопку Личный кабинет после авторизации")
    public UserAccountPage clickUserAccountButtonAfterAuthorization() {
        driver.findElement(USER_ACCOUNT_BUTTON).click();
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USER_PROFILE_BUTTON));
        return new UserAccountPage (driver);
    }

    @Step("Переключение на Соусы в конструкторе ингредиентов")
    public boolean isSaucesDisplayed () {
        driver.findElement(SAUCES_BUTTON).click();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(SAUCES_ARE_SELECTED));
        return element.isDisplayed();
    }

    @Step("Переключение на Булки в конструкторе ингредиентов")
    public boolean isBunsDisplayed () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUNS_BUTTON));
        driver.findElement(BUNS_BUTTON).click();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BUNS_ARE_SELECTED));
        return element.isDisplayed();
    }

    @Step("Переключение на Начинки в конструкторе ингредиентов")
    public boolean isFillingDisplayed () {
        driver.findElement(FILLINGS_BUTTON).click();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(FILLINGS_ARE_SELECTED));
        return element.isDisplayed();
    }
}
