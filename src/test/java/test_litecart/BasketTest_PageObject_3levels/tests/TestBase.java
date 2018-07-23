package test_litecart.BasketTest_PageObject_3levels.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test_litecart.BasketTest_PageObject_3levels.app.Application;

import java.util.concurrent.TimeUnit;

// Базовый класс для тестов:
public class TestBase {

    // public static WebDriver driver;
    public static Application app;

    @BeforeClass
    public static void start(){
        System.out.print("\n\n***** Внутри метода start() *****\n\n");

        app = new Application(); // экземпляр класса Application

        /*
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver(); // инициализация драйвера
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
        */
    }

    @AfterClass
    public static void stop(){
        System.out.print("\n\n***** Внутри метода stop() *****\n\n");

        // driver.quit();
        // driver = null;
        app.quit();
    }
}
