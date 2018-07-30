package litecart_without_page_object;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketTest extends TestBase {

  private void addOneProductToBasket() {
    System.out.println("\nМетод для добавления 1 товара в корзину");
    System.out.println("Открываю главную страницу магазина");
    driver.get("http://localhost/litecart/en/");
    System.out.println("Открываю первый товар из списка Most Popular");
    driver.findElement(By.xpath("//div[@id='box-most-popular']//ul/li[1]")).click();
    int currentAmount = Integer.parseInt(driver.findElement(By.xpath("//span[@class='quantity']")).getText());
    int increasedAmount = currentAmount + 1;

    // Для некоторых товаров необходимо указать размер:
    if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
      System.out.println("Поле Size присутствует");
      driver.findElement(By.xpath("//select[@name='options[Size]']")).sendKeys("Small");
    } else {
      System.out.println("Поле Size отсутствует");
    }

    System.out.println("Жму кнопку добавления в корзину");
    driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();

    System.out.println("Жду пока счётчик товаров в корзине обновится");
    // increasedAmount = 22; // для проверки падения теста
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
        By.xpath("//span[@class='quantity']"),
        String.valueOf(increasedAmount)
    ));
  }

  private void deleteAllProductsFromBasket() {
    System.out.println("\nМетод для удаления всех товаров из корзины");
    System.out.println("Открываю главную страницу магазина");
    driver.get("http://localhost/litecart/en/");
    System.out.println("Жму кнопку чтобы открыть корзину");
    driver.findElement(By.xpath("//div[@id='cart']/a[@class='link']")).click();
    int amountProductsInBasket = driver.findElements(By.xpath("//ul[@class='shortcuts']/li")).size();
    System.out.println("Начальное количество товаров в корзине = " + amountProductsInBasket);

    for (int i = 1; i <= amountProductsInBasket; i++) {
      int currentLines = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr")).size();
      int newLines = currentLines - 1;
      System.out.print("Удаляю один товар. ");
      driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();

      if (i < amountProductsInBasket) {
        System.out.println("Жду пока обновится таблица товаров");
        // newLines = 12; // для проверки падения теста
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfElementsToBe(
            By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr"),
            newLines
        ));
      } else {
        System.out.println("Жду сообщения о том что корзина пуста");
        String expectedMessage = "There are no items in your cart.";
        // expectedMessage = "Test Message"; // для проверки падения теста
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
            By.xpath("//div[@id='checkout-cart-wrapper']//em"),
            expectedMessage
        ));
      }
    }
  }

  @Test
  public void test013() {
    System.out.print("\n\n***** Внутри метода test013() *****\n\n");

    addOneProductToBasket();
    addOneProductToBasket();
    addOneProductToBasket();
    deleteAllProductsFromBasket();
  }
}
