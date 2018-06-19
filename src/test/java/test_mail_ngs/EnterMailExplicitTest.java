/*
В данном тесте использую явные ожидания.
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnterMailExplicitTest {
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
        driver.get("https://mail.ngs.ru/");

        // ВВОЖУ ЛОГИН:
        //
        // Можно делать таким способом:
        // WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login']")));
        // loginField.sendKeys("user.testov");
        //
        // Или таким способом:
        wait
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login']")))
          .sendKeys("user.testov");

        // Ввожу пароль:
        (new WebDriverWait(driver, 10))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='pass']")))
          .sendKeys("zxc67*Q");

        // Жму кнопку Войти:
        (new WebDriverWait(driver, 15))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'ngsmail__login-submit')]")))
          .click();

        // Убеждаюсь что попал в свой почтовый ящик:
        String mailUser = wait
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@id='td_header_right1']")))
                            .getText();
        Assert.assertEquals("user.testov@ngs.ruВыход", mailUser);

        // Нажимаю кнопку Выйти:
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='logout']"))).click();
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
