/*
Объект типа ChromeOptions можно
напрямую передать в конструктор ChromeDriver() то есть:
driver = new ChromeDriver(options);
и в этом случае он будет автоматически преобразован
в объект типа капабилитиз и использован.

Но если мы хотим указать некоторые дополнительные капабилитиз,
то тогда можно вызвать конструктор в который передаются капабилитиз.
И туда же внутрь этого объекта типа DesiredCapabilities
упаковать опции командной строки.
*/

package different_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeOptionsAndCapabilitiesTest {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(caps);
    }

    @Test
    public void action(){
        driver.get("https://yandex.ru/");
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.titleIs("Яндекс"));
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
