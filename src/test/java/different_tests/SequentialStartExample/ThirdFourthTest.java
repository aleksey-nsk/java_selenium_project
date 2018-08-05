package different_tests.SequentialStartExample;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ThirdFourthTest extends Base {

  @Test
  public void thirdTest() {
    System.out.print("\n\n***** Внутри метода thirdTest() *****\n\n");
    driver.navigate().to("https://www.google.ru/");
    driver.findElement(By.name("q")).sendKeys("thirdTest" + Keys.ENTER);
    wait.until(titleIs("thirdTest - Поиск в Google"));
  }

  @Test
  public void fourthTest() {
    System.out.print("\n\n***** Внутри метода fourthTest() *****\n\n");
    driver.navigate().to("https://www.google.ru/");
    driver.findElement(By.name("q")).sendKeys("fourthTest" + Keys.ENTER);
    wait.until(titleIs("fourthTest - Поиск в Google"));
  }
}
