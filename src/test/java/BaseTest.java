import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static constants.Path.PATH_TO_DRIVER_YANDEX;
import static constants.Path.PATH_TO_YANDEX_BROWSER;

public class BaseTest {
    WebDriver driver;

    @Before
    public void setup() {

        //тест на Google Chrome раскоментировать перед выполнением и закоментировать настройки по подключению Yandex Browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //тесты на Yandex Browser
        ChromeOptions options = new ChromeOptions();
        //System.setProperty("webdriver.chrome.driver", PATH_TO_DRIVER_YANDEX);
        //options.setBinary(PATH_TO_YANDEX_BROWSER);
        //driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
