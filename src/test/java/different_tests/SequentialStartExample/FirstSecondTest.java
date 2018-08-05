package different_tests.SequentialStartExample;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstSecondTest extends Base {

  @Test
  public void firstTest() {
    System.out.print("\n\n***** Внутри метода firstTest() *****\n\n");
    driver.navigate().to("https://www.google.ru/");
    driver.findElement(By.name("q")).sendKeys("firstTest" + Keys.ENTER);
    wait.until(titleIs("firstTest - Поиск в Google"));
  }

  @Test
  public void secondTest() {
    System.out.print("\n\n***** Внутри метода secondTest() *****\n\n");
    driver.navigate().to("https://www.google.ru/");
    driver.findElement(By.name("q")).sendKeys("secondTest" + Keys.ENTER);
    wait.until(titleIs("secondTest - Поиск в Google"));
  }
}
