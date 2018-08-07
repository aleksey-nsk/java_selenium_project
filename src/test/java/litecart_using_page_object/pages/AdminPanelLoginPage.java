package litecart_using_page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс для создания
// объекта-страницы который предоставляет возможность выполнить вход в систему:
public class AdminPanelLoginPage extends Page {

  public AdminPanelLoginPage(WebDriver driver) {
    super(driver);
  }

  public AdminPanelLoginPage open() {
    driver.get("http://localhost/litecart/admin"); // открываю админскую панель
    return this;
  }

  // Проверка вдруг логин уже выполнен (чтобы потом повторно его не выполнять):
  public boolean isOnThisPage() {
    return driver.findElements(By.id("box-login")).size() > 0;
  }

  public AdminPanelLoginPage enterUsername(String username) {
    driver.findElement(By.name("username")).sendKeys(username);
    return this;
  }

  public AdminPanelLoginPage enterPassword(String password) {
    driver.findElement(By.name("password")).sendKeys(password);
    return this;
  }

  // После нажатия кнопки мы уже уйдём на другую страницу:
  public void submitLogin() {
    driver.findElement(By.name("login")).click();
    wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
  }
}
