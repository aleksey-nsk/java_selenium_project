package litecart_using_page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class HomePage extends Page {

  @FindBy(xpath = "//div[@id='cart']/a[@class='link']") private WebElement openBasketButton;
  @FindBy(xpath = "//div[@id='box-most-popular']//ul/li[1]") private WebElement firstProduct;
  @FindBy(xpath = "//div[@class='middle']/div[@class='content']/div[@class='box']") private List<WebElement> merchandiseCategories;
  @FindBy(xpath = "//ul[@class='list-vertical']/li[4]/a") private WebElement logoutButton;
  @FindBy(name = "email") private WebElement email;
  @FindBy(name = "password") private WebElement password;
  @FindBy(name = "login") private WebElement loginButton;

  public HomePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    System.out.println("Открываю домашнюю страницу магазина");
    driver.get("http://localhost/litecart/en/");
    (new WebDriverWait(driver, 3)).until(ExpectedConditions.titleIs("Online Store | My Store"));
  }

  public void firstProductOpen() {
    System.out.println("Нажимаю на первый товар в категории Most Popular");
    firstProduct.click();
  }

  public void openBasket() {
    System.out.println("Жму кнопку открытия корзины");
    openBasketButton.click();
  }

  // Метод для определения количества категорий товаров:
  public int amountMerchandiseCategories() {
    return merchandiseCategories.size();
  }

  // Метод для определения количества товаров в текущей категории:
  public int amountMerchandisesInCategory(int n) {
    WebElement currentMerchandiseCategory = merchandiseCategories.get(n);
    return currentMerchandiseCategory.findElements(By.xpath(".//ul[@class='listing-wrapper products']/li")).size();
  }

  // Метод для определения заголовка текущей категории товаров:
  public String currentCategoryTitle(int n) {
    return merchandiseCategories.get(n).findElement(By.xpath("./h3[@class='title']")).getText();
  }

  // Метод для определения названия текущего товара:
  public String currentProductName(int i, int j) {
    return merchandiseCategories
        .get(i)
        .findElement(By.xpath(".//ul[@class='listing-wrapper products']/li["+(j+1)+"]//div[@class='name']"))
        .getText();
  }

  // Метод возвращающий количество стикеров:
  public int stickerAmount(int i, int j) {
    return merchandiseCategories
        .get(i)
        .findElements(By.xpath(".//ul[@class='listing-wrapper products']/li["+(j+1)+"]//div[contains(@class,'sticker')]"))
        .size();
  }

  public void logOut() {
    System.out.println("Выхожу из клиентской учётки (Logout)");
    logoutButton.click();
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
        By.xpath("//div[@class='notice success']"),
        "You are now logged out."
    ));
  }

  public void logIn(long random) {
    System.out.println("Захожу в клиентскую учётку (Login)");
    email.sendKeys("email" + random + "@test.ru");
    password.sendKeys("qwerty");
    loginButton.click();
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
        By.xpath("//div[@class='notice success']"),
        "You are now logged in as Firstname-" + random + " Lastname."
    ));
  }
}
