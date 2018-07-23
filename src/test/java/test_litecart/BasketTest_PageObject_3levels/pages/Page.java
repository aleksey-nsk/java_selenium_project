package test_litecart.BasketTest_PageObject_3levels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver driver;
    // protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        // wait = new WebDriverWait(driver, 10);
    }

    // Метод проверяющий есть ли элемент:
    public boolean isElementPresent(WebDriver driver, By locator){
        return driver.findElements(locator).size() > 0;
    }


}
