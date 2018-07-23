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
    /* public void selectCountry(String country) {
        driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(country + Keys.ENTER);
    } */
    /*
    // Для некоторых товаров необходимо указать размер:
        if (areElementsPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            driver.findElement(By.xpath("//select[@name='options[Size]']")).sendKeys("Small");
        }
     */
    public void selectSize(String size) {
        driver.findElement(By.xpath("//select[@name='options[Size]']")).sendKeys(size);
    }

    /*
    public void selectZone(String zone) {
        wait.until((WebDriver d) -> d.findElement(
                By.cssSelector(String.format("select[name=zone_code] option[value=%s]", zone))));
        new Select(driver.findElement(By.name("select[name=zone_code]"))).selectByValue(zone);
    }
    */
    /*
    System.out.print("Жду пока счётчик товаров в корзине обновится... ");
        // increasedAmount = 22; // для проверки падения теста
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//span[@class='quantity']"),
                String.valueOf(increasedAmount)
        ));
        System.out.print("Счётчик обновлён!\n");
     */
    public void waitForCounterUpdating(int oldAmount) {
        int newAmount = oldAmount + 1;
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//span[@class='quantity']"),
                String.valueOf(newAmount)
        ));
    }


}
