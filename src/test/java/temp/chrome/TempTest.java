package temp.chrome;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TempTest {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.out.print("***** Внутри метода start() *****\n\n");

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver(); // инициализация драйвера
        // System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        // driver = new InternetExplorerDriver(); // инициализация драйвера

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
    }

    @Test
    public void action(){
        System.out.print("***** Внутри метода action() *****\n\n");

        // Захожу в панель администратора:
        /* driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String accountUser = driver.findElement(By.xpath("//div[contains(@class,'notice success')]")).getText();
        Assert.assertEquals("You are now logged in as admin", accountUser); */

        driver.get("https://selenium2.ru/");
        String color = driver.findElement(By.xpath("//h2[@itemprop='headline']/a")).getCssValue("color");
        System.out.println(color);

        String color2 = driver.findElement(By.xpath("//h2[@itemprop='headline']/a")).getCssValue("background-color");
        System.out.println(color2);

        String color3 = driver.findElement(By.xpath("//h2[@itemprop='headline']/a")).getCssValue("border-color");
        System.out.println(color3);

        // Выхожу из панели администрирования:
        // driver.findElement(By.xpath("//a[@title='Logout']")).click();
    }

    @AfterClass
    public static void stop(){
        System.out.print("***** Внутри метода stop() *****\n\n");

        // driver.quit();
        // driver = null;
    }
}
