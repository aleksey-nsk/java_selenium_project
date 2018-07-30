package litecart_without_page_object;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

public class CheckRightProductPageTest extends TestBase {

  @Test
  public void test010() {
    System.out.print("\n\n***** Внутри метода test010() *****\n\n");

    driver.get("http://localhost/litecart/en/");
    WebElement firstProduct = driver.findElements(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li")).get(0);
    System.out.println("Зашёл на главную страницу и выбрал первый товар в блоке Campaigns");

    String titleOnMainPage = firstProduct.findElement(By.xpath(".//div[@class='name']")).getText();
    System.out.println("Название товара на главной странице = " + titleOnMainPage);

    WebElement regularPrice = firstProduct.findElement(By.xpath(".//s[@class='regular-price']"));
    String valueOfRegularPrice = regularPrice.getText();
    System.out.println("Обычная цена на главной странице = " + valueOfRegularPrice);

    WebElement campaignPrice = firstProduct.findElement(By.xpath(".//strong[@class='campaign-price']"));
    String valueOfCampaignPrice = campaignPrice.getText();
    System.out.println("Акционная цена на главной странице = " + valueOfCampaignPrice);

    System.out.print("Проверяю что обычная цена серая: ");
    String rgbaColorOfRegularPrice = regularPrice.getCssValue("color");
    Color colorOfRegularPrice = Color.fromString(rgbaColorOfRegularPrice);
    int red = colorOfRegularPrice.getColor().getRed();
    int green = colorOfRegularPrice.getColor().getGreen();
    int blue = colorOfRegularPrice.getColor().getBlue();
    // red = 99; // для проверки падения теста
    System.out.println("red=" + red + " green=" + green + " blue=" + blue);
    Assert.assertTrue(red == green && red == blue);

    System.out.print("Проверяю что обычная цена зачёркнута: ");
    String styleOfRegularPrice = regularPrice.getCssValue("text-decoration");
    // styleOfRegularPrice = "solid rgb(119, 119, 119)"; // для проверки падения теста
    System.out.println("styleOfRegularPrice = " + styleOfRegularPrice);
    Assert.assertTrue(styleOfRegularPrice.contains("line-through"));

    System.out.print("Проверяю что акционная цена красная: ");
    String rgbaColorOfCampaignPrice = campaignPrice.getCssValue("color");
    Color colorOfCampaignPrice = Color.fromString(rgbaColorOfCampaignPrice);
    int greenOfCampaignPrice = colorOfCampaignPrice.getColor().getGreen();
    int blueOfCampaignPrice = colorOfCampaignPrice.getColor().getBlue();
    // greenOfCampaignPrice = 22; // для проверки падения теста
    System.out.println("greenOfCampaignPrice=" + greenOfCampaignPrice + " blueOfCampaignPrice=" + blueOfCampaignPrice);
    Assert.assertTrue(greenOfCampaignPrice == 0 && blueOfCampaignPrice == 0);

    System.out.print("Проверяю что акционная цена жирная: ");
    String fontWeightOfCampaignPrice = campaignPrice.getCssValue("font-weight");
    // fontWeightOfCampaignPrice = "400"; // для проверки падения теста
    System.out.println("fontWeightOfCampaignPrice = " + fontWeightOfCampaignPrice);
    Assert.assertTrue(fontWeightOfCampaignPrice.compareTo("700") >= 0);

    System.out.print("Проверяю что акционная цена крупнее чем обычная: ");
    String sizeOfRegularPrice = regularPrice.getCssValue("font-size");
    String sizeOfCampaignPrice = campaignPrice.getCssValue("font-size");
    // sizeOfRegularPrice = "23px"; // для проверки падения теста
    System.out.println("  sizeOfRegularPrice=" + sizeOfRegularPrice + " sizeOfCampaignPrice=" + sizeOfCampaignPrice);
    Assert.assertTrue(sizeOfRegularPrice.compareTo(sizeOfCampaignPrice) < 0);

    System.out.print("\nПерехожу на страницу товара\n");
    firstProduct.click();

    String titleOnProductPage = driver.findElement(By.xpath("//div[@id='box-product']//h1[@class='title']")).getText();
    // titleOnProductPage = "Test Title"; // для проверки падения теста
    System.out.println("Название товара на странице товара = " + titleOnProductPage);
    Assert.assertTrue(titleOnMainPage.compareTo(titleOnProductPage) == 0);

    WebElement regularPriceOnProductPage = driver.findElement(By.xpath("//div[@class='content']//s[@class='regular-price']"));
    String valueOfRegularPriceOnProductPage = regularPriceOnProductPage.getText();
    // valueOfRegularPriceOnProductPage = "$33"; // для проверки падения теста
    System.out.println("Обычная цена на странице товара = " + valueOfRegularPriceOnProductPage);
    Assert.assertTrue(valueOfRegularPrice.compareTo(valueOfRegularPriceOnProductPage) == 0);

    WebElement campaignPriceOnProductPage = driver.findElement(By.xpath("//div[@class='content']//strong[@class='campaign-price']"));
    String valueOfCampaignPriceOnProductPage = campaignPriceOnProductPage.getText();
    // valueOfCampaignPriceOnProductPage = "$44"; // для проверки падения теста
    System.out.println("Акционная цена на странице товара = " + valueOfCampaignPriceOnProductPage);
    Assert.assertTrue(valueOfCampaignPrice.compareTo(valueOfCampaignPriceOnProductPage) == 0);

    System.out.print("Проверяю что на странице товара:\n  обычная цена серая: ");
    String rgbaColorOfRegularPriceOnProductPage = regularPriceOnProductPage.getCssValue("color");
    Color colorOfRegularPriceOnProductPage = Color.fromString(rgbaColorOfRegularPriceOnProductPage);
    int redOnProductPage = colorOfRegularPriceOnProductPage.getColor().getRed();
    int greenOnProductPage = colorOfRegularPriceOnProductPage.getColor().getGreen();
    int blueOnProductPage = colorOfRegularPriceOnProductPage.getColor().getBlue();
    // redOnProductPage = 43; // для проверки падения теста
    System.out.println("redOnProductPage=" + redOnProductPage + " greenOnProductPage=" + greenOnProductPage + " blueOnProductPage=" + blueOnProductPage);
    Assert.assertTrue(redOnProductPage == greenOnProductPage && redOnProductPage == blueOnProductPage);

    System.out.print("  обычная цена зачёркнута: ");
    String styleOfRegularPriceOnProductPage = regularPriceOnProductPage.getCssValue("text-decoration");
    // styleOfRegularPriceOnProductPage = "solid rgb(102, 102, 102)"; // для проверки падения теста
    System.out.println("styleOfRegularPriceOnProductPage = " + styleOfRegularPriceOnProductPage);
    Assert.assertTrue(styleOfRegularPriceOnProductPage.contains("line-through"));

    System.out.print("  акционная цена красная: ");
    String rgbaColorOfCampaignPriceOnProductPage = campaignPriceOnProductPage.getCssValue("color");
    Color colorOfCampaignPriceOnProductPage = Color.fromString(rgbaColorOfCampaignPriceOnProductPage);
    int greenOfCampaignPriceOnProductPage = colorOfCampaignPriceOnProductPage.getColor().getGreen();
    int blueOfCampaignPriceOnProductPage = colorOfCampaignPriceOnProductPage.getColor().getBlue();
    // greenOfCampaignPriceOnProductPage = 34; // для проверки падения теста
    System.out.println("greenOfCampaignPriceOnProductPage=" + greenOfCampaignPriceOnProductPage + " blueOfCampaignPriceOnProductPage=" + blueOfCampaignPriceOnProductPage);
    Assert.assertTrue(greenOfCampaignPriceOnProductPage == 0 && blueOfCampaignPriceOnProductPage == 0);

    System.out.print("  акционная цена жирная: ");
    String fontWeightOfCampaignPriceOnProductPage = campaignPriceOnProductPage.getCssValue("font-weight");
    // fontWeightOfCampaignPriceOnProductPage = "400"; // для проверки падения теста
    System.out.println("fontWeightOfCampaignPriceOnProductPage = " + fontWeightOfCampaignPriceOnProductPage);
    Assert.assertTrue(fontWeightOfCampaignPriceOnProductPage.compareTo("700") == 0);

    System.out.print("  акционная цена крупнее чем обычная: ");
    String sizeOfRegularPriceOnProductPage = regularPriceOnProductPage.getCssValue("font-size");
    String sizeOfCampaignPriceOnProductPage = campaignPriceOnProductPage.getCssValue("font-size");
    // sizeOfRegularPriceOnProductPage = "31px"; // для проверки падения теста
    System.out.println("sizeOfRegularPriceOnProductPage=" + sizeOfRegularPriceOnProductPage + " sizeOfCampaignPriceOnProductPage=" + sizeOfCampaignPriceOnProductPage);
    Assert.assertTrue(sizeOfRegularPriceOnProductPage.compareTo(sizeOfCampaignPriceOnProductPage) < 0);
  }
}
