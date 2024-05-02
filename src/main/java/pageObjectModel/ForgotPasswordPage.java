package pageObjectModel;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By ENTER_BUTTON_FORGOT_PASSWORD_PAGE = By.xpath(".//a[@class='Auth_link__1fOlj']");

    @Step("Нажать на кнопку Восстановить пароль")
    public LoginPage clickEnterButtonForgotPasswordPage() {
        driver.findElement(ENTER_BUTTON_FORGOT_PASSWORD_PAGE).click();
        WebElement loginPage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(LoginPage.EMAIL_FIELD));
    return new LoginPage(driver);
    }
}
