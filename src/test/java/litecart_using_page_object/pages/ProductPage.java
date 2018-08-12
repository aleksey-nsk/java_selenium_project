package litecart_using_page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends Page {

  @FindBy(xpath = "//button[@name='add_cart_product']") private WebElement addToBasketButton;
  @FindBy(xpath = "//span[@class='quantity']") private WebElement amountProductsInBasket;

  public ProductPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void selectSizeIfPresent() {
    if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
      System.out.println("Поле Size присутствует");
      driver.findElement(By.xpath("//select[@name='options[Size]']")).sendKeys("Small");
    } else {
      System.out.println("Поле Size отсутствует");
    }
  }

  public void waitForCounterUpdating(int oldAmount) {
    System.out.println("Метод ожидания обновления счётчика товаров в корзине");
    int newAmount = oldAmount + 1;
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
        By.xpath("//span[@class='quantity']"),
        String.valueOf(newAmount)
    ));
  }

  public void addProductToBasket() {
    System.out.println("Нажимаю кнопку добавления товара в корзину");
    addToBasketButton.click();
  }

  // Метод возвращающий текущее количество товаров в корзине:
  public int currentAmountProductsInBasket() {
    return Integer.parseInt(amountProductsInBasket.getText());
  }
}
