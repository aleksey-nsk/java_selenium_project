/*
Тест, который проходит по всем разделам админки. Описание теста:
- войти в панель администратора
- прокликнуть последовательно все пункты меню слева (включая вложенные пункты)
- для каждой страницы проверить наличие заголовка (то есть элемента с тегом h1)
*/

package test_litecart;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class PassAllAdminSectionTest {
    private static WebDriver driver;

    private boolean areElementsPresent(WebDriver driver, By locator){
        return driver.findElements(locator).size() > 0;
    }

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
        // Захожу в панель администратора
        // --------------------------------------
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String accountUser = driver.findElement(By.xpath("//div[contains(@class,'notice success')]")).getText();
        Assert.assertEquals("You are now logged in as admin", accountUser);

        // --------------------------------------
        // Прокликнуть все пункты меню/подменю
        // и осуществить необходимые проверки
        // --------------------------------------
        int amountMainMenuItems = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).size();
        for(int i=0; i<amountMainMenuItems; i++){
            int iIncrement = i+1;
            driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li["+iIncrement+"]")).click();
            System.out.println("\n\nПункт главного меню #" + iIncrement);

            if( areElementsPresent(driver, By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul[@class='docs']")) ){
                int amountSubMenuItems = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul[@class='docs']/li")).size();
                for(int j = 0; j < amountSubMenuItems; j++){
                    int jIncrement = j+1;
                    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul[@class='docs']/li["+jIncrement+"]")).click();
                    System.out.print(" подменю-" + iIncrement + "." + jIncrement);

                    // Проверить наличие заголовка (то есть элемента с тегом h1):
                    Assert.assertTrue(areElementsPresent(driver, By.xpath("//td[@id='content']/h1")));
                }
            }
            else {
                // Проверить наличие заголовка (то есть элемента с тегом h1):
                Assert.assertTrue(areElementsPresent(driver, By.xpath("//td[@id='content']/h1")));
            }
        }

        // --------------------------------------
        // Выхожу из панели администрирования
        // --------------------------------------
        driver.findElement(By.xpath("//a[@title='Logout']")).click();
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }
}
