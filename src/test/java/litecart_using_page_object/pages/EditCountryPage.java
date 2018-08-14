package litecart_using_page_object.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;

public class EditCountryPage extends Page {

  @FindBy(xpath = "//i[@class='fa fa-external-link']")
  private List<WebElement> externalLinks;

  @FindBy(name = "cancel")
  private WebElement cancelButton;

  public EditCountryPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    System.out.println("Захожу в раздел Countries");
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    System.out.println("Для примера открою страницу редактирования Бразилии");
    driver.findElement(By.xpath("//form[@name='countries_form']//tbody/tr[31]/td[7]/a")).click();
    (new WebDriverWait(driver, 3)).until(ExpectedConditions.titleIs("Edit Country | My Store"));
  }

  // Метод определяющий количество ссылок с иконкой в виде квадратика со стрелкой:
  public int amountOfExternalLinks() {
    System.out.println("Ищу ссылки с иконкой в виде квадратика со стрелкой");
    int amount = externalLinks.size();
    System.out.println("  всего таких ссылок: " + amount);
    return amount;
  }

  private ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
    System.out.println("Метод для ожидания появления нового окна");
    return new ExpectedCondition<String>() {
      @Override
      public String apply(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(oldWindows);
        return handles.size() > 0 ? handles.iterator().next() : null;
      }
    };
  }

  public void checkCurrentExternalLink(int n) {
    System.out.println("Проверяю текущую ссылку номер: " + (n+1) );

    System.out.print("  запоминаю идентификатор текущего окна: ");
    String currentWindow = driver.getWindowHandle();
    System.out.println("currentWindow = " + currentWindow);

    System.out.print("  запоминаю идентификаторы уже открытых окон: ");
    Set<String> existingWindows = driver.getWindowHandles();
    System.out.println("existingWindows = " + existingWindows);

    System.out.println("  кликаю ссылку которая открывает новое окно");
    externalLinks.get(n).click();

    System.out.print("  жду появления нового окна с новым идентификатором\n    ");
    String newWindow = (new WebDriverWait(driver, 5)).until(anyWindowOtherThan(existingWindows));
    System.out.println("    появилось! newWindow = " + newWindow);

    System.out.println("  переключаюсь в новое окно");
    driver.switchTo().window(newWindow);

    System.out.println("  убедимся что переключились куда надо");
    Set<String> newExistingWindows = driver.getWindowHandles();
    System.out.println("    newExistingWindows = " + newExistingWindows);
    String newCurrentWindow = driver.getWindowHandle();
    System.out.println("    newCurrentWindow = " + newCurrentWindow);
    Assert.assertEquals(newWindow, newCurrentWindow);

    // Хочу немного задержаться в открытом окне:
    if (isElementPresent(driver, By.xpath("//div[@id='thisElementDoesNotExist']"))) {
      System.out.println("Эту надпись мы никогда не увидим!");
    }

    System.out.println("  закрываю это новое окно");
    driver.close();

    System.out.println("  возвращаюсь в исходное окно");
    driver.switchTo().window(currentWindow);
    (new WebDriverWait(driver, 3)).until(ExpectedConditions.titleIs("Edit Country | My Store"));
  }

  public void refuseToEdit() {
    System.out.println("Ничего редактировать не буду. Жму кнопку Cancel");
    cancelButton.click();
    (new WebDriverWait(driver, 3)).until(ExpectedConditions.titleIs("Countries | My Store"));
  }
}
