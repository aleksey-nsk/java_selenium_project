/*
Пример реализации паттерна Page Object
Нашёл на каком-то форуме
Мне данный вариант реализации не нравится !!!
*/

package different_tests.PageObjectExample.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import different_tests.delete_this.pages.LoginPage;
import different_tests.delete_this.pages.MailBoxPage;
import java.util.concurrent.TimeUnit;

public class LoginTest {

  public static WebDriver driver;
  public static LoginPage loginPage;
  public static MailBoxPage mailBoxPage;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
    loginPage = new LoginPage(driver);
    mailBoxPage = new MailBoxPage(driver);
  }

  @Test
  public void loginTest() {
    System.out.print("\n\n***** Внутри метода loginTest() *****\n\n");

    System.out.println("Открываю почту НГС");
    driver.get("https://mail.ngs.ru/");

    System.out.println("Вхожу в свой почтовый аккаунт");
    loginPage.inputLogin("user.testov");
    loginPage.inputPassword("zxc67*Q");
    loginPage.clickLoginButton();

    System.out.println("Проверю что попал в свой почтовый аккаунт");
    String userMail = mailBoxPage.getUserMail();
    Assert.assertEquals("user.testov@ngs.ruВыход", userMail);

    System.out.println("Выхожу из почтового аккаунта");
    mailBoxPage.userLogout();
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    driver.quit();
    driver = null;
  }
}
