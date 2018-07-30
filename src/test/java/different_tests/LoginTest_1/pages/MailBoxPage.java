package different_tests.LoginTest_1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// MailBoxPage — класс страницы, на которой
// находится почтовый ящик пользователя:
public class MailBoxPage {

  // Конструктор класса:
  public MailBoxPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public WebDriver driver;

  @FindBy(xpath = "//td[@id='td_header_right1']")
  private WebElement userProfile;

  @FindBy(xpath = "//a[@href='logout']")
  private WebElement buttonLogout;

  // Теперь опишем методы:

  public String getUserMail() {
    String userMail = userProfile.getText();
    return userMail;
  }

  public void userLogout() {
    buttonLogout.click();
  }
}
