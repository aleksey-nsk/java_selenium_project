package temp.firefox;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class My027Test {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.gecko.driver", "C:\\Tools\\geckodriver-v0.20.1-win64.exe");
        DesiredCapabilities caps = new DesiredCapabilities();
        driver = new FirefoxDriver(caps);
        System.out.println( ((HasCapabilities) driver).getCapabilities() );

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void action(){
        driver.get("https://www.google.ru/");
        wait.until(titleIs("Google"));
    }

    @After
    public void stop(){
        // driver.quit();
        // driver = null;
    }
}
