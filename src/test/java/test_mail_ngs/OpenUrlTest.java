package test_mail_ngs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class OpenUrlTest {
    private static WebDriver driver;
    private static WebDriverWait wait; // с помощью объекта wait буду реализовывать явные ожидания

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        driver = new InternetExplorerDriver(); // инициализация драйвера
        wait = new WebDriverWait(driver, 5); // инициализирую объект wait
        driver.manage().window().maximize();
    }

    @Test
    public void action(){
        driver.get("https://mail.ngs.ru/");
        wait.until(titleIs("ГОРОДСКАЯ ПОЧТОВАЯ СЛУЖБА"));
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
