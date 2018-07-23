package test_litecart.UserRegistrationTest_PageObject_3levels;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    // Опишем тут методы selectCountry и selectZone:

    public void selectCountry(String country) {
        driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(country + Keys.ENTER);
    }

    public void selectZone(String zone) {
        driver.findElement(By.xpath("//select[@name='zone_code']")).click();
        driver.findElement(By.xpath(String.format("//select[@name='zone_code']/option[@value='%s']", zone))).click();
    }

}
