package temp.ie;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;

public class My023Test {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehaviour", "dismiss");
        driver = new InternetExplorerDriver(caps);
        System.out.println( ((HasCapabilities) driver).getCapabilities() );

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
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
