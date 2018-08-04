package litecart_without_page_object;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class CustomerRegistrationTest extends TestBase {

  @Test
  public void test011() {
    System.out.print("\n\n***** Внутри метода test011() *****\n\n");

    System.out.println("Открываю страницу Create Account");
    driver.get("http://localhost/litecart_using_page_object/en/create_account");

    System.out.println("Заполняю форму регистрации нового клиента");
    long random = System.currentTimeMillis();
    driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Firstname-" + random);
    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Lastname");
    driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("Test Adress");
    driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("12345");
    driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Test City");
    driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
    driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("United States" + Keys.ENTER);
    driver.findElement(By.xpath("//select[@name='zone_code']")).click();
    driver.findElement(By.xpath("//select[@name='zone_code']/option[@value='KS']")).click(); // штат Канзас
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys("email" + random + "@test.ru");
    driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("+10123456789");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qwerty");
    driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys("qwerty");

    System.out.println("Нажимаю кнопку Create Account");
    driver.findElement(By.xpath("//button[@name='create_account']")).click();

    System.out.println("Убеждаюсь что регистрация прошла успешно");
    String registrationMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    Assert.assertEquals("Your customer account has been created.", registrationMessage);

    System.out.println("Выхожу из созданной учётки (Logout)");
    driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
    String logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    Assert.assertEquals("You are now logged out.", logOutMessage);

    System.out.println("Повторно захожу (Login) в только что созданную учётную запись");
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys("email" + random + "@test.ru");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qwerty");
    driver.findElement(By.xpath("//button[@name='login']")).click();
    String expectedResult = "You are now logged in as Firstname-" + random + " Lastname.";
    String logInMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    Assert.assertEquals(expectedResult, logInMessage);

    System.out.println("Повторно выхожу из созданной учётки (Logout)");
    driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
    logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    Assert.assertEquals("You are now logged out.", logOutMessage);
  }
}
