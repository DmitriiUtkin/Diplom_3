import api.RestClient;
import configuration.SelectingBrowsers;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjectModel.HomePage;

import static org.junit.Assert.assertTrue;

public class IngredientConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = SelectingBrowsers.createDriver();
        driver.manage().window().maximize();
        driver.get(RestClient.BASE_URL);

    }

    @After
    public void tearDown() {

        driver.quit();
    }

    @Test
    @DisplayName("Переключение на соусы в конструкторе ингредиентов")
    public void selectSaucesInConstructor() {
        HomePage homePage = new HomePage(driver);
        boolean isSaucesDisplayed = homePage.isSaucesDisplayed();
        assertTrue(isSaucesDisplayed);
    }

    @Test
    @DisplayName("Переключение на булки в конструкторе ингредиентов")
    public void selectBunsInConstructor() {
        HomePage homePage = new HomePage(driver);
        homePage.isSaucesDisplayed();
        boolean isBunsDisplayed = homePage.isBunsDisplayed();
        assertTrue(isBunsDisplayed);
    }

    @Test
    @DisplayName("Переключение на начинки в конструкторе ингредиентов")
    public void selectFillingsInConstructor() {
        HomePage homePage = new HomePage(driver);
        boolean isFillingsDisplayed = homePage.isFillingDisplayed();
        assertTrue(isFillingsDisplayed);
    }

}
