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
  private RegistrationPage registrationPage;
  private AddNewProductPage addNewProductPage;
  private CatalogPage catalogPage;

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
    registrationPage = new RegistrationPage(driver);
    addNewProductPage = new AddNewProductPage(driver);
    catalogPage = new CatalogPage(driver);
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

  public void checkStickers() {
    System.out.println("\nПРОВЕРИТЬ ЧТО У КАЖДОГО ТОВАРА КОЛИЧЕСТВО СТИКЕРОВ 0 ИЛИ 1");
    homePage.open();
    int amountMerchandiseCategories = homePage.amountMerchandiseCategories();
    System.out.println("Количество категорий товаров = " + amountMerchandiseCategories);
    for (int i = 0; i < amountMerchandiseCategories; i++) {
      System.out.println("\nКатегория №" + (i+1) + "\nЗаголовок = " + homePage.currentCategoryTitle(i));
      int amountMerchandisesInCategory = homePage.amountMerchandisesInCategory(i);
      System.out.println("Количество товаров в текущей категории = " + amountMerchandisesInCategory);
      for (int j = 0; j < amountMerchandisesInCategory; j++) {
        System.out.print("Категория/товар: " + (i+1) + "/" + (j+1) + ", ");
        System.out.print("название = " + homePage.currentProductName(i,j) + ". Проверяю что стикеров один или ноль: ");
        int stickerAmount = homePage.stickerAmount(i,j);
        // stickerAmount = 3; // для проверки падения теста
        System.out.println("количество стикеров = " + stickerAmount);
        Assert.assertTrue(stickerAmount==0 || stickerAmount==1);
      }
    }
  }

  public void customerRegistration() {
    System.out.println("\nМЕТОД ДЛЯ РЕГИСТРАЦИИ НОВОГО КЛИЕНТА");
    registrationPage.open();
    System.out.println("Заполняю форму регистрации нового клиента");
    long random = System.currentTimeMillis();
    registrationPage.firstnameEnter(random);
    registrationPage.lastnameEnter();
    registrationPage.addressEnter();
    registrationPage.postcodeEnter();
    registrationPage.cityEnter();
    registrationPage.selectCountry("United States");
    registrationPage.selectZone("KS" /*штат Канзас*/);
    registrationPage.emailEnter(random);
    registrationPage.phoneEnter();
    registrationPage.passwordEnter();
    registrationPage.confirmedPasswordEnter();
    registrationPage.createAccountButtonClick();
    homePage.logOut();
    homePage.logIn(random);
    homePage.logOut();
  }

  public void addProduct() {
    System.out.println("\nМЕТОД ДЛЯ ДОБАВЛЕНИЯ НОВОГО ТОВАРА");
    String productName = "Test Duck " + System.currentTimeMillis();
    addNewProductPage.open();

    addNewProductPage.tabGeneralOpen(); // открываю вкладку General
    addNewProductPage.statusEnabledClick();
    addNewProductPage.statusEnabledClick();
    addNewProductPage.nameEnter(productName);
    addNewProductPage.codeEnter();
    addNewProductPage.genderUnisexCheck();
    addNewProductPage.quantityEnter();
    addNewProductPage.uploadImage();
    addNewProductPage.dateValidFromEnter();
    addNewProductPage.dateValidToEnter();

    addNewProductPage.tabInformationOpen(); // открываю вкладку Information
    addNewProductPage.manufacturerSelect();
    addNewProductPage.keywordsEnter();
    addNewProductPage.shortDescriptionEnter();
    addNewProductPage.descriptionEnter(productName);
    addNewProductPage.headTitleEnter(productName);
    addNewProductPage.metaDescriptionEnter();

    addNewProductPage.tabPricesOpen(); // открываю вкладку Prices
    addNewProductPage.purchasePriceEnter();
    addNewProductPage.currencyCodeSelect();
    addNewProductPage.priceUsdEnter();

    addNewProductPage.saveProduct();
    catalogPage.isProductAppear(productName);
  }
}
