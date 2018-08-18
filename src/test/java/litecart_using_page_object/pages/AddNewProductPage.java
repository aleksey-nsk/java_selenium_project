package litecart_using_page_object.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;

public class AddNewProductPage extends Page {

  @FindBy(xpath = "//a[@href='#tab-general']") private WebElement tabGeneral;
  @FindBy(xpath = "//a[@href='#tab-information']") private WebElement tabInformation;
  @FindBy(xpath = "//a[@href='#tab-prices']") private WebElement tabPrices;
  @FindBy(xpath = "//div[@id='tab-general']//tbody/tr[1]//label[1]") private WebElement statusEnabled;
  @FindBy(name = "name[en]") private WebElement name;
  @FindBy(name = "code") private WebElement code;
  @FindBy(xpath = "//input[@value='1-3']") private WebElement genderUnisex;
  @FindBy(name = "quantity") private WebElement quantity;
  @FindBy(name = "new_images[]") private WebElement image;
  @FindBy(name = "date_valid_from") private WebElement dateValidFrom;
  @FindBy(name = "date_valid_to") private WebElement dateValidTo;
  @FindBy(name = "manufacturer_id") private WebElement manufacturer;
  @FindBy(name = "keywords") private WebElement keywords;
  @FindBy(name = "short_description[en]") private WebElement shortDescription;
  @FindBy(xpath = "//div[@class='trumbowyg-editor']") private WebElement description;
  @FindBy(name = "head_title[en]") private WebElement headTitle;
  @FindBy(name = "meta_description[en]") private WebElement metaDescription;
  @FindBy(name = "purchase_price") private WebElement purchasePrice;
  @FindBy(name = "purchase_price_currency_code") private WebElement currencyCode;
  @FindBy(name = "prices[USD]") private WebElement priceUsd;
  @FindBy(name = "save") private WebElement saveButton;

  public AddNewProductPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    System.out.println("Захожу в меню Catalog");
    driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
    System.out.println("Жму кнопку Add New Product");
    driver.findElement(By.xpath("//td[@id='content']/div[@style='float: right;']/a[2]")).click();
    (new WebDriverWait(driver, 3)).until(ExpectedConditions.titleIs("Add New Product | My Store"));
  }

  public void tabGeneralOpen() {
    System.out.println("Открываю вкладку General");
    tabGeneral.click();
  }

  public void tabInformationOpen() {
    System.out.println("Открываю вкладку Information");
    tabInformation.click();
  }

  public void tabPricesOpen() {
    System.out.println("Открываю вкладку Prices");
    tabPrices.click();
  }

  public void statusEnabledClick() { statusEnabled.click(); }
  public void nameEnter(String productName) { name.sendKeys(productName); }
  public void codeEnter() { code.sendKeys("12345"); }
  public void genderUnisexCheck() { genderUnisex.click(); }
  public void dateValidFromEnter() { dateValidFrom.sendKeys("17.06.2018"); }
  public void dateValidToEnter() { dateValidTo.sendKeys("17.06.2022"); }
  public void manufacturerSelect() { manufacturer.sendKeys("ACME Corp."); }
  public void keywordsEnter() { keywords.sendKeys("keywords"); }
  public void shortDescriptionEnter() { shortDescription.sendKeys("Short Description"); }
  public void descriptionEnter(String productName) { description.sendKeys(productName); }
  public void headTitleEnter(String productName) { headTitle.sendKeys(productName); }
  public void metaDescriptionEnter() { metaDescription.sendKeys("Meta Description"); }
  public void currencyCodeSelect() { currencyCode.sendKeys("US Dollars"); }
  public void priceUsdEnter() { priceUsd.sendKeys("23"); }

  public void quantityEnter() {
    // String selectAll = Keys.chord(Keys.CONTROL, "a"); // Ctrl + A
    // quantity.sendKeys(selectAll);
    // quantity.sendKeys(Keys.DELETE);
    quantity.clear();
    quantity.sendKeys("7");
  }

  public void uploadImage() {
    File fileImage = new File("images\\duck.png");
    String absolutePathToFile = fileImage.getAbsolutePath(); // абсолютный путь к файлу начиная с корня системы
    image.sendKeys(absolutePathToFile);
  }

  public void purchasePriceEnter() {
    purchasePrice.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    purchasePrice.sendKeys(Keys.DELETE);
    purchasePrice.sendKeys("23");
  }

  public void saveProduct() {
    System.out.println("Сохраняю товар");
    saveButton.click();
    String expectedMessage = "Changes were successfully saved.";
    String actualMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    Assert.assertEquals(expectedMessage, actualMessage);
  }
}
