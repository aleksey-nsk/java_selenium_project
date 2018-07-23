package test_litecart.UserRegistrationTest_PageObject_3levels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Данный класс описывает страницу регистрации.
// Информация об адресе этой страницы и о локаторах элементов
// сосредоточена исключительно в пределах этого класса.
// (а как выглядит использование этого класса - см. в класс Application)
public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Метод open() открывает эту страницу регистрации:
    public void open() {
        driver.get("http://localhost/litecart/en/create_account");
    }

    // Методы котрые находят и возвращают элементы
    // расположенные на этой странице:

    public WebElement firstnameInput() {
        return driver.findElement(By.xpath("//input[@name='firstname']"));
    }

    public WebElement lastnameInput() {
        return driver.findElement(By.xpath("//input[@name='lastname']"));
    }

    public WebElement adressInput() {
        return driver.findElement(By.xpath("//input[@name='address1']"));
    }

    public WebElement postcodeInput() {
        return driver.findElement(By.xpath("//input[@name='postcode']"));
    }

    public WebElement cityInput() {
        return driver.findElement(By.xpath("//input[@name='city']"));
    }

    public WebElement emailInput() {
        return driver.findElement(By.xpath("//input[@name='email']"));
    }

    public WebElement phoneInput() {
        return driver.findElement(By.xpath("//input[@name='phone']"));
    }

    public WebElement passwordInput() {
        return driver.findElement(By.xpath("//input[@name='password']"));
    }

    public WebElement confirmedPasswordInput() {
        return driver.findElement(By.xpath("//input[@name='confirmed_password']"));
    }

    public WebElement createAccountButton() {
        return driver.findElement(By.xpath("//button[@name='create_account']"));
    }
}

/*
        public void registerNewCustomer(Customer customer) {
        // Регистрируем нового клиента:
        // (МОЖЕТ ПЕРЕД РЕГИСТРАЦИЕЙ ВЫЙТИ ИЗ АДМИНКИ ?????????)
        System.out.print("Открываю страницу Create Account...\n");
        driver.get("http://localhost/litecart/en/create_account");
        System.out.print("Заполняю форму регистрации...\n");
        // driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(customer.getFirstname());
        // driver.findElement(By.xpath("//input[@name='city']")).sendKeys(customer.getCity());
        driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(customer.getCountry() + Keys.ENTER);
        driver.findElement(By.xpath("//select[@name='zone_code']")).click();
        driver.findElement(By.xpath(String.format("//select[@name='zone_code']/option[@value='%s']", customer.getZone()))).click();
        // driver.findElement(By.xpath("//input[@name='email']")).sendKeys(customer.getEmail());
        // driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(customer.getPhone());
        // driver.findElement(By.xpath("//input[@name='password']")).sendKeys(customer.getPassword());
        // driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(customer.getPassword());
        System.out.print("Нажимаю кнопку Create Account...\n");
        driver.findElement(By.xpath("//button[@name='create_account']")).click();

        // НАВЕРНО ВЫХОД НАДО ВЫНЕСТИ
        // КАК ОТДЕЛЬНЫЙ МЕТОД !!!!!!!
        System.out.print("Выхожу из созданной учётки (Logout)... ");
        driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
        String logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
        // logOutMessage = "Тестовое сообщение 2"; // для проверки падения теста
        Assert.assertEquals("You are now logged out.", logOutMessage);
        System.out.print("Успех!\n");
    }
*/