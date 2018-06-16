// Неявное ожидание.

package temp.chrome;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class My040ImplicitTest {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");

        // Инициализация драйвера:
        driver = new ChromeDriver();

        // Задаём неявное ожидание. В скобках указываем, сколько
        // времени команда поиска элемента должна ждать:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://mail.ngs.ru/");
    }

    @Test
    public void action(){
        // Ввожу логин:
        WebElement loginField = driver.findElement(By.xpath("//input[@id='login']"));
        loginField.sendKeys("user.testov");

        // Ввожу пароль:
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='pass']"));
        passwordField.sendKeys("zxc67*Q");

        // Жму кнопку Войти:
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@class,'ngsmail__login-submit')]"));
        loginButton.click();

        // Убеждаюсь что попал в свой почтовый ящик:
        WebElement profileUser = driver.findElement(By.xpath("//td[@id='td_header_right1']"));
        String mailUser = profileUser.getText();
        Assert.assertEquals("user.testov@ngs.ruВыход", mailUser);
    }

    @AfterClass
    public static void stop(){
        // Нажать кнопку Выйти:
        WebElement logoutButton = driver.findElement(By.xpath("//a[@href='logout']"));
        logoutButton.click();

        // Закрыть браузер:
        driver.quit();
    }
}
