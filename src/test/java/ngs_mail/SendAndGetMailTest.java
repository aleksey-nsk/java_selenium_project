/*
Описание теста:
- открываю страницу почты ngs
- захожу в ПочтовыйЯщик1
- отправляю письмо на ПочтовыйЯщик2
- выхожу из ПочтовогоЯщика1
- захожу в ПочтовыйЯщик2
- убеждаюсь что письмо пришло
- выхожу из ПочтовогоЯщика2
*/

package ngs_mail;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class SendAndGetMailTest {

  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");

    // Проверю работу автотеста в 3 основных браузерах:
    // System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    // driver = new ChromeDriver(); // инициализация драйвера
    System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    driver = new InternetExplorerDriver(); // инициализация драйвера
    // System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
    // driver = new FirefoxDriver(); // инициализация драйвера

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
  }

  @Test
  public void sendAndGetMailTest() {
    System.out.print("\n\n***** Внутри метода sendAndGetMailTest() *****\n\n");

    System.out.println("Открываю страницу почты ngs");
    driver.get("https://mail.ngs.ru/");

    System.out.println("Захожу в ПочтовыйЯщик1");
    driver.findElement(By.xpath("//input[@id='login']")).sendKeys("user.testov");
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("zxc67*Q");
    driver.findElement(By.xpath("//button[@class='ngsmail__login-submit']")).click();
    String mailUser1 = driver.findElement(By.xpath("//td[@id='td_header_right1']")).getText();
    Assert.assertEquals("user.testov@ngs.ruВыход", mailUser1);

    System.out.println("Отправляю письмо на ПочтовыйЯщик2");
    driver.findElement(By.xpath("//a[@href='Compose.wssp?selectedAddressBook=[addressbook]&OpenBook=send']")).click();
    String newLetter = driver.findElement(By.xpath("//td[@id='folder_name']")).getText();
    Assert.assertEquals(" НОВОЕ ПИСЬМО", newLetter);
    driver.findElement(By.xpath("//input[@name='To']")).sendKeys("test9921@ngs.ru");
    long random = System.currentTimeMillis();
    String subject = "Тема письма " + random;
    String text = "Текст письма " + random;
    driver.findElement(By.xpath("//input[@name='Subject']")).sendKeys(subject);
    driver.findElement(By.xpath("//textarea[@name='Body']")).sendKeys(text);
    driver.findElement(By.xpath("//input[@name='Send']")).click();
    String messageSent = driver.findElement(By.xpath("//font[@color='green']")).getText();
    Assert.assertEquals("Сообщение отослано", messageSent);

    System.out.println("Выхожу из ПочтовогоЯщика1");
    driver.findElement(By.xpath("//a[@href='logout']")).click();

    System.out.println("Захожу в ПочтовыйЯщик2");
    driver.get("https://mail.ngs.ru/");
    driver.findElement(By.xpath("//input[@id='login']")).sendKeys("test9921");
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("157RTq");
    driver.findElement(By.xpath("//button[@class='ngsmail__login-submit']")).click();
    String mailUser2 = driver.findElement(By.xpath("//td[@id='td_header_right1']")).getText();
    Assert.assertEquals("test9921@ngs.ruВыход", mailUser2);

    System.out.println("Убеждаюсь что письмо пришло");
    System.out.println("  нахожу письмо и открываю его");
    WebElement parentElement = driver.findElement(By.xpath("//div[text()='"+subject+"']/../.."));
    WebElement childElement = parentElement.findElement(By.xpath("./td/div[@class='from_field']"));
    childElement.click();
    System.out.println("  проверяю поле От");
    String fromUser = driver.findElement(By.xpath("//td[text()='От']/../td[@class='mail_fields_data']")).getText();
    Assert.assertEquals("<user.testov@ngs.ru>", fromUser);
    System.out.println("  проверяю поле Тема");
    String messageSubject = driver.findElement(By.xpath("//td[text()='Тема']/../td[@class='mail_fields_data']")).getText();
    Assert.assertEquals(subject, messageSubject);
    System.out.println("  проверяю поле Кому");
    String toUser = driver.findElement(By.xpath("//td[text()='Кому']/../td[@class='mail_fields_data']")).getText();
    Assert.assertEquals("test9921@ngs.ru", toUser);
    System.out.println("  проверяю текст письма");
    String messageText = driver.findElement(By.xpath("//div[@class='mail_content']/tt")).getText();
    Assert.assertEquals(text, messageText);

    System.out.println("Выхожу из ПочтовогоЯщика2");
    driver.findElement(By.xpath("//a[@href='logout']")).click();
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    driver.quit();
    driver = null;
  }
}
