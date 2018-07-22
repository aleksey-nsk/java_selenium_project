package test_page_object.LoginTest_2_update.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test_page_object.LoginTest_1.pages.LoginPage;
import test_page_object.LoginTest_1.pages.MailBoxPage;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static MailBoxPage mailBoxPage;

    @BeforeClass
    public static void start() {
        System.out.print("\n\n***** Внутри метода start() *****\n\n");
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver(); // инициализация драйвера
        loginPage = new LoginPage(driver);
        mailBoxPage = new MailBoxPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
        driver.get("https://mail.ngs.ru/");
    }

    @Test
    public void loginTest() {
        System.out.print("\n\n***** Внутри метода loginTest() *****\n\n");
        loginPage.inputLogin("user.testov");
        loginPage.inputPassword("zxc67*Q");
        loginPage.clickLoginButton();
        String mailUser = mailBoxPage.getUserMail();
        Assert.assertEquals("user.testov@ngs.ruВыход", mailUser);
    }

    @AfterClass
    public static void tearDown() {
        System.out.print("\n\n***** Внутри метода tearDown() *****\n\n");
        mailBoxPage.userLogout();
        driver.quit();
        driver = null;
    }
}
