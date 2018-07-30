package litecart_without_page_object;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.io.File;

public class AddProductTest extends TestBase {

  @Test
  public void test012() {
    System.out.print("\n\n***** Внутри метода test012() *****\n\n");

    goToAdminPanel();

    System.out.println("Захожу в меню Catalog");
    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[2]")).click();

    System.out.println("Жму кнопку Add New Product");
    driver.findElement(By.xpath("//td[@id='content']/div[@style='float: right;']/a[2]")).click();

    System.out.println("Иду на вкладку General заполнять поля");
    driver.findElement(By.xpath("//a[@href='#tab-general']")).click(); // кликнул по General
    driver.findElement(By.xpath("//div[@id='tab-general']//tbody/tr[1]//label[1]")).click();
    String name = "Test Duck " + System.currentTimeMillis();
    driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(name);
    driver.findElement(By.xpath("//input[@name='code']")).sendKeys("12345");
    driver.findElement(By.xpath("//input[@value='1-3']")).click();
    String selectAll = Keys.chord(Keys.CONTROL, "a"); // Ctrl + A
    driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(selectAll);
    driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(Keys.DELETE);
    driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("7");
    File fileImage = new File("images\\duck.png");
    String absolutePathToFile = fileImage.getAbsolutePath(); // абсолютный путь к файлу начиная с корня системы
    driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(absolutePathToFile);
    driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("17.06.2018");
    driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("17.06.2022");

    System.out.println("Иду на вкладку Information заполнять поля");
    driver.findElement(By.xpath("//a[@href='#tab-information']")).click(); // кликнул по Information
    driver.findElement(By.xpath("//select[@name='manufacturer_id']")).sendKeys("ACME Corp.");
    driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("keywords");
    driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("Short Description");
    driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys(name);
    driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys(name);
    driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("Meta Description");

    System.out.println("Иду на вкладку Prices заполнять поля");
    driver.findElement(By.xpath("//a[@href='#tab-prices']")).click(); // кликнул по Prices
    driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
    driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(Keys.DELETE);
    driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("23");
    driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")).sendKeys("US Dollars");
    driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys("23");

    System.out.println("Сохраняю товар");
    driver.findElement(By.xpath("//button[@name='save']")).click();
    String expectedMessage = "Changes were successfully saved.";
    String actualMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    Assert.assertEquals(expectedMessage, actualMessage);

    System.out.print("\nУбедимся что созданный товар появился в каталоге:");
    Assert.assertTrue(isElementPresent(driver, By.linkText(name)));
    System.out.print("\n" + driver.findElement(By.linkText(name)) + "\n\n");

    exitAdminPanel();
  }
}
