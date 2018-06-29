/*
Несколько браузеров сразу.
Для примера запущу сразу 2 браузера Firefox.
Также проверю куки.
*/

package test_different;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeveralBrowsersFirefoxTest {
    private static WebDriver driver1;
    private static WebDriver driver2;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
        driver1 = new FirefoxDriver();
        driver2 = new FirefoxDriver();
    }

    @Test
    public void action(){
        // В обоих браузерах открываю какую-нибудь страницу:
        driver1.get("https://selenium2.ru/");
        driver2.get("https://selenium2.ru/");

        // Сразу же при открытии страницы устанавливаются какие-то куки:
        System.out.print("Куки1: ");
        System.out.println( driver1.manage().getCookies() );
        System.out.print("Куки2: ");
        System.out.println( driver2.manage().getCookies() );

        // Удалим все куки в первом экземпляре браузера:
        driver1.manage().deleteAllCookies();

        // Опять смотрю куки:
        System.out.print("Куки1: ");
        System.out.println( driver1.manage().getCookies() );
        System.out.print("Куки2: ");
        System.out.println( driver2.manage().getCookies() );
    }

    @AfterClass
    public static void stop(){
        driver1.quit();
        driver1 = null;

        driver2.quit();
        driver2 = null;
    }
}
