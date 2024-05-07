package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SelectingBrowsers {

    public static WebDriver createDriver() {
        ChromeOptions chromeOptions = getChromeOptions();
        String browserPropValue = System.getProperty("browser");
        if (browserPropValue != null && browserPropValue.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", "C:/ydriver/yandexdriver.exe");
            return new ChromeDriver();
        }
        return new ChromeDriver(chromeOptions);
    }

    private static ChromeOptions getChromeOptions() {
        return new ChromeOptions();
    }


}
