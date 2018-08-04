package litecart_using_page_object.UserRegistrationTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

// Класс для создания
// объекта-страницы который возвращает список идентификаторов клиентов:
public class CustomerListPage extends Page {

  public CustomerListPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public CustomerListPage open() {
    // После входа в админскую панель открываем страницу со списком клиентов:
    driver.get("http://localhost/litecart_using_page_object/admin/?app=customers&doc=customers");
    return this;
  }

  @FindBy(css = "table.dataTable tr.row")
  private List<WebElement> customerRows;

  public Set<String> getCustomerIds() {
    // Возвращаем строки таблицы, которые содержат нужную инфу
    // и у каждой строки берем текст третьего столбца (идентификатор клиента):
        /*
        return driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
        */
    return customerRows.stream()
        .map(e -> e.findElements(By.tagName("td")).get(2).getText())
        .collect(toSet());
  }
}
