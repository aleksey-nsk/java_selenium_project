package litecart_without_page_object;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TestBase {

  protected static WebDriver driver;

  // Метод проверяющий наличие элемента:
  protected boolean isElementPresent(WebDriver driver, By locator) {
    return driver.findElements(locator).size() > 0;
  }

  protected void goToAdminPanel() {
    System.out.println("Метод для входа в панель админа");
    driver.get("http://localhost/litecart_using_page_object/admin/");
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@name='login']")).click();
    String accountUser = driver.findElement(By.xpath("//div[contains(@class,'notice success')]")).getText();
    Assert.assertEquals("You are now logged in as admin", accountUser);
  }

  protected void exitAdminPanel() {
    System.out.println("Метод для выхода из панели админа");
    driver.findElement(By.xpath("//a[@title='Logout']")).click();
  }

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    driver.quit();
    driver = null;
  }
}
