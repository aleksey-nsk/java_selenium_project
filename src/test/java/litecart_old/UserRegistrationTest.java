/*
Описание теста:

Задание 11. Сделайте сценарий регистрации пользователя. Сделайте сценарий
для регистрации нового пользователя в учебном приложении litecart
(не в админке, а в клиентской части магазина).

1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты
(чтобы не конфликтовало с ранее созданными пользователями, в том числе
при предыдущих запусках того же самого сценария).
2) выход (logout), потому что после успешной регистрации автоматически происходит вход.
3) повторный вход в только что созданную учётную запись.
4) и ещё раз выход.

В качестве страны выбирайте United States, штат произвольный. При этом формат индекса - 5 цифр.
Проверки можно никакие не делать, только действия - заполнение полей, нажатия на кнопки и ссылки.
Если сценарий дошёл до конца, то есть созданный пользователь смог выполнить вход и выход,
значит создание прошло успешно. В форме регистрации есть капча, её нужно отключить в
админке учебного приложения на вкладке Settings -> Security.
*/

package litecart_old;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserRegistrationTest {
  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.out.print("\n***** Внутри метода start() *****\n\n");

    // Проверю работу автотеста в 3 основных браузерах:
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    // System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    // driver = new InternetExplorerDriver(); // инициализация драйвера
    // System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
    // driver = new FirefoxDriver(); // инициализация драйвера

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
  }

  @Test
  public void action() {
    System.out.print("\n***** Внутри метода action() *****\n\n");

    // int nextInt(int n) - возвращает случайное значение типа int в диапазоне от 0 до n
    int randomInt = 10000 + (new Random()).nextInt(89999); // случайное 5-значное целое (диапазон от 10.000 до 99.999)
    String randomString = String.valueOf(randomInt);
    System.out.print("randomString = " + randomString + "\n\n");

    System.out.print("Создам тестовые данные для регистрации нового пользователя:\n");
    String taxId = randomString;
    String company = "Test-Company-" + randomString;
    String firstName = "First-Name-" + randomString;
    String lastName = "Last-Name-" + randomString;
    String adress1 = "Test-Adress1-" + randomString;
    String adress2 = "Test-Adress2-" + randomString;
    String postcode = randomString;
    String city = "Test-City-" + randomString;
    String country = "United States";
    String email = "test-email-" + randomString + "@autotest.ru";
    String phone = "+1" + randomString + randomString;
    String password = "password" + randomString;
    System.out.print("taxId = " + taxId + "\ncompany = " + company + "\nfirstName = " + firstName
        + "\nlastName = " + lastName + "\nadress1 = " + adress1 + "\nadress2 = " + adress2
        + "\npostcode = " + postcode + "\ncity = " + city + "\nemail = " + email
        + "\nphone = " + phone + "\npassword = " + password + "\n\n");

    System.out.print("Открываю страницу Create Account...\n");
    driver.get("http://localhost/litecart/en/create_account");

    System.out.print("Заполняю форму регистрации...\n");
    driver.findElement(By.xpath("//input[@name='tax_id']")).sendKeys(taxId);
    driver.findElement(By.xpath("//input[@name='company']")).sendKeys(company);
    driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
    driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(adress1);
    driver.findElement(By.xpath("//input[@name='address2']")).sendKeys(adress2);
    driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(postcode);
    driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
    driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
    driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(country + Keys.ENTER);
    driver.findElement(By.xpath("//select[@name='zone_code']")).click();
    driver.findElement(By.xpath("//select[@name='zone_code']/option[@value='AK']")).click(); // штат Аляска
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);

    System.out.print("Нажимаю кнопку Create Account...\n");
    driver.findElement(By.xpath("//button[@name='create_account']")).click();

    System.out.print("Убеждаюсь что регистрация прошла успешно... ");
    String registrationMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    // registrationMessage = "Тестовое сообщение 1"; // для проверки падения теста
    Assert.assertEquals("Your customer account has been created.", registrationMessage);
    System.out.print("Успех!\n");

    System.out.print("Выхожу из созданной учётки (Logout)... ");
    driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
    String logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    // logOutMessage = "Тестовое сообщение 2"; // для проверки падения теста
    Assert.assertEquals("You are now logged out.", logOutMessage);
    System.out.print("Успех!\n");

    System.out.print("Повторно захожу (Login) в только что созданную учётную запись... ");
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    driver.findElement(By.xpath("//button[@name='login']")).click();
    String expectedResult = "You are now logged in as " + firstName + " " + lastName + ".";
    String logInMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    // logInMessage = "Тестовое сообщение 3"; // для проверки падения теста
    Assert.assertEquals(expectedResult, logInMessage);
    System.out.print("Успех!\n");

    System.out.print("Повторно выхожу из созданной учётки (Logout)... ");
    driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
    logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    // logOutMessage = "Тестовое сообщение 4"; // для проверки падения теста
    Assert.assertEquals("You are now logged out.", logOutMessage);
    System.out.print("Успех!\n");
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n***** Внутри метода stop() *****\n\n");

    driver.quit();
    driver = null;
  }
}
