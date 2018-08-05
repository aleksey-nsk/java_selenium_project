/*
Пример последовательного запуска нескольких тестов
в одном экземпляре браузера.

Описание теста:
- откроем браузер
- последовательно запустим в нём несколько тестов
- закроем браузер
*/

package different_tests.SequentialStartExample;

import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

  protected static WebDriver driver;
  protected static WebDriverWait wait;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");

    if (driver != null) { return; }
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 5);

    Runtime.getRuntime().addShutdownHook(
        new Thread( () -> {driver.quit(); driver = null;} )
    );
  }

  @After
  public void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    // driver.quit();
    // driver = null;
  }
}
