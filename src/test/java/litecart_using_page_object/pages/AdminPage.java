package litecart_using_page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class AdminPage extends Page {

  @FindBy(xpath = "//a[@title='Logout']") private WebElement logoutButton;
  @FindBy(xpath = "//ul[@id='box-apps-menu']/li") private List<WebElement> mainMenuItems;
  @FindBy(xpath = "//li[@class='selected']/ul[@class='docs']/li") private List<WebElement> subMenuItems;

  public AdminPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void logOut() {
    System.out.println("Нажимаю кнопку Logout");
    logoutButton.click();
    (new WebDriverWait(driver, 5)).until(
        (WebDriver d) -> d.findElement(By.id("box-login"))
    );
  }

  // Метод для определения количества пунктов в главном меню:
  public int amountMainMenuItems() {
    return mainMenuItems.size();
  }

  // Метод для определения количества пунктов в подменю:
  public int amountSubMenuItems() {
    return subMenuItems.size();
  }

  public void clickMainMenuItem(int n) {
    System.out.println("Нажимаю пункт главного меню #" + n);
    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li["+n+"]")).click();
  }

  // Метод для нажатия на n-ый пункт подменю:
  public void clickSubMenuItem(int n) {
    driver.findElement(By.xpath("//li[@class='selected']/ul[@class='docs']/li["+n+"]")).click();
  }

  // Метод для проверки наличия подменю:
  public boolean isSubMenuPresent() {
    return isElementPresent(driver, By.xpath("//li[@class='selected']/ul[@class='docs']"));
  }

  // Метод для проверки наличия заголовка h1
  public boolean isHeaderPresent() {
    return isElementPresent(driver, By.xpath("//td[@id='content']/h1"));
  }
}
