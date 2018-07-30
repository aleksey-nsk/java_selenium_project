/*
В данном тесте использую явные ожидания.
Описание теста:
- открываю страницу почты ngs
- жду пока загрузится
- закрываю браузер
*/

package ngs_mail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenUrlTest {
  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    /*
    System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    driver = new InternetExplorerDriver(); // инициализация драйвера
    driver.manage().window().maximize();
    */
    // Проверю работу автотеста в 3 основных браузерах:
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    // System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    // driver = new InternetExplorerDriver(); // инициализация драйвера
    // System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
    // driver = new FirefoxDriver(); // инициализация драйвера
  }

  @Test
  public void action() {
    driver.get("https://mail.ngs.ru/");
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.titleIs("ГОРОДСКАЯ ПОЧТОВАЯ СЛУЖБА"));
  }

  @AfterClass
  public static void stop() {
    driver.quit();
    driver = null;
  }
}
