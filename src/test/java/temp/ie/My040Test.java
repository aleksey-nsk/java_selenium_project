package temp.ie;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;

public class My040Test {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        driver = new InternetExplorerDriver();
    }

    @Test
    public void action(){
        driver.get("https://selenium2.ru/");
        System.out.println(  ((JavascriptExecutor) driver).executeScript("return 2+2")  );

        List<WebElement> links = (List<WebElement>) ((JavascriptExecutor) driver)
                .executeScript("return $$('a:contains(WebDriver)')");
        System.out.println(links);
    }

    @After
    public void stop(){
        // driver.quit();
        // driver = null;
    }
}
