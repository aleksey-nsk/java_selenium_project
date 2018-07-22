package test_page_object.LoginTest_2_update.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// LoginPage — класс содержащий описание страницы логина:
public class LoginPage {

    // Конструктор класса:
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement loginField; // поле логина

    @FindBy(xpath = "//input[@id='pass']")
    private WebElement passwordField; // поле для ввода пароля

    @FindBy(xpath = "//button[contains(@class,'ngsmail__login-submit')]")
    private WebElement loginButton; // кнопка логина

    // Вёб-элементы есть. Теперь можно описать методы
    // для взаимодействия с ними:

    // Метод для ввода логина:
    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    // Метод для ввода пароля:
    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    // Метод для нажатия кнопки входа:
    public void clickLoginButton() {
        loginButton.click();
    }
}
