package different_tests.My031Test;

import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
  public static WebDriver driver;
  public static WebDriverWait wait;

  @BeforeClass
  public static void start() {
    if (driver != null) {
      return;
    }
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);

    Runtime.getRuntime().addShutdownHook(
        new Thread(() -> {
          driver.quit();
          driver = null;
        })
    );
  }

  @After
  public void stop() {
    // driver.quit();
    // driver = null;
  }
}
