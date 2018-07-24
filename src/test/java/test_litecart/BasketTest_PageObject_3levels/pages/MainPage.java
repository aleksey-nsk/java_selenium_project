package test_litecart.BasketTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
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

    /*
    // Методы котрые находят и возвращают элементы
    // расположенные на этой странице:
    public WebElement openBasketButton() {
        return driver.findElement(By.xpath("//div[@id='cart']/a[@class='link']"));
    }
    // Первый товар в категории Most Popular:
    public WebElement firstProduct() {
        return driver.findElement(By.xpath("//div[@id='box-most-popular']//ul/li[1]"));
    }
    */

    // Теперь вместо методов создадим поля, перед которыми
    // стоит специальная аннотация указывающая локатор:

    @FindBy(xpath = "//div[@id='cart']/a[@class='link']")
    public WebElement openBasketButton;

    @FindBy(xpath = "//div[@id='box-most-popular']//ul/li[1]")
    public WebElement firstProduct; // первый товар в категории Most Popular




}
