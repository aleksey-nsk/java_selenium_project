package litecart_using_page_object.UserRegistrationTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Данный класс описывает страницу регистрации.
// Информация об адресе этой страницы и о локаторах элементов
// сосредоточена исключительно в пределах этого класса.
// (а как выглядит использование этого класса - см. в класс Application)
public class RegistrationPage extends Page {

  public RegistrationPage(WebDriver driver) {
    super(driver);

    // Дописываю сюда PageFactory:
    PageFactory.initElements(driver, this);
  }

  // Метод open() открывает эту страницу регистрации:
  public void open() {
    driver.get("http://localhost/litecart_using_page_object/en/create_account");
  }

    /*
    // Методы котрые находят и возвращают элементы
    // расположенные на этой странице:
    public WebElement firstnameInput() {
        return driver.findElement(By.xpath("//input[@name='firstname']"));
    }
    public WebElement lastnameInput() {
        return driver.findElement(By.xpath("//input[@name='lastname']"));
    }
    public WebElement adressInput() {
        return driver.findElement(By.xpath("//input[@name='address1']"));
    }
    public WebElement postcodeInput() {
        return driver.findElement(By.xpath("//input[@name='postcode']"));
    }
    public WebElement cityInput() {
        return driver.findElement(By.xpath("//input[@name='city']"));
    }
    public WebElement emailInput() {
        return driver.findElement(By.xpath("//input[@name='email']"));
    }
    public WebElement phoneInput() {
        return driver.findElement(By.xpath("//input[@name='phone']"));
    }
    public WebElement passwordInput() {
        return driver.findElement(By.xpath("//input[@name='password']"));
    }
    public WebElement confirmedPasswordInput() {
        return driver.findElement(By.xpath("//input[@name='confirmed_password']"));
    }
    public WebElement createAccountButton() {
        return driver.findElement(By.xpath("//button[@name='create_account']"));
    }
    */

  // Теперь вместо методов создадим поля, перед которыми
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

  // Опишем тут методы selectCountry и selectZone:

  public void selectCountry(String country) {
    driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
    driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(country + Keys.ENTER);
  }

  public void selectZone(String zone) {
    driver.findElement(By.xpath("//select[@name='zone_code']")).click();
    driver.findElement(By.xpath(String.format("//select[@name='zone_code']/option[@value='%s']", zone))).click();
  }

}
