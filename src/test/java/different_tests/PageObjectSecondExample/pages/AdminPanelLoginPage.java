package different_tests.PageObjectSecondExample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Данный класс описывает страницу входа в систему:
public class AdminPanelLoginPage extends Page {

  public AdminPanelLoginPage(WebDriver driver) {
    super(driver);
  }

  public AdminPanelLoginPage open() {
    System.out.println("Метод для открытия панели админа");
    driver.get("http://localhost/litecart/admin");
    return this;
  }

  public boolean isOnThisPage() {
    System.out.println("Метод для проверки того, что логин ещё не выполнен");
    return driver.findElements(By.id("box-login")).size() > 0;
  }

  public AdminPanelLoginPage enterUsername(String username) {
    System.out.println("Метод для ввода логина");
    driver.findElement(By.name("username")).sendKeys(username);
    return this;
  }

  public AdminPanelLoginPage enterPassword(String password) {
    System.out.println("Метод для ввода пароля");
    driver.findElement(By.name("password")).sendKeys(password);
    return this;
  }

  // После нажатия кнопки Login мы уже уйдём на другую страницу:
  public void submitLogin() {
    System.out.println("Метод для нажатия кнопки Login");
    driver.findElement(By.name("login")).click();
    wait.until( (WebDriver d) -> d.findElement(By.id("box-apps-menu")) );
  }
}
