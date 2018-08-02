package litecart_without_page_object;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;

public class LinksInNewWindowTest extends TestBase {

  public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
    System.out.println("Метод для ожидания появления нового окна");
    return new ExpectedCondition<String>() {
      @Override
      public String apply(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(oldWindows);
        return handles.size() > 0 ? handles.iterator().next() : null;
      }
    };
  }

  @Test
  public void test014() throws InterruptedException {
    System.out.print("\n\n***** Внутри метода test014() *****\n\n");

    goToAdminPanel();

    System.out.println("Захожу в раздел Countries");
    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[3]")).click();
    System.out.println("Например, открою страницу редактирования Бразилии");
    driver.findElement(By.xpath("//form[@name='countries_form']//tbody/tr[31]/td[7]/a")).click();
    String originalTitle = driver.getTitle();
    System.out.println("Заголовок страницы редактирования = " + originalTitle);
    System.out.println("\nИщу ссылки с иконкой в виде квадратика со стрелкой");
    int amountOfExternalLinks = driver.findElements(By.xpath("//i[@class='fa fa-external-link']")).size();
    System.out.println("  всего таких ссылок: " + amountOfExternalLinks);
    System.out.println("  убедимся что все эти ссылки открываются в новом окне");

    for (int i = 0; i < amountOfExternalLinks; i++) {
      System.out.println("\nПроверяю текущую ссылку номер: " + (i+1) );

      System.out.print("  запоминаю идентификатор текущего окна: ");
      String currentWindow = driver.getWindowHandle();
      System.out.println("currentWindow = " + currentWindow);

      System.out.print("  запоминаю идентификаторы уже открытых окон: ");
      Set<String> existingWindows = driver.getWindowHandles();
      System.out.println("existingWindows = " + existingWindows);

      System.out.println("  кликаю ссылку которая открывает новое окно");
      driver.findElements(By.xpath("//i[@class='fa fa-external-link']")).get(i).click();

      System.out.print("  жду появления нового окна с новым идентификатором...\n    ");
      String newWindow = (new WebDriverWait(driver, 5)).until(anyWindowOtherThan(existingWindows));
      System.out.println("    появилось! newWindow = " + newWindow);

      System.out.println("  переключаюсь в новое окно");
      driver.switchTo().window(newWindow);

      System.out.println("  убедимся что переключились куда надо...");
      Set<String> newExistingWindows = driver.getWindowHandles();
      System.out.println("    newExistingWindows = " + newExistingWindows);
      String newCurrentWindow = driver.getWindowHandle();
      // newCurrentWindow = "CDwindow-00112233"; // для проверки падения теста
      System.out.println("    newCurrentWindow = " + newCurrentWindow);
      Assert.assertEquals(newWindow, newCurrentWindow);
      // Хочу немного задержаться в открытом окне:
      if (isElementPresent(driver, By.xpath("//div[@id='thisElementIsNotExist']"))) {
        System.out.println("Эту надпись мы никогда не увидим!");
      }

      System.out.println("  закрываю это новое окно");
      driver.close();

      System.out.println("  возвращаюсь в исходное окно");
      driver.switchTo().window(currentWindow);

      System.out.println("  проверю заголовок страницы...");
      String finalTitle = driver.getTitle();
      // finalTitle = "Test Title"; // для проверки падения теста
      System.out.println("    ОР = " + originalTitle);
      System.out.println("    ФР = " + finalTitle);
      Assert.assertEquals(originalTitle, finalTitle);

      // if (i == 1) break; // использую для отладки
    }

    System.out.println("\nНичего редактировать не буду. Жму кнопку Cancel");
    driver.findElement(By.xpath("//button[@name='cancel']")).click();

    exitAdminPanel();
  }
}
