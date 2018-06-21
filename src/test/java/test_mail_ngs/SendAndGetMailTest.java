/*
Описание теста:
- открываю страницу почты ngs
- захожу в ПочтовыйЯщик1
- отправляю письмо на ПочтовыйЯщик2
- выхожу из ПочтовогоЯщика1
- захожу в ПочтовыйЯщик2
- убеждаюсь что письмо пришло
- выхожу из ПочтовогоЯщика2
*/

package test_mail_ngs;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class SendAndGetMailTest {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        driver = new InternetExplorerDriver(); // инициализация драйвера
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
    }

    @Test
    public void action(){
        // --------------------------------------
        // Открываю страницу почты ngs
        // --------------------------------------
        driver.get("https://mail.ngs.ru/");

        // --------------------------------------
        // Захожу в ПочтовыйЯщик1
        // --------------------------------------
        driver.findElement(By.xpath("//input[@id='login']")).sendKeys("user.testov");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("zxc67*Q");
        driver.findElement(By.xpath("//button[contains(@class,'ngsmail__login-submit')]")).click();
        String mailUser1 = driver.findElement(By.xpath("//td[@id='td_header_right1']")).getText();
        Assert.assertEquals("user.testov@ngs.ruВыход", mailUser1);

        // --------------------------------------
        // Отправляю письмо на ПочтовыйЯщик2
        // --------------------------------------
        driver.findElement(By.xpath("//a[@href='Compose.wssp?selectedAddressBook=[addressbook]&OpenBook=send']")).click();
        String newLetter = driver.findElement(By.xpath("//td[@id='folder_name']")).getText();
        Assert.assertEquals(" НОВОЕ ПИСЬМО", newLetter);
        driver.findElement(By.xpath("//input[@name='To']")).sendKeys("test9921@ngs.ru");
        int R1 = (int)(Math.random() * 10000); // случайное целое число в диапазоне [0; 10000)
        int R2 = (int)(Math.random() * 10000); // случайное целое число в диапазоне [0; 10000)
        String subject = "Тема " + R1 + "-" + R2; // тема письма
        String text = "Текст " + R1 + "-" + R2; // текст письма
        driver.findElement(By.xpath("//input[@name='Subject']")).sendKeys(subject);
        driver.findElement(By.xpath("//textarea[@name='Body']")).sendKeys(text);
        driver.findElement(By.xpath("//input[@name='Send']")).click();
        String messageSent = driver.findElement(By.xpath("//font[@color='green']")).getText();
        Assert.assertEquals("Сообщение отослано", messageSent);

        // --------------------------------------
        // Выхожу из ПочтовогоЯщика1
        // --------------------------------------
        driver.findElement(By.xpath("//a[@href='logout']")).click();

        // --------------------------------------
        // Захожу в ПочтовыйЯщик2
        // --------------------------------------
        driver.get("https://mail.ngs.ru/");
        driver.findElement(By.xpath("//input[@id='login']")).sendKeys("test9921");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("157RTq");
        driver.findElement(By.xpath("//button[contains(@class,'ngsmail__login-submit')]")).click();
        String mailUser2 = driver.findElement(By.xpath("//td[@id='td_header_right1']")).getText();
        Assert.assertEquals("test9921@ngs.ruВыход", mailUser2);

        // --------------------------------------
        // Убеждаюсь что письмо пришло
        // --------------------------------------
        WebElement parentElement = driver.findElement(By.xpath("//div[text()='"+subject+"']/../.."));
        WebElement childElement = parentElement.findElement(By.xpath("//div[contains(@class,'from_field')]"));
        childElement.click();

        String fromUser = driver.findElement(By.xpath("//td[text()='От']/../td[contains(@class,'mail_fields_data')]")).getText();
        Assert.assertEquals("<user.testov@ngs.ru>", fromUser);

        String messageSubject = driver.findElement(By.xpath("//td[text()='Тема']/../td[contains(@class,'mail_fields_data')]")).getText();
        Assert.assertEquals(subject, messageSubject);

        String toUser = driver.findElement(By.xpath("//td[text()='Кому']/../td[contains(@class,'mail_fields_data')]")).getText();
        Assert.assertEquals("test9921@ngs.ru", toUser);

        String messageText = driver.findElement(By.xpath("//div[contains(@class,'mail_content')]/tt")).getText();
        Assert.assertEquals(text, messageText);

        // --------------------------------------
        // Выхожу из ПочтовогоЯщика2
        // --------------------------------------
        driver.findElement(By.xpath("//a[@href='logout']")).click();
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
