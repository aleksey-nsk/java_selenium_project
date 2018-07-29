package different_tests.LoginTest_2_update.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import different_tests.LoginTest_1.pages.LoginPage;
import different_tests.LoginTest_1.pages.MailBoxPage;
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
    }

    @Test
    public void action() {
        System.out.print("\n\n***** Внутри метода action() *****\n\n");
        driver.get("https://mail.ngs.ru/");

        System.out.println("Ввожу логин");
        loginPage.inputLogin("user.testov");
        System.out.println("Ввожу пароль");
        loginPage.inputPassword("zxc67*Q");
        System.out.println("Нажимаю кнопку входа");
        loginPage.clickLoginButton();

        System.out.println("Проверю что попал в свой почтовый аккаунт...");
        String mailUser = mailBoxPage.getUserMail();
        // mailUser = "new-user.testov@ngs.ruВыход"; // для проверки падения теста
        System.out.println("  expected = user.testov@ngs.ruВыход");
        System.out.println("  mailUser = " + mailUser);
        Assert.assertEquals("user.testov@ngs.ruВыход", mailUser);
        System.out.println("  Верно!");

        System.out.println("Выхожу из почтового аккаунта");
        mailBoxPage.userLogout();
    }

    @AfterClass
    public static void stop() {
        System.out.print("\n\n***** Внутри метода stop() *****\n\n");
        driver.quit();
        driver = null;
    }
}
