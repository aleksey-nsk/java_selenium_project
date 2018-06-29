/*
ОПЦИИ КОМАНДНОЙ СТРОКИ (ОПЦИИ ЗАПУСКА БРАУЗЕРОВ)

Опции командной строки используются для настройки браузера.
Для примера использую браузер Chrome.

Создаётся специальный объект типа ChromeOptions, и в этот
объект добавляются аргументы ---> это и есть опции командной строки,
которые будут использованы при запуске браузера.
*/

package test_different;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeOptionsTest {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("start-fullscreen"); // полноэкранный режим
        options.addArguments("start-maximized");
        options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        driver = new ChromeDriver(options);
    }

    @Test
    public void action(){
        driver.get("https://yandex.ru/");
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
