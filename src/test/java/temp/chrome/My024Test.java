package temp.chrome;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class My024Test {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
    }

    @Test
    public void action(){
        driver.get("https://www.google.ru/");
    }

    @After
    public void stop(){
        // driver.quit();
        // driver = null;
    }
}
