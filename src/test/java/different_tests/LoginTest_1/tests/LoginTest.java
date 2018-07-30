package different_tests.LoginTest_1.tests;

import different_tests.LoginTest_1.pages.LoginPage;
import different_tests.LoginTest_1.pages.MailBoxPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
  public static WebDriver driver;
  public static LoginPage loginPage;
  public static MailBoxPage mailBoxPage;

  @BeforeClass
  public static void setup() {
    System.out.print("\n\n***** Внутри метода setup() *****\n\n");
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    loginPage = new LoginPage(driver);
    mailBoxPage = new MailBoxPage(driver);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
    driver.get("https://mail.ngs.ru/");
  }

  @Test
  public void loginTest() {
    System.out.print("\n\n***** Внутри метода loginTest() *****\n\n");
    loginPage.inputLogin("user.testov");
    loginPage.inputPassword("zxc67*Q");
    loginPage.clickLoginButton();
    String mailUser = mailBoxPage.getUserMail();
    Assert.assertEquals("user.testov@ngs.ruВыход", mailUser);
  }

  @AfterClass
  public static void tearDown() {
    System.out.print("\n\n***** Внутри метода tearDown() *****\n\n");
    mailBoxPage.userLogout();
    driver.quit();
    driver = null;
  }
}
