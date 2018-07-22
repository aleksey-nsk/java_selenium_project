/*
ИСХОДНЫЙ ТЕСТ:
В данном тесте использую неявные ожидания.
Описание теста:
- открываю страницу почты ngs
- захожу в свой почтовый ящик
- убеждаюсь что попал в свой почтовый ящик
- выхожу из почтового ящика

ПЕРЕДЕЛАЮ с использованием Page Object
*/

package temp.chrome;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class delete_this {
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
    public void action(){
        System.out.print("\n\n***** Внутри метода action() *****\n\n");

        driver.get("https://mail.ngs.ru/");
        driver.findElement(By.xpath("//input[@id='login']")).sendKeys("user.testov");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("zxc67*Q");
        driver.findElement(By.xpath("//button[contains(@class,'ngsmail__login-submit')]")).click();

        // Убеждаюсь что попал в свой почтовый ящик:
        String mailUser = driver.findElement(By.xpath("//td[@id='td_header_right1']")).getText();
        Assert.assertEquals("user.testov@ngs.ruВыход", mailUser);

        // Нажимаю кнопку Выйти:
        driver.findElement(By.xpath("//a[@href='logout']")).click();
    }

    @AfterClass
    public static void stop(){
        System.out.print("\n\n***** Внутри метода stop() *****\n\n");

        driver.quit();
        driver = null;
    }
}
