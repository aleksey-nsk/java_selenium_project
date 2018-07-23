package test_litecart.BasketTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasketPage extends Page {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    // Методы котрые находят и возвращают элементы
    // расположенные на этой странице:
    public WebElement removeProductButton() {
        // return driver.findElement(By.xpath("//button[@name='add_cart_product']"));
        return driver.findElement(By.xpath("//button[@name='remove_cart_item']"));
    }

    public List<WebElement> productsInBasket() {
        return driver.findElements(By.xpath("//ul[@class='shortcuts']/li"));
    }

    public List<WebElement> linesInTable() {
        return driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr"));
    }



    // Опишем тут еще методы:

    // Количество товаров в корзине
    public int amountProductsInBasket() {
        return driver.findElements(By.xpath("//ul[@class='shortcuts']/li")).size();
    }
    /*
    int amountProductsInBasket = driver.findElements(By.xpath("//ul[@class='shortcuts']/li")).size();
    System.out.print("Количество товаров в корзине = " + amountProductsInBasket + "\n");
    */

    // Текущее количество строк в таблице
    public int currentLines() {
        return driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr")).size();
    }

    // Функция которая ждёт пока количество строк в таблице обновится:
    public void waitForTableUpdating(int oldAmount) {
        System.out.println("Метод ожидания обновления таблицы товаров");
        int newAmount = oldAmount - 1;
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr"),
                newAmount
        ));
    }

    // Функция которая ждёт появления сообщения о том что корзина пуста:
    public void waitForEmptyBasketMessage() {
        System.out.println("Метод ожидания сообщения о том что корзина пуста");
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//div[@id='checkout-cart-wrapper']//em"),
                "There are no items in your cart."
        ));
    }


}

