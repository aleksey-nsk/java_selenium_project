package litecart_using_page_object.app;

import litecart_using_page_object.pages.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

// В тестах вообще нигде не видно что используется Селениум.
// Внутрь класса Application прячем все технические подробности.
// Только в этом классе видим что используется Селениум.
// Именно тут создаётся драйвер, и здесь же он используется:
public class Application {

  private WebDriver driver;
  private HomePage homePage;
  private ProductPage productPage;
  private BasketPage basketPage;
  private AdminPanelLoginPage adminPanelLoginPage;
  private AdminPage adminPage;

  public Application() {
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
    homePage = new HomePage(driver);
    productPage = new ProductPage(driver);
    basketPage = new BasketPage(driver);
    adminPanelLoginPage = new AdminPanelLoginPage(driver);
    adminPage = new AdminPage(driver);
  }

  public void quit() {
    driver.quit();
  }

  public void addOneProductToBasket() {
    System.out.println("\nМЕТОД ДЛЯ ДОБАВЛЕНИЯ 1 ТОВАРА В КОРЗИНУ");
    homePage.open();
    homePage.firstProductOpen();
    int oldAmountProductsInBasket = productPage.currentAmountProductsInBasket();
    productPage.selectSizeIfPresent();
    productPage.addProductToBasket();
    productPage.waitForCounterUpdating(oldAmountProductsInBasket);
  }

  public void deleteAllProductsFromBasket() {
    System.out.println("\nМЕТОД ДЛЯ УДАЛЕНИЯ ВСЕХ ТОВАРОВ ИЗ КОРЗИНЫ");
    homePage.open();
    homePage.openBasket();
    int oldAmountProductsInBasket = basketPage.currentAmountProductsInBasket();
    System.out.println("Начальное количество товаров в корзине = " + oldAmountProductsInBasket);
    for (int i = 1; i <= oldAmountProductsInBasket; i++) {
      int oldLines = basketPage.currentLinesInTable();
      basketPage.removeOneProduct();
      if (i < oldAmountProductsInBasket) {
        basketPage.waitForTableUpdating(oldLines);
      } else {
        basketPage.waitForEmptyBasketMessage();
      }
    }
  }

  public void openAdminPanel() {
    System.out.println("\nМЕТОД ДЛЯ ВХОДА В ПАНЕЛЬ АДМИНА");
    if (adminPanelLoginPage.open().isOnThisPage()) {
      adminPanelLoginPage.enterUsername("admin").enterPassword("admin").clickLoginButton();
    }
  }

  public void exitAdminPanel() {
    System.out.println("\nМЕТОД ДЛЯ ВЫХОДА ИЗ ПАНЕЛИ АДМИНА");
    adminPage.logOut();
  }

  public void passAllAdminSections() {
    System.out.println("\nПРОЙТИ ВСЕ РАЗДЕЛЫ АДМИНКИ И ПРОВЕРИТЬ НАЛИЧИЕ ЗАГОЛОВКОВ h1");
    int amountMainMenuItems = adminPage.amountMainMenuItems();
    System.out.println("Количество пунктов в главном меню = " + amountMainMenuItems);
    for (int i = 1; i <= amountMainMenuItems; i++) {
      // if (i>4 && i<15) { continue; } // использую для отладки
      adminPage.clickMainMenuItem(i);
      if (adminPage.isSubMenuPresent()) {
        int amountSubMenuItems = adminPage.amountSubMenuItems();
        System.out.println("  количество пунктов подменю = " + amountSubMenuItems);
        for (int j = 1; j <= amountSubMenuItems; j++) {
          adminPage.clickSubMenuItem(j);
          System.out.println("  подменю" + i + "." + j + " - проверяю наличие заголовка");
          Assert.assertTrue(adminPage.isHeaderPresent());
        }
      } else {
        System.out.println("  подменю отсутствует. Проверяю наличие заголовка в пункте главного меню");
        Assert.assertTrue(adminPage.isHeaderPresent());
      }
    }
  }
}
