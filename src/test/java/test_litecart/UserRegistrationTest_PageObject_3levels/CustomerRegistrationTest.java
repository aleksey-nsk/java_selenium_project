package test_litecart.UserRegistrationTest_PageObject_3levels;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@RunWith(DataProviderRunner.class)
public class CustomerRegistrationTest extends TestBase {

    @Test
    @UseDataProvider(value = "validCustomers", location = DataProviders.class)
    public void action(Customer customer /*тестовые данные передаём как параметр*/) {
        System.out.print("\n***** Внутри метода action() *****\n\n");

        /* Перед созданием нового клиента запоминаем текущее множество айдишников:
           Set<String> oldIds = getCustomerIds(); */
        // Получаем из приложения множество идентификаторов клиентов:
        Set<String> oldIds = app.getCustomerIds();

        // registerNewCustomer(customer);
        // Создаём в приложении нового клиента:
        app.registerNewCustomer(customer);

        /* Далее получаю новый список айдишников клиентов:
           Set<String> newIds = getCustomerIds(); */
        // Получаем из приложения новый список идентификаторов:
        Set<String> newIds = app.getCustomerIds();

        // Выполняем сравнение:
        // Новое множество содержит все айдишники старого множества, т.е.
        // никого нечайно не удалили:
        Assert.assertTrue(newIds.containsAll(oldIds));
        // Во-вторых в новом множестве появился еще один
        // дополнительный элемент (новый зарегистрировавшийся клиент):
        Assert.assertTrue(newIds.size() == oldIds.size() + 1);
    }
}

        /*
        // Регистрируем нового клиента:
        // (МОЖЕТ ПЕРЕД РЕГИСТРАЦИЕЙ ВЫЙТИ ИЗ АДМИНКИ ?????????)
        System.out.print("Открываю страницу Create Account...\n");
        driver.get("http://localhost/litecart/en/create_account");
        System.out.print("Заполняю форму регистрации...\n");
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(customer.getFirstname());
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(customer.getLastname());
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(customer.getAddress());
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(customer.getPostcode());
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(customer.getCity());
        driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(customer.getCountry() + Keys.ENTER);
        driver.findElement(By.xpath("//select[@name='zone_code']")).click();
        driver.findElement(By.xpath(String.format("//select[@name='zone_code']/option[@value='%s']", customer.getZone()))).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(customer.getEmail());
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(customer.getPhone());
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(customer.getPassword());
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(customer.getPassword());
        System.out.print("Нажимаю кнопку Create Account...\n");
        driver.findElement(By.xpath("//button[@name='create_account']")).click();
        */

        /*
        // Далее получаю новый список айдишников клиентов:
        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        Set<String> newIds = driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
        */


        /* System.out.print("Убеждаюсь что регистрация прошла успешно... ");
        String registrationMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
        // registrationMessage = "Тестовое сообщение 1"; // для проверки падения теста
        Assert.assertEquals("Your customer account has been created.", registrationMessage);
        System.out.print("Успех!\n"); */

        /* System.out.print("Выхожу из созданной учётки (Logout)... ");
        driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
        String logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
        // logOutMessage = "Тестовое сообщение 2"; // для проверки падения теста
        Assert.assertEquals("You are now logged out.", logOutMessage);
        System.out.print("Успех!\n"); */

        /* System.out.print("Повторно захожу (Login) в только что созданную учётную запись... ");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String expectedResult = "You are now logged in as " + firstName + " " + lastName + ".";
        String logInMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
        // logInMessage = "Тестовое сообщение 3"; // для проверки падения теста
        Assert.assertEquals(expectedResult, logInMessage);
        System.out.print("Успех!\n"); */

        /* System.out.print("Повторно выхожу из созданной учётки (Logout)... ");
        driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
        logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
        // logOutMessage = "Тестовое сообщение 4"; // для проверки падения теста
        Assert.assertEquals("You are now logged out.", logOutMessage);
        System.out.print("Успех!\n"); */
