package different_tests.My031Test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest extends BaseTest {

    @Test
    public void action_1_1(){
        driver.navigate().to("https://www.google.ru/");
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys("action_1_1");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(titleIs("action_1_1 - Поиск в Google"));
    }

    @Test
    public void action_1_2(){
        driver.navigate().to("https://www.google.ru/");
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys("action_1_2");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(titleIs("action_1_2 - Поиск в Google"));
    }
}