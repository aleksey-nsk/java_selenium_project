package litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntry;
import java.util.Set;

public class GetBrowserLogsTest extends TestBase {

  @Test
  public void test017() {
    System.out.print("\n\n***** Внутри метода test017() *****\n\n");

    // Какие логи доступны:
    Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
    System.out.println("Доступны следующие логи: " + logTypes); // [browser, driver, client]

    goToAdminPanel();

    System.out.println("Открываю категорию которая содержит товары");
    driver.get("http://localhost/litecart_using_page_object/admin/?app=catalog&doc=catalog&category_id=1");
    int amountOfLines = driver.findElements(By.xpath("//form[@name='catalog_form']//tbody/tr")).size();
    int amountOfProducts = amountOfLines - 5;
    System.out.print("Количество товаров = " + amountOfProducts + "\n\n");

    for (int i = 1; i <= amountOfProducts; i++) {
      System.out.println("Кликаю по товару " + i);
      driver.findElement(By.xpath("//form[@name='catalog_form']//tr[" + (i+4) + "]/td[3]/a")).click();
      System.out.println("Проверю, не появились ли в логе браузера сообщения (любого уровня)");
      int amountOfLogs = 0; // количество записей в логах

      for (LogEntry logEntry : driver.manage().logs().get("browser").getAll()) {
        System.out.println("logEntry = " + logEntry);
        amountOfLogs++;
      }

      // amountOfLogs = 5; // для проверки падения теста
      System.out.println("Всего записей в логах браузера: " + amountOfLogs);
      Assert.assertTrue(amountOfLogs == 0);
      System.out.print("Возвращаюсь обратно к списку товаров\n\n");
      driver.findElement(By.xpath("//button[@name='cancel']")).click();
    }

    exitAdminPanel();
  }
}
