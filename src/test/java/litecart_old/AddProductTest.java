/*
Описание теста:

Задание 12. Сделайте сценарий для добавления нового товара (продукта)
в учебном приложении litecart (в админке).

1) для добавления товара нужно открыть меню Catalog, в правом верхнем углу
нажать кнопку "Add New Product", заполнить поля с информацией о товаре и сохранить.
2) достаточно заполнить только информацию на вкладках General, Information и Prices.
Скидки (Campains) на вкладке Prices можно не добавлять.
3) переключение между вкладками происходит не мгновенно, поэтому после
переключения можно сделать небольшую паузу
(о том, как делать более правильные ожидания, будет рассказано в следующих занятиях).
4) картинку с изображением товара нужно уложить в репозиторий вместе с кодом.
При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине
работать не будет. Надо средствами языка программирования
преобразовать относительный путь в абсолютный.
5) после сохранения товара нужно убедиться, что он появился
в каталоге (в админке). Клиентскую часть магазина можно не проверять.
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

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddProductTest {
  private static WebDriver driver;

  private boolean areElementsPresent(WebDriver driver, By locator) {
    return driver.findElements(locator).size() > 0;
  }

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");

    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
  }

  @Test
  public void action() {
    System.out.print("\n\n***** Внутри метода action() *****\n\n");

    // int nextInt(int n) - возвращает случайное значение типа int в диапазоне от 0 до n
    int randomInt = 10000 + (new Random()).nextInt(89999); // случайное 5-значное целое (диапазон от 10.000 до 99.999)
    String randomString = String.valueOf(randomInt);
    System.out.print("randomString = " + randomString + "\n\n");

    System.out.print("Создам тестовые данные для добавления нового товара:\n");
    String name = "Test Duck " + randomString;
    String code = randomString;
    String quantity = "7";
    File fileImage = new File("images\\duck.png");
    String absolutePathToFile = fileImage.getAbsolutePath(); // абсолютный путь к файлу начиная с корня системы
    String dateFrom = "17.06.2018";
    String dateTo = "17.06.2022";
    String manufacturer = "ACME Corp.";
    String keywords = "keywords";
    String shortDescription = "Short Description";
    String description = "Description: this is test duck " + randomString;
    String headTitle = "Test Duck " + randomString;
    String metaDescription = "Meta Description";
    String purchasePrice = "23";
    String purchasePriseValuta = "US Dollars";
    String priceUSD = "23";
    System.out.print("name = " + name + "\ncode = " + code + "\nquantity = " + quantity
        + "\nfileImage = " + fileImage + "\nabsolutePathToFile = " + absolutePathToFile
        + "\ndateFrom = " + dateFrom + "\ndateTo = " + dateTo
        + "\nmanufacturer = " + manufacturer + "\nkeywords = " + keywords
        + "\nshortDescription = " + shortDescription + "\ndescription = " + description
        + "\nheadTitle = " + headTitle + "\nmetaDescription = " + metaDescription
        + "\npurchasePrice = " + purchasePrice + "\npurchasePriseValuta = " + purchasePriseValuta
        + "\npriceUSD = " + priceUSD + "\n\n");

    System.out.print("Захожу в панель администратора...\n");
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@name='login']")).click();
    String accountUser = driver.findElement(By.xpath("//div[contains(@class,'notice success')]")).getText();
    Assert.assertEquals("You are now logged in as admin", accountUser);

    System.out.print("Захожу в меню Catalog...\n");
    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[2]")).click();
    System.out.print("Жму кнопку Add New Product...\n");
    driver.findElement(By.xpath("//td[@id='content']/div[@style='float: right;']/a[2]")).click();

    System.out.print("Иду на вкладку General заполнять поля...\n");
    driver.findElement(By.xpath("//a[@href='#tab-general']")).click(); // кликнул по General
    driver.findElement(By.xpath("//div[@id='tab-general']//tbody/tr[1]//label[1]")).click();
    driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(name);
    driver.findElement(By.xpath("//input[@name='code']")).sendKeys(code);
    driver.findElement(By.xpath("//input[@value='1-3']")).click();
    String selectAll = Keys.chord(Keys.CONTROL, "a"); // Ctrl + A
    driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(selectAll);
    driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(Keys.DELETE);
    driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(quantity);
    driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(absolutePathToFile);
    driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys(dateFrom);
    driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys(dateTo);

    System.out.print("Иду на вкладку Information заполнять поля...\n");
    driver.findElement(By.xpath("//a[@href='#tab-information']")).click(); // кликнул по Information
    driver.findElement(By.xpath("//select[@name='manufacturer_id']")).sendKeys(manufacturer);
    driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys(keywords);
    driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys(shortDescription);
    driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys(description);
    driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys(headTitle);
    driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys(metaDescription);

    System.out.print("Иду на вкладку Prices заполнять поля...\n");
    driver.findElement(By.xpath("//a[@href='#tab-prices']")).click(); // кликнул по Prices
    driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
    driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(Keys.DELETE);
    driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(purchasePrice);
    driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")).sendKeys(purchasePriseValuta);
    driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys(priceUSD);

    System.out.print("Все поля заполнил. Пытаюсь сохранить товар... ");
    driver.findElement(By.xpath("//button[@name='save']")).click();
    String expectedMessage = "Changes were successfully saved.";
    String actualMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    Assert.assertEquals(expectedMessage, actualMessage);
    System.out.print("Успех!\n\n");

    System.out.print("Убедимся что созданный товар появился в каталоге... ");
    Assert.assertTrue(areElementsPresent(driver, By.linkText(name)));
    System.out.print("\n" + driver.findElement(By.linkText(name)) + "\n");
    System.out.print("Успех!\n\n");

    System.out.print("Выхожу из панели администрирования... ");
    driver.findElement(By.xpath("//a[@title='Logout']")).click();
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");

    driver.quit();
    driver = null;
  }
}
