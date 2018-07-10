package temp.ie;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class My043Test {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.out.print("***** Внутри метода start() *****\n\n");

        // System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        // driver = new ChromeDriver(); // инициализация драйвера
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        driver = new InternetExplorerDriver(); // инициализация драйвера

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
    }

    @Test
    public void action(){
        System.out.print("***** Внутри метода action() *****\n\n");

        // --------------------------------------
        // Захожу в панель администратора
        // --------------------------------------
        /* driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String accountUser = driver.findElement(By.xpath("//div[contains(@class,'notice success')]")).getText();
        Assert.assertEquals("You are now logged in as admin", accountUser); */

        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.xpath("//input[@name='query']")).sendKeys("test");
        String result = driver.findElement(By.xpath("//input[@name='query']")).getAttribute("value");
        System.out.println("result = " + result);

        String result2 = driver.findElement(By.xpath("//input[@name='query']")).getText();
        System.out.println("result2 = " + result2);

        String reference = driver.findElement(By.xpath("//nav[@id='breadcrumbs']/ul/li/a")).getAttribute("href");
        System.out.println("reference = " + reference);

        driver.findElement(By.xpath("//div[@id='region']/div[@class='change']/a")).click();
        String s2 = driver.findElement(By.xpath("//select[@name='currency_code']/option[@value='EUR']")).getAttribute("selected-ttttt");
        System.out.println("s2 = " + s2);

        // -------------------------------------------
        // Выхожу из панели администрирования
        // -------------------------------------------
        // driver.findElement(By.xpath("//a[@title='Logout']")).click();
    }

    @AfterClass
    public static void stop(){
        System.out.print("***** Внутри метода stop() *****\n\n");

        // driver.quit();
        // driver = null;
    }
}
