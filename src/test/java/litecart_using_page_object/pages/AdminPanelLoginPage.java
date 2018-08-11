package litecart_using_page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPanelLoginPage extends Page {

  @FindBy(xpath = "//input[@name='username']")
  private WebElement usernameField;

  @FindBy(xpath = "//input[@name='password']")
  private WebElement passwordField;

  @FindBy(xpath = "//button[@name='login']")
  private WebElement loginButton;

  public AdminPanelLoginPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public AdminPanelLoginPage open() {
    System.out.println("Открываю страницу входа в админку");
    driver.get("http://localhost/litecart/admin");
    return this;
  }

  public boolean isOnThisPage() {
    System.out.println("Метод для проверки того что логин ещё не выполнен");
    return isElementPresent(driver, By.xpath("//div[@id='box-login']"));
  }

  public AdminPanelLoginPage enterUsername(String username) {
    System.out.println("Ввожу логин");
    usernameField.sendKeys(username);
    return this;
  }

  public AdminPanelLoginPage enterPassword(String password) {
    System.out.println("Ввожу пароль");
    passwordField.sendKeys(password);
    return this;
  }

  // После нажатия кнопки Login мы уже уйдём на другую страницу:
  public void clickLoginButton() {
    System.out.println("Нажимаю кнопку Login");
    loginButton.click();
    (new WebDriverWait(driver, 5)).until(
        (WebDriver d) -> d.findElement(By.id("box-apps-menu"))
    );
  }
}
