/*
В данном тесте использую неявные ожидания.
Описание теста:
- открываю страницу почты ngs
- захожу в свой почтовый ящик
- убеждаюсь что попал в свой почтовый ящик
- выхожу из почтового ящика
*/

package test_mail_ngs;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class EnterMailImplicitTest {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        driver = new InternetExplorerDriver(); // инициализация драйвера
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
    }

    @Test
    public void action(){
        driver.get("https://mail.ngs.ru/");

        // Ввожу логин:
        // WebElement loginField = driver.findElement(By.xpath("//input[@id='login']"));
        // loginField.sendKeys("user.testov");
        driver.findElement(By.xpath("//input[@id='login']")).sendKeys("user.testov");

        // Ввожу пароль:
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("zxc67*Q");

        // Жму кнопку Войти:
        driver.findElement(By.xpath("//button[contains(@class,'ngsmail__login-submit')]")).click();

        // Убеждаюсь что попал в свой почтовый ящик:
        String mailUser = driver.findElement(By.xpath("//td[@id='td_header_right1']")).getText();
        Assert.assertEquals("user.testov@ngs.ruВыход", mailUser);

        // Нажимаю кнопку Выйти:
        driver.findElement(By.xpath("//a[@href='logout']")).click();
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
