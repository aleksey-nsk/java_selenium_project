/*
В данном тесте использую различные капабилити,
на примере браузера InternetExplorer.

Использование капабилити:
- для выбора правильного браузера
- для настройки драйвера
- для настройки браузера
*/

package different_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InternetExplorerCapabilitiesTest {

  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");

    System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");

    // Объект капабилити используется для настройки браузера:
    DesiredCapabilities caps = new DesiredCapabilities();

    // Как должен реагировать браузер, если встретился неожиданный алерт:
    caps.setCapability("unexpectedAlertBehaviour", "dismiss");

    caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
    caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

    // Передадим в конструктор драйвера инфу о настройках браузера:
    driver = new InternetExplorerDriver(caps);

    // Вывожу на консоль инфу о текущих настройках браузера:
    System.out.println(((HasCapabilities) driver).getCapabilities());
  }

  @Test
  public void internetExplorerCapabilitiesTest() {
    System.out.print("\n\n***** Внутри метода internetExplorerCapabilitiesTest() *****\n\n");
    System.out.println("Открываю страничку Гугла");
    driver.get("https://www.google.ru/");
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.titleIs("Google"));
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    driver.quit();
    driver = null;
  }
}
