package litecart_using_page_object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends Page {

  @FindBy(name = "firstname") private WebElement firstname;
  @FindBy(name = "lastname") private WebElement lastname;
  @FindBy(name = "address1") private WebElement address;
  @FindBy(name = "postcode") private WebElement postcode;
  @FindBy(name = "city") private WebElement city;
  @FindBy(name = "email") private WebElement email;
  @FindBy(name = "phone") private WebElement phone;
  @FindBy(name = "password") private WebElement password;
  @FindBy(name = "confirmed_password") private WebElement confirmedPassword;
  @FindBy(name = "create_account") private WebElement createAccountButton;

  public RegistrationPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    System.out.println("Открываю страницу регистрации клиента");
    driver.get("http://localhost/litecart/en/create_account");
  }

  public void firstnameEnter(long random) { firstname.sendKeys("Firstname-" + random); }
  public void lastnameEnter() { lastname.sendKeys("Lastname"); }
  public void addressEnter() { address.sendKeys("Test Adress"); }
  public void postcodeEnter() { postcode.sendKeys("12345"); }
  public void cityEnter() { city.sendKeys("Test City"); }
  public void emailEnter(long random) { email.sendKeys("email" + random + "@test.ru"); }
  public void phoneEnter() { phone.sendKeys("+10123456789"); }
  public void passwordEnter() { password.sendKeys("qwerty"); }
  public void confirmedPasswordEnter() { confirmedPassword.sendKeys("qwerty"); }

  // Метод для выбора страны:
  public void selectCountry(String country) {
    driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
    driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(country + Keys.ENTER);
  }

  // Метод для выбора штата:
  public void selectZone(String zone) {
    driver.findElement(By.xpath("//select[@name='zone_code']")).click();
    driver.findElement(By.xpath(String.format("//select[@name='zone_code']/option[@value='%s']", zone))).click();
  }

  public void createAccountButtonClick() {
    System.out.println("Нажимаю кнопку Create Account");
    createAccountButton.click();
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
        By.xpath("//div[@class='notice success']"),
        "Your customer account has been created."
    ));
  }
}
