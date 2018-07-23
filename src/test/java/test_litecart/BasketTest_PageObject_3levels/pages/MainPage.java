package test_litecart.BasketTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Метод open() открывает эту главную страницу:
    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    // Методы котрые находят и возвращают элементы
    // расположенные на этой странице:

    public WebElement openBasketButton() {
        return driver.findElement(By.xpath("//div[@id='cart']/a[@class='link']"));
    }

    // Первый товар в категории Most Popular:
    public WebElement firstProduct() {
        return driver.findElement(By.xpath("//div[@id='box-most-popular']//ul/li[1]"));
    }
}
