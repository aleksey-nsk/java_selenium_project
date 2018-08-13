package litecart_using_page_object.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends Page {

  public CatalogPage(WebDriver driver) {
    super(driver);
  }

  public void isProductAppear(String productName) {
    System.out.println("Убедимся что созданный товар появился в каталоге:");
    Assert.assertTrue(isElementPresent(driver, By.linkText(productName)));
    System.out.println(driver.findElement(By.linkText(productName)));
  }
}
