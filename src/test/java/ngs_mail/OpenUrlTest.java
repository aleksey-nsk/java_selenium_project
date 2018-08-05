/*
Описание теста:
- открываю страницу почты ngs
- жду пока загрузится (использую явные ожидания)
- закрываю браузер
*/

package ngs_mail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenUrlTest {

  private static WebDriver driverChrome, driverIE, driverFirefox;
  private static WebDriver[] drivers = new WebDriver[3];

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");

    // Проверю работу автотеста в 3 основных браузерах:
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driverChrome = new ChromeDriver(); // инициализация драйвера
    System.out.println("====================================");
    System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    driverIE = new InternetExplorerDriver(); // инициализация драйвера
    System.out.println("====================================");
    System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
    driverFirefox = new FirefoxDriver(); // инициализация драйвера

    drivers[0] = driverChrome;
    drivers[1] = driverIE;
    drivers[2] = driverFirefox;
  }

  @Test
  public void openUrlTest() {
    System.out.print("\n\n***** Внутри метода openUrlTest() *****\n\n");

    for (int i=0; i<3; i++) {
      System.out.println("Открываю страницу почты ngs. Использую драйвер:");
      System.out.print(drivers[i] + "\n\n");
      drivers[i].get("https://mail.ngs.ru/");
      (new WebDriverWait(drivers[i], 5)).until(ExpectedConditions.titleIs("ГОРОДСКАЯ ПОЧТОВАЯ СЛУЖБА"));
    }
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");

    for (int i=0; i<3; i++) {
      System.out.println("Останавливаю драйвер:");
      System.out.print(drivers[i] + "\n\n");
      drivers[i].quit();
      drivers[i] = null;
    }
  }
}
