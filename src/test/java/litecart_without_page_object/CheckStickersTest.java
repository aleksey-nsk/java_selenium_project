/*
Тест, который проверяет наличие стикеров у всех товаров
в учебном приложении litecart на главной странице.
Стикеры - это полоски в левом верхнем углу изображения товара,
на которых написано New или Sale или что-нибудь другое.
Сценарий должен проверять, что у каждого товара имеется ровно один стикер.

Описание теста:
- открыть главную страницу
- проверить что у каждого товара имеется ровно 1 стикер
*/

package litecart_without_page_object;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CheckStickersTest {
  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    driver = new InternetExplorerDriver(); // инициализация драйвера
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
  }

  @Test
  public void action() {
    // ----------------------------------------------------
    // Открыть главную страницу
    // ----------------------------------------------------
    driver.get("http://localhost/litecart/");
    (new WebDriverWait(driver, 3)).until(ExpectedConditions.titleIs("Online Store | My Store"));

    // ----------------------------------------------------
    // Найти все категории товаров.
    // Просмотреть товары в каждой категории и убедиться,
    // что у каждого товара имеется ровно 1 стикер
    // ----------------------------------------------------
    int amountMerchandiseCategories = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']")).size();
    System.out.println("Количество категорий товаров = " + amountMerchandiseCategories);
    for (int i = 0; i < amountMerchandiseCategories; i++) {
      int iIncrement = i + 1;
      String categoryTitle = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']/h3[@class='title']")).get(i).getText();
      System.out.println("\nКАТЕГОРИЯ №" + iIncrement + "\nЗаголовок = " + categoryTitle);

      WebElement merchandiseCategory = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']")).get(i);
      int amountMerchandiseInCategory = merchandiseCategory.findElements(By.xpath(".//ul[@class='listing-wrapper products']/li")).size();
      System.out.println("Количество товаров в текущей категории = " + amountMerchandiseInCategory);
      for (int j = 0; j < amountMerchandiseInCategory; j++) {
        int jIncrement = j + 1;
        System.out.print("Категория/товар: " + iIncrement + "/" + jIncrement + ", ");

        merchandiseCategory = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']")).get(i);
        String merchandiseName = merchandiseCategory.findElement(By.xpath(".//ul[@class='listing-wrapper products']/li[" + jIncrement + "]//div[@class='name']")).getText();
        System.out.print("название = " + merchandiseName + ". ");

        System.out.print("Проверяю что стикер один... ");
        merchandiseCategory = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']")).get(i);
        int stickerAmount = merchandiseCategory.findElements(By.xpath(".//ul[@class='listing-wrapper products']/li[" + jIncrement + "]//div[contains(@class,'sticker')]")).size();
        System.out.println("Количество стикеров = " + stickerAmount);
        Assert.assertTrue(stickerAmount == 1); // проверяю что стикер один
      }
    }
  }

  @AfterClass
  public static void stop() {
    driver.quit();
    driver = null;
  }
}
