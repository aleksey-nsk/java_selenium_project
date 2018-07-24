package test_litecart.BasketTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BasketPage extends Page {

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@name='remove_cart_item']")
    public WebElement removeProductButton;

    @FindBy(xpath = "//ul[@class='shortcuts']/li")
    public List<WebElement> productsInBasket;

    @FindBy(xpath = "//table[@class='dataTable rounded-corners']/tbody/tr")
    public List<WebElement> linesInTable;

    public void waitForTableUpdating(int oldAmount) {
        System.out.println("Метод ожидания обновления таблицы товаров");
        int newAmount = oldAmount - 1;
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr"),
                newAmount
        ));
    }

    public void waitForEmptyBasketMessage() {
        System.out.println("Метод ожидания сообщения о том что корзина пуста");
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//div[@id='checkout-cart-wrapper']//em"),
                "There are no items in your cart."
        ));
    }
}
