/*
Описание теста:

Задание 17. Проверьте отсутствие сообщений в логе браузера. Сделайте сценарий,
который проверяет, не появляются ли в логе браузера сообщения при открытии страниц
в учебном приложении, а именно - страниц товаров в каталоге в административной панели.

1) зайти в админку.
2) открыть каталог, категорию которая содержит товары
(страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1).
3) последовательно открывать страницы товаров и проверять,
не появляются ли в логе браузера сообщения (любого уровня).
*/

package litecart_old;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GetBrowserLogsTest {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.out.print("\n\n***** Внутри метода start() *****\n\n");

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver(); // инициализация драйвера

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
    }

    @Test
    public void action() {
        System.out.print("\n\n***** Внутри метода action() *****\n\n");

        // Какие логи доступны:
        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
        System.out.print("Доступны следующие логи: " + logTypes + "\n\n"); // [browser, driver, client]

        /*
        // Проверю что логи браузера действительно отображаются:
        System.out.print("Открою сайт на котором у меня всегда отображаются логи браузера\n");
        driver.get("http://acdc.16mb.com/video.php");
        int amountOfLogsOnTestSite = 0; // количество записей в логах
        for (LogEntry testLogEntry : driver.manage().logs().get("browser").getAll()) {
            System.out.print("testLogEntry = " + testLogEntry + "\n");
            amountOfLogsOnTestSite++;
        }
        System.out.print("Всего записей в логах браузера: " + amountOfLogsOnTestSite + "\n\n");
        */

        System.out.print("Захожу в панель администрирования\n");
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String accountUser = driver.findElement(By.xpath("//div[contains(@class,'notice success')]")).getText();
        Assert.assertEquals("You are now logged in as admin", accountUser);

        System.out.print("Открываю категорию которая содержит товары\n");
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        int amountOfLines = driver.findElements(By.xpath("//form[@name='catalog_form']//tbody/tr")).size();
        int amountOfProducts = amountOfLines - 5;
        System.out.print("amountOfLines = " + amountOfLines + "\namountOfProducts = " + amountOfProducts + "\n\n");

        for (int i=1; i<=amountOfProducts; i++) {
            int i_plus_4 = i + 4;
            System.out.print("Кликаю по товару " + i + "\n");
            driver.findElement(By.xpath("//form[@name='catalog_form']//tr["+i_plus_4+"]/td[3]/a")).click();

            System.out.print("Проверю, не появились ли в логе браузера сообщения (любого уровня)...\n");
            int amountOfLogs = 0; // количество записей в логах
            for (LogEntry logEntry : driver.manage().logs().get("browser").getAll()) {
                System.out.print("logEntry = " + logEntry + "\n");
                amountOfLogs++;
            }
            System.out.print("Всего записей в логах браузера: " + amountOfLogs + "\n");
            Assert.assertTrue(amountOfLogs == 0);
            System.out.print("В логах браузера пусто!\n");

            System.out.print("Возвращаюсь обратно к списку товаров\n\n");
            driver.findElement(By.xpath("//button[@name='cancel']")).click();
        }

        System.out.print("Выхожу из панели администрирования\n");
        driver.findElement(By.xpath("//a[@title='Logout']")).click();
    }

    @AfterClass
    public static void stop(){
        System.out.print("\n\n***** Внутри метода stop() *****\n\n");

        driver.quit();
        driver = null;
    }
}
