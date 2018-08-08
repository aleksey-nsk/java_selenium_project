package different_tests.PageObjectSecondExample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Данный класс описывает страницу регистрации клиента.
// Информация об адресе этой страницы и о локаторах элементов
// сосредоточена исключительно в пределах этого класса.
// А как выглядит использование этого класса -- см. в классе Application
public class RegistrationPage extends Page {

  public RegistrationPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this); // дописал сюда PageFactory
  }

  public void open() {
    System.out.println("Метод для открытия страницы регистрации клиента");
    driver.get("http://localhost/litecart/en/create_account");
  }

  // Сначала были методы, котрые находили и возвращали элементы,
  // расположенные на этой странице.
  // Затем вместо методов создали поля, перед которыми
  // стоит специальная аннотация указывающая локатор:

  @FindBy(name = "firstname")
  public WebElement firstnameInput;

  @FindBy(name = "lastname")
  public WebElement lastnameInput;

  @FindBy(name = "address1")
  public WebElement adressInput;

  @FindBy(name = "postcode")
  public WebElement postcodeInput;

  @FindBy(name = "city")
  public WebElement cityInput;

  @FindBy(name = "email")
  public WebElement emailInput;

  @FindBy(name = "phone")
  public WebElement phoneInput;

  @FindBy(name = "password")
  public WebElement passwordInput;

  @FindBy(name = "confirmed_password")
  public WebElement confirmedPasswordInput;

  @FindBy(name = "create_account")
  public WebElement createAccountButton;

  // Далее опишем тут
  // методы selectCountry и selectZone:

  public void selectCountry(String country) {
    System.out.println("Метод для выбора страны");
    driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
    driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(country + Keys.ENTER);
  }

  public void selectZone(String zone) {
    System.out.println("Метод для выбора штата");
    driver.findElement(By.xpath("//select[@name='zone_code']")).click();
    driver.findElement(By.xpath(String.format("//select[@name='zone_code']/option[@value='%s']", zone))).click();
  }
}
