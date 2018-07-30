/*
Описание теста:

Задание 10. Проверить, что открывается правильная страница товара.
Более точно, нужно открыть главную страницу, выбрать первый товар
в блоке Campaigns и проверить следующее:

1) на главной странице и на странице товара совпадает текст названия товара.
2) на главной странице и на странице товара совпадают цены (обычная и акционная).
3) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой,
у которого в RGBa представлении одинаковые значения для каналов R, G и B).
4) акционная жирная и красная (можно считать, что "красный" цвет это такой,
у которого в RGBa представлении каналы G и B имеют нулевые значения).
(Цвета надо проверить на каждой странице независимо, при этом цвета на
разных страницах могут не совпадать).
5) акционная цена крупнее, чем обычная (это тоже надо
проверить на каждой странице независимо).

Необходимо убедиться, что тесты работают в разных браузерах, желательно
проверить во всех трёх ключевых браузерах (Chrome, Firefox, IE).
*/

package litecart_without_page_object;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

import java.util.concurrent.TimeUnit;

public class CheckRightProductPageTest {
  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.out.print("\n***** Внутри метода start() *****\n\n");

    // Проверю работу автотеста в 3 основных браузерах:
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    // System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
    // driver = new InternetExplorerDriver(); // инициализация драйвера
    // System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
    // driver = new FirefoxDriver(); // инициализация драйвера

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
  }

  @Test
  public void action() {
    System.out.print("\n***** Внутри метода action() *****\n\n");

    // -------------------------------------------
    // Осуществляю проверки на главной странице
    // -------------------------------------------
    driver.get("http://localhost/litecart/en/");
    WebElement firstProduct = driver.findElements(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li")).get(0);
    System.out.print("Зашёл на главную страницу и выбрал первый товар в блоке Campaigns\n");

    String titleOnMainPage = firstProduct.findElement(By.xpath(".//div[@class='name']")).getText();
    System.out.print("Название товара на главной странице = " + titleOnMainPage + "\n");

    WebElement regularPrice = firstProduct.findElement(By.xpath(".//s[@class='regular-price']"));
    String valueOfRegularPrice = regularPrice.getText();
    System.out.print("Обычная цена на главной странице = " + valueOfRegularPrice + "\n");

    WebElement campaignPrice = firstProduct.findElement(By.xpath(".//strong[@class='campaign-price']"));
    String valueOfCampaignPrice = campaignPrice.getText();
    System.out.print("Акционная цена на главной странице = " + valueOfCampaignPrice + "\n\n");

    System.out.print("Проверяю что обычная цена серая...\n");
    String rgbaColorOfRegularPrice = regularPrice.getCssValue("color");
    Color colorOfRegularPrice = Color.fromString(rgbaColorOfRegularPrice);
    int red = colorOfRegularPrice.getColor().getRed();
    int green = colorOfRegularPrice.getColor().getGreen();
    int blue = colorOfRegularPrice.getColor().getBlue();
    // red = 99; // для проверки падения теста
    System.out.print("red=" + red + " green=" + green + " blue=" + blue + "\n");
    Assert.assertTrue(red == green && red == blue);
    System.out.print("Верно!\n\n");

    System.out.print("Проверю что обычная цена зачёркнута...\n");
    String styleOfRegularPrice = regularPrice.getCssValue("text-decoration");
    // styleOfRegularPrice = "solid rgb(119, 119, 119)"; // для проверки падения теста
    System.out.print("styleOfRegularPrice = " + styleOfRegularPrice + "\n");
    Assert.assertTrue(styleOfRegularPrice.contains("line-through"));
    System.out.print("Верно!\n\n");

    System.out.print("Проверяю что акционная цена красная...\n");
    String rgbaColorOfCampaignPrice = campaignPrice.getCssValue("color");
    Color colorOfCampaignPrice = Color.fromString(rgbaColorOfCampaignPrice);
    int greenOfCampaignPrice = colorOfCampaignPrice.getColor().getGreen();
    int blueOfCampaignPrice = colorOfCampaignPrice.getColor().getBlue();
    // greenOfCampaignPrice = 22; // для проверки падения теста
    System.out.print("greenOfCampaignPrice=" + greenOfCampaignPrice + " blueOfCampaignPrice=" + blueOfCampaignPrice + "\n");
    Assert.assertTrue(greenOfCampaignPrice == 0 && blueOfCampaignPrice == 0);
    System.out.print("Верно!\n\n");

    System.out.print("Проверю что акционная цена жирная...\n");
    String fontWeightOfCampaignPrice = campaignPrice.getCssValue("font-weight");
    // fontWeightOfCampaignPrice = "400"; // для проверки падения теста
    System.out.print("fontWeightOfCampaignPrice = " + fontWeightOfCampaignPrice + "\n");
    Assert.assertTrue(fontWeightOfCampaignPrice.compareTo("700") >= 0);
    System.out.print("Верно!\n\n");

    System.out.print("Проверю что акционная цена крупнее чем обычная...\n");
    String sizeOfRegularPrice = regularPrice.getCssValue("font-size");
    String sizeOfCampaignPrice = campaignPrice.getCssValue("font-size");
    // sizeOfRegularPrice = "23px"; // для проверки падения теста
    System.out.print("sizeOfRegularPrice=" + sizeOfRegularPrice + " sizeOfCampaignPrice=" + sizeOfCampaignPrice + "\n");
    Assert.assertTrue(sizeOfRegularPrice.compareTo(sizeOfCampaignPrice) < 0);
    System.out.print("Верно!\n\n");

    // -------------------------------------------
    // Осуществляю проверки на странице товара
    // -------------------------------------------
    System.out.print("ПЕРЕХОЖУ НА СТРАНИЦУ ТОВАРА\n\n");
    firstProduct.click();

    String titleOnProductPage = driver.findElement(By.xpath("//div[@id='box-product']//h1[@class='title']")).getText();
    // titleOnProductPage = "Test Title"; // для проверки падения теста
    System.out.print("Название товара на странице товара = " + titleOnProductPage + "\n");
    Assert.assertTrue(titleOnMainPage.compareTo(titleOnProductPage) == 0);
    System.out.print("Совпадает с названием товара на главной странице!\n\n");

    WebElement regularPriceOnProductPage = driver.findElement(By.xpath("//div[@class='content']//s[@class='regular-price']"));
    String valueOfRegularPriceOnProductPage = regularPriceOnProductPage.getText();
    // valueOfRegularPriceOnProductPage = "$33"; // для проверки падения теста
    System.out.print("Обычная цена на странице товара = " + valueOfRegularPriceOnProductPage + "\n");
    Assert.assertTrue(valueOfRegularPrice.compareTo(valueOfRegularPriceOnProductPage) == 0);
    System.out.print("Совпадает с обычной ценой на главной странице!\n\n");

    WebElement campaignPriceOnProductPage = driver.findElement(By.xpath("//div[@class='content']//strong[@class='campaign-price']"));
    String valueOfCampaignPriceOnProductPage = campaignPriceOnProductPage.getText();
    // valueOfCampaignPriceOnProductPage = "$44"; // для проверки падения теста
    System.out.print("Акционная цена на странице товара = " + valueOfCampaignPriceOnProductPage + "\n");
    Assert.assertTrue(valueOfCampaignPrice.compareTo(valueOfCampaignPriceOnProductPage) == 0);
    System.out.print("Совпадает с акционной ценой на главной странице!\n\n");

    System.out.print("Проверяю что на странице товара обычная цена серая...\n");
    String rgbaColorOfRegularPriceOnProductPage = regularPriceOnProductPage.getCssValue("color");
    Color colorOfRegularPriceOnProductPage = Color.fromString(rgbaColorOfRegularPriceOnProductPage);
    int redOnProductPage = colorOfRegularPriceOnProductPage.getColor().getRed();
    int greenOnProductPage = colorOfRegularPriceOnProductPage.getColor().getGreen();
    int blueOnProductPage = colorOfRegularPriceOnProductPage.getColor().getBlue();
    // redOnProductPage = 43; // для проверки падения теста
    System.out.print("redOnProductPage=" + redOnProductPage + " greenOnProductPage=" + greenOnProductPage + " blueOnProductPage=" + blueOnProductPage + "\n");
    Assert.assertTrue(redOnProductPage == greenOnProductPage && redOnProductPage == blueOnProductPage);
    System.out.print("Верно!\n\n");

    System.out.print("Проверю что на странице товара обычная цена зачёркнута...\n");
    String styleOfRegularPriceOnProductPage = regularPriceOnProductPage.getCssValue("text-decoration");
    // styleOfRegularPriceOnProductPage = "solid rgb(102, 102, 102)"; // для проверки падения теста
    System.out.print("styleOfRegularPriceOnProductPage = " + styleOfRegularPriceOnProductPage + "\n");
    Assert.assertTrue(styleOfRegularPriceOnProductPage.contains("line-through"));
    System.out.print("Верно!\n\n");

    System.out.print("Проверяю что на странице товара акционная цена красная...\n");
    String rgbaColorOfCampaignPriceOnProductPage = campaignPriceOnProductPage.getCssValue("color");
    Color colorOfCampaignPriceOnProductPage = Color.fromString(rgbaColorOfCampaignPriceOnProductPage);
    int greenOfCampaignPriceOnProductPage = colorOfCampaignPriceOnProductPage.getColor().getGreen();
    int blueOfCampaignPriceOnProductPage = colorOfCampaignPriceOnProductPage.getColor().getBlue();
    // greenOfCampaignPriceOnProductPage = 34; // для проверки падения теста
    System.out.print("greenOfCampaignPriceOnProductPage=" + greenOfCampaignPriceOnProductPage + " blueOfCampaignPriceOnProductPage=" + blueOfCampaignPriceOnProductPage + "\n");
    Assert.assertTrue(greenOfCampaignPriceOnProductPage == 0 && blueOfCampaignPriceOnProductPage == 0);
    System.out.print("Верно!\n\n");

    System.out.print("Проверю что на странице товара акционная цена жирная...\n");
    String fontWeightOfCampaignPriceOnProductPage = campaignPriceOnProductPage.getCssValue("font-weight");
    // fontWeightOfCampaignPriceOnProductPage = "400"; // для проверки падения теста
    System.out.print("fontWeightOfCampaignPriceOnProductPage = " + fontWeightOfCampaignPriceOnProductPage + "\n");
    Assert.assertTrue(fontWeightOfCampaignPriceOnProductPage.compareTo("700") == 0);
    System.out.print("Верно!\n\n");

    System.out.print("Проверю что на странице товара акционная цена крупнее чем обычная...\n");
    String sizeOfRegularPriceOnProductPage = regularPriceOnProductPage.getCssValue("font-size");
    String sizeOfCampaignPriceOnProductPage = campaignPriceOnProductPage.getCssValue("font-size");
    // sizeOfRegularPriceOnProductPage = "31px"; // для проверки падения теста
    System.out.print("sizeOfRegularPriceOnProductPage=" + sizeOfRegularPriceOnProductPage + " sizeOfCampaignPriceOnProductPage=" + sizeOfCampaignPriceOnProductPage + "\n");
    Assert.assertTrue(sizeOfRegularPriceOnProductPage.compareTo(sizeOfCampaignPriceOnProductPage) < 0);
    System.out.print("Верно!\n\n");
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n***** Внутри метода stop() *****\n\n");

    driver.quit();
    driver = null;
  }
}
