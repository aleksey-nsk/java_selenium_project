/*
Пример для проверки
ожидания исчезновения элемента
*/

package different_tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class PaginationTest {

  private static WebDriver driver;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    driver.manage().window().maximize();
  }

  @Test
  public void paginationTest() {
    System.out.print("\n\n***** Внутри метода paginationTest() *****\n\n");

    // Открываем нужную страницу:
    driver.get("http://pagination.js.org/");

    // Находим старые элементы:
    List<WebElement> items = driver.findElements(By.xpath("//div[@id='demo1']/div[@class='data-container']/ul/li"));
    List<WebElement> buttons = driver.findElements(By.xpath("//div[@id='demo1']/div[@class='paginationjs']//ul/li"));

    // Нажимаем какую-нибудь кнопку переключения:
    buttons.get(2).click();

    // Ожидание исчезновения старого элемента:
    (new WebDriverWait(driver, 5)).until(ExpectedConditions.stalenessOf(items.get(0)));
    // - если это ожидание убрать, то с некоторой вероятностью после клика по кнопке переключения
    // следующая команда (команда поиска) может выполниться ещё до того как элементы исчезнут.
    // И в таком случае эта команда найдёт старые элементы.
    // Тем самым в тестах возникнет очаг нестабильности --> тесты будут то успешно проходить, то падать!

    // Теперь найдём новые элементы (по тому же самому локатору):
    items = driver.findElements(By.xpath("//div[@id='demo1']/div[@class='data-container']/ul/li"));

    // Проверка:
    Assert.assertTrue(items.get(0).getText().equals("11"));
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    driver.quit();
    driver = null;
  }
}
