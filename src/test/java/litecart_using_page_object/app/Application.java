package litecart_using_page_object.app;

import litecart_using_page_object.pages.BasketPage;
import litecart_using_page_object.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import litecart_using_page_object.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class Application {

  private WebDriver driver;

  private MainPage mainPage;
  private ProductPage productPage;
  private BasketPage basketPage;

  public Application() {
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();

    mainPage = new MainPage(driver);
    productPage = new ProductPage(driver);
    basketPage = new BasketPage(driver);
  }

  public void quit() {
    driver.quit();
    driver = null;
  }

  public void addOneProductToBasket() {
    System.out.println("\nМетод для добавления 1 товара в корзину");
    mainPage.open();
    System.out.println("Открываю первый товар из списка Most Popular");
    mainPage.firstProduct.click();
    int oldAmountProductsInBasket = Integer.parseInt(productPage.amountProductsInBasket.getText());
    productPage.selectSizeIfPresent();
    System.out.println("Жму кнопку для добавления в корзину");
    productPage.addToBasketButton.click();
    System.out.println("Жду пока счётчик товаров в корзине обновится");
    productPage.waitForCounterUpdating(oldAmountProductsInBasket);
  }

  public void deleteAllProductsFromBasket() {
    System.out.println("\nМетод удаления всех товаров из корзины");
    mainPage.open();
    System.out.println("Жму кнопку чтобы открыть корзину");
    mainPage.openBasketButton.click();
    int amountProductsInBasket = basketPage.productsInBasket.size();
    System.out.println("Начальное количество товаров в корзине = " + amountProductsInBasket);

    for (int i = 1; i <= amountProductsInBasket; i++) {
      int oldLines = basketPage.linesInTable.size();
      System.out.print("Удаляю один товар. ");
      basketPage.removeProductButton.click();
      if (i < amountProductsInBasket) {
        basketPage.waitForTableUpdating(oldLines);
      } else {
        basketPage.waitForEmptyBasketMessage();
      }
    }
  }
}
