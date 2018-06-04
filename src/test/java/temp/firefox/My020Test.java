package temp.firefox;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class My020Test {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void action(){
        driver.get("https://www.google.ru/");

        // Найти поле для ввода текста и ввести значение:
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys("webdriver");

        driver.findElement(By.name("q")).sendKeys(Keys.ENTER); // нажал Enter
        wait.until(titleIs("webdriver - Поиск в Google")); // для Chrome и Firefox
        // wait.until(titleIs("webdriver - Google Search")); // для IE-11
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
