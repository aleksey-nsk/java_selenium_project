/*
Описание теста:

Задание 14. Проверьте, что ссылки открываются в новом окне.
Сделайте сценарий, который проверяет, что ссылки на странице
редактирования страны открываются в новом окне.

1) зайти в админку.
2) открыть пункт меню Countries.
3) открыть на редактирование какую-нибудь страну.
4) возле некоторых полей есть ссылки с иконкой
в виде квадратика со стрелкой - они ведут на внешние страницы
и открываются в новом окне, именно это и нужно проверить.

Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank".
Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась
в новом окне, потом переключиться в новое окно, закрыть его, вернуться обратно,
и повторить эти действия для всех таких ссылок. Не забудьте, что новое окно
открывается не мгновенно, поэтому требуется ожидание открытия окна.
*/

package test_litecart;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LinksInNewWindowTest {
    private static WebDriver driver;

    // Ожидание появления нового окна:
    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next() : null;
            }
        };
    }

    @BeforeClass
    public static void start(){
        System.out.print("\n\n***** Внутри метода start() *****\n\n");

        System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        driver = new InternetExplorerDriver(); // инициализация драйвера

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
    }

    @Test
    public void action() throws InterruptedException {
        System.out.print("\n\n***** Внутри метода action() *****\n\n");

        System.out.print("Захожу в панель администрирования\n");
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String accountUser = driver.findElement(By.xpath("//div[contains(@class,'notice success')]")).getText();
        Assert.assertEquals("You are now logged in as admin", accountUser);

        System.out.print("Захожу в раздел Countries\n");
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[3]")).click();

        // System.out.print("Например, открою страницу редактирования Албании\n");
        // driver.findElement(By.xpath("//form[@name='countries_form']//tbody/tr[3]/td[7]/a")).click();
        System.out.print("Например, открою страницу редактирования Бразилии\n");
        driver.findElement(By.xpath("//form[@name='countries_form']//tbody/tr[31]/td[7]/a")).click();

        System.out.print("Заголовок страницы редактирования = ");
        String originalTitle = driver.getTitle();
        System.out.print(originalTitle + "\n");

        System.out.print("Ищу ссылки с иконкой в виде квадратика со стрелкой...\n");
        int amountOfExternalLinks = driver.findElements(By.xpath("//i[@class='fa fa-external-link']")).size();
        System.out.print("  всего таких ссылок: " + amountOfExternalLinks + "\n");
        System.out.print("  убедимся что все эти ссылки открываются в новом окне\n");

        for (int i=0; i<amountOfExternalLinks; i++){
            System.out.print("\nПроверяю текущую ссылку номер: " + (i+1) + " ...\n");

            System.out.print("  запоминаю идентификатор текущего окна: ");
            String currentWindow = driver.getWindowHandle();
            System.out.print("currentWindow = " + currentWindow + "\n");

            System.out.print("  запоминаю идентификаторы уже открытых окон: ");
            Set<String> existingWindows = driver.getWindowHandles();
            System.out.print("existingWindows = " + existingWindows + "\n");

            System.out.print("  кликаю ссылку которая открывает новое окно\n");
            driver.findElements(By.xpath("//i[@class='fa fa-external-link']")).get(i).click();

            System.out.print("  жду появления нового окна с новым идентификатором...\n");
            String newWindow = (new WebDriverWait(driver, 5)).until(anyWindowOtherThan(existingWindows));
            System.out.print("    появилось! newWindow = " + newWindow + "\n");

            System.out.print("  переключаюсь в новое окно\n");
            driver.switchTo().window(newWindow);

            System.out.print("  убедимся что переключились куда надо...\n");
            Set<String> newExistingWindows = driver.getWindowHandles();
            System.out.print("    newExistingWindows = " + newExistingWindows + "\n");
            String newCurrentWindow = driver.getWindowHandle();
            // newCurrentWindow = "CDwindow-00112233"; // для проверки падения теста
            System.out.print("    newCurrentWindow = " + newCurrentWindow + "\n");
            Assert.assertEquals(newWindow, newCurrentWindow);
            System.out.print("    Верно!\n");

            System.out.print("  закрываю это новое окно\n");
            driver.close();

            System.out.print("  возвращаюсь в исходное окно\n");
            driver.switchTo().window(currentWindow);

            System.out.print("  проверю заголовок страницы...\n");
            String finalTitle = driver.getTitle();
            // finalTitle = "Test Title"; // для проверки падения теста
            System.out.print("    ОР = " + originalTitle + "\n    ФР = " + finalTitle + "\n");
            Assert.assertEquals(originalTitle, finalTitle);
            System.out.print("    Верно!\n");

            // if (i == 1) break; // использую для отладки
        }

        System.out.print("\nНичего редактировать не буду. Жму кнопку Cancel\n");
        driver.findElement(By.xpath("//button[@name='cancel']")).click();
        System.out.print("Выхожу из панели администрирования\n");
        driver.findElement(By.xpath("//a[@title='Logout']")).click();
    }

    @AfterClass
    public static void stop(){
        System.out.print("\n\n***** Внутри метода stop() *****\n\n");

        driver.quit();
        driver = null;
    }
}
