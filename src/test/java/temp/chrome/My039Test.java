// Проверяю ситуацию, когда элемент не найден.

package temp.chrome;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class My039Test {
    public static WebDriver driver;

    // Функция проверяющая наличие элементов по заданному локатору:
    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void action(){
        driver.get("https://www.google.ru/");

        // Тест отработает:
        boolean b = areElementsPresent(By.name("un_presented"));
        System.out.println("b = " + b);

        // Тест упадёт с исключением InvalidSelectorException:
        // boolean b = areElementsPresent(By.xpath("//div["));
        // System.out.println("b = " + b);
    }

    @AfterClass
    public static void stop(){
        // driver.quit();
        // driver = null;
    }
}
