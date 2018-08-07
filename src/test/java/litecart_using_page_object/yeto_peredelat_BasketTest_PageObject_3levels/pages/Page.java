package litecart_using_page_object.yeto_peredelat_BasketTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {

  protected WebDriver driver;

  public Page(WebDriver driver) {
    this.driver = driver;
  }

  // Метод проверяющий наличие элемента:
  public boolean isElementPresent(WebDriver driver, By locator) {
    return driver.findElements(locator).size() > 0;
  }
}
