package litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class CheckGeoZonesTest extends TestBase {

  @Test
  public void test009_2() {
    System.out.print("\n\n***** Внутри метода test009_2() *****\n\n");

    goToAdminPanel();

    System.out.println("Открываю раздел Geo Zones");
    driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    int amountOfCountries = driver.findElements(By.xpath("//td[@id='content']//tbody/tr[@class='row']")).size();
    System.out.print("Количество стран = " + amountOfCountries + "\n\n");

    for (int i = 1; i <= amountOfCountries; i++) {
      System.out.println(i + "-ая итерация внешнего цикла");
      String currentCountry = driver.findElement(By.xpath("//td[@id='content']//tbody/tr[" + (i+1) + "]/td[3]/a")).getText();
      System.out.println("Текущая страна: " + currentCountry);
      int amountOfZonesInCurrentCountry = Integer.parseInt(
          driver.findElement(By.xpath("//td[@id='content']//tbody/tr[" + (i+1) + "]/td[4]")).getText()
      );
      System.out.println("Количество зон в текущей стране = " + amountOfZonesInCurrentCountry);
      if (amountOfZonesInCurrentCountry != 0) {
        driver.findElement(By.xpath("//td[@id='content']//tbody/tr[" + (i+1) + "]/td[3]/a")).click();
        System.out.println("\n  Открыл карточку текущей страны (" + currentCountry + ")");

        for (int j = 1; j <= amountOfZonesInCurrentCountry; j++) {
          System.out.println("\n  " + j + "-ая итерация внутреннего цикла");
          String currentZone = driver.findElement(By.xpath("//table[@id='table-zones']/tbody/tr[" + (j+1) + "]/td[3]/select/option[@selected='selected']")).getText();
          System.out.println("  Текущая зона: " + currentZone);
          if (j < amountOfZonesInCurrentCountry) {
            String nextZone = driver.findElement(By.xpath("//table[@id='table-zones']/tbody/tr[" + (j+2) + "]/td[3]/select/option[@selected='selected']")).getText();
            // if (j == 5) { nextZone = "Aaaa zone test"; } // для проверки падения теста
            System.out.println("  Следующая зона: " + nextZone);
            System.out.println("  Проверяю что обе зоны расположены в алфавитном порядке");
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
