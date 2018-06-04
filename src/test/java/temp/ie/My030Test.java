package temp.ie;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class My030Test {
    private static WebDriver driver1;
    private static WebDriver driver2;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        driver1 = new InternetExplorerDriver();
        driver2 = new InternetExplorerDriver();
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

    @After
    public void stop(){
        driver1.quit();
        driver1 = null;

        driver2.quit();
        driver2 = null;
    }
}
