package litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckStickersTest extends TestBase {

  @Test
  public void test008() {
    System.out.print("\n\n***** Внутри метода test008() *****\n\n");

    System.out.println("Открываю главную страницу");
    driver.get("http://localhost/litecart_using_page_object/");
    (new WebDriverWait(driver, 3)).until(ExpectedConditions.titleIs("Online Store | My Store"));
    int amountMerchandiseCategories = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']")).size();
    System.out.println("Количество категорий товаров = " + amountMerchandiseCategories);

    for (int i = 0; i < amountMerchandiseCategories; i++) {
      String categoryTitle = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']/h3[@class='title']")).get(i).getText();
      System.out.println("\nКАТЕГОРИЯ №" + (i+1) + "\nЗаголовок = " + categoryTitle);
      WebElement merchandiseCategory = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']")).get(i);
      int amountMerchandiseInCategory = merchandiseCategory.findElements(By.xpath(".//ul[@class='listing-wrapper products']/li")).size();
      System.out.println("Количество товаров в текущей категории = " + amountMerchandiseInCategory);

      for (int j = 0; j < amountMerchandiseInCategory; j++) {
        System.out.print("Категория/товар: " + (i+1) + "/" + (j+1) + ", ");
        merchandiseCategory = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']")).get(i);
        String merchandiseName = merchandiseCategory.findElement(By.xpath(".//ul[@class='listing-wrapper products']/li[" + (j+1) + "]//div[@class='name']")).getText();
        System.out.print("название = " + merchandiseName + ". ");

        System.out.print("Проверяю что стикеров один или ноль: ");
        merchandiseCategory = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content']/div[@class='box']")).get(i);
        int stickerAmount = merchandiseCategory.findElements(By.xpath(".//ul[@class='listing-wrapper products']/li[" + (j+1) + "]//div[contains(@class,'sticker')]")).size();
        // stickerAmount = 3; // для проверки падения теста
        System.out.println("количество стикеров = " + stickerAmount);
        Assert.assertTrue(stickerAmount==0 || stickerAmount==1);
      }
    }
  }
}
