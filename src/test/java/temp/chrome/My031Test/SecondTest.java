package temp.chrome.My031Test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SecondTest extends BaseTest {

    @Test
    public void action_2_1(){
        driver.navigate().to("https://www.google.ru/");
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys("action_2_1");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(titleIs("action_2_1 - Поиск в Google"));
    }

    @Test
    public void action_2_2(){
        driver.navigate().to("https://www.google.ru/");
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys("action_2_2");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(titleIs("action_2_2 - Поиск в Google"));
    }
}