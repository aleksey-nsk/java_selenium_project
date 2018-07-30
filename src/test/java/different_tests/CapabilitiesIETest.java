/*
В данном тесте использую различные капабилити, на примере браузера IE.
Использование капабилитиз:
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

public class CapabilitiesIETest {
  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    // Объект "капабилити" используется для настройки браузера:
    DesiredCapabilities caps = new DesiredCapabilities();
    // Как должен реагировать браузер, когда встретился неожиданный алерт:
    caps.setCapability("unexpectedAlertBehaviour", "dismiss");
    caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
    caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
    // Передадим в конструктор драйвера инфу о настройках браузера:
    driver = new InternetExplorerDriver(caps);
    // Выведем на консоль инфу о текущих настройках браузера:
    System.out.println(((HasCapabilities) driver).getCapabilities());
  }

  @Test
  public void action() {
    driver.get("https://www.google.ru/");
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.titleIs("Google"));
  }

  @AfterClass
  public static void stop() {
    driver.quit();
    driver = null;
  }
}
