package litecart_without_page_object;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class CountriesAndZonesInAlphabetOrderTest extends TestBase {

  @Test
  public void test009_1() {
    System.out.print("\n\n***** Внутри метода test009_1() *****\n\n");

    goToAdminPanel();

    System.out.println("Захожу в раздел Countries");
    driver.get("http://localhost/litecart_using_page_object/admin/?app=countries&doc=countries");
    int amountOfCountries = driver.findElements(By.xpath("//td[@id='content']//tbody/tr[@class='row']")).size();
    System.out.print("Количество стран = " + amountOfCountries + "\n\n");

    for (int i = 1; i <= amountOfCountries; i++) {
      // if (i>5 && i< 220) { continue; } // использую для отладки
      System.out.println(i + "-ая итерация внешнего цикла");
      String currentCountry = driver.findElement(By.xpath("//td[@id='content']//tbody/tr[" + (i+1) + "]//a")).getText();
      System.out.println("Текущая страна: " + currentCountry);
      if (i < amountOfCountries) {
        String nextCountry = driver.findElement(By.xpath("//td[@id='content']//tbody/tr[" + (i+2) + "]//a")).getText();
        // if (i == 3) { nextCountry = "Aaaa country test"; } // для проверки падения теста
        System.out.println("Следующая страна: " + nextCountry);
        System.out.println("Проверяю что обе страны расположены в алфавитном порядке");
        Assert.assertTrue(currentCountry.compareTo(nextCountry) < 0);
      }

      // Проверю количество зон у текущей страны. И если оно отлично от нуля, то открою
      // страницу этой страны и там проверю что зоны расположены в алфавитном порядке:
      int amountOfZonesInCurrentCountry = Integer.parseInt(
          driver.findElement(By.xpath("//td[@id='content']//tbody/tr[" + (i+1) + "]/td[6]")).getText()
      );
      System.out.print("Количество зон в текущей стране = " + amountOfZonesInCurrentCountry + "\n\n");
      if (amountOfZonesInCurrentCountry != 0) {
        driver.findElement(By.xpath("//td[@id='content']//tbody/tr[" + (i+1) + "]/td[5]/a")).click();
        System.out.print("  Открыл карточку текущей страны (" + currentCountry + ")\n\n");
        for (int j = 1; j <= amountOfZonesInCurrentCountry; j++) {
          System.out.println("  " + j + "-ая итерация внутреннего цикла");
          String currentZone = driver.findElement(By.xpath("//table[@id='table-zones']/tbody/tr[" + (j+1) + "]/td[3]")).getText();
          System.out.println("  Текущая зона: " + currentZone);
          if (j < amountOfZonesInCurrentCountry) {
            String nextZone = driver.findElement(By.xpath("//table[@id='table-zones']/tbody/tr[" + (j+2) + "]/td[3]")).getText();
            // if (j == 5) { nextZone = "Aaaa zone test"; } // для проверки падения теста
            System.out.println("  Следующая зона: " + nextZone);
            System.out.print("  Проверяю что обе зоны расположены в алфавитном порядке\n\n");
            Assert.assertTrue(currentZone.compareTo(nextZone) < 0);
          }
        }
        System.out.print("\n  Проверку зон для текущей страны закончил. Возвращаюсь к списку стран\n\n");
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']")).click();
      }
    }

    exitAdminPanel();
  }
}
