package different_tests.PageObjectExample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Класс содержащий описание страницы почтового ящика:
public class MailBoxPage {

  // Конструктор класса:
  public MailBoxPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//td[@id='td_header_right1']")
  private WebElement userProfile;

  @FindBy(xpath = "//a[@href='logout']")
  private WebElement buttonLogout;

  public String getUserMail() {
    String userMail = userProfile.getText();
    return userMail;
  }

  public void userLogout() {
    buttonLogout.click();
  }
}
