package litecart_using_page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {

  @FindBy(xpath = "//div[@id='cart']/a[@class='link']")
  private WebElement openBasketButton;

  @FindBy(xpath = "//div[@id='box-most-popular']//ul/li[1]")
  private WebElement firstProduct; // первый товар в категории Most Popular

  @FindBy(xpath = "//a[@title='Logout']")
  private WebElement logoutButton;

  public HomePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    System.out.println("Открываю домашнюю страницу магазина");
    driver.get("http://localhost/litecart/en/");
  }

  public void firstProductOpen() {
    System.out.println("Нажимаю на первый товар в категории Most Popular");
    firstProduct.click();
  }

  public void openBasket() {
    System.out.println("Жму кнопку открытия корзины");
    openBasketButton.click();
  }

  public void logOut() {
    System.out.println("Нажимаю кнопку Logout");
    logoutButton.click();
    (new WebDriverWait(driver, 5)).until(
        (WebDriver d) -> d.findElement(By.id("box-login"))
    );
  }
}
