/*
Описание теста:
1) Зайти в раздел Countries.
2) Проверить, что страны расположены в алфавитном порядке.
3) Для тех стран, у которых количество зон отлично от нуля - открыть страницу
этой страны и там проверить, что зоны расположены в алфавитном порядке.
*/

package test_litecart;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class CountriesAndZonesInAlphabetOrderTest {
    private static WebDriver driver;

    @BeforeClass
    public static void start(){
        System.out.print("***** Внутри метода start() *****\n\n");

        // System.setProperty("webdriver.ie.driver", "C:\\Tools\\IEDriverServer_Win32_3.12.0.exe");
        // driver = new InternetExplorerDriver(); // инициализация драйвера
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver(); // инициализация драйвера

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
    }

    @Test
    public void action(){
        System.out.print("***** Внутри метода action() *****\n\n");

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
        // Захожу в раздел Countries
        // --------------------------------------
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        // --------------------------------------
        // Проверить что страны расположены
        // в алфавитном порядке
        // --------------------------------------
        int amountOfCountries = driver.findElements(By.xpath("//td[@id='content']//tbody/tr[@class='row']")).size();
        System.out.print("Количество стран = " + amountOfCountries + "\n\n");

        for (int i=0; i<amountOfCountries; i++){
            // if (i>5 && i< 220) { continue; } // использую для отладки
            int i_plus_1=i+1, i_plus_2=i+2, i_plus_3=i+3;
            System.out.print(i_plus_1 + "-ая итерация внешнего цикла\n");
            String currentCountry = driver.findElement(By.xpath("//td[@id='content']//tbody/tr["+i_plus_2+"]//a")).getText();
            System.out.print("Текущая страна: " + currentCountry + "\n");

            if (i_plus_1 < amountOfCountries){
                String nextCountry = driver.findElement(By.xpath("//td[@id='content']//tbody/tr["+i_plus_3+"]//a")).getText();
                // if (i == 3) { nextCountry = "Aaaa country test"; } // для проверки падения теста
                System.out.print("Следующая страна: " + nextCountry + "\n");
                System.out.print("Проверяю что обе страны расположены в алфавитном порядке... ");
                Assert.assertTrue(currentCountry.compareTo(nextCountry) < 0);
                System.out.print("Верно!\n");
            }

            // Проверю количество зон у текущей страны. И если оно отлично от нуля, то открою
            // страницу этой страны и там проверю что зоны расположены в алфавитном порядке:
            int amountOfZonesInCurrentCountry = Integer.parseInt(
                    driver.findElement(By.xpath("//td[@id='content']//tbody/tr["+i_plus_2+"]/td[6]")).getText()
            );
            System.out.print("Количество зон в текущей стране = " + amountOfZonesInCurrentCountry + "\n\n");

            if (amountOfZonesInCurrentCountry != 0){
                driver.findElement(By.xpath("//td[@id='content']//tbody/tr["+i_plus_2+"]/td[5]/a")).click();
                System.out.print("    Открыл карточку текущей страны (" + currentCountry + ")\n\n");

                for (int j=0; j<amountOfZonesInCurrentCountry; j++){
                    int j_plus_1=j+1, j_plus_2=j+2, j_plus_3=j+3;
                    System.out.print("    " + j_plus_1 + "-ая итерация внутреннего цикла\n");
                    String currentZone = driver.findElement(By.xpath("//table[@id='table-zones']/tbody/tr["+j_plus_2+"]/td[3]")).getText();
                    System.out.print("    Текущая зона: " + currentZone + "\n");

                    if (j_plus_1 < amountOfZonesInCurrentCountry){
                        String nextZone = driver.findElement(By.xpath("//table[@id='table-zones']/tbody/tr["+j_plus_3+"]/td[3]")).getText();
                        // if (j == 5) { nextZone = "Aaaa zone test"; } // для проверки падения теста
                        System.out.print("    Следующая зона: " + nextZone + "\n");
                        System.out.print("    Проверяю что обе зоны расположены в алфавитном порядке... ");
                        Assert.assertTrue(currentZone.compareTo(nextZone) < 0);
                        System.out.print("Верно!\n\n");
                    }
                }

                System.out.print("\n" + "    Проверку зон для текущей страны закончил. Возвращаюсь к списку стран\n\n");
                driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']")).click();
            }
        }

        // --------------------------------------
        // Выхожу из панели администрирования
        // --------------------------------------
        driver.findElement(By.xpath("//a[@title='Logout']")).click();
    }

    @AfterClass
    public static void stop(){
        System.out.print("***** Внутри метода stop() *****\n\n");

        driver.quit();
        driver = null;
    }
}
