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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenUrlTest {

  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
  }

  @Test
  public void openUrlTest() {
    System.out.print("\n\n***** Внутри метода openUrlTest() *****\n\n");
    System.out.println("Открываю страницу почты ngs");
    driver.get("https://mail.ngs.ru/");
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.titleIs("ГОРОДСКАЯ ПОЧТОВАЯ СЛУЖБА"));
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    driver.quit();
    driver = null;
  }
}
