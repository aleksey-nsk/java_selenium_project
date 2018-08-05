/*
Описание теста:
- запустить сразу 2 браузера
- в обоих браузерах открыть какую-нибудь страницу
- посмотреть куки в обоих браузерах
- удалить все куки в первом экземпляре браузера
- опять посмотреть куки в обоих браузерах
*/

package different_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CheckCookieTest {

  private static WebDriver driver1;
  private static WebDriver driver2;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");

    // Проверю работу автотеста в 3 основных браузерах:
    // System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    // driver1 = new ChromeDriver();
    // driver2 = new ChromeDriver();
    // System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    // driver1 = new InternetExplorerDriver();
    // driver2 = new InternetExplorerDriver();
    System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
    driver1 = new FirefoxDriver();
    driver2 = new FirefoxDriver();
  }

  @Test
  public void checkCookieTest() {
    System.out.print("\n\n***** Внутри метода checkCookieTest() *****\n\n");

    System.out.println("В обоих браузерах открываю некую страницу");
    driver1.get("https://selenium2.ru/");
    driver2.get("https://selenium2.ru/");

    System.out.println("Сразу же при открытии страницы устанавливаются какие-то куки");
    System.out.print("  куки браузера1: ");
    System.out.println(driver1.manage().getCookies());
    System.out.print("  куки браузера2: ");
    System.out.println(driver2.manage().getCookies());

    System.out.println("Удаляю все куки в первом экземпляре браузера");
    driver1.manage().deleteAllCookies();

    System.out.println("Опять смотрю куки в обоих браузерах");
    System.out.print("  куки браузера1: ");
    System.out.println(driver1.manage().getCookies());
    System.out.print("  куки браузера2: ");
    System.out.println(driver2.manage().getCookies());
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");

    driver1.quit();
    driver1 = null;

    driver2.quit();
    driver2 = null;
  }
}
