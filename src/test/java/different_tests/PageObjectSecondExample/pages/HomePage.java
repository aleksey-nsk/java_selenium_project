package different_tests.PageObjectSecondExample.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Данный класс описывает домашнюю страницу приложения
// Попадаем на эту страницу после регистрации клиента
public class HomePage extends Page {

  public HomePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//ul[@class='list-vertical']/li[4]/a")
  public WebElement logoutButton;

  @FindBy(xpath = "//div[@class='notice success']")
  public WebElement logoutMessage;

  public void logOut() {
    System.out.println("Метод для выхода из созданной учётки");
    logoutButton.click();
    String actualMessage = logoutMessage.getText();
    Assert.assertEquals("You are now logged out.", actualMessage);
  }
}
