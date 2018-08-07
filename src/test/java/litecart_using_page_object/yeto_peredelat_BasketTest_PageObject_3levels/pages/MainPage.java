package litecart_using_page_object.yeto_peredelat_BasketTest_PageObject_3levels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

  public MainPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    System.out.println("Метод для открытия главной страницы магазина");
    driver.get("http://localhost/litecart/en/");
  }

  @FindBy(xpath = "//div[@id='cart']/a[@class='link']")
  public WebElement openBasketButton;

  @FindBy(xpath = "//div[@id='box-most-popular']//ul/li[1]")
  public WebElement firstProduct; // первый товар в категории Most Popular
}
