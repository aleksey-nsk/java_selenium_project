/*
Описание теста:
- открываю страницу входа в панель администрирования учебного приложения
- ввожу логин/пароль
- захожу в панель администрирования
- убеждаюсь что вошёл в панель из-под админской учётки
- выхожу из панели администрирования
*/

package test_litecart;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnterAdminPanelTest {
    private static WebDriver driver;
    private static WebDriverWait wait; // с помощью объекта wait буду реализовывать явные ожидания

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        driver = new InternetExplorerDriver(); // инициализация драйвера
        wait = new WebDriverWait(driver, 5); // инициализирую объект wait
        driver.manage().window().maximize();
    }

    @Test
    public void action(){
        driver.get("http://localhost/litecart/admin/");

        // Ввожу логин:
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']"))).sendKeys("admin");

        // Ввожу пароль:
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='password']"))).sendKeys("admin");

        // Жму кнопку Login:
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='login']"))).click();

        // Убеждаюсь что вошёл в панель из-под админской учётки:
        String accountUser = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'notice success')]"))).getText();
        Assert.assertEquals("You are now logged in as admin", accountUser);

        // Выхожу из панели администрирования:
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Logout']"))).click();
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
