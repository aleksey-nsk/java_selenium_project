package litecart_without_page_object;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class PassAllAdminSectionTest extends TestBase {

  @Test
  public void test007() {
    System.out.print("\n\n***** Внутри метода test007() *****\n\n");

    goToAdminPanel();

    int amountMainMenuItems = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).size();
    System.out.println("Количество пунктов в главном меню = " + amountMainMenuItems);

    for (int i = 1; i <= amountMainMenuItems; i++) {
      // if (i>4 && i<15) { continue; } // использую для отладки
      System.out.println("Нажимаю пункт главного меню #" + i);
      driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li["+i+"]")).click();

      if (isElementPresent(driver, By.xpath("//li[@class='selected']/ul[@class='docs']"))) {
        int amountSubMenuItems = driver.findElements(By.xpath("//li[@class='selected']/ul[@class='docs']/li")).size();
        System.out.println("  количество пунктов подменю = " + amountSubMenuItems);
        for (int j = 1; j <= amountSubMenuItems; j++) {
          driver.findElement(By.xpath("//li[@class='selected']/ul[@class='docs']/li["+j+"]")).click();
          System.out.println("  подменю" + i + "." + j + " - проверяю наличие заголовка");
          Assert.assertTrue(isElementPresent(driver, By.xpath("//td[@id='content']/h1")));
        }
      } else {
        System.out.println("  подменю отсутствует. Проверяю наличие заголовка в пункте главного меню");
        Assert.assertTrue(isElementPresent(driver, By.xpath("//td[@id='content']/h1")));
      }
    }

    exitAdminPanel();
  }
}
