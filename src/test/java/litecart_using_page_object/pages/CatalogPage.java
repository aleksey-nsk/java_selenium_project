package litecart_using_page_object.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class CatalogPage extends Page {

  @FindBy(xpath = "//form[@name='catalog_form']//tbody/tr")
  private List<WebElement> lines;

  public CatalogPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    System.out.println("Открываю каталог");
    driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    (new WebDriverWait(driver, 3)).until(ExpectedConditions.titleIs("Catalog | My Store"));
  }

  public void isProductAppear(String productName) {
    System.out.println("Убедимся что созданный товар появился в каталоге:");
    Assert.assertTrue(isElementPresent(driver, By.linkText(productName)));
    System.out.println(driver.findElement(By.linkText(productName)));
  }

  // Метод определяющий количество товаров в каталоге:
  public int amountOfProducts() {
    int linesAmount = lines.size();
    int productsAmount = linesAmount - 5;
    System.out.println("Количество товаров в каталоге = " + productsAmount);
    return productsAmount;
  }

  public void clickProductAndGetBrowserLogs(int n) {
    System.out.println("Кликаю по товару " + n);
    driver.findElement(By.xpath("//form[@name='catalog_form']//tr["+(n+4)+"]/td[3]/a")).click();
    System.out.println("  Проверю, не появились ли в логе браузера сообщения (любого уровня)");
    int amountOfLogs = 0; // количество записей в логах

    for (LogEntry logEntry : driver.manage().logs().get("browser").getAll()) {
      System.out.println("logEntry = " + logEntry);
      amountOfLogs++;
    }

    // amountOfLogs = 5; // для проверки падения теста
    System.out.println("  Всего записей в логах браузера: " + amountOfLogs);
    Assert.assertTrue(amountOfLogs == 0);
    System.out.println("  Возвращаюсь обратно к списку товаров");
    driver.findElement(By.xpath("//button[@name='cancel']")).click();
  }
}
