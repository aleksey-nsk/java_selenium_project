/*
В данном тесте использую явные ожидания.
Описание теста:
- открываю страницу почты ngs
- захожу в свой почтовый ящик
- убеждаюсь что попал в свой почтовый ящик
- выхожу из почтового ящика
*/

package ngs_mail;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NgsExplicitWaitTest {

  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");

    // Проверю работу автотеста в 3 основных браузерах:
    // System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    // driver = new ChromeDriver(); // инициализация драйвера
    // System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    // driver = new InternetExplorerDriver(); // инициализация драйвера
    System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
    driver = new FirefoxDriver(); // инициализация драйвера

    driver.manage().window().maximize();
  }

  @Test
  public void ngsExplicitWaitTest() {
    System.out.print("\n\n***** Внутри метода ngsExplicitWaitTest() *****\n\n");

    System.out.println("Открываю страницу почты ngs");
    driver.get("https://mail.ngs.ru/");
    (new WebDriverWait(driver, 2)).until(ExpectedConditions.titleIs("ГОРОДСКАЯ ПОЧТОВАЯ СЛУЖБА"));

    System.out.println("Ввожу логин");
    (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login']")))
        .sendKeys("user.testov");

    System.out.println("Ввожу пароль");
    (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='pass']")))
        .sendKeys("zxc67*Q");

    System.out.println("Жму кнопку Войти");
    (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'ngsmail__login-submit')]")))
        .click();

    System.out.println("Убеждаюсь что попал в свой почтовый ящик");
    String mailUser = (new WebDriverWait(driver, 5))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@id='td_header_right1']")))
        .getText();
    Assert.assertEquals("user.testov@ngs.ruВыход", mailUser);

    System.out.println("Нажимаю кнопку Выйти");
    (new WebDriverWait(driver, 5))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='logout']")))
        .click();
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    driver.quit();
    driver = null;
  }
}
