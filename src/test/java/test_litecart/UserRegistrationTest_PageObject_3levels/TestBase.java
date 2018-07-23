package test_litecart.UserRegistrationTest_PageObject_3levels;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

// Базовый класс для тестов:
public class TestBase {

    // public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>(); // может убрать потом
    // private static WebDriver driver;

    // public static WebDriver driver;
    // public static WebDriverWait wait;
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public static Application app;

    @BeforeClass
    public static void start(){
        System.out.print("\n\n***** Внутри метода start() *****\n\n");

        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        // System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        // driver = new ChromeDriver(); // инициализация драйвера
        // wait = new WebDriverWait(driver, 10);
        app = new Application();
        tlApp.set(app);

        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        // driver.manage().window().maximize();

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; })
        );
    }

    /*
    @AfterClass
    public static void stop(){
        System.out.print("\n\n***** Внутри метода stop() *****\n\n");

        driver.quit();
        driver = null;
    }
    */
}
