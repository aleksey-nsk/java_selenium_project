package test_litecart.BasketTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Методы котрые находят и возвращают элементы
    // расположенные на этой странице:
    public WebElement addToBasketButton() {
        return driver.findElement(By.xpath("//button[@name='add_cart_product']"));
    }

    public WebElement amountProductsInBasket() {
        //int currentAmount = Integer.parseInt(driver.findElement(By.xpath("//span[@class='quantity']")).getText());
        return driver.findElement(By.xpath("//span[@class='quantity']"));
    }

    public WebElement goToMainPageButton() {
        // System.out.print("Возвращаюсь на главную страницу\n");
        // driver.findElement(By.xpath("//li[@class='general-0']")).click();
        return driver.findElement(By.xpath("//li[@class='general-0']"));
    }

    // Опишем тут еще методы:

    // Для некоторых товаров необходимо указать размер:
    public void selectSizeIfPresent() {
        if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            System.out.println("Поле Size присутствует");
            driver.findElement(By.xpath("//select[@name='options[Size]']")).sendKeys("Small");
        }
        else {
            System.out.println("Поле Size отсутствует");
        }
    }

    public void waitForCounterUpdating(int oldAmount) {
        System.out.println("Метод ожидания обновления счётчика");
        int newAmount = oldAmount + 1;
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//span[@class='quantity']"),
                String.valueOf(newAmount)
        ));
    }


}
