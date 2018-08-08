package litecart_using_page_object.app;

import litecart_using_page_object.pages.AdminPanelLoginPage;
import litecart_using_page_object.pages.HomePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import litecart_using_page_object.model.Customer;
import litecart_using_page_object.pages.CustomerListPage;
import litecart_using_page_object.pages.RegistrationPage;
import java.util.Set;

// В тестах вообще нигде не видно что используется Селениум.
// Внутрь класса Application прячем все технические подробности.
// Только в этом классе видим что используется Селениум.
// Именно тут создаётся драйвер, и здесь же он используется:
public class Application {

  private WebDriver driver;
  private RegistrationPage registrationPage;
  private AdminPanelLoginPage adminPanelLoginPage;
  private CustomerListPage customerListPage;
  private HomePage homePage;

  public Application() {
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    driver.manage().window().maximize();
    registrationPage = new RegistrationPage(driver);
    adminPanelLoginPage = new AdminPanelLoginPage(driver);
    customerListPage = new CustomerListPage(driver);
    homePage = new HomePage(driver);
  }

  public void quit() {
    driver.quit();
  }

  public void registerNewCustomer(Customer customer) {
    System.out.println("Метод для регистрации нового клиента");

    registrationPage.open();

    System.out.println("Заполняю форму регистрации");
    registrationPage.firstnameInput.sendKeys(customer.getFirstname());
    registrationPage.lastnameInput.sendKeys(customer.getLastname());
    registrationPage.adressInput.sendKeys(customer.getAddress());
    registrationPage.postcodeInput.sendKeys(customer.getPostcode());
    registrationPage.cityInput.sendKeys(customer.getCity());
    registrationPage.selectCountry(customer.getCountry());
    registrationPage.selectZone(customer.getZone());
    registrationPage.emailInput.sendKeys(customer.getEmail());
    registrationPage.phoneInput.sendKeys(customer.getPhone());
    registrationPage.passwordInput.sendKeys(customer.getPassword());
    registrationPage.confirmedPasswordInput.sendKeys(customer.getPassword());

    System.out.println("Нажимаю кнопку Create Account");
    registrationPage.createAccountButton.click();

    homePage.logOut();
  }

  public Set<String> getCustomerIds() {
    System.out.println("Метод для получения множества идентификаторов клиентов");
    if (adminPanelLoginPage.open().isOnThisPage()) {
      adminPanelLoginPage.enterUsername("admin").enterPassword("admin").submitLogin();
      // - тут методы вытягиваются в цепочку (это так называемый флуент-интерфейс, особый стиль написания кода).
      // Для реализации этого стиля надо чтобы методы возвращали готовый объект-страницу (return this;)
    }
    return customerListPage.open().getCustomerIds();
    // - в итоге оба объекта-страницы не возвращают никаких элементов, находящихся на этих страницах.
    // Они предоставляют сервисы (предоставляют услуги) по заполнению формы и выполнению входа.
    // И услугу по получению списка идентификаторов клиентов.
  }
}
