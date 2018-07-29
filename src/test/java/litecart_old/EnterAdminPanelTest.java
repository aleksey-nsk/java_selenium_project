/*
Описание теста:
- открываю страницу входа в панель администрирования учебного приложения
- ввожу логин/пароль
- захожу в панель администрирования
- убеждаюсь что вошёл в панель из-под админской учётки
- выхожу из панели администрирования
*/

package litecart_old;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class EnterAdminPanelTest {
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
        driver.get("http://localhost/litecart/admin/");

        // Ввожу логин:
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");

        // Ввожу пароль:
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");

        // Жму кнопку Login:
        driver.findElement(By.xpath("//button[@name='login']")).click();

        // Убеждаюсь что вошёл в панель из-под админской учётки:
        String accountUser = driver.findElement(By.xpath("//div[contains(@class,'notice success')]")).getText();
        Assert.assertEquals("You are now logged in as admin", accountUser);

        // Выхожу из панели администрирования:
        driver.findElement(By.xpath("//a[@title='Logout']")).click();
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
